define(function(require, exports, module) {


	var picker = require("lib/datepicker/js/bootstrap-datetimepicker");


	module.exports = {
			//渲染URL加载的select
			init : function(){

				var allinput = $("input[data-date-format]");
				for(var i = 0 ; i < allinput.length; i++){
					var input = $(allinput[i]);
					var id = input.attr("id");
				 	$('#'+id).datetimepicker({
				 		// minView: "month",//设置只显示到月份
						 autoclose:true,//选中关闭
						 todayBtn: true,//今日按钮
						 weekStart:1,
						 todayHighlight:true
				 	});
				}

			},
			/**
			 * 返回今天 yyyy-MM-dd
			 */
			getToday:function(){
				var date = new Date();
				var year = date.getFullYear();
				var month = date.getMonth() + 1;
				var day = date.getDate()+1;
				var hour = date.getHours();
				var minute = date.getMinutes();
				var second = date.getSeconds();
				return year + '-' + month + '-' + day ;
			},
			/**
			 * 返回一个星期前 yyy-MM-dd
			 */
			getLastWeek:function(){
				var now = new Date();
				var date = new Date(now.getTime() - 7 * 24 * 3600 * 1000);
				var year = date.getFullYear();
				var month = date.getMonth() + 1;
				var day = date.getDate();
				var hour = date.getHours();
				var minute = date.getMinutes();
				var second = date.getSeconds();
				return year + '-' + month + '-' + day ;
			},
			/**
			 * 返回两个日期相差天数
			 */
			getDays:function(strDateStart,strDateEnd){
				   var strSeparator = "-"; //日期分隔符
				   var oDate1;
				   var oDate2;
				   var iDays;
				   oDate1= strDateStart.split(strSeparator);
				   oDate2= strDateEnd.split(strSeparator);
				   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
				   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
				   iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数
				   return iDays ;
			}


	}

});