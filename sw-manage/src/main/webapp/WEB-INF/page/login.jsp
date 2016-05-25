<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%@include file="./inc/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="<%=rootUrl %>/resources/css/style-comm.css" />
    <title>后台管理登录页</title>
    <script>
	    $(function(){
	        disableValidateWhenInit('fmLogin');
	        
	        <c:if test="${not empty error}">
    			$.messager.alert("操作提示", "${error}","error");
			</c:if>
			
			document.onkeydown = function(event_e){
				if(window.event) {
					event_e = window.event;
				}
				var int_keycode = event_e.charCode||event_e.keyCode;
				if( int_keycode == '13' ) {
					doLogin();
					return false;
				}
			}
	    });
	  	//提交登录请求
	    function doLogin(){
	        enableValidateWhenSubmit('fmLogin');
	        if($("#fmLogin").form('validate')){
	        	$("#fmLogin").submit();
	        }
	    }
	  	//验证码
	  	//function changeCode() { 
	  		//var imgNode = document.getElementById("vimg");
	  		//imgNode.src = "servlet/AuthImageServlet?t=" + Math.random();
	  	//}
    </script>
</head>
<body style="text-align: center; background-color: #eee;">
    <table style="width: 450px; margin-top: 200px; border: 0; background-color: #a11e2c;">
        <tr>
            <td>
                <div>
                    <div style="margin-left: 30px; float: left;">
                        <img src="<%=rootUrl %>/resources/images/logo(1).png" style="height: 50px;"/>
                    </div>
                    <div style="font-weight: bold; font-size: 22px; color: #fff; margin-top: 10px; float: right; margin-right: 30px;">E-ZONE后台管理平台</div>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="easyui-panel" style="width: 450px; padding: 10px 20px" data-options="noheader:true">
                    <form id="fmLogin" class="fm" method="post">
                        <div class="fitem">
                            <label>账户号码 : </label>
                            <input id="username" name="username" class="easyui-validatebox" data-options="required:true,validType:['loginName']"/>
                        </div>
                        <div class="fitem">
                            <label>登录密码 : </label>
                            <input id="password" name="password" class="easyui-validatebox" type="password" data-options="required:true,validType:['password']"/>
                        </div>
                        <!--  
                        <div class="fitem">
                            <label>验证码 : </label>
                            <input style="width: 100px;" id="randomCode" name="randomCode" class="easyui-validatebox" type="text" data-options=""/>
                            <img id="vimg"  title="点击更换" onclick="changeCode();" src="servlet/AuthImageServlet">
                        </div>
                        <br>
                        -->
                        <div class="fitem">
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="resetForm('fmLogin');">重置</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="doLogin();">登录</a>
                        </div>
                    </form>
                </div>
            </td>
        </tr>
    </table>
</body>
</html>