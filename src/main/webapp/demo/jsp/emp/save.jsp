<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加员工</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/emp/saveEmp" method="post">
    <p>姓名：<input type="text" name="name"  /></p>
    <p>年龄：<input type="text" name="age"  /></p>
    <p>地址：<input type="text" name="address" /></p>
    <p>生日：<input type="date" name="birthday"/></p>
    <p><input type="submit" value="添加" /></p>
</form>
</body>
</html>
