<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<html>
<body>
    <h2>Hello World! ${applicationScope.APP_PATH}</h2>
    <h3>Hello World! ${pageContext.request.contextPath}</h3>

    <%--跳转到登录页面--%>
    <jsp:forward page="WEB-INF/jsp/login.jsp" />
</body>
</html>