<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>客户资料管理管理</title>

    <!-- 工具栏 -->
    <div id="toolbarTblCustomer">
    	<label>客户姓名：</label><input id="searchCustomerName" name="customerName" class="easyui-validatebox validatebox-text textbox"/>
    		<label>手机：</label><input id="searchPhone" name="phone" class="easyui-validatebox validatebox-text textbox"/>
    		<label>公司姓名：</label><input id="searchCompanyName" name="companyName" class="easyui-validatebox validatebox-text textbox"/>
    		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchBtn()">查询</a>
        <div>
        	<shiro:hasPermission name="datas:customer:create">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">新增客户资料</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="datas:customer:update">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateUIDialog();">修改客户资料</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="datas:customer:delete">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteCustomer();">删除客户资料</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblCustomer" style="width: 98%;" class="easyui-datagrid"></table>
    
    <!-- 新增窗口 -->
    <div id="dialogAddCustomer" class="easyui-dialog" style="width: 500px; height: 500px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddCustomer'">
        <form id="formAddCustomer" method="post">
       		 <div class="fitem">
                <label>客户姓名：</label>
                <input name="customerName" class="easyui-validatebox"  maxlength="20" data-options="required:true"/><span class="spanRequired">*</span>
            </div>
            <div class="fitem">
                <label>公司名称：</label>
                <input name="companyName" class="easyui-validatebox"   maxlength="20" data-options="required:true"/><span class="spanRequired">*</span>
            </div>
        	<div class="fitem">
                <label>客户分组：</label>
                <select name="customerGrouping" class="easyui-combobox" style="width: 200px;">
                		<option value="">--请选择--</option>
                    <c:forEach items="${customerGroupings }" var="customerGrouping">
                        <option value="${customerGrouping }">${customerGrouping.info }</option>
                   	</c:forEach>
                </select>
            </div>
            <div class="fitem">
                <label>客户类型：</label>
                <select name="customerType" class="easyui-combobox"  style="width: 200px;">
                		<option value="">--请选择--</option>
                    <c:forEach items="${customerTypes }" var="customerType">
                        <option value="${customerType }">${customerType.info }</option>
                   	</c:forEach>
                </select>
            </div>
            <div class="fitem">
                <label>手机：</label>
                <input style="width: 200px;"  name="phone" class="easyui-numberbox" validType="mphone" />
            </div>
            <div class="fitem">
                <label>电话：</label>
                <input  name="telephone" class="easyui-validatebox" data-options="validType:'phone'" maxlength="20" />
            </div>
            <div class="fitem">
                <label>QQ：</label>
                <input style="width: 200px;" name="qq" class="easyui-numberbox" maxlength="20" />
            </div>
            <div class="fitem">
                <label>邮箱：</label>
                <input name="email" class="easyui-validatebox" data-options="validType:'email'" maxlength="20" />
            </div>
            <div class="fitem">
                <label>职务：</label>
                <input name="position" class="easyui-validatebox" maxlength="20" />
            </div>
            <div class="fitem">
                <label>角色描述：</label>
                <input name="roleDesc" class="easyui-validatebox"/>
            </div>
            <div class="fitem">
                <label>备注：</label>
                <input name="remarks" class="easyui-validatebox"  maxlength="100"/>
            </div>
        </form>
    </div>
    <div id="toolbarDialogAddCustomer">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveCustomer();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddCustomer').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- END新增窗口 -->
    
    <!-- 修改客户资料信息 -->
    <div id="dlgCustomerBaseInfo" class="easyui-dialog" style="width: 500px; height: 500px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#tbRoleBaseInfo'">
        <form id="formCustomerBaseInfo" method="post">
        	<div class="fitem">
                <label>客户姓名：</label>
                <input name="customerName" class="easyui-validatebox"  maxlength="20" data-options="required:true"/><span class="spanRequired">*</span>
                <input name="id" type="hidden"/>
            </div>
            <div class="fitem">
                <label>公司名称：</label>
                <input name="companyName" class="easyui-validatebox"  maxlength="20" data-options="required:true"/><span class="spanRequired">*</span>
            </div>
        	<div class="fitem">
                <label>客户分组：</label>
                <select name="customerGrouping" class="easyui-combobox"  style="width: 200px;">
                		<option value="">--请选择--</option>
                    <c:forEach items="${customerGroupings }" var="customerGrouping">
                        <option value="${customerGrouping }">${customerGrouping.info }</option>
                   	</c:forEach>
                </select>
            </div>
            <div class="fitem">
                <label>客户类型：</label>
                <select name="customerType" class="easyui-combobox"  style="width: 200px;">
                		<option value="">--请选择--</option>
                    <c:forEach items="${customerTypes }" var="customerType">
                        <option value="${customerType }">${customerType.info }</option>
                   	</c:forEach>
                </select>
            </div>
            <div class="fitem">
                <label>手机：</label>
                <input style="width: 200px;" name="phone" class="easyui-numberbox" validType="mphone" />
            </div>
            <div class="fitem">
                <label>电话：</label>
                <input  name="telephone" class="easyui-validatebox" maxlength="20" />
            </div>
            <div class="fitem">
                <label>QQ：</label>
                <input style="width: 200px;" name="qq" class="easyui-numberbox" maxlength="20" />
            </div>
            <div class="fitem">
                <label>邮箱：</label>
                <input name="email" class="easyui-validatebox" maxlength="20" data-options="validType:'email'"/>
            </div>
            <div class="fitem">
                <label>职务：</label>
                <input name="position" class="easyui-validatebox" maxlength="20" />
            </div>
            <div class="fitem">
                <label>角色描述：</label>
                <input name="roleDesc" class="easyui-validatebox"/>
            </div>
            <div class="fitem">
                <label>备注：</label>
                <input name="remarks" class="easyui-validatebox"  maxlength="100"/>
            </div>
        </form>
    </div>
    <div id="tbRoleBaseInfo">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateCustomer();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlgCustomerBaseInfo').dialog('close');" style="width: 90px">取消</a>
    </div>
    <!-- END修改客户资料信息-->
    <script type="text/javascript">
    //查询
    function searchBtn(){
    	var searchCustomerName=$('#searchCustomerName').val();
    	var searchPhone=$('#searchPhone').val();
    	var searchCompanyName=$('#searchCompanyName').val();
    	$('#tblCustomer').datagrid({
        	queryParams: {
        		customerName: searchCustomerName,
        		phone: searchPhone,
        		companyName: searchCompanyName,
        		}
        	});
    	}
        $(function(){
            $("#tblCustomer").datagrid({
                url:"<%=rootUrl%>/customer/list.grid",
                columns:[[
                    {field:'companyName',title:'公司名称',width:50},
                    {field:'customerName',title:'姓名',width:50},
                    {field:'position',title:'职务',width:50},
                    {field:'phone',title:'手机',width:50},
                    {field:'telephone',title:'电话',width:50},
                    {field:'email',title:'E-mail',width:50},
                    {
                        field:'customerType',
                        title:'客户类型',
                        width:50,
                        formatter:function(value,data,index){
                            <c:forEach items="${customerTypes }" var="customerType">
	                            if("${customerType}"==value){
	                                return "${customerType.info}";
	                            }
                        	</c:forEach>
                        }
                    },
                    {
                        field:'customerGrouping',
                        title:'客户分组',
                        width:50,
                        formatter:function(value,data,index){
                            <c:forEach items="${customerGroupings }" var="customerGrouping">
	                            if("${customerGrouping}"==value){
	                                return "${customerGrouping.info}";
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
                    }
                ]],
                border:false,
                rownumbers:true,
                fitColumns:true,
                singleSelect:true,
                fit:true,
                pagination: true,
                striped:true,
                toolbar:'#toolbarTblCustomer'
            });
        });
        //弹出新增角色窗口
        function showAddDialog() {
            $('#dialogAddCustomer').dialog('open').dialog('setTitle','新增客户资料');
            disableValidateWhenInit("formAddCustomer");
        }
        //保存
        function saveCustomer() {
            enableValidateWhenSubmit('formAddCustomer');
            if($('#formAddCustomer').form('validate')){
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/customer/save",
                    type:"POST",
                    dataType:"json",
                    data:$('#formAddCustomer').serialize(),
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
                            $('#dialogAddCustomer').form("clear");
                            $('#dialogAddCustomer').dialog("close");
                            $("#tblCustomer").datagrid("reload");
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
        //弹出修改客户资料信息
        function showUpdateUIDialog() {
            var row=$('#tblCustomer').datagrid("getSelected");
            if(row){
                $('#dlgCustomerBaseInfo').dialog('open').dialog('setTitle','修改客户资料');
                $('#formCustomerBaseInfo').form('clear');
                $('#formCustomerBaseInfo').form('load',row);
                disableValidateWhenInit("formCustomerBaseInfo");
            } else {
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存修改后的客户资料信息
        function updateCustomer(){
            enableValidateWhenSubmit('formCustomerBaseInfo');
            if ($('#formCustomerBaseInfo').form('validate')) {
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/customer/update",
                    type:"POST",
                    dataType:"json",
                    data:$('#formCustomerBaseInfo').serialize(),
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
                            $('#dlgCustomerBaseInfo').dialog("close");
                            $("#tblCustomer").datagrid("reload");
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
        //删除客户资料
        function deleteCustomer(){
            var row=$('#tblCustomer').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','确定删除该数据吗?',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"<%=rootUrl%>/customer/delete",
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
                                    $('#tblCustomer').datagrid('reload');
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
