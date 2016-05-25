<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>供应商列表</title>
	<body>
	<table id="supplierDatagrid" singleSelect="true" striped="true" fit="true" class="easyui-datagrid" url="<%=rootUrl%>/supplier/list.grid" border="false" fitColumns="true" rownumbers="false" pagination="true">
		<thead>
			<tr>
				<th field="supplierName" editor="text" width="80">供应商名称</th>
				<th field="phone" width="80">手机</th>
				<th field="telephone" width="80">电话</th>
				<th field="email" width="80">邮箱</th>
				<th field="name" width="80">联系人</th>
				<th field="remarks" width="100">备注</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	//双击选择供应商退出dialog
	$('#supplierDatagrid').datagrid({
		onDblClickCell: function(rowIndex, rowData){
			 var row=$('#supplierDatagrid').datagrid("getSelections");
			 var type=${type};
			 //类型是1为增加窗口
			 if(type==1){
				 $("#supplierNameAdd").val(row[0].supplierName);
				 $("#supplierIdAdd").val(row[0].id);
			 }else{
				 $("#supplierNameUpdate").val(row[0].supplierName);
				 $("#supplierIdUpdate").val(row[0].id);
			 }
			 $('#openCommodity').dialog("close");
		}
	});
	</script>
</body>