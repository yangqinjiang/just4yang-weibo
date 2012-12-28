package cn.yang.sina.weibo.imagecache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import cn.yang.sina.weibo.ui.util.EncryptUtil;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 管理图片资源的类
 * @author Administrator
 *
 */
public class ImageManager {

	/**定义一个map对象，其中SoftReference .Bitmap .是对图片数据的软引用，便于内存回收*/
	Map<String,SoftReference<Bitmap>> imgCache ;
	/**Context上下文*/
	private Context context;
	/**构造方法*/
	public ImageManager(Context context){
		this.context=context;//TODO  应该在Application类中保存一个context对象
		imgCache = new HashMap<String, SoftReference<Bitmap>>();
	}
	
	/**
	 * 判断缓存Cache中是否存在网络图片
	 * @param key 网络图片的url
	 * @return  返回true,表示有key 的url图片，返回false,表示没有key图片,需要下载该图片
	 */
	public boolean containsImg(String key){
		return imgCache.containsKey(key);
	}

	/**
	 * 从Cache缓存中取出图片数据
	 * @param url	网络图片url
	 * @return  Bitmap  以url为key的网络图片
	 */
	public Bitmap getFromCache(String url) {
		System.out.println("getFromCache(string url) running");		
		Bitmap bitmap = null;
		//先尝试从ImageMapCache缓存中取图片数据
		bitmap = this.getFromImageMapCache(url);
		if(null == bitmap){//取不到图片数据
			//则从文件缓存中取  fileCache
			bitmap = this.getFromImageFileCache(url);
			if(bitmap==null){//TODO 再次取不到图片数据时，则开始从网络上获取图片数据
				System.out.println("getFromCache(String url) 找不到图片文件...开始线程下载...");
			}
				
		}
		return bitmap; 
	}

	/**
	 * 从ImageFileCache文件缓存中取出图片数据
	 * @param url	网络图片url
	 * @return  Bitmap 以url为key的网络图片
	 * @throws IOException 
	 */
	public Bitmap getFromImageFileCache(String url) {
		System.out.println("getFromImageFileCache(string url) running");
		//为了防止http://www.xxx.com/j.png这样的url作为文件名发生错误时，需要将url加密
		String fileName = this.getMd5(url);
		FileInputStream is = null;
		try {
			//打开一个文件对象，并返回FileInputStream文件输入流对象
			 is = context.openFileInput(fileName);
			 Bitmap bitmap =BitmapFactory.decodeStream(is);//使用Bitmap的工厂类，对is进行编码，返回 bitmap
			 //将从文件中读取到的图片放进imgCache缓存中
			 imgCache.put(url, new SoftReference<Bitmap>(bitmap));
			 return bitmap;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;//当发生异常时，返回null
			
		}finally{
			try{
				if(null!=is)is.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从ImageMapCache  Map缓存中取出图片数据
	 * @param url  网络图片url
	 * @return Bitmap 以url为key的网络图片
	 */
	public Bitmap getFromImageMapCache(String url) {
		System.out.println("getFromImageMapCache(string url) running");
		Bitmap bitmap = null;
		/**软引用的bitmap*/
		SoftReference<Bitmap>  ref = null;
		synchronized(this){	//加锁，避免线程安全问题
			ref = imgCache.get(url);//尝试从imgCache缓存中取出数据
			if(null!=ref){//如果取出的ref不为空
				bitmap = ref.get();//从ref中取出bitmap
				if(null!=bitmap)//再来判断bitmap是否为空
					return bitmap;
				else 
					return null;//否则从文件缓存中取出  file
				
			}
		}
		return bitmap;
	}
	
	/**
	 * 从缓存或网络上加载图片数据
	 * @param url  图片url
	 * @return  bitmap 返回key为url的图片
	 * @throws Exception
	 */
	public Bitmap safeGet(String url){
		//System.out.println("safeGet(string url) running");
		//如果在缓存中不存在图片数据，则从网络上加载图片数据
		Bitmap bitmap =downloadImg(url);
		//将下载下来的图片数据放进缓存中
		imgCache.put(url, new SoftReference<Bitmap>(bitmap));
		return bitmap;
	}
	
	/**
	 * 将参数 url进行加密，并返回加密后的string
	 * @param url  需要加密的String  url对象
	 * @return 加密后的string
	 */
	private String getMd5(String url){
		return EncryptUtil.getMD5String(url);
	}

	/**
	 * 从网络上获取图片数据，并返回一个图片Bitmap对象，
	 * @param url  网络图片的url
	 * @return bitmap对象
	 */
	private Bitmap downloadImg(String url) {
		System.out.println("downloadImg(string url) running");
		try
		{
			//连接网络
			HttpURLConnection connection =(HttpURLConnection) new URL(url).openConnection();
			//得到输入流
			InputStream is = connection.getInputStream();
			//TODO 保存到文件中
			//System.out.println("downloadImg(string url) url "+url);
			String fileName = writeToFile(url,is);
			//System.out.println("downloadImg(string url) fileName "+fileName);
			//再从文件中读取出来
			return BitmapFactory.decodeFile(fileName);
		} catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * 保存网络数据流  
	 * @param url  url是文件名
	 * @param is  从网络上得到的数据流
	 */
	private String writeToFile(String url, InputStream is) {
		System.out.println("writeToFile  running...");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			//System.out.println("writeToFile  starting...");
			String fileName =this.getMd5(url);
			//System.out.println("writeToFile  fileName  "+fileName);
			
			bos = new BufferedOutputStream(context.openFileOutput(fileName, Context.MODE_APPEND));
			//System.out.println("writeToFile  ending...");
			
			byte[] buffer = new byte[1024];
			int length;
			while((length=bis.read(buffer))!=-1)
				bos.write(buffer, 0, length);
			//System.out.println("writeToFile  fileDir&fileName:   "+context.getFilesDir()+"/"+fileName);
			return context.getFilesDir()+"/"+fileName;//返回文件路径与文件名的组合字符串
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
				try {
					if(null!=bis)
						bis.close();//关闭输入流
					if(null!=bos)
						bos.flush();//刷新
						bos.close();//关闭输出流
				} catch (IOException e) {}
		}
	}
}
