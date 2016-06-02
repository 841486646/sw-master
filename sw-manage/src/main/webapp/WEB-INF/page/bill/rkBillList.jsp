<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>入库商品查询</title>
    <!-- 表格 -->
    <table id="rkTblBill" style="width: 98%;" class="easyui-datagrid"></table>
    <div id="rkSerchBillDialog"></div>
    <script type="text/javascript">
        $(function(){
            $("#rkTblBill").datagrid({
                url:"<%=rootUrl%>/bill/commodityStorageList.grid",
                columns:[[
                    {field:'orderNumber',title:'入库单号',width:50},
                    {field:'totalPrice',title:'总价格',width:50},
                    {
                    	field:'createUserId',title:'经手人',width:80,
                    	formatter:function(value,data,index){
                    		<c:forEach items="${usersList}" var="lists">
	                            if('${lists.id}'==value){
	                                return '${lists.nickName}';
	                            }
	                       </c:forEach>
                        }
                    },
                    {
                        field:'type',
                        title:'入库类型',
                        width:50,
                        formatter:function(value,data,index){
                            <c:forEach items="${billRKTypes }" var="billRKType">
	                            if("${billRKType}"==value){
	                                return "${billRKType.info}";
	                            }
                        	</c:forEach>
                        }
                    },
                    {
                        field:'state',
                        title:'入库状态',
                        width:50,
                        formatter:function(value,data,index){
                            <c:forEach items="${billRKStates }" var="billRKState">
	                            if("${billRKState}"==value){
	                                return "${billRKState.info}";
	                            }
                        	</c:forEach>
                        }
                    },
                    {
                        field:'createTime',
                        title:'申请时间',
                        width:50,
                        formatter:function(value,data,index){
                            return fmtDate(value,"yyyy-MM-dd");
                        }
                    },{
                        field:'_operate',
                        title:'明细',
                        width:50,
                        formatter:function(value,data,index){
                            return "<img onclick='rk_search()' src='<%=rootUrl %>/resources/js/easyui/themes/icons/vcard_add.png' title='明细' style='cursor:pointer'/>";
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
            });
        });
        //明细信息
        function rk_search(){
        	var row=$('#rkTblBill').datagrid("getSelected");
        	console.log(row);
            if(row){
            	$('#rkSerchBillDialog').dialog({
                    title: '入库单号明细',
                    width: 900,
                    height: 600,
                    closed: false,
                    cache: false,
                    href: '<%=rootUrl%>/currency/toRkCommodityBill?biiId='+row.id,
                    modal: true
                });
            }else{
                $.messager.show({
                    title:'提示',
                    msg:'请选择一条记录.'
                });
            }
        }
    </script>
