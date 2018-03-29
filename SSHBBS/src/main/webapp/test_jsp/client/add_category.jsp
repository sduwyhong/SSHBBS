<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div class="title">添加板块</div>
	<div class="form_div">
		<s:form action="categoryAction_add" namespace="/category" id="ac_form" enctype="multipart/form-data">
			<s:textfield name="categoryName" label="板块名称"></s:textfield>
			<s:textfield name="description" label="板块描述"></s:textfield>
			<s:file name="categoryImg" label="板块图片"></s:file>
			<s:submit value="添加"></s:submit>
		</s:form>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
