package cn.yang.sina.weibo.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import cn.yang.weibo4android.User;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.yang.sina.weibo.R;
public class LoginGridViewAdapter extends BaseAdapter {
	private Context context;
	List<User> users;
	public LoginGridViewAdapter(Context context,List<User> users){
		this.context=context;
		this.users = users;
	}
	public LoginGridViewAdapter(Context context){
		this.context=context;
		this.users=new ArrayList<User>();
	}
	public void addUser(User user){
		this.users.add(user);
		this.notifyDataSetChanged();
	}
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getCount() {
		// TODO 返回users里的数目
		return users==null?0:users.size();
	}

	public Object getItem(int position) {
		// TODO 根据position返回users里的某个user
		return users==null?null:users.get(position);
	}

	public long getItemId(int position) {
		// TODO 根据position返回users里的某个user的Id
		return users.get(position).getId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if(v==null){
			v = LayoutInflater.from(context).inflate(R.layout.login_gridview_item,null);
			ImageView img_ShowUserHead = (ImageView)v.findViewById(R.id.img_ShowUserHead);
			TextView txt_ShowUserName = (TextView)v.findViewById(R.id.txt_ShowUserName);
			//if(null!=users.get(position).getUserIcon()){
			//	img_ShowUserHead.setImageDrawable(users.get(position).getUserIcon());
			//}else{
				img_ShowUserHead.setImageDrawable(context.getResources().getDrawable(R.drawable.home_icon));
			//}
			txt_ShowUserName.setText(users.get(position).getScreen_name());
			
		}
		return v;
	}

}
