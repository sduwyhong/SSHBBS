package service.base;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
	
	List<T> getAll();

	void save(T t);

	void update(T t);

	T delete(Serializable id);

	T findById(Serializable id);
}
