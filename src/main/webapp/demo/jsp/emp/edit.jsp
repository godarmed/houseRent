<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>员工修改</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/emp/updateEmp" method="post">
    <input type="hidden" name="id" value="${emp.id}" />
    <p>姓名：<input type="text" name="name" value="${emp.name}" /></p>
    <p>年龄：<input type="text" name="age" value="${emp.age}" /></p>
    <p>地址：<input type="text" name="address" value="${emp.address}" /></p>
    <p>生日：<input type="date" name="birthday" value="<fmt:formatDate value='${emp.birthday}' pattern='yyyy-MM-dd'/>" /></p>
    <p><input type="submit" value="保存" /></p>
</form>
</body>
</html>
