<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<style>
  .tree li {
    list-style-type: none;
    cursor: pointer;
  }

  .tree-closed {
    height: 40px;
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
        <h1 class="page-header">控制面板</h1>

        <div class="row placeholders">
          <div class="col-xs-6 col-sm-3 placeholder">
            <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
            <h4>Label</h4>
            <span class="text-muted">Something else</span>
          </div>
          <div class="col-xs-6 col-sm-3 placeholder">
            <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
            <h4>Label</h4>
            <span class="text-muted">Something else</span>
          </div>
          <div class="col-xs-6 col-sm-3 placeholder">
            <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
            <h4>Label</h4>
            <span class="text-muted">Something else</span>
          </div>
          <div class="col-xs-6 col-sm-3 placeholder">
            <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
            <h4>Label</h4>
            <span class="text-muted">Something else</span>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script>
    $(() => {
      $(".list-group-item").click(function () {
        // jquery对象的回调方法中的this关键字为DOM对象
        // $(DOM) ==> JQuery
        if ($(this).find("ul")) { // 3 li
          $(this).toggleClass("tree-closed")
          if ($(this).hasClass("tree-closed")) {
            $("ul", this).hide("fast")
          } else {
            $("ul", this).show("fast")
          }
        }
      })
    })
  </script>
</body>
</html>
