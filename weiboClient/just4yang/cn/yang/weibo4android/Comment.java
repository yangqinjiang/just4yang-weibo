package cn.yang.weibo4android;

import java.io.Serializable;

import cn.yang.sina.weibo.beans.nullable.Nullable;

public class Comment implements Serializable,Nullable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5311958296443243775L;
	
	
	private String created_at;                    //评论时间
	private long id;                           //评论id
	private String text;                       //评论内容
	private String source;                     //内容来源
	private User user;
	private String mid;
	private String idstr;
	private Status status;
	private Comment reply_comment=null;
	
	
	public Comment(String created_at, long id, String text, String source,
			User user, String mid, String idstr, Status status,
			Comment reply_comment) {
		this.created_at = created_at;
		this.id = id;
		this.text = text;
		this.source = source;
		this.user = user;
		this.mid = mid;
		this.idstr = idstr;
		this.status = status;
		this.reply_comment = reply_comment;
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
	public String getMid() {
		return mid;
	}
	public User getUser() {
		if(this.user==null)return User.NULL;
		return user;
	}
	public Status getStatus() {
		if(this.status==null)return Status.NULL;
		return status;
	}
	public String getIdstr() {
		return idstr;
	}
	public Comment getReply_comment() {
		if(this.reply_comment==null)return Comment.NULL;
		return reply_comment;
	}
	@Override
	public String toString() {
		return "Comment [created_at=" + created_at + ", id=" + id + ", text="
				+ text + ", source=" + source + ", user=" + user + ", mid="
				+ mid + ", idstr=" + idstr + ", status=" + status
				+ ", reply_comment=" + reply_comment + "]";
	}
	
	public static final Comment NULL=new NullComment();
	public static class NullComment extends Comment implements Nullable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 5365529715736875057L;

		private NullComment(){
			super("Wed Jun 01 00:50:25 +0800 2011", 0, "None", 
					"None", User.NULL, "None", "None", Status.NULL,
					Comment.NULL);
		}
		
		@Override
		public String toString() {
			return "NullComment";
		}

		@Override
		public boolean isNull() {
			return true;
		}
		
	}
	public boolean isNull() {
		if(this==null)return true;
		return false;
	}
	
	
	

}
