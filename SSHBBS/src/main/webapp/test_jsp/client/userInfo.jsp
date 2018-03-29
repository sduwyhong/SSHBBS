<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div class="title">
		个人信息
	</div>
	<a href="${pageContext.request.contextPath }/user/userAction_editUI?id=${user.id}" style="margin-top:20px;">修改信息</a>
	<div class="form_div" id="userInfo_div">
				<img src="${pageContext.request.contextPath }/user/userAction_showSculpture?id=${user.id}">
				<br>
				<br>
				<b>用户名：</b><s:property value="#session.user.username"/>
				<br>
				<br>
				<b>性别：</b><s:property value="#session.user.gender"/>
				<br>
				<br>
				<b>出生日期：</b><fmt:formatDate value="${sessionScope.user.birthday }" pattern="yyyy-MM-dd"/>
				<br>
				<br>
				<b>手机号码：</b><s:property value="#session.user.telephone"/>
				<br>
				<br>
				<b>邮箱：</b><s:property value="#session.user.email"/>
				<br>
				<br>
				<b>地址：</b><s:property value="#session.user.address"/>
				<br>
				<br>
				<b>注册时间：</b><fmt:formatDate value="${sessionScope.user.regist_time }" pattern="yyyy-MM-dd HH:mm"/>
				<br>
				<br>
				<b>贴子数：</b><s:property value="#session.user.article_num"/>
				<br>
				<br>
				<b>权限：</b><s:if test="#session.user.privilege==1">普通用户</s:if>
					<s:if test="#session.user.privilege!=1">
						<s:if test="#session.user.privilege!=3">
							版主
						</s:if>
					</s:if>
					<s:if test="#session.user.privilege==3">管理员</s:if>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>