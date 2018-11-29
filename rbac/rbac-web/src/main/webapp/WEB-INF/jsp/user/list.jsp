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
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                    </div>
                    <div class="panel-body">
                        <form class="form-inline pull-left" role="form">
                            <div class="form-group has-feedback">
                                <div class="input-group">
                                    <div class="input-group-addon">查询条件</div>
                                    <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
                                </div>
                            </div>
                            <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                        </form>
                        <button type="button" class="btn btn-danger pull-right" style="margin-left:10px;" onclick="deleteUsers()"><i
                                class=" glyphicon glyphicon-remove"></i> 删除
                        </button>
                        <button type="button" class="btn btn-primary pull-right"
                                onclick="window.location.href='${applicationScope.APP_PATH}/user/add'"><i
                                class="glyphicon glyphicon-plus"></i> 新增
                        </button>
                        <br>
                        <hr style="clear:both;">
                        <div class="table-responsive">
                            <form id="userForm">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th width="50">序号</th>
                                            <th width="30">
                                                <label for="allSelBox">
                                                    <input type="checkbox" id="allSelBox">
                                                </label>
                                            </th>
                                            <th>账号</th>
                                            <th>名称</th>
                                            <th>邮箱地址</th>
                                            <th>创建时间</th>
                                            <th>修改时间</th>
                                            <th width="100">操作</th>
                                        </tr>
                                    </thead>

                                    <tbody id="userData"></tbody>

                                    <tfoot>
                                        <tr>
                                            <td colspan="8" align="center">
                                                <ul class="pagination"></ul>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="${applicationScope.APP_PATH}/static/jquery/jquery-2.1.1.min.js"></script>
    <script src="${applicationScope.APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${applicationScope.APP_PATH}/static/script/docs.min.js"></script>
    <script src="${applicationScope.APP_PATH}/static/layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
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

            // 分页查询，默认从第一页开始查
            pageQuery(1)

            // 查询
            $('#queryBtn').click(function () {
                var queryText = $('#queryText').val()
                pageQuery(1, queryText)
            })

            // 全选
            $('#allSelBox').click(function () {
                var self = this
                $('#userData :checkbox').each(function () {
                    this.checked = self.checked
                })
            })
        })

        $("tbody .btn-success").click(function () {
            window.location.href = "assign${applicationScope.APP_PATH}/role/list";
        });
        $("tbody .btn-primary").click(function () {
            window.location.href = "edit.html";
        });

        // 分页查询
        var pageSize = 10

        // 分页查询
        function pageQuery(pageNum, queryText) {
            var loadingIndex = null
            var jsonData = {
                'pageNum': pageNum,
                'pageSize': pageSize,
                'loginacct': queryText
            }
            $.ajax({
                type: 'post',
                url: '${applicationScope.APP_PATH}/user/pageQuery',
                data: jsonData,
                beforeSend: function () {
                    loadingIndex = layer.msg('处理中', {icon: 16})
                },
                success: function (result) {
                    layer.close(loadingIndex)

                    var userList = result.data
                    var totalSize = result.total
                    var totalPage = Math.ceil(totalSize / pageSize)

                    // 局部刷新页面数据
                    // 表格
                    var tableContext = ''
                    $.each(userList, function (index, user) {
                        tableContext += '<tr>'
                        tableContext += '   <td>' + (index + 1) + '</td>'
                        tableContext += '   <td><input type="checkbox" name="id" value="' + user.id + '"></td>'
                        tableContext += '   <td>' + user.loginacct + '</td>'
                        tableContext += '   <td>' + user.username + '</td>'
                        tableContext += '   <td>' + user.email + '</td>'
                        tableContext += '   <td>' + user.createTime + '</td>'
                        tableContext += '   <td>' + user.updateTime + '</td>'
                        tableContext += '   <td>'
                        tableContext += '       <button type="button" onclick=\'goAssignPage("' + user.id + '")\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>'
                        tableContext += '       <button type="button" onclick=\'goUpbatePage("' + user.id + '")\'" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>'
                        tableContext += '       <button type="button" onclick=\'delUser("' + user.id + '")\'" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>'
                        tableContext += '   </td>'
                        tableContext += '</tr>'
                    })
                    $('#userData').html(tableContext)

                    // 分页
                    var paginationContext = ''
                    if (pageNum <= 1) {
                        paginationContext += `<li class="disabled"><a href="#">上一页</a></li>`
                    } else {
                        paginationContext += '<li><a href="#" onclick="pageQuery(' + (pageNum - 1) + ')">上一页</a></li>'
                    }

                    for (var i = 1; i <= totalPage; i++) {
                        if (i === pageNum) {
                            paginationContext += '<li class="active"><a href="#">' + i + '<span class="sr-only">(current)</span></a></li>'
                        } else {
                            paginationContext += '<li><a href="#" onclick="pageQuery(' + i + ')">' + i + '</a></li>'
                        }
                    }

                    if (pageNum < totalPage) {
                        paginationContext += '<li><a href="#" onclick="pageQuery(' + (pageNum + 1) + ')">下一页</a></li>'
                    } else {
                        paginationContext += `<li class="disabled"><a href="#">下一页</a></li>`
                    }
                    $('.pagination').html(paginationContext)
                }
            })
        }

        // 跳转编辑页面
        function goUpbatePage(id) {
            window.location.href = '${applicationScope.APP_PATH}/user/edit?id=' + id
        }

        // 跳转分配角色页面
        function goAssignPage(id) {
            window.location.href = '${applicationScope.APP_PATH}/user/assign?id=' + id
        }

        // 批量删除用户
        function deleteUsers() {
            /*
            var ids = []
            $('#userData :checkbox').each(function () {
                if (this.checked) {
                    ids.push($(this).val())
                }
            })
            ids = ids.join(',')
            */

            var boxes = $('#userData :checkbox')
            if (boxes.length === 0) {
                layer.msg('请选择需要删除的用户信息', {time: 1000, icon: 5, shift: 6})
            } else {
                layer.confirm('删除用户信息，是否继续？', {icon: 3, title: '提示'}, function (cindex) {
                    layer.close(cindex)
                    var loadingIndex = null
                    $.ajax({
                        type: 'post',
                        url: '${applicationScope.APP_PATH}/user/delUsers',
                        data: $('#userForm').serialize(),
                        beforeSend: function () {
                            loadingIndex = layer.msg('处理中', {icon: 16})
                        },
                        success: function (result) {
                            layer.close(loadingIndex);
                            if (result.code === 0) {
                                layer.msg('用户信息删除成功', {time: 1000, icon: 6, shift: 5}, function () {
                                    window.location.href = '${applicationScope.APP_PATH}/user/list'
                                })
                            } else {
                                layer.msg(result.message, {time: 1000, icon: 5, shift: 6})
                            }
                        }
                    })
                }, function (cindex) {
                    layer.close(cindex)
                })
            }
        }

        // 删除用户
        function delUser(id) {
            layer.confirm('删除用户信息，是否继续？', {icon: 3, title: '提示'}, function (cindex) {
                layer.close(cindex)
                var loadingIndex = null
                $.ajax({
                    type: 'post',
                    url: '${applicationScope.APP_PATH}/user/delUser',
                    data: {
                        'id': id,
                    },
                    beforeSend: function () {
                        loadingIndex = layer.msg('处理中', {icon: 16})
                    },
                    success: function (result) {
                        layer.close(loadingIndex);
                        if (result.code === 0) {
                            layer.msg('用户信息删除成功', {time: 1000, icon: 6, shift: 5}, function () {
                                window.location.href = '${applicationScope.APP_PATH}/user/list'
                            })
                        } else {
                            layer.msg(result.message, {time: 1000, icon: 5, shift: 6})
                        }
                    }
                })
            }, function (cindex) {
                layer.close(cindex)
            })
        }
    </script>
</body>
</html>
