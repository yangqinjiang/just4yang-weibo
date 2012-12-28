package cn.yang.sina.weibo.db;

import java.util.List;

import android.graphics.Bitmap;
import cn.yang.sina.weibo.beans.BaseEmotions;
import weibo4android.Emotion;
import cn.yang.weibo4android.User;
import cn.yang.weibo4android.Users;

import com.weibo.net.AccessToken;

/**
 * 数据库代理类
 * TODO 20120507
 * @author yangqinjiang
 *
 */
public class DBAgent implements IDB{

	/**idb*/
	private static IDB idb;
	/**
	 * 私有构造方法
	 */
	private DBAgent(){
		idb = DBService.getDBServiceInstance();
	}
	/**采用单例模式生成一个dbAgent*/
	private static final DBAgent dbAgent = new DBAgent();
	/**单例模式的方法*/
	public static DBAgent getDBAgentInstance(){
		return dbAgent;
	}
	public boolean saveUserInfo2DB(User user, AccessToken accessToken) {
		if(user==null&&accessToken==null){
			throw new IllegalArgumentException("user (or accessToken) is null or empty");
		}else{
			try{
				return idb.saveUserInfo2DB(user, accessToken);
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				//TODO 应该写关闭数据库操作
			}
		}
		return false;
	}


	public boolean userInfoIsExists(long userId) {
		if(userId<0){
			throw new IllegalArgumentException("userId<0");
		}else{
			try {
				return idb.userInfoIsExists(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//TODO 应该写关闭数据库操作
			}
		}
		return false;
	}


	public boolean updataUserInfo(User user, AccessToken accessToken) {
		if(user==null&&accessToken==null){
			throw new IllegalArgumentException("user is null and accessToken is null");
		}else{
			try{
				return idb.updataUserInfo(user, accessToken);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				//TODO 应该写关闭数据库操作
			}
		}
		return false;
	}


	public Bitmap getUserIconFromDB(String userId) {
		if(userId.isEmpty()||userId==null){
			throw new IllegalArgumentException("userId is isEmpty or userId is null");
		}else{
			try {
				return idb.getUserIconFromDB(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//TODO 应该写关闭数据库操作
			}
		}
		return null;//TODO 这里应该返回一张默认图片
	}

	public Users getUsers() {
		Users userTemp = idb.getUsers();
		if(userTemp==null)
			return null;//应该抛出一个异常信息
		//TODO 应该写关闭数据库操作
		return userTemp;
	}


	public User getUser(String userId) {
		if(userId.isEmpty()||userId==null){
			throw new IllegalArgumentException("userId is isEmpty or userId is null");
		}else{
			try {
				return idb.getUser(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//TODO 应该写关闭数据库操作
			}
		}
		return null;
	}


	public AccessToken getAccessTokenFromDB(String userId) {
		if(userId.isEmpty()||userId==null){
			throw new IllegalArgumentException("userId is isEmpty or userId is null");
		}else{
			try {
				return idb.getAccessTokenFromDB(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//TODO 应该写关闭数据库操作
			}
		}
		return null;
	}


	public Boolean saveEmotions2DB(Emotion emotion) {
		if(null==emotion){
			throw new IllegalArgumentException("emotion is null");
		}else{
			try {
				return idb.saveEmotions2DB(emotion);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//TODO 应该写关闭数据库操作
			}
		}
		return false;
	}


	public List<BaseEmotions> getEmotionsFromDB() {
		List<BaseEmotions> e =idb.getEmotionsFromDB();
		if(e==null)
			return null;//应该抛出一个异常信息
		return e;
	}


	public Bitmap getOneEmotionFromDB(String phrase) {
		if(phrase.isEmpty()||phrase==null){
			throw new IllegalArgumentException("phrase is isEmpty or userId is null");
		}else{
			try {
				return idb.getOneEmotionFromDB(phrase);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//TODO 应该写关闭数据库操作
			}
		}
		return null;//TODO 这里应该返回一张默认图片
	}
}
