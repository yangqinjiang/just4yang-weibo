package cn.yang.sina.weibo.view;

import cn.yang.sina.weibo.imagecache.SimpleImageLoader;
import cn.yang.weibo4android.User;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.yang.sina.weibo.R;
/**myViewWrapper 显示关注好友的详细信息的视图 */
public class FriendDetailsViewWrapper{

	/**构造方法*/
	public FriendDetailsViewWrapper(Context context,View base){
		setContext(context);
		setBase(base);
	}
	/**base 视图*/
	private View base;
	public View getBase() {
		return base;
	}
	public void setBase(View base) {
		this.base = base;
	}
	/**friend 这个类要用到的数据*/
	private User friend;
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
	}
	/**context 上下文*/
	private Context context;
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	/**关注好友是否有新浪认证*/
	private ImageView imgIsV;
	public ImageView getImgIsV() {
		if(imgIsV==null)
			imgIsV=(ImageView)getBase().
					findViewById(R.id.imgIsVOfFriend);
		return imgIsV;
	}
	public void setImgIsV(ImageView imgIsV) {
		this.imgIsV = imgIsV;
	}
	/**关注好友的头像*/
	private ImageView imgHead;
	public ImageView getImgHead() {
		if(imgHead==null)
			imgHead=(ImageView)getBase().
					findViewById(R.id.imgHeadOfFriendShow);
		return imgHead;
	}
	public void setImgHead(ImageView imgHead) {
		this.imgHead = imgHead;
	}
	/**关注好友的性别*/
	private ImageView imgGender;
	public ImageView getImgGender() {
		if(imgGender==null)
			imgGender=(ImageView)getBase().
						findViewById(R.id.imgGenderOfFriend);
		return imgGender;
	}
	public void setImgGender(ImageView imgGender) {
		this.imgGender = imgGender;
	}
	/**关注好友的昵称*/
	private TextView tvName;
	public TextView getTvName() {
		if(tvName==null)
			tvName=(TextView)getBase().
					findViewById(R.id.tvNameOfFriend);
		return tvName;
	}
	public void setTvName(TextView tvName) {
		this.tvName = tvName;
	}
	/**关注好友的地址*/
	private TextView tvLocation;
	public TextView getTvLocation() {
		if(tvLocation==null)
			tvLocation=(TextView)getBase().
						findViewById(R.id.tvLocationOfFriend);
		return tvLocation;
	}
	public void setTvLocation(TextView tvLocation) {
		this.tvLocation = tvLocation;
	}
	/**关注好友的个人描述*/
	private TextView tvDescription;
	public TextView getTvDescription() {
		if(tvDescription==null)
			tvDescription=(TextView)getBase().
							findViewById(R.id.tvDescriptionOfFriend);
		return tvDescription;
	}
	public void setTvDescription(TextView tvDescription) {
		this.tvDescription = tvDescription;
	}
	/**关注好友的最新微博数据*/
	private TextView tvNewestStatus;
	public TextView getTvNewestStatus() {
		if(tvNewestStatus==null)
			tvNewestStatus=(TextView)getBase().
							findViewById(R.id.tvNewestStatusOfFriend);
		return tvNewestStatus;
	}
	public void setTvNewestStatus(TextView tvNewestStatus) {
		this.tvNewestStatus = tvNewestStatus;
	}
	
	/**关注好友的关注数*/
	private TextView tvFriendsCount;
	public TextView getTvFriendsCount() {
		if(tvFriendsCount==null)
			tvFriendsCount=(TextView)getBase().
			findViewById(R.id.tvFriendsCountOfFriend);
		return tvFriendsCount;
	}
	public void setTvFriendsCount(TextView tvFriendsCount) {
		this.tvFriendsCount = tvFriendsCount;
	}
	/**关注好友的粉丝数*/
	private TextView tvFollowersCount;
	public TextView getTvFollowersCount() {
		if(tvFollowersCount==null)
			tvFollowersCount=(TextView)getBase().
			findViewById(R.id.tvFollowersCountOfFriend);
		return tvFollowersCount;
	}
	public void setTvFollowersCount(TextView tvFollowersCount) {
		this.tvFollowersCount = tvFollowersCount;
	}
	/**关注好友的微博数*/
	private TextView tvStatusesCount;
	public TextView getTvStatusesCount() {
		if(tvStatusesCount==null)
			tvStatusesCount=(TextView)getBase().
			findViewById(R.id.tvStatusesCountOfFriend);
		return tvStatusesCount;
	}
	public void setTvStatusesCount(TextView tvStatusesCount) {
		this.tvStatusesCount = tvStatusesCount;
	}
	public View setUpView(){
		
		//设置关注好友是否新浪认证
		getImgIsV().setVisibility(getFriend().isVerified()?
												View.VISIBLE:
												View.GONE);
		//设置关注好友的头像
		SimpleImageLoader.showImg(friend.getProfile_image_url().
				toString(),getImgHead());
		
		//设置关注好友的性别
		if(getFriend().getGender().equals("m"))
			getImgGender().setImageResource(R.drawable.m);
		else
			getImgGender().setImageResource(R.drawable.f);
		//设置关注好友的昵称
		getTvName().setText(getFriend().getName());
		//设置关注好友的地址
		getTvLocation().setText(getFriend().getLocation());
		//设置关注好友的个人描述
		getTvDescription().setText(getFriend().getDescription());
		//设置关注好友的最新发表的微博内容
		getTvNewestStatus().setText("最新发表的微博内容");
		
		//关注好友的关注数
		getTvFriendsCount().setText(getFriend().getFriends_count()+"");
		//关注好友的粉丝数
		getTvFollowersCount().setText(getFriend().getFollowers_count()+"");
		//关注好友的微博数
		getTvStatusesCount().setText(getFriend().getStatuses_count()+"");
		return getBase();
	}
	

}
