<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	
	 <div id="article_header">
	 	搜索结果
	</div>
	 <s:debug></s:debug>
	<div id="articles">
			<div class="article" id="article_info_header" style="background-color: #ADD8E6">
				<div class="article_title" >标题</div>
				<div class="article_user">作者</div>
				<div class="article_click_num">点击数</div>
				<div class="article_review_num">评论数</div>
				<div class="article_publish_time">发布时间</div>
			</div>
		<s:iterator value="page.articles" var="article">
			<div class="article">
				<a class="article_title" href="${pageContext.request.contextPath }/article/articleAction_showArticleDetail?id=<s:property value="#article.id"/>"><s:property value="#article.title" escapeHtml="false"/></a>
				<div class="article_user"><s:property value="#article.user.username"/></div>
				<div class="article_click_num"><s:property value="#article.click_num"/></div>
				<div class="article_review_num"><s:property value="#article.review_num"/></div>
				<!-- EL在这有时加var别名，有时直接填属性名，一脸懵逼？？ -->
				<div class="article_publish_time"><div><fmt:formatDate value="${publish_time }" pattern="yyyy-MM-dd HH:mm"/></div></div>
			</div>
		</s:iterator>
		
		 <!-- easyUI表格 -->
		 <!-- <table id="article"></table>  -->
	</div>
	<div class="page">
			<s:iterator begin="page.beginPage" end="page.endPage" var="pageIndex">
				<a href="${pageContext.request.contextPath }/article/articleAction_search?pageNum=<s:property value="#pageIndex"/>"><s:property value="#pageIndex"/></a>&nbsp;&nbsp;
			</s:iterator>
	</div>
<%@ include file="footer.jsp" %>
  </body>
</html>
