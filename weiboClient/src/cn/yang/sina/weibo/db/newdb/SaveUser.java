package cn.yang.sina.weibo.db.newdb;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.weibo.net.AccessToken;

import cn.yang.sina.weibo.beans.DBConstant;
import cn.yang.sina.weibo.ui.util.NetUtil;
import cn.yang.weibo4android.User;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class SaveUser<T> extends UserDBHandle<T> {

	private User user;
	private AccessToken token;
	protected SaveUser(User user,AccessToken token) {
		this.user=user;
		this.token=token;
	}


	@Override
	ContentValues createContentValue() {
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
		
		values.put(DBConstant.TOKEN,token.getToken());
		values.put(DBConstant.TOKEN_SECRET, token.getSecret());
		
		return values;
	}

	@Override
	T operateDB(SQLiteDatabase sd, ContentValues values) {
		long row =sd.insert(DBConstant.TABLE_NAME, null, values);
		return null;
	}

	
	public static void main(String[] args) {
		
	}

}
