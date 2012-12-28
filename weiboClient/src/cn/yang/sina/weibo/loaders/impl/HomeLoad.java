package cn.yang.sina.weibo.loaders.impl;

import java.util.List;

import android.util.Log;

import cn.yang.sina.weibo.loaders.Loadable;
import cn.yang.weibo4android.Status;

import weibo4android.Paging;

public class HomeLoad implements Loadable<cn.yang.weibo4android.Status> {

	public Object loading(Object... objects) {
		
		return new Paging();
	}

	public List<cn.yang.weibo4android.Status> append(Object... objects) {
		Log.i("HomeActivity", "ShowWeiboStatusTask onProgressUpdate running...---6");
		List<cn.yang.weibo4android.Status> source=(List<Status>) objects[0];
		List<cn.yang.weibo4android.Status> target=(List<Status>) objects[1];
		source.addAll(0, target);
		return source;
	}



}
