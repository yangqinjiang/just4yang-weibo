package cn.yang.weibo4android.util.asynTask;

import weibo4android.Paging;

import com.weibo.net.Weibo;

import cn.yang.weibo4android.Users;
import cn.yang.weibo4android.util.FollowersList;
import cn.yang.weibo4android.util.WeiboGetter;
import android.content.Intent;
import android.util.Log;
/**
 * 用来【获取用户粉丝列表】 的异步任务类
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class FollowersListWorker extends WeiboWorker {
	
	private String uid;
	private Paging paging;
	/**
	 * @param receiverActionName 广播接收者的action名称
	 * @param KeyOfResult 用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
	 * @param uid 用户的id
	 * @param paging 分页参数
	 */
	public FollowersListWorker(Weibo weibo,String receiverActionName,String KeyOfResult,
			String uid,Paging paging){
		super(weibo, receiverActionName, KeyOfResult);
		this.uid=uid;
		this.paging=paging;
	}
	//获取用户粉丝列表
	private Users getFollowerLists(String uid,Paging paging){
		Log.i(TAG, "getFollowerLists running...");
		WeiboGetter<Users> wg = new FollowersList<Users>(this.getWeibo(), Users.class, uid, paging);
    	return wg.getData();
	}
	@Override
	protected Intent doInBackground(Intent... params) {

		//获取好友的粉丝列表
		Users followers=this.getFollowerLists(this.uid,this.paging);

		return this.putWeiboData2Intent(followers);
	}
	

}
