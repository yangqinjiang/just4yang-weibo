package cn.yang.sina.weibo.ui;

import cn.yang.sina.weibo.logic.WeiBoService;
import cn.yang.sina.weibo.ui.impl.IWeiBoAct;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import cn.yang.sina.weibo.R;

public class Logo extends Activity implements IWeiBoAct {

	//private static final String TAG = "Logo";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        
        ImageView img_logo = (ImageView) this.findViewById(R.id.img_logo);
        img_logo.setAnimation(animation);
        startService(new Intent(Logo.this,WeiBoService.class));
        animation.setAnimationListener(new AnimationListener() {
			
			public void onAnimationStart(Animation animation) {

			}
			
			public void onAnimationRepeat(Animation animation) {
	
			}
			public void onAnimationEnd(Animation animation) {

				startActivity(new Intent(Logo.this, Login.class));
				finish();

			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void init() {
		
	}

	public void refresh(Object... params) {
		
	}

}
