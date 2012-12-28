package cn.yang.sina.weibo.ui;

import java.util.List;

import com.weibo.net.Weibo;

import weibo4android.Paging;
import cn.yang.weibo4android.Status;
import cn.yang.weibo4android.util.asynTask.FriendsTimelineWorker;
import cn.yang.sina.weibo.R;
import cn.yang.sina.weibo.beans.Statuses;
import cn.yang.sina.weibo.beans.app.WeiboApp;
import cn.yang.sina.weibo.loaders.Loadable;
import cn.yang.sina.weibo.loaders.impl.HomeLoad;
import cn.yang.sina.weibo.loaders.impl.HomeMore;
import cn.yang.sina.weibo.loaders.impl.HomeRefresh;
import cn.yang.sina.weibo.logic.WeiBoService;
import cn.yang.sina.weibo.ui.adapter.NewWeiboStatusAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * 获取当前登录用户及其所关注用户的最新微博 的窗口Activity weibo_friends_timeline
 * 
 * @author Administrator
 * 
 */
public class HomeActivity extends WeiboActivity implements OnClickListener,
		OnItemClickListener {

	/** TAG标签 */
	protected static final String TAG = "HomeActivity";
	/** lvWeiboFriendsTimeline 显示朋友时间线微博的列表(ListView) */
	private static ListView lvWeiboFriendsTimeline = null;
	/** adaWeiboStatus : lvWeiboFriendsTimeline列表的适配器 */
	private NewWeiboStatusAdapter newAdaWeiboStatus = null;
	/**
	 * pbarlayoutLoad 加载微博前要显示的进度条视图, pbar...表示是进度条progress_bar
	 * */
	private static LinearLayout pbarlayoutLoad = null;
	/** 刷新按钮 */
	private Button btnRefreshWeibo;
	/** HomeActivity窗口的标题栏视图，显示：刷新按钮，当前用户昵称，写微博的按钮 */
	private View vTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		initUI();
		newAdaWeiboStatus = new NewWeiboStatusAdapter(WeiboApp.context);
		lvWeiboFriendsTimeline.setAdapter(newAdaWeiboStatus);
		lvWeiboFriendsTimeline.setOnItemClickListener(this);
		this.statusReceiver = this.bindReceiver(this,
				new WeiboStatusReceiver(), "WeiboStatusReceiver");
		Log.i(TAG, "第一次加载数据...----1");
		this.currentLoader=new HomeLoad();
		getWeiboData(new Paging(), this.newAdaWeiboStatus,this.currentLoader);
	}

	/** 加载更多微博数据的视图，位于显示微博的列表底部 */
	private View vLoadMore = null;

	/** btnSendWeibo 发送微博的按钮,按下后就弹出发送微博SendWeiboActivity */
	private Button btnSendWeibo = null;

	/**
	 * 初始化工作，初始化HomeActivity上所有的控件，如：按钮，列表等等
	 */
	private void initUI() {
		lvWeiboFriendsTimeline = (ListView) this
				.findViewById(R.id.weibo_listview);
		pbarlayoutLoad = (LinearLayout) this
				.findViewById(R.id.layoutLoadProgressBar);
		// 将“加载更多”视图加入ListView底部
		vLoadMore = View.inflate(this, R.layout.more_layout, null);

		lvWeiboFriendsTimeline.addFooterView(vLoadMore);
		vLoadMore.setOnClickListener(this);
		// 初始化titleBar上的刷新按钮
		vTitle = this.findViewById(R.id.layoutTitleBar);
		btnRefreshWeibo = (Button) vTitle.findViewById(R.id.btn_refresh);
		// btnRefreshWeibo.setOnClickListener(this);
		btnSendWeibo = (Button) vTitle.findViewById(R.id.btn_write);
		btnSendWeibo.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 将apapter和status-->null
		newAdaWeiboStatus = null;
		Log.i(TAG, "HomeActivity-->onDestroy()");
		unregisterReceiver(this.statusReceiver);
		stopService(new Intent(this, WeiBoService.class));
	}

	private static final String receiverActionName="WeiboStatusReceiver";
	private static final String keyOfResult="FriendTimeline";
	

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.moreLayout:
			Log.i(TAG, "moreLayout-->onClick!");
			// 加载更多weibo列表
			vLoadMore.setEnabled(false);
			this.currentLoader=new HomeMore();
			getWeiboData(new Paging(),this.newAdaWeiboStatus,this.currentLoader);
			break;
		case R.id.btn_refresh:
			Log.i(TAG, "btn_refresh-->onClick!");
			btnRefreshWeibo.setEnabled(false);
			this.currentLoader=new HomeRefresh();
			getWeiboData(new Paging(),this.newAdaWeiboStatus,this.currentLoader);
			break;
		case R.id.btn_write:
			Log.i(TAG, "btn_write-->onClick!");
			Intent intent = new Intent(this, SendWeiboActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	/*当前的加载器*/
	private Loadable<Status> currentLoader;
	
	//获取微博内容，比如第一次获取微博、刷新微博、获取更多微博等，
	protected void getWeiboData(Paging paging, NewWeiboStatusAdapter adapter,
			Loadable<Status> loader) {
		Log.i(TAG, "加载Paging...----2");
		Paging newPaging =(Paging)loader.loading(adapter);
		new FriendsTimelineWorker(Weibo.getInstance(),receiverActionName ,
				keyOfResult, newPaging).execute(new Intent());
		
	}
	
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Log.i(TAG, "listView onItemClick");
		Status clickStatus = (Status) arg0.getItemAtPosition(arg2);
		// Log.i(TAG, "Text: "+clickStatus.getText());
		// Log.i(TAG, "包装Intent");
		Intent statusIntent = new Intent(HomeActivity.this,
				WeiboTextActivity.class);
		Bundle statusBundle = new Bundle();
		statusBundle.putSerializable("clickStatus", clickStatus);
		statusIntent.putExtras(statusBundle);
		startActivity(statusIntent);
	}

	private BroadcastReceiver statusReceiver;

	/**
	 * 微博内容广播接收者 接收从WeiboService中传递过来的数据
	 */
	public class WeiboStatusReceiver extends WeiboBroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent intent) {
			Log.i(TAG, "onReceive正在接收信息---3");
			//传入当前的微博加载器
			new ShowWeiboStatusTask(currentLoader).execute(intent);
		}

	}

	
	private class ShowWeiboStatusTask extends
			AsyncTask<Intent,Statuses, Void> {
		private Loadable<cn.yang.weibo4android.Status> loader;
		public ShowWeiboStatusTask(Loadable<cn.yang.weibo4android.Status> loader){
			this.loader=loader;
		}
		
		@Override
		public Void doInBackground(Intent... params){
			Log.i(TAG, "doInBackground---4");
			Bundle bundle = params[0].getExtras();
			Statuses statuses = (Statuses) bundle
					.getSerializable(keyOfResult);
			publishProgress(statuses);
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Statuses... item) {
			super.onProgressUpdate(item);
			Log.i(TAG, "onProgressUpdate running...---5");
			pbarlayoutLoad.setVisibility(View.GONE);
			if(item[0]!=null&&item.length>0){
				List<cn.yang.weibo4android.Status> target=item[0].getStatuses();
				List<cn.yang.weibo4android.Status> source =newAdaWeiboStatus.getStatus();
					if(target!=null&&target.size()>0&&source!=null&&source.size()>0){
						List<cn.yang.weibo4android.Status> result =this.loader.append(source,target);
						newAdaWeiboStatus.setStatus(result);
					}
			}
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Log.i(TAG, "onPostExecute running...---7");
				btnRefreshWeibo.setEnabled(true);
				vLoadMore.setEnabled(true);
		}

	}


}
