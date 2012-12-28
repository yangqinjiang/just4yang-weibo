package cn.yang.weibo4android.util.asynTask;

import com.weibo.net.Weibo;

import weibo4android.Paging;
import cn.yang.sina.weibo.beans.Statuses;
import cn.yang.weibo4android.util.FriendsTimeline;
import cn.yang.weibo4android.util.WeiboGetter;
import android.content.Intent;
import android.util.Log;

/**
 * 获取微博列表数据AsyncTask
 * @author yangqinjiang17
 *
 */
public class FriendsTimelineWorker extends WeiboWorker{

	private Paging paging;
	public FriendsTimelineWorker(Weibo weibo, String receiverActionName,
			String keyOfResult,Paging paging) {
		super(weibo, receiverActionName, keyOfResult);
		this.paging=paging;
	}
	

	/*获取好友时间线*/
	private Statuses getFriendsTimeline(Weibo weibo,Paging paging){
		
		WeiboGetter<Statuses> sd = 
				new FriendsTimeline<Statuses>(weibo,Statuses.class, paging);
    	return sd.getData();
	}
	@Override
	protected Intent doInBackground(Intent... intents) {
		Log.i(TAG, "doInBackground running...--3");
		Statuses statuses =this.getFriendsTimeline(this.getWeibo(),this.paging);
		return this.putWeiboData2Intent(statuses);
	}

}