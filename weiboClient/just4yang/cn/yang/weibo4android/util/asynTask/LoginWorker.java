package cn.yang.weibo4android.util.asynTask;

import com.weibo.net.AccessToken;
import com.weibo.net.Oauth2AccessTokenHeader;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;

import cn.yang.sina.weibo.db.DBAgent;
import cn.yang.sina.weibo.db.IDB;
import cn.yang.weibo4android.User;
import android.content.Intent;
import android.util.Log;

/**
 *  用户登录 异步任务类
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class LoginWorker extends WeiboWorker{
	/**dbAgent 数据库代理类实例(单例模式)*/
	private static IDB dbAgent = DBAgent.getDBAgentInstance();
	private User user;
	/**
	 * 
	 * @param weibo 通过Weibo.getInstance()获取
	 * @param receiverActionName 广播接收者的action名称
	 * @param keyOfResult 用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
	 * @param currentUser 当前登录的用户 
	 */
	public LoginWorker(Weibo weibo,String receiverActionName,String keyOfResult,User currentUser) {
		super(weibo, receiverActionName, keyOfResult);
		this.user=currentUser;
	}

	private void findAccessTokenByUserId() {
		AccessToken currentAccessToken = dbAgent.getAccessTokenFromDB(this.user.getId()+"");
		Log.i(TAG, "find current User token: "+currentAccessToken.getToken()+" secret:"+currentAccessToken.getSecret());
		this.setCurrentAccessToken(currentAccessToken);
	}
	
	private void setCurrentAccessToken(AccessToken accessToken) {
		//关键一句
		Utility.setAuthorization(new Oauth2AccessTokenHeader()); 
		this.getWeibo().setAccessToken(accessToken);
	}
	

	@Override
	protected Intent doInBackground(Intent... params) {
		this.findAccessTokenByUserId();
		return this.putWeiboData2Intent(this.user);
	}
	
	
}