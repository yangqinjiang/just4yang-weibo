package cn.yang.weibo4android.util.asynTask;
import cn.yang.weibo4android.WeiboContent;
import cn.yang.weibo4android.util.Repost;
import cn.yang.weibo4android.util.WeiboGetter;

import com.weibo.net.Weibo;

import android.content.Intent;
import android.util.Log;
/**
 * 【转发一条微博 】异步任务类
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class RepostWeiboWorker extends WeiboWorker {

	private WeiboContent weiboContent;
	/**
	 * @param weibo 通过Weibo.getInstance()获取
	 * @param receiverActionName  广播接收者的action名称
	 * @param keyOfResult   用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
	 * @param weiboContent  要发表的微博内容
	 */
	public RepostWeiboWorker(Weibo weibo,String receiverActionName, 
			String keyOfResult,WeiboContent weiboContent) {
		super(weibo, receiverActionName, keyOfResult);
		this.weiboContent = weiboContent;
	}
	
	private cn.yang.weibo4android.Status repost(Weibo weibo,WeiboContent weiboContent){
    	WeiboGetter<Status> wg = new Repost<Status>(weibo, Status.class, weiboContent);
    	return wg.getData();
	}
	@Override
	protected Intent doInBackground(Intent... params) {
		Log.i(TAG, "repost a weibo.");
		cn.yang.weibo4android.Status status=this.repost(this.getWeibo(),this.weiboContent);
		return this.putWeiboData2Intent(status);
	}


}
