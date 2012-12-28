package cn.yang.weibo4android.util.asynTask;

import weibo4android.Paging;
import cn.yang.weibo4android.Users;
import cn.yang.weibo4android.util.FriendsList;
import cn.yang.weibo4android.util.WeiboGetter;
import android.content.Intent;
import android.util.Log;
import com.weibo.net.Weibo;
/**
 * 用来【 获取某个用户的关注列表】 的异步任务类
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class FriendsListWorker extends WeiboWorker{
	
		private String uid;
		private Paging paging;
		/**
		 * @param weibo 通过Weibo.getInstance()获取
		 * @param receiverActionName 广播接收者的action名称
		 * @param KeyOfResult 用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
		 * @param uid 用户的id
		 * @param paging 分页参数
		 */
		public FriendsListWorker(Weibo weibo,String receiverActionName,String KeyOfResult,String uid,Paging paging){
			super(weibo, receiverActionName,KeyOfResult);
			this.uid=uid;
			this.paging=paging;
		}
		/**
		 * 获取某个用户的关注列表
		 * @param uid 用户UID
		 * @param paging  分页参数
		 * @return users Users的实例，Users的结构与Json数据结构相同
		 */
		private Users getFriendsList(Weibo weibo,String uid,Paging paging){
			Log.i(TAG, "getFriendLists running...");
			WeiboGetter<Users> wg = 
					new FriendsList<Users>(weibo, Users.class, uid, paging);
	    	return wg.getData();
		}
		
		@Override
		protected Intent doInBackground(Intent... params) {
			//获取用户的好友列表
			Users friends = this.getFriendsList(this.getWeibo(),this.uid,this.paging);
			return this.putWeiboData2Intent(friends);
		}

		
	}