<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css" >

<script language="text/javascript" type="text/javascript"  src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript">
    SelectAbout=function(){
        var startTime = $("#wdate1").val();
        var endTime = $("#wdate2").val();
        if(startTime==''||endTime=='')
        {
            alert("请输入检索日期");
        }
        else{
            window.location.href = "showSomeKtxx.do?startTime=" + startTime
                + "&endTime=" + endTime;
        }
    }

    getExport= function(){
            var startTime = $("#wdate1").val();
            var endTime = $("#wdate2").val();
            if(startTime==''||endTime=='')
            {
                alert("请输入检索日期");
            }
            else{
                window.location.href = "exportKtxx.do?startTime=" + startTime
                    + "&endTime=" + endTime;
            }
        }

    getPrint=function(){
        window.print();
    }

    JumpTo=function(){
        var Number=$("#jumpNum").val();
        var totalPage=$(".totalPage").val();
        var startTime = $("#wdate1").val();
        var endTime = $("#wdate2").val();
        if(Number==''){
            alert("请输入跳转页面");
        }
        else if(Number>totalPage){
            alert("不存在该跳转页面");
        }
        else{
            window.location.href = "showSomeKtxx.do?pageNum="+Number+"&startTime="+startTime+"&endTime="+endTime;
        }
    }
    getGenerate=function(){
        var startTime = $("#wdate1").val();
        var endTime = $("#wdate2").val();
        if(startTime==''||endTime=='')
        {
            alert("请输入检索日期");
        }
        else{
            window.location.href = "noticeKtxx.do?startTime=" + startTime
                + "&endTime=" + endTime;
        }
    }
    Goback=function(){
        window.location.href = "main.do";
    }


    //修改的弹框功能
    updateInfo=function (AJXH,SQBH){
        $.ajax({
            url : "loadInfoDialog.json",
            processData : true,
            data : {
                AJXH : AJXH,
                SQBH:SQBH
            },
            type:"POST",
            dataType : "json",//json--返回json数据类型；xml--返回xml
            success : function(msg) {
                var d1=new Date(msg.SQ_DATE);
                var d2=new Date(msg.RQ);
                var d3=d1.getFullYear() + '-' + (d1.getMonth() + 1) + '-' + d1.getDate() + ' ' + d1.getHours() + ':' + d1.getMinutes() + ':' + d1.getSeconds();
                var d4=d2.getFullYear() + '-' + (d2.getMonth() + 1) + '-' + d2.getDate() + ' ' + d2.getHours() + ':' + d2.getMinutes() + ':' + d2.getSeconds();
                $('#ajxh').val(msg.AJXH);
                $('#sqbh').val(msg.SQBH);
                $('#dmms').val(msg.DMMS);
                $('#men_count').val(msg.MEN_COUNT);
                $('#sq_date').val(d3);
                $('#yg_men').val(msg.YG_MEN);
                $('#rq').val(d4);
                $('#case_reason').val(msg.CASE_REASON);
                $('#bg_men ').val(msg.BG_MEN);
                $('#sjft').val(msg.SJFT);
                $('#lx_men').val(msg.LX_MEN);
                $('#fz').val(msg.FZ);
                $("#KtxxDialog").dialog("open");
            },
            error : function(jqXHR, textStatus, errorThrown) {
            },
        });
    }

    $(function() {
        $("#KtxxDialog").dialog({
            autoOpen: false,
            modal : true,    // 设置为模态对话框
            resizable : false,
            width : 800,   //弹出框宽度
            height : 400,   //弹出框高度
        });
        $("#back-button").click(function(){
            $("#KtxxDialog").dialog("close");
        });
    });

</script>


<div id="KtxxDialog"  title="信息修改" style="z-index:999;">
    <form id="KtxxForm" name="KtxxForm" method="post" action="updateKtxx.do" style="margin-left:50px;padding-top: 50px;">
       <table style="width:100%">
           <div>
               <input name="AJXH" TYPE="text" style="width:150px;opacity: 0;" value="${U.AJXH}" id="ajxh">
               <input name="SQBH" TYPE="text" style="width:150px;opacity: 0;" value="${U.SQBH}" id="sqbh">
           </div>
           <div style="margin-top:-40px;margin-bottom:15px;">
               <label style="width:50px;">部门:</label><input name="UNIT" TYPE="text" style="width:150px;"value="${U.DMMS}" id="dmms">  <label style="width:50px;">人 数：</label><input name="MEN_COUNT" TYPE="text" style="width:150px;" value="${U.MEN_COUNT}" id="men_count"><label style="width:70px;color: red;"> 申请日期：</label><input name="SQ_DATE" id="sq_date"  TYPE="text" style="width:150px;" value="${U.SQ_DATE}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
           </div>
           <div style="width:700px;height: 50px;">
               <label style="width:70px;color: red">原告人：</label><input name="YG_MEN" id="yg_men" TYPE="text" style="width:334px;" value="${U.YG_MEN}"><label style="width:70px;color: red;">实际日期：</label><input name="RQ" id="rq"  TYPE="text"style="width:150px;" value="${U.RQ}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
           </div>
           <div style="width:700px;height: 50px;">
               <label style="width:50px;">案 由</label>：<input name="CASE_REASON" id="case_reason"  TYPE="text" style="width: 560px;" value="${U.CASE_REASON}">
           </div>
           <div style="width:700px;height: 50px;">
               <label style="width:70px;color:red;">被告人：</label><input name="BG_MEN" id="bg_men "TYPE="text" style="width: 560px;" value="${U.BG_MEN}">
           </div>
           <div style="width:700px;height: 50px;">
               <label style="width:50px;color:red;">法  庭：</label><input name="SJFT" id="sjft" TYPE="text" style="width:150px;" value="${U.SJFT}">
               <label style="width:70px;"> 联系人：</label><input name="LX_MEN" id="lx_men"TYPE="text" style="width:150px;" value="${U.LX_MEN}">
               <label style="width:70px;color:red;"> 分钟数：</label><input id="fz" name="FZ" TYPE="text" style="width:130px;" value="${U.FZ}">
           </div>
           <input id="back-button" type="submit"value="提交"style="margin-left:40%">

       </table>
    </form>

