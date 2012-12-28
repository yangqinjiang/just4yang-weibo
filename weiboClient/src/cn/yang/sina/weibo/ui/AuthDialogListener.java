package cn.yang.sina.weibo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.yang.weibo4android.util.asynTask.OAuth2Worker;

import com.weibo.net.DialogError;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboDialogListener;
import com.weibo.net.WeiboException;


public class AuthDialogListener implements WeiboDialogListener {

	/**
	 * 当完成认证后，执行onComplete方法
	 */
	
	public void onComplete(Bundle values) {
		//获取uid
		String uid = values.getString("uid");
		//获取token
		String token = values.getString("access_token");
		//获取expires_int
		String expires_in = values.getString("expires_in");
		
		Log.i("AuthDialogListener","values.toString(): "+values.toString());
		
		Log.i("AuthDialogListener","udi: "+uid+ " access_token: "+token+" expires_in: "+expires_in);
		Log.i("AuthDialogListener", "发送到service中，保存access_token和expires_in到数据库中");
		//TODO 发送到service中，保存access_token和expires_in到数据库中
		new OAuth2Worker(Weibo.getInstance(), "", "").execute(new Intent().putExtras(values));
	}

	/**
	 * 当认证出错时，显示一个Toast及出错信息
	 */
	
	public void onError(DialogError e) {
		Log.i("OAuth2 Test","DialogError : "+e);
	}

	/**
	 * 当认证被取消时，显示一个Toast及取消信息
	 */
	
	public void onCancel() {
		Log.i("OAuth2 Test","Cancel.");
	}

	/**
	 * 当认证有异常时，显示一个Toast及异常信息
	 */
	
	public void onWeiboException(WeiboException e) {
		Log.i("OAuth2 Test","WeiboException : "+e);
	}

}
