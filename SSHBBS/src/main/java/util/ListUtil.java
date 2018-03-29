package util;

import java.util.ArrayList;
import java.util.List;

import entity.Article;

public class ListUtil {

	public static Article[] getNotNullElement(List<Article> articles){
		List<Article> temp = new ArrayList<Article>();
		for (Article article : articles) {
			if(article!=null){
				temp.add(article);
			}
		}
		Article[] as = new Article[temp.size()];
		temp.toArray(as);
		return as;
	}
}
