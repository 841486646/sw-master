<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>商品列表</title>
	<body>
	 <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" title="商品类型" style="width:150px;">
			<shiro:hasPermission name="warehouse:commodityCategory:treeView">
				<div id="currencyCommodityCategoryDivTree">数据加载中...</div>
			</shiro:hasPermission>
		</div>
		<div data-options="region:'center'" style="padding:10px">
	<table id="currencyCommodityDatagrid" singleSelect="true" striped="true" fit="true" class="easyui-datagrid" url="<%=rootUrl%>/commodity/list.grid" border="false" fitColumns="true" rownumbers="false" pagination="true">
		<thead>
			<tr>
				<th field="categoryName" editor="text" width="80">商品类别</th>
				<th field="commodityName" width="80">商品名称</th>
				<th field="sellingPrice" width="80">销售价格</th>
				<th field="wholesalePrice" width="80">批发价格</th>
				<th field="costPrice" width="80">成本价格</th>
				<th field="commodityNumber" width="100">库存数量</th>
			</tr>
		</thead>
	</table>
	</div>
	</div>
	<script type="text/javascript">
	$(function(){
		//商品类型属性菜单显示
   	 $('#currencyCommodityCategoryDivTree').tree({
           method:'GET',
           animate:true,
           lines:true,
           url:"<%=rootUrl%>/commodityCategory/findCommodityCategoryCheckedTree",
           onClick: function(node){ //点击商品类型节点查询事件
           var row=$('#currencyCommodityCategoryDivTree').tree("getSelected");
           var array=$('#currencyCommodityCategoryDivTree').tree('getChildren',node.target);
           var str="";
           for (var i = 0; i < array.length; i++) {
           	str+=array[i].id+",";
			}
           $('#currencyCommodityDatagrid').datagrid({
           	queryParams: {
           		ids: str+node.id
           		}
           });
       	}
   });
});
	//双击选择供应商退出dialog
	$('#currencyCommodityDatagrid').datagrid({
		onDblClickCell: function(rowIndex, rowData){
			 var row=$('#currencyCommodityDatagrid').datagrid("getSelections");
			 //商品类型
			 var categoryName=row[0].categoryName;
			 //商品名称
			 var commodityName=row[0].commodityName;
			 //销售价格
			 var unitPrice=row[0].sellingPrice;
			 //单位
			 var companyType=row[0].companyType;
			 //id
			 var commodityId=row[0].id;
			 addrows(categoryName,commodityName,unitPrice,companyType,commodityId);
			 $('#openCommodityDialog').dialog("close");
		}
	});
	function addrows(categoryName,commodityName,unitPrice,companyType,commodityId) {
	    $('#insertBilldatagrid').datagrid('appendRow', {
	    	commodityId:commodityId,
	    	categoryName: categoryName,
	    	commodityName:commodityName,
	    	companyType:companyType,
	    	commodityNumber: 1,
	    	unitPrice:unitPrice
	    });
	}
	</script>
</body>