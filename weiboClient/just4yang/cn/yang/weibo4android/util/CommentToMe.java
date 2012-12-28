package cn.yang.weibo4android.util;

import weibo4android.Paging;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;
/**获取当前登录用户所接收到的评论列表*/
public class CommentToMe<T> extends WeiboGetter<T> {

	private Paging paging;
	public CommentToMe(Weibo weibo,Class<T> type,Paging paging){
		super(weibo, type);
		this.paging=paging;
		if(this.paging==null){
			this.paging=Paging.NULL;
			//throw new RuntimeException("weibo content must not null");
		}
	}
	
	@Override
	protected String buildUrl() {
		String url = Weibo.SERVER + "comments/to_me.json";
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
    	params.add("since_id", this.paging.getSinceId()+"");
    	params.add("max_id", this.paging.getMaxId()+"");
    	params.add("count",this.paging.getCount()+"");
    	params.add("page", this.paging.getPage()+"");
    	params.add("filter_by_author", 0+"");
    	params.add("filter_by_source", 0+"");
    	
		return params;
	}
}
