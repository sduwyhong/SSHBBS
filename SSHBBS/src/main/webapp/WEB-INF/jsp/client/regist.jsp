<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div class="title">
		用户注册
	</div>
	<div class="form_div">
		<s:form action="userAction_regist" namespace="/user" id="regist_form">
				<s:textfield name="username" label="用户名"></s:textfield>
				<s:textfield name="password" label="密码"></s:textfield>
				<s:textfield name="repassword" label="密码确认"></s:textfield>
				<s:textfield name="gender" label="性别"></s:textfield>
				<s:textfield name="birthday" label="出生日期"></s:textfield>
				<s:textfield name="telephone" label="手机号码"></s:textfield>
				<s:textfield name="email" label="邮箱"></s:textfield>
				<s:textfield name="address" label="地址"></s:textfield>
				<s:file name="sculpture" label="头像"></s:file>
		</s:form>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
