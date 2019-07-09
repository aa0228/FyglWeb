<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="shortcut icon" href="css/img/fy_logo.png" />
    <title>欢迎来到法院管理系统</title>
</head>
<body>
<div class="top_div">
    <div class="" style="margin-top: 50px;display: inline-block;padding-left: 33%;">
        <img alt="" src="css/img/huizhang.png" style="height:100px;">
        <span style="font-size:50px;text-align:center;color:white;">法院管理系统</span>
    </div>
</div>
<form id="loginForm" action="login.do" method="post">
    <div class="main-div">

        <p style="padding: 30px 0px 10px; position: relative;">
            <span class="u_logo"></span>
            <input class="ipt" type="text" placeholder="请输入用户名" value="" id="yhm" name="username">
        </p>
        <p style="position: relative;">
            <span class="p_logo"></span>
            <input class="ipt" type="password" id="mm" name="password" placeholder="请输入密码">
        </p>
        <a class="error" style="display: none;float:right;"></a>

        <span style="float: left;margin-top:5px;margin-left:30px;">
				    <label style="font-size:15px;">请选择:</label>
                     <select id="bmbhselect" name="bmbhselectname" style="width:120px;height:24px;overflow-y:auto;">
                     <option value="1111">天津市高级人民法院</option>
                 <!-- <option selected value="120000 200">天津市高级人民法院</option>-->
                     <option value="120100 210">第一中级人民法院</option>
                     <option value="120200 220">第二中级人民法院</option>
                     <option value="960200 230">天津海事法院</option>
                     <option value="120101 211">和平区人民法院</option>
                     <option value="120104 212">南开区人民法院</option>
                     <option value="120105 213">河北区人民法院</option>
                     <option value="120106 214">红桥区人民法院</option>
                     <option value="120107 215">西青区人民法院</option>
                     <option value="120108 216">北辰区人民法院</option>
                     <option value="120202 221">河东区人民法院</option>
                     <option value="120203 222">河西区人民法院</option>
                     <option value="120204 223">塘沽审判区</option>
                     <option value="120205 224">汉沽审判区</option>
                     <option value="120206 225">大港审判区</option>
                     <option value="120207 226">东丽区人民法院</option>
                     <option value="120208 227">津南区人民法院</option>
                     <option value="120221 228">宁河区人民法院</option>
                     <option value="120222 217">武清区人民法院</option>
                     <option value="120223 218">静海区人民法院</option>
                     <option value="120224 219">宝坻区人民法院</option>
                     <option value="120225 21A">蓟县人民法院</option>
                     <option value="120241 229">功能区审判区</option>
                     <option value="120242 22A">天津滨海新区人民法院</option>
                     <option value="920103 132">天津铁路运输法院</option>
                    </select>
                    </span>

        <div class="bottom-div">
            <p style="margin: 20px 35px 20px 28px;">
				   <span style="float: left;">
					<input type="checkbox" id="reme" class="reme" /> <label for="reme" class="reme-lable">记住密码</label>
                </span>
                <span style="float: right;">
					<input class="login-button" type="submit" id="loginButton" value="登录" />
					</span>

            </p>
        </div>
    </div>
</form>
</body>
</html>


 
