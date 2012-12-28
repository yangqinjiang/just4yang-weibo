package cn.yang.weibo4android.util;

import android.util.Log;

/**
 * 微博数据的基类
 * @author yangqinjiang
 *
 */
public abstract class WeiboData {
	public static final String TAG="WeiboData";
	public void doMyWork(WeiboData wb){
		Log.i(TAG, wb.getClass().getSimpleName()+"running...");
	}
	
}
