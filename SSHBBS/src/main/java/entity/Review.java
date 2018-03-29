package entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="bbs_review")
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="content")
	private String content;
	@Column(name="publish_time")
	private Date publish_time;
	
	@ManyToOne(targetEntity=Article.class,fetch=FetchType.EAGER)
	private Article article;
	@ManyToOne(targetEntity=User.class,fetch=FetchType.EAGER)
	private User user;
	
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}


}
