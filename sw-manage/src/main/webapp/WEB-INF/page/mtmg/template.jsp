<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>模版管理</title>

    <!-- 工具栏 -->
    <div id="toolbarTblTemplate">
        <div>
        	<shiro:hasPermission name="mt:template:create">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="showAddDialog();">新增模版</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="mt:bug:delete">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteMachineBug();">删除模版</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblTemplate" style="width: 98%;" class="easyui-datagrid"></table>
    
    <!-- 新增窗口 -->
    <div id="dialogAddTemplate" class="easyui-dialog" style="width: 900px; height: 640px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddTemplate'">
        <form id="formAddTemplate" method="post">
            <div>
            <label>标题：</label>
                <input name="title" id="title" class="easyui-validatebox"  />
             <label>类型：</label>
            	 <select name="type" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                        <option value="0">成功案例</option>
                </select>
            </div>
            <div class="fitem">
                <label>标题内容：</label>
               <textarea class="ckeditor"  id="contaceContent"  cols="80">
               </textarea>
            </div>
        </form>
    </div>
    <div id="toolbarDialogAddTemplate">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveTemplate();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddTemplate').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 新增窗口 -->
    
    <!-- 修改故障基本信息 -->
    <div id="dlgUpdateTemplate" class="easyui-dialog" style="width: 900px; height: 640px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbMachineBugBaseInfo'">
        <form id="formUpdateTemplate" method="post">
 		<div>
            <label>标题：</label>
                <input name="title" id="title" class="easyui-validatebox" data-options="required:true,validType:['between[1,50]']" />
             <label>类型：</label>
            	 <select name="type" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                        <option value="0">成功案例</option>
                </select>
            </div>
            <div class="fitem">
                <label>标题内容：</label>
               <textarea class="ckeditor" name="content" id="contaceContentUpdate"  cols="80">
               </textarea>
            </div>
        </form>
    </div>
    <div id="tbMachineBugBaseInfo">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateTemplate();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgUpdateTemplate').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 修改故障基本信息 -->
    
    <div id="tbMachineBugResouce">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateMachineBugResouce();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgMachineBugResouce').dialog('close');" style="width: 90px">取消</a>
    </div>

    <script type="text/javascript">
        $(function(){
            $("#tblTemplate").datagrid({
                url:"${rootUrl}/template/list.grid",
                columns:[[
                    {field:'id',title:'ID',width:20},
                    {field:'title',title:'标题',width:50},
                    {
                        field:'type',
                        title:'类型',
                        width:40,
                        formatter:function(value,row,index){
	                            if(value==0){
	                                return "成功案例";
	                            }
                        }
                    },
                    {
                        field:'createtime',
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
                toolbar:'#toolbarTblTemplate'
            });
        });
        //弹出新增故障窗口
        function showAddDialog() {
            $('#dialogAddTemplate').dialog('open').dialog('setTitle','新增');
            $('#formAddColor').form('clear');
            disableValidateWhenInit("formAddTemplate");
        }
        //保存新故障
        function saveTemplate() {
        	var title=$("#title").val();
        	var contaceContent = CKEDITOR.instances.contaceContent.getData();
        	var data={content:contaceContent,title:title,type:0}; 
        	if(contaceContent==''){
        		$.messager.show({
                    title:'提示',
                    msg:"请填写内容"
                });
        		return false;
        	}
            enableValidateWhenSubmit('formAddTemplate');
            if($('#formAddTemplate').form('validate')){
                $.ajax({
                    async:false,
                    url:"${rootUrl}/template/save",
                    type:"POST",
                    dataType:"json",
                    data:data,
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
                            $('#dialogAddTemplate').dialog("close");
                            $("#tblTemplate").datagrid("reload");
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
            var row=$('#tblTemplate').datagrid("getSelected");
            console.log(row);
            if(row){
                $('#dlgUpdateTemplate').dialog('open').dialog('setTitle','修改');
                $('#formUpdateTemplate').form('clear');
                $('#formUpdateTemplate').form('load',row);
                disableValidateWhenInit("formUpdateTemplate");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后的故障的基本信息
        function updateTemplate(){
            enableValidateWhenSubmit('formUpdateTemplate');
            if ($('#formUpdateTemplate').form('validate')) {
                $.ajax({
                    async:false,
                    url:"${rootUrl}/mtMachineBug/update",
                    type:"POST",
                    dataType:"json",
                    data:$('#formUpdateTemplate').serialize(),
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
                            $('#dlgUpdateTemplate').dialog("close");
                            $("#tblTemplate").datagrid("reload");
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
            var row=$('#tblTemplate').datagrid("getSelected");
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
                                    $('#tblTemplate').datagrid('reload');
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
