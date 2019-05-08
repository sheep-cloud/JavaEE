<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<link rel="stylesheet" href="${applicationScope.APP_PATH}/static/css/login.css">
</head>
<body>
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <div><a class="navbar-brand" href="index.jsp" style="font-size:32px;">创意产品众筹平台</a></div>
      </div>
    </div>
  </nav>

  <div class="container">
    <%--获取页面 errorMsg，使用ajax登录后不需要了--%>
    <c:if test="${not empty param.errorMsg }">
      <div class="alert alert-danger alert-dismissable text-center">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
          &times;
        </button>
          ${param.errorMsg }
      </div>
    </c:if>

    <form id="loginForm" action="doLogin" method="post" class="form-signin" role="form">
      <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
      <div class="form-group has-success has-feedback">
        <input type="text" class="form-control" id="loginacct" name="loginacct" placeholder="请输入登录账号" autofocus>
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-success has-feedback">
        <input type="password" class="form-control" id="password" name="password" placeholder="请输入登录密码" style="margin-top:10px;">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-success has-feedback">
        <label for="type"></label>
        <select class="form-control" id="type">
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
        <label class="pull-right">
          <a href="reg.html">我要注册</a>
        </label>
      </div>
      <a class="btn btn-lg btn-success btn-block" onclick="dologin()"> 登录</a>
    </form>
  </div>

  <script>
    // 用户登录
    function dologin() {
      // 非空校验
      let loginacct = $('#loginacct').val()
      // 表单元素的value取值不会为null，取值是空字符串
      if (!loginacct) {
        layer.msg('用户登录帐号不能为空, 请输入', {time: 1000, icon: 5, shift: 6})
        return
      }

      let password = $('#password').val()
      if (!password) {
        layer.msg('用户密码不能为空, 请输入', {time: 1000, icon: 5, shift: 6})
        return
      }

      // 使用ajax提交数据
      let loadingIndex = null
      $.ajax({
        type: 'post',
        url: '${applicationScope.APP_PATH}/doAjaxLogin',
        data: {loginacct, password},
        beforeSend: () => {
          loadingIndex = layer.msg('处理中', {icon: 16})
        },
        success: data => {
          layer.close(loadingIndex)

          if (data.code === 0) {
            window.location.href = '${applicationScope.APP_PATH}/main'
          } else {
            layer.msg(data.message, {time: 1000, icon: 5, shift: 6})
          }
        }
      })
    }
  </script>
</body>
</html>