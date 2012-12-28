package cn.yang.sina.weibo.loaders;

import java.util.List;

public interface Loadable<T> {

	Object loading(Object...objects);
	List<T> append(Object... objects);
}
