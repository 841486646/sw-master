<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>客户资料列表</title>
	<body>
	<table  singleSelect="true" fit="true" class="easyui-datagrid" url="<%=rootUrl%>/commodityBill/commodityBillList.grid?billId=${biiId}" border="false" fitColumns="true" rownumbers="true" pagination="true">
		<thead>
			<tr>
				<th field="categoryName" editor="text" width="80">商品类别</th>
				<th field="commodityName" width="80">商品名称</th>
				<th field="commodityNumber" width="80">数量</th>
				<th field="unitPrice" width="80">单价</th>
				<th field="companyType"  formatter="managerstr" width="80">单位</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function managerstr(value,rowData,rowIndex) {
		 <c:forEach items="${companyTypes }" var="companyType">
         	if("${companyType}"==value){
             	return "${companyType.info}";
         	}
 		</c:forEach>
    }
	</script>
</body>
