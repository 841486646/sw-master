<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>入库管理</title>
    <!-- 工具栏 -->
    <div id="toolbarTblBill">
        <div>
        	<shiro:hasPermission name="warehouse:bill:saveBill">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">商品入库</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="warehouse:bill:updateBill">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateDialog();">修改入库</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="warehouse:bill:deleteBill">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteRole();">删除入库</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblBill" style="width: 98%;" class="easyui-datagrid"></table>
    <div id="insertBillDialog"></div>
    <div id="updateBillDialog"></div>
    <script type="text/javascript">
        $(function(){
            $("#tblBill").datagrid({
                url:"<%=rootUrl%>/bill/commodityStorageList.grid?state="+'sh_in',
                columns:[[
                    {field:'orderNumber',title:'入库单号',width:50},
                    {field:'totalPrice',title:'总价格',width:50},
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
                        title:'入库类型',
                        width:50,
                        formatter:function(value,data,index){
                            <c:forEach items="${billRKTypes }" var="billRKType">
	                            if("${billRKType}"==value){
	                                return "${billRKType.info}";
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
                        field:'_operate',
                        title:'入库(请慎重操作)',
                        width:50,
                        formatter:function(value,data,index){
                            return "<img onclick='rk_storage()' src='<%=rootUrl %>/resources/js/easyui/themes/icons/exclamation.png' title='结算单据，同时更新库存数量' style='cursor:pointer'/>";
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
                toolbar:'#toolbarTblBill'
            });
        });
        //删除供应商
        function deleteRole(){
            var row=$('#tblBill').datagrid("getSelected");
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
                                    $('#tblBill').datagrid('reload');
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
        
        //入库的操作
        function rk_storage(){
        	var row=$('#tblBill').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','您确定入库操作吗?入库将会改变商品库存数量！',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"<%=rootUrl%>/bill/updateBill",
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
                                    $('#tblBill').datagrid('reload');
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
        //增加订单
        function showAddDialog(){
           $('#insertBillDialog').dialog({
                title: '新增入库单',
                width: 950,
                height: 550,
                closed: false,
                cache: false,
                href: '<%=rootUrl%>/bill/toInsertBill',
                modal: true,
                onClose: function () {  
                	$(this).dialog('destroy');//销毁  
                }
            });
        }
        
        function showUpdateDialog(){
        	var row=$('#tblBill').datagrid("getSelected");
            if(row){
            	$('#updateBillDialog').dialog({
                    title: '修改入库单',
                    width: 900,
                    height: 600,
                    closed: false,
                    closable: false,
                    cache: false,
                    href: '<%=rootUrl%>/bill/toupdateBill?id='+row.id,
                    modal: true
                });
            }else{
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
    </script>
