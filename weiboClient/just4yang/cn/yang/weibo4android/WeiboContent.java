package cn.yang.weibo4android;

import java.io.Serializable;

import cn.yang.sina.weibo.beans.nullable.Nullable;

/**
 * 发送的微博内容
 * @author Administrator
 *
 */
public class WeiboContent implements Serializable,Nullable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7466291010187196383L;
	private String id;
	private String weiboStatus;
	
	public WeiboContent(){}
	public WeiboContent(String id){
		this.id=id;
	}
	public WeiboContent(String id, String weiboStatus) {
		this.id = id;
		this.weiboStatus = weiboStatus;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setWeiboStatus(String weiboStatus) {
		this.weiboStatus = weiboStatus;
	}
	public String getId() {
		return id;
	}
	public String getWeiboStatus() {
		return weiboStatus;
	}
	public boolean isNull() {
		return false;
	}
	public static final WeiboContent NULL=new NullWeiboContent();
	public static class NullWeiboContent extends WeiboContent implements Nullable{

		private static final long serialVersionUID = -8570146920110823857L;

		private NullWeiboContent() {
			super("3509698298584226", "None");
		}
		@Override
		public String toString() {
			return "NullWeiboContent";
		}
		@Override
		public boolean isNull() {
			return true;
		}
		
	}
	
}
