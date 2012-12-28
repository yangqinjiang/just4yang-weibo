package cn.yang.sina.weibo.view;

import cn.yang.weibo4android.Status;
import cn.yang.weibo4android.User;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.yang.sina.weibo.imagecache.SimpleImageLoader;
import cn.yang.sina.weibo.ui.util.StringUtil;
import cn.yang.sina.weibo.view.click.OnUserIconClick;
import cn.yang.sina.weibo.R;

	/**
	 * 每一条微博内容的视图
	 * @author yangqinjiang
	 *
	 */
	public class NewWeiboStatusViewHolder{
		private static final String TAG = "NewWeiboStatusViewHolder";
		/**
		 * 关注用户或当前登录用户头像
		 */
		 
		public ImageButton ibUserIcon;
		/**
		 *  用户名称:关注用户或当前登录用户的名称
		 */
		public TextView tvUserName;
		/**
		 * 是否有新浪认证
		 */
		public ImageView ivIsV;
		/**
		 * 创建时间:关注用户或当前登录用户的微博发表时间
		 */
		public TextView tvCreatedTime;
		/**
		 * 关注用户或当前登录用户的微博发表内容
		 */
		public TextView tvWeiboContent;
		/**
		 * 关注用户或当前登录用户的微博发表内容附带的图片 缩略图
		 */
		public ImageView ivThumbnailPic;
		
		/**
		 * 视图 View
		 */
		View vWeiboListViewItem;
		
		public LinearLayout llRetweeted;
		private Context context;
		public NewWeiboStatusViewHolder(Context context){
			Log.i(TAG, "new a holder...");
			this.context=context;
			//this.vWeiboListViewItem=newView(context);
		}

		//getter
		/**
		 * 取得user_icon_img
		 * @return
		 */
		public ImageButton getIbUserIcon() {
			if(ibUserIcon==null){
				ibUserIcon=
						(ImageButton)vWeiboListViewItem.findViewById(R.id.new_weibo_listview_item_user_icon_img);
			}
			return ibUserIcon;
		}
		public TextView getTvUserName() {
			if(tvUserName==null)
			{
				tvUserName=
						(TextView)vWeiboListViewItem.findViewById(R.id.new_weibo_listview_item_user_name_txt);
			}
			return tvUserName;
		}
		public ImageView getIvIsV() {
			if(ivIsV==null){
				ivIsV=
						(ImageView)vWeiboListViewItem.findViewById(R.id.new_weibo_listview_item_v_img);
			}
			return ivIsV;
		}
		public TextView getTvCreatedTime() {
			if(tvCreatedTime==null){
				tvCreatedTime=
						(TextView)vWeiboListViewItem.findViewById(R.id.new_weibo_listview_item_created_at_txt);
			}
			return tvCreatedTime;
		}
		public TextView getTvWeiboContent() {
			if(tvWeiboContent==null){
				tvWeiboContent=
						(TextView)vWeiboListViewItem.findViewById(R.id.new_weibo_listview_item_text_txt);
			}
			return tvWeiboContent;
		}
		
		public ImageView getIvThumbnailPic() {
			if(ivThumbnailPic==null){
				ivThumbnailPic=
						(ImageView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_thumbnail_pic_img);
			}
			return ivThumbnailPic;
		}
		
	
		public View getVWeiboListViewItem() {
			if(vWeiboListViewItem==null)
				vWeiboListViewItem=newView(this.context);
			return vWeiboListViewItem;
		}
		public LinearLayout getLlRetweeted() {
			if(llRetweeted==null){
				llRetweeted=
						(LinearLayout)vWeiboListViewItem.findViewById(R.id.weibo_listview_retweeted_layout);
			}
			return llRetweeted;
		}
		
		/**
		 * 新建一个微博内容视图View
		 * @param context
		 * @return
		 */
		private View newView(Context context){
			if(vWeiboListViewItem==null)
				this.vWeiboListViewItem = LayoutInflater.from(context).inflate(R.layout.new_simple_weibo_listview_item_template, null);
			return vWeiboListViewItem;
		}
	
	

		/**
		 * 邦定微博内容的数据
		 * @param status 微博内容
		 */
		public void bindStatusData(Status status,int position){
			Log.i(TAG, "bindStatusData...");
			if(status!=null){
				User user =status.getUser();
				if(user!=null){
					//用户头像
					SimpleImageLoader.showImg(
							user.getProfile_image_url().toString(),
							this.getIbUserIcon());
				
					this.getIbUserIcon()
							.setOnClickListener(new OnUserIconClick(user));
				
			//用户名称
			this.getTvUserName().setText(user.getName());
			//创建时间
			this.getTvCreatedTime().setText(status.getCreated_at()+"");
			//新浪认证
			if(user.isVerified())
			{
				this.getIvIsV().setVisibility(View.VISIBLE);
			}else{
				this.getIvIsV().setVisibility(View.GONE);
			}
			//微博内容
			String wbText =status.getText();
			this.getTvWeiboContent().setText(wbText);
	    	//使用异步加载程序来 加载表情
	    	this.getTvWeiboContent().setTag(position);
			
			//判断原微博是否有缩略图
			if(!StringUtil.isEmpty(status.getThumbnail_pic())){
					//SimpleImageLoader.showImg(status.getThumbnail_pic().toString(),this.getIvThumbnailPic());
					//this.getIvThumbnailPic().setVisibility(View.VISIBLE);
				Log.i(TAG, "有缩略图");
				//Drawable rightDrawable = this.context.getResources().getDrawable(R.drawable.redirect_icon);
				//this.getTvWeiboContent().setCompoundDrawables(null,null,rightDrawable,null);
			}else{
				Log.i(TAG, "没有缩略图");
				//this.getTvWeiboContent().setCompoundDrawables(null,null,null,null);	
			}
			

		}
			}
		}
	}


