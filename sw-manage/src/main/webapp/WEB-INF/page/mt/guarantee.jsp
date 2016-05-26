<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    <title>保修查询</title>
    <script src="${rootUrl}/resources/js/mt/inc/warranty.js" type="text/javascript"></script>
</head>
<body class="record-body" style="display: block;">
    <%@include file="../inc/mtNav.jsp" %>
    <div class="record-content">
        <p class="record-main1" id="warrantyPrompt1">您的保修服务期限。</p>
        <p class="record-main2" id="warrantyPrompt2">从维修完成之日起，我们提供维修故障点非人为损坏情况下，6个月的免费保修服务。</p>
        <div class="record-main3">
            <input type="text" name="phone" id="phone" placeholder="请输入您的手机号">
            <input type="button" value="保修查询" onclick="orderList()">
        </div>
    </div>
    <div id="divhtml" class="record-content1">
    </div>
    <%@include file="../inc/mtFooter.jsp" %>
    <script>
	    seajs.use(['app/scale', 'app/handler'], function(scale, handler) {
	        scale.calcScale();
	        //handler.recordError(null);
	    });
    </script>
</body>
</html>