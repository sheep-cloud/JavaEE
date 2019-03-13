<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="${applicationScope.APP_PATH}/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${applicationScope.APP_PATH}/static/css/font-awesome.min.css">
<link rel="stylesheet" href="${applicationScope.APP_PATH}/static/css/main.css">
<link rel="stylesheet" href="${applicationScope.APP_PATH}/static/css/doc.min.css">
<style>
  .tree li {
    list-style-type: none;
    cursor: pointer;
  }
</style>
</head>
<body>
  <%--导航--%>
  <%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-3 col-md-2 sidebar">
        <%--菜单--%>
        <%@ include file="/WEB-INF/jsp/common/menu.jsp" %>
      </div>
      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <ol class="breadcrumb">
          <li><a href="${applicationScope.APP_PATH}/main">首页</a></li>
          <li><a href="${applicationScope.APP_PATH}/permission/tree">数据列表</a></li>
          <li class="active">新增</li>
        </ol>
        <div class="panel panel-default">
          <div class="panel-heading">
            表单数据
            <div class="pull-right" style="cursor:pointer;" data-toggle="modal" data-target="#myModal">
              <i class="glyphicon glyphicon-question-sign"></i>
            </div>
          </div>
          <div class="panel-body">
            <form id="userForm" role="form">
              <div class="form-group">
                <label for="name">许可名称</label>
                <input type="text" class="form-control" id="name" placeholder="请输入许可名称">
              </div>
              <div class="form-group">
                <label for="url">链接地址</label>
                <input type="text" class="form-control" id="url" placeholder="请输入链接地址">
              </div>
              <button id="insertBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
              <button id="resetBtn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title" id="myModalLabel">帮助</h4>
        </div>
        <div class="modal-body">
          <div class="bs-callout bs-callout-info">
            <h4>测试标题1</h4>
            <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
          </div>
          <div class="bs-callout bs-callout-info">
            <h4>测试标题2</h4>
            <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>
  <script src="${applicationScope.APP_PATH}/static/jquery/jquery-2.1.1.min.js"></script>
  <script src="${applicationScope.APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>
  <script src="${applicationScope.APP_PATH}/static/script/docs.min.js"></script>
  <script src="${applicationScope.APP_PATH}/static/layer/layer.js"></script>
  <script>
    $(function () {
      $('.list-group-item').click(function () {
        if ($(this).find('ul')) {
          $(this).toggleClass('tree-closed')
          if ($(this).hasClass('tree-closed')) {
            $('ul', this).hide('fast')
          } else {
            $('ul', this).show('fast')
          }
        }
      })

      // 重置
      $('#resetBtn').click(function () {
        // jQuery[0]    ===> DOM
        // $(DOM)       ===> jQuery
        $('#userForm')[0].reset()
      })

      // 新增
      $('#insertBtn').click(function () {
        let name = $('#name').val()
        if (!name) {
          layer.msg('许可名称不能为空，请输入', {time: 1000, icon: 5, shift: 6})
          return
        }
        let loadingIndex = null
        $.ajax({
          type: 'post',
          url: '${applicationScope.APP_PATH}/permission/insert',
          data: {
            'name': name,
            'url': $('#url').val(),
            'pid': '${param.id}'
          },
          beforeSend: function () {
            loadingIndex = layer.msg('处理中', {icon: 16})
          },
          success: function (result) {
            layer.close(loadingIndex)
            if (result.code === 0) {
              layer.msg('许可信息保存成功', {time: 1000, icon: 6, shift: 5}, function () {
                window.location.href = '${applicationScope.APP_PATH}/permission/tree'
              })
            } else {
              layer.msg(result.message, {time: 1000, icon: 5, shift: 6})
            }
          }
        })
      })
    })
  </script>
</body>
</html>