<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%
    String rootUrl = request.getScheme()
        + "://" + request.getServerName()
        + ":" 
        + request.getServerPort()
        + request.getContextPath();
	%>
    <title>销售/维修付款</title>
<body>
    <form id="updateReceivablesForm">
    	<div style="margin:0 auto; width:95%;">
    		<table cellpadding="5">
    			<tr>
    				<td>订单号：</td>
    				<td>
    					<span style="color: red;">${rView.orderNumber}</span>
    					<input name="id" type="hidden" value="${rView.id}">
    					<input name="billId" type="hidden" value="${rView.billId}">
    					<input name="orderNumber" type="hidden" value="${rView.orderNumber}">
    				</td>
    				<td>订单总价：</td>
    				<td>
    					<span style="color: red;">${rView.billTotalPrice+rView.otherExpenses}</span>
    					<input id="totalPrice" name="totalPrice" type="hidden" value="${rView.billTotalPrice+rView.otherExpenses}">
    				</td>
    			</tr>
    			<tr>
    				<td>商品价格：</td>
    				<td>
    					<span style="color: red;">${rView.billTotalPrice}</span>
    				</td>
    				<td>其它费用：</td>
    				<td>
    					<span style="color: red;">${rView.otherExpenses}</span>
    				</td>
    			</tr>
    			<tr>
    				<td>财务类别：</td>
    				<td>
    				<span style="color: red;"><c:if test="${rView.cType==0}">销售收款</c:if><c:if test="${rView.cType==1}">维修收款</c:if></span>
    				<input name="cType" type="hidden" value="${rView.cType}">
    				</td>
    				<td>收款类型：</td>
    				<td>
    					<select name="rType" class="easyui-combobox"  data-options="validType:'selectValueRequired'" style="width: 200px;">
                				<option value="">--请选择--</option>
                        		<option value="0" <c:if test="${rView.rType==0}">selected="selected"</c:if>>支付宝</option>
                        		<option value="1" <c:if test="${rView.rType==1}">selected="selected"</c:if>>银行卡</option>
                        		<option value="2" <c:if test="${rView.rType==2}">selected="selected"</c:if>>支微信</option>
                        		<option value="3" <c:if test="${rView.rType==3}">selected="selected"</c:if>>其它</option>
                		</select>
    					<span class="spanRequired">*</span>
    				</td>
    			</tr>
    			<tr>
    				<td>实际收款金额：</td>
    				<td><input class="easyui-numberbox" precision="2" value="${rView.receivablesPrice}" data-options="required:true" name="receivablesPrice" style="width: 200px;"/><span class="spanRequired">*</span></td>
    				<td>收款日期：</td>
    				<td><input class="easyui-datebox"  value="<fmt:formatDate value='${rView.receivablesTime}' pattern='YYYY-MM-dd' />" data-options="required:true" id="receivablesTimes" style="width: 200px;"/><span class="spanRequired">*</span></td>
    			</tr>
    			<tr>
    				<td>是否开票：</td>
    				<td>
    					<select name="isInvoice" id="isInvoice" class="easyui-combobox"  data-options="validType:'selectValueRequired',onSelect: function(rec){isInvoice()}" style="width: 200px;">
                				<option value="">--请选择--</option>
                				<option value="0" <c:if test="${rView.isInvoice==0}">selected="selected"</c:if>>是</option>
                				<option value="1" <c:if test="${rView.isInvoice==1}">selected="selected"</c:if>>否</option>
                		</select>
    					<span class="spanRequired">*</span>
    				</td>
    				<td>发票号：</td>
    				<td><input class="easyui-textbox" value="${rView.invoiceNumber}" id="invoiceNumbers" name="invoiceNumber" style="width: 200px;"/></td>
    			</tr>
    			<tr>
    				<td>经手人：</td>
    				<td>
    					<select  class="easyui-combobox"  data-options="validType:'selectValueRequired'" name="createUserId" style="width: 200px;">
                				<option value="">--请选择--</option>
                    		<c:forEach items="${usersList }" var="list">
                        		<option value="${list.id}" >${list.nickName}</option>
                        		<option value="${list.id}" <c:if test="${rView.createUserId==list.id}">selected="selected"</c:if>>${list.nickName}</option>
                   			</c:forEach>
                		</select>
                		<span class="spanRequired">*</span>
    				</td>
    				<td>订单状态：</td>
    				<td>
    					<select  class="easyui-combobox"  data-options="validType:'selectValueRequired'" name="rState" style="width: 200px;">
                				<option value="">--请选择--</option>
                				<option value="0" <c:if test="${rView.rState==0}">selected="selected"</c:if>>收款完结</option>
                				<option value="1" <c:if test="${rView.rState==1}">selected="selected"</c:if>>收款未完结</option>
                		</select>
                		<span class="spanRequired">*</span>
    				</td>
    			</tr>
    		</table>
    	</div>
    	<div id="toolbarDialogAddResource" style="text-align: center;">
        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="save();" style="width: 90px">修改</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showReceivablesUpdateDialog').dialog('close');" style="width: 90px">取消</a>
    	</div>
    </form>
    <script type="text/javascript">
    console.log('${rView.receivablesTime}');
    function isInvoice(){
    	 //获取是否选取发票值
        var isInvoice=$('#isInvoice').combobox('getValue');
    	 if(isInvoice==1){
    		 $('#invoiceNumbers').textbox('disable');
    	 }else{
    		 $('#invoiceNumbers').textbox('enable'); 
    	 }
    }
    
    //保存新增的资源
    function save(){
    	//订单总价格
    	var totalPrice=$("#totalPrice").val();
    	var receivablesTimes=$("#receivablesTimes").datebox("getValue");
        enableValidateWhenSubmit('updateReceivablesForm');
        if($('#updateReceivablesForm').form('validate')){
            $.ajax({
                async:false,
                url:"<%=rootUrl%>/receivables/updateReceivables?receivablesTimes="+receivablesTimes,
                type:"POST",
                data:$('#updateReceivablesForm').serialize(),
                dataType: "json",
                beforeSend: function(){
                    showLoading();
                },
                success:function(data){
                    hideLoading();
                    if(0==data.code){
                        $.messager.show({
                            title:'提示',
                            msg:data.msg
                        });
                        $('#showReceivablesUpdateDialog').dialog('close');
                        $("#tblSaleBill").datagrid("reload");
                    }else{
                        $.messager.show({
                            title:'错误',
                            msg:data.msg
                        });
                    }
                },
                error:function(xhr,status,e){
                    hideLoading();
                    //服务器响应失败时的处理函数
                    $.messager.show({
                        title:'错误',
                        msg:'服务器请求失败.'
                    });
                }
            });
        }
    }
    </script>
    </body>