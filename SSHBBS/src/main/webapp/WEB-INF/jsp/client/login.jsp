<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div class="title">
		用户登录
	</div>
	<div class="form_div">
		<s:form action="" id="login_form">
	    	<s:textfield name="username" label="用户名"></s:textfield>
	    	<s:textfield name="password" label="密码"></s:textfield>
	    	<s:textfield name="validatecode" label="验证码"></s:textfield>
	    	<s:submit value="登录"></s:submit>
	    </s:form>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
