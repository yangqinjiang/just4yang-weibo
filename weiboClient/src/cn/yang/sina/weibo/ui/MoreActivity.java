package cn.yang.sina.weibo.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import weibo4android.Geo;
import weibo4android.Paging;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.yang.sina.weibo.beans.Statuses;
import cn.yang.sina.weibo.ui.util.NetUtil;
import cn.yang.weibo4android.Status;
import cn.yang.weibo4android.map.OverItemT;
import cn.yang.weibo4android.map.OverlayItemBmp;
import cn.yang.weibo4android.util.FriendTimelinePlace;
import cn.yang.weibo4android.util.WeiboGetter;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.weibo.net.Weibo;
import cn.yang.sina.weibo.R;

public class MoreActivity extends MapActivity {

	protected static final String TAG = "MoreActivity";
	BMapManager mBMapMan = null;
	private Button btnWhereAmI;
	private EditText lon;
	private EditText lat;
	private MapView mMapView=null;
	private OverItemT overitem = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lbs);
		btnWhereAmI=(Button)this.findViewById(R.id.btnWhereAmI);
		lon=(EditText)this.findViewById(R.id.lon);
		lat=(EditText)this.findViewById(R.id.lat);
	    mBMapMan = new BMapManager(getApplication());
	    mBMapMan.init("01D8574E8062B22E5298AC13F469568BA191BC0C", null);
	    super.initMapActivity(mBMapMan);
	     
	    mMapView = (MapView) findViewById(R.id.bmapsView);
	    mMapView.setBuiltInZoomControls(true); //设置启用内置的缩放控件
	     
	    final MapController mMapController = mMapView.getController(); // 得到mMapView的控制权,可以用它控制和驱动平移和缩放
	    //110.929806,21.681767
	    GeoPoint point = new GeoPoint((int) (21.681767* 1E6),
	    (int) ( 110.929806 * 1E6)); //用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
	    //mMapController.setCenter(point); //设置地图中心点
	    mMapController.setZoom(12); //设置地图zoom级别
		Log.i(TAG, "MoreActivity-->onCreate(..)");
		btnWhereAmI.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				new AsyncTask<Void, Void, List<OverlayItemBmp>>(){

					@Override
					protected List<OverlayItemBmp> doInBackground(Void... params) {
						return getFriendPlace();
					}
					@Override
					protected void onPostExecute(List<OverlayItemBmp> result) {
						// TODO Auto-generated method stub
						super.onPostExecute(result);
						fun(result);
					}
					
				}.execute();
			}
		});
	
	
		
	}


	/**
	 * 
	 */
	private void fun(List<OverlayItemBmp> geoList) {
		if(geoList!=null&&geoList.size()>0){
	        // 添加ItemizedOverlay
			Drawable marker = getResources().getDrawable(R.drawable.iconmarka);  //得到需要标在地图上的资源
			//List<OverlayItemBmp> geoList=getFriendPlace();
	
			marker.setBounds(0, 0,marker.getIntrinsicWidth(),marker
					.getIntrinsicHeight());   //为maker定义位置和边界
			//TODO 20120921 地图上的分条目覆盖物：ItemizedOverlay 
			overitem = new OverItemT(marker, this,geoList,null);
			mMapView.getOverlays().add(overitem); //添加ItemizedOverlay实例到mMapView
		}
	}


	
	private Statuses getFriendPlaceTimeLine(){
		WeiboGetter<Statuses> wg =new FriendTimelinePlace<Statuses>(Weibo.getInstance(),Statuses.class,new Paging(1,10));
		Statuses s =wg.getData();
		if(s==null)
			return Statuses.NULL;
		return s;
	}
	public List<OverlayItemBmp> getFriendPlace() {
		Log.i(TAG, "getFriendPlace() running...");
		Statuses statuses=getFriendPlaceTimeLine();
		List<OverlayItemBmp> geoList=new ArrayList<OverlayItemBmp>();
		if(!statuses.isNull()){
			for (Status s : statuses.getStatuses()) {
				String geo =s.getGeo().toString();
				Log.i(TAG,"geo: "+geo);
				Geo g = s.getGeo();
				
				double latitude=g.getCoordinates()[0];
				double longitude=g.getCoordinates()[1];
				GeoPoint point=new GeoPoint((int)(latitude*1E6), (int)(longitude*1E6));
				String arg1=s.getUser().getScreen_name();
				String arg2=s.getUser().getIdstr();
				Bitmap bitmap=fun3(s.getUser().getProfile_image_url());
				geoList.add(new OverlayItemBmp(point, arg1, arg2,bitmap));
			}
		}else{
			
			Log.i(TAG, "没有返回数据!");
		}
		Log.i(TAG, "getFriendPlace() ending...");
		return geoList;
	}
	
	private Bitmap fun3(String urlStr){
		Log.i(TAG, "fun3 running...");
		Log.i(TAG, "urlStr : "+urlStr);
		URL url=null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
		}
		Drawable marker = NetUtil.getNetImage(url);
		BitmapDrawable b = (BitmapDrawable)marker;
		return b.getBitmap();
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
    @Override
    protected void onDestroy() {
    if (mBMapMan != null) {
    mBMapMan.destroy();
    mBMapMan = null;
    }
    super.onDestroy();
    }
    @Override
    protected void onPause() {
    if (mBMapMan != null) {
    mBMapMan.stop();
    }
    super.onPause();
    }
    @Override
    protected void onResume() {
    if (mBMapMan != null) {
    mBMapMan.start();
    }
    super.onResume();
    }
	
}
