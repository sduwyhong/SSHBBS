package service;

import java.util.List;
import java.util.Set;

import service.base.BaseService;
import entity.Article;

public interface ArticleService extends BaseService<Article> {
	
	Set<Article> findArticleByTitle(String title);
	//搜索帖子
	List<Article> search(String keyword);
}
