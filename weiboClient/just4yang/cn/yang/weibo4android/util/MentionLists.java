package cn.yang.weibo4android.util;

import weibo4android.Paging;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;
/*获取最新的提到登录用户的微博列表，即@我的微博*/
public class MentionLists<T> extends WeiboGetter<T> {

	private Paging paging;
	public MentionLists(Weibo weibo,Class<T> type,Paging paging){
		super(weibo, type);
		this.paging=paging;
		if(this.paging==null)
			this.paging=Paging.NULL;
	}

	@Override
	protected String buildUrl() {
		String url = Weibo.SERVER + "statuses/mentions.json";
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
    	params.add("filter_by_type", 0+"");
    	params.add("trim_user", 0+"");

		return params;
	}


}
