package dao.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	//查询全部
	List<T> getAll();
	//保存
	void save(T t);
	//更新
	void update(T t);
	//删除
	T delete(Serializable id);
	//查询单个
	T findById(Serializable id);
}
