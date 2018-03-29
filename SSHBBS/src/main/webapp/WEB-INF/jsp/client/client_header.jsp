<!-- 中文乱码解决办法：contentType="text/html; charset=UTF-8" -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	<script type="text/javascript"> 
		var editor = null;
	  	function save(){
	        editor.updateElement(); //非常重要的一句代码
	        //前台验证工作
	        //提交到后台
	    }
	  	function initCKEditor(){
			//参数‘content’是textarea元素的name属性值，而非id属性值
	  		editor = CKEDITOR.replace( 'content', {
	            customConfig:'${pageContext.request.contextPath }/ckeditor/myconfig.js',
	            on: {
	                instanceReady: function( ev ) {
	                    this.dataProcessor.writer.setRules( 'p', {
	                        indent: false,
	                        breakBeforeOpen: false,   //<p>之前不加换行
	                        breakAfterOpen: false,    //<p>之后不加换行
	                        breakBeforeClose: false,  //</p>之前不加换行
	                        breakAfterClose: false    //</p>之后不加换行7
	                    });
	                }
	            }
	        });
	  	}
		$(function(){
			
		  $("#article").datagrid({
		  		columns:[[
					{"checkbox":true},
					{title:"题目",width:600,field:'title'},
					{title:"用户",width:100,field:'username'},
					{title:"发布时间",width:100,field:'publish_time'},
					{title:"点击数",width:100,field:'click_num'}
				]
				],
				fitColumns:true,
				url:"",
				toolbar:
					[{
						text:"发帖",
						iconCls: 'icon-edit',
						handler: function(){
							
							alert('发帖按钮');
						}
					}]
				,
				onLoadSuccess:function(){
				},
				loadMsg:"数据正在加载中....",
				method:"POST",
				pagination:true,
				rownumbers:true,
				singleSelect:true,
				striped:true
		  });
		  //为啥放开头，datagrid无法执行？
		  initCKEditor();
		  
		});		
	</script> 
	<style type="text/css">
		/* header.jsp */
		*{
			text-align: center;
		}
		div{
			margin: 0 auto;
		}
		#header{
			width: 100%;
			height: 50px;
			position:relative;
			top:0;
			background-color: #1E90FF
		}
		.header_content{
			height:100%;
			width:33%;
			float: left;
			color: #FDF5E6;
		}
		.header_content a{
			margin-right:30px;
			float: right;
			color: #FDF5E6;
		}
		.header_content input,a{
			margin-top: 15px;;
		}
		#bbs_pic{
			font-size: 40px;
		}
		/* homepage.jsp */
		#category{
			border:1px solid;
			width: 1000px;
			height: 400px;
			margin-top: 20px;
		}
		#add_category{
			float: right;
			width: 1000px;
			font-size: 20px;
		}
		.category_content{
			border: 1px solid;
			width: 30%;
			height: 80%;
			margin-left: 26px;
			margin-top:40px;
			float: left; 
		}
		.category_img{
			border:1px solid;
			width: 100%;
			height: 80%;
		}
		.category_article_num{
			border: 1px solid;
			width: 100%;
			height: 20%;
		}
		/* 所有title */
		.title{
			width: 150px;
			height: 30px;
			font-size: 28px;
			font-style:oblique;
			color: #FDF5E6;
			background-color:#1E90FF;
			margin-top: 20px;
			
		}
		/* footer.jsp */
		#footer{
			width: 100%;
			height: 50px;
			font-size: 28px;
			background-color:#1E90FF;
			margin-top: 20px;
			bottom:0;  
			overflow:hidden;
		}
		/* regist.jsp */
		/* login.jsp */
		/* add_category.jsp */		
		/* edit_category.jsp */		
		.form_div{
			width: 500px;
			margin-top: 20px;
		}
		#regist_form{
			margin-left: 100px;
		}
		#regist_form input{
			margin-top: 20px;
		}
		#login_form{
			margin-left: 100px;
		}
		#login_form input{
			margin-top: 20px;
		}
		#ac_form{
			margin-left: 100px;
		}
		#ac_form input{
			margin-top: 20px;
		}
		#ec_form{
			margin-left: 100px;
		}
		#ec_form input{
			margin-top: 20px;
		}
		
		/* category.jsp */
		#category_info{
			width: 1000px;
			height: 200px;
			background-color: #1E90FF;
			color: #FDF5E6;
			margin-top: 30px;
			font-size: 100px;
		}
		#category_operation{
			float: right;
			font-size: 20px;
		}
		/*
		#article_header{
			width: 1000px;
			height: 30px;
			border: 1px solid;
			margin-top: 30px;
		}
		*/
		#article_list{
			border: 1px solid;
			float: left;
		}
		#write_article{
			border: 1px solid;
			float:right; 
		}
		#articles{
			width: 1000px;
			height: auto;
			border: 1px solid;
			margin-top: 30px;
		}
		/*
		.article{
			width: 900px;
			height: 40px;
			border: 1px solid;
			margin-top: 10px;
		}
		.article_title{
			width: 60%;
			height: 40px;
			float: left;
			font-size: 15px;
			background-color: #ADD8E6;
		}
		.article_user,.article_click_num{
			width: 20%;
			height: 40px;
			float: left;
			font-size: 15px;
			background-color: #ADD8E6;
		}
		*/
		
		/* article.jsp */
		#article_content{
			width: 1000px;
			height: 500px;
			border: 1px solid;
			margin-top: 30px;
		}
		.article_review{
			width: 1000px;
			height: 500px;
			border: 1px solid;
			margin-top: 30px;
		}
		.publisher_info{
			float: left;
			width:250px;
			height: 450px;
			border: 1px solid;
			margin-top: 10px;
			margin-left: 20px;
		}
		.article_content,.review_content{
			float: left;
			width:700px;
			height: 450px;
			border: 1px solid;
			margin-top: 10px;
			margin-left: 20px;
		}
		.article_operation,.review_operation{
			float: right;
			width:30%;
			height: 20px;
			margin-right: 20px;
			border: 1px solid;
		}
		.floor{
			width:700px;
			height: 20px;
			border: 1px solid;
			float:left;
		}
		/* writeArticle.jsp */
		#ckeditor_div{
			width: 1000px;
			height: 500px;
			margin-top: 20px;
		}
	</style>
  </head>
  
  <body>
     <div id="header">
     	<div class="header_content" id="bbs_pic">
     		BBS
     	</div>
     	<div class="header_content" id="search">
     		帖子关键字：
     		<input type="text" id="search_text">
     		<input type="button" value="搜索帖子">
     	</div>
     	<div class="header_content" id="log_reg">
     		<a href="#">注销</a>
     		<a href="regist.jsp">注册</a>
     		<a href="#">个人中心</a>
     		<a href="login.jsp">登录</a>
     	</div>
     	
     </div>

