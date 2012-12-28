package cn.yang.sina.weibo.ui;

import cn.yang.sina.weibo.logic.WeiBoService;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class WeiboActivity extends Activity {

	
	private static final String TAG = "WeiboActivity";
	
	/*邦定一个BroadcastReceiver到当前Activity上*/
	protected WeiboBroadcastReceiver bindReceiver(Activity activity ,WeiboBroadcastReceiver receiver,
			String action) {
		Log.i(TAG, "实例化一个BroadcastReceiver对象，并注册在Activity上");
		//新建一个广播接收者
		IntentFilter filter = new IntentFilter();
		filter.addAction(action);
		// 给当前Activity注册一个Receiver
		activity.registerReceiver(receiver, filter);
		return receiver;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopService(new Intent(this,WeiBoService.class));
	}
	
}
