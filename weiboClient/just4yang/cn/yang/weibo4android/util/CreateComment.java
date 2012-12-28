package cn.yang.weibo4android.util;

import cn.yang.weibo4android.WeiboContent;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;

public class CreateComment<T> extends WeiboGetter<T> {

	private WeiboContent weiboContent;
	public CreateComment(Weibo weibo,Class<T> type,WeiboContent weiboContent){
		super(weibo, type);
		this.weiboContent=weiboContent;
		if(this.weiboContent==null){
			this.weiboContent=WeiboContent.NULL;
			throw new RuntimeException("weibo content must not null");
		}
	}
	
	@Override
	protected String buildUrl() {
		String url = Weibo.SERVER + "comments/create.json";
		return url;
	}

	@Override
	protected String setHttpMethod() {
		return "POST";
	}

	@Override
	protected WeiboParameters createRequestParams() {
		//params
    	WeiboParameters params = new WeiboParameters();
    	params.add("source", Weibo.getAppKey());
    	params.add("id", weiboContent.getId());
    	params.add("comment",weiboContent.getWeiboStatus());
		return params;
	}
}
