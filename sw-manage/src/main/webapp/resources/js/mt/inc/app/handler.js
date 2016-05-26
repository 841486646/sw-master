define(function(require, exports, module) {
	var $ = require('zepto');
	require('module/fx');
	require('module/touch');

	var win = $(window);

	function focusHanlde() {
	    var focusMain = $('.focus-content');
	    var ul = focusMain.find('ul');
	    var ulLis = ul.find('li');
	    var ol = focusMain.find('ol');
	    var olLis;
	    var iNow1 = 0;
	    var iNow2 = 0;
	    var time;

	    ul.css('width', ulLis.length * 6.4 + 'rem');

	    for (var i = 0; i < ulLis.length; i++) {
	        var olLi = $('<li></li>');
	        if (i == 0) {
	            olLi.addClass('active');
	        }
	        ol.append(olLi);
	    }
	    olLis = ol.find('li');
	    ol.css({
	        width: olLis.length * 0.24 + 'rem',
	        marginLeft: -(olLis.length * 0.24) / 2 + 'rem'
	    });

	    focusMain.on('swipeLeft', swipeLeftHandle);

	    function swipeLeftHandle() {
	        if (iNow1 == ulLis.length - 1) {
	            iNow1 = 0;
	            ulLis.first().css({
	                position: 'relative',
	                left: ulLis.length * 6.4 + 'rem'
	            })
	        } else {
	            iNow1++;
	        }
	        iNow2++;
	        setIndex()
	        ul.animate({
	            left: -iNow2 * 6.4 + 'rem'
	        }, function() {
	            if (iNow1 == 0) {
	                iNow2 = 0;
	                ulLis.first().css('position', 'static');
	                ul.css('left', 0);
	            }
	        })
	    };

	    time = setInterval(swipeLeftHandle, 3000);

	    // focusMain.on('touchstart', function(event) {
	    // 	event.preventDefault();
	    // 	clearInterval(time);
	    // });

	    // focusMain.on('touchend', function(event) {
	    // 	event.preventDefault();
	    // 	time = setInterval(swipeLeftHandle, 5000);
	    // });

	    focusMain.on('swipeRight', function() {
	        if (iNow1 == 0) {
	            iNow1 = ulLis.length - 1;
	            iNow2 = ulLis.length - 1;
	            ul.css('left', -ulLis.length * 6.4 + 'rem');
	            ulLis.first().css({
	                position: 'relative',
	                left: ulLis.length * 6.4 + 'rem'
	            });
	            setIndex();
	            ul.animate({
	                left: -iNow2 * 6.4 + 'rem'
	            }, function() {
	                ulLis.first().css('position', 'static');
	            });
	        } else {
	            iNow1--;
	            iNow2--;
	            setIndex();
	            var left = parseFloat(ul.css('left'));
	            ul.animate({
	                left: left + 6.4 + 'rem'
	            });
	        }
	    });

	    function setIndex() {
	        olLis.eq(iNow1).addClass('active').siblings('li').removeClass('active');
	    };
	};

	function setUlWidth(ul) {
		var ulElm = $(ul);
		var lis = ulElm.find('li');

		ulElm.css('width', lis.length * 3.15 + 'rem');
	};

	function mediaTab() {
		var elem = $('.media');
		var elemUl = elem.find('.media-tab li');
		var elemDiv = elem.find('div');

		elemUl.on('tap', function() {
			var index = $(this).index();
			$(this).addClass('active').siblings().removeClass('active');
			elemDiv.eq(index).show().siblings('div').hide();
		});
	};
	

	exports.focusHanlde = focusHanlde;
	exports.setUl = setUlWidth;
	exports.mediaTab = mediaTab;
});