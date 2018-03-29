<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div class="title">
		修改信息
	</div>
	<div class="form_div">
	<s:iterator value="actionMessages" var="message" status="s">
		<div style="color:#FF0000"><s:property value="#s.count"/>:<s:property value="#message"/></div>
	</s:iterator>
	<s:debug></s:debug>
		<s:form id="regist_form" action="userAction_edit" namespace="/user" enctype="multipart/form-data" method="post">
			<input type="hidden" value="${id }" name="id">
			<table>
				<tr>
					<td>
						用户名:
					</td>
					<td>
						<s:textfield name="username" label="用户名" theme="simple"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						密码:
					</td>
					<td>
						<s:textfield name="password" label="密码" theme="simple"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						性别:
					</td>
					<td>
						<s:radio name="gender" list="#{'男':'男','女':'女'}" value="gender" label="性别" theme="simple"></s:radio>
					</td>
				</tr>
				<tr>
					<td>
						出生日期:
					</td>
					<td>
						<s:textfield value="%{formatBirthday}" name="birthday" label="出生日期" theme="simple"></s:textfield>
					</td>
				</tr>
				</tr>
				<tr>
					<td>
						手机号码:
					</td>
					<td>
						<s:textfield name="telephone" label="手机号码" theme="simple"></s:textfield>
					</td>
				</tr>
				</tr>
				<tr>
					<td>
						邮箱:
					</td>
					<td>
						<s:textfield name="email" label="邮箱" theme="simple"></s:textfield>
					</td>
				</tr>
				</tr>
				<tr>
					<td>
						地址:
					</td>
					<td>
						<s:select id="selectProvince" name="addr" list="provinces" listKey="value" listValue="value" headerKey="0" headerValue="--省份--" label="地址" theme="simple"></s:select>
						<select id="selectCity" name="addr" >
							<option value="0">--城市--</option>
						</select>
					</td>
				</tr>
				</tr>
				<tr>
					<td>
						头像:
					</td>
					<td>
						<img style="width: 100px;" src="${pageContext.request.contextPath }/user/userAction_showSculpture?id=${user.id}">
						<a href="javascript:changeSculpture()">更换</a>
					</td>
					<td>
						<div id="userSculptureDiv" hidden="true">
							<s:file id="userSculpture" name="userSculpture" label="头像" theme="simple"></s:file>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:submit value="修改"></s:submit>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
