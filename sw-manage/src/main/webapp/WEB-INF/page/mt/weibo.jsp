<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf8">
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
    <title>哈喽Media</title>
    <script src="${rootUrl}/resources/js/mt/inc/video.js" type="text/javascript"></script>
</head>
<body>
    <%@include file="../inc/mtNav.jsp" %>
    <section class="media">
        <ul class="media-tab clearfix">
            <li class="active">新闻动态</li>
            <li>官方视频</li>
        </ul>
        <div class="media-new">
            <iframe width="100%" height="550" class="share_self"  frameborder="0" scrolling="no" src="#"></iframe>
        </div>
        <div id="divhtml" class="media-video">
            <ul class="clearfix">
            </ul>
            <div id="divmore" class="media-vide-btn">加载更多</div>
        </div>
    </section>
    <%@include file="../inc/mtFooter.jsp" %>
    <script>
        seajs.use(['app/scale', 'app/handler'], function(scale, handler) {
            scale.calcScale();
            handler.mediaTab();
        });
        getVideoList(1);
    </script>
</body>
</html>