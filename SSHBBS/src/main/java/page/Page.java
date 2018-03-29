package page;

import java.util.ArrayList;
import java.util.List;

import entity.Article;

public class Page {
	//查询记录集合
	private List<Article> articles = new ArrayList<Article>();
	//总记录数
	private int totalRecordsNum;
	//每页记录数
	private int numEachPage;
	//目标页数
	private int targetPage;
	//页数
	private int totalPageNum;
	//第一页
	private int beginPage;
	//最后一页
	private int endPage;
	//起始索引
	private int beginIndex;
	//终止索引
	private int endIndex;
	/*
	 * 1 0 4
	 * 2 5 9
	 * 3 10 14
	 */
	public Page(List<Article> articles,int targetPage,int numEachPage ){
		
		this.totalRecordsNum = articles.size();
		
		this.numEachPage = numEachPage;
		if(totalRecordsNum%numEachPage==0){
			totalPageNum = totalRecordsNum/numEachPage;
		}else{
			totalPageNum = totalRecordsNum/numEachPage+1;
		}
		
		if(totalPageNum==1){
			beginPage = endPage = 1;
		}else{
			beginPage = 1;
			endPage = totalPageNum;
		}
		
		beginIndex = (targetPage-1)*numEachPage;
		if((articles.size()-1)-beginIndex<numEachPage){
			//最后一页结果数小于每页结果数
			//则结束索引 = 总结果数-1
			endIndex =  articles.size()-1;
		}else{
			//结束索引 = 起始索引 +每页结果数-1
			endIndex = beginIndex + numEachPage -1;
		}
		System.out.println("beginIndex"+beginIndex);
		System.out.println("endIndex"+endIndex);
		System.out.println(articles.size());
		for (int i = beginIndex ; i <= endIndex; i++) {
			this.articles.add(articles.get(i));
		}
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getNumEachPage() {
		return numEachPage;
	}

	public void setNumEachPage(int numEachPage) {
		this.numEachPage = numEachPage;
	}

	public int getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(int targetPage) {
		this.targetPage = targetPage;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getTotalRecordsNum() {
		return totalRecordsNum;
	}

	public void setTotalRecordsNum(int totalRecordsNum) {
		this.totalRecordsNum = totalRecordsNum;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	
	
	
	
}
