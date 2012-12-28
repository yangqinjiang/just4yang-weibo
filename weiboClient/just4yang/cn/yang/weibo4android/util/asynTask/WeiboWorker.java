package cn.yang.weibo4android.util.asynTask;

import java.io.Serializable;

import cn.yang.sina.weibo.beans.app.WeiboApp;

import com.weibo.net.Weibo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public abstract class WeiboWorker extends AsyncTask<Intent, Integer, Intent> {


	protected static final String TAG = "AsyncTask";
	private String receiverActionName;
	private String keyOfResult;
	private Weibo weibo;
	/**
	 * 
	 * @param weibo 通过Weibo.getInstance()获取
	 * @param receiverActionName  广播接收者的action名称
	 * @param keyOfResult   用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
	 */
	protected WeiboWorker(Weibo weibo, String receiverActionName,
			String keyOfResult) {
		super();
		this.weibo = weibo;
		this.receiverActionName = receiverActionName;
		if(this.receiverActionName==null)
			this.receiverActionName="";
		this.keyOfResult = keyOfResult;
	}
	public String getReceiverActionName() {
		return receiverActionName;
	}
	public void setReceiverActionName(String receiverActionName) {
		this.receiverActionName = receiverActionName;
	}
	public String getKeyOfResult() {
		return keyOfResult;
	}

	public Weibo getWeibo() {
		return weibo;
	}

	
	@Override
	protected void onPostExecute(Intent result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		WeiboApp.context.sendBroadcast(result);
	}
	
	protected Intent putWeiboData2Intent(Object data){
		Intent intent = new Intent(this.receiverActionName);
		Bundle bundle = new Bundle();
		bundle.putSerializable(this.keyOfResult, (Serializable)data);
		intent.putExtras(bundle);
		return intent;
	}
	protected Intent putWeiboData2Intent2(Bundle bundle){
		Intent intent = new Intent(this.receiverActionName);
		intent.putExtras(bundle);
		return intent;
	}

}
