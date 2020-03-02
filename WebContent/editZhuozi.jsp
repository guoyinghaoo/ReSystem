<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<font color="blue">菜单列表>选择桌号</font>
	<br>
	<br>
	<c:choose>
	<c:when test="${dindan.zhuozi != null}">
			<form action="AdminServlet" method="POST">
				<input type="hidden" name="uid" value="${dindan.did}"> <input
					type="hidden" name="currentPage" value="${currentPage }"> <input
					type="hidden" name="action" value="editDindan"> <input
					type="hidden" name="username" value="${dindan.username}"> <br>
				下单用户名：${dindan.username} <br> 修改订单状态为：<select name="situation">
					<option value="未配送">未配送</option>
					<option value="已配送">已配送</option>
                    <option value="已付款">已付款</option>  
				</select> <input type="submit" value="确定">
			</form>

		</c:when>
	
	<c:otherwise>
	
	<form action="AdminServlet" method="POST">
		<input type="hidden" name="uid" value="${dindan.did}">
		<input type="hidden" name="currentPage" value="${currentPage }">
		<input type="hidden" name="action" value="editDindan">
		<input type="hidden" name="username" value="${dindan.username}">
		<br>
		下单用户名：${dindan.username}
		<br>
		选择桌号：<input type="text" name="zhuozi">
		<input type="submit" value="确定">
	</form>
	
		
	</c:otherwise>
</c:choose>
	
</body>
</html>