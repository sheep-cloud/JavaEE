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
                        <button type="button" class="btn btn-danger pull-right" style="margin-left:10px;" onclick="deleteRoles()">
                            <i class=" glyphicon glyphicon-remove"></i> 删除
                        </button>
                        <button type="button" class="btn btn-primary pull-right" onclick="window.location.href='${applicationScope.APP_PATH}/role/add'">
                            <i class="glyphicon glyphicon-plus"></i> 新增
                        </button>
                        <br>
                        <hr style="clear:both;">
                        <div class="table-responsive">
                            <form id="roleForm">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th width="50">序号</th>
                                            <th width="30">
                                                <label for="allSelBox">
                                                    <input type="checkbox" id="allSelBox">
                                                </label>
                                            </th>
                                            <th>名称</th>
                                            <th>创建时间</th>
                                            <th>修改时间</th>
                                            <th width="100">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody id="roleData"></tbody>
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
            });

            // 默认查第一页
            pageQuery(1)

            // 搜索
            $('#queryBtn').click(function () {
                let queryText = $('#queryText').val()
                pageQuery(1, queryText)
            })

            // 全选
            $('#allSelBox').click(function () {
                let self = this
                $('#roleData :checkbox').each(function () {
                    this.checked = self.checked
                })
            })
        });

        $("tbody .btn-success").click(function () {
            window.location.href = "assign${applicationScope.APP_PATH}/permission/tree";
        });

        // 分页查询
        let pageSize = 10

        function pageQuery(pageNum, queryText) {
            let loadingIndex = null
            let jsonData = {
                'pageNum': pageNum,
                'pageSize': pageSize,
                'name': queryText
            }
            $.ajax({
                type: 'post',
                url: '${applicationScope.APP_PATH}/role/pageQuery',
                data: jsonData,
                beforeSend: function () {
                    loadingIndex = layer.msg('处理中', {icon: 16})
                },
                success: function (result) {
                    layer.close(loadingIndex)

                    let userList = result.data
                    let totalSize = result.total
                    let totalPage = Math.ceil(totalSize / pageSize)

                    // 表格
                    let tableContext = ''
                    $.each(userList, function (index, role) {
                        tableContext += '<tr>'
                        tableContext += '   <td>' + (index + 1) + '</td>'
                        tableContext += '   <td><input type="checkbox" name="id" value="' + role.id + '"></td>'
                        tableContext += '   <td>' + role.name + '</td>'
                        tableContext += '   <td>' + role.createTime + '</td>'
                        tableContext += '   <td>' + role.updateTime + '</td>'
                        tableContext += '   <td>'
                        tableContext += '       <button type="button" onclick=\'goAssignPage("' + role.id + '")\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>'
                        tableContext += '       <button type="button" onclick=\'goUpbatePage("' + role.id + '")\'" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>'
                        tableContext += '       <button type="button" onclick=\'delRole("' + role.id + '")\'" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>'
                        tableContext += '   </td>'
                        tableContext += '</tr>'
                    })
                    $('#roleData').html(tableContext)

                    // 分页
                    let paginationContext = ''
                    if (pageNum <= 1) {
                        paginationContext += `<li class="disabled"><a href="#">上一页</a></li>`
                    } else {
                        paginationContext += '<li><a href="#" onclick="pageQuery(' + (pageNum - 1) + ')">上一页</a></li>'
                    }

                    for (let i = 1; i <= totalPage; i++) {
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
            window.location.href = '${applicationScope.APP_PATH}/role/edit?id=' + id
        }

        // 批量删除角色
        function deleteRoles() {
            let boxes = $('#roleForm :checkbox')
            if (boxes.length === 0) {
                layer.msg('请选择需要删除的角色信息', {time: 1000, icon: 5, shift: 6})
            } else {
                layer.confirm('删除角色信息，是否继续？', {icon: 3, title: '提示'}, function (cindex) {
                    layer.close(cindex)
                    let loadingIndex = null
                    $.ajax({
                        type: 'post',
                        url: '${applicationScope.APP_PATH}/role/delRoles',
                        data: $('#roleForm').serialize(),
                        beforeSend: function () {
                            loadingIndex = layer.msg('处理中', {icon: 16})
                        },
                        success: function (result) {
                            layer.close(loadingIndex);
                            if (result.code === 0) {
                                layer.msg('角色信息删除成功', {time: 1000, icon: 6, shift: 5}, function () {
                                    window.location.href = '${applicationScope.APP_PATH}/role/list'
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

        // 删除角色
        function delRole(id) {
            layer.confirm('删除角色信息，是否继续？', {icon: 3, title: '提示'}, function (cindex) {
                layer.close(cindex)
                let loadingIndex = null
                $.ajax({
                    type: 'post',
                    url: '${applicationScope.APP_PATH}/role/delRole',
                    data: {
                        'id': id,
                    },
                    beforeSend: function () {
                        loadingIndex = layer.msg('处理中', {icon: 16})
                    },
                    success: function (result) {
                        layer.close(loadingIndex);
                        if (result.code === 0) {
                            layer.msg('角色信息删除成功', {time: 1000, icon: 6, shift: 5}, function () {
                                window.location.href = '${applicationScope.APP_PATH}/role/list'
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

        // 跳转分配许可页面
        function goAssignPage(id) {
            window.location.href = '${applicationScope.APP_PATH}/role/assign?id=' + id
        }
    </script>
</body>
</html>
