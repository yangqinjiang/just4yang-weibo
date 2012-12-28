package cn.yang.sina.weibo.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.yang.sina.weibo.beans.nullable.Nullable;
import cn.yang.weibo4android.Status;

public class Statuses implements Serializable,Nullable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3377991775681370689L;
	/**
	 * 微博列表
	 */
	private  List<Status> statuses;
	/**
	 * 
	 */
	private boolean hasvisible;
	/**
	 * 
	 */
	private String previous_cursor;
	/**
	 * 
	 */
	private String next_cursor;
	/**
	 * 
	 */
	private String total_number;
	
	
	public Statuses(List<Status> statuses, boolean hasvisible,
			String previous_cursor, String next_cursor, String total_number) {
		super();
		this.statuses = statuses;
		this.hasvisible = hasvisible;
		this.previous_cursor = previous_cursor;
		this.next_cursor = next_cursor;
		this.total_number = total_number;
	}
	public List<Status> getStatuses() {
		if(this.statuses==null){
			return NullStatuses.createNullStatusList();
		}
		return statuses;
	}
	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
		
	}
	public boolean isHasvisible() {
		return hasvisible;
	}
	public void setHasvisible(boolean hasvisible) {
		this.hasvisible = hasvisible;
	}
	public String getPrevious_cursor() {
		return previous_cursor;
	}
	public void setPrevious_cursor(String previous_cursor) {
		this.previous_cursor = previous_cursor;
	}
	public String getNext_cursor() {
		return next_cursor;
	}
	public void setNext_cursor(String next_cursor) {
		this.next_cursor = next_cursor;
	}
	public String getTotal_number() {
		return total_number;
	}
	public void setTotal_number(String total_number) {
		this.total_number = total_number;
	}
	@Override
	public String toString() {
		return "Statuses [statuses=" + statuses.toString() + ", hasvisible=" + hasvisible
				+ ", previous_cursor=" + previous_cursor + ", next_cursor="
				+ next_cursor + ", total_number=" + total_number + "]";
	}
	public boolean isNull() {
		if(this==null)return true;
		return false;
	}
	/**Statuses 的空对象*/
	public static final Statuses NULL =new NullStatuses();
	
	//TODO 建立一个空对象
	public static class NullStatuses extends Statuses implements Nullable{

		
		private NullStatuses() {
			super(createNullStatusList(), false, "0","0","0");
		
		}
		private static ArrayList<Status> createNullStatusList(){
			ArrayList<Status> nullStatusList =new ArrayList<Status>(1);
			nullStatusList.add(Status.NULL);
			return nullStatusList;
		}
		@Override
		public String toString() {
			return "NullStatuses";
		}
		private static final long serialVersionUID = 5799597668006263389L;
		@Override
		public boolean isNull() {
			return true;
		}
		
		
	}
	
	
	
}
