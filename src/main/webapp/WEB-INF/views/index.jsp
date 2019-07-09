<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">   <%-- 在IE运行最新的渲染模式 --%>
		<meta name="viewport" content="width=device-width, initial-scale=1">   <%-- 初始化移动浏览显示 --%>
		<meta name="Author" content="Dreamer-1.">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-change.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/My97DatePicker/skin/WdatePicker.css" >
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css" >

		<script language="javascript" type="text/javascript"  src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>

	
		<title>- 法院管理系统 -</title>
	</head>
	
	<body>
	<!-- 顶部菜单 -->
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div style="height:50px;margin-top:7px;margin-bottom: 7px;background-color:#127ebc;">
      		<div class="container">
        		<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" >
		            	<span class="sr-only">Toggle navigation</span>
		            	<span class="icon-bar"></span>
		            	<span class="icon-bar"></span>
		            	<span class="icon-bar"></span>
					</button>
					<span><img src="${pageContext.request.contextPath}/image/fahui.png" alt="fahui" style="margin-left: -240px;height:50px;"></span>
	          		<span class="navbar-brand" style="color: white;width:200px;font-size: x-large;">法院管理系统</span>
        		</div>
        		
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
					    <!--<li style="visibility: hidden"><a><label name="fydm" value="${fydm}">${fydm}</label></a></li>
					    <li><a><i class="fa fa-list">${fymc}</i></a></li>
						<li><a><i class="fa fa-list">${username}</i></a></li>
						<li><a href="${pageContext.request.contextPath}/logout.do" style="text-decoration:none;"><i class="fa fa-list">退出登录</i></a></li>
                        -->
						<li style="visibility: hidden"><a><label name="fydm" value="${fydm}">${fydm}</label></a></li>
						<li><a><i class="fa">${fymc}</i></a></li>
						<li><a><img src="${pageContext.request.contextPath}/image/person.png" alt="person" style="margin-top:-3px; height:27px;"><i class="fa" style="color:#ffffff;margin-left: 8px;">${username}</i></a></li>
						<li><a href="${pageContext.request.contextPath}/logout.do" style="text-decoration:none;"><i class="fa"></i><img src="${pageContext.request.contextPath}/image/logout.png" alt="layout" style="height: 25px;"></a></li>
					</ul>
          			
        		</div>
      		</div>
			</div>
		</nav>

	<!-- 左侧菜单选项========================================= -->
		<div class="container-fluid">
			<div class="row-fluie">
				<div class="col-sm-3 col-md-2 sidebar">		
					<ul class="nav nav-sidebar">
						<!-- 一级菜单 -->
						<li class="active"><a href="#userMeun" class="nav-header menu-first collapsed" data-toggle="collapse">
							<i class="fa fa-user"></i>&nbsp; 法庭管理 <span class="sr-only">(current)</span></a>
						</li> 
						<!-- 二级菜单 -->
						<!-- 注意一级菜单中<a>标签内的href="#……"里面的内容要与二级菜单中<ul>标签内的id="……"里面的内容一致 -->
						<ul id="userMeun" class="nav nav-list collapse menu-second">
							<li><a href="javascript:void(0)" onclick=" showAtRight('showFtxx.do')"><i class="fa fa-users"></i> 查看法庭信息</a></li>
							<li><a href="javascript:void(0)" onclick=" showAtRight('NewCase.jsp')"><i class="fa fa-users"></i> 新增法庭信息</a></li>
						</ul>
						 
						<li><a href="#productMeun" class="nav-header menu-first collapsed" data-toggle="collapse">
							<i class="fa fa-globe"></i>&nbsp;开庭管理 <span class="sr-only">(current)</span></a>
						</li> 
						<ul id="productMeun" class="nav nav-list collapse menu-second">
							<li><a href="javascript:void(0)" onclick="showAtRight('showSomeKtxx.do')"><i class="fa fa-list-alt"></i> 查看法庭使用信息</a></li>
							<li><a href="javascript:void(0)" onclick="showAtRight('selectinsert.do')"><i class="fa fa-list-alt"></i> 新增法庭使用信息</a></li>
							<li><a href="javascript:void(0)" onclick="showAtRight('statistics.jsp')"><i class="fa fa-list-alt"></i> 统计法庭使用情况</a></li>
						</ul>


					</ul>
					
				</div>
			</div>
		</div>

