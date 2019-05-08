<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<link rel="stylesheet" href="${applicationScope.APP_PATH}/static/ztree/zTreeStyle.css">
<script src="${applicationScope.APP_PATH}/static/ztree/jquery.ztree.all-3.5.min.js"></script>
<style>
  .tree li {
    list-style-type: none;
    cursor: pointer;
  }

  table tbody tr:nth-child(odd) {
    background: #F4F4F4;
  }

  table tbody td:nth-child(even) {
    color: #C00;
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

        <div class="panel panel-default">
          <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
            <div class="pull-right" style="cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                    class="glyphicon glyphicon-question-sign"></i>
            </div>
          </div>
          <div class="panel-body">
            <ul id="permissionTree" class="ztree"></ul>
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
            <h4>没有默认类</h4>
            <p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
          </div>
          <div class="bs-callout bs-callout-info">
            <h4>没有默认类</h4>
            <p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>

  <script>
    $(() => {
      $(".list-group-item").click(function () {
        if ($(this).find("ul")) {
          $(this).toggleClass("tree-closed")
          if ($(this).hasClass("tree-closed")) {
            $("ul", this).hide("fast")
          } else {
            $("ul", this).show("fast")
          }
        }
      })

      // 树形结构
      let setting = {
        async: {
          enable: true,
          url: '${applicationScope.APP_PATH}/permission/loadData',
          autoParam: ["id", "name=n", "level=lv"],
          dataFilter: (treeId, parentNode, responseData) => {
            console.log(treeId, parentNode, responseData)
            return responseData.data
          }
        },
        view: {
          selectedMulti: false,
          addDiyDom: (treeId, treeNode) => {
            // 用于在节点上固定显示用户自定义控件
            let icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
            if (treeNode.icon) {
              // 使用 bootstrop 中的样式
              icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background", "")
            }
          },
          addHoverDom: (treeId, treeNode) => {
            // 用于当鼠标移动到节点上时，显示用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
            //   <a><span></span></a>
            let aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
            aObj.attr("href", "javascript:;");
            if (treeNode.editNameFlag || $("#btnGroup" + treeNode.tId).length > 0) return;
            let s = '<span id="btnGroup' + treeNode.tId + '">'
            if (treeNode.level === 0) {
              s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick=\'addNode("' + treeNode.id + '")\'" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>'
            } else if (treeNode.level === 1) {
              s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick=\'editNode("' + treeNode.id + '")\'" href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>'
              if (treeNode.children.length === 0) {
                s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick=\'delNode("' + treeNode.id + '")\'" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>'
              }
              s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick=\'addNode("' + treeNode.id + '")\'" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>'
            } else if (treeNode.level === 2) {
              s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick=\'editNode("' + treeNode.id + '")\'" href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>'
              s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick=\'delNode("' + treeNode.id + '")\'" href="#">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>'
            }
            s += '</span>'
            aObj.after(s)
          },
          removeHoverDom: (treeId, treeNode) => {
            $("#btnGroup" + treeNode.tId).remove()
          }
        }
      }

      // 异步获取属性结构数据
      $.fn.zTree.init($("#permissionTree"), setting)
    })

    // 跳转添加许可页面
    function addNode(id) {
      window.location.href = '${applicationScope.APP_PATH}/permission/add?id=' + id
    }

    // 跳转修改许可页面
    function editNode(id) {
      window.location.href = '${applicationScope.APP_PATH}/permission/edit?id=' + id
    }

    // 删除
    function delNode(id) {
      layer.confirm('删除许可信息, 是否继续?', {icon: 3, title: '提示'}, cindex => {
        layer.close(cindex)
        let loadingIndex = null
        $.ajax({
          type: 'post',
          url: '${applicationScope.APP_PATH}/permission/delPermission',
          data: {id},
          beforeSend: () => {
            loadingIndex = layer.msg('处理中', {icon: 16})
          },
          success: data => {
            layer.close(loadingIndex);
            if (data.code === 0) {
              layer.msg('许可信息删除成功', {time: 1000, icon: 6, shift: 5}, () => {
                // 刷新当前树形结构数据
                let treeObj = $.fn.zTree.getZTreeObj('permissionTree')
                treeObj.reAsyncChildNodes(null, 'refresh')
              })
            } else {
              layer.msg(data.message, {time: 1000, icon: 5, shift: 6})
            }
          }
        })
      }, cindex => {
        layer.close(cindex)
      })
    }
  </script>
</body>
</html>
