<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html>
<head>
<title>JSP</title>
</head>
<body>
<%=request.getParameter("option") %>
<%=request.getAttribute("serachname") %>
</body>
</html>