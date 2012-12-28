package cn.yang.sina.weibo.ui.adapter;

import java.util.ArrayList;
import java.util.List;
import cn.yang.sina.weibo.beans.Task;
import cn.yang.sina.weibo.view.WeiboFavoritesViewHolder;
import cn.yang.weibo4android.Favorite;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 微博内容Adapter
 * @author yangqinjiang
 *
 */
public class WeiboFavoritesAdapter extends BaseAdapter {

	private static final String TAG = "WeiboStatusAdapter";
	/**
	 * 上下文
	 */
	private Context context;
	/**
	 * status数据集合
	 */
	private List<Favorite> status;
	
	public WeiboFavoritesAdapter(Context context){
		this.context=context;
		this.status=new ArrayList<Favorite>();
	}
	public WeiboFavoritesAdapter(Context context,List<Favorite> status){
		this.context=context;
		this.status=status;
	}
	
	
	public void add(Favorite status){
		if(status!=null&&this.status!=null)
			this.status.add(status);
		this.notifyDataSetChanged();
	}
	
	public void setStatus(List<Favorite> status) {
		this.status = status;
	}
	/**
	 * 往status集合尾部追加指定的元素
	 * @param otherStatus 向列表的尾部追加指定的元素
	 */
	public void addStatusToTail(List<Favorite> otherStatus){
		for(Favorite s: otherStatus){
			this.status.add(s);
		}
	}
	/**
	 * 往status集合头部部追加指定的元素
	 * @param otherStatus
	 */
	public void addStatusToHead(List<Favorite> otherStatus){
		Log.i(TAG, ":addStatusFromHead:");
		this.status.addAll(0, otherStatus);
	}
	/**
	 * 往status追加指定的元素
	 * @param otherStatus
	 */
	public void addStatusToHead(Favorite otherStatus){
		Log.i(TAG, ":addStatusFromHead:");
		this.status.add(0, otherStatus);
		this.notifyDataSetChanged();
	}
	/**
	 * 返回status集合里数据的个数
	 */
	public int getCount() {
		return status!=null?status.size():0;
	}

	public Object getItem(int position) {
		Object object=null;
		//如果position在status的0~status.size()个数范围内
		if(0<=position&&position<status.size())
			object=status.get(position);
		return object;
	}

	public long getItemId(int position) {
		
		return 0;
	}


    public View getView(int position, View tempView, ViewGroup parent) {
    	Log.i(TAG, "getView..");
    	WeiboFavoritesViewHolder vHolder=null;
    	Favorite fav= status.get(position);
		if(Task.DEBUG)Log.i(TAG, "第  "+position+" 项");
		if(null==tempView){//如果tempView为空
			vHolder=new WeiboFavoritesViewHolder(context);
			tempView=vHolder.getVWeiboListViewItem();
			
			tempView.setTag(vHolder);
		}else{
			vHolder=(WeiboFavoritesViewHolder)tempView.getTag();
		}
		
		//bindData
		vHolder.bindStatusData(fav, position);
		
		return tempView;
	}

}
