package cn.yang.sina.weibo.db;

import cn.yang.sina.weibo.beans.DBConstant;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static final String TAG = "DBHelper";

	public DBHelper(Context context) {
		super(context, DBConstant.DB_NAME, null, DBConstant.VERSION);
		Log.i(TAG, "调用了DBHelper构造方法");
	}

	/**
	 * 生成数据库和表结构
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		Log.i(TAG, "生成了一张"+DBConstant.TABLE_NAME+"数据表...");
		db.execSQL(DBConstant.CREATE_TABLE_SQL);
		db.execSQL(DBConstant.CREATE_TABLE_EMOTIONS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "更新了一张"+DBConstant.TABLE_NAME+"数据表...");
	}

}
