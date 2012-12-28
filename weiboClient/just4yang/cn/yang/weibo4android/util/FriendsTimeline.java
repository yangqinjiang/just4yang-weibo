package cn.yang.weibo4android.util;

import weibo4android.Paging;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;

/**
 * 获取当前登录用户及其所关注用户的最新微博 
 * */
public class FriendsTimeline<T> extends WeiboGetter<T> {

	private Paging paging;
	public FriendsTimeline(Weibo weibo,Class<T> type,Paging paging){
		super(weibo,type);
		this.paging=paging;
		if(this.paging==null)
			this.paging=Paging.NULL;
	}

	@Override
	protected String buildUrl() {
		String url = Weibo.SERVER+"statuses/friends_timeline.json";
		return url;
	}

	@Override
	protected String setHttpMethod() {
		return "GET";
	}

	@Override
	protected WeiboParameters createRequestParams() {
		//params
    	WeiboParameters bundle = new WeiboParameters();
    	bundle.add("source", Weibo.getAppKey());
    	bundle.add("since_id", String.valueOf(paging.getSinceId()));
    	bundle.add("max_id", String.valueOf(paging.getMaxId()));
    	bundle.add("count", String.valueOf(paging.getCount()));
    	bundle.add("page", String.valueOf(paging.getPage()));
    	 
		return bundle;
	}

}
