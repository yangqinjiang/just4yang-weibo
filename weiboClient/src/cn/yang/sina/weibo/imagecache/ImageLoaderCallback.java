package cn.yang.sina.weibo.imagecache;

import android.graphics.Bitmap;

/**负责刷新UI*/
public interface ImageLoaderCallback {

	/**
	 * 调用这个方法来刷新UI，比如ImageView
	 * @param url  从网络上下载来的图片Url
	 * @param bitmap  Bitmap图片对象
	 */
	void refresh(String url,Bitmap bitmap);
}
