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
<jsp:include page="../../base/head.jsp"></jsp:include>
<div style="width:500px;height:50px;margin-left: 30px;margin-top:40px;overflow-y: auto;">
    <label style="font-size:15px;">请选择时间:</label>
    <input type="text" class="Wdatep"  id="wdatep" name="year" value="${year}"  onfocus="WdatePicker({dateFmt:'yyyy'})"/>
    <button class="searchbtn"  onclick="searchAbout()" style="margin-left:20px;">查询</button></th>
</div>

<div style="width:1000px;min-height:310px;max-height:800px;margin-top:20px;text-align:center;margin-left:120px">
    <label style="font-size: 18px;">法庭使用情况统计表</label>
    <table border="1" cellpadding="0" style="white-space: normal;width:100%;">
        <colgroup>
            <col width="140">
            <col width="70">
            <col width="70">
            <col width="70">
            <col width="70">
            <col width="70">
            <col width="70">
            <col width="70">
            <col width="70">
            <col width="70">
            <col width="70">
            <col width="70">
            <col width="70">
        </colgroup>
        <thead>
        <tr>
        <th>单位\月份</th>
        <th>一月</th>
        <th>二月</th>
        <th>三月</th>
        <th>四月</th>
        <th>五月</th>
        <th>六月</th>
        <th>七月</th>
        <th>八月</th>
        <th>九月</th>
        <th>十月</th>
        <th>十一月</th>
        <th>十二月</th>
        </tr>
        </thead>
    </table>
    <div style="overflow-y: scroll;max-height: 700px;width:1017px;min-height:300px;">
        <table border="1" cellpadding="0" style="border-top:0;white-space: normal;width:100%;min-height:300px;">
            <colgroup>
                <col width="140">
                <col width="70">
                <col width="70">
                <col width="70">
                <col width="70">
                <col width="70">
                <col width="70">
                <col width="70">
                <col width="70">
                <col width="70">
                <col width="70">
                <col width="70">
                <col width="70">
            </colgroup>
        <tbody style="scroll-y:auto;">
        <c:forEach var="U" items="${st}"  >
        <tr>
            <td>${U.DMMS}</td>
            <td>${U.JAN}</td>
            <td>${U.FEB}</td>
            <td>${U.MAR}</td>
            <td>${U.APR}</td>
            <td>${U.MAY}</td>
            <td>${U.JUN}</td>
            <td>${U.JULY}</td>
            <td>${U.AUG}</td>
            <td>${U.SEP}</td>
            <td>${U.OCT}</td>
            <td>${U.NOV}</td>
            <td>${U.DEC}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
</div>
<div >
   <button style="margin-left:45%;margin-top:20px;"><a href="main.do">返回主页面</a></button>
</div>
<div>
<jsp:include page="../../base/foot.jsp"></jsp:include>
</div>
</body>
</html>
