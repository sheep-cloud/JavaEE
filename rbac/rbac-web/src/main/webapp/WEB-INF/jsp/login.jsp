<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keys" content="">
<meta name="author" content="">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/login.css">
<style>

</style>
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
            </div>
        </div>
    </nav>

    <div class="container">
        <h1 style="color: red">${errorMsg }a</h1>
        <form id="loginForm" action="doLogin" method="post" class="form-signin" role="form">
            <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
            <div class="form-group has-success has-feedback">
                <input type="text" class="form-control" id="loginacct" name="loginacct" placeholder="请输入登录账号" autofocus>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-success has-feedback">
                <input type="text" class="form-control" id="password" name="password" placeholder="请输入登录密码" style="margin-top:10px;">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-success has-feedback">
                <select class="form-control">
                    <option value="member">会员</option>
                    <option value="user">管理</option>
                </select>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> 记住我
                </label>
                <br>
                <label>忘记密码</label>
                <label style="float:right">
                    <a href="reg.html">我要注册</a>
                </label>
            </div>
            <a class="btn btn-lg btn-success btn-block" onclick="dologin()"> 登录</a>
        </form>
    </div>
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script>
        function dologin() {
        	// 非空校验
        	var loginacct = $('#loginacct').val();
        	// 表单元素的value取值不会为null，取值是空字符串
        	if (loginacct == null || loginacct === '') {
        		alert('用户登录帐号不能为空，请输入');
        		return;
        	}
        	
        	var password = $('#password').val();
        	if (password == null || password === '') {
        		alert('用户密码不能为空，请输入');
        		return;
        	}
        	
        	// 提交表单
        	/* alert('提交表单'); */
        	$('#loginForm').submit();
        }
    </script>
</body>
</html>