package cn.yang.weibo4android.util.asynTask;


import cn.yang.weibo4android.Comment;
import cn.yang.weibo4android.WeiboContent;
import cn.yang.weibo4android.util.CreateComment;
import cn.yang.weibo4android.util.WeiboGetter;

import com.weibo.net.Weibo;

import android.content.Intent;
import android.util.Log;
/**
 * 【评论一条微博】 异步任务类
 * @author yangqinjiang
 * @email yangqinjiang@gmail.com
 * @date 2012-11-7
 */
public class CommentWeiboWorker extends WeiboWorker {

	private WeiboContent weiboContent;
	/**
	 * 
	 * @param receiverActionName  广播接收者的action名称
	 * @param keyOfResult   用在BroadcastReceiver中接收返回数据的key，即从bundle中取出数据时的key
	 * @param weibo 通过Weibo.getInstance()获取
	 * @param weiboContent  要发表的微博内容
	 */
	public CommentWeiboWorker(Weibo weibo,String receiverActionName, String keyOfResult,
			 WeiboContent weiboContent) {
		super(weibo, receiverActionName, keyOfResult);
		this.weiboContent = weiboContent;
	}
	private Comment comment(Weibo weibo,WeiboContent weiboContent){
		WeiboGetter<Comment> wg= new CreateComment<Comment>(weibo, Comment.class, weiboContent);
    	return wg.getData();
	}
	@Override
	protected Intent doInBackground(Intent... params) {
		Log.i(TAG, "comment a weibo.");
		Comment com= this.comment(this.getWeibo(),this.weiboContent);
		return this.putWeiboData2Intent(com);
	}
}
