<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>商品入库录入</title>
<body style="text-align:center">
    <form id="updateBillForm">
    	<div style=" margin:0 auto; width:850px;">
    		<table>
    			<tr>
    				<td>入库日期:</td>
    				<td>
    					<input class="easyui-datebox"  value="${billView.createTime}" data-options="required:true" name="createTime" id="createTime"></input>
    					<span class="spanRequired">*</span>
    					<input type="hidden"  name="id" value="${billView.id}">
    				</td>
    				<td>入库类别:</td>
    				<td>
    					<select name="type" class="easyui-combobox"  data-options="validType:'selectValueRequired'" name="type" style="width: 200px;">
                			<option value="">--请选择--</option>
                    			<c:forEach items="${billRKTypes}" var="billRKType">
                        		<option value="${billRKType}" <c:if test="${billView.type==billRKType}">selected="selected" </c:if>>${billRKType.info}</option>
                   			</c:forEach>
                		</select>
    					<span class="spanRequired">*</span>
    				</td>
    				<td><label>经手人:</label></td>
    				<td>
    					<select name="createUserId" name="createUserId" data-options="validType:'selectValueRequired'" class="easyui-combobox"  style="width: 200px;">
    						<option value="">--请选择--</option>
    						<c:forEach items="${usersList }" var="list">
                        		<option value="${list.id}" <c:if test="${billView.createUserId==list.id}">selected="selected" </c:if>>${list.nickName }</option>
                   			</c:forEach>
                   		</select>
    					<span class="spanRequired">*</span>
    				</td>
    			</tr>
    			<tr>
    				<td colspan="3" style="font-family:宋体;color: red;">入库单号为：${billView.orderNumber}<input name="orderNumber" value="${billView.orderNumber}" type="hidden"></td>
    			</tr>
    		</table>
    	</div>
    </form>
    	<table id="updateBilldatagrid" style="width: 98%;" class="easyui-datagrid"></table>
    	<div id="openCommodityDialog"></div>
    	<script type="text/javascript">
    	$(function() {
    	    $('#updateBilldatagrid').datagrid({
    	    	 method:"POST",
    	    	url:"<%=rootUrl%>/commodityBill/commodityBillList.grid?billId="+'${billView.id}',
    	        columns: [[
				{
				    field: 'id',
				    title: '单号商品id',
				    width: "10%",
				},
				{
				    field: 'categoryName',
				    title: '商品类型',
				    width: "15%",
				},
    	        {
    	            field: 'commodityName',
    	            title: '商品名称',
    	            width: "20%",
    	        },
    	        {
    	            field: 'companyType',
    	            title: '单位',
    	            width: "10%",
    	        },
    	        {
    	            field: 'commodityNumber',
    	            title: '数量',
    	            width: "15%",
    	            editor: {
    	                type: 'validatebox',
    	                options: {
    	                    validType: 'unsignedint',
    	                    required:true
    	                }
    	            }
    	        },
    	        {
    	            field: 'unitPrice',
    	            title: '单价',
    	            width: "15%",
    	            editor: 'text',
    	            editor: {
    	                type: 'validatebox',
    	                options: {
    	                    validType: 'money',
    	                    required:true
    	                }
    	            }
    	        },
    	        {
    	            field: 'action',
    	            title: '操作',
    	            width: "15%",
    	            align: 'center',
    	            formatter: function(value, row, index) {
    	                if (row.editing) {
    	                    var s = '<img  href="#" onclick="saverow(' + index + ')" src="<%=rootUrl %>/resources/images/tick.png" title="保存" style="cursor:pointer">&nbsp;';
    	                    var c = '<img href="#" onclick="cancelrow(' + index + ')" src="<%=rootUrl %>/resources/images/arrow_redo.png" title="取消" style="cursor:pointer">';
    	                    return s + c
    	                } else {
    	                    var e = '<img href="#" onclick="editrow(' + index + ')" src="<%=rootUrl %>/resources/images/user_edit.png" title="编辑" style="cursor:pointer">&nbsp;';
    	                    var d = '<img href="#" onclick="deleterow(' + index + ')" src="<%=rootUrl %>/resources/images/cross.png" title="删除" style="cursor:pointer">';
    	                    return e + d
    	                }
    	            }
    	        }]],
    	        border:false,
                rownumbers:true,
                fitColumns:true,
                singleSelect:true,
                fit:true,
                pagination: true,
                striped:true,
    	        toolbar: [{
    	            text: '添加商品',
    	            iconCls: 'icon-add',
    	            handler: addUpdateRows
    	        },
    	        {
    	            text: '保存',
    	            iconCls: 'icon-save',
    	            handler: saveall
    	        }],
    	        onBeforeEdit: function(index, row) {
    	            row.editing = true;
    	            $('#updateBilldatagrid').datagrid('refreshRow', index);
    	            editcount++
    	        },
    	        onAfterEdit: function(index, row) {
    	            row.editing = false;
    	            $('#updateBilldatagrid').datagrid('refreshRow', index);
    	            editcount--
    	        },
    	        onCancelEdit: function(index, row) {
    	            row.editing = false;
    	            $('#updateBilldatagrid').datagrid('refreshRow', index);
    	            editcount--
    	        }
    	    });
    	});
    	var editcount = 0;
    	function editrow(index) {
    	    $('#updateBilldatagrid').datagrid('beginEdit',index);
    	}
    	function deleterow(index) {
    	    $.messager.confirm('确认', '您确定删除吗?',
    	    function(r) {
    	        if (r) {
    	            $('#updateBilldatagrid').datagrid('deleteRow', index);
    	            $('#updateBilldatagrid').datagrid('refreshRow',index);
    	        }
    	    })
    	}
    	function saverow(index) {
    		var row=$('#updateBilldatagrid').datagrid("getSelected");
            if(row){
            	$('#updateBilldatagrid').datagrid('endEdit', index);
                $.messager.confirm('提示','您确定修改此信息吗？',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"<%=rootUrl%>/commodityBill/update",
                            type:"POST",
                            dataType:"json",
                            data:{id:row.id,commodityNumber:row.commodityNumber,unitPrice:row.unitPrice},
                            beforeSend:function(){
                                showLoading();
                            },
                            success:function(data) {
                                hideLoading();
                                if(0==data.code){
                                    $.messager.show({
                                        title:'提示',
                                        msg:data.msg
                                    });
                                    $('#updateBilldatagrid').datagrid('reload');
                                } else {
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
                });
            } else {
                $.messager.show({
                    title : '提示',
                    msg : '请选择一条记录.'
                });
            }
    	}
    	function cancelrow(index) {
    	    $('#updateBilldatagrid').datagrid('cancelEdit', index)
    	}
    	//新增操作
    	function addUpdateRows() {
    		$('#openCommodityDialog').dialog({
                title: '商户列表',
                width: 800,
                height: 600,
                closed: false,
                cache: false,
                href: '<%=rootUrl%>/currency/commodityList?type='+1+"&billId="+'${billView.id}',
                modal: true,
                onClose: function () {  
                	$(this).dialog('destroy');//销毁  
                }
            });
    	}
    	function saveall() {
    	    if (editcount > 0) {
    	        $.messager.alert('警告', '当前还有' + editcount + '记录正在编辑，不能保存数据。');
    	        return false;
    	    }
    		//获取所有加载的数据
    		var row=$('#updateBilldatagrid').datagrid("getData");
    		  //保存
            	enableValidateWhenSubmit('updateBillForm');
                if($('#updateBillForm').form('validate')){
                	var data={bill:$('#updateBillForm').serialize(),commodityNames:row.rows[0]};
                	//获取时间
                	var createTime = $('#createTime').datebox('getValue');
                	var obj = [];
                	for(var i=0;i<row.rows.length;i++) {
                	   var person = {};
                	   //商品id
                	   person.commodityId = row.rows[i].commodityId;
                	   //数量
                	   person.commodityNumber = row.rows[i].commodityNumber;
                	   //单价
                	   person.unitPrice = row.rows[i].unitPrice;
                	   obj.push(person);
                	}
                    $.ajax({
                        async:false,
                        url:"<%=rootUrl%>/bill/updateCommodityBill?billObj="+JSON.stringify(obj)+"&bill="+$('#updateBillForm').serialize()+"&createTimes="+createTime,
                        type:"POST",
                        dataType:"json",
                        data:null,
                        beforeSend:function() {
                            showLoading();
                        },
                        success:function(data) {
                            hideLoading();
                            if (0==data.code) {
                                $.messager.show({
                                    title:'提示',
                                    msg:data.msg
                                });
                                $('#updateBillDialog').dialog("close");
                                $("#tblBill").datagrid("reload");
                            } else {
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
    	function cancelall() {
    	    $('#updateBilldatagrid').datagrid('rejectChanges')
    	}
    	</script>
    </body>