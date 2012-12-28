package cn.yang.sina.weibo.view.async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.TextView;
import cn.yang.sina.weibo.db.DBAgent;
import cn.yang.sina.weibo.db.IDB;


/**
 *  异步解释  表情、话题、昵称、URL地址的AsyncTask
 *  Tointerpret 是  解释 的意思
 * @author yangqinjiang
 *
 */
public class TointerpretTextAsyncaTask extends AsyncTask<TextView,Integer,Void>{

	private static final String TAG = "TointerpretTextAsyncaTask";
	/**SourceText 需要解释的源文字,
	 * 例如 像   ... [哈哈]...@JustForYang....http://xxx  等类似的文字 
	 */
	private String SourceText;
	/**tv 显示SourceText的TextView
	 * tv在解释完成后，在onPostExecute(var...)中调用其方法setText(ss)即可
	 */
	private TextView tv;
	/**position 是列表中item的位置,
	 * 只要用在setTag(position)和getTag(position)方法中
	 */
	private int position;
	public TointerpretTextAsyncaTask(String s,int position){
		Log.i(TAG, "ShowEmotionsAsyncaTask start...");
		this.SourceText=s;
		this.position=position;
		this.ss=new SpannableString(this.SourceText);
	}

	/**ss 重新生成的String*/
	private SpannableString ss;
	  /**表示话题的正则表达式*/
    private static final String TOPIC ="#.+?#";
    /**表示昵称的正则表达式*/
    private static final String NAME="@([\u4e00-\u9fa5A-Za-z0-9_]*)";
    /**表示URL的正则表达式*/
    private static final String HTTP="http://.*";
    /**表示表情的正则表达式*/
    private static final String EMOTION="\\[[\u4e00-\u9fa5a-zA-Z0-9]*\\]";
    
	/**在后台工作的方法，相当于一个线程，这里执行一个耗时的操作，例如操作数据库
	 * @return */
	@Override
	protected Void doInBackground(TextView... params) {
		Log.i(TAG, "doInBackground...");
		this.tv =params[0];
		
		if(this.tv.getTag()!=null){
			//解释表情
			showEmotion(Pattern.compile(EMOTION),this.SourceText);
			//解释话题
			hightLight(Pattern.compile(TOPIC),this.SourceText);
			//解释昵称  @XXX
			hightLight(Pattern.compile(NAME),this.SourceText);
			//解释URL地址 http://xxx
			hightLight(Pattern.compile(HTTP),this.SourceText);
		}
		return null;
			
		
	}


	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		Log.i(TAG, "onPostExecute...");
		if(this.tv.getTag().equals(this.position)){
			Log.i(TAG, "position..."+this.position);
			if(this.ss!=null){
				this.tv.setText(this.ss);
			}
		}
	}
	
    private void hightLight(Pattern pattern,String wbText){
    	List<HashMap<String,String>> lst =this.getStartAndEnd(pattern,wbText);
    	for(HashMap<String,String> map:lst){
    		this.ss.setSpan(new ForegroundColorSpan(Color.RED), Integer.parseInt(map.get("start")),
    				Integer.parseInt(map.get("end")),
    				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	}
    }

    private IDB dbAgent = DBAgent.getDBAgentInstance();
    /**
     * 解释weibo中的表情示例
     * @param pattern  正则表达式
     */
    public void showEmotion(Pattern pattern,String wbText){
    	List<HashMap<String,String>> lst = getStartAndEnd(pattern,wbText);
    	
    	//IDB dbService = new DBService(WeiboApp.context);
    	
    	for(HashMap<String,String> map:lst){
    		Bitmap bmp =dbAgent.getOneEmotionFromDB(map.get("phrase"));
    		ImageSpan imgSpan = new ImageSpan(bmp);
    		this.ss.setSpan(imgSpan, Integer.parseInt(map.get("start")),
    				Integer.parseInt(map.get("end")), 
    				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	}
    	
    }
    
    /**
     * 查找与正则表达式符合的字符串的开始位置和结束位置
     * @param pattern  正则表达式
     * @param str  字符串
     * @return  符合正则表达式的字符串的开始和结束位置的List
     */
    private List<HashMap<String,String>> getStartAndEnd(Pattern pattern,String wbText)
    {
    	List<HashMap<String,String>> lst =
    			new ArrayList<HashMap<String,String>>();
    	Matcher matcher = pattern.matcher(wbText);
    	while(matcher.find()){
    		HashMap<String,String> map = 
    				new HashMap<String, String>();
    		map.put("start",matcher.start()+"");
    		map.put("phrase",matcher.group()+"");
    		map.put("end",matcher.end()+"");
    		lst.add(map);
    	}
    		return lst;
    }
	
	
}

