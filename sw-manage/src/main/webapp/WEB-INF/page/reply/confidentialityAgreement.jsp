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
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/reply.js"></script>
    <title>签订保密协议</title>
</head>
<body style="display: block;" oncontextmenu='return false'   ondragstart='return false'    onbeforecopy='return false' >
    <%@include file="../inc/mtNav.jsp" %>
    <main class="wrap">
        <section class="content-type">
           <div class="about-title">签订保密协议</div><div class="about-content">
<p><strong> 免费咨询检测 /数据绝对性保密/ 恢复不成功不收费  /只读操作杜绝二次破坏/</strong></p>
<p>&nbsp; 免费检测<br />
&nbsp;&nbsp;&nbsp;&nbsp;您可以把硬盘送到E-ZONE易族数据恢复中心，我们的数据恢复工程师为您免费检测，大多数情况下检测时间需要十五分钟或者半个小时以内。对于个别磁盘，需要开盘才能确定，所以需要的时间长一些，最长不超过半个工作日。</p>
<p>&nbsp;恢复前报价<br />
&nbsp;&nbsp;&nbsp;&nbsp;我们会根据检测结果，给您一个明确报价和恢复所需要的时间，以及您需要的准备工作。</p>
<p>&nbsp;只读操作<br />
&nbsp;&nbsp;&nbsp;&nbsp;E-ZONE易族数据恢复中心的工程师，接受过专业的技能培训以及道德教育，不会对用户媒介做任何写操作，即使我处不能恢复，也保证不对用户数据二次破 坏，对有坏扇区的盘，先镜像后进行数据恢复，防止因坏扇区扩散而进一步丢失数据，省去了客户不必要的麻烦。</p>
<p>&nbsp; 安全可靠<br />
&nbsp;&nbsp;&nbsp;&nbsp;签定《数据恢复保密协议》，对于重要数据，可以和用户签定《数据恢复保密协议》，如果您的数据在我公司被泄露，我公司承担一切法律责任。对于敏感数据，用户可以全程监视数据恢复全过程。</p>
<p>上门取盘服务<br />
&nbsp;&nbsp;&nbsp;&nbsp;我们提供北京市区内的上门取盘服务。</p>
<p>&nbsp;免费备份三天数据<br />
&nbsp;&nbsp;&nbsp;&nbsp;如果恢复出来的数据，在三天内再次丢失，我们会提供免费的拷贝数据服务。</p>
<p>&nbsp;恢复成功收取费用<br />
&nbsp;&nbsp;&nbsp;&nbsp;客户验证数据并拷贝数据后，才收取服务费用。</p>
<p>&nbsp;成功率高<br />
&nbsp;&nbsp;&nbsp;&nbsp;经验所得：无论是什么原因造成的数据丢失，数据恢复的成功率可达85%以上。对于软件原因造成的数据丢失，只要数据没有没真正覆盖，数据恢复的成功率可达 100%，对于物理原因的造成的数据丢失，只要盘片没有被划坏，数据恢复的成功率可达90%以上。对于磁盘阵列，只要没有初始化和非法的Rebuild, 数据恢复的成功率可达100%。</p>
</div>
        </section>
    </main>
    <%@include file="../inc/reFooter.jsp" %>
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