//在页面初始化的时候禁止验证
//防止页面初始化后需验证的框全是红色的
function disableValidateWhenInit(formId){
    $("#"+formId).form('disableValidation');
    $(".easyui-validatebox").addClass("textbox");
}
//Form验证被禁止使用后在提交前恢复验证
function enableValidateWhenSubmit(formId){
    $("#"+formId).form('enableValidation');
}
//Form验证被禁止使用后在输入框获得焦点时恢复验证并可在焦点离开该输入框时进行验证
function enableValidateWhenInputfocus(inputId){
    $("#"+inputId)
    .focus(function(){
        $(this).validatebox('enableValidation');
    })
    .blur(function(){
        $(this).validatebox('validate');
    });
}
//字符串为空验证
function isEmptyStr(s){
    if(null==s || ''==s || typeof(s)=='undefined'){
        return true;
    }else{
        return false;
    }
}
//计算验证用密码（登录或修改密码时用于验证原密码）
function calPwd(pwd){
    if(isEmptyStr(pwd)){
        return '';
    }
    return hex_md5(hex_md5(pwd).toUpperCase()).toUpperCase();
}
//form重填
function resetForm(formId){
  $("#"+formId).form('reset');
}
//显示加载页面
function showLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({"z-index":"9999",display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").css({"z-index":"99999",display:"block",left:($(document.body).outerWidth(true)-190)/2,top:($(window).height()-45)/2}).appendTo("body");
}
//隐藏加载页面
function hideLoading(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}
//去除html标签
function delHtmlTag(str){
    return str.replace(/<[^>]+>/g,"");
}
//对Date的扩展，将 Date转化为指定格式的String
//月(M)、日(d)、小时(H)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//format(new Date(),"yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//format(new Date(),"yyyy-M-d H:m:s.S")      ==> 2006-7-2 8:9:4.18 
function fmtDate(dtData, fmt){
    if(null==dtData || ''==dtData || typeof(dtData)=='undefined'){
        return '';
    }
    var dt=new Date(new Number(dtData));
    if (/(y+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1,(dt.getFullYear()+"").substr(4-RegExp.$1.length));
    }
    if (/(M+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(new Number(dt.getMonth())+1):(("00"+(new Number(dt.getMonth())+1)).substr((""+ (new Number(dt.getMonth())+1)).length)));
    }
    if (/(d+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(dt.getDate()):(("00"+dt.getDate()).substr((""+ dt.getDate()).length)));
    }
    if (/(H+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(dt.getHours()):(("00"+dt.getHours()).substr((""+ dt.getHours()).length)));
    }
    if (/(m+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(dt.getMinutes()):(("00"+dt.getMinutes()).substr((""+ dt.getMinutes()).length)));
    }
    if (/(s+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(dt.getSeconds()):(("00"+dt.getSeconds()).substr((""+ dt.getSeconds()).length)));
    }
    if (/(q+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(Math.floor((this.getMonth() + 3) / 3)):(("00"+Math.floor((this.getMonth() + 3) / 3)).substr((""+ Math.floor((this.getMonth() + 3) / 3)).length)));
    }
    if (/(S)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(dt.getMilliseconds()):(("00"+dt.getMilliseconds()).substr((""+ dt.getMilliseconds()).length)));
    }
    return fmt;
}