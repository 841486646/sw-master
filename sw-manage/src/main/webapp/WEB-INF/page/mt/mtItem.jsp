<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,maximum-scale=1,user-scalable=no,minimal-ui">
    <!-- 允许全屏模式浏览 -->
    <meta content="yes" name="apple-mobile-web-app-capable">
    <!-- iphone中safari顶端的状态条的样式 -->
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <!-- 忽略将页面中的数字识别为电话号码 -->
    <meta content="telephone=no" name="format-detection">
    <!-- 不识别邮箱 -->
    <meta content="email=no" name="format-detection">
    <!-- 不记录缓存从服务器刷新 -->
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <%@include file="../inc/mtHead.jsp" %>
    <title>维修条款</title>
</head>
<body style="display: block;">
    <%@include file="../inc/mtNav.jsp" %>
    <main class="wrap">
        <section class="content-type">
           <div class="about-title">维修条款</div>
        </section>
    </main>
    <%@include file="../inc/mtFooter.jsp" %>
    <script>
        seajs.use('app/mobile', function(mobile) {
            mobile.contact('.wrap', '.content-info');
        });
        seajs.use(['app/scale', 'app/handler'], function(scale, handler) {
            scale.calcScale();
            handler.focusHanlde();
        });
    </script>
</body>
</html>