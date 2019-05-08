<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keys" content="">
<meta name="author" content="">
<link rel="stylesheet" href="${applicationScope.APP_PATH}/static/css/error.css">
</head>
<body>
  <!-- 代码 开始 -->
  <div id="container"><img class="png" src="${applicationScope.APP_PATH}/static/images/404.png"/>
    <img class="png msg" src="${applicationScope.APP_PATH}/static/images/404_msg.png"/>
    <p>
      <a href="${applicationScope.APP_PATH}/login" target="_blank">
        <img class="png" src="${applicationScope.APP_PATH}/static/images/404_to_index.png"/>
      </a>
    </p>
  </div>
  <div id="cloud" class="png"></div>
  <!-- 代码 结束 -->
</body>
</html>