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
    <title>填写个人信息</title>
</head>
<body style="display: block;">
	<%@include file="../inc/mtNav.jsp" %>
    <main class="wrap"><section class="fill-form"><ul><li><input type="text" placeholder="请输入您的姓名" name="name" id="name"></li>
    <li><input type="text" placeholder="请输入能联系到您的手机号（必填）" name="phone" id="phone"></li>
    <li><textarea class="fill-form-textarea" name="content" placeholder="请详细描述故障（必填）" id="content" cols="30" rows="3"></textarea></li>
    <li><input type="text" placeholder="请详细填写地址" name="address" id="address"></li>
    </ul><input type="hidden" name="orderid" id="orderid" value="0"><input type="hidden" name="pc_id" id="pc_id" value="1"><input type="hidden" name="pm_id" id="pm_id" value="6"><input type="hidden" name="pr_id" id="pr_id" value="7"><input type="hidden" name="pf_id" id="pf_id" value="7"><input type="hidden" name="machineBugIds" id="machineBugIds" value="${machineBugIds}"><div class="fill-form-btn">
    <p><input type="text" name="code" id="code">
    <img id="codeImg" onclick="javascript:refreshCode()" src="getValidCode" border="0" alt="验证码"></p>
    <p class="clause-main"><input type="checkbox" name="clause" id="clause" checked="" onclick="javascript:if(this.checked){document.getElementById(&quot;btn&quot;).className = &quot;&quot;;}else{document.getElementById(&quot;btn&quot;).className=&quot;disabled&quot;}">
            我同意<a href="${rootUrl}/apple/mtItem">维修条款</a></p>
    <button type="" id="btn">提交信息</button></div></section></main>
    <%@include file="../inc/mtFooter.jsp" %>
    <script>
    seajs.use('app/mobile', function(mobile) {
        mobile.contact('.wrap', '.content-info');
        mobile.writeInfo();
    });
    seajs.use(['app/scale', 'app/handler'], function(scale, handler) {
        scale.calcScale();
        handler.setUl('.choice-pro ul');
    });
    </script>
	<script>
    //验证码
    function refreshCode() {
        document.getElementById("codeImg").src = 'getValidCode?' + Date.parse(new Date());
    }
</script>
</body>
</html>