package cn.yang.weibo4android.util;

import cn.yang.weibo4android.WeiboContent;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;
/**
 * 添加收藏 
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 * @param <T>
 */
public class CreateFavorite<T> extends WeiboGetter<T> {

	private WeiboContent weiboContent;
	public CreateFavorite(Weibo weibo,Class<T> type,WeiboContent weiboContent){
		super(weibo, type);
		this.weiboContent=weiboContent;
		if(this.weiboContent==null)
			this.weiboContent=WeiboContent.NULL;
	}

	@Override
	protected String buildUrl() {
		String url = Weibo.SERVER + "favorites/create.json";
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
    	params.add("id",this.weiboContent.getId());
		return params;
	}

}
