<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>联系我们</title>
</head>
<body style="display: block;">
    <%@include file="../inc/mtNav.jsp" %>
    <section class="about-content">
        <div class="about-content1">
            <ul>
                <li>详细地址：</li>
                <li>华普国际大厦15层1509B室</li>
                <li>地铁：2/6号线朝阳门站A口出往东50米即到华普大厦（小肥羊入口进.电梯15层1509B室）。</li>
                <li>公交：75路、101电车、109电车、110路、112电车、420路、615路、619路、750快车"朝阳门外" 站下车即到。</li>
                <li><img src="${rootImgUrl}20141222234317903.jpg" alt=""></li>
                <li><img src="${rootImgUrl}20141222234330100.jpg" alt=""></li>
                <li><br></li>
            </ul>
        </div>
        <div class="about-content2">
            <ul class="clearfix">
                <li>
                    <a href="tel:13810301668">
                        <img src="${rootImgUrl}phone.png" alt="">
                        <p>24小时<br>电话客服</p>
                    </a>
                </li>
                <li><a href="http://www.computerfix.cn/shouji/contact.asp" target="_blank">
                    <img src="${rootImgUrl}110105-1208.png" alt="">
                    <p>8:00-24:00<br>在线客服</p>
                    </a>
                </li>
            </ul>
        </div>
    </section>
	<%@include file="../inc/mtFooter.jsp" %>
    <script>
        seajs.use(['app/scale', 'app/handler'], function(scale, handler) {
            scale.calcScale();
        });
    </script>
</body>
</html>