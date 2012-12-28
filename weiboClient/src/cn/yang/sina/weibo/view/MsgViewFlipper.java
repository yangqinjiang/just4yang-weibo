package cn.yang.sina.weibo.view;

import com.laohuai.appdemo.customui.ui.MyListView;
import com.laohuai.appdemo.customui.ui.MyListView.OnRefreshListener;
import com.weibo.net.Weibo;

import cn.yang.weibo4android.Comments;
import cn.yang.weibo4android.Favorites;
import cn.yang.weibo4android.util.CommentToMe;
import cn.yang.weibo4android.util.FavoriteLists;
import cn.yang.weibo4android.util.MentionLists;
import cn.yang.weibo4android.util.WeiboGetter;
import cn.yang.sina.weibo.beans.Statuses;
import cn.yang.sina.weibo.ui.adapter.MsgAdapter;
import cn.yang.sina.weibo.ui.adapter.WeiboFavoritesAdapter;
import cn.yang.sina.weibo.ui.adapter.WeiboStatusAdapter;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewFlipper;
import cn.yang.sina.weibo.R;
public class MsgViewFlipper {

	protected static final String TAG = "MsgViewFlipper";
	public MsgViewFlipper(Context context,View base){
		Log.i(TAG, "MsgViewFlipper init...");
		setContext(context);
		setBase(base);
	}
	
	/**context 上下文*/
	private Context context;
	/**base 视图*/
	private View base;
	/**inflater */
	private LayoutInflater inflater;
	/**mViewFlipper 翻转视图 */
	private ViewFlipper mViewFlipper;
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
	public LayoutInflater getInflater() {
		if(inflater==null)
			inflater = (LayoutInflater)getContext().
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return inflater;
	}
	public void setInflater(LayoutInflater inflater) {
		this.inflater = inflater;
	}
	public ViewFlipper getmViewFlipper() {
		if(mViewFlipper==null)
			mViewFlipper=(ViewFlipper)getInflater().
				inflate(R.layout.view_flipper, null);
		return mViewFlipper;
	}
	public void setmViewFlipper(ViewFlipper mViewFlipper) {
		this.mViewFlipper = mViewFlipper;
	}
	/**mMsgMainPage 消息主页*/
	private View mMsgMainPage;
	
	public View getmMsgMainPage() {
		if(mMsgMainPage==null)
			mMsgMainPage=(View)getInflater().
			inflate(R.layout.layout_msg_main_page, null);
		
		return mMsgMainPage;
	}
	public void setmMsgMainPage(View mMsgMainPage) {
		this.mMsgMainPage = mMsgMainPage;
	}

	/**mMsgAtMe @我的视图*/
	private View mMsgAtMe;
	public View getmMsgAtMe() {
		if(mMsgAtMe==null)
			mMsgAtMe=(View)getInflater().
			inflate(R.layout.layout_msg_at_me,null);
		
		return mMsgAtMe;
	}
	public void setmMsgAtMe(View mMsgAtMe) {
		this.mMsgAtMe = mMsgAtMe;
	}
	/**mMsgFavorites 收藏列表视图*/
	private View mMsgFavorites;
	
	public View getmMsgFavorites() {
		if(mMsgFavorites==null)
			mMsgFavorites=(View)getInflater().
			inflate(R.layout.layout_msg_favorite, null);
		return mMsgFavorites;
		
	}
	public void setmMsgFavorites(View mMsgFavorites) {
		this.mMsgFavorites = mMsgFavorites;
	}
	/**返回一个ViewFlipper给调用者*/
	public ViewFlipper getViewFlipper(){
		Log.i(TAG, "getViewFlipper running...");
		//0
		getmViewFlipper().addView(getmMsgMainPage());
		//1
		getmViewFlipper().addView(getmMsgAtMe());
		//2
		getmViewFlipper().addView(getmMsgFavorites());
		bindView();
		return getmViewFlipper();
	}
	
	private void bindView(){
		Log.i(TAG, "bindView running...");
		//Comment 评论
		lvMsgMainPage =(MyListView) getmMsgMainPage().findViewById(R.id.lvMsgMainPage);
		commentAdapter=new MsgAdapter(this.context);
		lvMsgMainPage.setonRefreshListener(new OnRefreshListener() {
			
			public void onRefresh() {
				// TODO 20121204 加载评论列表数据
				Log.i(TAG, "lvMsgMainPage OnRefreshing...");
				new AsyncTask<Void, Void, Comments>(){

					@Override
					protected Comments doInBackground(Void... params) {

						WeiboGetter<Comments> wg = new CommentToMe<Comments>(Weibo.getInstance(), Comments.class, null);
						Comments coms =wg.getData();
						if(coms==null)
							return Comments.NULL;
						return coms;
					}
					protected void onPostExecute(Comments result) {
						commentAdapter.setComs(result.getComments());
						lvMsgMainPage.onRefreshComplete();
					};
					
				}.execute();

			}
		});
		
		lvMsgMainPage.setAdapter(commentAdapter);
		
		//atMeAdapter  @我
		lvAtMe=(MyListView)getmMsgAtMe().findViewById(R.id.lvMsgAtMe);
		atMeAdapter=new WeiboStatusAdapter(this.context);
		lvAtMe.setonRefreshListener(new OnRefreshListener() {
			
			public void onRefresh() {
				// TODO 20121204
				Log.i(TAG, "lvAtMe OnRefreshing...");
				new AsyncTask<Void, Void, Statuses>(){

					@Override
					protected Statuses doInBackground(Void... params) {

						WeiboGetter<Statuses> wg = new MentionLists<Statuses>(Weibo.getInstance(), Statuses.class,null);
				    	Statuses statuses =wg.getData();
				    	if(statuses==null)
				    		return Statuses.NULL;
						return statuses;
					}
					protected void onPostExecute(Statuses result) {
						atMeAdapter.setStatus(result.getStatuses());
						lvAtMe.onRefreshComplete();
					};
					
				}.execute();
			}
		});
		lvAtMe.setAdapter(atMeAdapter);
		
		//FavoritesAdapter  收藏列表
		lvFavorites=(MyListView)getmMsgFavorites().
				findViewById(R.id.lvMsgFavorite);
		favoritesAdapter=new WeiboFavoritesAdapter(this.context);
		lvFavorites.setonRefreshListener(new OnRefreshListener() {
			
			public void onRefresh() {
				// TODO 20121204
				Log.i(TAG, "lvFavorites OnRefreshing...");
				new AsyncTask<Void, Void, Favorites>(){

					@Override
					protected Favorites doInBackground(Void... params) {

						WeiboGetter<Favorites> wg = new FavoriteLists<Favorites>(Weibo.getInstance(), Favorites.class, null);
				    	Favorites favs = wg.getData();
				    	if(favs==null)
				    		return Favorites.NULL;
						return favs;
					}
					protected void onPostExecute(Favorites result) {
						favoritesAdapter.setStatus(result.getFavorites());
				    	lvFavorites.onRefreshComplete();
					};
					
				}.execute();
			}
		});
		lvFavorites.setAdapter(favoritesAdapter);
	}
	
	private MyListView lvMsgMainPage;
	private MyListView lvAtMe;
	private MyListView lvFavorites;
	
	private MsgAdapter commentAdapter;

	private WeiboStatusAdapter atMeAdapter;

	private WeiboFavoritesAdapter favoritesAdapter;

}
