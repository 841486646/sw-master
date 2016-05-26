<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>故障管理</title>

    <!-- 工具栏 -->
    <div id="toolbarTblMachineBug">
        <div>
        	<shiro:hasPermission name="mt:bug:create">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">新增故障</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="mt:bug:update">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateUIDialog();">修改故障</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="mt:bug:delete">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteMachineBug();">删除故障</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblMachineBug" style="width: 98%;" class="easyui-datagrid"></table>
    
    <!-- 新增窗口 -->
    <div id="dialogAddMachineBug" class="easyui-dialog" style="width: 500px; height: 380px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddMachineBug'">
        <form id="formAddMachineBug" method="post">
            <div class="fitem">
                <label>故障名称：</label>
                <input name="name" class="easyui-validatebox" data-options="required:true,validType:['between[1,50]']" />
            </div>
            <div class="fitem">
                <label>价格：</label>
                <input style="width:70%" name="price" class="easyui-numberbox" data-options="required:true,precision:2,validType:['between[1,10]']" />
            </div>
            <div class="fitem">
                <label>机型：</label>
                <select name="machineTypeId" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired'],url:'${rootUrl}/mtMachineBug/findProductAndMachineTypes',method:'post',groupField:'group'" style="width: 150px;">
                </select>
            </div>
            <div class="fitem">
                <label>颜色：</label>
                <select name="machineColorId" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    <c:forEach items="${colors }" var="color">
                        <option value="${color.id }">${color.name }</option>
                   	</c:forEach>
                </select>
            </div>
            <div class="fitem">
                <label>故障描述：</label>
                <input name="description" class="easyui-validatebox"/>
            </div>
        </form>
    </div>
    <div id="toolbarDialogAddMachineBug">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveMachineBug();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddMachineBug').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 新增窗口 -->
    
    <!-- 修改故障基本信息 -->
    <div id="dlgMachineBugBaseInfo" class="easyui-dialog" style="width: 500px; height: 350px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbMachineBugBaseInfo'">
        <form id="formMachineBugBaseInfo" method="post">
        	<div class="fitem">
                <label>故障ID：</label>
                <input name="id" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>故障名称：</label>
                <input name="name" class="easyui-validatebox" data-options="required:true,validType:['between[1,50]']" />
            </div>
            <div class="fitem">
                <label>价格：</label>
                <input style="width:70%" name="price" class="easyui-numberbox" data-options="required:true,precision:2,validType:['between[1,10]']" />
            </div>
            <div class="fitem">
                <label>机型：</label>
                <select name="machineTypeId" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired'],url:'${rootUrl}/mtMachineBug/findProductAndMachineTypes',method:'post',groupField:'group'" style="width: 150px;">
                </select>
            </div>
            <div class="fitem">
                <label>颜色：</label>
                <select name="machineColorId" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    <c:forEach items="${colors }" var="color">
                        <option value="${color.id }">${color.name }</option>
                   	</c:forEach>
                </select>
            </div>
            <div class="fitem">
                <label>故障描述：</label>
                <input name="description" class="easyui-validatebox"/>
            </div>
        </form>
    </div>
    <div id="tbMachineBugBaseInfo">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateMachineBugInfo();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgMachineBugBaseInfo').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 修改故障基本信息 -->
    
    <div id="tbMachineBugResouce">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateMachineBugResouce();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgMachineBugResouce').dialog('close');" style="width: 90px">取消</a>
    </div>

    <script type="text/javascript">
        $(function(){
            $("#tblMachineBug").datagrid({
                url:"${rootUrl}/mtMachineBug/list.grid",
                columns:[[
                    {field:'id',title:'ID',width:20},
                    {field:'name',title:'故障名称',width:50},
                    {field:'productName',title:'产品名称',width:50},
                    {field:'machinetypeName',title:'机型名称',width:50},
                    {field:'colorName',title:'颜色名称',width:50},
                    {field:'price',title:'价格',width:30},
                    {field:'description',title:'故障描述',width:80},
                    {
                        field:'createTime',
                        title:'创建时间',
                        width:50,
                        formatter:function(value,data,index){
                            return fmtDate(value,"yyyy-MM-dd HH:mm");
                        }
                    },
                    {
                        field:'updateTime',
                        title:'更新时间',
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
                toolbar:'#toolbarTblMachineBug'
            });
        });
        //弹出新增故障窗口
        function showAddDialog() {
            $('#dialogAddMachineBug').dialog('open').dialog('setTitle','新增故障');
            $('#formAddColor').form('clear');
            disableValidateWhenInit("formAddMachineBug");
        }
        //保存新故障
        function saveMachineBug() {
            enableValidateWhenSubmit('formAddMachineBug');
            if($('#formAddMachineBug').form('validate')){
                $.ajax({
                    async:false,
                    url:"${rootUrl}/mtMachineBug/save",
                    type:"POST",
                    dataType:"json",
                    data:$('#formAddMachineBug').serialize(),
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
                            $('#dialogAddMachineBug').dialog("close");
                            $("#tblMachineBug").datagrid("reload");
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
        //弹出修改故障基本信息窗口
        function showUpdateUIDialog() {
            var row=$('#tblMachineBug').datagrid("getSelected");
            if(row){
                $('#dlgMachineBugBaseInfo').dialog('open').dialog('setTitle','修改故障基本信息');
                $('#formMachineBugBaseInfo').form('clear');
                $('#formMachineBugBaseInfo').form('load',row);
                disableValidateWhenInit("formMachineBugBaseInfo");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后的故障的基本信息
        function updateMachineBugInfo(){
            enableValidateWhenSubmit('formMachineBugBaseInfo');
            if ($('#formMachineBugBaseInfo').form('validate')) {
                $.ajax({
                    async:false,
                    url:"${rootUrl}/mtMachineBug/update",
                    type:"POST",
                    dataType:"json",
                    data:$('#formMachineBugBaseInfo').serialize(),
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
                            $('#dlgMachineBugBaseInfo').dialog("close");
                            $("#tblMachineBug").datagrid("reload");
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
        //删除故障
        function deleteMachineBug(){
            var row=$('#tblMachineBug').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','确定删除该故障吗?',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"${rootUrl}/mtMachineBug/delete",
                            type:"POST",
                            dataType:"json",
                            data:{mtMachineBugId:row.id},
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
                                    $('#tblMachineBug').datagrid('reload');
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
    </script>
