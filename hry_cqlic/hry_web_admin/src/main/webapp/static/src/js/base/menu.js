define(function(require,exports,module){
	
	var base = require("base");
	/**
	 * 异步加载center面，防止center计算量太大影响整体框架加载
	 */
    if (window.localStorage == undefined || window.localStorage.menu == undefined) {
        base.loadUrl(_ctx + "/customer/appcustomer/toCustomer.do?isReal=0");
    }
	
	module.exports = {
		initapp : function(){
			var loadmenu = this.loadmenu;
			// 顶部菜单点击事件
			$("#top_app").on("click","a",function(){
				// 清除状态
                $(this).parent("li").siblings().removeClass("active");
                // 获取appname属性值
				var appname = $(this).attr("appname");
                $(this).parent("li").addClass("active");
				loadmenu(_ctx+"/menu/appmenu/loadleft?appname="+appname);
			});
		},
		/**
		 * 动态
		 */
		loadmenu : function(url){

			//加载对应的页面
			$.ajax({
				type : "get",
				url :url,
				cache : false,
				dataType : "html",
				success : function(data) {
					if(data.indexOf("402880e3604021992ac3a50005login")==-1) {
                        //清空主体
                        $("#platform_menu").empty();
                        //进行渲染
                        $("#platform_menu").html(data);

                        /**
                         * 监听菜单点击事件
                         * 2016-12-28
                         */
                        $("#platform_menu").find("li a").on("click", function (o) {
                            var src = $(this).attr("src");
                            var mkey = $(this).attr("mkey");
                            var pkey = $(this).attr("pkey");
                            var ppkey = $(this).attr("ppkey");
                            var topname = $("#top_app li.active a").attr("appname");
                            if (src != undefined) {
                                if(window.localStorage != undefined){
                                    //将当前菜单路径存放到localStorage中
                                    var storage = window.localStorage;
                                    var menuObj = {
                                        mkey: mkey,
                                        pkey: pkey,
                                        ppkey: ppkey,
                                        topname: topname
                                    };
                                    storage.setItem("menu", JSON.stringify(menuObj));
                                }
                                base.loadUrl(src);
                            }
                        });
                        //菜单初始化
                        $('#side-menu').metisMenu();

                        var apptext = $("#top_app li.active a").text().trim();
                        //设置展开内容
                        $("#shrinkMenu_span").text(apptext);

                        // 菜单跳转
                        if(window.localStorage && window.localStorage.menu != undefined){
                            var storage = window.localStorage;
                            var menu = storage.getItem("menu");
                            if (menu) {
                                var info = JSON.parse(menu);
                                // 一级展开
                                if (info.ppkey) {
                                    $("[mkey='" + info.ppkey + "']").click();
                                }
                                // 二级展开
                                if (info.pkey) {
                                    $("[mkey='" + info.pkey + "']").click();
                                }
                                // 三级展开
                                if (info.mkey) {
                                    $("[mkey='" + info.mkey + "']").click();
                                }
                            }
                        }
                    }

				},
				error : function(e) {
					$("#platform_menu").html("<div class='row'><h1>此路径不存在："+url.substring(url.indexOf("u=")+2)+"</h1></div>");
				}
			});
		},
		//静态菜单
		loadstaticmenu : function(){
			/**
			 * 监听菜单点击事件
			 * 2016-12-28
			 */
			$("#platform_menu").find("li a").on("click", function(o) {
				var src = $(this).attr("src");
                var mkey = $(this).attr("mkey");
                var pkey = $(this).attr("pkey");
                var ppkey = $(this).attr("ppkey");
				if (src != undefined) {
                    if(window.localStorage != undefined){
                        //将当前菜单路径存放到localStorage中
                        var storage = window.localStorage;
                        var menuObj = {
                            mkey: mkey,
                            pkey: pkey,
                            ppkey: ppkey
                        };
                        storage.setItem("menu", JSON.stringify(menuObj));
                    }
					base.loadUrl(src);
				}
			});
			
			//菜单初始化
			$('#side-menu').metisMenu()
		
		},/**
         * 动态
         */
        loadNews : function(url){
            //清空主体
            $("#newsMenu").empty();
            //加载对应的页面
            $.ajax({
                type : "get",
                url :url,
                cache : false,
                dataType : "html",
                success : function(data) {
                    //进行渲染
                    $("#newsMenu").html(data);
                    //菜单初始化
                    $('#newsMenu').metisMenu()
                },
                error : function(e) {
                    $("#newsMenu").html("");
                }
            });

        }
	}
	
	
	
})