package cn.yang.weibo4android.util;

import weibo4android.Paging;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;
/**
 *  获取用户的关注列表
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 * @param <T> 返回数据类型 如Statuses,Users,User等
 */
public class FriendsList<T> extends WeiboGetter<T> {


	private String uid;
	private Paging paging;
	/**
	 * @param weibo Weibo实例    
	 * @param type 返回数据类型的class字节码
	 * @param uid 用户的uid
	 * @param paging Paging实例，可为null。如果为null,则paging的page=1,count=0
	 */
	public FriendsList(Weibo weibo,Class<T> type,String uid,Paging paging){
		super(weibo, type);
		this.uid=uid;
		this.paging=paging;
		if(this.paging==null)
			this.paging=Paging.NULL;
	}
	
	@Override
	protected String buildUrl() {
		String url = Weibo.SERVER + "friendships/friends.json";
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
    	params.add("uid",uid);
    	params.add("count",this.paging.getCount()+"");
    	params.add("cursor",this.paging.getCursor()+"");
		return params;
	}

}
