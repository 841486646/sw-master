<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>供应商管理</title>

    <!-- 工具栏 -->
    <div id="toolbarTblSupplier">
    		<label>手机号码：</label><input id="searchSuppPhone" name="phone" class="easyui-validatebox validatebox-text textbox"/>
    		<label>供应商名称：</label><input id="searchSupplierName" name="supplierName" class="easyui-validatebox validatebox-text textbox"/>
    		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchBtn()">查询</a>
        <div>
        	<shiro:hasPermission name="datas:supplier:create">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">新增供应商</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="datas:supplier:update">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateUIDialog();">修改供应商</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="datas:supplier:delete">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteRole();">删除供应商</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblSupplier" style="width: 98%;" class="easyui-datagrid"></table>
    
    <!-- 新增窗口 -->
    <div id="dialogAddSupplier" class="easyui-dialog" style="width: 500px; height: 500px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddSupplier'">
        <form id="formAddSupplier" method="post">
            <div class="fitem">
                <label>供应商名称：</label>
                <input name="supplierName" class="easyui-validatebox" maxlength="20" data-options="required:true" /><span class="spanRequired">*</span>
            </div>
            <div class="fitem">
                <label>联系人：</label>
                <input name="name" class="easyui-validatebox" maxlength="20" data-options="required:true"/><span class="spanRequired">*</span>
            </div>
            <div class="fitem">
                <label>手机：</label>
                <input style="width: 200px;" name="phone" class="easyui-numberbox" data-options="validType:'mphone'" />
            </div>
            <div class="fitem">
                <label>电话：</label>
                <input name="telephone" class="easyui-validatebox" maxlength="20" />
            </div>
            <div class="fitem">
                <label>邮箱：</label>
                <input name="email" class="easyui-validatebox" data-options="validType:'email'" maxlength="20" />
            </div>
            <div class="fitem">
                <label>备注：</label>
                <input name="remarks" class="easyui-validatebox" maxlength="100"/>
            </div>
        </form>
    </div>
    <div id="toolbarDialogAddSupplier">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveRole();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddSupplier').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 新增窗口 -->
    
    <!-- 修改供应商本信息 -->
    <div id="dlgSupplierBaseInfo" class="easyui-dialog" style="width: 500px; height: 350px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbRoleBaseInfo'">
        <form id="formSupplier" method="post">
        	 <div class="fitem">
                <label>供应商名称：</label>
                <input name="supplierName" class="easyui-validatebox" maxlength="20" data-options="required:true" /><span class="spanRequired">*</span>
                <input type="hidden" name="id">
            </div>
            <div class="fitem">
                <label>联系人：</label>
                <input name="name" class="easyui-validatebox" maxlength="20" data-options="required:true"/><span class="spanRequired">*</span>
            </div>
            <div class="fitem">
                <label>手机：</label>
                <input style="width: 200px;" name="phone" class="easyui-numberbox" data-options="validType:'mphone'" />
            </div>
            <div class="fitem">
                <label>电话：</label>
                <input name="telephone" class="easyui-validatebox" maxlength="20" />
            </div>
            <div class="fitem">
                <label>邮箱：</label>
                <input name="email" class="easyui-validatebox" data-options="validType:'email'" maxlength="20" />
            </div>
            <div class="fitem">
                <label>备注：</label>
                <input name="remarks" class="easyui-validatebox" maxlength="100"/>
            </div>
        </form>
    </div>
    <div id="tbRoleBaseInfo">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateRoleInfo();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgSupplierBaseInfo').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 修改供应商本信息 -->
    <script type="text/javascript">
        $(function(){
            $("#tblSupplier").datagrid({
                url:"<%=rootUrl%>/supplier/list.grid",
                columns:[[
                    {field:'supplierName',title:'供应商名称',width:50},
                    {field:'phone',title:'手机',width:50},
                    {field:'telephone',title:'电话',width:80},
                    {field:'email',title:'邮箱',width:80},
                    {field:'name',title:'联系人',width:80},
                    {field:'remarks',title:'备注',width:80},
                    {
                        field:'createTime',
                        title:'创建时间',
                        width:50,
                        formatter:function(value,data,index){
                            return fmtDate(value,"yyyy-MM-dd HH:mm");
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
                toolbar:'#toolbarTblSupplier'
            });
        });
        //弹出新增供应商窗口
        function showAddDialog() {
            $('#dialogAddSupplier').dialog('open').dialog('setTitle','新增供应商');
            disableValidateWhenInit("formAddSupplier");
        }
        //保存供应商
        function saveRole() {
            enableValidateWhenSubmit('formAddSupplier');
            if($('#formAddSupplier').form('validate')){
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/supplier/save",
                    type:"POST",
                    dataType:"json",
                    data:$('#formAddSupplier').serialize(),
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
                            //清楚表单
                            $('#formAddSupplier').form("clear");
                            $('#dialogAddSupplier').dialog("close");
                            $("#tblSupplier").datagrid("reload");
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
        //弹出修改供应商信息窗口
        function showUpdateUIDialog() {
            var row=$('#tblSupplier').datagrid("getSelected");
            if(row){
                $('#dlgSupplierBaseInfo').dialog('open').dialog('setTitle','修改供应商信息');
                $('#formSupplier').form('clear');
                $('#formSupplier').form('load',row);
                disableValidateWhenInit("formSupplier");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后供应商信息
        function updateRoleInfo(){
            enableValidateWhenSubmit('formSupplier');
            if ($('#formSupplier').form('validate')) {
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/supplier/update",
                    type:"POST",
                    dataType:"json",
                    data:$('#formSupplier').serialize(),
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
                            $('#dlgSupplierBaseInfo').dialog("close");
                            $("#tblSupplier").datagrid("reload");
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
        
        //删除供应商
        function deleteRole(){
            var row=$('#tblSupplier').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','确定删除该数据吗?',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"<%=rootUrl%>/supplier/delete",
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
                                    $('#tblSupplier').datagrid('reload');
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
        
        //查询
        function searchBtn(){
        	var searchSuppPhone=$('#searchSuppPhone').val();
        	var searchSupplierName=$('#searchSupplierName').val();
        	$('#tblSupplier').datagrid({
            	queryParams: {
            		phone: searchSuppPhone,
            		supplierName:searchSupplierName,
            		}
            	});
        	}
    </script>
