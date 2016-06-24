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
    <title>数据恢复报价单</title>
</head>
<body style="display: block;" oncontextmenu='return false'   ondragstart='return false'   onselectstart ='return false'     onbeforecopy='return false' >
    <%@include file="../inc/mtNav.jsp" %>
    <main class="wrap">
        <section class="content-type">
           <div class="about-title">数据恢复报价单</div>
           <div class="about-content">
           <table align="center" border="1" cellpadding="1" cellspacing="1" style="border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;">
	<tbody>
		<tr>
			<td>项目</td>
			<td>说明</td>
			<td><strong>系统及存储介质</strong></td>
			<td><strong>收费标准</strong></td>
		</tr>
		<tr>
			<td colspan="1" rowspan="4">逻辑故障</td>
			<td colspan="1" rowspan="4">系统覆盖、PQ及破坏、文件<br />
			删除、分区格式化、分区丢失<br />
			病毒破坏、目录结构损坏等</td>
			<td>数码闪存/SD卡/CF卡/U盘/记忆棒/录音笔/<br />
			光盘/软盘等</td>
			<td>200－400元/次</td>
		</tr>
		<tr>
			<td>普通IDE、SATA硬盘</td>
			<td>400元/块起</td>
		</tr>
		<tr>
			<td>笔记本或移动硬盘</td>
			<td>400元/块起</td>
		</tr>
		<tr>
			<td>SCSI硬盘</td>
			<td>1000元/块起</td>
		</tr>
		<tr>
			<td colspan="1" rowspan="3">硬物理故障&nbsp;<br />
			（无需开盘）</td>
			<td colspan="1" rowspan="3">硬盘坏道及固件信息丢失&nbsp;<br />
			（视坏道多少及恢复难度<br />
			最后定价）</td>
			<td>普通IDE、SATA硬盘</td>
			<td>600元~800元</td>
		</tr>
		<tr>
			<td>笔记本或移动硬盘</td>
			<td>600元~800元</td>
		</tr>
		<tr>
			<td>SCIS硬盘</td>
			<td>1500元~2000元</td>
		</tr>
		<tr>
			<td colspan="1" rowspan="3">硬物理故障&nbsp;<br />
			开盘处理</td>
			<td colspan="1" rowspan="3">开盘更换磁头</td>
			<td>80GB以下</td>
			<td>800元/块起</td>
		</tr>
		<tr>
			<td>320GB及以上</td>
			<td>1500元/块起</td>
		</tr>
		<tr>
			<td>SCSI硬盘</td>
			<td>2000元/块起</td>
		</tr>
		<tr>
			<td colspan="1" rowspan="4">Raid磁盘阵<br />
			列服务器</td>
			<td colspan="1" rowspan="4">Raid0、Raid1、Raid0+1、&nbsp;<br />
			Raid5、Raid6、NAS</td>
			<td>Windows系统</td>
			<td>800元/块起</td>
		</tr>
		<tr>
			<td>Linux系统、BSD</td>
			<td>1200元起/块</td>
		</tr>
		<tr>
			<td>Unix系统(Solaris、SCO等)</td>
			<td>1500元起/块</td>
		</tr>
		<tr>
			<td>Unix(Solaris、SCO等)采用裸设备(裸分区)</td>
			<td>1500元起/块</td>
		</tr>
		<tr>
			<td>文件解密</td>
			<td>密码在8位以内</td>
			<td>Word、Excel、rar、zip等</td>
			<td>200元/个</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

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