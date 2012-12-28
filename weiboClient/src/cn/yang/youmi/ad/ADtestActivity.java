package cn.yang.youmi.ad;

import net.youmi.android.AdManager;
import net.youmi.android.AdView;
import net.youmi.android.AdViewListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;


public class ADtestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AdManager.init(ADtestActivity.this,
        				"f489ef742a593077",
        				"95aef74a5a719180",
        				10, true);
       
       //setContentView(R.layout.advertisement);
     //初始化广告视图
		AdView adView = new AdView(this);

		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		//设置广告出现的位置(悬浮于屏幕右下角)		 
		params.gravity=Gravity.BOTTOM|Gravity.RIGHT; 
		adView.setAdViewListener(new AdViewListener() {
			
			public void onConnectFailed(AdView arg0) {
				System.out.println("****onConnectFailed***");
			}
			
			public void onAdViewSwitchedAd(AdView arg0) {
				// TODO Auto-generated method stub
				System.out.println("****onAdViewSwitchedAd***");
			}
		});
		
		//将广告视图加入Activity中
		addContentView(adView, params);
    }
}