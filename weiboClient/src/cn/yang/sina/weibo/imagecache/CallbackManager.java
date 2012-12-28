package cn.yang.sina.weibo.imagecache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import android.graphics.Bitmap;

public class CallbackManager {

	/**???????*/
	private ConcurrentHashMap<String, List<ImageLoaderCallback>> callbackMap;
	public CallbackManager(){
		callbackMap =new ConcurrentHashMap<String, List<ImageLoaderCallback>>();
	}
	/**放进callbackMap中*/
	public void put(String url,ImageLoaderCallback callback){
		if(!callbackMap.contains(url))
			callbackMap.put(url, new ArrayList<ImageLoaderCallback>());
		callbackMap.get(url).add(callback);
	}
	/**callback方法,暂时不清楚它的工作原理*/
	public void callback(String url,Bitmap bitmap){
		//取出
		List<ImageLoaderCallback> callbacks = callbackMap.get(url);
		if(null==callbacks)return;
		//不断地调用refresh(,)更新UI，
		for (ImageLoaderCallback callback:callbacks) {
			callback.refresh(url, bitmap);
		}
		//清除
		callbacks.clear();
		callbackMap.remove(url);
	}
}
