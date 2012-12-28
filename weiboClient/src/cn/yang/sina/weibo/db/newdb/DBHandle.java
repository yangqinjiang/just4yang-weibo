package cn.yang.sina.weibo.db.newdb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.yang.sina.weibo.beans.app.WeiboApp;
import cn.yang.sina.weibo.db.DBHelper;

public abstract class DBHandle<T> implements DBHandleable<T> {

	/**
	 * 数据库助手
	 */
	private DBHelper dBHelper=new DBHelper(WeiboApp.context);
	
	//TODO 20120905
	public final T handle() {
		//TODO 1 获取SQLiteDatabase对象
		SQLiteDatabase sqLiteDatabase = dBHelper.getWritableDatabase();
		//TODO 2生成一个CotentValues对象，并设置相应的值
		ContentValues values=createContentValue();
		//TODO 3调用SQLiteDatabase对象的相关方法
		T result =operateDB(sqLiteDatabase,values);
		//TODO 4返回结果
		return result;
	}
	//2生成一个CotentValues对象，并设置相应的值
	abstract ContentValues createContentValue();
	//3 调用SQLiteDatabase对象的相关方法
	abstract T operateDB(SQLiteDatabase sd,ContentValues values);
	

}
