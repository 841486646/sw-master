<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>颜色/尺寸管理</title>

    <!-- 工具栏 -->
    <div id="toolbarTblColor">
        <div>
        	<shiro:hasPermission name="mt:color:create">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">新增颜色/尺寸</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="mt:color:update">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateUIDialog();">修改颜色/尺寸</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="mt:color:delete">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteColor();">删除颜色/尺寸</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblColor" style="width: 98%;" class="easyui-datagrid"></table>
    
    <!-- 新增窗口 -->
    <div id="dialogAddColor" class="easyui-dialog" style="width: 500px; height: 380px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddColor'">
        <form id="formAddColor" method="post">
            <div class="fitem">
                <label>颜色/尺寸名称：</label>
                <input name="name" class="easyui-validatebox" data-options="required:true,validType:['between[1,50]']" />
            </div>
            <div class="fitem">
                <label>颜色/尺寸描述：</label>
                <input name="description" class="easyui-validatebox"/>
            </div>
        </form>
    </div>
    <div id="toolbarDialogAddColor">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveColor();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddColor').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 新增窗口 -->
    
    <!-- 修改颜色/尺寸基本信息 -->
    <div id="dlgColorBaseInfo" class="easyui-dialog" style="width: 500px; height: 350px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbColorBaseInfo'">
        <form id="formColorBaseInfo" method="post">
        	<div class="fitem">
                <label>颜色/尺寸ID：</label>
                <input name="id" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>颜色/尺寸名称：</label>
                <input name="name" class="easyui-validatebox" data-options="required:true,validType:['between[1,50]']" />
            </div>
            <div class="fitem">
                <label>颜色/尺寸描述：</label>
                <input name="description" class="easyui-validatebox"/>
            </div>
        </form>
    </div>
    <div id="tbColorBaseInfo">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateColorInfo();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgColorBaseInfo').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 修改颜色/尺寸基本信息 -->
    
    <div id="tbColorResouce">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateColorResouce();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgColorResouce').dialog('close');" style="width: 90px">取消</a>
    </div>

    <script type="text/javascript">
        $(function(){
            $("#tblColor").datagrid({
                url:"${rootUrl}/mtMachineColor/list.grid",
                columns:[[
                    {field:'id',title:'ID',width:20},
                    {field:'name',title:'颜色/尺寸名称',width:50},
                    {field:'description',title:'颜色/尺寸描述',width:80},
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
                toolbar:'#toolbarTblColor'
            });
        });
        //弹出新增颜色/尺寸窗口
        function showAddDialog() {
            $('#dialogAddColor').dialog('open').dialog('setTitle','新增颜色/尺寸');
            $('#formAddColor').form('clear');
            disableValidateWhenInit("formAddColor");
        }
        //保存新颜色/尺寸
        function saveColor() {
            enableValidateWhenSubmit('formAddColor');
            if($('#formAddColor').form('validate')){
                $.ajax({
                    async:false,
                    url:"${rootUrl}/mtMachineColor/save",
                    type:"POST",
                    dataType:"json",
                    data:$('#formAddColor').serialize(),
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
                            $('#dialogAddColor').dialog("close");
                            $("#tblColor").datagrid("reload");
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
        //弹出修改颜色/尺寸基本信息窗口
        function showUpdateUIDialog() {
            var row=$('#tblColor').datagrid("getSelected");
            if(row){
                $('#dlgColorBaseInfo').dialog('open').dialog('setTitle','修改颜色/尺寸基本信息');
                $('#formColorBaseInfo').form('clear');
                $('#formColorBaseInfo').form('load',row);
                disableValidateWhenInit("formColorBaseInfo");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后的颜色/尺寸的基本信息
        function updateColorInfo(){
            enableValidateWhenSubmit('formColorBaseInfo');
            if ($('#formColorBaseInfo').form('validate')) {
                $.ajax({
                    async:false,
                    url:"${rootUrl}/mtMachineColor/update",
                    type:"POST",
                    dataType:"json",
                    data:$('#formColorBaseInfo').serialize(),
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
                            $('#dlgColorBaseInfo').dialog("close");
                            $("#tblColor").datagrid("reload");
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
        //删除颜色/尺寸
        function deleteColor(){
            var row=$('#tblColor').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','确定删除该颜色/尺寸吗?',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"${rootUrl}/mtMachineColor/delete",
                            type:"POST",
                            dataType:"json",
                            data:{mtMachineColorId:row.id},
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
                                    $('#tblColor').datagrid('reload');
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
