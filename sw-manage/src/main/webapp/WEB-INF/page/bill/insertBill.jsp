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
    				<td>入库日期:</td>
    				<td><input class="easyui-datebox" data-options="required:true" name="createTime" id="createTime"></input><span class="spanRequired">*</span></td>
    				<td>入库类别:</td>
    				<td>
    					<select name="type" class="easyui-combobox"  data-options="validType:'selectValueRequired'" name="type" style="width: 200px;">
                				<option value="">--请选择--</option>
                    		<c:forEach items="${billRKTypes }" var="billRKType">
                        		<option value="${billRKType }" >${billRKType.info }</option>
                   			</c:forEach>
                		</select>
    					<span class="spanRequired">*</span>
    				</td>
    				<td><label>经手人:</label></td>
    				<td>
    					<select name="createUserId" name="createUserId" data-options="validType:'selectValueRequired'" class="easyui-combobox"  style="width: 200px;">
    						<option value="">--请选择--</option>
    						<c:forEach items="${usersList }" var="list">
                        		<option value="${list.id}">${list.nickName }</option>
                   			</c:forEach>
                   		</select>
    					<span class="spanRequired">*</span>
    				</td>
    			</tr>
    			<tr>
    				<td colspan="3" style="font-family:宋体;color: red;">入库单号为：${RKrandomNumber}<input name="orderNumber" value="${RKrandomNumber}" type="hidden"></td>
    			</tr>
    		</table>
    	</div>
    </form>
    	<table id="insertBilldatagrid" ></table>
    	<script type="text/javascript">
    	var users = {total:0,rows:[]}; 
    	$(function() {
    	    $('#insertBilldatagrid').datagrid({
    	        width: "100%",
    	        height: 450,
    	        singleSelect: true,
    	        columns: [[
    	                   {
    	       	            field: 'commodityId',
    	       	            title: '商品id',
    	       	            width: "15%",
    	       	        },{
    	            field: 'categoryName',
    	            title: '商品类型',
    	            width: "15%",
    	        },
    	        {
    	            field: 'commodityName',
    	            title: '商品名称',
    	            width: "15%",
    	        },
    	        {
    	            field: 'companyType',
    	            title: '单位',
    	            width: "15%",
    	            formatter:function(value,row,index){
                    	<c:forEach items="${companyTypes}" var="companyType">
                            if("${companyType}"==value){
                                return "${companyType.info}";
                            }
                		</c:forEach>
                    }
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
    	            width: "10%",
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
    	            width: "14%",
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
    	        toolbar: [{
    	            text: '添加商品',
    	            iconCls: 'icon-add',
    	            handler: addrow
    	        },
    	        {
    	            text: '保存',
    	            iconCls: 'icon-save',
    	            handler: saveall
    	        }],
    	        onBeforeEdit: function(index, row) {
    	            row.editing = true;
    	            $('#insertBilldatagrid').datagrid('refreshRow', index);
    	            editcount++
    	        },
    	        onAfterEdit: function(index, row) {
    	            row.editing = false;
    	            $('#insertBilldatagrid').datagrid('refreshRow', index);
    	            editcount--
    	        },
    	        onCancelEdit: function(index, row) {
    	            row.editing = false;
    	            $('#insertBilldatagrid').datagrid('refreshRow', index);
    	            editcount--
    	        }
    	    }).datagrid('loadData', users).datagrid('acceptChanges')
    	});
    	var editcount = 0;
    	function editrow(index) {
    	    $('#insertBilldatagrid').datagrid('beginEdit',index);
    	}
    	function deleterow(index) {
    	    $.messager.confirm('确认', '您确定删除吗?',
    	    function(r) {
    	        if (r) {
    	            $('#insertBilldatagrid').datagrid('deleteRow', index);
    	            $('#insertBilldatagrid').datagrid('refreshRow',index);
    	        }
    	    })
    	}
    	function saverow(index) {
    	    $('#insertBilldatagrid').datagrid('endEdit', index)
    	}
    	function cancelrow(index) {
    	    $('#insertBilldatagrid').datagrid('cancelEdit', index)
    	}
    	//新增操作
    	function addrow() {
    		$('<div></div>').dialog({
    			id: 'openCommodityDialog',
                title: '商户列表',
                width: 800,
                height: 500,
                closed: false,
                cache: false,
                href: '<%=rootUrl%>/currency/commodityList?type='+0,
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
    		var row=$('#insertBilldatagrid').datagrid("getData");
    		  //保存
            	enableValidateWhenSubmit('insertBillForm');
                if($('#insertBillForm').form('validate')){
                	var data={bill:$('#insertBillForm').serialize(),commodityNames:row.rows[0]};
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
                        url:"<%=rootUrl%>/bill/saveBill?billObj="+JSON.stringify(obj)+"&bill="+$('#insertBillForm').serialize()+"&createTimes="+createTime,
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
                                $('#insertBillForm').form("clear");
                                $('#insertBillDialog').dialog("close");
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
    	    $('#insertBilldatagrid').datagrid('rejectChanges')
    	}
    	</script>
    </body>