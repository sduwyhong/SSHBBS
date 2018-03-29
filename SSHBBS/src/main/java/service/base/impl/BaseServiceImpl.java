package service.base.impl;

import java.io.Serializable;

import java.util.List;

import service.base.BaseService;
import dao.base.BaseDao;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> baseDao;
	
	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<T> getAll() {
		return baseDao.getAll();
	}

	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public T delete(Serializable id) {
		return (T) baseDao.delete(id);
	}

	@Override
	public T findById(Serializable id) {
		return (T) baseDao.findById(id);
	}

}
