package cn.yang.sina.weibo.db.newdb;
//数据库操作接口
public interface DBHandleable<T> {

	//返回类型为泛型
	 T handle();
}
