package cn.yang.weibo4android.util.asynTask;

import com.weibo.net.AccessToken;
import com.weibo.net.Weibo;

import cn.yang.sina.weibo.beans.OAuthConstant;
import cn.yang.sina.weibo.db.DBAgent;
import cn.yang.sina.weibo.db.IDB;
import cn.yang.weibo4android.User;
import cn.yang.weibo4android.util.UserProfile;
import cn.yang.weibo4android.util.WeiboGetter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
/**
 * 【OAuth 2.0授权】异步任务类
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class OAuth2Worker extends WeiboWorker{
	
		/**dbAgent 数据库代理类实例(单例模式)*/
		private static IDB dbAgent = DBAgent.getDBAgentInstance();
		/**
		 * @param weibo  通过Weibo.getInstance()获取
		 * @param receiverActionName  广播接收者的action名称
		 * @param keyOfResult  用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
		 */
		public OAuth2Worker(Weibo weibo,String receiverActionName,String keyOfResult){
			super(weibo, receiverActionName, keyOfResult);
		}
		/**
		 * 获取用户信息
		 */
		private User getUserProfile(Weibo weibo,String userId){
	    	if(null==userId)
	    		throw new RuntimeException("the uid must not null");
	    	WeiboGetter<User> wg = new UserProfile<User>(weibo,User.class,userId);
	    	return wg.getData();
		}
		@Override
		protected Intent doInBackground(Intent... params) {
			//
			Bundle values = params[0].getExtras();
			String uid = values.getString("uid");
			String access_token =values.getString("access_token");
			String expires_in = values.getString("expires_in");
			
			//生成一个AccessToken对象，使用token和CONSUMER_SECRET
			AccessToken accessToken = 
					new AccessToken(access_token, OAuthConstant.CONSUMER_SECRET);
			//再设置AccessToken对象的ExpiresIn
			accessToken.setExpiresIn(expires_in);

			//1. 获取用户信息
			User user =this.getUserProfile(this.getWeibo(),uid);
			
			this.saveUser(uid, accessToken, user);
			return this.putWeiboData2Intent(user);
		}
		
		private void saveUser(String uid, AccessToken accessToken, User user) {
			//先判断用户之前是否存在数据库中
			boolean exists = dbAgent.userInfoIsExists(Long.parseLong(uid));
			if(exists){//如果存在，则更新
				dbAgent.updataUserInfo(user, accessToken);
			}else{//如果不存在，则保存新的数据
				boolean ok =dbAgent.saveUserInfo2DB(user, accessToken);
				if(ok)Log.i(TAG,"保存成功...");
			}
		}
}