package cn.yang.weibo4android.util.asynTask;

import java.util.ArrayList;

import com.weibo.net.Weibo;

import cn.yang.sina.weibo.db.DBAgent;
import cn.yang.sina.weibo.db.IDB;

import weibo4android.Emotion;
import android.content.Intent;
import android.os.Bundle;

public class DownLoadEmotionsTaskMan extends WeiboWorker {
	
	protected DownLoadEmotionsTaskMan(Weibo weibo, String receiverActionName,
			String keyOfResult) {
		super(weibo, receiverActionName, keyOfResult);
		// TODO Auto-generated constructor stub
	}
	private ArrayList<Emotion> emotions=null;
	/**dbAgent 数据库代理类实例(单例模式)*/
	private static IDB dbAgent = DBAgent.getDBAgentInstance();
	@SuppressWarnings("unchecked")
	@Override
	protected Intent doInBackground(Intent... params) {
		Bundle bundle =params[0].getExtras();
		
		this.emotions=(ArrayList<Emotion>)bundle.get("emotions");
		for(Emotion e:emotions){
			//保存到数据库中
			dbAgent.saveEmotions2DB(e);
		}
		return null;
	}
}
