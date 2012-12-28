package cn.yang.sina.weibo.imagecache;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.Thread;
import java.lang.Thread.State;

import cn.yang.sina.weibo.beans.app.WeiboApp;
public class LazyImageLoader {

	/**Context上下文*/
	private Context context;
	/**生成一个ImageManager对象，用于管理图片数据*/
	private  static ImageManager imgManager = new ImageManager(WeiboApp.context);
	
	/**
	 * 分别从缓存cache或file中取出图片数据
	 * @param url  网络图片url
	 * @param callback  回调接口
	 * @return   bitmap  以url为key的网络图片
	 */
	public Bitmap get(String url,ImageLoaderCallback callback){
		
		Bitmap bitmap =null;
		if(imgManager.containsImg(url)){//1.判断图片是否存在缓存中ImageMapCache
			bitmap = imgManager.getFromImageMapCache(url);//getFromCache(url);//尝试从缓存中取得图片数据
			if(null!=bitmap){
				return bitmap;
			}else{
				//从文件缓存中获取
				bitmap=imgManager.getFromImageFileCache(url);
				if(null!=bitmap)return bitmap;
			}
		}else{
			//在开始线程之前将callback放进去
			callbacker.put(url, callback);
			//图片数据不在缓存中，开辟一条线程来获取图片数据
			startDownloadThread(url);
			
		}
		return bitmap;
		
	}


	/**空白队列 用于存储url的*/
	private BlockingQueue<String> urlQueue = new ArrayBlockingQueue<String>(50);
	/**将一个url放进urlQueue队列中*/
	private void putUrlToUrlQueue(String url){
		if(!urlQueue.contains(url)){//先判断url是否存在urlQueue队列中
			try {
				urlQueue.put(url);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**新建一个线程*/
	private DownloadImageThread downloadImgThread = new DownloadImageThread();
	/**开始一个线程*/
	private void startDownloadThread(String url){
		//在开始一个线程前，将url放进队列中...
		putUrlToUrlQueue(url);
		//获得线程的运行状态 state
		State state = downloadImgThread.getState();
		if(state==State.NEW){//NEW 至今尚未启动的线程处于这种状态
			downloadImgThread.start();
		}else if(state==State.TERMINATED){//TERMINATED 已退出的线程处于这种状态。
			downloadImgThread = new DownloadImageThread();
			downloadImgThread.start();
		}
	}
	/**图片url的key*/
	private static final String EXTRA_IMG_URL="Extra_img_url";
	/**图片Image的key*/
	private static final String EXTRA_IMG="Extra_img";
	
	/**线程类，用于下载网络图片数据*/
	private class DownloadImageThread extends Thread{
		/**一个线程运行控制开关*/
		private boolean isRun = true;
		@Override
		public void run() {

			try {
				while(isRun){
					String url = urlQueue.poll();//poll() 检索并移除此队列的头，如果此队列为空，则返回 null
					if(null==url)break;
					//TODO 
					Bitmap bitmap = imgManager.safeGet(url);
					//handler
					Message msg =handler.obtainMessage(MESS_ID);
					Bundle bundle = msg.getData();
					//使用putSerializable(key,value)和putParcelable(key,value)存放复杂的数据
					bundle.putSerializable(EXTRA_IMG_URL, url);
					bundle.putParcelable(EXTRA_IMG, bitmap);
					//发送消息
					handler.sendMessage(msg);
				}
			} catch (Exception e) {
				System.out.println("Thread Run Error");
			}finally{
				//shutThread();
			}
		
		}
		/**
		 * 关线程,将isRun设置为false
		 */
		public void shutThread() {
			isRun=false;
		}
	}


	/**生成一个CallbackManager实例对象*/
	private CallbackManager callbacker = new CallbackManager();
	/**消息ID*/
	private static final int MESS_ID=1;
	/**负责更新UI的handler*/
	private Handler handler= new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESS_ID:
			{
				//取出数据来
				Bundle bundle = msg.getData();
				String url = bundle.getString(EXTRA_IMG_URL);
				Bitmap bitmap = bundle.getParcelable(EXTRA_IMG);
				//调用 callback
				callbacker.callback(url, bitmap);
				break;
			}
			default:
				break;
			}
		
		}
		
	};
}


