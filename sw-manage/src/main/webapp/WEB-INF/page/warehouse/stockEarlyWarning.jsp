<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>库存预警</title>
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
        	//商品类型属性菜单显示
        	 $('#commodityCategoryDiv').tree({
                method:'GET',
                animate:true,
                lines:true,
                url:"<%=rootUrl%>/commodityCategory/findCommodityCategoryCheckedTree",
                onClick: function(node){ //点击商品类型节点查询事件
                var row=$('#commodityCategoryDiv').tree("getSelected");
                var array=$('#commodityCategoryDiv').tree('getChildren',node.target);
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
                url:"<%=rootUrl%>/warehouse/list.grid?type="+1,
                columns:[[
                    {field:'categoryName',title:'商品类型',width:50},
                    {field:'commodityIdentifier',title:'商品编号',width:50},
                    {field:'commodityName',title:'商品名称',width:50},
                    {field:'model',title:'商品型号',width:50},
                    {field:'upperLimitNumber',title:'库存上限',width:30},
                    {field:'lowerNumber',title:'库存下限',width:30},
                    {field:'commodityNumber',title:'库存数',width:30},
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
    </script>