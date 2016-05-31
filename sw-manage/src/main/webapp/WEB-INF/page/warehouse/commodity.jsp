<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>商品管理</title>
    <!-- 工具栏 -->
    <div id="toolbarTblCommodity">
    	<form id="searchCommodityForm">
    		<label>商品名称：</label><input id="searchCommodityName" name="commodityName" class="easyui-validatebox validatebox-text textbox"/>
    		<label>商品编号：</label><input id="searchIdentifier" name="commodityIdentifier" class="easyui-validatebox validatebox-text textbox"/>
    		<label>所属仓库：</label>
    			<select id="searchWarehouseType" name="warehouseType" class="easyui-combobox" style="width: 150px;">
    			<option value="">--请选择--</option>
         		<c:forEach items="${warehouseTypes}" var="warehouseType">
             		<option value="${warehouseType}">${warehouseType.info }</option>
         		</c:forEach>
    		</select>
    		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchBtn()">查询</a>
    	</form>
        <div>
        	<shiro:hasPermission name="warehouse:commodity:create">
                <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddDialog();">新增商品</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="warehouse:commodity:update">
                <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateDialog();">修改商品</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="warehouse:commodity:delete">
            	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteUser();">删除商品</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" title="商品类型" style="width:150px;">
			<shiro:hasPermission name="warehouse:commodityCategory:treeView">
				<div id="commodityCategoryDiv">数据加载中...</div>
			</shiro:hasPermission>
		</div>
		<div data-options="region:'center'" style="padding:10px">
		<!-- 表格 -->
    	<table id="tblCommodity" style="width: 98%;" class="easyui-datagrid">
    	</table>
    	</div>
	</div>
    <!-- 新增窗口 -->
    <div id="dialogAddCommodity" class="easyui-dialog" style="width: 700px; height: 600px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogAddCommodity'">
    	<form id="formAddCommodity" method="post">
        	<table style="width:100%">
        		<tr>
        			<td class="tdHeight"> <label>商品类别：</label> </td>
        			<td><input id="commodityCategoryAddId" name="commodityCategoryId"  class="easyui-validatebox commodityCategoryId" data-options="required:true" /><span class="spanRequired">*</span></td>
        			<td><label>品牌：</label> </td>
        			<td><input name="brand" class="easyui-validatebox" maxlength="20" /></td>
        		</tr>
        		<tr>
        			<td class="tdHeight"><label>商品编号：</label> </td>
        			<td><input name="commodityIdentifier" class="easyui-validatebox" maxlength="20" /></td>
        			<td><label>规格/型号：</label></td>
        			<td><input name="model" class="easyui-validatebox" maxlength="60"  /></td>
        		</tr>
        		<tr>
        			<td class="tdHeight"> <label>商品名称：</label> </td>
        			<td><input name="commodityName" class="easyui-validatebox" maxlength="20"  data-options="required:true" /><span class="spanRequired">*</span></td>
        			<td><label>计量单位：</label> </td>
        			<td>
        				<select name="companyType" class="easyui-combobox" style="width: 150px;">
                    		<c:forEach items="${companyTypes}" var="companyType">
                        		<option value="${companyType}" <c:if test="${companyType eq 'whole' }">selected="selected" </c:if>>${companyType.info}</option>
                   			</c:forEach>
                		</select>
        			</td>
        		</tr>
        		<tr>
        			<td class="tdHeight"><label>条码：</label></td>
        			<td><input name="barCode" class="easyui-numberbox" maxlength="20"  /></td>
        			<td><label>所属仓库：</label></td>
        			<td>
        				<select name="warehouseType" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    		<c:forEach items="${warehouseTypes}" var="warehouseType">
                        		<option value="${warehouseType}" <c:if test="${warehouseType eq 'whole' }">selected="selected" </c:if>>${warehouseType.info }</option>
                   			</c:forEach>
                		</select>
        			</td>
        		</tr>
        		<tr>
        			<td class="tdHeight"> <label>销售价格：</label> </td>
        			<td><input name="sellingPrice" class="easyui-numberbox" max="99999.99" precision="2" data-options="required:true" /><span class="spanRequired">*</span></td>
        			<td><label>批发价格：</label> </td>
        			<td><input name="wholesalePrice" class="easyui-numberbox" max="99999.99" precision="2" data-options="required:true" /><span class="spanRequired">*</span></td>
        		</tr>
        		<tr>
        			<td class="tdHeight"> <label>成本价格：</label> </td>
        			<td><input name="costPrice"   class="easyui-numberbox" max="99999.99" precision="2" data-options="required:true" /><span class="spanRequired">*</span></td>
        			<td><label>供应商：</label> </td>
        			<td>
        				<input name="supplierId" id="supplierIdAdd" type="hidden">
        				<input  readonly="readonly" id="supplierNameAdd" class="easyui-validatebox" />
        				<shiro:hasPermission name="currency:supplier:view">
        					<a class="easyui-linkbutton" data-options="iconCls:'icon-vcard_add'" onclick="openCommodityBtn('1')"></a>
        				</shiro:hasPermission>
        			</td>
        		</tr>
        		<tr>
        			<td class="tdHeight"><label>库存上限：</label> </td>
        			<td><input name="upperLimitNumber" class="easyui-numberbox" max="999" data-options="required:true" /><span class="spanRequired">*</span></td>
        			<td><label>库存下限：</label> </td>
        			<td><input name="lowerNumber" class="easyui-numberbox"  data-options="required:true" /><span class="spanRequired">*</span></td>
        		</tr>
        		<tr>
        			<td class="tdHeight"><label>库存数量：</label> </td>
        			<td><input  value="0" readonly="readonly" disabled="disabled" class="easyui-numberbox" /></td>
        		</tr>
        		<tr >
        			<td class="tdHeight"><label>备注：</label> </td>
        			<td colspan="3">
        				<input style="width: 85%" maxlength="100" name="remarks" class="easyui-validatebox"  />
        			</td>
        		</tr>
        	</table>
        </form>
    </div>
    	<div id="toolbarDialogAddCommodity">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveCommodity();" style="width: 90px">保存</a>
       	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogAddCommodity').dialog('close');" style="width: 90px">取消</a>
      	</div>
    <!-- 新增窗口 -->
    
    <!-- 修改窗口 -->
    <div id="dialogUpdateCommodity" class="easyui-dialog" style="width: 700px; height: 600px; padding: 10px 20px;" data-options="modal:true,closed:true,top:50,buttons:'#toolbarDialogUpdateCommodity'">
    	<form id="formUpdateCommodity" method="post">
        	<table style="width:100%">
        		<tr>
        			<td class="tdHeight"> <label>商品类别：</label> </td>
        			<td>
        				<select  class="commodityCategoryId" name="commodityCategoryId" data-options="required:true"></select>
        				<span class="spanRequired">*</span></td>
        			<td><label>品牌：</label> </td>
        			<td><input name="brand" class="easyui-validatebox" maxlength="20" /><input name="id" type="hidden"> </td>
        		</tr>
        		<tr>
        			<td class="tdHeight"><label>商品编号：</label> </td>
        			<td><input name="commodityIdentifier" class="easyui-validatebox" maxlength="20"  /></td>
        			<td><label>规格/型号：</label> </td>
        			<td><input name="model" class="easyui-validatebox" maxlength="60"  /></td>
        		</tr>
        		<tr>
        			<td class="tdHeight"> <label>商品名称：</label> </td>
        			<td><input name="commodityName" class="easyui-validatebox" maxlength="20"  data-options="required:true" /><span class="spanRequired">*</span></td>
        			<td><label>计量单位：</label> </td>
        			<td>
        				<select name="companyType" class="easyui-combobox" style="width: 150px;">
                    		<c:forEach items="${companyTypes}" var="companyType">
                        		<option value="${companyType}" <c:if test="${companyType eq 'whole' }">selected="selected" </c:if>>${companyType.info}</option>
                   			</c:forEach>
                		</select>
        			</td>
        		</tr>
        		<tr>
        			<td class="tdHeight"><label>条码：</label></td>
        			<td><input name="barCode" class="easyui-numberbox"   data-options="validType:['between[1,20]']"/></td>
        			<td><label>所属仓库：</label> </td>
        			<td>
        				<select name="warehouseType" class="easyui-combobox" data-options="editable:false,required:true,validType:['selectValueRequired']" style="width: 150px;">
                    		<c:forEach items="${warehouseTypes}" var="warehouseType">
                        		<option value="${warehouseType}" <c:if test="${warehouseType eq 'whole' }">selected="selected" </c:if>>${warehouseType.info }</option>
                   			</c:forEach>
                		</select>
        			</td>
        		</tr>
        		<tr>
        			<td class="tdHeight"> <label>销售价格：</label> </td>
        			<td><input name="sellingPrice" class="easyui-numberbox" precision="2" max="99999.99"  data-options="required:true" /><span class="spanRequired">*</span></td>
        			<td><label>批发价格：</label> </td>
        			<td><input name="wholesalePrice" class="easyui-numberbox" precision="2" max="99999.99" data-options="required:true" /><span class="spanRequired">*</span></td>
        		</tr>
        		<tr>
        			<td class="tdHeight"> <label>成本价格：</label> </td>
        			<td><input name="costPrice" class="easyui-numberbox" max="99999.99" precision="2" data-options="required:true" /><span class="spanRequired">*</span></td>
        			<td><label>供应商：</label> </td>
        			<td>
        				<input  name="supplierId" id="supplierIdUpdate" type="hidden">
        				<input  class="easyui-validatebox"  id="supplierNameUpdate" readonly="readonly"/>
        					<shiro:hasPermission name="currency:supplier:view">
        						<a class="easyui-linkbutton" data-options="iconCls:'icon-vcard_add'" onclick="openCommodityBtn('2')"></a>
        					</shiro:hasPermission>
        				</td>
        		</tr>
        		<tr>
        			<td class="tdHeight"><label>库存上限：</label> </td>
        			<td><input name="upperLimitNumber" class="easyui-numberbox" max="999" data-options="required:true" /><span class="spanRequired">*</span></td>
        			<td><label>库存下限：</label> </td>
        			<td><input name="lowerNumber" class="easyui-numberbox" data-options="required:true" /><span class="spanRequired">*</span></td>
        		</tr>
        		<tr>
        			<td class="tdHeight"><label>库存数量：</label> </td>
        			<td><input  readonly="readonly" disabled="disabled" name="commodityNumber" class="easyui-numberbox" /></td>
        		</tr>
        		<tr >
        			<td class="tdHeight"><label>备注：</label> </td>
        			<td colspan="3">
        				<input style="width: 85%" maxlength="100" name="remarks" class="easyui-validatebox"  />
        			</td>
        		</tr>
        	</table>
        </form>
    </div>
    	<div id="toolbarDialogUpdateCommodity">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateCommodity();" style="width: 90px">保存</a>
       	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dialogUpdateCommodity').dialog('close');" style="width: 90px">取消</a>
      	</div>
    <!-- 修改窗口 -->
    <div id="openCommodity"></div>
    <script type="text/javascript">
    //查询
    function searchBtn(){
    	var searchIdentifier=$('#searchIdentifier').val();
    	var name=$('#searchCommodityName').val();
    	var searchWarehouseType=$('#searchWarehouseType').combobox('getValue');
    	$('#tblCommodity').datagrid({
        	queryParams: {
        		commodityName: name,
        		warehouseType:searchWarehouseType,
        		commodityIdentifier:searchIdentifier,
        		}
        	});
    	}
        $(function(){
        	//下拉属性菜单
        	$('.commodityCategoryId').combotree({
            	method:'GET',
                animate:true,
                lines:true,
        		url:"<%=rootUrl%>/commodityCategory/findCommodityCategoryCheckedTree",
        		onClick: function(node){
        			$('#commodityCategoryAddId').val(node.id);
               	}
            });
        	//商品类型属性菜单显示
        	 $('#commodityCategoryDiv').tree({
                method:'GET',
                animate:true,
                lines:true,
                url:"<%=rootUrl%>/commodityCategory/findCommodityCategoryCheckedTree",
                onClick: function(node){ //点击商品类型节点查询事件
                var row=$('#commodityCategoryDiv').tree("getSelected");
                var array=$('#commodityCategoryDiv').tree('onDblClickCell');
                var str="";
                for (var i = 0; i < array.length; i++) {
                	str+=array[i].id+",";
				}
                $('#tblCommodity').datagrid({
                	queryParams: {
                		ids: str+node.id
                		}
                	});
            	}
            	
            });
            $("#tblCommodity").datagrid({
                url:"<%=rootUrl%>/commodity/list.grid",
                columns:[[
                    {field:'categoryName',title:'商品类型',width:30},
                    {field:'brand',title:'品牌',width:50},
                    {field:'commodityIdentifier',title:'商品编号',width:50},
                    {field:'commodityName',title:'商品名称',width:50},
                    {field:'model',title:'商品型号',width:50},
                    {field:'sellingPrice',title:'销售价格',width:30},
                    {field:'wholesalePrice',title:'批发价格',width:30},
                    {field:'costPrice',title:'成本价格',width:30},
                    {
                        field:'warehouseType',
                        title:'仓库',
                        width:40,
                        formatter:function(value,row,index){
                        	<c:forEach items="${warehouseTypes}" var="warehouseType">
	                            if("${warehouseType}"==value){
	                                return "${warehouseType.info}";
	                            }
                    		</c:forEach>
                        }
                    },
                    {
                        field:'companyType',
                        title:'单位',
                        width:40,
                        formatter:function(value,row,index){
                        	<c:forEach items="${companyTypes}" var="companyType">
	                            if("${companyType}"==value){
	                                return "${companyType.info}";
	                            }
                    		</c:forEach>
                        }
                    },
                    {field:'commodityNumber',title:'数量',width:30},
                    {
                        field:'createTime',
                        title:'创建时间',
                        width:80,
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
                toolbar:'#toolbarTblCommodity'
            });
        });
        //弹出新增用户窗口
        function showAddDialog() {
        	$('#dialogAddCommodity').dialog('open').dialog('setTitle','新增商品');
        	disableValidateWhenInit('formAddCommodity');
        }
        //保存
        function saveCommodity() {
        	var commodityCategoryAddId=$('#commodityCategoryAddId').val()
        	if(commodityCategoryAddId == ''){
        		 $.messager.show({
                     title:'提示',
                     msg:"请选择商品类别."
                 });
        	}
        	enableValidateWhenSubmit('formAddCommodity');
            if($('#formAddCommodity').form('validate')){
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/commodity/save",
                    type:"POST",
                    dataType:"json",
                    data:$('#formAddCommodity').serialize(),
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
                            $('#formAddCommodity').form("clear");
                            $('#dialogAddCommodity').dialog("close");
                            $("#tblCommodity").datagrid("reload");
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
        //删除
        function deleteUser(){
            var row=$('#tblCommodity').datagrid("getSelected");
            if(row){
                $.messager.confirm('提示','确定删除该用户吗?',function(r) {
                    if(r){
                        $.ajax({
                            async:false,
                            url:"<%=rootUrl%>/commodity/delete",
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
                                    $('#tblCommodity').datagrid('reload');
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
        
        //弹出修改商品窗口
        function showUpdateDialog(){
            var row=$('#tblCommodity').datagrid("getSelected");
            if(row){
                $('#dialogUpdateCommodity').dialog('open').dialog('setTitle','修改商品');
                $('#formUpdateCommodity').form('clear');
                $('#formUpdateCommodity').form('load',row);
                $('#supplierNameUpdate').val(row.supplierId);
                disableValidateWhenInit('formUpdateCommodity');
            }else{
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
        //保存
        function updateCommodity() {
        	enableValidateWhenSubmit('formUpdateCommodity');
            if($('#formUpdateCommodity').form('validate')){
                $.ajax({
                    async:false,
                    url:"<%=rootUrl%>/commodity/update",
                    type:"POST",
                    dataType:"json",
                    data:$('#formUpdateCommodity').serialize(),
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
                            $('#dialogUpdateCommodity').dialog("close");
                            $("#tblCommodity").datagrid("reload");
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
        function openCommodityBtn(type){
        	//打开供应商窗口
           $('#openCommodity').dialog({
                title: '供应商列表',
                width: 800,
                height: 600,
                closed: false,
                cache: false,
                href: '<%=rootUrl%>/currency/toSupplierList?type='+type,
                modal: true
            });
        }
    </script>