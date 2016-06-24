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
    <c:if test="${type=='apple'}">
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/model.js"></script>
    </c:if>
    <c:if test="${type=='reply'}">
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/reply.js"></script>
    </c:if>
    <title>请选择机型</title>
</head>
<body style="display: block;" oncontextmenu='return false'   ondragstart='return false'   onselectstart ='return false'   onselect='document.selection.empty()'   oncopy='document.selection.empty()'   onbeforecopy='return false'  onmouseup='document.selection.empty()'>
    <%@include file="../inc/mtNav.jsp" %>
    <c:if test="${type=='apple'}">
    <section class="choice-pro-tit">左右滑动选择机型</section>
    <section class="choice-pro">
        <ul class="clearfix" style="width: 25.2rem;">
           <c:forEach items="${machineTypes}" var="machineType">
                <li>
	                <a href="${rootUrl}/apple/machineBug?machineTypeId=${machineType.id}"><img src="${rootUploadImgUrl}${machineType.imgUrl}">${machineType.name}</a>
	            </li>
           	</c:forEach>
        </ul>
    </section>
    <%@include file="../inc/mtFooter.jsp" %>
    </c:if>
    <c:if test="${type=='reply'}">
    <section class="choice-pro-tit">故障分析(点击图片查看报价单)</section>
    <section class="choice-pro">
        <ul class="clearfix" >
           <c:forEach items="${machineTypes}" var="machineType">
           <div style="width: 40rem;">
                <li style="width: 6rem;">
	               <a href="${rootUrl}/reply/openRecoveryPrice"><img src="${rootUploadImgUrl}${machineType.imgUrl}"></a>
	               <div style="width: 6rem;text-align:justify;width:85%;margin: auto;line-height:150%;margin-top:-10px;font-size: 15px;">${machineType.description}</div>
	            </li>
	       </div>
           	</c:forEach>
        </ul>
    </section>
    <%@include file="../inc/reFooter.jsp" %>
    </c:if>
    <script>
        seajs.use(['app/scale', 'app/handler'], function(scale, handler) {
            scale.calcScale();
            handler.setUl('.choice-pro ul');
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