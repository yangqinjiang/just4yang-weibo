package cn.yang.sina.weibo.ui;

import cn.yang.weibo4android.User;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import cn.yang.sina.weibo.R;

/**
 * MainActivity 主窗口
 * @author yangqinjiang17
 * @email yangqinjiang@gmail.com
 *
 */
public class MainActivity extends TabActivity {

	/**TAB Tag 标签*/
	private static final String HOME_TAB="home"; 
	private static final String FRIEND_TAB="friends"; 
	private static final String MSG_TAB="msg"; 
	private static final String PLACE_TAB="place";
	/**TabHost*/
	private static TabHost tabHost;
	/**TabSpec 四个窗口*/
	private static TabSpec homeSpec;
	private static TabSpec friendSpec;
	private static TabSpec msgSpec;
	private static TabSpec placeSpec;
	/**主窗口下面的RadioGroup(单选按钮组),用于切换四个窗口*/
	private static RadioGroup radioGroup;
	/**TAG输出日志用到的标签*/
	protected static final String TAG = "MainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		initUI();
		initUserLayout();
		setUserProfile(getIntent());

	}
	private void setUserProfile(Intent nowUserIntent){
		Log.i(TAG, "setUserProfile running...");
		Bundle userBundle =nowUserIntent.getExtras();
		if(userBundle!=null&&userBundle.containsKey("currentUser")){
			User nowUser =(User)userBundle.getSerializable("currentUser");
			tvUserName.setText(nowUser.getName());
			tvUserStates.setText(String.valueOf(nowUser.getId()));
			Log.i(TAG, "user name :"+nowUser.getName());
			//用户头像
			//SimpleImageLoader.showImg(nowUser.getProfile_image_url().toString(),imgUserIcon);
		}
	}

	/**
	 * 初始化界面
	 */
	private void initUI(){
		Log.i(TAG, "initUI running...");
		tabHost =this.getTabHost();
		//初始化四个Spec
	    homeSpec=tabHost.newTabSpec(HOME_TAB).setIndicator(HOME_TAB).setContent(new Intent(this,HomeActivity.class));
	    friendSpec=tabHost.newTabSpec(FRIEND_TAB).setIndicator(FRIEND_TAB).setContent(new Intent(this,AtActivity.class));
	    msgSpec=tabHost.newTabSpec(MSG_TAB).setIndicator(MSG_TAB).setContent(new Intent(this,MsgActivity.class));
	    placeSpec=tabHost.newTabSpec(PLACE_TAB).setIndicator(PLACE_TAB).setContent(new Intent(this,MoreActivity.class));
	    //将四个Spec添加到tabHost上
	    tabHost.addTab(homeSpec);
	    tabHost.addTab(friendSpec);
	    tabHost.addTab(msgSpec);
	    tabHost.addTab(placeSpec);
	   
	    radioGroup = (RadioGroup)this.findViewById(R.id.rg_main_btns);
	    //设置按钮选择改变事件
	    radioGroup.setOnCheckedChangeListener(ccl);
	    
	    
	    
	}
	private RelativeLayout showUserLayout=null;
	private ImageView imgUserIcon;
	private TextView tvUserName;
	private TextView tvUserStates;
	public void initUserLayout() {
		Log.i(TAG, "initUserLayout running...");
		showUserLayout=(RelativeLayout)this.
				findViewById(R.id.show_user_layoutID);
		imgUserIcon =(ImageView)showUserLayout.findViewById(R.id.imgUserIcon);
		tvUserName=(TextView)showUserLayout.findViewById(R.id.tvUserName);
		tvUserStates=(TextView)showUserLayout.findViewById(R.id.tvUserStates);
	}
	
	

	/**
	 * ccl 监听器，用于监听radioGroup上的按钮选择改变事件
	 * */
	private OnCheckedChangeListener ccl = new OnCheckedChangeListener() {
		
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			int visibility=-1;
			switch (checkedId) {
			case R.id.rd_home:
				Log.i(TAG, "rd_home");
				//选定HOME为当前窗口
				tabHost.setCurrentTabByTag(HOME_TAB);
				visibility=View.VISIBLE;
				break;
			case R.id.rd_at:
				Log.i(TAG, "rd_at");
				//选定AT为当前窗口
				tabHost.setCurrentTabByTag(FRIEND_TAB);
				visibility=View.GONE;
				break;
			case R.id.rd_msg:
				Log.i(TAG, "rd_msg");
				//选定MSG为当前窗口
				tabHost.setCurrentTabByTag(MSG_TAB);
				visibility=View.GONE;
				break;
			case R.id.rd_more:
				Log.i(TAG, "rd_more");
				//选定MORE为当前窗口
				tabHost.setCurrentTabByTag(PLACE_TAB);
				visibility=View.GONE;
				break;
			default:
				break;
			}
			showUserLayout.setVisibility(visibility);
		}
	};

	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy running...");
		super.onDestroy();
	}

}
