<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-change.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

<script>
    $(function(){
        $(".updatebtn").click(function () {
            var BH = $(this).data("bh");
            var BMMC = $(this).parents("tr").find(".bmmc").val();
            var BZ = $(this).parents("tr").find(".bz").val();
            $.ajax({
                url: "updateFtxx.do",
                type: "POST",
                dataType: "json",
                async:false,
                data: {
                    BH:BH,
                    BMMC: BMMC,
                    BZ: BZ
                },
                success : function(data) {
                    $('content').html(data);
                }

            });
        });
    });
</script>
   <jsp:include page="../../base/head.jsp"></jsp:include>

  <div>
   <table id="FtxxList"  border="1" cellpadding="0" style="margin-left:10%;margin-top:2%; width:500px;">
       <thead>
         <tr>
            <th>法庭名称</th>
            <th>备注</th>
            <th>操作</th> 
         </tr>
       </thead>

     <tbody>
     <c:forEach var="U" items="${list}">
       <tr>
         <td style="width:200px;"><input type="text" value="${U.BMMC}" style="width:100%;" name="BMMC" class="bmmc"></td>
         <td style="width:200px;"><input type="text" value="${U.BZ}" name="BZ" style="width:100%;"class="bz"></td>
         <td style="width:100px!important;text-align: center;"><a class="updatebtn" data-bh="${U.BMBH}" href="javascript:void(0)">修改</a>
             <a href="deleteFtxx.do?BMBH=${U.BMBH}" onclick="if(confirm('确认删除吗？')==false) return false;">删除</a>
         </td>
       </tr>
    </c:forEach>
     </tbody>
  </table>
  </div>
   <div id="dialog1"></div>
     <jsp:include page="../../base/foot.jsp"></jsp:include>



