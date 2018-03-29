<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div class="title">编辑板块</div>
	<div class="form_div">
			<img style="width:400px;" src="${pageContext.request.contextPath }/category/categoryAction_showImg?img=<s:property value="img"/>">
			<br>
			<a href="javascript:changeCategoryImg()">更改</a>
			<br>
		<s:form action="categoryAction_edit" namespace="/category" enctype="multipart/form-data">
			<input type="hidden" value="${id }" name="id">
			<div hidden="true" id="categoryImgDiv">
				<s:file name="categoryImg" label="板块图片" theme="simple"></s:file>
			</div>
			板块名称:<s:textfield name="categoryName" label="板块名称" theme="simple"></s:textfield>
			<br>
			板块描述:<s:textfield name="description" label="板块描述" theme="simple"></s:textfield>
			<br>
			<s:submit value="修改" theme="simple"></s:submit>
		</s:form>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
