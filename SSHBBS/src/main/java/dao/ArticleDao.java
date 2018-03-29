package dao;

import java.util.List;
import java.util.Set;

import dao.base.BaseDao;
import entity.Article;

public interface ArticleDao extends BaseDao<Article> {
	
	Set<Article> findArticleByTitle(String title);
}
