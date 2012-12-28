package cn.yang.sina.weibo.ui;

import cn.yang.sina.weibo.R;
import cn.yang.sina.weibo.beans.Task;
import cn.yang.sina.weibo.logic.WeiBoService;
import cn.yang.weibo4android.User;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * OAuthAccessTokenActivity 显示用户授权后的信息
 * @author yangqinjiang
 *
 */
public class OAuthAccessTokenActivity extends Activity {

	/**TAG输出日志和调试程序使用的*/
	private static final String TAG ="OAuthAccessTokenAct";

	/**pdShowOauthInfo 是一个进度条对话,显示获取授权信息*/
	private ProgressDialog pdShowOauthInfo;
	/**userId 显示授权后用户的ID*/
	private TextView tvUserId;
	/**userName 显示授权后用户的昵称*/
	private TextView tvUserName;
	/**userIcon 显示授权后用户的头像*/
	private ImageView userIcon;
	/**user 是授权后用户*/
	private User user;
	/**登陆按钮*/
	private Button btnLogin;
	/**返回到登陆窗口按钮*/
	private Button btnBackLoginWin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(Task.DEBUG)Log.i(TAG,"OAuthAccessTokenAct-->onCreate...");
		setContentView(R.layout.access_token_activity_show_user);
		initUI();
		//得到从浏览器返回来的数据 uri
		//Uri uri = this.getIntent().getData();
		
		//weibo获取授权信息
		sendWeiboCommandToService(this.getIntent().getData(),CMD_OAUTH);
		
		pdShowOauthInfo=new ProgressDialog(this);
		pdShowOauthInfo.show();
		pdShowOauthInfo.setMessage("请稍候...");
	}
	private int CMD_OAUTH=0;
	private void sendWeiboCommandToService(Uri uri,int cmd){
		Log.i(TAG, "sendWeiboCommandToService running...----1");
		Log.i(TAG, "uri "+uri);
		Intent intent = new Intent(this,WeiBoService.class);
		intent.putExtra("OAuthCmd",cmd);
		Bundle uriBundle = new Bundle();
		uriBundle.putParcelable("uri", uri);
		intent.putExtras(uriBundle);
		startService(intent);
	}

	/**cl 按钮点击事件的监听器*/
	private OnClickListener cl = new OnClickListener() {
		
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.confirm:
				//not do something
				break;

			case R.id.cancel:
				Log.i(TAG, "click cancel");
				//返回到Login界面
				startActivity(new Intent(OAuthAccessTokenActivity.this, Login.class));
				finish();
				break;
			default:
				break;
			}
		}
	};
	/**初始化UI*/
	private void initUI() {
		if(Task.DEBUG)Log.i(TAG, "OAuthAccessTokenAct--init()..初始化控件");
		tvUserId = (TextView)this.findViewById(R.id.userId);
		tvUserName =(TextView)this.findViewById(R.id.userName);
		userIcon = (ImageView)this.findViewById(R.id.userIcon);
		btnLogin =(Button)this.findViewById(R.id.confirm);
		btnBackLoginWin = (Button)this.findViewById(R.id.cancel);
		
		btnLogin.setOnClickListener(cl);
		btnBackLoginWin.setOnClickListener(cl);
	}
	
	/**
	 * @param params
	 */
	private void UpdateUIWithUser(Intent userIntent) {
		Log.i(TAG,"UpdateUIWithUser");
		Log.i(TAG, "userIntent"+userIntent);
		user = (User)userIntent.getExtras().getSerializable("user");
		Log.i(TAG, "2.user"+user);
		tvUserId.setText(String.valueOf(user.getId()));
		tvUserName.setText(user.getName());
	}
	private OAuthReceiver oauthReceiver;
	/**
	 * 
	 */
	private void registerReceiver1() {
		Log.i(TAG, "实例化一个OAuthReceiver对象");
		oauthReceiver=new OAuthReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("OAuthReceiver");
		registerReceiver(oauthReceiver, filter);
	}
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
		registerReceiver1();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy");
		unregisterReceiver(oauthReceiver);
	}
	private class OAuthReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO 更新UI
			Log.i(TAG, "OAuthReceiver onReceive");
			UpdateUIWithUser(intent);
			pdShowOauthInfo.dismiss();
		}
		
	}
}
