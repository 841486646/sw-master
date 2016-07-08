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
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/model.js"></script>
    <title>关于我们</title>
</head>
<body style="display: block;" oncontextmenu='return false'   ondragstart='return false'   onselectstart ='return false'     onbeforecopy='return false' >
    <%@include file="../inc/mtNav.jsp" %>
    <main class="wrap">
        <section class="content-type">
           <div class="about-title">关于我们</div><div class="about-content">
           <p><strong><br><img src="${rootImgUrl}20141222234317903.jpg" alt=""></strong></p>
           		<c:if test="${type==0}">
				<p>我们维修技术人员均来自苹果产品代工厂富士康，对苹果手机系列产品的原理构造软硬件都十分的了解，可以迅速准确的判断出故障,维修配件一律采用原厂配件拒绝使用仿冒兼容配件，维修过程全程透明，更换维修配件时严格把关，拆机过程也是力求做到精益求精，尽量不损伤机器的外观，对iphone全系列维修服务包括：
				<span style="color: red;">液晶屏更换、进水维修、不开机、死机定屏、关机自动开机、关不了机、无信号、不读卡、不充电、HOM键失灵、 USB无法识别、不连机、触摸屏失灵、电池更换、听筒无声、小声、主板芯片维修等.</span></p>
				<p><strong style="color:red;"><br>维修过程透明化,24小时无死角监控,过程客户看在眼里,明确双方责任,避免尴尬产生,承接北京同行屏幕代加工.</strong></p>
				<p><strong><br><img src="${rootImgUrl}yejing.jpg" alt=""></strong></p>
				<p><strong><br><img src="${rootImgUrl}2013516213042362.jpg" alt=""></strong></p>
				<p><span style="color: rgb(0, 0, 255)">技术过硬：</span>十年的技术积淀,公司每年选派技术工程师深入富士康代工厂进行技术培训。</p>
				<p><span style="color: rgb(0, 0, 255)">设备：</span>先后引进各类维修设备（主板BGA返修台、日立示波器、超净无尘工作台)。</p>
				<p><span style="color: rgb(0, 0, 255)">配件：</span>具有充足的配件供货渠道，维修配件一律采用原厂配件，拒绝使用仿冒兼容配件。</p>
				<p><span style="color: rgb(0, 0, 255)">维修质量：</span>提供修复更换件后保修期三个月，根据不同故障的维修，提供对应的免费保修服务让您真正的售后无忧!</p>
				<p><span style="color: rgb(0, 0, 255)">维修价格：</span>我们的收费绝对是合理的。所以在选择维修时（不少商家甚至低于成本报价，最后的实际维修价格惊人!）价格尽量不要一味的追求最低化，我们在保证自身利润的同时为客户提供更良好的服务!并可以提供免费检测，如果客户不维修或者维修不成功不收取任何检测费用，低价格的维修，在信誉和质量上都很难得以保障。</p>
				<p><span style="color: rgb(0, 0, 255)">效率上:</span>现场维修,全程观看，小问题不隔夜，立等可取，大问题不隔天，次日可交货；外地客户，我们签约顺丰速运，提供上门取货服务，全国范围内一般3-4个工作日即可完成交.</p>
				<p><strong><br><img src="${rootImgUrl}2013516213153935.jpg" alt=""></strong></p>
				<p style="color: red;">iPhone专业换液晶 换触屏 外屏保证质量 现场更换 立等可取！全城最低价！</p>
				<p>1，苹果 <span style="color: red;">iPhone4、iPhone4S、iPhone5、iPhone5s、iPhone6、iPhone6 plus</span>显示屏 触屏 液晶 更换，摔坏 进水 不显示 白屏，现场快速更换。保证原装正品！</p>
				<p>2，苹果 <span style="color: red;">iPhone3G iPhone 3GS</span>显示屏 触屏更换，保证原装 价格最低！</p>
				<p>3，苹果 <span style="color: red;">ipad</span>显示屏 触屏 更换，价格最低 保证原装 质量最好！</p>
				<p style="color:red; ">本维修中心所有显示屏均为原厂订货，质量有保证价格最低！现场更换 立等可取！</p>
				<p>当面拆机 当面更换,所有苹果屏幕原装正品</p>
				<p><strong><span style="color: red;">免费咨询热线：</span><span style="color: rgb(0, 0, 255)">400-6810-208</span></strong></p>
				</c:if>
				<c:if test="${type==1}">
					<p>&nbsp;&nbsp;北京易族恒信科技有限公司03年创建，是北京为数不多最早从事电脑硬盘数据恢复领域的公司。本公司拥有多名专职电脑芯片级维修人员，有着丰富的硬盘维修和数据恢复经验。为适应科技潮流，为了不懈追求技术上的提升。公司自成立以来不断投资于技术创新及设备的更新，先后引进各类维修设备（超净无尘工作台、俄罗斯硬盘数据恢复工具PC-3000 UDMA卡等)。并每年定期派员深入厂家进行新技术培训。不断的完善技术服务体系，全面培养专业的技术人才，打造一支优质、实力雄厚的IT专业服务团队。 公司坚持以专业级精湛的技术实惠的价格、完善优质的服务来赢得客户的信赖。同时也是中小企业和个人用户优质的服务商。</p>
					<p><strong><br><img src="${rootImgUrl}jd.gif" alt=""></strong></p>
				</c:if>
			</div>
        </section>
    </main>
    <c:if test="${type==0}">
    <%@include file="../inc/mtFooter.jsp" %>
    </c:if>
	<c:if test="${type==1}">
    <%@include file="../inc/reFooter.jsp" %>
    </c:if>
    <script>
        seajs.use(['app/scale', 'app/handler'], function(scale, handler) {
            scale.calcScale();
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