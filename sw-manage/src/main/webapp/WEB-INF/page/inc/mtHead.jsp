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
<c:set var="rootUrl" value="<%=rootUrl%>"></c:set>
<c:set var="rootImgUrl" value="http://o7zhzk6dq.bkt.clouddn.com/mt/"></c:set>
<c:set var="rootUploadImgUrl" value="http://o7zhzk6dq.bkt.clouddn.com/mt/upload/"></c:set>
    <script type="text/javascript">var CONTEXT_PATH='<%=rootUrl%>';</script>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="<%=rootUrl %>/resources/css/mt/style.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootUrl %>/resources/css/mt/sweetalert.css"/>
    <!-- Favicons -->
    <link rel="icon" href="<%=rootUrl %>/resources/images/logo.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="<%=rootUrl %>/resources/images/logo.ico" type="image/x-icon" />
    <!-- JS -->
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/mt/sea.js"></script>
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/mt/inc/zepto.js"></script>
    <script type="text/javascript" src="<%=rootUrl %>/resources/js/mt/sweetalert.min.js"></script>
    <script>
    seajs.config({
    	base: '${rootUrl}/resources/js/mt/inc',
        alias: {
            'zepto': 'module/zepto',
            exports: 'Zepto'
        }
    });
    </script>
