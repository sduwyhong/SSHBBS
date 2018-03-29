<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<s:debug></s:debug>
	<div class="title">
		板块分类
	</div>
	<div id="add_category">
		<a href="${pageContext.request.contextPath }/test_jsp/client/add_category.jsp">添加板块</a>
	</div>
	<div id="category">
		<s:if test="categories!=null">
			<s:iterator value="categories" var="category">
				<div class="category_content">
					<div class="category_img" onclick="">
						<div hidden="true" class="category_id"><s:property value="#category.id"/></div>
						<img alt="" src="${pageContext.request.contextPath }/category/categoryAction_showImg?img=<s:property value="#category.img"/>">
						<br>
						<br>
						<b><s:property value="#category.categoryName"/></b>
						<br>
						<s:property value="#category.description"/>
					</div>
					<br>
					<div class="category_article_num">(<font style="color: #FF0000"><s:property value="#category.article_num"/></font>)</div>
				</div>
			</s:iterator>
		</s:if>
		<!--
		<div class="category_content">
			<div class="category_img">category_img</div>
			<div class="category_article_num">category_article_num</div>
		</div>
		<div class="category_content">
			<div class="category_img">category_img</div>
			<div class="category_article_num">category_article_num</div>
		</div>
		 -->
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
