package cn.yang.weibo4android.util;

import android.util.Log;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

import cn.yang.sina.weibo.beans.app.WeiboApp;
import cn.yang.weibo4android.util.impl.WeiboDataGetter;


/**
 * 抽象基类StatutesData
 * @author yangqinjiang17
 *
 */
public abstract  class WeiboGetter<T> extends WeiboData implements WeiboDataGetter<T>{

	private Weibo weibo;
	private Class<T> classOfT;
	public WeiboGetter(Weibo weibo,Class<T> classOfT){
		this.weibo=weibo;
		this.classOfT=classOfT;
	}
	
	/**
	 * 模板方法
	 */
	@SuppressWarnings("hiding")
	public final <T> T getData() {
		//1 生成URL
		String requestUrl = buildUrl();
		//2 设置HTTP请求方式
		String httpMethod = setHttpMethod();
		//3 生成WeiboParameters参数
		WeiboParameters params = createRequestParams();
		//4 获取weibo对象
		Weibo weibo=getWeibo();
		//5 调用weibo对象的request方法，得到返回的jsonData
		String jsonData =requestWeiboServer(weibo, requestUrl, httpMethod, params);
		//6 设置jsonData解释后的类型
		Class<T> classOfT=getClassT();
		return praseJsonData(classOfT, jsonData);
	}


	/**
	 * 　微博接口的URL
	 * @return URL字符串 如  https://api.weibo.com/2/statuses/friends_timeline.json
	 */
	abstract protected String buildUrl();
	
	/**
	 *  设置HTTP请求方式
	 * @return "GET or POST"
	 */
	abstract protected String setHttpMethod();
	
	/**
	 *　生成WeiboParameters参数
	 * @return 设置好的WeiboParameters实例
	 */
	abstract protected WeiboParameters createRequestParams();
	/**
	 *  调用weibo对象的request方法，得到返回的jsonData
	 * @param weibo weibo实例，使用com.weibo.net.Weibo.getInstance()来获取
	 * @param requestUrl 请求微博接口的URL
	 * @param httpMethod HTTP请求方式
	 * @param params 微博请求参数
	 * @return 从微博服务器返回的Json原始数据
	 */
	private String requestWeiboServer(Weibo weibo,String requestUrl,
			String httpMethod,WeiboParameters params) {
		
		String jsonData="";
		try {
			jsonData = weibo.request(WeiboApp.context,
					requestUrl,
					params, 
					httpMethod,
					weibo.getAccessToken());
			//Log.d(TAG,"从weibo server返回的json数据是: "+jsonData);
		} catch (WeiboException e) {
			Log.i(TAG, "Error: "+e.getMessage());
			Log.i(TAG, "Error: "+e.getStatusCode());
		}
		return jsonData;
	}
	/**
	 * 获取Weibo对象
	 * @return
	 */
	protected Weibo getWeibo(){
		return this.weibo;
	}
	/**
	 * 解释jsonData，返回T对象
	 * @param classOfT
	 * @param jsonData
	 * @return
	 */
	@SuppressWarnings("hiding")
	private <T> T praseJsonData(Class<T> classOfT,String jsonData) {
		return PraseJsonDataUtil.praseJsonData(classOfT, jsonData);
	}
	
	/**
	 * 设置jsonData解释后的类型
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	protected <T> Class<T> getClassT(){
		return (Class<T>) this.classOfT;
	}

}
