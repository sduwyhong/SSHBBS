package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.lucene.queryparser.classic.ParseException;

import service.ArticleService;
import service.base.impl.BaseServiceImpl;
import util.LuceneUtil;
import dao.ArticleDao;
import entity.Article;

public class ArticleServiceImpl  extends BaseServiceImpl<Article> implements ArticleService {

	private ArticleDao articleDao;

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	@Override
	public Set<Article> findArticleByTitle(String title) {
		return articleDao.findArticleByTitle(title);
	}

	@Override
	public List<Article> search(String keyword) {
		LuceneUtil luceneUtil = new LuceneUtil();
		if(luceneUtil.dirIsEmpty()){
			luceneUtil.createIndex(getAll());
		}else{
			int[] ids = null;
			try {
				ids = luceneUtil.searchByTerm("title", keyword, 10);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			List<Article> articles = new ArrayList<Article>();
			Article article = null;
			for (int i = 0; i < ids.length; i++) {
				article = findById(ids[i]);
				if(article!=null){
					article.setTitle(article.getTitle().replace(keyword, "<font style=\"color:#FF0000\">"+keyword+"</font>"));
					articles.add(article);
				}
			}
			return articles;
		}
		return null;
	}
}
