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
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/model.js"></script>
    <title>服务流程</title>
</head>
<body style="display: block;" oncontextmenu='return false'   ondragstart='return false'   onselectstart ='return false'     onbeforecopy='return false' >
    <%@include file="../inc/mtNav.jsp" %>
    <main class="wrap">
        <section class="content-type">
           <div class="about-title">服务流程</div><div class="about-content"><p><strong style="font-size: 12pt;">易族速修（E-ZONE）上门、到店维修流程。</strong></p>
<p><strong style="font-size: 12pt;">一.提交订单</strong></p>
<p>您可以通过易族速修官网或微信公众号提交维修订单。</p>
<p><strong>二.上门、到店</strong></p>
<p>提交订单后，易族速修会及时与您联系确认上门、到店维修时间。</p>
<p><strong style="font-size: 12pt;">三.维修设备</strong></p>
<p>工程师检测确认维修方案后，开始对您的设备进行维修。</p>
<p><strong>四.支付费用</strong></p>
<p>设备维修完成后，您可以使用支付宝或者微信完成支付。</p>
<p><strong><br>易族速修（E-ZONE）邮寄维修流程。<br></strong></p>
<p><strong>一.提交订单</strong></p>
<p>您可以通过易族速修官网或微信公众号提交维修订单。</p>
<p><strong>二.邮寄设备</strong></p>
<p>1. 订单提交成功后，您需要将设备邮寄给我们（建议顺丰95338，顺丰快递会上门取件）以便我们检测和维修。</p>
<p>2.邮寄前请取消手机锁屏密码，并备份好您的个人数据，易族速修在维修过程中不能保证您的数据完整性，也不对数据丢失负责。</p>
<p>3.请裸机邮寄，不要携带配件，否则如有遗失概不负责</p>
<p><strong>三.确认维修方案</strong></p>
<p>易族速修在收到您的设备后会进行检测，并根据检测结果核对订单故障信息。如果维修方案有变，我们会再与您沟通确认维修方案。如果您拒绝新维修方案，我们会将设备寄回。</p>
<p><strong>四.支付费用</strong></p>
<p>设备维修完成后，您需要访问易族速修官网或者通过微信公众号“支付查询”功能支付维修费用。</p>
<p><strong>五.邮寄签收</strong></p>
<p>1.维修完成后，我们会将设备通过顺丰邮寄给您，并为您的设备保价。邮寄地址为您在提交订单时填写的。如果地址有变，请及时联系我们。</p>
<p>2.设备寄出时，我们会通过短信通知您。您也可以访问易族速修官网支付查询功能查询快递单号，如有疑问请及时联系我们400-6810-208 。</p></div>
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
        
        function click() { 
        	if (event.button==2) { 
        	alert('对不起，本页禁用右键！') 
        } 
    } 
       document.onmousedown=click 
    </script>
</body>
</html>