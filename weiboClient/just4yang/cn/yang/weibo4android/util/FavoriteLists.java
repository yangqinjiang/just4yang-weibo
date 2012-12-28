package cn.yang.weibo4android.util;

import weibo4android.Paging;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;
/**
 * 获取当前登录用户的收藏列表
 * @author yangqinjiang17
 *
 * @param <T>
 */
public class FavoriteLists<T> extends WeiboGetter<T>{

	private Paging paging;
	public FavoriteLists(Weibo weibo,Class<T> type,Paging paging){
		super(weibo, type);
		this.paging=paging;
		if(this.paging==null)
			this.paging=Paging.NULL;
	}

	@Override
	protected String buildUrl() {
		String url = Weibo.SERVER + "favorites.json";
		return url;
	}

	@Override
	protected String setHttpMethod() {
		return "GET";
	}

	@Override
	protected WeiboParameters createRequestParams() {
		//params
    	WeiboParameters params = new WeiboParameters();
    	params.add("source", Weibo.getAppKey());
    	params.add("count",String.valueOf(this.paging.getCount()));
    	params.add("page",String.valueOf(this.paging.getPage()));
		return params;
	}

}
