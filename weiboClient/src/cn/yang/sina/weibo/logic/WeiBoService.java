package cn.yang.sina.weibo.logic;

import cn.yang.weibo4android.util.asynTask.FriendsTimelineWorker;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WeiBoService extends Service {

	
		private static final String TAG = "WeiBoService";

		@Override
		public void onCreate()
		{
			//Log.i(TAG,"WeiBoService当前线程名称："+ Thread.currentThread().getName()+"  ID:"+Thread.currentThread().getId());
			//Log.i(TAG, "WeiBoService-->onCreate()");
			super.onCreate();
		}
		

	
		
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		//从Activity中调用startService(Intent)方法，发送获取微博命令
		//Log.i(TAG, "onStartCommand running...---2");
		//从intent中取出相关的数据，再判断是哪一个操作
		//Log.i(TAG, "intent-->"+intent);
		Log.i(TAG, "startId-->"+startId);
		//如果是从HomeActivity发过来的命令，则执行这个Task
		boolean hasCmd =intent.hasExtra("cmd");
		if(hasCmd)
			//new StatusTaskMan().execute(intent);
		//如果是从Login发过来的命令，则执行这个Task
		if(intent.hasExtra("LogCmd")){
			Log.i(TAG, "LogCmd");
			//new LoginWorker().execute(intent);
		}
		else if(intent.hasExtra("AtCmd")){
			Log.i(TAG, "AtCmd");
			//20120713 new AsyncTask...
			//new FriendsListWorker().execute(intent);
		}else if(intent.hasExtra("MsgCmd")){
			Log.i(TAG, "MsgCmd");
			//20120714 new AsyncTask...
			//new MentionsListWorker().execute(intent);
		}else if(intent.hasExtra("SendWeiboCmd")||intent.hasExtra("WeiboTextCmd")){
			Log.i(TAG, "SendWeiboCmd");
			//20120714 new AsyncTask...
			//new SendWeiboWorker().execute(intent);
		}else if(intent.hasExtra("AuthDialogListenerCmd")){
			Log.i(TAG, "AuthDialogListenerCmd");
			//new OAuth2Worker().execute(intent);
		}
		return START_STICKY;
	}



	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
