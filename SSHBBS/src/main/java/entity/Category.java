package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table(name="bbs_category")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//strategy=GenerationType.AUTO
	//id必须是int类型才能自增
	@Column(name="id",nullable=false)
	private Integer id;
	@Column(name="categoryName")
	private String categoryName;
	@Column(name="description")
	private String description;
	@Column(name="img")
	private String img;
	@Column(name="article_num")
	private int article_num;

	@OneToOne(targetEntity=User.class,fetch=FetchType.EAGER)
	private User user;
	@OneToMany(targetEntity=Article.class,mappedBy="category",fetch=FetchType.EAGER)
	private Set<Article> articles;
	
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getArticle_num() {
		return article_num;
	}
	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
