<%@ include file="client_header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	
	<div id="article_content">
		<div class="publisher_info">publisher_info</div>
		<div class="article_content">article_content
			<div class="article_operation">
				<a class="operation" id="setExcellence" href="#">setExcellence</a>
				<a class="operation" id="delete" href="#">delete</a>
				<a class="operation" id="edit" href="#">edit</a>
			</div>
		</div>
	</div>
	<div class="article_review">
		<div class="publisher_info">publisher_info</div>
		<div class="review_content">article_content
			<div class="review_operation">
				<a class="operation" id="delete" href="#">delete</a>
			</div>
			<div class="floor">
				X楼
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
