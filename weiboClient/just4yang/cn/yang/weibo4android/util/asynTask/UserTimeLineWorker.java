package cn.yang.weibo4android.util.asynTask;

import weibo4android.Paging;
import cn.yang.sina.weibo.beans.Statuses;
import cn.yang.weibo4android.util.UserTimeline;
import cn.yang.weibo4android.util.WeiboGetter;

import com.weibo.net.Weibo;

import android.content.Intent;
import android.util.Log;
/**
 * 用来 获取用户发布的微博 的异步任务类
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class UserTimeLineWorker extends WeiboWorker {
	private String uid;
	private Paging paging;
	public UserTimeLineWorker( Weibo weibo,String receiverActionName, String keyOfResult,
			String uid, Paging paging) {
		super(weibo, receiverActionName, keyOfResult);
		this.uid = uid;
		this.paging = paging;
	}


	// 获取用户发布的微博
	private Statuses getUserTimeLine(Weibo weibo,String uid,Paging paging){
		Log.i(TAG, "getUserTimeLine running...");
		WeiboGetter<Statuses> sd=
				new UserTimeline<Statuses>(weibo, Statuses.class, paging, uid);
    	return sd.getData();
	}
	@Override
	protected Intent doInBackground(Intent... params) {
		//返回指定用户最新发表的微博消息列表
		Statuses statuses = this.getUserTimeLine(this.getWeibo(),this.uid,this.paging);
		return this.putWeiboData2Intent(statuses);
	}

}
