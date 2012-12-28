package cn.yang.weibo4android.util.asynTask;

import weibo4android.Paging;

import com.weibo.net.Weibo;

import cn.yang.sina.weibo.beans.Statuses;
import cn.yang.weibo4android.util.MentionLists;
import cn.yang.weibo4android.util.WeiboGetter;
import android.content.Intent;
import android.util.Log;
/**
 * 用来【获取@我的微博】的异步任务类
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class MentionsListWorker extends WeiboWorker{

	private Paging paging;
	
	/**
	 * @param weibo 通过Weibo.getInstance()获取
	 * @param paging 分页参数
	 * @param receiverActionName 广播接收者的action名称
	 * @param keyOfResult 用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
	 */
	public MentionsListWorker(Weibo weibo, String receiverActionName,
			String keyOfResult ,Paging paging) {
		super(weibo, keyOfResult, keyOfResult);
		this.paging = paging;
	}
	
	private Statuses getMentions(Weibo weibo,Paging paging){
		Log.i(TAG, "getMentions running...");
    	WeiboGetter<Statuses> wg = new MentionLists<Statuses>(weibo, Statuses.class, paging);
    	return wg.getData();
	}
	@Override
	protected Intent doInBackground(Intent... params) {
		Statuses mentions = this.getMentions(this.getWeibo(),this.paging);
		return this.putWeiboData2Intent(mentions);
	}
	
}