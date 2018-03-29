<!-- 难点4：中文乱码解决办法：contentType="text/html; charset=UTF-8" -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎来到BBS</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- jsp下引用文件要用项目相对路径，绝对路径定位不到 -->
	<!--jquery 的主文件...-->
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<!--jquery  easyui 的主文件...-->
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>
	<!--jQuery 的主样式文件...-->
	<!--<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/js/themes/default/easyui.css">-->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/js/themes/bootstrap/easyui.css">
	<!--jQuery 的图标文件...-->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/js/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/test_jsp/client.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/test_jsp/client.css">
	
  </head>
  
  <body>
     <div id="header">
     	<div class="header_content" id="bbs_pic"
     	onmouseover="this.style.cursor='hand'"   
     	onmouseout="this.style.cursor='normal'" 
     	onclick="window.location.href='${pageContext.request.contextPath }/category/categoryAction_homepage.action'">
     		BBS
     	</div>
     	<div class="header_content" id="search">
     		<s:form action="articleAction_search.action" namespace="/article">
	     		帖子关键字：
	     		<input type="text" name="keyword" id="search_text">
	     		<input type="submit" value="搜索帖子">
     		</s:form>
     	</div>
     	<div class="header_content" id="log_reg">
     		<s:a action="userAction_logout" namespace="/user">注销</s:a>
     		<a href="${pageContext.request.contextPath }/user/userAction_registUI">注册</a>
     		<s:if test="#session.user!=null">
	     		<a href="${pageContext.request.contextPath }/test_jsp/client/userInfo.jsp">个人中心</a>
	     		<p>欢迎您：<s:property value="#session.user.username"/></p>
     		</s:if>
     		<s:if test="#session.user==null">
     			<a href="${pageContext.request.contextPath }/test_jsp/client/login.jsp">登录</a>
     		</s:if>
     	</div>
     	
     </div>

