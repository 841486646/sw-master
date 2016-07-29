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
    <form id="insertReceivablesForm">
    	<div style="margin:0 auto; width:95%;">
    		<table cellpadding="5">
    			<tr>
    				<td>订单号：</td>
    				<td>
    					<span style="color: red;">${bView.orderNumber}</span>
    					<input name="billId" type="hidden" value="${bView.id}">
    					<input name="orderNumber" type="hidden" value="${bView.orderNumber}">
    				</td>
    				<td>订单总价：</td>
    				<td>
    					<span style="color: red;">${bView.totalPrice+bView.otherExpenses}</span>
    					<input id="totalPrice" name="totalPrice" type="hidden" value="${bView.totalPrice+bView.otherExpenses}">
    				</td>
    			</tr>
    			<tr>
    				<td>商品价格：</td>
    				<td>
    					<span style="color: red;">${bView.totalPrice}</span>
    				</td>
    				<td>其它费用：</td>
    				<td>
    					<span style="color: red;">${bView.otherExpenses}</span>
    				</td>
    			</tr>
    			<tr>
    				<td>财务类别：</td>
    				<td>
    					<select name="cType" class="easyui-combobox"  data-options="validType:'selectValueRequired'" style="width: 200px;">
                				<option value="">--请选择--</option>
                        		<option value="0" >销售收款</option>
                        		<option value="2" >维修收款</option>
                		</select>
    					<span class="spanRequired">*</span>
    				</td>
    				<td>收款类型：</td>
    				<td>
    					<select name="rType" class="easyui-combobox"  data-options="validType:'selectValueRequired'" style="width: 200px;">
                				<option value="">--请选择--</option>
                        		<option value="0" >支付宝</option>
                        		<option value="1" >银行卡</option>
                        		<option value="2" >微信</option>
                        		<option value="3" >其它</option>
                		</select>
    					<span class="spanRequired">*</span>
    				</td>
    			</tr>
    			<tr>
    				<td>实际收款金额：</td>
    				<td><input class="easyui-numberbox" data-options="required:true" name="receivablesPrice" style="width: 200px;"/></td>
    				<td>收款日期：</td>
    				<td><input class="easyui-datebox" data-options="required:true" id="receivablesTimes" style="width: 200px;"/></td>
    			</tr>
    			<tr>
    				<td>是否开票：</td>
    				<td>
    					<select name="isInvoice" id="isInvoice" class="easyui-combobox"  data-options="validType:'selectValueRequired',onSelect: function(rec){isInvoice()}" style="width: 200px;">
                				<option value="">--请选择--</option>
                        		<option value="0" >是</option>
                        		<option value="1" >否</option>
                		</select>
    					<span class="spanRequired">*</span>
    				</td>
    				<td>发票号：</td>
    				<td><input class="easyui-textbox"  id="invoiceNumbers" name="invoiceNumber" style="width: 200px;"/></td>
    			</tr>
    			<tr>
    				<td>经手人：</td>
    				<td>
    					<select  class="easyui-combobox"  data-options="validType:'selectValueRequired'" name="createUserId" style="width: 200px;">
                				<option value="">--请选择--</option>
                    		<c:forEach items="${usersList }" var="list">
                        		<option value="${list.id}" >${list.nickName}</option>
                   			</c:forEach>
                		</select>
    				</td>
    				<td>订单状态：</td>
    				<td>
    					<select  class="easyui-combobox"  data-options="validType:'selectValueRequired'" name="rState" style="width: 200px;">
                				<option value="">--请选择--</option>
                        		<option value="0" >收款完结</option>
                        		<option value="1" >收款未完结</option>
                		</select>
    				</td>
    			</tr>
    		</table>
    	</div>
    	<div id="toolbarDialogAddResource" style="text-align: center;">
        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="save();" style="width: 90px">保存</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#insertEquipmentAdminInfoDialog').dialog('close');" style="width: 90px">取消</a>
    	</div>
    </form>
    <script type="text/javascript">
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
        enableValidateWhenSubmit('insertReceivablesForm');
        if($('#insertReceivablesForm').form('validate')){
            $.ajax({
                async:false,
                url:"<%=rootUrl%>/receivables/saveReceivables?receivablesTimes="+receivablesTimes,
                type:"POST",
                data:$('#insertReceivablesForm').serialize(),
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
                        $('#showReceivablesAddDialog').dialog('close');
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