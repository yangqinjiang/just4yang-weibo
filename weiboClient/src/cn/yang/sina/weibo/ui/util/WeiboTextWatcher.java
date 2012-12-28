package cn.yang.sina.weibo.ui.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

public class WeiboTextWatcher implements TextWatcher {

	private static final String TAG = "WeiboTextWatcher";

	private Context context;
	public WeiboTextWatcher(Context context){
		this.context=context;
		
	}
	/**
	 * s  输入结束呈现在输入框中的信息
	 */
	public void afterTextChanged(Editable s) {
		//Log.i(TAG, "[TextWatcher][afterTextChanged]"+s);
		Log.i(TAG,"[TextWatcher][afterTextChanged]输入的文字长度为: "+s.length());
		if(s.length()<=140);
			//context.tvWordNumber;
	}

	/**
	 * s  输入框中改变前的字符串信息
     * start 输入框中改变前的字符串的起始位置
     * count 输入框中改变前后的字符串改变数量一般为0
     * after 输入框中改变后的字符串与起始位置的偏移量
	 */
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		//Log.i(TAG, "[TextWatcher][beforeTextChanged][s]"+s+"[start]"+
		//		start+"[count]"+count+"[after]"+after);
	}

	/**
	 * s  输入框中改变后的字符串信息
     * start 输入框中改变后的字符串的起始位置
     * before 输入框中改变前的字符串的位置 默认为0
     * count 输入框中改变后的一共输入字符串的数量
	 */
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		//Log.i(TAG, "[TextWatcher][onTextChanged][s]"+"s+[start]"+
		//		start+"[before]"+before+"[count]"+count);
		Log.i(TAG,"[TextWatcher][onTextChanged]输入的文字长度为: "+s.length());
	}

}
