package test;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import service.ArticleService;
import service.CategoryService;
import service.ReviewService;
import service.UserSerivce;
import entity.Article;
import entity.Category;
import entity.Review;
import entity.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@Transactional
public class UserDaoTest {
	@Resource
	private UserSerivce userService;
	@Resource
	private ArticleService articleService;
	@Resource
	private ReviewService reviewService;
	@Resource
	private CategoryService categoryService;
	//junit测试需初始化容器
//	@Before
//	public void setup(){
//		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
//				"applicationContext-dao.xml",
//				"applicationContext-db.xml"
//		});
//		userService = (UserSerivce)context.getBean("userService");
//	}
//
	public void setUserService(UserSerivce userService) {
		this.userService = userService;
	}
	
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Test
	@Rollback(false)
	public void saveUser(){
			User user = new User();
			user.setUsername("yhw");
			user.setPassword("123");
			user.setGender("男");
			user.setBirthday(new Date());
			user.setTelephone("17864154784");
			user.setEmail("285944978@qq.com");
			user.setAddress("水岸新都");
			user.setPrivilege(3);
			user.setRegist_time(new Date());
			
			userService.save(user);
	}
	
	@Test
	@Rollback(false)
	public void saveCategory(){
			Category category = new Category();
			category.setCategoryName("汽车");
			category.setDescription("来这里畅谈汽车！");
			
			User user = userService.findUserByUsername("wyh");
			System.out.println(user);
			
			category.setUser(user);
			Article article1 = new Article();
			article1.setTitle("天晴不错噢");
			article1.setPublish_time(new Date());
			article1.setContent("今天天气好好啊");
			article1.setExcellent(true);
			
			Article article2 = new Article();
			article2.setTitle("天好差噢");
			article2.setPublish_time(new Date());
			article2.setContent("今天天气好差啊");
			article2.setExcellent(false);
			
			Set<Article> set = category.getArticles();
			set.add(article1);
			set.add(article2);
			
			//多方关联单方对象，这样才能在多方中保存单方id
			article1.setCategory(category);
			article2.setCategory(category);
			
			category.setArticle_num(set.size());
			
			categoryService.save(category);
	}

	@Test
	@Rollback(false)
	public void saveArticle(){
		Article article = new Article();
		article.setTitle("天晴不错噢");
		article.setPublish_time(new Date());
		article.setContent("今天天气好好啊");
		article.setExcellent(true);
//			User user = userService.findUserByUsername("wyh");
//			System.out.println(user);
		userService.postArticle(article);	
	}
	
	@Test
	@Rollback(false)
	public void saveReview(){
		Review review = new Review();
		review.setContent("我这边天气也不错呢！");
		review.setPublish_time(new Date());
//			User user = userService.findUserByUsername("wyh");
//			System.out.println(user);
		userService.review(review);
	}
	
	@Test
	@Rollback(false)
	public void updateReview(){
		Review review = reviewService.findById(1);
		
		User user = userService.findUserByUsername("wyh");
		Article article = articleService.findById(3);
		
		review.setArticle(article);
		review.setUser(user);
		
		reviewService.update(review);
		
	}
	
	@Test
	@Rollback(false)
	public void updateArticle(){
		Article article1 = articleService.findById(3);
		Article article2 = articleService.findById(4);
		
		User user = userService.findUserByUsername("wyh");
		article1.setUser(user);
		article2.setUser(user);
		
		articleService.update(article1);
		articleService.update(article2);
		
	}
	
}
