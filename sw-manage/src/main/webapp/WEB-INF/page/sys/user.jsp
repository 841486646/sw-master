<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>用户管理</title>

    <!-- 工具栏 -->
    <div id="toolbarTblUser">
        <div>
        	<shiro:hasPermission name="sys:user:create">
                <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">新增用户</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:update">
                <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateUIDialog();">修改用户</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:delete">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteUser();">删除用户</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:giverole">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateRoleDialog();">角色分配</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:user:repwd">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdatePwdDialog();">密码重置</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblUser" style="width: 98%;" class="easyui-datagrid"></table>
    
    <!-- 新增窗口 -->
    <div id="dialogAddUser" class="easyui-dialog" style="width: 500px; height: 380px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddUser'">
        <form id="formAddUser" method="post">
            <div class="fitem">
                <label>登录账号：</label>
                <input name="loginName" class="easyui-validatebox" data-options="required:true,validType:['loginName','between[1,50]']" />
            </div>
            <div class="fitem">
                <label>用户密码：</label>
                <input name="pwd" type="password" class="easyui-validatebox" data-options="required:true,validType:['password','between[1,50]']"/>
            </div>
            <div class="fitem">
                <label>用户昵称：</label>
                <input name="nickName" class="easyui-validatebox" data-options="validType:'between[1,90]'"/>
            </div>
            <div class="fitem">
                <label>手机号码：</label>
                <input name="mobile" class="easyui-validatebox" data-options="validType:['mphone','between[1,20]']"/>
            </div>
            <div class="fitem">
                <label>用户状态：</label>
                <select name="state" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    <c:forEach items="${userStates }" var="userState">
                        <option value="${userState }" <c:if test="${userState eq 'normal' }">selected="selected" </c:if>>${userState.info }</option>
                   	</c:forEach>
                </select>
            </div>
        </form>
    </div>
    <div id="toolbarDialogAddUser">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveUser();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddUser').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 新增窗口 -->
    
    <!-- 修改用户基本信息 -->
    <div id="dlgUserBaseInfo" class="easyui-dialog" style="width: 500px; height: 350px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbUserBaseInfo'">
        <form id="formUserBaseInfo" method="post">
            <div class="fitem">
                <label>用户ID：</label>
                <input name="id" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>用户账号：</label>
                <input name="loginName" class="easyui-validatebox" data-options="required:true" readonly="readonly" />
            </div>
            <div class="fitem">
                <label>用户昵称：</label>
                <input name="nickName" class="easyui-validatebox" data-options="validType:'between[1,90]'"/>
            </div>
            <div class="fitem">
                <label>手机号码：</label>
                <input name="mobile" class="easyui-validatebox" data-options="validType:['mphone','between[1,20]']"/>
            </div>
            <div class="fitem">
                <label>用户状态：</label>
                <select name="state" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    <c:forEach items="${userStates }" var="userState">
                        <option value="${userState }">${userState.info }</option>
                   	</c:forEach>
                </select>
            </div>
        </form>
    </div>
    <div id="tbUserBaseInfo">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateUserInfo();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgUserBaseInfo').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 修改用户基本信息 -->

    <!-- 修改用户角色 -->
    <div id="dlgUpdateUserRole" class="easyui-dialog" style="width: 500px; height: 250px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbUpdateUserRole'">
        <form id="formUpdateUserRole" method="post">
            <div class="fitem">
                <label>用户ID：</label>
                <input id="updUserRoleId" name="updUserRoleId" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>用户账号：</label>
                <input id="updUserRoleLoginNm" name="updUserRoleLoginNm" class="easyui-validatebox" data-options="required:true" readonly="readonly" />
            </div>
            <div class="fitem">
                <label>用户角色：</label>
                <select id="updUserRole" class="easyui-combobox" data-options="editable:false,required:true,valueField:'id',textField:'roleName',multiple:true,multiline:true,validType:['selectValueRequired']" style="width: 240px; height: 50px;">
                </select>
            </div>
        </form>
    </div>
    <div id="tbUpdateUserRole">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateUserRole();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgUpdateUserRole').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 修改用户角色 -->

    <!-- 修改用户密码 -->
    <div id="dlgUpdatePwd" class="easyui-dialog" style="width: 500px; height: 300px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbUpdatePwd'">
        <form id="formUpdatePwd" method="post">
            <div class="fitem">
                <label>用户ID：</label>
                <input name="id" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>用户账号：</label>
                <input name="loginName" class="easyui-validatebox" data-options="required:true" readonly="readonly" />
            </div>
            <div class="fitem">
                <label>重置密码：</label>
                <input id="updNewPwd" name="pwd" type="password" class="easyui-validatebox" data-options="required:true,validType:['password','between[1,50]']"/>
            </div>
            <div class="fitem">
                <label>确认密码：</label>
                <input name="repwd" type="password" class="easyui-validatebox" data-options="required:true" validType="equalTo['#updNewPwd']"/>
            </div>
        </form>
    </div>
    <div id="tbUpdatePwd">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="resetPassword();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgUpdatePwd').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- 修改用户密码 -->

    <script type="text/javascript">
        $(function(){
            $("#tblUser").datagrid({
                url:"<%=rootUrl%>/user/list.grid",
                columns:[[
                    {field:'id',title:'ID',width:50},
                    {field:'loginName',title:'登录账号',width:50},
                    {field:'nickName',title:'用户昵称',width:80},
                    {field:'mobile',title:'手机号码',width:80},
                    {
                        field:'state',
                        title:'用户状态',
                        width:50,
                        formatter:function(value,data,index){
                            <c:forEach items="${userStates }" var="userState">
	                            if("${userState}"==value){
	                                return "${userState.info}";
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
                    },
                    {
                        field:'lastLoginTime',
                        title:'最后登录时间',
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
                toolbar:'#toolbarTblUser'
            });
        });
        //弹出新增用户窗口
        function showAddDialog() {
            $('#dialogAddUser').dialog('open').dialog('setTitle','新增用户');
            disableValidateWhenInit("formAddUser");
        }
        //保存新用户
        function saveUser() {
            enableValidateWhenSubmit('formAddUser');
            if($('#formAddUser').form('validate')){
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/user/save",
                    type:"POST",
                    dataType:"json",
                    data:$('#formAddUser').serialize(),
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
                            $('#dialogAddUser').dialog("close");
                            $("#tblUser").datagrid("reload");
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
        //弹出修改用户基本信息窗口
        function showUpdateUIDialog() {
            var row=$('#tblUser').datagrid("getSelected");
            if(row){
                $('#dlgUserBaseInfo').dialog('open').dialog('setTitle','修改用户基本信息');
                $('#formUserBaseInfo').form('clear');
                $('#formUserBaseInfo').form('load',row);
                disableValidateWhenInit("formUserBaseInfo");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后的用户的基本信息
        function updateUserInfo(){
            enableValidateWhenSubmit('formUserBaseInfo');
            if ($('#formUserBaseInfo').form('validate')) {
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/user/update",
                    type:"POST",
                    dataType:"json",
                    data:$('#formUserBaseInfo').serialize(),
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
                            $('#dlgUserBaseInfo').dialog("close");
                            $("#tblUser").datagrid("reload");
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
        //弹出角色修改窗口
        function showUpdateRoleDialog(){
            var row=$('#tblUser').datagrid("getSelected");
            if(row){
            	$.ajax({
                    async:false,
                    url:"<%=rootUrl%>/user/findRolesWithUserChecked",
                    type:"POST",
                    dataType:"json",
                    data:{
                        userId:row.id
                    },
                    beforeSend:function() {
                        showLoading();
                    },
                    success:function(data) {
                        hideLoading();
                        if (0==data.code) {
                        	$('#dlgUpdateUserRole').dialog('open').dialog('setTitle','修改用户角色');
                            $('#formUpdateUserRole').form('clear');
                            $('#formUpdateUserRole').form('load',{
                                updUserRoleId:row.id,
                                updUserRoleLoginNm:row.loginName
                            });
                            $("#updUserRole").combobox("loadData", data.body);
                            disableValidateWhenInit("formUpdateUserRole");
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
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后的角色
        function updateUserRole(){
            enableValidateWhenSubmit('formUpdateUserRole');
            if ($('#dlgUpdateUserRole').form('validate')) {
                var roleIds=$("#updUserRole").combobox("getValues").toString();
                if(isEmptyStr(roleIds)){
                    $.messager.show({
                        title:'错误',
                        msg:'角色必选.'
                    });
                    return;
                }
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/user/giveRoles",
                    type:"POST",
                    dataType:"json",
                    data:{
                        userId:$("#updUserRoleId").val(),
                        roleIds:roleIds
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
                            $('#dlgUpdateUserRole').dialog("close");
                            $("#tblUser").datagrid("reload");
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
        //弹出重置密码窗口
        function showUpdatePwdDialog(){
            var row=$('#tblUser').datagrid("getSelected");
            if(row){
                $('#dlgUpdatePwd').dialog('open').dialog('setTitle','重置密码');
                $('#formUpdatePwd').form('clear');
                $('#formUpdatePwd').form('load',row);
                $('#updNewPwd').val('');
                disableValidateWhenInit("formUpdatePwd");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //重置密码
        function resetPassword(){
            enableValidateWhenSubmit('formUpdatePwd');
            if ($('#formUpdatePwd').form('validate')) {
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/user/resetpwd",
                    type:"POST",
                    dataType:"json",
                    data:$('#formUpdatePwd').serialize(),
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
                            $('#dlgUpdatePwd').dialog("close");
                            $("#tblUser").datagrid("reload");
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
        //删除用户
        function deleteUser(){
            var row=$('#tblUser').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','确定删除该用户吗?',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"<%=rootUrl%>/user/delete",
                            type:"POST",
                            dataType:"json",
                            data:{userId:row.id},
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
                                    $('#tblUser').datagrid('reload');
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