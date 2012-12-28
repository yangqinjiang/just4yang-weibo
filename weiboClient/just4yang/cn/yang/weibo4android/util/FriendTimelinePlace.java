package cn.yang.weibo4android.util;

import weibo4android.Paging;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;

public class FriendTimelinePlace<T> extends WeiboGetter<T> {

	private Paging paging;
	public FriendTimelinePlace(Weibo weibo,Class<T> type,Paging paging){
		super(weibo, type);
		this.paging=paging;
	}

	@Override
	protected String buildUrl() {
		String url ="https://api.weibo.com/2/place/friends_timeline.json";
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
    	params.add("since_id", String.valueOf(paging.getSinceId()));
    	params.add("max_id", String.valueOf(paging.getMaxId()));
    	params.add("count", String.valueOf(paging.getCount()));
    	params.add("page", String.valueOf(paging.getPage()));
		return params;
	}

}
