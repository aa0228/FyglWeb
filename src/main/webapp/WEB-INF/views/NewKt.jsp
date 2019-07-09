<%--
  Created by IntelliJ IDEA.
  User: zhoupeipei
  Date: 2018/8/22
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body style="">
<jsp:include page="../../base/head.jsp"></jsp:include>
<div style="width:800px;margin-left:20%;margin-top:10px;">
    <form action="insertKtxx.do" method="post" style="padding-top:-700px;">
        <div style="width:700px;margin-top:20px;height: 50px;">
            <label style="width:50px;">部  门:</label><select name="UNIT" style="width:150px;height:27px;overflow-y:auto;">
             <c:forEach var="bumenlist" items="${bumenlist}">
                <option value="${bumenlist.DMMS}">${bumenlist.DMMS}</option>
             </c:forEach>
            </select>
            <label style="width:50px;margin-left:20px;">人  数:</label><input name="MEN_COUNT" TYPE="text"style="width:150px;"><label style="width:70px;color: red;"> 申请日期:</label><input name="SQ_DATE" TYPE="text"style="width:148px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" required>
        </div>
         <div style="width:700px;height: 50px;">
        <label style="width:50px;color: red">原告人:</label><input name="YG_MEN" TYPE="text" style="width:375px;"required><label style="width:70px;color: red;">实际日期:</label><input name="RQ" TYPE="text"style="width:149px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" required>
        </div>
        <div style="width:700px;height: 50px;">
            <label style="width:50px;">案  由:</label><input name="CASE_REASON" TYPE="text" style="width: 593px;">
        </div>
        <div style="width:700px;height: 50px;">
            <label style="width:50px;color:red;">被告人:</label><input name="BG_MEN" TYPE="text" style="width: 593px;"required>
        </div>
        <div style="width:700px;height: 50px;">
        <label style="width:50px;color:red;">法 庭:</label><select name="SJFT" style="width:150px;height:27px;overflow-y:auto;" required>
                <c:forEach var="ftlist" items="${ftlist}">
                    <option value="${ftlist.BMMC}">${ftlist.BMMC}</option>
                </c:forEach>
            </select>
        <label style="width:60px;margin-left:9px;"> 联系人：</label><input name="LX_MEN" TYPE="text" style="width:150px;">
        <label style="width:65px;color:red;"> 分钟数：</label><input name="FZ" TYPE="text" style="width:150px;"required>
        </div>
         <input type="reset"value="重置" style="margin-top:5px;margin-left:25%"><input type="submit"value="确定"style="margin-left:10%">
    </form>
</div>
<div style="margin-top:30px;margin-bottom: -15px;">
    <a href="main.do" style="margin-left:45%;">返回主页面</a>

</div>
<jsp:include page="../../base/foot.jsp"></jsp:include>
</body>
</html>
