package cn.yang.sina.weibo.view.click;

import cn.yang.weibo4android.User;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import cn.yang.sina.weibo.beans.app.WeiboApp;
import cn.yang.sina.weibo.view.UserInfoViewHolder;
import cn.yang.sina.weibo.R;

/**
 * 当点击用户或好友的头像时，弹出一个窗口，显示他的详细信息
 * @author Administrator
 *
 */
public class OnUserIconClick implements OnClickListener{

	private static final String TAG = "OnUserIconClick";

	private User user;
	public OnUserIconClick(User user){
		
		this.user=user;
		mLayoutInflater = (LayoutInflater) WeiboApp.context.
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public void onClick(View v) {
		showPaopaoWin(v,user);
	}

	/**pwShowUserInfo 当点击某一个关注好友的头像时，
	 * 弹出一个popupWindow窗口，显示该关注好友的资料
	 */
	private PopupWindow pwShowUserInfo;

	/**vUserInfo 一个视图，使用mLayoutInflater来inflate进去*/
	private View vUserInfo;

	/**mLayoutInflater 视图填充工具*/
	private LayoutInflater mLayoutInflater;
	/**
	 * 当点击ListView时某个项目时，弹出一个popupWindow式窗口
	 * @param view  在某个view下显示出来
	 */
	private void showPaopaoWin(View view,User user){
		Log.i(TAG, "showPaopaoWin");
		//TODO  使用PopHolder内部类  2012 03 22晚
		UserInfoViewHolder popHolder =null;
		if(pwShowUserInfo==null){
			vUserInfo = mLayoutInflater.inflate(R.layout.friend_info,null);
			pwShowUserInfo=new PopupWindow(vUserInfo,LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			popHolder=new UserInfoViewHolder(vUserInfo);
			vUserInfo.setTag(popHolder);
		}else{
			popHolder=(UserInfoViewHolder)vUserInfo.getTag();
		}
		
		
		int Pos[] = { -1, -1 };  //保存当前坐标的数组
		view.getLocationOnScreen(Pos);
		//邦定用户的信息数据
		popHolder.bindUserData(user);
		
		//显示pop
		pwShowUserInfo.showAsDropDown(view,5,-Pos[1]);
		}
	
	/**
	 * 将泡泡式窗口dismiss().
	 */
	private void dismissPopupWin() {
		if(pwShowUserInfo!=null&&pwShowUserInfo.isShowing()){
			pwShowUserInfo.dismiss();
			pwShowUserInfo=null;
		}
	
	
}
	}
