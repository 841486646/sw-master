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
    <title>维修官网-专注苹果产品维修，手机上门维修</title>
</head>
<body style="display: block;">
        <%@include file="../inc/mtNav.jsp" %>
        <section class="focus-content">
	        <ul>
	            <li><a href="#"><img src="<%=rootUrl %>/resources/images/mt/banner1.jpg" alt=""></a></li>
	            <li><a href="#"><img src="<%=rootUrl %>/resources/images/mt/banner2.jpg" alt=""></a></li>
	            <li><a href="#"><img src="<%=rootUrl %>/resources/images/mt/banner3.jpg" alt=""></a></li>
	        </ul>
	        <ol></ol>
    	</section>
        <section class="choice-content clearfix">
        <ul>
        	<c:forEach items="${products }" var="product">
                <li>
	                <a href="${rootUrl}/mt/machineType?productId=${product.id}"><img src="${rootUploadImgUrl}${product.imgUrl}"></a>
	            </li>
           	</c:forEach>
        </ul>
    </section>
    <section class="otherchoice-content clearfix">
        <ul>
            <li><a href="#"><img src="<%=rootUrl %>/resources/images/mt/otherchoice_1.png" alt=""></a></li>
            <li><a href="${rootUrl}/mt/about"><img src="<%=rootUrl %>/resources/images/mt/otherchoice_2.png" alt=""></a></li>
            <li><a href="${rootUrl}/mt/guarantee"><img src="<%=rootUrl %>/resources/images/mt/otherchoice_3.png" alt=""></a></li>
            <li><a href="https://shop58866385.taobao.com/"><img src="<%=rootUrl %>/resources/images/mt/otherchoice_4.png" alt=""></a></li>
        </ul>
    </section>
    <%@include file="../inc/mtFooter.jsp" %>
    <script>
    seajs.use(['app/scale', 'app/handler'], function(scale, handler) {
        scale.calcScale();
        handler.focusHanlde();
    });
    </script>
</body>
</html>