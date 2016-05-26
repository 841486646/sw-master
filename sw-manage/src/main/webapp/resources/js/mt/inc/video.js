
//加载数据列表
function getVideoList(a){$.ajax({type:"POST",url:"Handler/Video.ashx",data:"action\x3dgetVideoList"+("\x26page\x3d"+a+"")+"",success:function(b){dataObj=eval("("+b+")");"true"==dataObj.result&&($("#divhtml ul").append(dataObj.html),a+1<=dataObj.totalpage?$("#divmore").each(function(){$(this).click(function(){getVideoList(a+1)})}):$("#divmore").hide())},error:function(){}})};