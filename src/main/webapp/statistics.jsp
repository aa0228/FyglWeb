<%--
  Created by IntelliJ IDEA.
  User: zhoupeipei
  Date: 2018/8/28
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-change.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/My97DatePicker/skin/WdatePicker.css" >
<script language="javascript" type="text/javascript"  src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
    searchAbout=function(){
        var year = $("#wdatep").val();
        if(year=='')
        {
            alert("请输入检索年份");
        }
        else{
            window.location.href = "StatisticsData.do?year=" + year;
        }
    }
</script>
<html>
<head>
    <title>统计表</title>
</head>
<body>
<jsp:include page="base/head.jsp"></jsp:include>
<div style="width:500px;height:270px;margin-left: 30px;margin-top:40px;">
    <label style="font-size:15px;">请选择时间:</label>
    <input type="text" class="Wdatep"  id="wdatep" name="year" value="${year}"  onfocus="WdatePicker({dateFmt:'yyyy'})"/>
    <button class="searchbtn"  onclick="searchAbout()" style="margin-left:20px;">查询</button></th>
</div>

</div>


<jsp:include page="base/foot.jsp"></jsp:include>
</body>
</html>
