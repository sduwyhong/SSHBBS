package dao.impl;

import java.util.List;

import dao.UserDao;
import dao.base.impl.BaseDaoImpl;
import entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	

	@Override
	public User findUserByUsername(String username) {
		List<User> users = getHibernateTemplate().find("from entity.User where username=?",username);
		if(users.size()>0){
			return users.get(0);
		}else{
			//如果没有元素，users.get(0)会报数组越界异常
			return null;
		}
	}

	@Override
	public User findUserByUP(String username, String password) {
		List<User> users = getHibernateTemplate().find("from entity.User where username=? and password=?",username,password);
		if(users.size()>0){
			return users.get(0);
		}else{
			//如果没有元素，users.get(0)会报数组越界异常
			return null;
		}
	}



}
