package cn.yang.sina.weibo.db;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import weibo4android.Emotion;
import cn.yang.weibo4android.User;
import cn.yang.weibo4android.Users;

import com.weibo.net.AccessToken;
import cn.yang.sina.weibo.beans.BaseEmotions;
import cn.yang.sina.weibo.beans.DBConstant;
import cn.yang.sina.weibo.beans.app.WeiboApp;
import cn.yang.sina.weibo.ui.util.NetUtil;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
/**
 * TODO 20120904 使用模板方法来重构这个类
 * 操作微博用户数据库及其信息的类
 * @author yangqinjiang
 *
 */
public  class DBService implements IDB{

	private static final String TAG = "DBSerivce";
	/**
	 * 数据库助手
	 */
	private DBHelper dBHelper;
	/**
	 * 构造方法
	 * @param context 上下文
	 */
	private DBService(Context context){
		this.dBHelper = new DBHelper(context);
	}
	/**单例模式*/
	private static DBService dbService = new DBService(WeiboApp.context);
	/**单例模式方法*/
	protected static DBService getDBServiceInstance(){
		return dbService;
	}

	public boolean saveUserInfo2DB(User user,AccessToken accessToken){
		Log.i(TAG,"开始将用户信息UserInfo插入数据库...");
		//TODO 1 获取SQLiteDatabase对象
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();
		//TODO 2生成一个CotentValues对象，并设置相应的值
		ContentValues values = new ContentValues(5);
		
		values.put(DBConstant.USER_ID, user.getId());
		values.put(DBConstant.USER_NAME, user.getName());

		Drawable userIcon=null;
		try {
			userIcon = NetUtil.getNetImage(new URL(user.getProfile_image_url()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		BitmapDrawable tempDrawable = (BitmapDrawable)userIcon;
		ByteArrayOutputStream os= new ByteArrayOutputStream();
		tempDrawable.getBitmap().compress(CompressFormat.PNG, 100, os);
		values.put(DBConstant.USER_HEAD_PORTRAIT, os.toByteArray());
		
		values.put(DBConstant.TOKEN,accessToken.getToken());
		values.put(DBConstant.TOKEN_SECRET, accessToken.getSecret());
		
		//TODO 3 调用SQLiteDatabase对象的相关方法
		long rowId = sqLiteDatabase.insert(DBConstant.TABLE_NAME, null, values);
		Log.i(TAG,"已经将用户信息UserInfo插入数据库...");
		//TODO 4返回操作结果
		if(rowId>0){
			Log.i(TAG,"插入成功");
			return true;
		}else{
			Log.e(TAG,"插入失败");
			return false;
		}
		
	}

	public boolean userInfoIsExists(long userId){
		Log.i(TAG,"使用用户的ID，查询用户信息是否存在数据库中...");
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.query(DBConstant.TABLE_NAME,
							new String[]{DBConstant.USER_ID}, 
							DBConstant.USER_ID+"=?",
							new String[]{String.valueOf(userId)},
							null, null, null);
		if(null !=cursor){
			if(cursor.getCount()>0){
				Log.i(TAG,"存在这个用户ID，返回true,不需要将该用户信息插入数据库中");
				cursor.close();
				//closeDB(sqLiteDatabase);
				return true;
			}
		}
		Log.i(TAG,"不存在这个用户ID，返回false,需要将该用户信息插入数据库中");
		cursor.close();
		return false;
	}

	public boolean updataUserInfo(User user,AccessToken accessToken){
		Log.i(TAG,"在数据库中更新用户信息...");
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues(5);
		values.put(DBConstant.USER_NAME, user.getName());
		Drawable userIcon=null;
		try {
			userIcon = NetUtil.getNetImage(new URL(user.getProfile_image_url()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		BitmapDrawable tempDrawable = (BitmapDrawable)userIcon;
		ByteArrayOutputStream os= new ByteArrayOutputStream();
		tempDrawable.getBitmap().compress(CompressFormat.PNG, 100, os);
		values.put(DBConstant.USER_HEAD_PORTRAIT, os.toByteArray());
		values.put(DBConstant.TOKEN,accessToken.getToken());
		values.put(DBConstant.TOKEN_SECRET, accessToken.getSecret());
		
		int rowsOfAffected = sqLiteDatabase.update(DBConstant.TABLE_NAME,
								values, 
								DBConstant.USER_ID+"=?",
								new String[]{String.valueOf(user.getId())});
		if(rowsOfAffected==1){
			Log.i(TAG,"更新用户信息成功...");
			//closeDB(sqLiteDatabase);
			return true;
		}else{
			//closeDB(sqLiteDatabase);
			Log.i(TAG,"更新用户信息失败...");
			return false;
		}
		
		
	}

	public Bitmap getUserIconFromDB(String userId){
		Log.i(TAG,"从数据库中读取用户头像数据...");
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();

		Cursor cursor = sqLiteDatabase.query(DBConstant.TABLE_NAME,
				new String[]{DBConstant.USER_ID,DBConstant.USER_NAME,DBConstant.USER_HEAD_PORTRAIT}, 
				DBConstant.USER_ID+"=?",
				new String[]{String.valueOf(userId)},
				null, null, null);
		
		Bitmap bmp ;
		if(null !=cursor){
				if(cursor.getCount()>0){
						cursor.moveToFirst();
						byte[] blob = cursor.getBlob(cursor.getColumnIndex(DBConstant.USER_HEAD_PORTRAIT));
						bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
						Log.i(TAG,"从数据库中读取出来了...");
						cursor.close();
						return bmp;
				}
		}
		return null;

	}

	public Users getUsers(){
		Log.i(TAG,"从数据库中读取全部用户的信息，只包括ID，姓名，头像...");
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();
		
		Cursor cursor = sqLiteDatabase.query(DBConstant.TABLE_NAME,
				new String[]{DBConstant.USER_ID,DBConstant.USER_NAME,DBConstant.USER_HEAD_PORTRAIT}, 
				null,null,null, null, null);
		
		Users users = new Users(cursor.getCount());
		while(cursor.moveToNext()){
			
			long id = cursor.getLong(cursor.getColumnIndex(DBConstant.USER_ID));
			String name = cursor.getString(cursor.getColumnIndex(DBConstant.USER_NAME));
			
			Log.i(TAG, "ID:"+id +" Name:"+name);
			
			byte[] blob = cursor.getBlob(cursor.getColumnIndex(DBConstant.USER_HEAD_PORTRAIT));
			Bitmap tempBmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
			BitmapDrawable userIcon = new BitmapDrawable(tempBmp);
			users.getUsers().add(new User(id,name));
			
			
		}
		cursor.close();
		return users;
	}

	public User getUser(String userId){
		Log.i(TAG,"从数据库中读取当前刚刚授权用户的信息，只包括ID，姓名，头像...");
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();
		
		User user =null;
		
		Cursor cursor = sqLiteDatabase.query(DBConstant.TABLE_NAME,
				new String[]{DBConstant.USER_ID,DBConstant.USER_NAME,DBConstant.USER_HEAD_PORTRAIT}, 
				DBConstant.USER_ID+"=?",
				new String[]{String.valueOf(userId)},
				null, null, null);
		if(null !=cursor){
			if(cursor.getCount()>0){
					cursor.moveToFirst();
					long id = cursor.getLong(cursor.getColumnIndex(DBConstant.USER_ID));
					String name = cursor.getString(cursor.getColumnIndex(DBConstant.USER_NAME));
					byte[] blob = cursor.getBlob(cursor.getColumnIndex(DBConstant.USER_HEAD_PORTRAIT));
					Bitmap tempBmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
					BitmapDrawable userIcon = new BitmapDrawable(tempBmp);
					user = new User(id, name/*, userIcon*/);
					cursor.close();
					return user;
			}
		}
		return null;
	}
	
	public AccessToken getAccessTokenFromDB(String userId){
		Log.i(TAG,"使用UserID从数据库中找出该用户的token和tokenSecret");
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();
		//User user =null;
		Cursor cursor = sqLiteDatabase.query(DBConstant.TABLE_NAME,
				new String[]{DBConstant.USER_ID,DBConstant.TOKEN,DBConstant.TOKEN_SECRET}, 
				DBConstant.USER_ID+"=?",
				new String[]{userId},
				null, null, null);
		AccessToken accessToken =null;
		if(null !=cursor){
			if(cursor.getCount()>0){
					cursor.moveToFirst();
					String token = cursor.getString(cursor.getColumnIndex(DBConstant.TOKEN));
					String tokenSecret = cursor.getString(cursor.getColumnIndex(DBConstant.TOKEN_SECRET));
					accessToken = new AccessToken(token, tokenSecret); 
					cursor.close();
					return accessToken;
			}
		}

		return null;
	}

	public Boolean saveEmotions2DB(Emotion e){
		Log.i(TAG,"将官方表情信息保存到DB中...");
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues(8);
		values.put(DBConstant.PHRASE,e.getPhrase());
		values.put(DBConstant.TYPE,e.getType());
		values.put(DBConstant.URL,e.getUrl());
		values.put(DBConstant.IS_HOT,e.isIs_hot());
		values.put(DBConstant.IS_COMMON,e.isIs_common());
		values.put(DBConstant.ORDER_NUMBER,e.getOrder_number());
		values.put(DBConstant.CATEGORY,e.getCategory());
		
		
		Drawable userIcon=null;
		try {
			userIcon = NetUtil.getNetImage(new URL(e.getUrl()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		BitmapDrawable tempDrawable = (BitmapDrawable)userIcon;
		ByteArrayOutputStream os= new ByteArrayOutputStream();
		tempDrawable.getBitmap().compress(CompressFormat.PNG, 100, os);
		values.put(DBConstant.EMOTICON, os.toByteArray());
		
		long rowId = sqLiteDatabase.insert(DBConstant.WEIBO_EMOTION_TN, null, values);
		Log.i(TAG,"已经将官方表情信息保存到DB中...");
		if(rowId>0){
			Log.i(TAG,"插入成功");
			return true;
		}else{
			Log.i(TAG,"插入失败");
			return false;
		}
		
	}

	public List<BaseEmotions> getEmotionsFromDB(){
		Log.i(TAG,"从数据库中找到对应的表情图片...!!!!!!!");
		
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();
		
		Cursor cursor = sqLiteDatabase.query(DBConstant.WEIBO_EMOTION_TN,
				new String[]{DBConstant.PHRASE,DBConstant.EMOTICON,DBConstant.URL}, 
				DBConstant.CATEGORY+"=?",
				new String[]{"天气"},
				null, null, null);
		
		List<BaseEmotions> allEmotions=new ArrayList<BaseEmotions>();//全部表情集合
		BaseEmotions baseEmotions;
		if(null !=cursor){
			cursor.moveToFirst();
			while(cursor.moveToNext()){
						byte[] blob = cursor.getBlob(cursor.getColumnIndex(DBConstant.EMOTICON));
						Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
						baseEmotions =new BaseEmotions();
						
						baseEmotions.setBmpEmotion(bmp);
						String name = cursor.getString(cursor.getColumnIndex(DBConstant.PHRASE));
						baseEmotions.setEmotionName(name);
						allEmotions.add(baseEmotions);
				}
				cursor.close();

			return allEmotions;
			}
		return null;

	}
	
	public Bitmap getOneEmotionFromDB(String phrase){
		Log.i(TAG,"使用phrase从数据库中找到对应的表情图片...");
		
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();
		
		Cursor cursor = sqLiteDatabase.query(DBConstant.WEIBO_EMOTION_TN,
				new String[]{DBConstant.PHRASE,DBConstant.EMOTICON,DBConstant.URL}, 
				DBConstant.PHRASE+"=?",
				new String[]{phrase},
				null, null, null);
		
		if(null !=cursor){
			if(cursor.getCount()>0){
						cursor.moveToFirst();
						byte[] blob = cursor.getBlob(cursor.getColumnIndex(DBConstant.EMOTICON));
						Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
						cursor.close();
						return bmp;
				}
			}
		return null;

	}
}
