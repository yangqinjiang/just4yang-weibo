package cn.yang.weibo4android.util.asynTask;

import com.weibo.net.Weibo;
import cn.yang.weibo4android.WeiboContent;
import cn.yang.weibo4android.util.SendWeibo;
import cn.yang.weibo4android.util.WeiboGetter;
import android.content.Intent;
import android.util.Log;
/**
 * 【发布一条微博信息 】异步任务类  
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class SendWeiboWorker extends WeiboWorker{
	
	private WeiboContent weiboContent;
	/**
	 * 
	 * @param weibo 通过Weibo.getInstance()获取
	 * @param receiverActionName  广播接收者的action名称
	 * @param keyOfResult   用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
	 * @param weiboContent  要发表的微博内容
	 */
	public SendWeiboWorker(Weibo weibo,String receiverActionName, 
			String keyOfResult, WeiboContent weiboContent) {
		super(weibo, receiverActionName, keyOfResult);
		this.weiboContent = weiboContent;
	}
	private cn.yang.weibo4android.Status sendWeibo(Weibo weibo,WeiboContent weiboContent){
		Log.i(TAG, "sendWeibo running...");
    	WeiboGetter<Status> wg = new SendWeibo<Status>(weibo, Status.class,weiboContent);
		return wg.getData();
	}
	
	@Override
	protected Intent doInBackground(Intent... params) {
		cn.yang.weibo4android.Status status=
				this.sendWeibo(this.getWeibo(),this.weiboContent);
		return this.putWeiboData2Intent(status);
	}

	
	
}