<!-- 右侧内容展示==================================================   -->   		
 				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<h1 class="page-header">
						<!--	<i class="fa fa-cog fa-spin"></i>--><i><img src="${pageContext.request.contextPath}/image/laba.png" alt="laba"></i>&nbsp;<small>&nbsp;&nbsp;&nbsp;欢迎使用法院管理系统</small></h1>
						
						<!-- 载入左侧菜单指向的jsp（或html等）页面内容 -->
          				<div id="content">

							温馨提示：开庭信息数据较多，查询时请稍等30s。
							<br><br>
          				</div>  
				</div> 
		
		
		<script type="text/javascript">
		
		/*
		 * 对选中的标签激活active状态，对先前处于active状态但之后未被选中的标签取消active
		 * （实现左侧菜单中的标签点击后变色的效果）
		 */
		$(document).ready(function () {
			$('ul.nav > li').click(function (e) {
				//e.preventDefault();	加上这句则导航的<a>标签会失效
				$('ul.nav > li').removeClass('active');
				$(this).addClass('active');
			});
		});
		
		/*
		 * 解决ajax返回的页面中含有javascript的办法：
		 * 把xmlHttp.responseText中的脚本都抽取出来，不管AJAX加载的HTML包含多少个脚本块，我们对找出来的脚本块都调用eval方法执行它即可
		 */
		function executeScript(html)
		{
		    
			var reg = /<script[^>]*>([^\x00]+)$/i;
		    //对整段HTML片段按<\/script>拆分
		    var htmlBlock = html.split("<\/script>");
		    for (var i in htmlBlock) 
		    {
		        var blocks;//匹配正则表达式的内容数组，blocks[1]就是真正的一段脚本内容，因为前面reg定义我们用了括号进行了捕获分组
		        if (blocks = htmlBlock[i].match(reg)) 
		        {
		            //清除可能存在的注释标记，对于注释结尾-->可以忽略处理，eval一样能正常工作
		            var code = blocks[1].replace(/<!--/, '');
		            try 
		            {
		                eval(code) //执行脚本
		            } 
		            catch (e) 
		            {
		            }
		        }
		    }
		}
		
		/*
		 * 利用div实现左边点击右边显示的效果（以id="content"的div进行内容展示）
		 * 注意：
		 *   ①：js获取网页的地址，是根据当前网页来相对获取的，不会识别根目录；
		 *   ②：如果右边加载的内容显示页里面有css，必须放在主页（即例中的index.jsp）才起作用
		 *   （如果单纯的两个页面之间include，子页面的css和js在子页面是可以执行的。 主页面也可以调用子页面的js。但这时要考虑页面中js和渲染的先后顺序 ）
		*/
		function showAtRight(url) {
			var xmlHttp;
			
			if (window.XMLHttpRequest) {
				// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlHttp=new XMLHttpRequest();	//创建 XMLHttpRequest对象
			}
			else {
				// code for IE6, IE5
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
		
			xmlHttp.onreadystatechange=function() {		
				//onreadystatechange — 当readystate变化时调用后面的方法
				
				if (xmlHttp.readyState == 4) {
					//xmlHttp.readyState == 4	——	finished downloading response
					
					if (xmlHttp.status == 200) {
						//xmlHttp.status == 200		——	服务器反馈正常			
						
						document.getElementById("content").innerHTML=xmlHttp.responseText;	//重设页面中id="content"的div里的内容
						executeScript(xmlHttp.responseText);	//执行从服务器返回的页面内容里包含的JavaScript函数
					}
					//错误状态处理
					else if (xmlHttp.status == 404){
						alert("出错了☹   （错误代码：404 Not Found），……！"); 
						/* 对404的处理 */
						return;
					}
					else if (xmlHttp.status == 403) {  
						alert("出错了☹   （错误代码：403 Forbidden），……"); 
						/* 对403的处理  */ 
						return;
			        }
					else {
						alert("出错了☹   （错误代码：" + request.status + "），……"); 
						/* 对出现了其他错误代码所示错误的处理   */
						return;
					}   
				} 
		            
			  }
			
			//把请求发送到服务器上的指定文件（url指向的文件）进行处理
			xmlHttp.open("GET", url, true);		//true表示异步处理
			xmlHttp.send();
		}		
		</script>
	
	</body>
</html>