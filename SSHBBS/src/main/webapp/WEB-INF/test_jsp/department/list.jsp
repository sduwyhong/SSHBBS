<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<s:debug></s:debug>
	<s:a action="DepartmentAction_addUI">add</s:a>
	<table border="1" align="center">
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>描述</td>
			<td>操作</td>
		</tr>
		<s:iterator value="#list">
			<tr>
				<td>
					<s:property value="did"/>
				</td>
				<td>
					<s:property value="name"/>
				</td>
				<td>
					<s:property value="description"/>
				</td>
				<td>
					<s:a action="DepartmentAction_editUI">
						编辑
						<s:param name="did"><s:property value="did" /></s:param>
					</s:a>
				</td>
			</tr>
		</s:iterator>
	</table>
  </body>
</html>
