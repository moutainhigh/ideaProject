define(function(require,exports,module) {
	
	//加载CSS
	//<!-- Bootstrap Core CSS -->
	require("lib/bootstrap/css/bootstrap.min.css");
	//<!-- MetisMenu CSS -->
	require("lib/sb_admin2/css/metisMenu.min.css");
	//<!-- Custom CSS -->
	require("lib/sb_admin2/css/sb-admin-2.css");
	//<!-- layer CSS -->
	require("lib/layer/css/layer.css");
	//ztree css
	require("lib/ztree/metro.css");
	//<!-- Custom Fonts -->
	require("lib/sb_admin2/css/font-awesome.min.css");
    require("lib/sb_admin2/fonts/iconfont.css");
	//<!-- Bootstrap-table CSS -->
	require("lib/bootstrap-table/css/bootstrap-table.min.css");
	require("lib/bootstrap-table/css/bootstrap-editable.css");
	require("lib/bootstrap-table/css/examples.css");
	require("lib/bootstrap-table/css/jumpto.css");
	//<!-- datepicker -->
	require("lib/datepicker/css/bootstrap-datetimepicker.css");

	
	layer.msg('欢迎进入', {icon: 1});

	module.exports = {
		
		//index.html页面初始化
		indexInit : function(){
			
			
			require("bootstrap");
			require("lib/bootstrap-validator/js/bootstrapValidator.js");
			
			require("metisMenu");
			require("sbadmin2");
			
			require("ztree");
			
			require("lib/bootstrap-treeview/bootstrap-treeview.js");
			
			//初化化bootstrap table
			require("lib/bootstrap-table/bootstrap-table.js");
			require("lib/bootstrap-table/bootstrap-table-zh-CN.js");
			require("lib/bootstrap-table/bootstrap-table-export.js");
			require("lib/bootstrap-table/tableExport.js");
			require("lib/bootstrap-table/bootstrap-table-editable.js");
			require("lib/bootstrap-table/bootstrap-editable.js");
			require("lib/bootstrap-table/bootstrap-table-jumpto.js");
			require("lib/bootstrap-table/ga.js");
			
			//加载base
			require("base");

            var menutop = require("menuTop");
            menutop.loadmenuTop(_ctx+"/v.do?u=base/topurl");
			//加载menu
			var menu = require("menu");
            menu.loadNews(_ctx+"/v.do?u=base/rightTop");
			menu.initapp();
            if (window.localStorage) {//先判断浏览器是否支持
                //假如支持的话，就判断这里面是否有值，有值的话就不加载了，比如在登陆的时候刷新，没值的时候就加载，比如登陆的时候
            	if( window.localStorage.menu == undefined){
                    menu.loadmenu(_ctx + "/menu/appmenu/loadleft?appname=" + HRY_menukey);
				}
            }else {//浏览器不支持就直接加载
                menu.loadmenu(_ctx + "/menu/appmenu/loadleft?appname=" + HRY_menukey);
			}
			if (HRY_menukey != "admin") {
                $("#top_app li.active").removeClass("active");
				$("a[appname='"+HRY_menukey+"']").parent("li").addClass("active");
			}


            //设置左侧展开内容显示
            var apptext = $("#top_app li.active a").text().trim();
            $("#shrinkMenu_span").text(apptext);


			//鉴权
			permissions();

            $("#page-wrapper").on("click",".page-header", function(o) {
                $("#page-wrapper form[id='table_query_form']").toggle();
            });
			
			$(document).ready(function(){
				function autoWH(){
					var bdHeight=$(window).height();
					$("#platform_menu").height(bdHeight-120);
					$("#page-wrapper .centerRowBg").height(bdHeight-148);
					$("#page-wrapper .centerRowBg.centerRowBg_write").height(bdHeight-78);
					   
				}
				autoWH();
				$(window).resize(function () {
					autoWH();
			    });
				
		    });

            if (window.localStorage && window.localStorage.menu != undefined) {
                // 展开顶部菜单
                var storage = window.localStorage;
                var menu = storage.getItem("menu");
                if (menu) {
                    var info = JSON.parse(menu);
                    $("a[appname='" + info.topname + "']").click();
                }
            }
		}
	
	}
	

});

