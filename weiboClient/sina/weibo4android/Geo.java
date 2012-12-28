package weibo4android;

import java.io.Serializable;
import java.util.Arrays;

import cn.yang.sina.weibo.beans.nullable.Nullable;

public class Geo implements Serializable,Nullable{
/**
	 * 
	 */
	private static final long serialVersionUID = 4541190969985625659L;
	/*
	 "geo": {
    "type": "point",
    "coordinates": [0.0, 0.0]
},*/
	private String type;
	private double[] coordinates;
	
	
	public Geo(String type, double[] coordinates) {
		super();
		this.type = type;
		this.coordinates = coordinates;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double[] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
	@Override
	public String toString() {
		return "Geo [type=" + type + ", coordinates="
				+ Arrays.toString(coordinates) + "]";
	}
	
	
	public static final Geo NULL=new NullGeo();
	public static class NullGeo extends Geo implements Nullable{


		private static double[] nullCoordinates={21.5763298,111.8469051};

		private NullGeo() {
			super("Point", nullCoordinates);
		}

		private static final long serialVersionUID = 3006288372623583777L;

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
