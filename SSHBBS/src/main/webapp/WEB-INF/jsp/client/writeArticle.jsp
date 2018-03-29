<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div class="title">
		发帖
	</div>
	<div id="ckeditor_div">
		标题：<input type="text" name="title" style="width:500;">
		<form id="detailForm" method="post" style="margin-top:20px">
		    <textarea id="content" name="content"></textarea>
		    <input type="button" value="保存" id="save" onclick="save()" />
		</form>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
