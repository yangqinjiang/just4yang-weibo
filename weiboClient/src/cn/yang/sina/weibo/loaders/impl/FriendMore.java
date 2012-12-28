package cn.yang.sina.weibo.loaders.impl;

import java.util.ArrayList;
import java.util.List;

import weibo4android.Paging;

import cn.yang.sina.weibo.loaders.Loadable;
import cn.yang.weibo4android.Users;

public class FriendMore implements Loadable<Users> {

	//private static final String TAG = "FriendMore";

	public Object loading(Object... objects) {
		Paging paging =new Paging();
		//获取下一个cursor
		paging.setCursor((Integer)objects[0]);
		return paging;
	}

	public List<Users> append(Object... objects) {
		
		Users result =(Users)objects[1];
		List<Users> users =new ArrayList<Users>();
		users.add(result);
		return users;
	}

}
