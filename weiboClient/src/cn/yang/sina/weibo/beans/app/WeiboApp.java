package cn.yang.sina.weibo.beans.app;

import cn.yang.sina.weibo.imagecache.LazyImageLoader;
import android.app.Application;
import android.content.Context;

public class WeiboApp extends Application {

	/**LazyImageLoader实例对象*/
	public static LazyImageLoader lazyImageLoader;
	/**Context上下文对象*/
	public static Context context = null;
	/**单例模式，返回Context上下文对象*/
	public  Context getAppContext(){
		if(null==context)
			context=this.getApplicationContext();
		return context;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		context = this.getAppContext();
		lazyImageLoader = new LazyImageLoader();
	}
}
