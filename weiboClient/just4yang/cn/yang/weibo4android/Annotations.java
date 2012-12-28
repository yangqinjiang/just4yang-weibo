package cn.yang.weibo4android;

import java.io.Serializable;

import cn.yang.sina.weibo.beans.nullable.Nullable;

public class Annotations implements Serializable,Nullable {

	/*
	 * "place": {
                        "poiid": "123456",
                        "title": "Wall Street",
                        "type": "checkin",
                        "source": "微领地"
                    }
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7060475717979472412L;
	private Place place;
	

	public Annotations(Place place) {
		super();
		this.place = place;
	}


	public Place getPlace() {
		if(this.place==null)return Place.NULL;
		return place;
	}


	public void setPlace(Place place) {
		this.place = place;
	}


	@Override
	public String toString() {
		return "Annotations [place=" + place + "]";
	}
	public static final Annotations NULL = new NullAnnotations();
	/**空对象*/
	public static class NullAnnotations extends Annotations implements Nullable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 4206753968087982144L;
		private NullAnnotations(){
			super(Place.NULL);
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
