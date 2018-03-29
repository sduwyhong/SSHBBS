<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div id="category_info">
		<div class="category_info_img">
			<div hidden="true" class="category_id"><s:property value="category.id"/></div>
			<img alt="" src="${pageContext.request.contextPath }/category/categoryAction_showImg?img=<s:property value="img"/>">
		</div>
		<div class="category_info_desc">
			<br>
			<br>
			<b><s:property value="categoryName"/></b>
			<br>
			<br>
			<s:property value="description"/>
			<br>
			<br>
			<a style="font-size:10px;" href="${pageContext.request.contextPath }/category/categoryAction_editUI?id=${id}">编辑板块</a>
		</div>
		<!-- 
	<div id="category_operation">
	</div> -->
	</div>
	<!-- 
	<div id="article_header">
		<div id="article_list">article_list</div>
		<div id="write_article">write_article</div>
	</div>
	 -->
	 <div id="article_header">
		<a id="write_article" href=""><b>发帖</b></a>
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
				<a class="article_title" href="${pageContext.request.contextPath }/article/articleAction_showArticleDetail?id=<s:property value="#article.id"/>"><s:property value="#article.title"/></a>
				<div class="article_user"><s:property value="#article.user.username"/></div>
				<div class="article_click_num"><s:property value="#article.click_num"/></div>
				<div class="article_review_num"><s:property value="#article.review_num"/></div>
				<div class="article_publish_time"><div><fmt:formatDate value="${article.publish_time }" pattern="yyyy-MM-dd HH:mm"/></div></div>
			</div>
		</s:iterator>
		 <!-- easyUI表格 -->
		 <!-- <table id="article"></table>  -->
	</div>
	<div class="page">
			<s:iterator begin="page.beginPage" end="page.endPage" var="pageIndex">
				<a href="${pageContext.request.contextPath }/category/categoryAction_showArticleList?pageNum=<s:property value="#pageIndex"/>&id=${sessionScope.categoryId}"><s:property value="#pageIndex"/></a>&nbsp;&nbsp;
			</s:iterator>
	</div>
<%@ include file="footer.jsp" %>
  </body>
</html>
