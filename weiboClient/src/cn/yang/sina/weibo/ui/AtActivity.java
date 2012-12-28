package cn.yang.sina.weibo.ui;

import cn.yang.sina.weibo.R;
import cn.yang.sina.weibo.ui.adapter.FriendsAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Gallery;

public class AtActivity extends WeiboActivity {

	protected static final String TAG = "AtActivity";
	/**galleryFriend 显示关注好友头像的gallery*/
	private Gallery galleryFriend;
	/**friendsAdapter galleryFriend的适配器 */
	private FriendsAdapter friendsAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.at_activity);
		Log.i(TAG, "AtActivity-->onCreate(..)");
		initUI();
	}

	private void initUI(){
		
		galleryFriend =(Gallery)this.
				findViewById(R.id.gayFriendsHead);
		friendsAdapter = new FriendsAdapter(this);
		galleryFriend.setAdapter(friendsAdapter);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		galleryFriend=null;
		friendsAdapter=null;
	}

}
