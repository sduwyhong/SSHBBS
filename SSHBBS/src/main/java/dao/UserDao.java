package dao;

import java.util.List;

import dao.base.BaseDao;
import entity.User;

public interface UserDao extends BaseDao<User> {
	
	User findUserByUP(String username,String password);
	
	User findUserByUsername(String username);
}
