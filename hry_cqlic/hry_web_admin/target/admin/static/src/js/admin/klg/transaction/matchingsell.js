define(function(require, exports, module) {
	this._table = require("js/base/table");
	this.sele = require("js/base/HrySelect");
	var sellSum = 0;
	var overAllIds = new Array();                // 全局保存选中行的对象
	module.exports = {
		//列表页面--初始化方法
		listbuy : function() {
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				//url : _ctx + "/klg/transaction/klgbuytransaction/list.do",
				url : _ctx + "/klg/transaction/matching/pipeiSellSelect.do?overAllIds="+overAllIds+"&status=1",
				columns : [ 
				{
					title : '买单账户',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                    	var mobilePhone = row.mobilePhone;
                    	if(mobilePhone==""||mobilePhone==null){
                    		return value;
                    	}
                    	return mobilePhone;
                    }
				},
				{
					title : '订单号',
					field : 'transactionNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '买入数量',
					field : 'smeMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '支付USDT',
					field : 'usdtMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '预约时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table_buy"), conf);
		},
		//列表页面--初始化方法
		listsell : function() {
			overAllIds = new Array();
			$.ajax({
				type : "post",
				url : _ctx + "/klg/transaction/matching/buysellData.do",
				cache : false,
				dataType : "json",
				success : function(data) {
					$("#buyPay1").html(data.obj.buyPay1);
					$("#buyPay2").html(data.obj.buyPay2);
					$("#buyPay3").html(data.obj.buyPay3);
					$("#sellPay1").html(data.obj.sellPay1);
					$("#sellPay2").html(data.obj.sellPay2);
					$("#sellPay3").html(data.obj.sellPay3);
				}
			});
			 //重置按钮
            $("#table_reset_sell").on("click", function () {
                $("#table_query_form_sell")[0].reset();
            });
            //查询按钮
            $("#table_query_sell").on("click", function () {
                var params = $("#table_query_form_sell").serializeJson();
                //查询
                _table.tableQuery($("#table_sell"), params);
            });
            $('#table_sell').on('uncheck.bs.table check.bs.table check-all.bs.table uncheck-all.bs.table',function(e,rows){
                var datas = $.isArray(rows) ? rows : [rows];        // 点击时获取选中的行或取消选中的行
                examine(e.type,datas);                                 // 保存到全局 Set() 里
            });
            var smeSellSum = 0;
            var smeSellSumArray = new Array();  //全局数组

            function examine(type,datas){            
                if(type.indexOf('uncheck')==-1){    
                    $.each(datas,function(i,v){
                       // 添加时，判断一行或多行的 id 是否已经在数组里 不存则添加　
                    	overAllIds.indexOf(v.id) == -1 ? overAllIds.push(v.id) : -1;
            			smeSellSum = Number(smeSellSum)+Number(v.smeMoney)+Number(v.candySmeMoney);
            			});
                }else{
                    $.each(datas,function(i,v){
                        overAllIds.splice(overAllIds.indexOf(v.id),1);    //删除取消选中行
                        smeSellSum = Number(smeSellSum)-Number(v.smeMoney)-Number(v.candySmeMoney);
                    });
                }
                $("#smeSellSum").html("已选买单："+smeSellSum+"SME");
            }
            
            /*匹配按钮*/
            $("#pipei").on("click", function () {
            	$("#overAllIds").val(overAllIds);
            	sellSum = 0;
			    var opt = {
			        url: _ctx + "/klg/transaction/matching/pipeiSellSelect.do?overAllIds="+overAllIds+"&status=1",
			        silent: true,
			        query:{
			        	overAllIds:overAllIds
			        }
			    };
			    //带参数 刷新
			    $("#table_buy").bootstrapTable('refresh', opt);
			});
            
            /*匹配确认按钮*/
            $("#pipeiConfirm").on("click", function () {
            	
            	if (overAllIds != undefined && overAllIds.length >= 1) {
            		$.ajax({
    					type : "post",
    					url : _ctx + "/klg/transaction/matching/pipeisell.do?ids="+overAllIds+"&status=1",
    					cache : false,
    					dataType : "html",
    					success : function(data) {
    						layer.open({
    						   type: 1,
    						   skin: 'layui-layer-rim', //加上边框
    						   closeBtn: 1, //不显示关闭按钮
    						   title:"匹配确认",
    						   area: ['700px', '500px'], //宽高
    						   content: data,
    						   resize:false,
    						   shadeClose : false,
    						   success: function(layero, index){
    							   /*匹配提交按钮*/
    					            $("#peipeiSubmit").on("click", function () {
    					            	$("#peipeiSubmit").attr("disabled", true); 
    					            	$.ajax({
    					    				type : "post",
    					    				url : _ctx + "/klg/transaction/matching/peipeiSellSubmit.do?ids="+overAllIds+"&status=1",
    					    				cache : false,
    					    				dataType : "json",
    					    				success : function(data) {
    					    					if (data.success) {
    					    						overAllIds = new Array();
    					    						layer.msg('匹配成功!', {icon : 1,time:5000});
    					    						layer.closeAll();
    								            	loadUrl(_ctx+'/v.do?u=/admin/klg/transaction/matchingselllist');
    											} else {
    												$("#peipeiSubmit").attr("disabled", false); 
    												layer.msg(data.msg, {icon : 2});
    											}
    					    					
    					    				}
    					    			});
    					            	
    					            });
    						   },
    						   end: function () {
    								layer.closeAll();
    						   }
    						});
    					}
    				});
            	}else{
            		layer.msg('请选择一条以上数据', {icon: 2});
            	}
            });
            
            /*调控按钮*/
            $("#tiaokong").on("click", function () {
            	$.ajax({
					type : "post",
					url : _ctx + "/klg/transaction/matching/tiaokong.do",
					cache : false,
					dataType : "html",
					success : function(data) {
						layer.open({
						   type: 1,
						   skin: 'layui-layer-rim', //加上边框
						   closeBtn: 1, //不显示关闭按钮
						   title:"调控",
						   area: ['500px', '300px'], //宽高
						   content: data,
						   resize:false,
						   shadeClose : false,
						   success: function(layero, index){
							   /*调控提交按钮*/
					            $("#tiaokongSubmit").on("click", function () {
					            	$("#tiaokongSubmit").attr("disabled", true); 
					            	var tiaokongSme = $("#tiaokongSme").val();
					            	var tiaokongUsdt = $("#tiaokongUsdt").val();
					            	if(Number(tiaokongSme)<0){
					            		layer.msg("调控金额不能小于0", {icon : 2});
					            		$("#tiaokongSubmit").attr("disabled", false); 
					            		return;
					            	}
					            	if(Number(tiaokongUsdt)<0){
					            		layer.msg("调控金额不能小于0", {icon : 2});
					            		$("#tiaokongSubmit").attr("disabled", false);
					            		return;
					            	}
					            	$.ajax({
					    				type : "post",
					    				url : _ctx + "/klg/transaction/matching/tiaokongSubmit.do?tiaokongSme="+tiaokongSme+"&tiaokongUsdt="+tiaokongUsdt,
					    				cache : false,
					    				dataType : "json",
					    				success : function(data) {
					    					if (data.success) {
					    						layer.closeAll();
								            	loadUrl(_ctx+'/v.do?u=/admin/klg/transaction/matchingselllist');
											} else {
												layer.msg(data.msg, {icon : 2});
												$("#tiaokongSubmit").attr("disabled", false);
											}
					    					
					    				}
					    			});
					            	
					            });
						   },
						   end: function () {
								layer.closeAll();
						   }
						});
					}
				});
			});
            /*吃单按钮*/
            $("#chidan").on("click", function () {
            	var ids = _table.getIdSelects($("#table_sell"));
            	//alert(ids);
            	if (ids != undefined && ids.length >= 1) {
            		layer.confirm('你确定要吃掉这些订单吗？', {
                        btn: ['确定', '取消'],
                        ids: ids
                    }, function () {
                        $.ajax({
                        	url: _ctx + "/klg/transaction/matching/chidanSellSubmit.do?ids=" + ids,
                            type: "post",
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("吃单成功", {
                                            icon: 1,
                                        })
                                    } else {
                                        layer.msg("吃单失败", {
                                            icon: 2,
                                        })
                                    }
					            	loadUrl(_ctx+'/v.do?u=/admin/klg/transaction/matchingselllist');
                                }
                            },
                            error: function (e) {

                            }
                        });

                    })
            	}else{
            		layer.msg('请选择一条以上数据', {icon: 2});
            	}
            	
			});
            
            /*剩余转出按钮*/
            $("#surplusOut").on("click", function () {
            	$.ajax({
					type : "post",
					url : _ctx + "/klg/transaction/matching/outSurplus.do",
					cache : false,
					dataType : "html",
					success : function(data) {
						layer.open({
						   type: 1,
						   skin: 'layui-layer-rim', //加上边框
						   closeBtn: 1, //不显示关闭按钮
						   title:"剩余转出",
						   area: ['500px', '300px'], //宽高
						   content: data,
						   resize:false,
						   shadeClose : false,
						   success: function(layero, index){
							   /*调控提交按钮*/
					            $("#outSubmit").on("click", function () {
					            	$("#outSubmit").attr("disabled", true); 
					            	var outSme = $("#outSme").val();
					            	var outUsdt = $("#outUsdt").val();
					            	var buyPay3 = $("#buyPay3").html();
					            	var sellPay3 = $("#sellPay3").html();
					            	if(Number(outSme)>Number(sellPay3)){
					            		layer.msg("转出数量不能大于剩余数量", {icon : 2});
					            		$("#outSubmit").attr("disabled", false); 
					            		return;
					            	}
					            	if(Number(outUsdt)>Number(buyPay3)){
					            		layer.msg("转出数量不能大于剩余数量", {icon : 2});
					            		$("#outSubmit").attr("disabled", false); 
					            		return;
					            	}
					            	$.ajax({
					    				type : "post",
					    				url : _ctx + "/klg/transaction/matching/outSurplusSubmit.do?outSme="+outSme+"&outUsdt="+outUsdt,
					    				cache : false,
					    				dataType : "json",
					    				success : function(data) {
					    					if (data.success) {
					    						layer.closeAll();
								            	loadUrl(_ctx+'/v.do?u=/admin/klg/transaction/matchingbuylist');
											} else {
												$("#outSubmit").attr("disabled", false); 
												layer.msg(data.msg, {icon : 2});
											}
					    					
					    				}
					    			});
					            	
					            });
						   },
						   end: function () {
								layer.closeAll();
						   }
						});
					}
				});
			});
            
            /*卖单单独匹配按钮*/
            $("#sellAlonePipeiSubmit").on("click", function () {
            	var ids = _table.getIdSelects($("#table_sell"));
            	//alert(ids);
            	if (ids != undefined && ids.length >= 1) {
            		layer.confirm('你确定要单独匹配这些订单吗？', {
                        btn: ['确定', '取消'],
                        ids: ids
                    }, function () {
                        $.ajax({
                        	url: _ctx + "/klg/transaction/matching/sellAlonePipeiSubmit.do?ids=" + ids,
                            type: "post",
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("匹配成功", {
                                            icon: 1,
                                        })
                                    } else {
                                        layer.msg("匹配失败", {
                                            icon: 2,
                                        })
                                    }
					            	loadUrl(_ctx+'/v.do?u=/admin/klg/transaction/matchingselllist');
                                }
                            },
                            error: function (e) {

                            }
                        });

                    })
            	}else{
            		layer.msg('请选择一条以上数据', {icon: 2});
            	}
            	
			});
            
            sellSum = 0;
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/transaction/klgselltransaction/list.do",
				//url : _ctx + "/klg/transaction/matching/pipeiSelect.do?overAllIds="+overAllIds+"&status=1",
				columns : [ 
				{
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false,
					formatter: function (i,row) {            // 每次加载 checkbox 时判断当前 row 的 id 是否已经存在全局 Set() 里
				        if($.inArray(row.id,Array.from(overAllIds))!=-1){    // 因为 Set是集合,需要先转换成数组  
				            return {
				                checked : true               // 存在则选中
				            }
				        }
				    }  
				
				},
				{
					title : '卖单账户',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                    	var mobilePhone = row.mobilePhone;
                    	if(mobilePhone==""||mobilePhone==null){
                    		return value;
                    	}
                    	return mobilePhone;
                    }
				},
				{
					title : '流水号',
					field : 'transactionNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '卖单数量',
					field : 'smeMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                    	/*sellSum = Number(sellSum)+Number(value)+Number(row.candySmeMoney);
                    	$("#smeSellSum").html("已选卖单："+sellSum+"SME");*/
                    	return value;
                    }
				},
				{
					title : '糖果数量',
					field : 'candySmeMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '预约时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '预约天数',
					field : 'sellDay',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '预约卖出时间',
					field : 'sellEndTime',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table_sell"), conf);
		}
	}

});