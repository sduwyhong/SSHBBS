package dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import util.SessionFactoryUtil;

import dao.base.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class classt;

	private HibernateTemplate hibernateTemplate;//难点1
	
//	public HibernateTemplate getHibernateTemplate() {
//		return hibernateTemplate;
//	}
//	//spring配置
//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernateTemplate = hibernateTemplate;
//	}

	public BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		classt = (Class) type.getActualTypeArguments()[0];
	}
	
	@Override
	public List<T> getAll() {
		return getHibernateTemplate().find("from "+this.classt.getName());
	}

	@Override
	public void save(T t) {
		System.out.println("In save");
		getHibernateTemplate().save(t);
		System.out.println("End save");
	}

	@Override
	public void update(T t) {
		t = getHibernateTemplate().merge(t);//难点2
		getHibernateTemplate().update(t);
	}

	@Override
	public T delete(Serializable id) {
		T t = (T) getHibernateTemplate().get(classt, id);
		getHibernateTemplate().delete(t);
		return t;
	}

	@Override
	public T findById(Serializable id) {
		return (T) getHibernateTemplate().get(classt, id);
	}

}
