package cn.yang.weibo4android;

import java.util.ArrayList;
import java.util.List;

import weibo4android.Geo;
import cn.yang.sina.weibo.beans.nullable.Nullable;
import cn.yang.weibo4android.User;

public class Status implements java.io.Serializable,Nullable {

	
	private static final long serialVersionUID = -8795691786466526420L;

	private String created_at;             //status创建时间
	private long id;                    //status id
	
	private String mid;                 //mid
	//private String idstr;                 //idstr
	
	private String text;                //微博内容
	private String source;              //微博来源
	private boolean favorited;        //保留字段，未弃用
	private boolean truncated;        //保留字段
	private String in_reply_to_status_id;
	private String in_reply_to_user_id;
	private String in_reply_to_screen_name;
	private String thumbnail_pic;       //微博内容中的图片的缩略地址
	private String bmiddle_pic;         //中型图片
	private String original_pic;        //原始图片
	//TODO geo 地理信息
	private Geo geo;
	
	private User user;
	private int reposts_count;
	private int comments_count;
	//annotations  注释
	private List<Annotations> annotations;
	private int mlevel;
	
	//private Visible visible;
	private Status retweeted_status;    //转发的微博内容

	public Status(String created_at, long id, String mid, String text,
			String source, boolean favorited, boolean truncated,
			String in_reply_to_status_id, String in_reply_to_user_id,
			String in_reply_to_screen_name, String thumbnail_pic,
			String bmiddle_pic, String original_pic, Geo geo, User user,
			int reposts_count, int comments_count,
			List<Annotations> annotations, int mlevel, Status retweeted_status) {
		super();
		this.created_at = created_at;
		this.id = id;
		this.mid = mid;
		this.text = text;
		this.source = source;
		this.favorited = favorited;
		this.truncated = truncated;
		this.in_reply_to_status_id = in_reply_to_status_id;
		this.in_reply_to_user_id = in_reply_to_user_id;
		this.in_reply_to_screen_name = in_reply_to_screen_name;
		this.thumbnail_pic = thumbnail_pic;
		this.bmiddle_pic = bmiddle_pic;
		this.original_pic = original_pic;
		this.geo = geo;
		this.user = user;
		this.reposts_count = reposts_count;
		this.comments_count = comments_count;
		this.annotations = annotations;
		this.mlevel = mlevel;
		this.retweeted_status = retweeted_status;
	}



	public Geo getGeo() {
		if(this.geo==null)return Geo.NULL;
		return geo;
	}



	public void setGeo(Geo geo) {
		this.geo = geo;
	}


	//使用空对象
	public List<Annotations> getAnnotations() {
		if(this.annotations==null){
			return NullStatus.createAnnList();
		}
		return annotations;
	}



	public void setAnnotations(List<Annotations> annotations) {
		this.annotations = annotations;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getCreated_at() {
		return created_at;
	}



	public long getId() {
		return id;
	}



	public String getText() {
		return text;
	}



	public String getSource() {
		return source;
	}



	public boolean isFavorited() {
		return favorited;
	}



	public boolean isTruncated() {
		return truncated;
	}



	public String getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}



	public String getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}



	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}



	public String getThumbnail_pic() {
		return thumbnail_pic;
	}



	public String getBmiddle_pic() {
		return bmiddle_pic;
	}



	public String getOriginal_pic() {
		return original_pic;
	}



	public User getUser() {
		if(this.user==null)return User.NULL;
		return user;
	}



	public int getReposts_count() {
		return reposts_count;
	}



	public int getComments_count() {
		return comments_count;
	}



	public int getMlevel() {
		return mlevel;
	}



	public Status getRetweeted_status() {
		if(this.retweeted_status==null)return Status.NULL;
		return retweeted_status;
	}




	public String getMid() {
		return mid;
	}


	

	@Override
	public String toString() {
		return "Status [created_at=" + created_at + ", id=" + id + ", mid="
				+ mid + ", text=" + text + ", source=" + source
				+ ", favorited=" + favorited + ", truncated=" + truncated
				+ ", in_reply_to_status_id=" + in_reply_to_status_id
				+ ", in_reply_to_user_id=" + in_reply_to_user_id
				+ ", in_reply_to_screen_name=" + in_reply_to_screen_name
				+ ", thumbnail_pic=" + thumbnail_pic + ", bmiddle_pic="
				+ bmiddle_pic + ", original_pic=" + original_pic + ", geo="
				+ geo + ", user=" + user + ", reposts_count=" + reposts_count
				+ ", comments_count=" + comments_count + ", annotations="
				+ annotations + ", mlevel=" + mlevel + ", retweeted_status="
				+ retweeted_status + "]";
	}




	public static class NullStatus extends Status implements Nullable{

		
		private static String create_at="Sat Nov 03 12:29:29 +0800 2012";
		private NullStatus() {
			super(create_at,0,"0","None","None",false,false,
					"0","0","None","None","None","None",Geo.NULL,
					User.NULL,0,0,createAnnList(),0,Status.NULL);
			
		}
		private static List<Annotations> createAnnList(){
			ArrayList<Annotations> an = new ArrayList<Annotations>(1);
			an.add(Annotations.NULL);
			return an;
		}
		private static final long serialVersionUID = 1046139792817548913L;

		@Override
		public boolean isNull() {
			return true;
		}
		
	}

	
	public static final Status NULL=new NullStatus();
	
	
	public boolean isNull() {
		boolean isnull=false;
		if(this==null)isnull=true;
		else isnull=false;
		return isnull;
	}
}
