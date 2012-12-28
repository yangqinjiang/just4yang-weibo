package cn.yang.sina.weibo.ui.adapter;

import java.util.ArrayList;
import java.util.List;
import cn.yang.sina.weibo.view.NewWeiboStatusViewHolder;
import cn.yang.weibo4android.Status;
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
public class NewWeiboStatusAdapter extends BaseAdapter {

	private static final String TAG = "WeiboStatusAdapter";
	/**
	 * 上下文
	 */
	private Context context;
	/**
	 * status数据集合
	 */
	private List<Status> status;
	
	
	public List<Status> getStatus() {
		return status;
	}
	public NewWeiboStatusAdapter(Context context){
		this.context=context;
		this.status=new ArrayList<Status>();
	}
	public NewWeiboStatusAdapter(Context context,List<Status> status){
		this.context=context;
		this.status=status;
	}
	
	
	public void add(Status status){
		if(status!=null&&this.status!=null)
			this.status.add(status);
		this.notifyDataSetChanged();
	}
	
	public void setStatus(List<Status> status) {
		this.status = status;
		this.notifyDataSetChanged();
	}
	/**
	 * 往status集合尾部追加指定的元素
	 * @param otherStatus 向列表的尾部追加指定的元素
	 */
	public void addStatusToTail(List<Status> otherStatus){
		for(Status s: otherStatus){
			this.status.add(s);
		}
	}
	/**
	 * 往status集合头部部追加指定的元素
	 * @param otherStatus
	 */
	public void addStatusToHead(List<Status> otherStatus){
		Log.i(TAG, ":addStatusFromHead:");
		this.status.addAll(0, otherStatus);
	}
	/**
	 * 往status追加指定的元素
	 * @param otherStatus
	 */
	public void addStatusToHead(Status otherStatus){
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
		long id=0;
		if(0<=position&&position<status.size())
			id=status.get(position).getId();
		return id;
	}


    public View getView(int position, View tempView, ViewGroup parent) {
    	Log.i(TAG, "getView..");
    	NewWeiboStatusViewHolder vHolder=null;
		Status s= status.get(position);
		if(null==tempView){//如果tempView为空
			vHolder=new NewWeiboStatusViewHolder(context);
			tempView=vHolder.getVWeiboListViewItem();
			
			tempView.setTag(vHolder);
		}else{
			vHolder=(NewWeiboStatusViewHolder)tempView.getTag();
		}
		
		//bindData
		vHolder.bindStatusData(s, position);
		
		return tempView;
	}

}
