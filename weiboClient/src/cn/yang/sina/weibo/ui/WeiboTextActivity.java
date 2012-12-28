package cn.yang.sina.weibo.ui;

import cn.yang.sina.weibo.R;
import cn.yang.sina.weibo.imagecache.SimpleImageLoader;
import cn.yang.sina.weibo.logic.WeiBoService;
import cn.yang.sina.weibo.ui.util.StringUtil;
import cn.yang.weibo4android.Status;
import cn.yang.weibo4android.User;
import cn.yang.weibo4android.WeiboContent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WeiboTextActivity extends Activity implements OnClickListener{

	private static final String TAG = "WeiboTextActivity";
	private RelativeLayout showFriendLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibo_text_activity);
		initFriendUI();
		initButtonUI();
		initTextUI();
		setWeiboText(this.getIntent());
	}
	
	private ImageButton imgFriendIcon;
	private TextView tvFriendName;
	private TextView tvFriendStates;
	
	private void initFriendUI() {
		showFriendLayout=(RelativeLayout)this.findViewById(R.id.show_friend_layoutID);
		imgFriendIcon=(ImageButton)showFriendLayout.findViewById(R.id.imgUserIcon);
		tvFriendName=(TextView)showFriendLayout.findViewById(R.id.tvUserName);
		tvFriendStates=(TextView)showFriendLayout.findViewById(R.id.tvUserStates);
		
	}
	private Button btnTransmit;
	private Button btnComment;
	private Button btnFavorite;
	private void initButtonUI(){
		btnTransmit=(Button)this.findViewById(R.id.btnTransmit);
		btnTransmit.setOnClickListener(this);
		btnComment=(Button)this.findViewById(R.id.btnComment);
		btnComment.setOnClickListener(this);
		btnFavorite=(Button)this.findViewById(R.id.btnFavorite);
		btnFavorite.setOnClickListener(this);

	}
	public void onClick(View v) {
		//TODO 
		
		switch (v.getId()) {
		case R.id.btnTransmit:
			//转发
			Intent transmitIntent = new Intent(WeiboTextActivity.this, SendWeiboActivity.class);
			Bundle transmitBundle = new Bundle();
			transmitBundle.putSerializable("transmitStatus", this.clickStatus);
			transmitIntent.putExtras(transmitBundle);
			startActivity(transmitIntent);
			break;
		case R.id.btnComment:
			//评论
			Intent commentIntent = new Intent(WeiboTextActivity.this, SendWeiboActivity.class);
			Bundle CommentBundle = new Bundle();
			CommentBundle.putSerializable("commentStatus", this.clickStatus);
			commentIntent.putExtras(CommentBundle);
			startActivity(commentIntent);
			break;
		case R.id.btnFavorite:
			//收藏
			//TODO 20120817 发送一个收藏指令到后台执行
			Intent favoriteIntent = new Intent(WeiboTextActivity.this, WeiBoService.class);
			favoriteIntent.putExtra("WeiboTextCmd", 0);
			Bundle favoriteBundle = new Bundle();
			String id=String.valueOf(this.clickStatus.getId());
			WeiboContent content = new WeiboContent(id, "");
			favoriteBundle.putSerializable("weiboContent",content);
			favoriteIntent.putExtras(favoriteBundle);
			startService(favoriteIntent);
			break;

		default:
			break;
		}
		
	}
	private TextView weiboText;
	private ImageView weiboImg;
	private TextView reWeiboText;
	private ImageView reWeiboImg;
	private LinearLayout re_layout;
	
	private void initTextUI(){
		weiboText=(TextView)this.findViewById(R.id.text_weibo_text_activity);
		weiboImg=(ImageView)this.findViewById(R.id.img_weibo_text_activity);
		reWeiboText=(TextView)this.findViewById(R.id.re_text_weibo_text_activity);
		reWeiboImg=(ImageView)this.findViewById(R.id.re_img_weibo_text_activity);
		re_layout=(LinearLayout)this.findViewById(R.id.re_layout_weibo_text_activity);
	}
	Status clickStatus;
	private void setWeiboText(Intent textIntent){
		//TODO 从textIntent中取出从HomeActivity中传递过来的值，并显示出来
		Bundle statusBundle =textIntent.getExtras();
		if(statusBundle!=null){
			
			boolean containStatus =statusBundle.containsKey("clickStatus");
			if(containStatus){
				//显示值
				this.clickStatus =(Status)statusBundle.getSerializable("clickStatus");
				
				setFriend(this.clickStatus.getUser());
				
				if(clickStatus!=null){
					weiboText.setText(this.clickStatus.getText());
					//判断是否有图片
					String picStr = this.clickStatus.getThumbnail_pic();
					setPic(picStr, weiboImg);

					//判断是否有转发微博
					Status reStatus = this.clickStatus.getRetweeted_status();
					if(reStatus!=null){
						this.re_layout.setVisibility(View.VISIBLE);
						String reWeiboText=reStatus.getText();
						this.reWeiboText.setText(reWeiboText);
						//判断是否有图片
						String rePicStr = reStatus.getThumbnail_pic();
						setPic(rePicStr, reWeiboImg);
					}else{
						this.re_layout.setVisibility(View.GONE);
					}
				}
			}
		}
	}
	private void setPic(String picUrl,ImageView displayImg){
		Log.i(TAG, "setPic.");
		boolean NotPic =StringUtil.isEmpty(picUrl);
		if(!NotPic){
			SimpleImageLoader.showImg(picUrl,displayImg);
			displayImg.setVisibility(View.VISIBLE);
		}else{
			displayImg.setVisibility(View.GONE);
		}
	}
	
	private void setFriend(User friend){
		
		if(friend!=null){
			//显示头像
			SimpleImageLoader.showImg(friend.getProfile_image_url(),
					this.imgFriendIcon);
			tvFriendName.setText(friend.getScreen_name());
			tvFriendStates.setText(""+friend.getId());
		}
	}
}
