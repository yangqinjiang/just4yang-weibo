package cn.yang.weibo4android.util;

import com.weibo.net.AccessToken;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboParameters;

public class LoginWithAccessToken<T> extends WeiboGetter<T> {

	private AccessToken accessToken;
	public LoginWithAccessToken(Weibo weibo,Class<T> type,AccessToken accessToken){
		super(weibo, type);
		this.accessToken=accessToken;
	}

	@Override
	protected String buildUrl() {
		String uri ="https://api.weibo.com/oauth2/access_token";
		return uri;
	}

	@Override
	protected String setHttpMethod() {
		// TODO Auto-generated method stub
		return "POST";
	}

	@Override
	protected WeiboParameters createRequestParams() {

		WeiboParameters params=new WeiboParameters();
		params.add("client_id", Weibo.getAppKey());
		params.add("client_secret", Weibo.getAppSecret());
		params.add("grant_type", "authorization_code");//authorization_code
		params.add("code", this.accessToken.getToken());//调用authorize获得的code值。
		params.add("redirect_uri","http://www.sina.com");//回调地址，需需与注册应用里的回调地址一致
		return params;
	}
	/**
	 * 请求参数
	必选 	类型及范围 	说明
client_id 	true 	string 	申请应用时分配的AppKey。
client_secret 	true 	string 	申请应用时分配的AppSecret。
grant_type 	true 	string 	请求的类型，可以为authorization_code、password、refresh_token。


grant_type为authorization_code时

	必选 	类型及范围 	说明
code 	true 	string 	调用authorize获得的code值。
redirect_uri 	true 	string 	回调地址，需需与注册应用里的回调地址一致。

	 */

}
