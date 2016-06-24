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
    <title>请选择故障</title>
</head>
<body style="display: block;">
    <%@include file="../inc/mtNav.jsp" %>
    <main class="wrap">
    <input type="hidden" id="machineTypeId" value="${machineType.id}"/>
        <section class="content-fault">
           <div class="fault-main1"><div class="fault-img1"><img src="${rootUploadImgUrl}${machineType.imgUrl}"></div><div class="fault-txt1 clearfix"><p>${machineType.name}<span></span></p><p class="fault-Intro"></p></div></div><div class="fault-main2 fault-color">
           <div class="fault-tit">请选择颜色或类型尺寸</div>
           <ul>
           	<c:forEach items="${machineColors }" var="machineColor">
           	   <li value="${machineColor.id}">${machineColor.name}</li><input type="radio" name="pr_radio" id="pr_radio_${machineColor.id}" value="${machineColor.id}" style="display:none">
           	</c:forEach>
           </ul></div><input type="hidden" name="pc_id" id="pc_id" value="1"><input type="hidden" name="pm_id" id="pm_id" value="6"><input type="hidden" name="orderid" id="orderid" value="0">
            <div class="fault-main2 fault-info">
                <div class="fault-tit">选择故障</div>
                <ul>${machineBugsHtml}</ul>
            <div class="create-mask"></div></div>
            <div class="fault-btn"><a onclick="submitPhone()">下一步</a></div>
        </section>
    </main>
    <%@include file="../inc/mtFooter.jsp" %>
    <script>
    seajs.use('app/mobile', function(mobile) {
        mobile.contact('.wrap', '.content-info');
        mobile.fault();
    });
    seajs.use(['app/scale', 'app/handler'], function(scale, handler) {
        scale.calcScale();
        handler.setUl('.choice-pro ul');
    });
    function submitPhone(){
    	seajs.use('app/mobile', function(mobile) {
            mobile.submitPhone();
        });
    }
    </script>
</body>
</html>