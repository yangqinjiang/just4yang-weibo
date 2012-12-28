/**
 *NetUtil.java
 *2011-9-13 下午09:35:08
 *Touch Android
 *http://bbs.droidstouch.com
 */
package cn.yang.sina.weibo.ui.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * @author <a href="http://bbs.droidstouch.com">Touch Android</a>
 *
 */
public class NetUtil
{

	private static final String TAG ="NetUtil";
	
	/**
	 * 从网络中获取图片
	 * @param url 网络图片URL
	 * @return Drawable 生成后的图片
	 */
	public static Drawable getNetImage(URL url){
		Log.i(TAG,"NetUtil---->getNetImage");
		if(null ==url)
		 return null;
		
		try
		{
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			
			return Drawable.createFromStream(connection.getInputStream(), "image");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
		
	}
    /**
     * check network state   检查网络状态     
     * @param activity 传入一个Activity实例
     * @return  如果是true,说明网络状态正常，是false,说明网络状态不好
     */
	public static boolean checkInternetConnection(Activity activity) {  
          Context context = activity.getApplicationContext();  
          ConnectivityManager connectivity = 
        		  (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
           if (connectivity == null) {    
                return false;               
           } else {                     
        	   	NetworkInfo[] info = connectivity.getAllNetworkInfo();    
        	   		if (info != null) {    
                        for (int i = 0; i < info.length; i++) {   
                                 if (info[i].getState() == NetworkInfo.State.CONNECTED) {   
                                         return true;                                     
                             }		                      
                        										}                   
        	   						}           
           		}           
           return false; 
	
	}	
}
