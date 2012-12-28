package cn.yang.sina.weibo.loaders.impl;

import java.util.ArrayList;
import java.util.List;

import weibo4android.Paging;

import cn.yang.sina.weibo.loaders.Loadable;
import cn.yang.weibo4android.Users;

public class FriendLoad implements Loadable<Users> {

	public Object loading(Object... objects) {
		return new Paging();
	}

	public List<Users> append(Object... objects) {
		//将第一次获取的结果返回
		Users result =(Users)objects[1];
		
		List<Users> users =new ArrayList<Users>();
		users.add(result);
		return users;
	}



}
