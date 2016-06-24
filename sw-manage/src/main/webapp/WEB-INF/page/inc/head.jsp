<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String rootUrl = request.getScheme()
        + "://" + request.getServerName()
        + ":" 
        + request.getServerPort()
        + request.getContextPath();
%>
    <script type="text/javascript">var CONTEXT_PATH='<%=rootUrl%>';</script>
    <c:set var="rootUrl" value="<%=rootUrl%>"></c:set>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="<%=rootUrl %>/resources/js/easyui/themes/default/app.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootUrl %>/resources/js/easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootUrl %>/resources/css/style-ui.css"/>
    <!-- JS -->
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/easyui/easyui-lang-zh_CN.js"></script>
     <script type="text/javascript" src="<%=rootUrl %>/resources/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/easyui-validate.js"></script>
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/mytool.js"></script>
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/ajaxfileupload.js?t=2015"></script>

    <!-- Favicons -->
    <link rel="icon" href="<%=rootUrl %>/resources/images/logo.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="<%=rootUrl %>/resources/images/logo.ico" type="image/x-icon" />
	<script type="text/javascript">
		$(function(){
			$("div.datagrid-mask-msg", window.parent.document).hide();
			$("div.tabs-mask-div", window.parent.document).hide();
		});
	</script>