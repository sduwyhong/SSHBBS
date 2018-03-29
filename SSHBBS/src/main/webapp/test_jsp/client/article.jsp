<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<s:debug></s:debug>
	<div id="article_content">
		<div class="publisher_info">
		<!-- user信息要从后台搜索后传递到前台 -->
			<div class="user_sculpture">
				<img src="${pageContext.request.contextPath }/user/userAction_showSculpture?id=<s:property value="article.user.id"/>">
			</div>
			<div class="user_info">
				<b style="color:#DC143C ">楼主</b>
				<br>
				<br>
				<b>用户名：</b><s:property value="article.user.username"/>
				<br>
				<br>
				<b>性别：</b><s:property value="article.user.gender"/>
				<br>
				<br>
				<b>出生日期：</b><fmt:formatDate value="${article.user.birthday }" pattern="yyyy-MM-dd"/>
				<br>
				<br>
				<b>注册时间：</b><fmt:formatDate value="${article.user.regist_time }" pattern="yyyy-MM-dd"/>
				<br>
				<br>
				<b>贴子数：</b><s:property value="article.user.article_num"/>
			</div>
		</div>
		
		<div class="article_content">
			
			<div class="article_operation">
				<a class="operation" id="setExcellence" href="${pageContext.request.contextPath }/user/userAction_setExc?id=<s:property value="article.id"/>">加精</a>
				<a class="operation" id="delete" href="${pageContext.request.contextPath }/article/articleAction_delete?id=<s:property value="article.id"/>">删除</a>
				<a class="operation" id="edit" href="${pageContext.request.contextPath }/article/articleAction_editUI?id=<s:property value="article.id"/>">编辑</a>
			</div>
			<!-- 将string转换成html代码 -->
			<div class="aticle_content_title">
				<b><s:property value="article.title"/></b>
				<font style="color: red"><s:if test="article.Excellent">(精)</s:if></font>
				<div class="publish_time">
					<fmt:formatDate value="${article.publish_time }" pattern="yyyy-MM-dd HH:mm"/>
				</div>
			</div>
			<div class="article_text">
				<s:property value="article.content" escapeHtml="false"/>
			</div>
		</div>
	</div>
	
	<div style="width: 900px;height:0px;border: solid #C0C0C0; "></div>
	
	<!-- 评论区 -->
	<s:iterator value="reviews" var="review" status="s">
		<!-- OGNL表达式可以调用对象的方法，如各种get方法 -->
		<!-- 定义了别名，要用#别名取元素 -->
		<!-- 若没定义别名，则直接写要取的元素名
			#review.user.username=user.username
		 -->
		<div class="article_review">
			<div class="publisher_info">
			<!-- user信息要从后台搜索后传递到前台 -->
				<div class="user_sculpture">
					<img src="${pageContext.request.contextPath }/user/userAction_showSculpture?id=<s:property value="#review.user.id"/>">
				</div>
				<div class="user_info">
					<br>
					<b>用户名：</b><s:property value="#review.user.username"/>
					<br>
					<br>
					<b>性别：</b><s:property value="#review.user.gender"/>
					<br>
					<br>
					<b>出生日期：</b><fmt:formatDate value="${review.user.birthday }" pattern="yyyy-MM-dd"/>
					<br>
					<br>
					<b>注册时间：</b><fmt:formatDate value="${review.user.regist_time }" pattern="yyyy-MM-dd"/>
					<br>
					<br>
					<b>贴子数：</b><s:property value="#review.user.article_num"/>
				</div>
			</div>
			<div class="review_content">
				<div class="review_operation">
					<a class="operation" id="delete" href="${pageContext.request.contextPath }/review/reviewAction_delete?id=<s:property value="#review.id"/>">删除</a>
				</div>
				<div class="floor">
					<b>
					<s:if test="#s.count==1">
						沙发
					</s:if>
					<s:if test="#s.count==2">
						板凳
					</s:if>
					<s:if test="#s.count==3">
						地板
					</s:if>
					<s:if test="#s.count!=1">
						<s:if test="#s.count!=2">
							<s:if test="#s.count!=3">
						<s:property value="#s.count"/>楼
							</s:if>
						</s:if>
					</s:if>
					</b>
					<div class="publish_time">
						<fmt:formatDate value="${review.publish_time }" pattern="yyyy-MM-dd HH:mm"/>
					</div>
				</div>
				<div class="review_text">
				<s:property value="#review.content" escapeHtml="false"/>
				</div>
			</div>
		</div>
	</s:iterator>
	<div class="title">
		回帖
	</div>
	<div id="ckeditor_div">
		<form id="detailForm" action="${pageContext.request.contextPath }/review/reviewAction_publish" method="post" style="margin-top:20px">
		    <textarea id="content" name="content"></textarea>
		    <input type="button" value="发布" id="save" />
		</form>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
