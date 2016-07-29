<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>入库管理</title>
    <!-- 工具栏 -->
    <div id="toolbarSaleTblBill">
        <div>
        	<shiro:hasPermission name="sale:bill:saveReceivables">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog(0);">添加销售订单</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sale:bill:saveReceivables">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog(1);">添加维修订单</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sale:bill:deleteReceivables">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteRole();">删除订单</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblSaleBill" style="width: 98%;" class="easyui-datagrid"></table>
    <div id="insertBillDialog"></div>
    <div id="showReceivablesAddDialog"></div>
    <!-- 维修修改 -->
    <div id="showReceivablesUpdateDialog"></div>
    <script type="text/javascript">
        $(function(){
            $("#tblSaleBill").datagrid({
                url:"<%=rootUrl%>/saleBill/commodityStorageList.grid",
                columns:[[
                    {field:'orderNumber',title:'销售单号',width:100},
                    {field:'scName',title:'维修/销售单名称',width:100},
                    {field:'totalPrice',title:'商品总价格',width:50,precision:2},
                    {field:'otherExpenses',title:'其它费用',width:50,precision:2},
                    {
                        field:'state',
                        title:'单号状态',
                        width:50,
                        formatter:function(value,data,index){
                            <c:forEach items="${billRKStates }" var="billRKState">
	                            if("${billRKState}"==value){
	                                return "${billRKState.info}";
	                            }
                        	</c:forEach>
                        }
                    },
                    {
                    	field:'createUserId',title:'经手人',width:80,
                    	formatter:function(value,data,index){
                    		<c:forEach items="${usersList}" var="lists">
                            if('${lists.id}'==value){
                                return '${lists.nickName}';
                            }
                       </c:forEach>
                        }
                    },
                    {
                        field:'type',
                        title:'类型',
                        width:50,
                        formatter:function(value,data,index){
                            <c:forEach items="${saleTypes }" var="saleType">
	                            if("${saleType}"==value){
	                                return "${saleType.info}";
	                            }
                        	</c:forEach>
                        }
                    },
                    {
                        field:'createTime',
                        title:'申请时间',
                        width:50,
                        formatter:function(value,data,index){
                            return fmtDate(value,"yyyy-MM-dd");
                        }
                    },{
                        field:'receivablesId',
                        title:'收款(请慎重操作)',
                        width:50,
                        formatter:function(value,data,index){
                        	if(value==null){
                        		return "<img onclick='showReceivablesAddDialog()' src='<%=rootUrl %>/resources/js/easyui/themes/icons/exclamation.png' title='结算单据，同时更新库存数量' style='cursor:pointer'/>";
                        	}else{
                        		return "<img onclick='showReceivablesUpdateDialog()' src='<%=rootUrl %>/resources/js/easyui/themes/icons/money_yen.png' title='结算单据，同时更新库存数量' style='cursor:pointer'/>";
                        	}
                        }
                    }
                ]],
                border:false,
                rownumbers:true,
                fitColumns:true,
                singleSelect:true,
                fit:true,
                pagination: true,
                striped:true,
                cache:false,
                toolbar:'#toolbarSaleTblBill'
            });
        });
        //删除供应商
        function deleteRole(){
            var row=$('#tblSaleBill').datagrid("getSelected");
            if(row.receivablesId!=null){
            	$.messager.alert("提示","此订单已有收款，禁止删除！您可以取消或者退回此订单！");
            	return false;
            }
            if(row){
                $.messager.confirm('提示','确定删除该数据吗?',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"<%=rootUrl%>/bill/deleteBill",
                            type:"POST",
                            dataType:"json",
                            data:{id:row.id},
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
                                    $('#tblSaleBill').datagrid('reload');
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
        
        //销售、维修增加
   function showReceivablesAddDialog(){
       var row=$('#tblSaleBill').datagrid("getSelected");
            if(row){
           $('#showReceivablesAddDialog').dialog({
                title: '销售/维修付款增加',
                width: 800,
                height: 300,
                closed: false,
                cache: false,
                href: '<%=rootUrl%>/receivables/toInsertReceivables?id='+row.id,
                modal: true,
            });
        }else{
        	 $.messager.show({
                 title:'提示',
                 msg:'请选择一条记录.'
             });
        	}
        }
	 //销售、维修付款修改
   function showReceivablesUpdateDialog(id){
       var row=$('#tblSaleBill').datagrid("getSelected");
       if(row){
           $('#showReceivablesUpdateDialog').dialog({
                title: '销售/维修付款修改',
                width: 800,
                height: 300,
                closed: false,
                cache: false,
                href: '<%=rootUrl%>/receivables/toUpdateReceivables?id='+row.receivablesId,
                modal: true,
            });
        }else{
       	 $.messager.show({
             title:'提示',
             msg:'请选择一条记录.'
         });
    	}
    }
        
        //增加销售单
        function showAddDialog(saleType){
           $('#insertBillDialog').dialog({
                title: '销售/维修单增加',
                width: 1100,
                height: 608,
                closed: false,
                cache: false,
                href: '<%=rootUrl%>/saleBill/toInsertSaleBill?saleType='+saleType,
                modal: true
            });
        }
    </script>
