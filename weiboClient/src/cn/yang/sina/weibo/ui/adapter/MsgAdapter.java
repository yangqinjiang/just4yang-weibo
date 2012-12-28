package cn.yang.sina.weibo.ui.adapter;

import java.util.List;

import cn.yang.weibo4android.Comment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.yang.sina.weibo.R;
public class MsgAdapter extends BaseAdapter {

	private List<Comment> coms;
	private Context context;
	public MsgAdapter(Context context,List<Comment> coms) {
		super();
		this.context=context;
		this.coms=coms;
	}
	
	public MsgAdapter(Context context) {
		super();
		this.context=context;
	}
	
	public void setComs(List<Comment> coms) {
		this.coms = coms;
	}


	public int getCount() {
		// TODO Auto-generated method stub
		return coms!=null?coms.size():0;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return coms!=null?coms.get(arg0):0;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return coms!=null?coms.get(arg0).getId():0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		MsgViewWrapper wrapper =null;
		if(view==null){
			view=LayoutInflater.from(this.context).inflate(R.layout.msg_item_view,null);
			wrapper=new MsgViewWrapper(view);
			view.setTag(wrapper);
		}else{
			wrapper=(MsgViewWrapper)view.getTag();
		}
		Comment com = this.coms.get(position);
		if(!com.isNull())
			wrapper.getMsgNewestText().setText(com.getText());
		return view;
	}
	
	

	class MsgViewWrapper
	{
		private View base;
		private ImageButton msgImageButton;
		private TextView msgNewestText;
		
		public MsgViewWrapper(View base)
		{
			this.base=base;
		}

		public View getBase() {
			return base;
		}

		public void setBase(View base) {
			this.base = base;
		}

		public ImageButton getMsgImageButton() {
			if(msgImageButton==null)
				msgImageButton=(ImageButton)this.base.findViewById(R.id.msgImageButton);
			return msgImageButton;
		}

		public void setMsgImageButton(ImageButton msgImageButton) {
			this.msgImageButton = msgImageButton;
		}

		public TextView getMsgNewestText() {
			if(msgNewestText==null)
				msgNewestText=(TextView)this.base.findViewById(R.id.msgNewestText);
			return msgNewestText;
		}

		public void setMsgNewestText(TextView msgNewestText) {
			this.msgNewestText = msgNewestText;
		}
		
	}


}
