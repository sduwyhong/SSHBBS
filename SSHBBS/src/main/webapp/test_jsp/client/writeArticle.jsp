<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div class="title">
		发帖
	</div>
	<s:debug></s:debug>
	<div id="ckeditor_div">
	
	
		<form id="detailForm" action="${pageContext.request.contextPath }/article/articleAction_publish" method="post" style="margin-top:20px">
		标题：<input type="text" name="title" value="<s:property value="article.title"/>" style="width:500;">
			<input type="hidden" name="edit_aid" value="<s:property value="article.id"/>" >
		    <textarea id="content" name="content" ><s:property value="article.content"/></textarea>
		    <input type="button" value="保存" id="save" />
		</form>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
