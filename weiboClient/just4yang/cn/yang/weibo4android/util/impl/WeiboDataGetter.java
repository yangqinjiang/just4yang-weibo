package cn.yang.weibo4android.util.impl;

/**
 * 获取时间线的接口
 * @author yangqinjiang
 *
 */
public interface WeiboDataGetter<T> {

	/**
	 * 模板方法
	 * @return 泛型T
	 */
	public <T> T getData();
}
