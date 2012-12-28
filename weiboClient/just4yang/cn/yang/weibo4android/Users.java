package cn.yang.weibo4android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.yang.sina.weibo.beans.nullable.Nullable;
import cn.yang.weibo4android.User;
/**使用空对象*/
public class Users implements Serializable,Nullable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1663670245365376503L;

	private  List<User> users;
	private String next_cursor;
	private String previous_cursor;
	private String total_number;
	public Users(){
		
	}
	
	public Users(List<User> users, String next_cursor, String previous_cursor,
			String total_number) {
		super();
		this.users = users;
		this.next_cursor = next_cursor;
		this.previous_cursor = previous_cursor;
		this.total_number = total_number;
	}

	public Users(int size){
		users=new ArrayList<User>(size);
	}
	public List<User> getUsers() {
		if(this.users==null)return NullUsers.createNullUserList();
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getNext_cursor() {
		return next_cursor;
	}
	public String getPrevious_cursor() {
		return previous_cursor;
	}
	public String getTotal_number() {
		return total_number;
	}
	
	
	public void setNext_cursor(String next_cursor) {
		this.next_cursor = next_cursor;
	}

	public void setPrevious_cursor(String previous_cursor) {
		this.previous_cursor = previous_cursor;
	}

	public void setTotal_number(String total_number) {
		this.total_number = total_number;
	}

	@Override
	public String toString() {
		return "Users [users=" + users + ", next_cursor=" + next_cursor
				+ ", previous_cursor=" + previous_cursor + ", total_number="
				+ total_number + "]";
	}

	public boolean isNull() {
		if(this==null)return true;
		return false;
	}
	public static final Users NULL=new NullUsers();
	public static class NullUsers extends Users implements Nullable {

		private static final long serialVersionUID = 4024994867271906102L;

		private NullUsers(){
			super(createNullUserList(), "0", "0", "0");
		}
		private static List<User> createNullUserList(){
			ArrayList<User> nullUserList = new ArrayList<User>(1);
			nullUserList.add(User.NULL);
			return nullUserList;
		}
		
		@Override
		public String toString() {
			return "NullUsers";
		}
		@Override
		public boolean isNull() {
			return true;
		}
		
		
	}
	

}
