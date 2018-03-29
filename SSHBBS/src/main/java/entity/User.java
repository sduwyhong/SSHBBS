package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="bbs_user")
public class User {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	@Column(name="id",nullable=false)
	//不同生成策略的id类型不同，出错的话无法创建表
	private String id;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="gender")
	private String gender;
	@Column(name="birthday")
	private Date birthday;
	@Column(name="telephone")
	private String telephone;
	@Column(name="email")
	private String email;
	/*
	 * 表中字段不能出现关键字，如：
		order，level，address，degree等。
	 */
	@Column(name="[address]")
	private String address;
	@Column(name="privilege")
	private int privilege;
	@Column(name="sculpture")
	private String sculpture;
	
	@Column(name="article_num")
	private int article_num;
	@Column(name="regist_time")
	private Date regist_time;
	@OneToOne(targetEntity=Category.class,mappedBy="user",fetch=FetchType.EAGER)
	private Category category;
	@OneToMany(targetEntity=Article.class,mappedBy="user",fetch=FetchType.EAGER)
	private Set<Article> articles;
	@OneToMany(targetEntity=Review.class,mappedBy="user",fetch=FetchType.EAGER)
	private Set<Review> reviews;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		
		return birthday;
	}
	public void setBirthday(Date birthday) {
		
		this.birthday = birthday;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPrivilege() {
		return privilege;
	}
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}
	public String getSculpture() {
		return sculpture;
	}
	public void setSculpture(String sculpture) {
		this.sculpture = sculpture;
	}
	public int getArticle_num() {
		return article_num;
	}
	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}
	public Date getRegist_time() {
		return regist_time;
	}
	public void setRegist_time(Date regist_time) {
		this.regist_time = regist_time;
	}
	
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	public Set<Review> getReviews() {
		return reviews;
	}
	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
