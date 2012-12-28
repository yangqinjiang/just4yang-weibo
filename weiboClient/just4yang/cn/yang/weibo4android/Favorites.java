package cn.yang.weibo4android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.yang.sina.weibo.beans.nullable.Nullable;

public class Favorites implements Serializable,Nullable {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -2066687068141158216L;
	private List<Favorite> favorites;
     private String  total_number;
     
	public Favorites(List<Favorite> favorites, String total_number) {
		this.favorites = favorites;
		this.total_number = total_number;
	}
	public List<Favorite> getFavorites() {
		if(this.favorites==null)return NullFavorites.createNullFavoriteList();
		return favorites;
	}
	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}
	public String getTotal_number() {
		return total_number;
	}
	@Override
	public String toString() {
		return "Favorites [favorites=" + favorites + ", total_number="
				+ total_number + "]";
	}
	public boolean isNull() {
		if(this==null)return true;
		return false;
	}
	
	public static final Favorites NULL =new NullFavorites();
     public static class NullFavorites extends Favorites implements Nullable{

		public NullFavorites() {
			super(createNullFavoriteList(), "0");
		}
		private static List<Favorite> createNullFavoriteList(){
			ArrayList<Favorite> nullFav = new ArrayList<Favorite>(1);
			nullFav.add(Favorite.NULL);
			return nullFav;
		}
		@Override
		public String toString() {
			return "NullFavorites";
		}
		/**
		 * 
		 */
		private static final long serialVersionUID = 8990874397792101940L;
    	 @Override
    	public boolean isNull() {
    		return true;
    	}
     }
     
}
