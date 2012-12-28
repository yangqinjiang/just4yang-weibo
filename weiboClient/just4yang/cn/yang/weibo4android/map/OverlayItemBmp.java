package cn.yang.weibo4android.map;

import android.graphics.Bitmap;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.OverlayItem;

public class OverlayItemBmp extends OverlayItem {

	private Bitmap bmp;
	public OverlayItemBmp(GeoPoint point, String arg1, String arg2,Bitmap bmp) {
		super(point, arg1, arg2);
		this.bmp=bmp;
	}
	public Bitmap getBmp() {
		return bmp;
	}
	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}
	

}
