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
    <title>数据恢复工程师忠告</title>
</head>
<body style="display: block;" oncontextmenu='return false'   ondragstart='return false'   onselectstart ='return false'     onbeforecopy='return false' >
    <%@include file="../inc/mtNav.jsp" %>
    <main class="wrap">
        <section class="content-type">
           <div class="about-title">数据恢复工程师忠告</div><div class="about-content"><p>
<p><br>1、选择一家专业的数据恢复公司是非常必要的。在我们接到的二次恢复当中，大多是因为非专业人员的误操作引起数据二次破坏，增加恢复难度。</p>
<p><br>2、遇到硬盘落水，火烧，摔落以后绝对不要对故障盘加电。会加深硬盘的损坏程度。</p>
<p><br>3、如果硬盘在启动时发出“咯哒咯哒”的声音，则意味着伺服信息丢失，磁头找不到0磁道，应马上断电。否则可能损坏硬盘里的0磁道限位器。</p>
<p><br>4、任何存储介质，包括硬盘、磁带、光盘、及各种移动存储设备，数据删除、格式化、误ghost、病毒破坏以后，尽量不要尝试自己用免费软件扫描，因为很多免费软件扫描的过程中会往故障盘里面写入数据，覆盖丢失的数据。造成严重后果。</p>
<p><br>5、当分区提示“未格式化”、“文件或目录损坏且无法读取”、“无法读取”等错误时，请不要对其做任何操作。很多操作只能解决小问题。但却能对数据造成非常大的破坏。</p>
<p><br>6、不要给你认为已经损坏的硬盘不断地加电断电，这样只会适得其反。</p>
<p><br>7、不要试图用敲击或抛掷硬盘来让硬盘“松开”或重新转起来。</p>
<p><br>8、不要拆掉硬盘外壳，硬盘对空气的洁净度要求非常高，普通环境开盘后会产生大量坏道。造成数据永久性丢失，及时是脑外科手术室的环境也达不到开盘的环境要求。</p>
<p><br>9、不要让硬盘靠近高温。</p>
<p><br>10、按时备份数据。</p>
<p><br>11、当送数据恢复公司恢复时，要给于恰当包装，恰当的包装能保证数据不被二次破坏。</p>
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