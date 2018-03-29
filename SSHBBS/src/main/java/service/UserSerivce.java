package service;

import java.util.List;

import service.base.BaseService;
import entity.Article;
import entity.Category;
import entity.Review;
import entity.User;

public interface UserSerivce extends BaseService<User> {
	
	//根据姓名查询
	User findUserByUsername(String username);
	//查看用户名是否已存在
	User checkUsername(String username);
	//登录（MD5加密）
	User login(String username,String password);
	//修改信息
	void editUser(User user);
	//发帖
	void postArticle(Article article);
	//回复
	void review(Review review);
	//加精（版主、管理员）
	Article setExc(int article_id);
	
	
}
