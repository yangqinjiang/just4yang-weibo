package cn.yang.sina.weibo.ui.adapter.wrapper;

import cn.yang.weibo4android.Status;
import cn.yang.weibo4android.User;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.yang.sina.weibo.imagecache.SimpleImageLoader;
import cn.yang.sina.weibo.R;
public class FriendsAndFollowerViewWrapper {

	/**base View*/
	private View base;
	
	public View getBase() {
		return base;
	}
	/**关注好友或粉丝的头像*/
	private ImageView imgHead;
	public ImageView getImgHead() {
		if(imgHead==null)
			imgHead=(ImageView) getBase().
					findViewById(R.id.popUserIcon);
		return imgHead;
	}

	/**关注好友或粉丝的按钮*/
	private Button btnFollowerMe;
	public Button getBtnFollowerMe() {
		if(btnFollowerMe==null)
			btnFollowerMe=(Button)getBase().
					findViewById(R.id.btnFollowerMe);
		return btnFollowerMe;
	}

	/**关注好友或粉丝的昵称*/
	private TextView tvName;
	public TextView getTvName() {
		if(tvName==null)
			tvName=(TextView)getBase().
					findViewById(R.id.popScreenName);
		return tvName;
	}

	/**关注好友或粉丝是否有新浪认证*/
	private ImageView imgIsV;
	public ImageView getImgIsV() {
		if(imgIsV==null)
			imgIsV=(ImageView) getBase().
				findViewById(R.id.popIsV);
		return imgIsV;
	}

	/**关注好友或粉丝的性别*/
	private ImageView imgGender;
	public ImageView getImgGender() {
		if(imgGender==null)
			imgGender=(ImageView) getBase().
					findViewById(R.id.popGender);
		return imgGender;
	}

	/**关注好友或粉丝的地址*/
	private TextView tvLocation;
	public TextView getTvLocation() {
		if(tvLocation==null)
			tvLocation=(TextView)getBase().
					findViewById(R.id.popLocation);
		return tvLocation;
	}

	/**关注好友或粉丝的关注数*/
	private TextView tvFriendsCount;
	public TextView getTvFriendsCount() {
		if(tvFriendsCount==null)
			tvFriendsCount=(TextView)getBase().
					findViewById(R.id.popFriendsCount);
		return tvFriendsCount;
	}

	/**关注好友或粉丝的粉丝数*/
	private TextView tvFollowersCount;
	public TextView getTvFollowersCount() {
		if(tvFollowersCount==null)
			tvFollowersCount=(TextView)getBase().
					findViewById(R.id.popFollowersCount);
		return tvFollowersCount;
	}

	/**关注好友或粉丝的微博数*/
	private TextView tvStatusesCount;
	public TextView getTvStatusesCount() {
		if(tvStatusesCount==null)
			tvStatusesCount=(TextView)getBase().
					findViewById(R.id.popStatusesCount);
		return tvStatusesCount;
	}

	/**关注好友或粉丝的个人描述*/
	private TextView tvDescription;
	public TextView getTvDescription() {
		if(tvDescription==null)
			tvDescription=(TextView)getBase().
					findViewById(R.id.popDescription);
		return tvDescription;
	}

	/**关注好友或粉丝的最新发表的微博*/
	private TextView tvNewsStatus;
	public TextView getTvNewsStatus() {
		if(tvNewsStatus==null)
			tvNewsStatus=(TextView)getBase().
					findViewById(R.id.popNewsStatus);
		return tvNewsStatus;
	}
	/**构造方法*/
	public FriendsAndFollowerViewWrapper(View base){
		this.base=base;
	}
	/**
	 * 使用friend提供的数据来邦定view的数据
	 * @param friend  关注好友或粉丝
	 */
	public void bindView(User friend){
		//设置关注好友的头像
		SimpleImageLoader.showImg(friend.getProfile_image_url().toString(),
								getImgHead());
		//设置关注好友的昵称
		getTvName().setText(friend.getName());
		//设置关注好友是否有新浪认证的
		getImgIsV().setVisibility(friend.isVerified()?
										View.VISIBLE:
										View.GONE);
		//设置关注好友的性别
		if(friend.getGender().equalsIgnoreCase("m"))
			getImgGender().setImageResource(R.drawable.m);
		else if(friend.getGender().equalsIgnoreCase("f"))
			getImgGender().setImageResource(R.drawable.f);
		//设置关注好友的地址
		getTvLocation().setText(friend.getLocation());
		//设置关注好友的关注数
		getTvFriendsCount().setText(friend.getFriends_count()+"");
		//设置关注好友的粉丝数
		getTvFollowersCount().setText(friend.getFollowers_count()+"");
		//设置关注好友的微博数
		getTvStatusesCount().setText(friend.getStatuses_count()+"");
		//设置关注好友的个人描述
		getTvDescription().setText(friend.getDescription());
		//设置关注好友的最新发表的微博内容
		Status friendNewStatus = friend.getStatus();
		if(friendNewStatus!=null)
			getTvNewsStatus().setText(friendNewStatus.getText()+"");
		else
			getTvNewsStatus().setText("");
	}
	
}
