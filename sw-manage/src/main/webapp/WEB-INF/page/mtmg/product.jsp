<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>产品管理</title>

    <!-- 工具栏 -->
    <div id="toolbarTblProduct">
        <div>
        	<shiro:hasPermission name="mt:product:create">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">新增产品</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="mt:product:update">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateUIDialog();">修改产品</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="mt:product:delete">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteProduct();">删除产品</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblProduct" style="width: 98%;" class="easyui-datagrid"></table>
    
    <!-- 新增窗口 -->
    <div id="dialogAddProduct" class="easyui-dialog" style="width: 500px; height: 380px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddProduct'">
        <form id="formAddProduct" method="post">
            <div class="fitem">
                <label>产品名称：</label>
                <input name="name" class="easyui-validatebox" data-options="required:true,validType:['between[1,50]']" />
            </div>
            <div class="fitem">
                <label>产品描述：</label>
                <input name="description" class="easyui-validatebox"/>
            </div>
            <div class="fitem">
                <label>图片319*182：</label>
                <a href="#" class="easyui-linkbutton" onclick="upload();">上  传</a>
                <input type="hidden" name="imgUrl" id="imgUrlAdd"/>
                <input type="file" id="imgFile" name="imgFile" style="width:200px"/>
            </div>
        </form>
    </div>
    <div id="toolbarDialogAddProduct">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveProduct();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddProduct').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 新增窗口 -->
    
    <!-- 修改产品基本信息 -->
    <div id="dlgProductBaseInfo" class="easyui-dialog" style="width: 500px; height: 350px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbProductBaseInfo'">
        <form id="formProductBaseInfo" method="post">
        	<div class="fitem">
                <label>产品ID：</label>
                <input name="id" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>产品名称：</label>
                <input name="name" class="easyui-validatebox" data-options="required:true,validType:['between[1,50]']" />
            </div>
            <div class="fitem">
                <label>产品描述：</label>
                <input name="description" class="easyui-validatebox"/>
            </div>
        </form>
    </div>
    <div id="tbProductBaseInfo">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateProductInfo();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgProductBaseInfo').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 修改产品基本信息 -->
    
    <div id="tbProductResouce">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateProductResouce();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgProductResouce').dialog('close');" style="width: 90px">取消</a>
    </div>

    <script type="text/javascript">
        $(function(){
            $("#tblProduct").datagrid({
                url:"${rootUrl}/mtProduct/list.grid",
                columns:[[
                    {field:'id',title:'ID',width:50},
                    {field:'name',title:'产品名称',width:50},
                    {field:'imgUrl',title:'图片路径',width:80},
                    {field:'description',title:'产品描述',width:80},
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
                toolbar:'#toolbarTblProduct'
            });
        });
        //弹出新增产品窗口
        function showAddDialog() {
            $('#dialogAddProduct').dialog('open').dialog('setTitle','新增产品');
            $('#formAddColor').form('clear');
            disableValidateWhenInit("formAddProduct");
        }
        //保存新产品
        function saveProduct() {
            enableValidateWhenSubmit('formAddProduct');
            if($('#formAddProduct').form('validate')){
                $.ajax({
                    async:false,
                    url:"${rootUrl}/mtProduct/save",
                    type:"POST",
                    dataType:"json",
                    data:$('#formAddProduct').serialize(),
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
                            $('#dialogAddProduct').dialog("close");
                            $("#tblProduct").datagrid("reload");
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
        //弹出修改产品基本信息窗口
        function showUpdateUIDialog() {
            var row=$('#tblProduct').datagrid("getSelected");
            if(row){
                $('#dlgProductBaseInfo').dialog('open').dialog('setTitle','修改产品基本信息');
                $('#formProductBaseInfo').form('clear');
                $('#formProductBaseInfo').form('load',row);
                disableValidateWhenInit("formProductBaseInfo");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后的产品的基本信息
        function updateProductInfo(){
            enableValidateWhenSubmit('formProductBaseInfo');
            if ($('#formProductBaseInfo').form('validate')) {
                $.ajax({
                    async:false,
                    url:"${rootUrl}/mtProduct/update",
                    type:"POST",
                    dataType:"json",
                    data:$('#formProductBaseInfo').serialize(),
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
                            $('#dlgProductBaseInfo').dialog("close");
                            $("#tblProduct").datagrid("reload");
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
        //删除产品
        function deleteProduct(){
            var row=$('#tblProduct').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','确定删除该产品吗?',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"${rootUrl}/mtProduct/delete",
                            type:"POST",
                            dataType:"json",
                            data:{mtProductId:row.id},
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
                                    $('#tblProduct').datagrid('reload');
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
        		url:"${rootUrl}/mtProduct/upload",
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
