package dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.ArticleDao;
import dao.base.impl.BaseDaoImpl;
import entity.Article;

public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao {

	@Override
	public Set<Article> findArticleByTitle(String title) {
		List<Article> articles = getHibernateTemplate().find("from entity.Article where title=?", title);
		Set set = new HashSet<Article>(articles);
		return set;
	}



}
