package cn.yang.sina.weibo.ui;

import cn.yang.sina.weibo.R;
import cn.yang.sina.weibo.view.MsgViewFlipper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MsgActivity extends WeiboActivity implements OnClickListener{

	protected static final String TAG = "MsgActivity";
	private MsgViewFlipper msgVF;
	private FrameLayout msgFrameLayout;
	private Button btnAtMe;
	private Button btnComment;
	private Button btnCollection;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "MsgActivity-->onCreate(..)");
		setContentView(R.layout.msg_activity);
		msgFrameLayout=(FrameLayout)this.findViewById(
				R.id.msgFrameLayout);
		
		msgVF = new MsgViewFlipper(this, null);
		LayoutParams params = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		msgFrameLayout.addView(msgVF.getViewFlipper(), params);
		
		btnAtMe=(Button)this.findViewById(R.id.msgBtnAtMe);
		btnAtMe.setOnClickListener(this);
		
		btnComment=(Button)this.findViewById(R.id.msgBtnComment);
		btnComment.setOnClickListener(this);
		
		btnCollection=(Button)this.findViewById(R.id.msgBtnCollection);
		btnCollection.setOnClickListener(this);
		
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.msgBtnAtMe:
			Toast.makeText(this,"At Me",Toast.LENGTH_LONG).show();
			msgVF.getmViewFlipper().setDisplayedChild(0);
			//用来【获取@我的微博】的异步任务类  获取最新的提到登录用户的微博列表，即@我的微博
			break;
		case R.id.msgBtnComment:
			Toast.makeText(this,"Comment To Me",Toast.LENGTH_LONG).show();
			msgVF.getmViewFlipper().setDisplayedChild(1);
			//获取当前登录用户所接收到的评论列表
			break;
		case R.id.msgBtnCollection:
			//获取当前登录用户的收藏列表
			msgVF.getmViewFlipper().setDisplayedChild(2);
			Toast.makeText(this,"Collection Favorites",Toast.LENGTH_LONG).show();
			break;
		}
		
	}
	

}
