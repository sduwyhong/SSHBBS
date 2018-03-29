package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import page.Page;
import service.CategoryService;
import util.DateSortUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Article;
import entity.Category;

public class CategoryAction extends ActionSupport implements ModelDriven<Category> {
	//model
	private Category category = new Category();

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public Category getModel() {
		return category;
	}
	//upload
	private File categoryImg;//必须是File类型，名字对应表单的上传输入域<s:file/>中的name属性值
	private String categoryImgFileName;//上传文件的文件名。XXXFileName固定写法
	private String categoryImgContentType;//上传文件的MIME类型。XXXContentType固定写法

	public File getCategoryImg() {
		return categoryImg;
	}


	public void setCategoryImg(File categoryImg) {
		this.categoryImg = categoryImg;
	}


	public String getCategoryImgFileName() {
		return categoryImgFileName;
	}


	public void setCategoryImgFileName(String categoryImgFileName) {
		this.categoryImgFileName = categoryImgFileName;
	}


	public String getCategoryImgContentType() {
		return categoryImgContentType;
	}


	public void setCategoryImgContentType(String categoryImgContentType) {
		this.categoryImgContentType = categoryImgContentType;
	}

	//service
	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//遍历集合
	//板块集合
	private List<Category> categories = new ArrayList<Category>();

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
//	//帖子集合
//	private List<Article> articles;
//
//	public List<Article> getArticles() {
//		return articles;
//	}
//
//	public void setArticles(List<Article> articles) {
//		this.articles = articles;
//	}
	
//	//page是easyui传递到后台的值，表示页码
//    public int page = 1;
//    //rows是easyui传递到后台的值，表示每页大小
//    public int rows = 10;
//    public JSONObject result;
//    
//	public int getPage() {
//		return page;
//	}
//
//	public void setPage(int page) {
//		this.page = page;
//	}
//
//	public int getRows() {
//		return rows;
//	}
//
//	public void setRows(int rows) {
//		this.rows = rows;
//	}
//
//	public JSONObject getResult() {
//		return result;
//	}
//
//	public void setResult(JSONObject result) {
//		this.result = result;
//	}

	//添加板块
	public String add(){

		System.out.println(categoryImg);

		String realPath = "e:/bbs_file/category_img";
		File outputFile = new File(realPath,categoryImgFileName);
		try {
			FileUtils.copyFile(categoryImg, outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		category.setImg(realPath+"/"+categoryImgFileName);
		categoryService.save(category);

		return "homepage";
	}
	//显示首页（传递category集合）
	public String homepage(){
		categories = categoryService.getAll();
		ActionContext.getContext().getValueStack().push(categories);
		//离开板块内部，删除session信息
		ServletActionContext.getRequest().getSession().removeAttribute("categoryId");
		ServletActionContext.getRequest().getSession().removeAttribute("keyword");
		return "turnToHomepage";
	}
	//显示图片
	public String showImg(){

		String fileLocation = category.getImg();
		InputStream in = null;
		try {
			in = new FileInputStream(new File(fileLocation));
			byte[] b = new byte[1024];
			int len = 0;
			while((len=in.read(b))!=-1){
				ServletActionContext.getResponse().getOutputStream().write(b, 0, len);
				ServletActionContext.getResponse().getOutputStream().flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	//进入一个板块，显示所有帖子
	public String showArticleList(){
		String pageNum = ServletActionContext.getRequest().getParameter("pageNum");
		if(pageNum==null){
			pageNum = "1";
		}
		int id;
		if(category.getId()==null){
			id = (Integer) ServletActionContext.getRequest().getSession().getAttribute("categoryId");
		}else{
			id = category.getId();
		}
		Category c = categoryService.findById(id);
		ActionContext.getContext().getValueStack().push(c);
		Article[] as = new Article[c.getArticles().size()];
		c.getArticles().toArray(as);
		DateSortUtil.lastestFirst(as);
		Page page = new Page(Arrays.asList(as),Integer.parseInt(pageNum),3);
		ServletActionContext.getRequest().getSession().setAttribute("categoryId", c.getId());
		ActionContext.getContext().getValueStack().set("page", page);
		
		return "showArticleList";
	}
	
	//跳转到修改页面
	public String editUI(){
		
		Category temp = categoryService.findById(category.getId());
		category = temp;
		ActionContext.getContext().getValueStack().push(temp);
		return "editUI";
	}
	//修改
	public String edit(){
		
		Category temp = categoryService.findById(category.getId());
		
		if(!temp.getCategoryName().equals(category.getCategoryName())){
			temp.setCategoryName(category.getCategoryName());
		}
		if(!temp.getDescription().equals(category.getDescription())){
			temp.setDescription(category.getDescription());
		}
		
		if(categoryImg!=null){
			String realPath = "e:/bbs_file/category_img";
			File outputFile = new File(realPath,categoryImgFileName);
			try {
				FileUtils.copyFile(categoryImg, outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			temp.setImg(realPath+"/"+categoryImgFileName);
		}
		
		categoryService.update(temp);
		
		return "edit";
	}
}
