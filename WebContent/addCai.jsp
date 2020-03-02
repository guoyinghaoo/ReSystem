<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="AdminServlet" method="POST">
		
		<input type="hidden" name="currentPage" value="<%=request.getParameter("currentPage")%>">
		<input type="hidden" name="action" value="AddCai">
		菜名：<input type="text" name="cainame">
		<br>
		菜价：<input type="text" name="caiprice">
		<br>
		描述：<input type="text" name="miaoshu">
		图片：<input type="text" name="img">
		<input type="submit" value="添加">
</form>
</body>
</html>