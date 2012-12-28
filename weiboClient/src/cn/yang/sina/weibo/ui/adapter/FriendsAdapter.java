package cn.yang.sina.weibo.ui.adapter;

import java.util.List;

import com.weibo.net.Weibo;

import weibo4android.Paging;


import cn.yang.sina.weibo.R;
import cn.yang.weibo4android.User;
import cn.yang.weibo4android.Users;
import cn.yang.weibo4android.util.FriendsList;
import cn.yang.weibo4android.util.WeiboGetter;
import cn.yang.sina.weibo.imagecache.SimpleImageLoader;
import cn.yang.sina.weibo.loaders.Loadable;
import cn.yang.sina.weibo.loaders.impl.FriendLoad;
import cn.yang.sina.weibo.loaders.impl.FriendMore;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FriendsAdapter extends BaseAdapter {

	private static final String TAG = "FriendsAdapter";
	private Context context;
	private Users currentFriends;
	private Loadable<Users> currentLoader;
	public FriendsAdapter(Context context){
		this.context=context;
		this.currentFriends=new Users();
		//第一次创建实例时,加载数据
		this.currentLoader=new FriendLoad();
		getWeiboData(Paging.NULL,this.currentLoader);
	}
	
	protected void getWeiboData(Paging paging,Loadable<Users> loader){
		Paging newPaging = (Paging)loader.loading(paging.getCursor());
		//TODO 不能用固定值,1705400461
		new ShowFriendTask(Weibo.getInstance(),"1705400461",newPaging,loader).execute(new Intent());
	}
	
	public void setUsers(Users userses){
		this.currentFriends=userses;
		this.notifyDataSetChanged();
	}
	

	public int getCount() {
		List<User> users = this.currentFriends.getUsers();
		return users!=null?users.size():0;
	}

	public Object getItem(int position) {
		List<User> users = this.currentFriends.getUsers();
		return users!=null?users.get(position):0;
	}

	public long getItemId(int position) {
		List<User> users = this.currentFriends.getUsers();
		return users!=null?users.get(position).getId():0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		int lastPostion = this.getCount()-1;

		
		View view=convertView;
		FriendHeadViewWrapper wrapper=null;
		if(view==null){
			//生成一个view
			view=LayoutInflater.from(this.context).
					inflate(R.layout.friends_profile_card, null);
			wrapper=new FriendHeadViewWrapper(view);
			view.setTag(wrapper);
		}else{
			wrapper=(FriendHeadViewWrapper)view.getTag();
		}
		wrapper.getTxtFriendName().setText(this.currentFriends.getUsers().get(position).getScreen_name().toString());
		//设置关注好友的头像
		SimpleImageLoader.showImg(this.currentFriends.getUsers().get(position).getAvatar_large().toString(),wrapper.getImgFriendHead());
/*		if(position==0){
			//给第一个ImageView加上点击事件
			wrapper.getImgFriendHead().setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Toast.makeText(context, "刷新数据", Toast.LENGTH_LONG).show();
					//new ShowFriendTask(currentLoader).execute("");
				}
			});
		}*/
		//开始时,允许加载更多
		if(position==lastPostion){
			wrapper.getImgFriendHead().setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					if(shouldLoadMore){
						shouldLoadMore=false;
						//当在加载更多时,将shouldLoadMore设置为false
						Toast.makeText(context, "加载更多数据", Toast.LENGTH_LONG).show();
						currentLoader=new FriendMore();//加载更多微博数据
						String next_cursor =currentFriends.getNext_cursor();
						Paging paging =new Paging();
						paging.setCursor(Integer.valueOf(next_cursor));
						getWeiboData(paging,currentLoader);
					}
				}
			});
		}
		
		return view;
	}
	/*应当加载更多 控制标志*/
	private boolean shouldLoadMore=true;
	private class ShowFriendTask extends AsyncTask<Intent,Void , Users>{

		private Weibo weibo;
		private String uid;
		private Paging paging;
		private Loadable<Users> loader;
		public ShowFriendTask(Weibo weibo,String uid,Paging paging,Loadable<Users> loader) {
			this.weibo=weibo;
			this.uid=uid;
			this.paging=paging;
			this.loader=loader;
		}
		/**
		 * 获取某个用户的关注列表
		 * @param uid 用户UID
		 * @param paging  分页参数
		 * @return users Users的实例，Users的结构与Json数据结构相同
		 */
		private Users getFriendsList(Weibo weibo,String uid,Paging paging){
			WeiboGetter<Users> wg = 
					new FriendsList<Users>(weibo, Users.class, uid, paging);
	    	return wg.getData();
		}
		@Override
		protected Users doInBackground(Intent... params) {
			Users friends = this.getFriendsList(this.weibo,this.uid,this.paging);
			
			if(friends==null){
				friends=Users.NULL;
			}
			 List<User> users =friends.getUsers();
			for (User user :users) {
				Log.i(TAG, "return user: "+user.getName());
			}
		
			return friends;
		}

		
		@Override
		protected void onPostExecute(Users result) {
			super.onPostExecute(result);
			//获取到返回的数据
			Users backResult =this.loader.append(currentFriends,result).get(0);
			//合并起来
			List<User> us= currentFriends.getUsers();
			us.addAll(backResult.getUsers());
			//设置到currentFriends中
			currentFriends.setUsers(us);
			currentFriends.setNext_cursor(backResult.getNext_cursor());
			currentFriends.setPrevious_cursor(backResult.getPrevious_cursor());
			currentFriends.setTotal_number(backResult.getTotal_number());
			notifyDataSetChanged();
			shouldLoadMore=true;
		}
		
	}
	
	
	class FriendHeadViewWrapper{
		/**base  view*/
		View base;
		/**imgFriendHead: 关注好友的头像*/
		ImageView imgFriendHead=null;
		TextView txtFriendName =null;
		
		FriendHeadViewWrapper(View base){
			this.base=base;
		}
		public ImageView getImgFriendHead() {
			if(imgFriendHead==null)
				imgFriendHead=(ImageView)getBase().
					findViewById(R.id.imgHeadOfFriend);
			return imgFriendHead;
		}
		
		public TextView getTxtFriendName(){
			if(txtFriendName==null)
				txtFriendName=(TextView)getBase().
					findViewById(R.id.txtHeadOfFriend);
			return txtFriendName;
		}
		public View getBase() {
			return base;
		}
		
		
	}
}
