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
    <title>关于我们</title>
</head>
<body style="display: block;">
    <%@include file="../inc/mtNav.jsp" %>
    <main class="wrap">
        <section class="content-type">
           <div class="about-title">常见问题</div><div class="about-content"><p><strong>维修后出现问题怎么办？</strong></p>
<p>易族速修（E-ZONE）从维修完成之日起，对维修过的设备提供维修故障点非人为损坏情况下，3个月的免费保修服务(屏幕不保修)。</p>
<p><strong><br>哪些设备可以维修？</strong></p>
<p>易族速修致力于苹果产品的专业级维修服务，包括iPhone、iPad、MacBook、iMac等。</p>
<p><strong><br>维修来回邮费谁承担？</strong></p>
<p>易族速修承担设备来回的邮寄费用以及寄回的保价费（但不包括寄来时的保价费）。如果设备经易族速修检测后，您主动放弃维修或申请退款，需要承担寄回的快递费和保价费。</p>
<p><strong><br>如何支付维修费用？</strong></p>
<p>您可以使用支付宝、微信，通过网站的<span style="color: #006fba;"><a href="#" target="_blank"><span style="color: #006fba;">支付查询</span></a></span>功能的提示完成支付操作。</p>
<p><strong><br>邮寄过程中出现设备损坏或丢失怎么办？</strong></p>
<p>如果您的设备采取了保价邮寄，快递过程中出现损坏或丢失的情况，我们将积极配合您向快递公司申请赔偿。如快递时没有保价，易族速修不承担任何责任。由易族速修寄出的设备，将统一采取保价邮寄，如在快递过程中出现损坏或丢失情况，我们将先行赔付与该设备相同或等价的设备。</p>
<p><strong><br>维修零配件品质如何？</strong></p>
<p>易族速修采用的维修零配件均为原厂品质配件。原厂品质配件是指配件的制造工艺、材料等结构上的品质等同原装，并非指官方授权配件。</p>
<p><strong><br>维修设备需要多久时间？</strong></p>
<p>通常寄修设备将在您确认维修后的24小时内完成维修。如遇特殊情况，易族速修将会提前与您取得联系。 为了方便您及时获悉维修进程，您可以通过易族速修官网，或者微信公众号查询订单状态。</p></div>
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