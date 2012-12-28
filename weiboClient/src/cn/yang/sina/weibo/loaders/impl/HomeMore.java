package cn.yang.sina.weibo.loaders.impl;

import java.util.List;

import weibo4android.Paging;
import android.util.Log;
import android.widget.BaseAdapter;
import cn.yang.sina.weibo.loaders.Loadable;
import cn.yang.weibo4android.Status;

public class HomeMore implements Loadable<cn.yang.weibo4android.Status> {

	private static final String TAG = "Loadable";

	public Object loading(Object... objects) {
		Log.i(TAG, "获取更多微博内容...");
		BaseAdapter adapter = (BaseAdapter)objects[0];
		// 取出列表里最后一项的微博内容status
		Status temp = (Status) adapter.getItem(adapter.getCount() - 1);
		// 再取出status的max_id
		Long max_id = Long.parseLong(temp.getMid()) - 1;
		// set到paging里去
		Paging paging =new Paging(1, 5);
		paging.setMaxId(max_id);
		return paging;
	}

	public List<cn.yang.weibo4android.Status> append(Object... objects) {
		//获取更多时,加载到列表尾部
		List<cn.yang.weibo4android.Status> source=(List<Status>) objects[0];
		List<cn.yang.weibo4android.Status> target=(List<Status>) objects[1];
		for(Status s: target){
			source.add(s);
		}
		return source;
	}

}
