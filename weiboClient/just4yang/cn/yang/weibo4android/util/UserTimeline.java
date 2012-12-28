package cn.yang.weibo4android.util;

import weibo4android.Paging;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;
/**
 * 获取用户发布的微博
 * @author yangqinjiang17
 * @param <T> 返回数据类型 如Statuses,Users,User等
 */
public class UserTimeline<T> extends WeiboGetter<T> {

	private Paging paging;
	private String uid;
	/**
	 * 
	 * @param weibo Weibo实例
	 * @param type 返回数据类型的class字节码
	 * @param paging Paging实例，可为null。如果为null,则paging的page=1,count=0
	 * @param uid 用户的uid
	 */
	public UserTimeline(Weibo weibo,Class<T> type,Paging paging,String uid){
		super(weibo, type);
		this.paging=paging;
		this.uid=uid;
		if(this.paging==null)
			this.paging=Paging.NULL;
	}

	@Override
	protected String buildUrl() {
		String url = Weibo.SERVER+"statuses/user_timeline.json";
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
    	params.add("uid",uid);
		return params;
	}

}
