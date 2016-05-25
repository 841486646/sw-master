<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="./inc/head.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%=rootUrl%>/resources/css/style-comm.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootUrl%>/resources/css/menu.css">
    <title>后台管理主页</title>
    
</head>
<body class="easyui-layout">
    <div data-options="region:'north',title:'',split:true" style="overflow: hidden; height: 70px; background-color: #95B8E7;">
        <div style="margin-left: 30px; float: left; margin-top: 5px;">
        	<img src="<%=rootUrl%>/resources/images/logo(1).png" style="height: 50px;">
        </div>
        <div style="font-weight: bold; font-size: 20px; color: #fff; margin-top: 15px; float: left; margin-left: 15px;">E-ZONE后台管理平台</div>
        <div style="color: #fff; margin-right: 30px; margin-top: 15px; float: right; vertical-align: middle;">
            <form id="fmMenu" method="post"></form>
            <span style="font-size: 16px; font-weight: normal; line-height: 16px;" id="Localnowtime"></span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="font-size: 16px; font-weight: normal; line-height: 16px;">欢迎你：${empty user.nickName ? user.loginName : user.nickName}</span>
            &nbsp;&nbsp;&nbsp;
            <a style="font-size: 16px; color: #fff; font-weight: normal;" target="_top" href="<%=rootUrl%>/logout">退出</a>
        </div>
    </div>
    <div data-options="region:'west',title:'导航菜单',split:true" style="width: 200px;">
        <%@ include file="./inc/menu.jsp"%>
    </div>
    <div data-options="region:'center',title:''" style="padding: 5px;">
        <div id="workTabs" class="easyui-tabs" data-options="tools:'#tabTools',fit:true,border:false">
            <div title="欢迎" style="padding: 10px">
                <p style="font-size: 16px;">欢迎使用E-ZONE后台管理平台.</p>
                <br>
                <ul>
                    <li style="font-size: 16px">系统管理：用户管理、角色管理、资源管理等；</li>
                </ul>
            </div>
        </div>
        <div id="tabTools">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="removeTabs();"></a>
        </div>
    </div>
    <div data-options="region:'south',split:true,title:''" style="height: 40px; background: #95B8E7; text-align: center; margin: 0 auto;">
        <span style="color: #fff; font-size: 12px; line-height: 30px;">版权所有：</span>
    </div>
    <script type="text/javascript">
	  	//添加新的Tab页
	    function addTab(params){
	        if($('#workTabs').tabs('exists',params.title)){
	            $('#workTabs').tabs('select',params.title);
	        }else{
	            var contentTxt='<iframe frameborder="0" src="'+params.url+'" style="width:100%;height:100%;"></iframe>';
	            $('#workTabs').tabs('add',{
	                title:params.title,
	                content:contentTxt,
	                closable:true
	            });
	            $("#workTabs").tabs("loading","正在加载，请稍后。。。");
	        }
	    }
	    //删除除欢迎页以外的所有Tab页
	    function removeTabs(){
	        var tabs=$('#workTabs').tabs('tabs');
	        if(tabs){
	            $(tabs).each(function(){
	                var index=$('#workTabs').tabs('getTabIndex',this);
	                if(0<index){
	                    $('#workTabs').tabs('close',index);
	                }
	            });
	        }
	    }
	    //当前时间
	 function showLocale(objD){
		var str,colorhead,colorfoot;
		var yy = objD.getYear();
			if(yy<1900) yy = yy+1900;
		var MM = objD.getMonth()+1;
			if(MM<10) MM = '0' + MM;
		var dd = objD.getDate();
			if(dd<10) dd = '0' + dd;
		var hh = objD.getHours();
			if(hh<10) hh = '0' + hh;
		var mm = objD.getMinutes();
			if(mm<10) mm = '0' + mm;
		var ss = objD.getSeconds();
			if(ss<10) ss = '0' + ss;
		var ww = objD.getDay();
			if  ( ww==0 )  colorhead="<font>";
			if  ( ww > 0 && ww < 6 )  colorhead="<font>";
			if  ( ww==6 )  colorhead="<font>";
			if  (ww==0)  ww="星期日";
			if  (ww==1)  ww="星期一";
			if  (ww==2)  ww="星期二";
			if  (ww==3)  ww="星期三";
			if  (ww==4)  ww="星期四";
			if  (ww==5)  ww="星期五";
			if  (ww==6)  ww="星期六";
		colorfoot="</font>"
		str = "当前时间："+colorhead + yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "  " + ww + colorfoot;
			return(str);
		}
			function tick(){
				var today;
				today = new Date();
				document.getElementById("Localnowtime").innerHTML = showLocale(today);
				window.setTimeout("tick()", 1000);
			}
		tick();  
    </script>
</body>
</html>