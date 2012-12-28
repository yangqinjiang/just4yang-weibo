package cn.yang.weibo4android.util.asynTask;


import weibo4android.Paging;

import cn.yang.weibo4android.Comments;
import cn.yang.weibo4android.util.CommentToMe;
import cn.yang.weibo4android.util.WeiboGetter;

import com.weibo.net.Weibo;

import android.content.Intent;
import android.util.Log;
/**
 * 用来 【获取我收到的评论列表 】的异步任务类
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class Comments2MeListWorker extends WeiboWorker{

	private Paging paging;
	/**
	 * 
	 * @param weibo 通过Weibo.getInstance()获取
	 * @param paging 分布参数
	 * @param receiverActionName 广播接收者的action名称
	 * @param keyOfResult 用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
	 */
	public Comments2MeListWorker(Weibo weibo,  String receiverActionName,
			String keyOfResult,Paging paging) {
		super(weibo, receiverActionName, keyOfResult);
		this.paging = paging;
	}
	private Comments getCommentsToMe(Weibo weibo,Paging paging){
		Log.i(TAG, "getCommentsToMe running...");
		WeiboGetter<Comments> wg = new CommentToMe<Comments>(weibo, Comments.class, paging);
    	return wg.getData();
	}
	@Override
	protected Intent doInBackground(Intent... params) {
		Log.i(TAG, "doInBackground running...");
		Comments c= this.getCommentsToMe(this.getWeibo(),this.paging);
		return this.putWeiboData2Intent(c);
	}
	

}
