package cn.yang.weibo4android.util.asynTask;

import cn.yang.sina.weibo.db.DBAgent;
import cn.yang.sina.weibo.db.IDB;
import cn.yang.weibo4android.Users;
import android.content.Intent;
import android.util.Log;
/**
 * 用来 【从本地数据库中查找出所有用户】 的异步任务类
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class FindAllUsersWorker extends WeiboWorker {
	
	/**dbAgent 数据库代理类实例(单例模式)*/
	private static IDB dbAgent = DBAgent.getDBAgentInstance();
	/**
	 * 
	 * @param receiverActionName 广播接收者的action名称
	 * @param keyOfResult 用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
	 */
	public FindAllUsersWorker(String receiverActionName, String keyOfResult) {
		super(null, receiverActionName, keyOfResult);
	}
	@Override
	protected Intent doInBackground(Intent... params) {
		Log.i(TAG, "从本地数据库中查找出所有用户...");
		Users allUser = dbAgent.getUsers();
		return this.putWeiboData2Intent(allUser);
	}


}
