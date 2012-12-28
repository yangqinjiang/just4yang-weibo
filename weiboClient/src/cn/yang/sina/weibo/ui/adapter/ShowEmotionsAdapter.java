package cn.yang.sina.weibo.ui.adapter;

import java.util.List;

import cn.yang.sina.weibo.beans.BaseEmotions;
import cn.yang.sina.weibo.db.DBAgent;
import cn.yang.sina.weibo.db.DBService;
import cn.yang.sina.weibo.db.IDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import cn.yang.sina.weibo.R;
public class ShowEmotionsAdapter extends BaseAdapter {

	private Context context;
	private IDB dbAgent ;
	private List<BaseEmotions> allEmotions;
	public ShowEmotionsAdapter(Context context,List<BaseEmotions> emotions){
		this.context=context;
		dbAgent = DBAgent.getDBAgentInstance();
		allEmotions =dbAgent.getEmotionsFromDB();
	}
	public int getCount() {
		return allEmotions.size();
	}

	public Object getItem(int position) {
		System.out.println("getItem...");
		return allEmotions==null?null:allEmotions.get(position);
	}

	public long getItemId(int position) {
		System.out.println("getItemId..."+position);
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		ImageView imgShowEmotions = null;
		if(v==null){
			v = LayoutInflater.from(context).inflate(R.layout.show_emotions_items,null);
			imgShowEmotions = (ImageView)v.findViewById(R.id.imgShowEmotion);
			imgShowEmotions.setImageBitmap(allEmotions.get(position).getBmpEmotion());
		}
		return v;
	}

}
