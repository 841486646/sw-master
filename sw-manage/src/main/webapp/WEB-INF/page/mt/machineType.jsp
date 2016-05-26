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
    <title>请选择机型</title>
</head>
<body style="display: block;">
    <%@include file="../inc/mtNav.jsp" %>
    <section class="choice-pro-tit">左右滑动选择机型</section>
    <section class="choice-pro">
        <ul class="clearfix" style="width: 25.2rem;">
           <c:forEach items="${machineTypes}" var="machineType">
                <li>
	                <a href="${rootUrl}/mt/machineBug?machineTypeId=${machineType.id}"><img src="${rootUploadImgUrl}${machineType.imgUrl}">${machineType.name}</a>
	            </li>
           	</c:forEach>
        </ul>
    </section>
    <%@include file="../inc/mtFooter.jsp" %>
    <script>
        seajs.use(['app/scale', 'app/handler'], function(scale, handler) {
            scale.calcScale();
            handler.setUl('.choice-pro ul');
        });
    </script>
</body>
</html>