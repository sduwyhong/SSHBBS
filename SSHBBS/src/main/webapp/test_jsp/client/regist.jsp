<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<div class="title">
		用户注册
	</div>
	<div class="form_div">
	<s:iterator value="actionMessages" var="message" status="s">
		<div style="color:#FF0000"><s:property value="#s.count"/>:<s:property value="#message"/></div>
	</s:iterator>
		<s:form id="regist_form" action="userAction_regist" namespace="/user" enctype="multipart/form-data" method="post">
			<table>
				<tr>
					<td>
						用户名:
					</td>
					<td>
						<s:textfield id="usernameText" name="username" label="用户名" theme="simple"></s:textfield>
					</td>
				</tr>
					<tr>
					<td>
						密码:
					</td>
					<td>
						<s:password id="passwordText" name="password" label="密码" theme="simple"></s:password>
					</td>
				</tr>
					<tr>
					<td>
						密码确认:
					</td>
					<td>
						<s:password id="rePasswordText" name="repassword" label="密码确认" theme="simple"></s:password>
					</td>
				</tr>
					<tr>
					<td>
						性别:
					</td>
					<td>
						<s:radio name="gender" list="#{'男':'男','女':'女'}" value="'男'" label="性别" theme="simple"></s:radio>
					</td>
				</tr>
					<tr>
					<td>
						出生日期:
					</td>
					<td>
						<s:textfield id="dateText" name="birthday" label="出生日期" theme="simple"></s:textfield>
					</td>
				</tr>
				</tr>
					<tr>
					<td>
						手机号码:
					</td>
					<td>
						<s:textfield id="telephoneText" name="telephone" label="手机号码" theme="simple"></s:textfield>
					</td>
				</tr>
				</tr>
					<tr>
					<td>
						邮箱:
					</td>
					<td>
						<s:textfield id="emailText" name="email" label="邮箱" theme="simple"></s:textfield>
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
						<s:file id="userSculpture" name="userSculpture" label="头像" theme="simple"></s:file>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" id="regist_submit" value="注册" disabled="disabled" onclick="submitRegistForm()">
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
