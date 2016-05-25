<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>客户资料列表</title>
	<body>
	<table  singleSelect="true" fit="true" class="easyui-datagrid" url="<%=rootUrl%>/supplier/list.grid" border="false" fitColumns="true" rownumbers="true" pagination="true">
		<thead>
			<tr>
				<th field="companyName" width="80">公司名称</th>
				<th field="customerName" width="80">客户姓名</th>
				<th field="phone" width="80">手机</th>
				<th field="telephone" width="80">电话</th>
				<th field="email" width="80">邮箱</th>
				<th field="name" width="80">职务</th>
				<th field="position" width="100">备注</th>
				<th field="<fmt:formatDate value="createTime" pattern="yyymmdd" /> " width="100">录入时间</th>
			</tr>
		</thead>
	</table>
</body>