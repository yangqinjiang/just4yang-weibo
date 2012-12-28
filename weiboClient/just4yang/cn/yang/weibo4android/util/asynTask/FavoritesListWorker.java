package cn.yang.weibo4android.util.asynTask;

import weibo4android.Paging;

import cn.yang.weibo4android.Favorites;
import cn.yang.weibo4android.util.FavoriteLists;
import cn.yang.weibo4android.util.WeiboGetter;

import com.weibo.net.Weibo;

import android.content.Intent;
import android.util.Log;
/**
 * 获取当前登录用户的收藏列表
 * @author yangqinjiang17
 *
 */
public class FavoritesListWorker extends WeiboWorker{

	private Paging paging;
	
	public FavoritesListWorker(Weibo weibo, String receiverActionName,
			String keyOfResult,Paging paging) {
		super(weibo, receiverActionName, keyOfResult);
		this.paging = paging;
	}
	
	private Favorites getFavorites(Weibo weibo,Paging paging){
		Log.i(TAG, "getFavorites running...");
		WeiboGetter<Favorites> wg = new FavoriteLists<Favorites>(weibo, Favorites.class, paging);
    	return wg.getData();
	}
	@Override
	protected Intent doInBackground(Intent... params) {
		Favorites favs = this.getFavorites(this.getWeibo(),this.paging);
		return this.putWeiboData2Intent(favs);
	}
	

}
