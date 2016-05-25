<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>角色管理</title>

    <!-- 工具栏 -->
    <div id="toolbarTblRole">
        <div>
        	<shiro:hasPermission name="sys:role:create">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">新增角色</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:role:update">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateUIDialog();">修改角色</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:role:delete">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteRole();">删除角色</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:role:giveres">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showGiveResourceDialog();">分配资源</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblRole" style="width: 98%;" class="easyui-datagrid"></table>
    
    <!-- 新增窗口 -->
    <div id="dialogAddRole" class="easyui-dialog" style="width: 500px; height: 380px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddRole'">
        <form id="formAddRole" method="post">
            <div class="fitem">
                <label>角色名称：</label>
                <input name="roleName" class="easyui-validatebox" data-options="required:true,validType:['loginName','between[1,50]']" />
            </div>
            <div class="fitem">
                <label>角色描述：</label>
                <input name="roleDesc" class="easyui-validatebox"/>
            </div>
            <div class="fitem">
                <label>角色状态：</label>
                <select name="isShow" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    <c:forEach items="${showTypes }" var="showType">
                        <option value="${showType }" <c:if test="${showType eq 'show' }">selected="selected" </c:if>>${showType.info }</option>
                   	</c:forEach>
                </select>
            </div>
        </form>
    </div>
    <div id="toolbarDialogAddRole">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveRole();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddRole').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 新增窗口 -->
    
    <!-- 修改角色基本信息 -->
    <div id="dlgRoleBaseInfo" class="easyui-dialog" style="width: 500px; height: 350px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbRoleBaseInfo'">
        <form id="formRoleBaseInfo" method="post">
        	<div class="fitem">
                <label>角色ID：</label>
                <input name="id" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>角色名称：</label>
                <input name="roleName" class="easyui-validatebox" readonly="readonly" data-options="required:true,validType:['loginName','between[1,50]']" />
            </div>
            <div class="fitem">
                <label>角色描述：</label>
                <input name="roleDesc" class="easyui-validatebox"/>
            </div>
            <div class="fitem">
                <label>角色状态：</label>
                <select name="isShow" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    <c:forEach items="${showTypes }" var="showType">
                        <option value="${showType }" >${showType.info }</option>
                   	</c:forEach>
                </select>
            </div>
        </form>
    </div>
    <div id="tbRoleBaseInfo">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateRoleInfo();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgRoleBaseInfo').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 修改角色基本信息 -->
    
    <!-- 角色分配资源列表 -->
    <div id="dlgRoleResouce" class="easyui-dialog" style="width: 500px; height: 350px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbRoleResouce'">
        <form id="formRoleResouce" method="post">
        	<div class="fitem">
                <label>角色ID：</label>
                <input id="roleResouceId" name="id" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>角色名称：</label>
                <input name="roleName" class="easyui-validatebox" readonly="readonly" data-options="required:true,validType:['loginName','between[1,50]']" />
            </div>
            <div class="fitem">
                <label>角色资源列表：</label>
                <select id="roleResources" name="roleResources" style="width:200px;"></select>
            </div>
        </form>
    </div>
    <div id="tbRoleResouce">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateRoleResouce();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgRoleResouce').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 角色分配资源列表 -->

    <script type="text/javascript">
        $(function(){
            $("#tblRole").datagrid({
                url:"<%=rootUrl%>/role/list.grid",
                columns:[[
                    {field:'id',title:'ID',width:50},
                    {field:'roleName',title:'角色名称',width:50},
                    {field:'roleDesc',title:'角色描述',width:80},
                    {
                        field:'isShow',
                        title:'角色状态',
                        width:50,
                        formatter:function(value,data,index){
                            <c:forEach items="${showTypes }" var="showType">
	                            if("${showType}"==value){
	                                return "${showType.info}";
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
                toolbar:'#toolbarTblRole'
            });
        });
        //弹出新增角色窗口
        function showAddDialog() {
            $('#dialogAddRole').dialog('open').dialog('setTitle','新增角色');
            disableValidateWhenInit("formAddRole");
        }
        //保存新角色
        function saveRole() {
            enableValidateWhenSubmit('formAddRole');
            if($('#formAddRole').form('validate')){
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/role/save",
                    type:"POST",
                    dataType:"json",
                    data:$('#formAddRole').serialize(),
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
                            $('#dialogAddRole').dialog("close");
                            $("#tblRole").datagrid("reload");
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
        //弹出修改角色基本信息窗口
        function showUpdateUIDialog() {
            var row=$('#tblRole').datagrid("getSelected");
            if(row){
                $('#dlgRoleBaseInfo').dialog('open').dialog('setTitle','修改角色基本信息');
                $('#formRoleBaseInfo').form('clear');
                $('#formRoleBaseInfo').form('load',row);
                disableValidateWhenInit("formRoleBaseInfo");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后的角色的基本信息
        function updateRoleInfo(){
            enableValidateWhenSubmit('formRoleBaseInfo');
            if ($('#formRoleBaseInfo').form('validate')) {
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/role/update",
                    type:"POST",
                    dataType:"json",
                    data:$('#formRoleBaseInfo').serialize(),
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
                            $('#dlgRoleBaseInfo').dialog("close");
                            $("#tblRole").datagrid("reload");
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
        //弹出分配资源窗口
        function showGiveResourceDialog() {
            var row=$('#tblRole').datagrid("getSelected");
            if(row){
                $('#dlgRoleResouce').dialog('open').dialog('setTitle','分配资源');
                $('#formRoleResouce').form('clear');
                $('#formRoleResouce').form('load',row);
                $('#roleResources').combotree({
            		multiple:true,
            		url:"<%=rootUrl%>/role/findResoucesWithRoleChecked?roleId=" + row.id,
	                onlyLeafCheck:false
	            });
                disableValidateWhenInit("formRoleResouce");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后分配的资源信息
        function updateRoleResouce(){
            enableValidateWhenSubmit('formRoleResouce');
            if ($('#formRoleResouce').form('validate')) {
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/role/giveResources",
                    type:"POST",
                    dataType:"json",
                    data:{
                    	resourceIds:$('#roleResources').combotree("getValues").toString(),
                    	roleId:$('#roleResouceId').val()
                    },
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
                            $('#dlgRoleResouce').dialog("close");
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
        //删除角色
        function deleteRole(){
            var row=$('#tblRole').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','确定删除该角色吗?',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"<%=rootUrl%>/role/delete",
                            type:"POST",
                            dataType:"json",
                            data:{roleId:row.id},
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
                                    $('#tblRole').datagrid('reload');
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
