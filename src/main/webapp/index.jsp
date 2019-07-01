<%--
  Created by IntelliJ IDEA.
  User: zzy
  Date: 2019/6/5
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>First Page</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/emp/selectEmpAll">展示所有员工</a>
    <a href="${pageContext.request.contextPath}/houseRent/admin.html">房屋后台管理系统</a>
    <a href="${pageContext.request.contextPath}/houseRent/html/foregroundPage/login.htm">房屋出租网前台登录</a>
</body>
</html>
