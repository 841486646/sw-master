//获取订单
function orderList(){if(""==document.getElementById("phone").value)return swal({title:"",text:'\x3cspan style\x3d"font-size:18px"\x3e\u8bf7\u586b\u5165\u60a8\u7684\u624b\u673a\u53f7\u7801\uff01\x3c/span\x3e',html:!0}),!1;if(!/^[1][3578]\d{9}$/.test(document.getElementById("phone").value))return swal({title:"",text:'\x3cspan style\x3d"font-size:18px"\x3e\u624b\u673a\u53f7\u683c\u5f0f\u4e0d\u6b63\u786e\uff01\x3c/span\x3e',html:!0}),!1;var a;a=""+("\x26phone\x3d"+document.getElementById("phone").value+"");a+="\x26"+(new Date).getTime();
$.ajax({
	type:"POST",
	url:"findOrder",
	data:"mobile="+a+"",
	dataType:"json",
	success:function(obj){
		"0"!=obj.code?($("#warrantyPrompt1").html("您的保修服务期限。"),$("#warrantyPrompt2").html("从维修完成之日起，我们提供维修故障点非人为损坏情况下，6个月的免费保修服务。"),$("#divhtml").html(obj.body)):($("#warrantyPrompt1").html("很抱歉，我们未在记录中找到您的订单信息。"),$("#warrantyPrompt2").html("请检查输入的手机号，然后再试"),$("#divhtml").html(""))},error:function(){}})};