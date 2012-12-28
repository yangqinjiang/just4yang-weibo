package cn.yang.sina.weibo.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import cn.yang.sina.weibo.R;
public class FriendFollowerStatusBtnView {

	public FriendFollowerStatusBtnView(){
		
	}
	public FriendFollowerStatusBtnView(Context context){
		setContext(context);
	}
	public FriendFollowerStatusBtnView(Context context,View base){
		setContext(context);
		setBase(base);
	}
	
	/**context 上下文*/
	private Context context;
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	/**base 视图*/
	private View base;
	public View getBase() {
		return base;
	}
	public void setBase(View base) {
		this.base = base;
	}
	/**关注好友的关注数  按钮*/
	private Button btnShowItsFriend;
	public Button getBtnShowItsFriend() {
		if(btnShowItsFriend==null)
			btnShowItsFriend=(Button)getBase().
			findViewById(R.id.btnShowItsFriend);
		return btnShowItsFriend;
	}
	public void setBtnShowItsFriend(Button btnShowItsFriend) {
		this.btnShowItsFriend = btnShowItsFriend;
	}
	/**得到BtnShowItsFriend按钮的ID*/
	public int getBtnShowItsFriendID(){
		return getBtnShowItsFriend().getId();
	}
	/**关注好友的粉丝数  按钮*/
	private Button btnShowItsFollowers;
	public Button getBtnShowItsFollowers() {
		if(btnShowItsFollowers==null)
			btnShowItsFollowers=(Button)getBase().
			findViewById(R.id.btnShowItsFollowers);
		return btnShowItsFollowers;
	}
	public void setBtnShowItsFollowers(Button btnShowItsFollowers) {
		this.btnShowItsFollowers = btnShowItsFollowers;
	}
	/**得到BtnShowItsFollowers按钮的ID*/
	public int getBtnShowItsFollowersID(){
		return getBtnShowItsFollowers().getId();
	}
	/**关注好友的粉丝数  按钮*/
	private Button btnShowItsStatus;
	public Button getBtnShowItsStatus() {
		if(btnShowItsStatus==null)
			btnShowItsStatus=(Button)getBase().
			findViewById(R.id.btnShowItsStatus);
		return btnShowItsStatus;
	}
	public void setBtnShowItsStatus(Button btnShowItsStatus) {
		this.btnShowItsStatus = btnShowItsStatus;
	}
	/**得到BtnShowItsStatus按钮的ID*/
	public int getBtnShowItsStatusID(){
		return getBtnShowItsStatus().getId();
	}
	
}
