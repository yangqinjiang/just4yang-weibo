package cn.yang.sina.weibo.db;

import java.net.MalformedURLException;
import java.util.List;

import weibo4android.Emotion;
import cn.yang.weibo4android.User;
import cn.yang.weibo4android.Users;

import com.weibo.net.AccessToken;
import android.graphics.Bitmap;
import cn.yang.sina.weibo.beans.BaseEmotions;

public interface IDB {

	/**
	 * 将用户信息UserInfo保存到数据库
	 * @param user 用户 
	 * @param accessToken 授权后的token
	 * @return true 表示插入数据成功，false表示插入数据失败
	 */
	public abstract boolean saveUserInfo2DB(User user, AccessToken accessToken);

	/**
	 * 查找用户是否存在数据库中
	 * @param userId 用户的ID
	 * @return 返回true,说明已经存在这个用户ID,不需要将该用户信息插入数据库中,返回false,说明不存在这个用户ID,需要将该用户信息插入数据库中
	 */
	public abstract boolean userInfoIsExists(long userId);

	//TODO 20120508  在DBAegnt代理类中调用这个方法
	/**
	 * 更新用户信息
	 * @param user  用户bean 
	 * @param accessToken 访问授权bean
	 * @return 如果返回true，则说明更新用户信息成功，如果返回false,则说明更新用户信息失败
	 */
	public abstract boolean updataUserInfo(User user, AccessToken accessToken);

	/**
	 * 
	 * 从数据库中读取用户头像：
	 * byte[] blob = cur.getBlob(cur.getColumnIndex(KEY_IMG));  
	 * Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
	 */
	public abstract Bitmap getUserIconFromDB(String userId);

	/**
	 * 从数据库中读取全部用户的信息，包括ID，姓名，头像
	 * @return 用户实例   user
	 */
	public abstract Users getUsers();

	/**
	 * 从数据库中读取当前刚刚授权用户的信息，只包括ID，姓名，头像...
	 * @param userId  当前刚刚授权用户的ID
	 * @return user实例
	 */
	public abstract User getUser(String userId);

	/**
	 * 使用UserID从数据库中找出该用户的token和tokenSecret
	 * @param UserID  用户ID
	 * @return  返回AccessToken访问令牌
	 */
	public abstract AccessToken getAccessTokenFromDB(String userId);

	/**
	 * 将官方表情信息保存到DB中
	 * @param e  每一个表情信息
	 * @return 
	 * @throws MalformedURLException 
	 */
	public abstract Boolean saveEmotions2DB(Emotion e);

	/**
	 * 使用phrase从数据库中找到对应的表情图片
	 * @param phrase   在微博内容上显示的，类似这样  [xx]
	 * @return	返回对应的图片
	 */
	public abstract List<BaseEmotions> getEmotionsFromDB();

	/**
	 * 使用phrase从数据库中找到对应的表情图片
	 * @param phrase   在微博内容上显示的，类似这样  [xx]
	 * @return	返回对应的图片
	 */
	public abstract Bitmap getOneEmotionFromDB(String phrase);

}