package cn.yang.sina.weibo.ui;

import com.weibo.net.Weibo;

import cn.yang.weibo4android.User;
import cn.yang.weibo4android.Users;
import cn.yang.weibo4android.util.asynTask.LoginWorker;
import cn.yang.sina.weibo.R;
import cn.yang.sina.weibo.beans.OAuthConstant;
import cn.yang.sina.weibo.beans.Task;
import cn.yang.sina.weibo.db.DBAgent;
import cn.yang.sina.weibo.db.IDB;
import cn.yang.sina.weibo.ui.adapter.LoginGridViewAdapter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class Login extends WeiboActivity{


	/**btnAddUser 添加用户 按钮*/
	private Button btnAddUser=null;
	/**gvShowAllUserInfo 显示所有使用本应用的用户的信息，*/
	private GridView gvShowAllUserInfo=null;
	/**AddUserID 一个标签，用在显示对话框上*/
	private static final int AddUserID =1;
	/**pwUserLogin 是一个弹窗式的对话框，用在显示用户登陆和注销的功能上*/
	private PopupWindow pwUserLogin = null;
	/**lgaShowAllUser gvShowAllUserInfo的适配器*/
	private static LoginGridViewAdapter lgaShowAllUser=null;
	/**TAG输出日志用到的标签*/
	private static final String TAG = "Login";
	/**dbAgent 数据库代理类实例(单例模式)*/
	private static IDB dbAgent = DBAgent.getDBAgentInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initUI();
		
		//从数据库中获取在当前应用登录过的用户
		Users users=dbAgent.getUsers();
		for (User user : users.getUsers()) {
			lgaShowAllUser.addUser(user);
		}
		
		this.loginReceiver=this.bindReceiver(this,new LoginReceiver(),"LoginReceiver");
	}
	
	int CMD_GET_USERS_INFO=0;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dismissPopupWin();
		unregisterReceiver(loginReceiver);
	}
	/**
	 * 将弹窗式的对话框dismiss掉.
	 */
	private void dismissPopupWin() {
		if(null!=pwUserLogin&&pwUserLogin.isShowing()){
			pwUserLogin.dismiss();
		}
	}

	/**初始化UI*/
	private void initUI(){
		gvShowAllUserInfo =(GridView)this.findViewById(R.id.login_ShowUser_gridview);
		gvShowAllUserInfo.setOnItemClickListener(icl);
		btnAddUser =(Button)this.findViewById(R.id.login_btn_addUser);
		btnAddUser.setOnClickListener(btnCL);
		lgaShowAllUser = new LoginGridViewAdapter(this);
		gvShowAllUserInfo.setAdapter(lgaShowAllUser);
	}

	


	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		super.dispatchTouchEvent(ev);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;

		case MotionEvent.ACTION_UP:
			dismissPopupWin();
			break;
		default:
			break;
		}
		return false;
				
	}

	
	

	/**icl gvShowAllUserInfo的ItemClick监听器*/
	private OnItemClickListener icl = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long id) {

			User user = (User)arg0.getItemAtPosition(position);
			Log.i(TAG, "onItemClick userName: "+user.getScreen_name()
					+" userId: "+user.getId());
			//使用getChildAt(int position)方法，根据position来返回这个View
			View childView =arg0.getChildAt(position);

			showPaopaoWin(childView,user);

		}
	};


	/**
	 * 当点击ListView时某个项目时，弹出一个popupWindow式窗口
	 * @param view  在某个view下显示出来
	 * @param user  在gridView的Item上显示的user信息
	 */
	private void showPaopaoWin(View view,final User user){
		if(Task.DEBUG)Log.i(TAG,"正在弹出泡泡式窗口...");
		if(pwUserLogin == null)
		{
			
			View pwLoginView = getLayoutInflater()
				.inflate(R.layout.paopao,null);
			ImageButton btnLogin = (ImageButton)pwLoginView.findViewById(R.id.paopao_btnLogin);
				btnLogin.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						btnLogin$Click(v,user);
					}
				});
			if(Task.DEBUG)Log.i(TAG,"正在生成一个pwUserLogin...");
			pwUserLogin = new PopupWindow(pwLoginView,LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
			//当点击外面的界面时，响应点击事件
			pwUserLogin.setOutsideTouchable(true);
		}else if(pwUserLogin.isShowing()){
			pwUserLogin.dismiss();
		}
		
			//显示pwUserLogin弹窗式的对话框
			pwUserLogin.showAsDropDown(view,50,0);
		

	}
	

	private Dialog addUserDialog(Context context){
		if(Task.DEBUG)Log.i(TAG,"addUserDialog create!!");
		AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);  
		builder.setView(null);
		builder.setTitle(R.string.Into_the_authorized_page_hints);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							Log.i("Login", "add user ok click.");
							OAuth2();
				            dialog.cancel();
						}
					});
			builder.setNegativeButton(R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							dialog.dismiss();
						}
					});
		return builder.create();
	}
	
	
	private void OAuth2() {
		Log.i("Login", "running OAuth2 method.");
		//TODO new oauth method  OAuth 2.0 
		////进入认证页面
		//生成一个weibo实例
		com.weibo.net.Weibo weibo =com.weibo.net.Weibo.getInstance();
		//设置CONSUMER_KEY, CONSUMER_SECRET
		weibo.setupConsumerConfig(OAuthConstant.CONSUMER_KEY,OAuthConstant.CONSUMER_SECRET);
		//回调页面
		weibo.setRedirectUrl("http://www.sina.com");
		//打开认证页面
		weibo.authorize(Login.this,
				new cn.yang.sina.weibo.ui.AuthDialogListener());
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case AddUserID:
			return addUserDialog(Login.this);
			
		default:
			break;
		}
		return super.onCreateDialog(id);
	}

	/**
	 * 用户登录按钮
	 * @param v
	 * @param user
	 */
	private void btnLogin$Click(View v,User user) {
		dismissPopupWin();
		new LoginWorker(Weibo.getInstance(), "LoginReceiver", "currentUser", user).execute(new Intent());

		//结束当前登录窗口
		//finish();
	}

	/**btnCL 所有按钮的监听器*/
	private OnClickListener btnCL = new OnClickListener() {
		
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.login_btn_addUser:
				if(Task.DEBUG)Log.i(TAG,"click login_btn_addUser");
				showDialog(AddUserID);
				break;

			default :
				break;
			}
		}
	};
	
	private BroadcastReceiver loginReceiver;


	
	private class LoginReceiver extends WeiboBroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i(TAG, "onReceive running...----5");
			//接收来自后台的消息，并更新UI
			new ShowUsersTask().execute(intent);
		}
		
	}
	
	private class ShowUsersTask extends AsyncTask<Intent, User, Void>{
		@Override
		protected Void doInBackground(Intent... params) {
			Log.i(TAG, "onReceive running...----6");
			//TODO 先判断是否登录成功。再跳转到MainActivity（在Receiver中执行下面代码）
			Intent startMainAct =new Intent(Login.this,MainActivity.class);
			startMainAct.putExtras(params[0].getExtras());
			startActivity(startMainAct);
			return null;
		}
	}
}


