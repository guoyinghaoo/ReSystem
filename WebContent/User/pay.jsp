<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   订单用户：<%=session.getAttribute("username")%><br>
   订单总价：${allprice}
	<form action="OrderServlet" method="Post">
		<input type="hidden" name="allprice" value="${allprice}" /> 
		<input type="hidden" name="username"value="<%=session.getAttribute("username")%>" />
		<input type="hidden"name="situation" value="已付款"> 
		<input type="submit"name="button" id="button" value="去付款" />
	</form>
</body>
</html>