//JEasyUi Validatebox扩展.
//增加Validatebox检查规则
$.extend($.fn.validatebox.defaults.rules,{
    selectValueRequired: {
        validator:function(value,param){             
            if(value=="" || value.indexOf('请选择')>=0){   
               return false;  
            }else{  
               return true;  
            }    
       },   
       message:'该下拉框为必选项' 
    },
    loginName:{
        validator:function(value,param){
            if(null==value || ''==value || typeof(value)=='undefined'){
                return true;
            }
            return 0==value.replace(/[a-zA-Z]{1}[0-9a-zA-Z_]{2,19}/,"").length;
        },
        message:'请输入英文字母、数字及下划线(英文字母开头，3-20个字符).'
    },
    password:{
        validator:function(value,param){
            if(null==value || ''==value || typeof(value)=='undefined'){
                return true;
            }
            return 0==value.replace(/[\w]{6,20}/,"").length;
        },
        message:'请输入英文字母、数字及符号(6-20个字符).'
    },
    url:{
        validator:function(value,param){
            if(null==value || ''==value || typeof(value)=='undefined'){
                return true;
            }
            return 0==value.replace(/^(http:\/\/|https:\/\/).+/i,"").length;
        },
        message:'请输入URL(以http://或https://开头).'
    },
    money:{
        validator:function(value,param){
            if(null==value || ''==value || typeof(value)=='undefined'){
                return true;
            }
            return 0==value.replace(/^(0|[1-9]+[0-9]*)(([\.]\d{1,2}){0,1})$/,"").length;
        },
        message:'请输入金额.'
    },
    unsignedint:{
        validator: function(value,param){
            if(null==value || ''==value || typeof(value)=='undefined'){
                return true;
            }
            return 0==value.replace(/[1-9]{1}[0-9]+/,"").length;
        },
        message:'请输入正整数.'
    },
    phone:{
        validator:function(value,param){
            if (null==value || ''==value || typeof(value)=="undefined"){
                return true;
            }
            var pArr=value.split(',');
            var pattenPhone=/^(\d{3,4})?\d{7,8}$/;
            var pattenMphone=/^(130|131|132|133|134|135|136|137|138|139|150|151|152|153|155|156|157|158|159|180|181|182|183|184|185|186|187|188|189|178|147|176|145|177|170)\d{8}$/;
            for(var i=0;i<pArr.length;i++){
                if (!pattenPhone.test(pArr[i]) && !pattenMphone.test(pArr[i])){
                    return false;
                }
            }
            return true;
        },
        message:'请输入电话或手机号码(全数字,不允许出现+ - #等符号),多个用[,]分隔.'
    },
    mphone:{
        validator:function(value,param){
            if(null==value || ''==value || typeof(value)=="undefined"){
                return true;
            }
            var patten=/^(130|131|132|133|134|135|136|137|138|139|150|151|152|153|155|156|157|158|159|180|181|182|183|184|185|186|187|188|189|178|147|176|145|177|170)\d{8}$/;
            if(patten.test(value)){
                return true;
            }
            return false;
        },
        message:'请输入正确的手机号码.'
    },
    equalTo:{
        validator:function(value,param){
            return value==$(param[0]).val();
        },
        message:'两次输入不一致.'
    },
    CHS:{
        validator:function (value, param) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message:'请输入汉字'
    },
    between:{
        validator:function (value, param) {
            //仅判断UTF-8编码
            var total=0;
            if(null==value || ''==value || typeof(value)=="undefined"){
                return true;
            }
            var charCode;
            for(var i=0;i<value.length;i++){
                charCode=value.charCodeAt(i);
                if(charCode<=0x007f){
                    total+=1;
                }else if(charCode<=0x07ff){
                    total+=2;
                }else if(charCode<=0xffff){
                    total+=3;
                }else{
                    total+=4;
                }
            }
            if(total<param[0] || total>param[1]){
                $.fn.validatebox.defaults.rules.between.message='输入内容长度要大于'+param[0]+'个半角英文字符'
                    +'或'+Math.floor(param[0]/3)+'个中文字符,'
                    +'同时小于'+param[1]+'个半角英文字符'
                    +'或'+Math.floor(param[1]/3)+'个中文字符';
                return false;
            }else{
                return true;
            }
        },
        message:''
    },
    betweenByChar:{
        validator:function (value, param) {
            if(null==value || ''==value || typeof(value)=="undefined"){
                return true;
            }
            if(value.length<param[0] || value.length>param[1]){
                $.fn.validatebox.defaults.rules.between.message='输入内容长度要大于'+param[0]+'个字符'
                    +'同时小于'+param[1]+'个字符';
                return false;
            }else{
                return true;
            }
        },
        message:''
    }
});