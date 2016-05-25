<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>商品类别管理</title>

    <!-- 工具栏 -->
    <div id="toolbarTblCommodityCategory">
        <div>
        	<shiro:hasPermission name="sys:resource:create">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">新增子级</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:resource:update">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateDialog();">修改资源</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:resource:delete">
	            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteResource();">删除资源</a>
            </shiro:hasPermission>
        </div>
    </div>
    <!-- 表格 -->
    <table id="tblCommodityCategory" style="width: 98%;" class="easyui-treegrid"></table>

    <!-- 添加菜单窗口 -->
    <div id="dialogAddCommodityCategory" class="easyui-dialog" style="width: 500px; height: 420px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddCommodityCategory'">
        <form id="formAddCommodityCategory" method="post">
        	<div class="fitem">
                <label>父ID：</label>
                <input id="addParentId" name="parentId" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <input type="hidden" id="addParentIds" name="parentIds"/>
            <div class="fitem">
                <label>商品类别名称：</label>
                <input name="categoryName" class="easyui-validatebox" data-options="required:true,validType:['between[1,20]']" />
            </div>
            <div class="fitem">
                <label>资源Url：</label>
                <input name="url" class="easyui-validatebox" />
            </div>
            <div class="fitem">
                <label>权限标识：</label>
                <input id="addPermission" name="permission" class="easyui-validatebox" data-options="required:true" />
            </div>
            <div class="fitem">
                <label>权值：</label>
                <input name="weight" class="easyui-validatebox" data-options="min:0,increment:1,max:10000,required:true" />
            </div>
            <div class="fitem">
                <label>资源描述：</label>
                <input name="resourceDesc" class="easyui-validatebox"/>
            </div>
            <div class="fitem">
                <label>资源状态：</label>
                <select name="isShow" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    <c:forEach items="${showTypes }" var="showType">
                        <option value="${showType }"  <c:if test="${showType eq 'show' }">selected="selected" </c:if>>${showType.info }</option>
                   	</c:forEach>
                </select>
            </div>
        </form>
    </div>
    <div id="toolbarDialogAddCommodityCategory">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveResource();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddCommodityCategory').dialog('close');" style="width: 90px">取消</a>
    </div>

    <!-- 修改菜单窗口 -->
    <div id="dialogUpdateResource" class="easyui-dialog" style="width: 500px; height: 420px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogUpdateResource'">
        <form id="formUpdateResource" method="post">
            <div class="fitem">
                <label>资源ID：</label>
                <input name="id" class="easyui-validatebox" readonly="readonly" data-options="required:true"/>
            </div>
            <div class="fitem">
                <label>资源名称：</label>
                <input name="name" class="easyui-validatebox" readonly="readonly" data-options="required:true,validType:['between[1,20]']" />
            </div>
            <div class="fitem">
                <label>资源Url：</label>
                <input name="url" class="easyui-validatebox" />
            </div>
            <div class="fitem">
                <label>权限标识：</label>
                <input name="permission" class="easyui-validatebox" data-options="required:true" />
            </div>
            <div class="fitem">
                <label>菜单类型：</label>
                <select name="menuType" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    <c:forEach items="${menuTypes }" var="menuType">
                        <option value="${menuType }" >${menuType.info }</option>
                   	</c:forEach>
                </select>
            </div>
            <div class="fitem">
                <label>权值：</label>
                <input name="weight" class="easyui-validatebox" data-options="min:0,increment:1,max:10000,required:true"/>
            </div>
            <div class="fitem">
                <label>资源描述：</label>
                <input name="resourceDesc" class="easyui-validatebox"/>
            </div>
            <div class="fitem">
                <label>资源状态：</label>
                <select name="isShow" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    <c:forEach items="${showTypes }" var="showType">
                        <option value="${showType }"  <c:if test="${showType eq 'show' }">selected="selected" </c:if>>${showType.info }</option>
                   	</c:forEach>
                </select>
            </div>
        </form>
    </div>
    <div id="toolbarDialogUpdateResource">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateResource();" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogUpdateResource').dialog('close');" style="width: 90px">取消</a>
    </div>

    <script type="text/javascript">
        //初始化页面
        $(function() {
            $("#tblCommodityCategory").treegrid({
                idField:'categoryName',
                treeField:'categoryName',
                url:"<%=rootUrl%>/commodityCategory/listTree.grid",
                columns:[[
                    {field:'categoryName',title:'类别名称',width:50},
                    {field:'remarks',title:'备注',width:100},
                    {field:'parentId',title:'父Id',width:30},
                    {field:'parentIds',title:'父Id组',width:60},
                    {
                        field:'createTime',
                        title:'创建时间',
                        width:100,
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
                toolbar:'#toolbarTblCommodityCategory'
            });
        });
        //弹出新增资源窗口
        function showAddDialog(){
            var row=$('#tblCommodityCategory').datagrid("getSelected");
            if(row){
            		$('#dialogAddCommodityCategory').dialog('open').dialog('setTitle','新增子级');
                    disableValidateWhenInit('formAddCommodityCategory');
                    $('#addParentId').val(row.id);
                    $('#addParentIds').val(row.parentIds);
                    $('#addPermission').val(row.permission);
            }else{
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存新增的资源
        function saveResource(){
            enableValidateWhenSubmit('formAddCommodityCategory');
            if($('#formAddCommodityCategory').form('validate')){
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/resource/save",
                    type:"POST",
                    data:$('#formAddCommodityCategory').serialize(),
                    dataType: "json",
                    beforeSend: function(){
                        showLoading();
                    },
                    success:function(data){
                        hideLoading();
                        if(0==data.code){
                            $.messager.show({
                                title:'提示',
                                msg:data.msg
                            });
                            $('#dialogAddCommodityCategory').dialog('close');
                            $("#tblCommodityCategory").treegrid("reload");
                        }else{
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
        //删除资源
        function deleteResource(){
            var row=$('#tblCommodityCategory').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','确定删除该资源（菜单）吗?',function(r){
                    if(r){
                        $.ajax({
                            async:false,
                            url:"<%=rootUrl%>/resource/delete",
                            type:"POST",
                            data:{resourceId:row.id},
                            dataType: "json",
                            beforeSend: function(){
                                showLoading();
                            },
                            success:function(data){
                                hideLoading();
                                if(0==data.code){
                                    $.messager.show({
                                        title:'提示',
                                        msg:data.msg
                                    });
                                    $('#tblCommodityCategory').treegrid('reload');
                                }else{
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
            }else{
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //弹出修改资源窗口
        function showUpdateDialog(){
            var row=$('#tblCommodityCategory').datagrid("getSelected");
            if(row){
                $('#dialogUpdateResource').dialog('open').dialog('setTitle','修改资源');
                $('#formUpdateResource').form('clear');
                $('#formUpdateResource').form('load',row);
                disableValidateWhenInit('formUpdateResource');
            }else{
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存更新的资源
        function updateResource(){
            enableValidateWhenSubmit('formUpdateResource');
            if ($('#formUpdateResource').form('validate')) {
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/resource/update",
                    type:"POST",
                    data:$('#formUpdateResource').serialize(),
                    dataType: "json",
                    beforeSend: function(){
                        showLoading();
                    },
                    success:function(data){
                        hideLoading();
                        if(0==data.code){
                            $.messager.show({
                                title:'提示',
                                msg:data.msg
                            });
                            $('#dialogUpdateResource').dialog("close");
                            $('#tblCommodityCategory').treegrid('reload');
                        }else{
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
    </script>