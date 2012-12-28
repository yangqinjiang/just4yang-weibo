package cn.yang.weibo4android;

import java.io.Serializable;

import cn.yang.sina.weibo.beans.nullable.Nullable;
/**已经建立空对象*/
public class Place implements Serializable,Nullable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4016185163504801176L;
	/**
	 * 例子
	 *  "poiid": "123456",
       "title": "Wall Street",
        "type": "checkin",
         "source": "微领地"
	 */
	private String poiid;
	private String title;
	private String type;
	private String source;
	
	
	public Place(String poiid, String title, String type, String source) {
		super();
		this.poiid = poiid;
		this.title = title;
		this.type = type;
		this.source = source;
	}
	public String getPoiid() {
		return poiid;
	}
	public void setPoiid(String poiid) {
		this.poiid = poiid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Override
	public String toString() {
		return "Place [poiid=" + poiid + ", title=" + title + ", type=" + type
				+ ", source=" + source + "]";
	}
	
	
	public boolean isNull() {
		if(this==null)return true;
		return false;
	}
	/**空对象*/
	public static final Place NULL=new NullPlace();
	public static class NullPlace extends Place implements Nullable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 6061626575774569846L;
		private NullPlace(){
			super("None","None","None","None");
		}
		@Override
		public boolean isNull() {
			return true;
		}
		
	}


	
}
