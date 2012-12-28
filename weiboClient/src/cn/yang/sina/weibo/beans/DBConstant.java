package cn.yang.sina.weibo.beans;
/**
 * SQLite数据库所用到的常量
 * @author Administrator
 *
 */
public class DBConstant {

	
	public static final String _ID_INTEGER_PRIMARY_KEY = " ( _id INTEGER PRIMARY KEY, "	//自增ID
;
	public static final String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";
	public static final String EMOTICON = "emoticon";
	public static final String CATEGORY = "category";
	public static final String ORDER_NUMBER = "order_number";
	public static final String IS_COMMON = "is_common";
	public static final String IS_HOT = "is_hot";
	public static final String URL = "url";
	public static final String TYPE = "type";
	public static final String PHRASE = "phrase";
	public static final String WEIBO_EMOTION_TN = "EMOTIONS";
	public static final String TABLE_INTEGER = " Integer,";
	/**
	 * 数据库名称
	 */
	public static final String DB_NAME="JustForYangDN";
	/**
	 * 表名
	 */
	public static final String TABLE_NAME = "JustForYangTN";
	public static final int VERSION=1;
	/**
	 * _id,是数据表的索引，必须有该列名,1
	 */
	public static final String _ID ="_id";
	/**
	 * 用户Id，是数据库中表列名，2
	 */
	public static final String USER_ID ="UserId";
	/**
	 * 用户名UserName，是数据库中表列名，3
	 */
	public static final String USER_NAME ="UserName";
	/**
	 * 用户头像，是数据库中表列名，4
	 */
	public static final String USER_HEAD_PORTRAIT = "UserHeadPortrait";
	/**
	 * 从用户授权获得的Token,是数据库中表列名，5
	 */
	public static final String TOKEN = "token";
	/**
	 * 从用户授权获得的TokenSecret,是数据库中表列名，6
	 */
	public static final String TOKEN_SECRET ="TokenSecret";

	public static final String TABLE_TEXT = " TEXT, ";
	/**
	 * 生成一张数据表的Sql语句,用于保存授权信息等
	 */
	public static final String CREATE_TABLE_SQL = CREATE_TABLE_IF_NOT_EXISTS
												+ TABLE_NAME 
												+_ID_INTEGER_PRIMARY_KEY
												+ USER_ID+TABLE_TEXT
												+ USER_NAME+TABLE_TEXT
												+ USER_HEAD_PORTRAIT+" BLOB, "
												+ TOKEN+TABLE_TEXT
												+ TOKEN_SECRET+" TEXT)";

	
	/**
	 * 生成一张数据表的Sql语句,用于保存官方表情等
	 */
	public static final String CREATE_TABLE_EMOTIONS =
			CREATE_TABLE_IF_NOT_EXISTS
			+ WEIBO_EMOTION_TN 
			+_ID_INTEGER_PRIMARY_KEY
			+ PHRASE+TABLE_TEXT			//phrase=[织],//(String)
			+ TYPE+TABLE_TEXT				//type=face, //(String)
			+ URL+TABLE_TEXT				//url=http://img.t.sinajs.cn/zz2_org.gif,//(String)
			+ IS_HOT+TABLE_INTEGER		//is_hot=false,//(boolean)
			+ IS_COMMON+TABLE_INTEGER		//is_common=true, //(boolean)
			+ ORDER_NUMBER+TABLE_INTEGER	//order_number=0, //(int)
			+ CATEGORY+TABLE_TEXT			//category=	//(String)
			+ EMOTICON+" BLOB)";			//表情图片(BLOB)
}
