define(function(require, exports, module) {
	var $ = require('zepto');

	var win = $(window);

	function calcScale() {
		var resizeEvt = 'onorientationchange' in window ? 'orientationchange' : 'resize';
		var resizeFun = function() {
			var winWidth = win.width();
			if (!winWidth) return;
			if (winWidth > 640) {
				$('html').css('fontSize', '100px');
			} else {
				$('html').css('fontSize', 100 * (winWidth / 640) + 'px');
			}
			$('body').show();
		};
		window.addEventListener('load', resizeFun, false);
		window.addEventListener(resizeEvt, resizeFun, false);
	};
	exports.calcScale = calcScale;
});