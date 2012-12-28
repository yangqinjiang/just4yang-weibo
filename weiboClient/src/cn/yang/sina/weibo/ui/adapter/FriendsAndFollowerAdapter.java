package cn.yang.sina.weibo.ui.adapter;

import java.util.List;

import cn.yang.weibo4android.User;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.yang.sina.weibo.imagecache.SimpleImageLoader;
import cn.yang.sina.weibo.ui.adapter.wrapper.FriendsAndFollowerViewWrapper;
import cn.yang.sina.weibo.R;
public class FriendsAndFollowerAdapter extends BaseAdapter {

	private List<User> friends;
	private Context context;
	public FriendsAndFollowerAdapter(Context context){
		this.context=context;
	}
	
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public int getCount() {
		return this.friends==null?0:
					this.friends.size();
	}

	public Object getItem(int arg0) {
		return this.friends==null?0:
					this.friends.get(arg0);
	}

	public long getItemId(int position) {
		return this.friends==null?-1:position;
	}

	private FriendsAndFollowerViewWrapper wrapper=null;
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView=convertView;
		if(rowView==null){
			rowView= LayoutInflater.from(this.context).
					inflate(R.layout.friend_info, null);
			wrapper=new FriendsAndFollowerViewWrapper(rowView);
			rowView.setTag(wrapper);
		}else{
			wrapper=(FriendsAndFollowerViewWrapper)rowView.getTag();
		}
		wrapper.bindView(this.friends.get(position));
		return rowView;
	}

}
