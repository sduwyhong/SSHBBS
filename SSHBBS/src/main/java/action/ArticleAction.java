package action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import page.Page;

import service.ArticleService;
import service.CategoryService;
import service.ReviewService;
import service.UserSerivce;
import util.DateSortUtil;
import util.ListUtil;
import util.LuceneUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Article;
import entity.Category;
import entity.Review;
import entity.User;

public class ArticleAction extends ActionSupport implements ModelDriven<Article> {

	private Article article = new Article();


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public Article getModel() {
		return article;
	}
	//service
	private ArticleService articleService;


	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	//id
	private int articleId;
	
	public int getArticleId() {
		return articleId;
	}
	//service
	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	private ReviewService reviewService;

	public ReviewService getReviewService() {
		return reviewService;
	}
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	private int categoryId;

	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	//service
	private UserSerivce userService;

	public void setUserService(UserSerivce userService) {
		this.userService = userService;
	}

//	//评论集合
//	private List<Review> reviews;
//
//	public List<Review> getReviews() {
//		return reviews;
//	}
//
//
//	public void setReviews(List<Review> reviews) {
//		this.reviews = reviews;
//	}


	//跳转到编辑页面
	public String add(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user==null){
			return "login";
		}else{
//			String categoryId = ServletActionContext.getRequest().getParameter("categoryId");
//			ServletActionContext.getRequest().getSession().setAttribute("categoryId", categoryId);
			return "writePage";
		}
	}
	//发布文章
	public String publish(){
		//判断是编辑还是修改，依据是前台传回的edit_aid字段是否为""
		String edit_aid = (String)ServletActionContext.getRequest().getParameter("edit_aid");
		if(!edit_aid.equals("0")){
			//编辑
			Article temp = articleService.findById(Integer.parseInt(edit_aid));
			temp.setContent(article.getContent());
			temp.setTitle(article.getTitle());
			articleService.update(temp);
			//更新lucene索引
			LuceneUtil luceneUtil = new LuceneUtil();
			try {
				luceneUtil.updateIndex("title", article.getTitle(), article);
			} catch (Exception e) {
				e.printStackTrace();
			}
			articleId = Integer.parseInt(edit_aid);
			return "edit";
		}else{
			String str = article.getContent();
			str = str.replace("</p>", "</p><br>");
			article.setContent(str);
			String id = ServletActionContext.getRequest().getSession().getAttribute("categoryId")+"";
			categoryId = Integer.parseInt(id);
			Category category = categoryService.findById(categoryId);
			Set<Article> as1 = category.getArticles();
			as1.add(article);
			category.setArticle_num(as1.size());

			article.setCategory(category);

			User temp = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
			//刷新一下数据
			User user = userService.findById(temp.getId());
			ServletActionContext.getRequest().getSession().setAttribute("user", user);

			Set<Article> as2 = user.getArticles();
			as2.add(article);
			user.setArticle_num(as2.size());

			article.setUser(user);

			article.setPublish_time(new Date());

			articleService.save(article);
			userService.update(user);
			categoryService.update(category);
			//向lucene添加索引
			LuceneUtil luceneUtil = new LuceneUtil();
			List<Article> list = new ArrayList<Article>();
			list.add(article);
			try {
				luceneUtil.createIndex(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//发帖完成，删除session中的目录id
//			ServletActionContext.getRequest().getSession().removeAttribute("categoryId");
		}
		return "publish";
	}
	//显示帖子详细信息
	public String showArticleDetail(){
		Article a = articleService.findById(article.getId());
		article = a;
		if(article==null){
			String id = ServletActionContext.getRequest().getParameter("id");
			System.out.println("param id:"+id);
			article = articleService.findById(Integer.parseInt(id));
		}
		ServletActionContext.getRequest().getSession().setAttribute("articleId", article.getId());

		article.setClick_num(article.getClick_num()+1);
		articleService.update(article);
		ActionContext.getContext().getValueStack().push(article);
		
		Review[] rs = new Review[article.getReviews().size()];
		article.getReviews().toArray(rs);
//		for (int i = 0; i < rs.length-1; i++) {
//			for (int j = i+1; j < rs.length; j++) {
//				if(rs[i].getPublish_time().after(rs[j].getPublish_time())){
//					Review t = rs[i];
//					rs[i] = rs[j];
//					rs[j] = t;
//				}
//			}
//		}
		DateSortUtil.sortDate(rs);
		ActionContext.getContext().getValueStack().set("reviews",Arrays.asList(rs));
		
		ServletActionContext.getRequest().getSession().removeAttribute("keyword");
		return "showArticleDetail";
	}

	//删除
	public String delete(){
		Article temp = articleService.findById(article.getId());
		User user = temp.getUser();
		//删除该帖子下所有评论
		Set<Review> reviews = temp.getReviews();
		for (Review review : reviews) {
			reviewService.delete(review.getId());
			user.getReviews().remove(review);
		}
		//该帖子所属板块贴子数-1
		Category category = temp.getCategory();
		Set<Article> as1 = category.getArticles();
		as1.remove(temp);
		category.setArticle_num(as1.size());
		//该帖子所属用户贴子数-1
		Set<Article> as2 = user.getArticles();
		as2.remove(temp);
		user.setArticle_num(as2.size());
		//删除
		articleService.delete(temp.getId());
		//更新
		userService.update(user);
		categoryService.update(category);
		//lucene删除索引
		LuceneUtil luceneUtil = new LuceneUtil();
		try {
			luceneUtil.delIndex("title", temp.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回帖子列表（category.jsp）
		categoryId = category.getId();
		return "delete";
	}

	//编辑
	public String editUI(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user==null){
			return "login";
		}else{
			Article temp = articleService.findById(article.getId());
			article = temp;
			return "writePage";
		}
	}
	
	//搜索
	public String search(){
		String pageNum = ServletActionContext.getRequest().getParameter("pageNum");
		if(pageNum==null){
			pageNum = "1";
		}
		//分页要传入关键字参数
		String keyword = ServletActionContext.getRequest().getParameter("keyword");
		if(keyword!=null){
			ServletActionContext.getRequest().getSession().setAttribute("keyword", keyword);
		}else{
			keyword = (String) ServletActionContext.getRequest().getSession().getAttribute("keyword");
		}
		List<Article> articles = articleService.search(keyword);
		Article[] as = ListUtil.getNotNullElement(articles);
		DateSortUtil.lastestFirst(as);
		Page page = new Page(Arrays.asList(as),Integer.parseInt(pageNum),2);
		System.out.println(page.getArticles());
//		ActionContext.getContext().getValueStack().set("articles", Arrays.asList(as));
		ActionContext.getContext().getValueStack().set("page", page);
		
		return "search";
	}
	
	
}
