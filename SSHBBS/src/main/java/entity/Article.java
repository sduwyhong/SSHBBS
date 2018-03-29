package entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="bbs_article")
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="publish_time")
	private Date publish_time;
	@Column(name="isExcellent")
	private boolean isExcellent;
	@Column(name="click_num")
	private int click_num;
	@Column(name="review_num")
	private int review_num;
	
	@Column(name="content")
	private String content;
	
	@ManyToOne(targetEntity=User.class,fetch=FetchType.EAGER)
	private User user;
	@ManyToOne(targetEntity=Category.class,fetch=FetchType.EAGER)
	private Category category;
	@OneToMany(targetEntity=Review.class,mappedBy="article",fetch=FetchType.EAGER)
	private Set<Review> reviews;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}
	public boolean isExcellent() {
		return isExcellent;
	}
	public void setExcellent(boolean isExcellent) {
		this.isExcellent = isExcellent;
	}
	public int getClick_num() {
		return click_num;
	}
	public void setClick_num(int click_num) {
		this.click_num = click_num;
	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Review> getReviews() {
		return reviews;
	}
	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	
	
}
