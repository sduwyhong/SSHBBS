package service.impl;

import java.util.List;
import java.util.Set;

import service.UserSerivce;
import service.base.impl.BaseServiceImpl;
import dao.ArticleDao;
import dao.CategoryDao;
import dao.ReviewDao;
import dao.UserDao;
import entity.Article;
import entity.Category;
import entity.Review;
import entity.User;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserSerivce {
	
	private UserDao userDao;
	private CategoryDao categoryDao;
	private ArticleDao articleDao;
	private ReviewDao reviewDao;

	public void setUserDao(UserDao userDao) {
		System.out.println("setUserDao");
		this.userDao = userDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	@Override
	public User checkUsername(String username) {
		//调用dao
		User user = userDao.findUserByUsername(username);
		if(user!=null){
			return user;
		}else{
			return null;
		}
	}

	@Override
	public User login(String username, String password) {
		//调用dao
		User user = userDao.findUserByUP(username, password);
		if(user!=null){
			return user;
		}else{
			return null;
		}
	}

	@Override
	public void editUser(User user) {
		//调用dao
		userDao.update(user);
	}

	@Override
	public void postArticle(Article article) {
		//调用dao
		Category category = article.getCategory();
		Set<Article> articles = category.getArticles();
		articles.add(article);
		category.setArticle_num(articles.size());
		
		User user = article.getUser();
		Set<Article> sets = user.getArticles();
		sets.add(article);
		user.setArticle_num(sets.size());
		
		articleDao.save(article);
		categoryDao.update(category);
		userDao.update(user);
	}

	@Override
	public void review(Review review) {
		
		Article article = review.getArticle();
		article.setReview_num(article.getReview_num()+1);
		
		reviewDao.save(review);
		articleDao.update(article);

	}

	@Override
	public Article setExc(int article_id) {
		Article article = articleDao.findById(article_id);
		article.setExcellent(true);
		return article;
	}

	@Override
	public User findUserByUsername(String username) {
		User user = userDao.findUserByUsername(username);
		if(user!=null){
			return user;
		}else{
			System.out.println("didn't find any user");
			return null;
		}
	}

}
