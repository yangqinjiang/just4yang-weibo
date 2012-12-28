package cn.yang.sina.weibo.view;

import cn.yang.weibo4android.User;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import cn.yang.sina.weibo.R;

public class UserInfoViewHolder{
	private static final String TAG = "UserInfoViewHolder";
	/**
	 * 视图View
	 */
	private View base;
	/**
	 * 已关注
	 */
	private Button popHasConcerned;
	/**
	 * 取消
	 */
	private Button popCancel;
	/**
	 * 昵称
	 */
	private TextView screenName;
	/**
	 * 性别
	 */
	private ImageView gender;
	/**
	 * 城市
	 */
	private TextView location;
	/**
	 * 关注数目
	 */
	private TextView friendsCount;
	/**
	 * 粉丝数目
	 */
	private TextView followersCount;
	/**
	 * 微博数目
	 */
	private TextView statusesCount;
	/**
	 * 个人描述
	 */
	private TextView userDescription;
	/**
	 * 头像
	 */
	private ImageButton userIcon;
	/**
	 * 构造方法
	 * @param base  view
	 */
	public UserInfoViewHolder(View base){
		this.base=base;
	}
	public Button getPopHasConcerned() {
		if(popHasConcerned==null)
			popHasConcerned=(Button)base.findViewById(R.id.popHasConcerned);
		return popHasConcerned;
	}
	public Button getPopCancel() {
		if(popCancel==null)
			popCancel=(Button)base.findViewById(R.id.popCancel);
		return popCancel;
	}
	public TextView getScreenName() {
		if(screenName==null)
			screenName=(TextView)base.findViewById(R.id.popScreenName);
		return screenName;
	}
	public ImageView getGender() {
		if(gender==null)
			gender=(ImageView)base.findViewById(R.id.popGender);
		return gender;
	}
	public TextView getLocation() {
		if(location==null)
			location=(TextView)base.findViewById(R.id.popLocation);
		return location;
	}
	public TextView getFriendsCount() {
		if(friendsCount==null)
			friendsCount=(TextView)base.findViewById(R.id.popFriendsCount);
		return friendsCount;
	}
	public TextView getFollowersCount() {
		if(followersCount==null)
			followersCount=(TextView)base.findViewById(R.id.popFollowersCount);
		return followersCount;
	}
	public TextView getStatusesCount() {
		if(statusesCount==null)
			statusesCount=(TextView)base.findViewById(R.id.popStatusesCount);
		return statusesCount;
	}
	public TextView getUserDescription() {
		if(userDescription==null)
			userDescription=(TextView)base.findViewById(R.id.popDescription);
		return userDescription;
	}
	@SuppressLint("WrongViewCast")
	public ImageButton getUserIcon() {
		if(userIcon==null)
			userIcon=(ImageButton)base.findViewById(R.id.popUserIcon);
		return userIcon;
	}
	
	public void bindUserData(User user){
		Log.i(TAG, "bindUserData");
		//昵称
		this.getScreenName().setText(user.getScreen_name());
		//性别
		if(user.getGender()=="f"){
			//女
			this.getGender().setImageResource(R.drawable.f);
		}else if(user.getGender()=="m"){
			//男
			this.getGender().setImageResource(R.drawable.m);
		}else{
			//未知
			this.getGender().setImageResource(R.drawable.m);
		}
		
		//城市
		this.getLocation().setText(user.getLocation());
		//关注数
		this.getFriendsCount().setText(user.getFriends_count()+"");
		//粉丝数
		this.getFollowersCount().setText(user.getFollowers_count()+"");
		//微博数
		this.getStatusesCount().setText(user.getStatuses_count()+"");
		//个人描述
		this.getUserDescription().setText(user.getDescription());
		//设置“已关注按钮事件”
		//this.getPopHasConcerned().setOnClickListener(null);
		//设置“取消按钮事件”
/*		this.getPopCancel().setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				dismissPopupWin();
			}
		});*/
	}

}
