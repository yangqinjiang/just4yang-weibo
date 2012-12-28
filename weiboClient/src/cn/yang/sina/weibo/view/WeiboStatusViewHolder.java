package cn.yang.sina.weibo.view;

import cn.yang.weibo4android.Status;
import cn.yang.weibo4android.User;
import android.content.Context;
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
	public class WeiboStatusViewHolder{
		private static final String TAG = "WeiboStatusViewHolder";
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
		 * 关注用户或当前登录用户的转发的微博     发表内容附带的图片
		 */
		public TextView tvRetweetedStatus;
		/**
		 * 关注用户或当前登录用户的转发的微博     发表内容附带的图片  缩略图 
		 */
		public ImageView ivRetweetedStatusThumbnailPic;
		/**
		 * 微博来源
		 */
		public TextView tvWeiboSource;
		/**
		 * 微博转发次数 
		 */
		public TextView tvRepostsCount;
		/**
		 * 微博收藏次数
		 */
		public TextView tvCollectionCount;
		/**
		 * 微博评论次数
		 */
		public TextView tvCommentsCount;
		/**
		 * 视图 View
		 */
		View vWeiboListViewItem;
		public LinearLayout llRetweeted;
		private Context context;
		public WeiboStatusViewHolder(Context context){
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
						(ImageButton)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_user_icon_img);
			}
			return ibUserIcon;
		}
		public TextView getTvUserName() {
			if(tvUserName==null)
			{
				tvUserName=
						(TextView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_user_name_txt);
			}
			return tvUserName;
		}
		public ImageView getIvIsV() {
			if(ivIsV==null){
				ivIsV=
						(ImageView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_v_img);
			}
			return ivIsV;
		}
		public TextView getTvCreatedTime() {
			if(tvCreatedTime==null){
				tvCreatedTime=
						(TextView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_created_at_txt);
			}
			return tvCreatedTime;
		}
		public TextView getTvWeiboContent() {
			if(tvWeiboContent==null){
				tvWeiboContent=
						(TextView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_text_txt);
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
		
		public TextView getTvRetweetedStatus() {
			if(tvRetweetedStatus==null){
				tvRetweetedStatus=
						(TextView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_retweeted_status_txt);
			}
			
			return tvRetweetedStatus;
		}
		public ImageView getIvRetweetedStatusThumbnailPic() {
			if(ivRetweetedStatusThumbnailPic==null){
				ivRetweetedStatusThumbnailPic=
						(ImageView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_retweeted_status_thumbnail_pic_img);
			}
			return ivRetweetedStatusThumbnailPic;
		}
		public TextView getTvWeiboSource() {
			if(tvWeiboSource==null){
				tvWeiboSource=
						(TextView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_source_txt);
			}
			return tvWeiboSource;
		}
		public TextView getTvRepostsCount() {
			if(tvRepostsCount==null){
				tvRepostsCount=
						(TextView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_reposts_count_txt);
			}
			return tvRepostsCount;
		}
		public TextView getTvCollectionCount() {
			if(tvCollectionCount==null){
				tvCollectionCount=
						(TextView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_collection_count_txt);
			}
			return tvCollectionCount;
		}
		public TextView getTvCommentsCount() {
			if(tvCommentsCount==null){
				tvCommentsCount=
						(TextView)vWeiboListViewItem.findViewById(R.id.weibo_listview_item_comments_count_txt);
			}
			return tvCommentsCount;
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
				this.vWeiboListViewItem = LayoutInflater.from(context).inflate(R.layout.weibo_listview_item_template, null);
			return vWeiboListViewItem;
		}
	
	

		/**
		 * 邦定微博内容的数据
		 * @param status 微博内容
		 */
		public void bindStatusData(Status status,int position){
			Log.i(TAG, "bindStatusData...");
			if(!status.isNull()){
				User user =status.getUser();
				if(!user.isNull()){
					
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
				//if(Task.DEBUG)Log.i(TAG, "第  "+position+" 项"+"是新浪认证用户");
				this.getIvIsV().setVisibility(View.VISIBLE);
			}else{
				this.getIvIsV().setVisibility(View.GONE);
			}
			//微博内容
			String content =status.getText();
			this.getTvWeiboContent().setText(content);
	    	//使用异步加载程序来 加载表情
	    	this.getTvWeiboContent().setTag(position);
	    	//new TointerpretTextAsyncaTask(wbText,position).execute(this.getTvWeiboContent());
			
			//判断原微博是否有缩略图
			if(!StringUtil.isEmpty(status.getThumbnail_pic())){
				//if(Task.DEBUG)Log.i(TAG, "第  "+position+" 项"+"有图片");
					SimpleImageLoader.showImg(status.getThumbnail_pic().toString(),this.getIvThumbnailPic());
					//vHolder.getWeibo_listview_item_thumbnail_pic_img().setImageResource(R.drawable.home_icon);
					this.getIvThumbnailPic().setVisibility(View.VISIBLE);
			}else{
				this.getIvThumbnailPic().setVisibility(View.GONE);
			}
			Log.i(TAG, "判断是否有转发的weibo");
			//判断是否有转发的weibo
			Status reStatus =status.getRetweeted_status();
			
			if(!reStatus.isNull()){
				User reUser =reStatus.getUser();
				if(!reUser.isNull()){
				
					this.getLlRetweeted().setVisibility(View.VISIBLE);
					//if(Task.DEBUG)Log.i(TAG, "第  "+position+" 项"+"有转发weibo");
					String userName="";
					if(reUser.getName()!=null)
						userName=reUser.getName();
					Log.i(TAG, "userName"+userName);
					String text ="";
					if(reStatus.getText() != null)
						text=reStatus.getText();
					Log.i(TAG, "text"+text);
					this.getTvRetweetedStatus().
						setText("@"+userName+":"+text);
				
				if(!StringUtil.isEmpty(reStatus.getThumbnail_pic())){
					//if(Task.DEBUG)Log.i(TAG, "第  "+position+" 项"+"转发weibo有图片");
					SimpleImageLoader.showImg(reStatus.getThumbnail_pic().toString(),
							this.getIvRetweetedStatusThumbnailPic());
					this.getIvRetweetedStatusThumbnailPic().setVisibility(View.VISIBLE);
				}else{
					this.getIvRetweetedStatusThumbnailPic().setVisibility(View.GONE);
				}
			}
			}else{
				this.getLlRetweeted().setVisibility(View.GONE);
				this.getTvRetweetedStatus().setText("");
			}
			
		}
			}
		}
	}


