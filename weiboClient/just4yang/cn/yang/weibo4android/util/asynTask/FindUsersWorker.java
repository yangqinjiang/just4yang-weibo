package cn.yang.weibo4android.util.asynTask;

import com.weibo.net.Weibo;

import android.content.Intent;

public class FindUsersWorker extends WeiboWorker {

	protected FindUsersWorker(Weibo weibo, String receiverActionName,
			String keyOfResult) {
		super(weibo, receiverActionName, keyOfResult);
	}

	@Override
	protected Intent doInBackground(Intent... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
