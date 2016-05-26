define(function(require, exports, module) {
    var $ = require('zepto');
    //require('module/fx');
    require('module/touch');

    function contactHandle() {
        var contentInfos = $('.content-info div img');
        var timer = null;
        contentInfos.on('tap', function() {
            timer = null;
            var parentElm = $(this).parent().parent();
            if (parentElm.hasClass('active')) {
                parentElm.removeClass('active')
            } else {
                parentElm.addClass('active pointer').siblings().removeClass('active');
                timer = setTimeout(function() {
                    parentElm.removeClass('pointer');
                }, 500)
            }
        });
    };

    function faultHandle() {
        var faultColorLis = $('.fault-color li');
        var faultInfoLis = $('.fault-info li');
        var faultTxt = $('.fault-txt1');
        var allText = '';
        var allPrice = 0;
        createMask();
        faultColorLis.on('tap', function() {
            $(this).addClass('active').siblings('li').removeClass('active');
            var innerText = getText(this);
            faultTxt.find('p span').text(innerText);
            $('.create-mask').remove();
            var machineTypeId = $('#machineTypeId').attr('value');
            //获取颜色id
            var colorId = $(this).attr('value');
            $(this).parent().find("input[id='pr_radio_" + colorId + "']").prop("checked", true);
            createInfoPrice("", 0);
            failureList(machineTypeId, colorId);

        });

        function getText(elem) {
            return $(elem).text();
        };

        function getPriceTag(elem) {
            return $(elem).find('p span').text();
        };

        function createInfoPrice(text, price) {
            faultTxt.find('.fault-Intro').text(text).append('<p>订单总价：' + price + '元</p>');
        };

        function createMask() {
            $('<div></div>').addClass('create-mask').appendTo('.fault-info');
        };

        // 浮动
        function posFixed() {
            var faultMain = $('.fault-main1');
            var iWidth = faultMain.width();
            var posTop = faultMain.offset().top;
            $('body').on('touchstart', function(event) {
                var startX = event.targetTouches[0].pageY;
                $(window).on('touchmove', function(event) {
                    var moveX = event.targetTouches[0].pageY;
                    var top = $(this).scrollTop();
                    if (startX - moveX > 0) {
                        if (posTop < top) {
                            $('.fault-color').addClass('set-margin');
                            faultMain.css({
                                position: 'fixed'
                            })
                        }
                    } else {
                        if ((posTop + 50) > top) {
                            $('.fault-color').removeClass('set-margin');
                            faultMain.css({
                                position: 'relative'
                            })
                        }
                    }
                });
            })
        };
        //posFixed();
    };




    function writeInfo() {
        var fillFormInputs = $('.fill-form input');
        var fillFormPs = $('.fill-form p');
        var fillFormBtn = $('.fill-form-btn button');
        fillFormBtn.on('tap', function() {
            var bSwith = false;
            fillFormInputs.each(function() {
                if ($(this).val() == '' && $(this).prop('id') != "code") {
                    $(this).parent('li').addClass('err');
                    bSwith = true;
                } else {
                    $(this).parent('li').removeClass('err');
                }
            })
            if (bSwith) {
                return false;
            }
            else {
                //提交订单信息
                submitOrderInformation();
            }
            // alert('填写全部完成及运行');
        });
    };

    function wrapHeightHandler(elem, posElem) {
        var element = $(elem);
        var posElement = $(posElem);
        var wrapHeight = element.height();
        var winHeight = $(window).height() - 220;
        if (wrapHeight < winHeight) {
            posElement.addClass('content-pos');
        } else {
            posElement.removeClass('content-pos');
        }
    };

    //获取故障信息
    function failureList(machineTypeId, colorId) {
        var faultInfoLis = $('.fault-info li');
        var faultTxt = $('.fault-txt1');
        var innerText = '';
        var pText = 0;
        var allText = '';
        var allPrice = 0;
        var Parameter = "machineTypeId=" + machineTypeId;
        Parameter += "&colorId=" + colorId;
        $.ajax({
            type: "POST",
            url: "findMachineBugs",
            data: Parameter + "",
            dataType : "json",
            success: function(msg) {
            	if(msg.code != '0'){
            		msg.obj = '';
            	}
                $('.fault-info ul').html(msg.body).find('li').on('tap', function() {
                    var pf_id = $(this).attr('value');
                    if (!$(this).find("input[id='pf_checkbox_" + pf_id + "']").prop("checked")) {
                        $(this).find("input[id='pf_checkbox_" + pf_id + "']").prop("checked", true);
                        $(this).addClass('active');
                        innerText = getText(this);
                        pText = getPriceTag(this);
                        allPrice += parseInt(pText);
                        allText += '、' + innerText;
                        if ($('.fault-Intro').text() == '' || $('.fault-Intro').text() == '订单总价：0元') {
                            var tempText = allText.split('、');
                            allText = tempText.pop();
                        }
                        if (allText != '') {
                            createInfoPrice(allText, allPrice);
                        }
                    } else {
                        $(this).find("input[id='pf_checkbox_" + pf_id + "']").prop("checked", false);
                        $(this).removeClass('active');
                        var delText = '、' + $(this).text();
                        var delPrice = getPriceTag(this);
                        allPrice = allPrice - delPrice;
                        allText = allText.replace(delText, '');
                        createInfoPrice(allText, allPrice);
                        if (allPrice == 0) {
                            createInfoPrice("", 0);
                        }
                    }

                    function getText(elem) {
                        return $(elem).text();
                    };

                    function getPriceTag(elem) {
                        return $(elem).find('p span').text();
                    };

                    function createInfoPrice(text, price) {
                        faultTxt.find('.fault-Intro').text(text).append('<p>订单总价：' + price + '元</p>');
                    };

                    function createMask() {
                        $('<div></div>').addClass('create-mask').appendTo('.fault-info');
                    };

                });

                wrapHeightHandler(".wrap", ".content-info");
            },
            error: function() {
                swal('加载错误！', '请重新选择颜色获取故障信息。');
            }
        });
    }

    //提交故障订单
    function submitPhone() {
        var orderid = $("#orderid").val();
        var pc_id = $("#pc_id").val();
        var pm_id = $("#pm_id").val();
        var pr_id = $("input[name='pr_radio']:checked").val();
        var str = "";
        $("input[name='pf_checkbox']:checked").each(function() {
            str += $(this).val() + ",";
        })
        if (str != "") {
            str = str.substring(0, str.length - 1);
        }
        var pf_id = str;

        if (pr_id == undefined) {

            swal({title: "", text: pc_id == 3 || pc_id == 4 ? '<span style="font-size:18px">请填选择尺寸</span>' : '<span style="font-size:18px">请填选择颜色！</span>', html: true });
            return false;
        }
        if (pf_id == "") {
            swal({ title: "", text: "<span style=\"font-size:18px\">请填选择故障！</span>", html: true });
            return false;
        }

        window.location.href = "personInfo?machineBugIds=" + encodeURIComponent(pf_id);
    }

    var checkSubmitFlg = false; //防止重复提交
    //提交订单信息
    function submitOrderInformation() {
        var re = /^[0-9]+.?[0-9]*$/;
        var orderid = $("#orderid");
        var pc_id = $("#pc_id");
        var pm_id = $("#pm_id");
        var pr_id = $("#pr_id");
        var pf_id = $("#machineBugIds");
        var name = $("#name");
        var path = $("#address");
        var phone = $("#phone");
        var productFailureDescribe = $("#content");
        // var isWarranty = $('input[name="isWarranty"]:checked').val()
        var code = $("#code");

        var ProvinceId = $("#ProvinceId");
        var CityId = $("#CityId");
        var AreaId = $("#AreaId");
        var isDoor = $('input[name="isDoor"]:checked').val();
        var doorDate = $("#DoorDate");

        if (phone.val() != "") {
            var rephone = /^[1][3578]\d{9}$/;
            if (!rephone.test(phone.val())) {
                swal({ title: "", text: "<span style=\"font-size:18px\">手机号格式不正确！</span>", html: true });
                return false;
            }
        }

        if (code.val() != "") {
            if (!re.test(code.val())) {
                swal({ title: "", text: "<span style=\"font-size:18px\">验证码错误！</span>", html: true });
                return false;
            }
        } else {
            swal({ title: "", text: "<span style=\"font-size:18px\">请输入验证码！</span>", html: true });
            return false;
        }
        //防止重复提交
        if (checkSubmitFlg) {
            return false;
        } else {
            checkSubmitFlg = true;
        }
        //维修条款不选中不能提交
        if (document.getElementById("clause").checked == false) {
            return false;
        }
        var Parameter = "";
        Parameter += "&orderId=" + orderid.val() + "";
        Parameter += "&pc_id=" + pc_id.val() + "";
        Parameter += "&pm_id=" + pm_id.val() + "";
        Parameter += "&pr_id=" + pr_id.val() + "";
        Parameter += "&machineBugIds=" + pf_id.val() + "";
        Parameter += "&realName=" + encodeURIComponent(name.val()) + "";
        Parameter += "&address=" + encodeURIComponent(path.val()) + "";
        Parameter += "&mobile=" + phone.val() + "";
        Parameter += "&bugDetail=" + encodeURIComponent(productFailureDescribe.val()) + "";
        Parameter += "&code=" + code.val() + "";

        Parameter += "&" + new Date().getTime(); //防止缓存
        $.ajax({
            type: "POST",
            url: "saveOrderInfo",
            data: Parameter + "",
            dataType:"json",
            success: function(dataObj) {
            checkSubmitFlg = false; //防止重复提交
	            if (dataObj.code == "0") {
	                code.addClass('here');
	                swal({ title: "", text: "<span style=\"font-size:18px\">提交成功！</span>", html: true });
	                return false;
	            } else {
	                swal({ title: "", text: "<span style=\"font-size:18px\">"+ dataObj.msg +"</span>", html: true });
	            }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                swal({ title: "", text: "<span style=\"font-size:18px\">提交失败请重新提交。</span>", html: true });
                checkSubmitFlg = false;  //防止重复提交
            }
        });

    }


    // 联系方式
    exports.contact = function(elem, posElem) {
        wrapHeightHandler(elem, posElem);
        contactHandle();
    };

    // 提交故障
    exports.fault = function() {
        faultHandle();
    };
    
    exports.submitPhone = function() {
    	submitPhone();
    };

    // 填写个人信息
    exports.writeInfo = function() {
        writeInfo();
    };

    // 订单查看
    exports.checkOrder = function() {
        // checkOrderHandle();
    };
});
