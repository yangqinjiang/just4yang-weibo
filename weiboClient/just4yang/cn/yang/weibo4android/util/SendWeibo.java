package cn.yang.weibo4android.util;

import cn.yang.weibo4android.WeiboContent;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;

public class SendWeibo<T> extends WeiboGetter<T> {

	private WeiboContent content;
	public SendWeibo(Weibo weibo,Class<T> type,WeiboContent content){
		super(weibo, type);
		this.content=content;
		if(this.content==null){
			this.content=WeiboContent.NULL;
			throw new RuntimeException("weibo content must not null");
		}
	}
	
	@Override
	protected String buildUrl() {
		String url = Weibo.SERVER + "statuses/update.json";
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
    	params.add("status",this.content.getWeiboStatus());
		return params;
	}

}
