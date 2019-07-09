<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增法庭</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body style="background-image:url(image/background-new.png)">
    <jsp:include page="base/head.jsp"></jsp:include>  
    <div style="width:600px;margin-left:30%;margin-top:90px;padding-bottom: 100px;">
    <form action="insertFtxx.do" method="post" style="padding-top:-700px;">
     


       法庭名称:<input name="BMMC" type="text"><br><br>

        备注:<br>
        <textarea name="BZ" row="10"cols="50"></textarea><br> 
        
       <input type="reset"value="重置" style="margin-top:35px;margin-left:15%"><input type="submit"value="确定"style="margin-top:35px;margin-left:10%">  
   </form>  
   </div>
     <jsp:include page="base/foot.jsp"></jsp:include>  
  </body>
</html>