</div>

  <div style="width:100%;height:100px;">
   <div>
   <table>
    <tbody>
    <tr style="height: 50px;">
    <th style="width: 300px;padding-right: 20px;"><span style="font-size:18px;color:#795548;">开始时间</span><input type="text" class="Wdate"  id="wdate1"name="startTime" value="${startTime}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="border-radius:3px;height: 30px;"/></th>
    <th style="width: 280px;"><span style="font-size:18px;color:#795548;">结束时间</span><input type="text" class="Wdate" id="wdate2" name="endTime" value="${endTime}"onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="border-radius:3px;height: 30px;"/></th>
    <th><img class="selectbtn "src="${pageContext.request.contextPath}/image/select.png" alt="select"  onclick="SelectAbout()" style="width:60px;height:35px;margin-top:-5px;"></th>
    </tr>
    </tbody>
    </table>
    </div>
    <div>
    <img src="${pageContext.request.contextPath}/image/export.png" alt="export" class="exportbtn" onclick="getExport()" style="height: 30px;width:105px;">
    <img src="${pageContext.request.contextPath}/image/notice.png" alt="notice" class="generatebtn" onclick="getGenerate()" style="height: 30px;width:105px;margin-left: 20px;">
    <img src="${pageContext.request.contextPath}/image/print.png" alt="print" class="printbtn" onclick="getPrint()" style="height: 30px;width:65px;margin-left: 20px;">
    <img src="${pageContext.request.contextPath}/image/fanhui.png" alt="fanhui" onclick="Goback()" style="height: 30px;width:65px;float: right;">
    </div>

  </div>
  
  <div style="height:523px;width:100%; overflow-x: auto;overflow-y:hidden;">
   <table  border="1" cellpadding="0" style="white-space: normal;width:1460px;">
       <thead>
        <tr>
            <th style="width:80px!important;text-align: center">部门</th>
            <th style="width:200px!important;text-align: center">原告人</th>
            <th style="width:200px!important;text-align: center">案由</th>
            <th style="width:200px!important;text-align: center">被告人</th>
            <th style="width:80px!important;text-align: center">是否安排</th>
            <th style="width:80px!important;text-align: center">联系人</th>
            <th style="width:160px!important;text-align: center">申请时间</th>
            <th style="width:160px!important;text-align: center">实际日期</th>
            <th style="width:80px!important;text-align: center">实际法庭</th>
            <th style="width:40px!important;text-align: center">分钟</th>
            <th style="width:80px!important;text-align: center">操作</th>
        </tr>
       </thead>
     <tbody>
       <c:forEach var="U" items="${requestScope.pageBean.list}"  >
      <form action="UpdateServlet" method="post">   
       <tr>
           <td style="width:50px!important;"><input type="text" value="${U.DMMS}" name="UNIT" class="unit" style="width:100%;"></td>
           <td style="width:150px!important;"><input type="text" value="${U.YG_MEN}" name="YG_MEN" class="yg_men" style="width:100%;"></td>
           <td style="width:150px!important;"><input type="text" value="${U.CASE_REASON}" name="CASE_REASON" class="case_reason" style="width:100%;"></td>
           <td style="width:150px!important;"><input type="text" value="${U.BG_MEN}" name="BG_MEN" class="bg_men" style="width:100%;"></td>
           <td style="width:20px!important;"><input type="text" value="是" name="SFAP" class="sfap" style="width:100%;"></td>
           <td style="width:50px!important;"><input type="text" value="${U.LX_MEN}" name="LX_MEN" class="lx_men" style="width:100%;"></td>
           <td style="width:50px!important;"><input type="text" value="${U.SQ_DATE}" name="SQ_DATE" class="sq_date" style="width:100%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
           <td style="width:50px!important;"><input type="text" value="${U.RQ}" name="RQ" class="rq" style="width:100%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
           <td style="width:50px!important;"><input type="text" value="${U.SJFT}" name="SJFT" class="sjft" style="width:100%;"></td>
           <td style="width:35px!important;"><input type="text" value="${U.FZ}" name="FZ" calss="fz" style="width:100%;"></td>
           <td style="width:80px!important;align-content: center"><a class="updatebtn"  onclick="updateInfo(${U.AJXH},${U.SQBH})">修改</a>
               <a href="deleteKtxx.do?AJXH=${U.AJXH}&SQBH=${U.SQBH}" onclick="if(confirm('确认删除吗？')==false) return false;">删除</a>
           </td>
       </tr>  
    </form>   
    </c:forEach>
     </tbody>
    </table>
  </div>
      <div>
          <%-- 构建分页导航 --%>
          共有<span style="color: red;">${requestScope.pageBean.totalRecord}</span>条记录，共<span id="totalPage" style="color: red;" value="${requestScope.pageBean.totalPage}">${requestScope.pageBean.totalPage}</span>页，当前为<span style="color: red;">${requestScope.pageBean.pageNum}</span>页
          <div style="display:inline-block;text-align: center;padding-left: 150px;">
          <a href="${pageContext.request.contextPath}/showSomeKtxx.do?pageNum=1&startTime=${startTime}&endTime=${endTime}">首页</a>
          <%--如果当前页为第一页时，就没有上一页这个超链接显示 --%>
          <c:if test="${requestScope.pageBean.pageNum ==1}">
              <c:forEach begin="${requestScope.pageBean.start}"
                         end="${requestScope.pageBean.end}" step="1" var="i">
                  <c:if test="${requestScope.pageBean.pageNum == i}">
                      ${i}
                  </c:if>
                  <c:if test="${requestScope.pageBean.pageNum != i}">
                      <a  href="${pageContext.request.contextPath}/showSomeKtxx.do?pageNum=${i}&startTime=${startTime}&endTime=${endTime}">${i}</a>
                  </c:if>
              </c:forEach>
              <a  href="${pageContext.request.contextPath}/showSomeKtxx.do?pageNum=${requestScope.pageBean.pageNum+1}&startTime=${startTime}&endTime=${endTime}">下一页</a>
          </c:if>

          <%--如果当前页不是第一页也不是最后一页，则有上一页和下一页这个超链接显示 --%>
          <c:if
                  test="${requestScope.pageBean.pageNum > 1 && requestScope.pageBean.pageNum < requestScope.pageBean.totalPage}">
              <a	href="${pageContext.request.contextPath}/showSomeKtxx.do?pageNum=${requestScope.pageBean.pageNum-1}&startTime=${startTime}&endTime=${endTime}">上一页</a>
              <c:forEach begin="${requestScope.pageBean.start}"
                         end="${requestScope.pageBean.end}" step="1" var="i">
                  <c:if test="${requestScope.pageBean.pageNum == i}">
                      ${i}
                  </c:if>
                  <c:if test="${requestScope.pageBean.pageNum != i}">
                      <a href="${pageContext.request.contextPath}/showSomeKtxx.do?pageNum=${i}&startTime=${startTime}&endTime=${endTime}">${i}</a>
                  </c:if>
              </c:forEach>
              <a	href="${pageContext.request.contextPath}/showSomeKtxx.do?pageNum=${requestScope.pageBean.pageNum+1}&startTime=${startTime}&endTime=${endTime}">下一页</a>
          </c:if>

          <%-- 如果当前页是最后一页，则只有上一页这个超链接显示，下一页没有 --%>
          <c:if
                  test="${requestScope.pageBean.pageNum == requestScope.pageBean.totalPage}">
              <a href="${pageContext.request.contextPath}/showSomeKtxx.do?pageNum=${requestScope.pageBean.pageNum-1}"&startTime=${startTime}&endTime=${endTime}>上一页</a>
              <c:forEach begin="${requestScope.pageBean.start}"
                         end="${requestScope.pageBean.end}" step="1" var="i">
                  <c:if test="${requestScope.pageBean.pageNum == i}">
                      ${i}
                  </c:if>
                  <c:if test="${requestScope.pageBean.pageNum != i}">
                      <a
                              href="${pageContext.request.contextPath}/showSomeKtxx.do?pageNum=${i}&startTime=${startTime}&endTime=${endTime}">${i}</a>
                  </c:if>
              </c:forEach>
          </c:if>
          <%--尾页 --%>
          <a href="${pageContext.request.contextPath}/showSomeKtxx.do?pageNum=${requestScope.pageBean.totalPage}&startTime=${startTime}&endTime=${endTime}">尾页</a>
          <span>直接跳转第<input id="jumpNum" type="text" style="width:40px;border-radius:4px;" onchange="JumpTo();">页</span>
          </div>
      </div>
    <jsp:include page="../../base/foot.jsp"></jsp:include>


