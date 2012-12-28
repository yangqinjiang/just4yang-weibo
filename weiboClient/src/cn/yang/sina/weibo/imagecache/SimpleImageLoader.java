package cn.yang.sina.weibo.imagecache;

import cn.yang.sina.weibo.beans.app.WeiboApp;
import android.graphics.Bitmap;
import android.widget.ImageView;
/**
 * 图片加载器
 * @author Administrator
 *
 */
public class SimpleImageLoader {

	/**
	 * 图片加载器方法，
	 * @param url 网络图片URI
	 * @param view 需要显示这个图片的ImageView控件
	 */
	public static void showImg(String url,ImageView view){
		view.setTag(url);
		view.setImageBitmap(WeiboApp.lazyImageLoader.get(url, getCallback(url,view)));
	}
	
	private static ImageLoaderCallback getCallback(final String url,final ImageView view){
		return new ImageLoaderCallback() {
			public void refresh(String url, Bitmap bitmap) {
				if(url.equals(view.getTag().toString())){
						view.setImageBitmap(bitmap);
				}
			}
		};
	}
}
