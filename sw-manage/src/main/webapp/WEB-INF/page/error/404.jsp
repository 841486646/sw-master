<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>404</title>
    <%@include file="../inc/head.jsp" %>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class=".col-xs-6 .col-md-4">&nbsp;</div>
            <div class=".col-xs-6 .col-md-4">
                <div class="jumbotron">
                    <h1>404</h1>
                    <p>没有找到您要的页面，重新登录再试试吧！</p>
                    <p><a class="btn btn-danger btn-lg" target="_top" href="<%=rootUrl%>/web/user/logout" role="button">从头再来</a></p>
                </div>
            </div>
            <div class=".col-xs-6 .col-md-4">&nbsp;</div>
        </div>
    </div>
</body>
</html>