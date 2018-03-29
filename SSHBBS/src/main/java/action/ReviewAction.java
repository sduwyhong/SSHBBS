package action;

import java.util.Date;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import service.ArticleService;
import service.ReviewService;
import service.UserSerivce;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Article;
import entity.Review;
import entity.User;

public class ReviewAction extends ActionSupport implements ModelDriven<Review> {

	private Review review = new Review();

	public Review getReview() {
		return review;
	}


	public void setReview(Review review) {
		this.review = review;
	}

	@Override
	public Review getModel() {
		return review;
	}

	private ReviewService reviewService;

	public ReviewService getReviewService() {
		return reviewService;
	}
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	//service
	private ArticleService articleService;


	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	private int articleId;
	
	public int getArticleId() {
		return articleId;
	}


	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

		//service
		private UserSerivce userService;

		public void setUserService(UserSerivce userService) {
			this.userService = userService;
		}
	public String publish(){
		User temp = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		//刷新一下数据
		User user = userService.findById(temp.getId());
		ServletActionContext.getRequest().getSession().setAttribute("user", user);
		
		if(user==null){
			return "login";
		}else{
			user.getReviews().add(review);
			review.setUser(user);
			
			int id = (Integer) ServletActionContext.getRequest().getSession().getAttribute("articleId");
			Article article = articleService.findById(id);
			Set<Review> reviews = article.getReviews();
			reviews.add(review);
			article.setReview_num(reviews.size());
			review.setArticle(article);
			
			review.setPublish_time(new Date());
			
			reviewService.save(review);
			userService.update(user);
			articleService.update(article);
			
			articleId = article.getId();
			
			return "publishReview";
		}
	}
	
	//删除
	public String delete(){
		int rid = review.getId();
		Review temp = reviewService.findById(rid);
		//从对应帖子中删除
		Article a = temp.getArticle();
		Set<Review> rs1 = a.getReviews();
		rs1.remove(temp);
		a.setReview_num(rs1.size());
		//从对应用户中删除
		User u = temp.getUser();
		Set<Review> rs2 = u.getReviews();
		rs2.remove(temp);
		//删除
		reviewService.delete(temp.getId());
		//更新帖子
		articleService.update(a);
		//更新用户
		userService.update(u);
		//跳转到文章
		articleId = a.getId();
		return "delete";
	}

}
