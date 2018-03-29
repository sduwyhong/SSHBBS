<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div class="title">
		用户登录
	</div>
	
	<div class="form_div">
		<div style="color:#FF0000">${actionMessages[0]}</div>
		<div><a href="${pageContext.request.contextPath }/user/userAction_registUI">没有账号？点击注册</a></div>
		<s:form action="userAction_login" namespace="/user" id="login_form">
	    	<s:textfield name="username" label="用户名"></s:textfield>
	    	<s:password name="password" label="密码"></s:password>
	    	<table>
	    		<tr>
	    			<td>
				    	验证码:<s:textfield id="validateCode" name="validateCode" label="验证码" theme="simple"></s:textfield>
	    			</td>
	    			<td id="VCimgdiv">
	    				<img src="${pageContext.request.contextPath}/user/userAction_showVC">
	    			</td>
	    			<td>
	    				<div id="reloadVC" 
	    				onmouseover="this.style.cursor='hand'"   
     					onmouseout="this.style.cursor='normal'" 
     					>看不清？换一张</div>
	    			</td>
	    		</tr>
	    	</table>
	    	<s:submit id="loginSubmit" value="登录"></s:submit>
	    </s:form>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
