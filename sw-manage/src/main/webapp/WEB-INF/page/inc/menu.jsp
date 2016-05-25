<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		var data = $.parseJSON('${menus}');
		$.each(data, function(k, v) {
			var childrenMenus = "";
			var node = this;
			$.each(this.children,function(index,value){
				var url;
				var tabOpts;
				if (this.url) {
					if (this.url.indexOf('/') == 0) {
						url = CONTEXT_PATH+this.url;
					} else {
						url = this.url;
					}
					tabOpts = '{"url":"'+url+'","title":"'+this.name+'","id":"'+this.id+'"}';
				}
				childrenMenus += "<li class='menu_item_leaf' tabid='"+this.id+"' onclick='addTab("+tabOpts+")'>"+this.name+"</li>";
			});
			var tmenu = "<div class='menu_group'>"+
							"<button class='menu_btn menu_parent' id='"+this.id+"' onclick='operMenu(this)'>"+
								"<div class='left'>"+this.name+"</div>"+
							"</button>"+
							"<ul class='menu_hide'>"+
								childrenMenus+
							"</ul>"+
						"</div>";
			$("#menu").append(tmenu);
		});
	});
	
	function operMenu(thisMenu){
		var menu_style = $(thisMenu).next().css("display");
		$("ul.menu_hide").slideUp(10);
        if(menu_style == "block"){
            $(thisMenu).next().slideUp(180);
            $(thisMenu).children().next().text("+");
        }else{
            $(thisMenu).next().slideDown(180);
            $(thisMenu).children().next().text("-");
        }
	}
</script>
<div class="easyui-accordion" data-options="fit:true,border:false">
	<div style="padding: 5px;" id="menu">
	</div>
</div>