package cn.yang.sina.weibo.loaders.impl;

import java.util.List;

import weibo4android.Paging;
import android.util.Log;
import android.widget.BaseAdapter;
import cn.yang.sina.weibo.loaders.Loadable;
import cn.yang.weibo4android.Status;

public class HomeRefresh implements Loadable<cn.yang.weibo4android.Status> {
	private static final String TAG = "Loadable";
	
	public Object loading(Object... objects) {
		
		Log.i(TAG, "刷新最新的微博内容...");
		BaseAdapter adapter = (BaseAdapter)objects[0];
		// 得到微博列表里的第一个微博内容status
		Status temp = (Status) adapter.getItem(0);
		Long since_id = temp.getId();
		Paging paging =new Paging(1, 5);		
		// 取得 since_id 也就是第一个微博ID
		paging.setSinceId(since_id);
		return paging;
	}

	@SuppressWarnings("unchecked")
	public List<cn.yang.weibo4android.Status> append(Object... objects) {
		List<cn.yang.weibo4android.Status> source=(List<Status>) objects[0];
		List<cn.yang.weibo4android.Status> target=(List<Status>) objects[1];
		source.addAll(0, target);
		return source;
	}
}
