package cn.yang.sina.weibo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ViewFlipper;
import cn.yang.sina.weibo.R;
public class FFSViewFlipper {

	public FFSViewFlipper(Context context,View base){
		setContext(context);
		setBase(base);
	}
	/**context 上下文*/
	private Context context;
	/**base 视图*/
	private View base;

	
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public View getBase() {
		return base;
	}
	public void setBase(View base) {
		this.base = base;
	}
	/**inflater */
	private LayoutInflater inflater;
	
	public LayoutInflater getInflater() {
		if(inflater==null)
			inflater = (LayoutInflater)getContext().
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return inflater;
	}
	public void setInflater(LayoutInflater inflater) {
		this.inflater = inflater;
	}
	/**mViewFlipper 翻转视图 */
	private ViewFlipper mViewFlipper;
	public ViewFlipper getmViewFlipper() {
		if(mViewFlipper==null)
			mViewFlipper=(ViewFlipper)getInflater().
				inflate(R.layout.view_flipper, null);
		return mViewFlipper;
	}
	public void setmViewFlipper(ViewFlipper mViewFlipper) {
		this.mViewFlipper = mViewFlipper;
	}
	
	/**mFriendsOfFriend 关注好友的好友列表的窗口*/
	private View mFriendsOfFriend;
	public View getmFriendsOfFriend() {
		if(mFriendsOfFriend==null)
			mFriendsOfFriend=(View)getInflater().
				inflate(R.layout.layout_friend_friends, null);
		return mFriendsOfFriend;
	}
	public void setmFriendsOfFriend(View mFriendsOfFriend) {
		this.mFriendsOfFriend = mFriendsOfFriend;
	}
	/**mFollowerOfFriend 关注好友的粉丝列表的窗口*/
	private View mFollowerOfFriend;
	public View getmFollowerOfFriend() {
		if(mFollowerOfFriend==null)
			mFollowerOfFriend=(View)getInflater().
				inflate(R.layout.layout_friend_followers, null);
		return mFollowerOfFriend;
	}
	public void setmFollowerOfFriend(View mFollowerOfFriend) {
		this.mFollowerOfFriend = mFollowerOfFriend;
	}
	/**mStatusOfFriend 关注好友的微博列表的窗口*/
	private View mStatusOfFriend;
	public View getmStatusOfFriend() {
		if(mStatusOfFriend==null)
			mStatusOfFriend=(View)getInflater().
				inflate(R.layout.layout_friend_status, null);
		return mStatusOfFriend;
	}
	public void setmStatusOfFriend(View mStatusOfFriend) {
		this.mStatusOfFriend = mStatusOfFriend;
	}


	/**lvFriendsOfFriend :关注好友的好友列表*/
	private ListView lvFriendsOfFriend;
	public ListView getLvFriendsOfFriend() {
		if(lvFriendsOfFriend==null);
			lvFriendsOfFriend=(ListView)getmFriendsOfFriend().
					findViewById(R.id.lvFriendsOfFriend);
		return lvFriendsOfFriend;
	}
	public void setLvFriendsOfFriend(ListView lvFriendsOfFriend) {
		this.lvFriendsOfFriend = lvFriendsOfFriend;
	}
	/**lvFollowersOfFriend :关注好友的粉丝列表*/
	private ListView lvFollowersOfFriend;
	public ListView getLvFollowersOfFriend() {
		if(lvFollowersOfFriend==null)
			lvFollowersOfFriend=(ListView)getmFollowerOfFriend().
				findViewById(R.id.lvFollowersOfFriend);
		return lvFollowersOfFriend;
	}
	public void setLvFollowersOfFriend(ListView lvFollowersOfFriend) {
		this.lvFollowersOfFriend = lvFollowersOfFriend;
	}

	/**lvStatusOfFriend:关注好友的微博列表*/
	private ListView lvStatusOfFriend;
	public ListView getLvStatusOfFriend() {
		if(lvStatusOfFriend==null)
			lvStatusOfFriend=(ListView)getmStatusOfFriend().
			findViewById(R.id.lvStatusOfFriend);
		return lvStatusOfFriend;
	}
	public void setLvStatusOfFriend(ListView lvStatusOfFriend) {
		this.lvStatusOfFriend = lvStatusOfFriend;
	}
	
	
	public ViewFlipper getViewFlipper(){
		
		getmViewFlipper().addView(getmFriendsOfFriend());
		getmViewFlipper().addView(getmFollowerOfFriend());
		getmViewFlipper().addView(getmStatusOfFriend());
		
		return getmViewFlipper();
	}

	/**设置ViewFlipper是否可见*/
	public void setViewFlipperVisibility(boolean isVisible){
		getmViewFlipper().setVisibility(isVisible?View.VISIBLE:View.GONE);
	}
	/**设置LvFriends适配器*/
	public void setLvFriendsAdapter(ListAdapter adapter){
		getLvFriendsOfFriend().setAdapter(adapter);
	}
	/**设置LvFollower适配器*/
	public void setLvFollowerAdapter(ListAdapter adapter){
		getLvFollowersOfFriend().setAdapter(adapter);
	}
	/**设置LvStatus适配器*/
	public void setLvStatusOfFriendAdapter(ListAdapter adapter){
		getLvStatusOfFriend().setAdapter(adapter);
	}
	public void setViewFlipperDisplayedChild(int childID){
		getmViewFlipper().setDisplayedChild(childID);
	}
	
	/**加载更多微博数据的视图，位于显示微博的列表底部*/
	private View vLoadMore = null;
	public View getvLoadMore() {
		if(vLoadMore==null)
			vLoadMore=getInflater().
				inflate(R.layout.more_layout,null);
		return vLoadMore;
	}
	public void setvLoadMore(View vLoadMore) {
		this.vLoadMore = vLoadMore;
	}
	/**
	 * 将“加载更多”视图加入ListView底部
	 * @param view
	 */
	public void addFooterView(View view){
		getLvFriendsOfFriend().addFooterView(getvLoadMore());
		getLvFollowersOfFriend().addFooterView(getvLoadMore());
		getLvStatusOfFriend().addFooterView(getvLoadMore());
	}
	
}
