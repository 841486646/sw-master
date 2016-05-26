<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="../inc/head.jsp"%>
    <title>订单管理</title>
<!-- 工具栏 -->
    <div id="toolbarOrder">
    	<form name="searchOrderForm" id="searchOrderForm">
        <div style="padding: 5px;">
        	<span>姓名：</span>
            <input id="seaRealName" name="realName" class="easyui-textbox" style="width: 150px;"/>
        	<span>手机号：</span>
            <input id="seaMobile" name="mobile" class="easyui-textbox" style="width: 150px;"/>
            <span>开始时间：</span>
            <input id="startTime" name="startTime" class="easyui-datebox" data-options="editable:false" style="width: 120px;"/>
            <span>截止时间：</span>
            <input id="endTime" name="endTime" class="easyui-datebox" data-options="editable:false" style="width: 120px;"/>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchOrder();" data-options="iconCls:'icon-search'">查询</a>
        </div>
        </form>
    </div>
    
    <!-- 表格 -->
    <table id="tblOrder" style="width: 98%;" class="easyui-datagrid"></table>

    <script type="text/javascript">
        $(function(){
            $("#tblOrder").datagrid({
                url:"${rootUrl}/mtOrder/list.grid",
                columns:[[
                    {field:'id',title:'ID',width:20},
                    {field:'realName',title:'姓名',width:30},
                    {field:'mobile',title:'手机号',width:40},
                    {field:'totalPrice',title:'总价',width:20},
                    {field:'bugs',title:'故障',width:80},
                    {field:'address',title:'地址',width:80},
                    {field:'bugDetail',title:'故障详情',width:100},
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
                toolbar:'#toolbarOrder'
            });
        });
        
      	//根据条件查询交易日志
        function searchOrder(){
            $("#tblOrder").datagrid('load', {
            	realName:$('#seaRealName').textbox('getValue'),
            	mobile:$('#seaMobile').textbox('getValue'),
            	startTime:$('#startTime').datebox('getValue'),
                endTime:$('#endTime').datebox('getValue')
            });
        }
    </script>
