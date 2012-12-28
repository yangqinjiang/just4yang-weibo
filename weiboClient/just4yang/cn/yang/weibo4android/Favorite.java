package cn.yang.weibo4android;

import java.io.Serializable;

import cn.yang.sina.weibo.beans.nullable.Nullable;

public class Favorite implements Serializable,Nullable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -6441190338661143589L;
	private Status status;
	 private String[] tags;
	 private String favorited_time;
	 
	 
	public Favorite(Status status, String[] tags, String favorited_time) {
		this.status = status;
		this.tags = tags;
		this.favorited_time = favorited_time;
	}
	public Status getStatus() {
		if(this.status==null)return Status.NULL;
		return status;
	}
	public String[] getTags() {
		return tags;
	}
	public String getFavorited_time() {
		return favorited_time;
	}
	@Override
	public String toString() {
		return "Favorite [status=" + status + ", tags=" + tags
				+ ", favorited_time=" + favorited_time + "]";
	}
	public boolean isNull() {
		if(this==null)return true;
		return false;
	}
	
	public static final Favorite NULL=new NullFavorite();
	public static class NullFavorite extends Favorite implements Nullable{
		private static final long serialVersionUID = 7107098663559549271L;
		private NullFavorite(){
			super(Status.NULL, new String[]{"None"},"None");
		}
		@Override
		public String toString() {
			return "NullFavorite";
		}
		@Override
		public boolean isNull() {
			return true;
		}
	}
	
}
