<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>机型管理</title>

    <!-- 工具栏 -->
    <div id="toolbarTblMachineType">
        <div>
        	<shiro:hasPermission name="mt:machinetype:create">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">新增机型</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="mt:machinetype:update">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateUIDialog();">修改机型</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="mt:machinetype:delete">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteMachineType();">删除机型</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblMachineType" style="width: 98%;" class="easyui-datagrid"></table>
    
    <!-- 新增窗口 -->
    <div id="dialogAddMachineType" class="easyui-dialog" style="width: 500px; height: 380px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddMachineType'">
        <form id="formAddMachineType" method="post">
            <div class="fitem">
                <label>机型名称：</label>
                <input name="name" class="easyui-validatebox" data-options="required:true,validType:['between[1,50]']" />
            </div>
            <div class="fitem">
                <label>产品：</label>
                <select name="productId" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    <c:forEach items="${products }" var="product">
                        <option value="${product.id }">${product.name }</option>
                   	</c:forEach>
                </select>
            </div>
            <div class="fitem">
                <label>机型描述：</label>
                <input name="description" class="easyui-validatebox"/>
            </div>
            <div class="fitem">
                <label>图片320*320：</label>
                <a href="#" class="easyui-linkbutton" onclick="upload();">上  传</a>
                <input type="hidden" name="imgUrl" id="imgUrlAdd"/>
                <input type="file" id="imgFile" name="imgFile" style="width:200px"/>
            </div>
        </form>
    </div>
    <div id="toolbarDialogAddMachineType">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveMachineType();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddMachineType').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 新增窗口 -->
    
    <!-- 修改机型基本信息 -->
    <div id="dlgMachineTypeBaseInfo" class="easyui-dialog" style="width: 500px; height: 350px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbMachineTypeBaseInfo'">
        <form id="formMachineTypeBaseInfo" method="post">
        	<div class="fitem">
                <label>机型ID：</label>
                <input name="id" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>机型名称：</label>
                <input name="name" class="easyui-validatebox" data-options="required:true,validType:['between[1,50]']" />
            </div>
            <div class="fitem">
                <label>机型描述：</label>
                <input name="description" class="easyui-validatebox"/>
            </div>
        </form>
    </div>
    <div id="tbMachineTypeBaseInfo">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateMachineTypeInfo();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgMachineTypeBaseInfo').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 修改机型基本信息 -->
    
    <div id="tbMachineTypeResouce">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateMachineTypeResouce();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgMachineTypeResouce').dialog('close');" style="width: 90px">取消</a>
    </div>

    <script type="text/javascript">
        $(function(){
            $("#tblMachineType").datagrid({
                url:"${rootUrl}/mtMachineType/list.grid",
                columns:[[
                    {field:'id',title:'ID',width:50},
                    {field:'name',title:'机型名称',width:50},
                    {field:'imgUrl',title:'图片路径',width:80},
                    {field:'description',title:'机型描述',width:80},
                    {
                        field:'productId',
                        title:'产品',
                        width:50,
                        formatter:function(value,data,index){
                            <c:forEach items="${products }" var="product">
	                            if("${product.id}"==value){
	                                return "${product.name}";
	                            }
                        	</c:forEach>
                        }
                    },
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
                toolbar:'#toolbarTblMachineType'
            });
        });
        //弹出新增机型窗口
        function showAddDialog() {
            $('#dialogAddMachineType').dialog('open').dialog('setTitle','新增机型');
            $('#formAddColor').form('clear');
            disableValidateWhenInit("formAddMachineType");
        }
        //保存新机型
        function saveMachineType() {
            enableValidateWhenSubmit('formAddMachineType');
            if($('#formAddMachineType').form('validate')){
                $.ajax({
                    async:false,
                    url:"${rootUrl}/mtMachineType/save",
                    type:"POST",
                    dataType:"json",
                    data:$('#formAddMachineType').serialize(),
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
                            $('#dialogAddMachineType').dialog("close");
                            $("#tblMachineType").datagrid("reload");
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
        //弹出修改机型基本信息窗口
        function showUpdateUIDialog() {
            var row=$('#tblMachineType').datagrid("getSelected");
            if(row){
                $('#dlgMachineTypeBaseInfo').dialog('open').dialog('setTitle','修改机型基本信息');
                $('#formMachineTypeBaseInfo').form('clear');
                $('#formMachineTypeBaseInfo').form('load',row);
                disableValidateWhenInit("formMachineTypeBaseInfo");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后的机型的基本信息
        function updateMachineTypeInfo(){
            enableValidateWhenSubmit('formMachineTypeBaseInfo');
            if ($('#formMachineTypeBaseInfo').form('validate')) {
                $.ajax({
                    async:false,
                    url:"${rootUrl}/mtMachineType/update",
                    type:"POST",
                    dataType:"json",
                    data:$('#formMachineTypeBaseInfo').serialize(),
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
                            $('#dlgMachineTypeBaseInfo').dialog("close");
                            $("#tblMachineType").datagrid("reload");
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
        //删除机型
        function deleteMachineType(){
            var row=$('#tblMachineType').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','确定删除该机型吗?',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"${rootUrl}/mtMachineType/delete",
                            type:"POST",
                            dataType:"json",
                            data:{mtMachineTypeId:row.id},
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
                                    $('#tblMachineType').datagrid('reload');
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
        
        function upload(){
        	$.ajaxFileUpload({
        		url:"${rootUrl}/mtMachineType/upload",
        		secureuri:false,                       //是否启用安全提交,默认为false 
        		fileElementId:'imgFile',           //文件选择框的id属性
        		dataType:'json',                       //服务器返回的格式,可以是json或xml等
        		success:function(data){        //服务器响应成功时的处理函数
        			if(0==data.code){
        				$("#imgUrlAdd").val(data.body);
                        $.messager.show({
                            title:'提示',
                            msg:data.msg
                        });
                    } else {
                        $.messager.show({
                            title:'错误',
                            msg:data.msg
                        });
                    }
        		},
        		error:function(data, status, e){
        			console.log(data);
        			console.log(status);
        			console.log(e);
        			$.messager.show({
                        title:'错误',
                        msg:'服务器请求失败.'
                    });
        		}
        	});
        }
    </script>
