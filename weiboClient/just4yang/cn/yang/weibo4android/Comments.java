package cn.yang.weibo4android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.yang.sina.weibo.beans.nullable.Nullable;

public class Comments implements Serializable,Nullable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8255076018507761296L;
	private List<Comment> comments;
	private boolean hasvisible;
	private String previous_cursor;
	private String next_cursor;
	private String total_number;
	
	
	public Comments(List<Comment> comments, boolean hasvisible,
			String previous_cursor, String next_cursor, String total_number) {
		this.comments = comments;
		this.hasvisible = hasvisible;
		this.previous_cursor = previous_cursor;
		this.next_cursor = next_cursor;
		this.total_number = total_number;
	}

	public List<Comment> getComments() {
		if(this.comments==null)return NullComments.createCommentList();
		return comments;
	}
	
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public boolean isHasvisible() {
		return hasvisible;
	}

	public String getPrevious_cursor() {
		return previous_cursor;
	}
	public String getNext_cursor() {
		return next_cursor;
	}
	public String getTotal_number() {
		return total_number;
	}

	@Override
	public String toString() {
		return "Comments [comments=" + comments + ", hasvisible=" + hasvisible
				+ ", previous_cursor=" + previous_cursor + ", next_cursor="
				+ next_cursor + ", total_number=" + total_number + "]";
	}
	
	public static final Comments NULL=new NullComments();
 	public static class NullComments extends Comments implements Nullable{

		public NullComments() {
			super(createCommentList(), false, 
					"0", "0", "0");
		}
		private static List<Comment> createCommentList(){
			ArrayList<Comment> c = new ArrayList<Comment>(1);
			c.add(Comment.NULL);
			return c;
		}
		private static final long serialVersionUID = -5035344475142407532L;
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
