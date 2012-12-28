package cn.yang.weibo4android.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class PraseJsonDataUtil {

    /**
     * TODO 20120902使用泛型来重写解释JsonData的代码
     * @param classOfT 与JsonData中的结构相对应的T.class
     * @param jsonData json字符串
     * @return 返回T对象
     */
    public static <T> T praseJsonData(Class<T> classOfT,String jsonData){
    	Gson gson = new Gson();
    	T t =null;
    	try {
			t=gson.fromJson(jsonData,classOfT);
		} catch (JsonSyntaxException e) {
			//返回一个空对象
		}
		return t;
    	
    }
}
