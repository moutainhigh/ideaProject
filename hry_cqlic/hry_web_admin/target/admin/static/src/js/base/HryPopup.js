/**
 * 弹窗组件
 */
define(function(require, exports, module) {
	
	this._table = require("js/base/table");
	
	module.exports = {
			/**
			 * 初始化窗口
			 * title  窗口名称
			 * btnid  按钮id
			 * tablefunction   弹窗中的表格初始化方法
			 */
			init : function(title,btnid,tablefunction){
				
				var divid = "popup_"+btnid;
				if($("#"+divid).html()){
                    $("#"+divid).remove();
				}
				$("#page-wrapper").append("<div class=\"row hide\"  id=\""+divid+"\"><div class=\"col-md-12\"></div></div>");
				
				$("#"+btnid).on("click",function(){
					var tableHtml = "<table   "+
							 	    "       data-show-refresh=\"true\"   "+
							 	    "       data-show-columns=\"false\"   "+
							 	    "       data-show-export=\"false\"    "+
							 	    "       data-search=\"true\"  "+
							 	    "       data-detail-view=\"false\"  "+
							 	    "       data-minimum-count-columns=\"2\"  "+
							 	    "       data-pagination=\"true\"  "+
							 	    "       data-id-field=\"id\"  "+
							 	    "       data-page-list=\"[10, 25, 50, 100, ALL]\"  "+
							 	    "       data-show-footer=\"false\"    "+
							 	    "       data-side-pagination=\"server\"  "+
							 	    "       >  "+
							 	    "</table>";
					
					layer.open({
						  type: 1,
						  title :title,
						  skin: 'layui-layer-rim', //加上边框
						  area: ['70%', '70%'], //宽高,
						  content: $("#"+divid),
						  closeBtn : 2,
						  shadeClose : false,
						  success: function(layero, index){
							  //弹出成功移除隐藏
							    $("#"+divid).removeClass("hide")
							    $("#"+divid+" div").empty().html(tableHtml);
							    tablefunction($("#"+divid+" table"));
						  },
						  cancel : function(){
							  //关闭回调设置隐藏
							  $("#"+divid).addClass("hide")
                              $("#"+divid+" div").empty();
						  }
					 });
				
				});
				
			},
			/**
			 * 关门窗口
			 */
			close : function(btnid){
				//隐藏表格
				$("#popup_"+btnid).addClass("hide")
				//关闭
				layer.closeAll();
				
			}
	}

});