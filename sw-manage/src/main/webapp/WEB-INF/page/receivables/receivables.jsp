<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>商品入库录入</title>
<body style="text-align:center">
    <form id="insertBillForm">
    	<div style=" margin:0 auto; width:850px;">
    		<table>
    			<tr>
    				<td>订单号:</td>
    				<td><input class="easyui-datebox" data-options="required:true" name="createTime" id="createTime"></input><span class="spanRequired">*</span></td>
    				<td>联系人:</td>
    				<td>
    					<select name="type" class="easyui-combobox"  data-options="validType:'selectValueRequired'" name="type" style="width: 200px;">
                				<option value="">--请选择--</option>
                    		<c:forEach items="${billRKTypes }" var="billRKType">
                        		<option value="${billRKType }" >${billRKType.info }</option>
                   			</c:forEach>
                		</select>
    					<span class="spanRequired">*</span>
    				</td>
    			</tr>
    			<tr>
    				<td>财务类别</td>
    				<td></td>
    				<td>收款类型</td>
    				<td></td>
    			</tr>
    			<tr>
    				<td>收款金额</td>
    				<td></td>
    				<td>收款日期</td>
    				<td></td>
    			</tr>
    			<tr>
    				<td>是否开票</td>
    				<td></td>
    				<td>发票号</td>
    				<td></td>
    			</tr>
    			<tr>
    				<td>经手人</td>
    				<td></td>
    				<td>收款类型</td>
    				<td></td>
    			</tr>
    		</table>
    	</div>
    </form>
    </body>