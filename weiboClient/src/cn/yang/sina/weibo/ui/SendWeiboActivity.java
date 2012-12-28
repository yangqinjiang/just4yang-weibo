package cn.yang.sina.weibo.ui;

import cn.yang.sina.weibo.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.yang.weibo4android.Status;
import cn.yang.weibo4android.WeiboContent;
import cn.yang.sina.weibo.beans.BaseEmotions;
import cn.yang.sina.weibo.logic.WeiBoService;
import cn.yang.sina.weibo.ui.adapter.ShowEmotionsAdapter;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class SendWeiboActivity extends Activity implements OnClickListener,OnItemClickListener{

	private static final String TAG = "SendWeiboActivity";
	
	/**etWeiboContent 编辑框，写入要发表微博的内容*/
	private EditText etWeiboContent=null;
	/**btnSendWeibo 按钮，按下就可以发表微博，内容在etWeiboContent编辑框中*/
	private Button btnSendWeibo =null;
	/**tvWordNumber 显示还可以输入多少个字*/
	private TextView tvWordNumber = null;
	/**显示表情*/
	private GridView showEmotions=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_weibo_activity);
		
		initUI();
		
		registerReceiver1();
		transmit(this.getIntent());
		comment(this.getIntent());
	}
	private int sendStates=0;
	private String statusID="";
	/**
	 * 转发
	 * @param transmitIntent
	 */
	private void transmit(Intent transmitIntent){
		
		Log.i(TAG, "transmit转发");
		Bundle transmitBundle = transmitIntent.getExtras();
		boolean transmitStatus = transmitBundle.containsKey("transmitStatus");
		if(transmitStatus){
			sendStates=1;
			Status status =(Status)transmitBundle.getSerializable("transmitStatus");
			statusID=String.valueOf(status.getId());
			printStatus(status);
			Status reStatus =status.getRetweeted_status();
			if(reStatus!=null){
				Log.i(TAG, "有转发的微博");
				String proStr = "//@"+ status.getUser().getScreen_name()+":"+status.getText();
				this.etWeiboContent.setText(proStr);
			}
		}
	}
	private void comment(Intent commentIntent){
		Log.i(TAG, "comment评论");
		Bundle commentBundle = commentIntent.getExtras();
		boolean commentStatus = commentBundle.containsKey("commentStatus");
		if(commentStatus){
			sendStates=2;
			Status status =(Status)commentBundle.getSerializable("commentStatus");
			this.statusID=String.valueOf(status.getId());
			printStatus(status);
		}
	}
	private void printStatus(Status status) {
		Log.i(TAG, "Statue ID: "+status.getId());
		Log.i(TAG, "Statue Text: "+status.getText());
	}
	/**
	 * 
	 */
	private void initUI() {
		etWeiboContent = (EditText)this.findViewById(R.id.etWeiboContent);
		etWeiboContent.addTextChangedListener(new WeiboTextWatcher());
		btnSendWeibo =(Button)this.findViewById(R.id.btnSendWeibo);
		btnSendWeibo.setEnabled(false);
		btnSendWeibo.setOnClickListener(this);
		tvWordNumber = (TextView)this.findViewById(R.id.tvWordNumber);
		
		showEmotions =(GridView)this.findViewById(R.id.gvShowEmotion);
		showEmotions.setAdapter(new ShowEmotionsAdapter(this, null));
		showEmotions.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				BaseEmotions baseEmotion = (BaseEmotions)arg0.getItemAtPosition(arg2); 
				etWeiboContent.append(baseEmotion.getEmotionName());
			}
		});
	}
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btnSendWeibo:
			Log.i(TAG, "btnSendWeibo-->onClick!");
			String weiboStatus = etWeiboContent.getText().toString().trim();
			if(!weiboStatus.equals("")){
				if(this.sendStates==0)sendWeiboTask("", weiboStatus);
				if(this.sendStates==1)transmitWeiboTask(this.statusID, weiboStatus);
				if(this.sendStates==2)commentWeiboTask(this.statusID, weiboStatus);//comment
			}else{
					etWeiboContent.setHint("先来写点什么吧");
			}
			break;

		default:
			break;
		}
	}
	int i =0;
	private static final int CMD_SEND_WEIBO=1;
	private static final int CMD_TRANSMIT_WEIBO = 2;
	private static final int CMD_COMMENT_WEIBO = 3;
	private void sendWeiboTask(String id,String weiboStatus) {
		WeiboContent weiboContent = new WeiboContent(id, weiboStatus);	
		sendCommandToServcie(weiboContent,CMD_SEND_WEIBO);
	}
	
	private void transmitWeiboTask(String id,String weiboStatus){
			WeiboContent weiboContent =new WeiboContent(id,weiboStatus);
			sendCommandToServcie(weiboContent,CMD_TRANSMIT_WEIBO);
	}
	private void commentWeiboTask(String id,String weiboStatus){
		WeiboContent weiboContent =new WeiboContent(id,weiboStatus);
		sendCommandToServcie(weiboContent,CMD_COMMENT_WEIBO);
	}
	private void sendCommandToServcie(WeiboContent weiboContent,int cmd) {
		Intent weiboContentIntent = 
				new Intent(SendWeiboActivity.this, WeiBoService.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("weiboContent", weiboContent);
		weiboContentIntent.putExtras(bundle);
		weiboContentIntent.putExtra("SendWeiboCmd", cmd);
		startService(weiboContentIntent);
	}
	class WeiboTextWatcher implements TextWatcher {

		private static final String TAG = "WeiboTextWatcher";

		/**
		 * s  输入结束呈现在输入框中的信息
		 */
		public void afterTextChanged(Editable s) {
			Log.i(TAG,"[TextWatcher][afterTextChanged]输入的文字长度为: "+s.length());
			int l = s.length();
			if(l==0){
				tvWordNumber.setText("请输入一些文字吧");
				etWeiboContent.setHint("请输入一些文字吧");
				btnSendWeibo.setEnabled(false);
			}else if(l<=140){
				tvWordNumber.setText(s.length()+"");
				btnSendWeibo.setEnabled(true);
			}else {
				tvWordNumber.setText("只能输入140个字了");
				btnSendWeibo.setEnabled(false);
			}
			
			//hightLight(Pattern.compile(TOPIC),s);
				
		}

		/**表示话题的正则表达式*/
	    private static final String TOPIC ="#.+?#";
	    public void hightLight(Pattern pattern,Editable s){
	    	List<HashMap<String,String>> lst =this.getStartAndEnd(pattern,s);
		    	for(HashMap<String,String> map:lst){
	    		s.setSpan(new ForegroundColorSpan(Color.RED), Integer.parseInt(map.get("start")),
	    				Integer.parseInt(map.get("end")),
	    				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	    	}
	    }
	    
	    /**
	     * 查找与正则表达式符合的字符串的开始位置和结束位置
	     * @param pattern  正则表达式
	     * @param str 字符串
	     * @return  符合正则表达式的字符串的开始和结束位置的List
	     */
	    private List<HashMap<String,String>> getStartAndEnd(Pattern pattern,Editable s)
	    {
	    	List<HashMap<String,String>> lst =
	    			new ArrayList<HashMap<String,String>>();
	    	Matcher matcher = pattern.matcher(s.toString());
	    	while(matcher.find()){
	    		HashMap<String,String> map = 
	    				new HashMap<String, String>();
	    		
	    		map.put("start",matcher.start()+"");
	    		map.put("phrase",matcher.group()+"");
	    		System.out.println("------>"+matcher.group());
	    		map.put("end",matcher.end()+"");
	    		lst.add(map);
	    	}
	    		return lst;
	    }
		/**
		 * s  输入框中改变前的字符串信息
	     * start 输入框中改变前的字符串的起始位置
	     * count 输入框中改变前后的字符串改变数量一般为0
	     * after 输入框中改变后的字符串与起始位置的偏移量
		 */
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		/**
		 * s  输入框中改变后的字符串信息
	     * start 输入框中改变后的字符串的起始位置
	     * before 输入框中改变前的字符串的位置 默认为0
	     * count 输入框中改变后的一共输入字符串的数量
		 */
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
	}

	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(sendReceiver!=null)
			unregisterReceiver(sendReceiver);
	}
	private SendWeiboReceiver sendReceiver;
	/**
	 * 
	 */
	private void registerReceiver1() {
		Log.i(TAG, "实例化一个WeiboStatusReceiver对象，并注册在Activity上");
		//新建一个广播接收者
		if(sendReceiver==null)
			sendReceiver =new SendWeiboReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("SendWeiboReceiver");
		//给当前Activity注册一个Receiver
		registerReceiver(sendReceiver, filter);
		Log.i(TAG, "statusReceiver已经注册在Activity上了");
	}
	private class SendWeiboReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Toast.makeText(SendWeiboActivity.this, "发布成功！", Toast.LENGTH_LONG).show();
			Status s = (Status)intent.getExtras().getSerializable("mySendStatus");
			if(s!=null)
				Log.i(TAG, "status: "+s.getText());
		}
		
	}
}
