<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>展示所有员工</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="<c:url value='/demo/css/amazeui.min.css'/>" />
    <link rel="stylesheet" href="<c:url value='/demo/css/amazeui.datatables.min.css'/>" />
    <link rel="stylesheet" href="<c:url value='/demo/css/app.css'/>">
    <script src="<c:url value='/demo/js/jquery.min.js'/>"></script>
</head>
<body>
    <div class="row">
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
            <div class="widget am-cf am-u-sm-centered">
                <div class="widget-head am-cf am-u-sm-centered">
                    <div class="widget-title  am-cf">员工列表</div>
                </div>

                <!--条件查询-->
                <div class="am-u-sm-8 am-u-sm-centered">
                    <form id="myForm" action="<c:url value='/emp/selectEmpAll'/>" method="post" >
                        姓名：<input type="text"  class="am-round" name="name" value="${params.name}" size="6"/>
                        地址：<input type="text"  class="am-round" name="address" value="${params.address}" size="6"/>
                        生日：<input type="date"  class="am-round" name="birthdayFrom" value="${params.birthdayFrom}" size="6"/>
                        -<input type="date"  class="am-round" name="birthdayTo" value="${params.birthdayTo}" size="6"/>
                        <input id="pn" type="hidden" name="pageNum" value="${params.pageNum}" />
                        <input id="ps" type="hidden" name="pageSize" value="${params.pageSize}" />
                        <input type="submit" class="am-btn am-btn-default am-btn-primary" value="搜索" />
                        <a href="<c:url value='/emp/savePageEmp'/>" class="am-btn am-btn-default am-btn-success"><span class="am-icon-plus"></span>添加</a>
                    </form>
                </div>

                <div class="am-u-sm-12 am-u-sm-centered" style="text-align:center">
                <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                    <tr>
                        <th>编号</th>
                        <th>姓名</th>
                        <th>年龄</th>
                        <th>地址</th>
                        <th>生日</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${pageInfo.list}" var="emp" >
                        <tr>
                            <td>${emp.id}</td>
                            <td>${emp.name}</td>
                            <td>${emp.age}</td>
                            <td>${emp.address}</td>
                            <td>
                                <fmt:formatDate value="${emp.birthday}" pattern="yyyy-MM-dd" />
                            </td>
                            <td>
                                <div class="tpl-table-black-operation">
                                    <a href="${pageContext.request.contextPath}/emp/updatePageEmp/${emp.id}">
                                        <i class="am-icon-pencil"></i> 修改
                                    </a>
                                    <a href="${pageContext.request.contextPath}/emp/deleteEmpById/${emp.id}">
                                        <i class="am-icon-trash"></i> 删除
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </div>
                <!--分页-->
                <div class="am-u-sm-8 am-u-sm-centered" style="text-align:center">
                    <a class="am-btn am-btn-warning am-btn-sm am-round" href="javascript:toPage(1);">首页</a>
                    <a class="am-btn am-btn-warning am-btn-sm am-round" href="javascript:toPage(${pageInfo.pageNum-1});">上一页</a>
                    <a class="am-btn am-btn-warning am-btn-sm am-round" href="javascript:toPage(${pageInfo.pageNum+1});">下一页</a>
                    <a class="am-btn am-btn-warning am-btn-sm am-round" href="javascript:toPage(${pageInfo.pages});">尾页</a>

                    <select id="pageSize" onchange="toPage(1)">
                        <option value="2"  <c:if test="${pageInfo.pageSize==2}">selected</c:if> >2</option>
                        <option value="4"  <c:if test="${pageInfo.pageSize==4}">selected</c:if>  >4</option>
                        <option value="6"  <c:if test="${pageInfo.pageSize==6}">selected</c:if>  >6</option>
                        <option value="8"  <c:if test="${pageInfo.pageSize==8}">selected</c:if>  >8</option>
                        <option value="10" <c:if test="${pageInfo.pageSize==10}">selected</c:if> >10</option>
                    </select>
                    第${pageInfo.pageNum}页/共${pageInfo.pages}页
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
    function toPage(pageNum) {
        //获取每天显示的条数
        var ps = $("#pageSize").val();
        //给pageSize设值
        $("#ps").val(ps);
        //给pageNum设值
        $("#pn").val(pageNum);
        //提交表单
        $("#myForm").submit();
    }
</script>
</html>
