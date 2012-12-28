package cn.yang.weibo4android.util;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;

public class UserProfile<T> extends WeiboGetter<T> {

	private String uid;
	public UserProfile(Weibo weibo,Class<T> type,String uid){
		super(weibo, type);
		this.uid=uid;
	}

	@Override
	protected String buildUrl() {
		String url = Weibo.SERVER + "users/show.json";
		return url;
	}

	@Override
	protected String setHttpMethod() {
		// TODO Auto-generated method stub
		return "GET";
	}

	@Override
	protected WeiboParameters createRequestParams() {
		//params
    	WeiboParameters params = new WeiboParameters();
    	params.add("source", Weibo.getAppKey());
    	if(uid!=null)
    		params.add("uid",uid);
		return params;
	}

}
