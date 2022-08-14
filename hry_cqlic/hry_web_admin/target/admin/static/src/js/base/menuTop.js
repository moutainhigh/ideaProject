define(function(require,exports,module){
	var menu = require("menu");
	module.exports = {
		/**
		 * 动态
		 */
		loadmenuTop : function(url){
			//清空主体
			$("#platform_menuTop").empty();
			//加载对应的页面
			$.ajax({
				type : "get",
				url :url,
				cache : false,
				dataType : "html",
				async : false,
				success : function(data) {
					//进行渲染
					$("#platform_menuTop").html(data);
					/**
					 * 监听菜单点击事件
					 */
					$("#side-menuTop").find("li a").on("click", function(o) {
						var src = $(this).attr("src");
						if (src != undefined) {
							menu.loadmenu(src);
						}
					});
					//鉴权
					//toppermissions();
					try {
						//菜单初始化
						$('#side-menuTop').metisMenu()
					} catch (e) {

					}
					//鉴权
					permissions();
				},
				error : function(e) {
					$("#platform_menuTop").html("<div class='row'><h1>功能正在完善中，敬请期待......</h1></div>");
				}
			});
		},
		//静态菜单
		loadstaticmenu : function(){
			/**
			 * 监听菜单点击事件
			 * 2016-12-28
			 */
			$("#platform_menuTop").find("li a").on("click", function(o) {
				var src = $(this).attr("src");
				if (src != undefined) {
					menu.loadmenu(src);
				}
			});
			//菜单初始化
			$('#side-menuTop').metisMenu()
		}
	}
	
	
	
})