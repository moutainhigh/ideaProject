define(function(require, exports, module) {
	this._table = require("js/base/table");
	this.sele = require("js/base/HrySelect");

	module.exports = {
		//查看页面--初始化方法
		see : function(){
		},
		//添加页面--初始化方法
		add : function() {
			// 表单验证
			$('#form').bootstrapValidator({
				submitButtons : "button[id=addSubmit]",
				message : '不能为空',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
										customerId : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					transactionNum : {
						validators : {
							notEmpty : {
								message : "流水号不能为空"
							}
						}
					},
					accountId : {
						validators : {
							notEmpty : {
								message : "数字货币账户id不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种不能为空"
							}
						}
					},
					smeMoney : {
						validators : {
							notEmpty : {
								message : "买入平台币金额不能为空"
							}
						}
					},
					usdtMoney : {
						validators : {
							notEmpty : {
								message : "需要支付USDT金额不能为空"
							}
						}
					},
					firstMoney : {
						validators : {
							notEmpty : {
								message : "保证金不能为空"
							}
						}
					},
					lastMoney : {
						validators : {
							notEmpty : {
								message : "尾款不能为空"
							}
						}
					},
					interestMoney : {
						validators : {
							notEmpty : {
								message : "利息不能为空"
							}
						}
					},
					trusteeshipStatus : {
						validators : {
							notEmpty : {
								message : "托管状态：1否 2是不能为空"
							}
						}
					},
					rushOrders : {
						validators : {
							notEmpty : {
								message : "是否抢单：1否 2是不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "订单状态：1排队中 2排队成功待支付 3待收款 4已成交 5已作废 不能为空"
							}
						}
					},
					userId : {
						validators : {
							notEmpty : {
								message : "操作人id不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "remark不能为空"
							}
						}
					},
					timeStamp : {
						validators : {
							notEmpty : {
								message : "排队开始时间戳不能为空"
							}
						}
					}
				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			});
			// 添加提交
			$("#addSubmit").on("click", function() {
				var options = {
					url : _ctx + "/klg/transaction/klgbuytransaction/add.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
						
						//重置验证
						jqForm.data("bootstrapValidator").resetForm();
						// 手动触发验证
						var bootstrapValidator = jqForm.data('bootstrapValidator');
						bootstrapValidator.validate();
						if (!bootstrapValidator.isValid()) {
							return false;
						}
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('添加成功!', {icon : 1});
								loadUrl(_ctx+'/v.do?u=/admin/klg/transaction/klgbuytransactionlist')
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}

				};
				$("#form").ajaxSubmit(options);
			});
		},
		//修改页面--初始化方法
		modify : function() {
			// 表单验证
			$('#form').bootstrapValidator({
				submitButtons : "button[id=modifySubmit]",
				message : '不能为空',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					customerId : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					transactionNum : {
						validators : {
							notEmpty : {
								message : "流水号不能为空"
							}
						}
					},
					accountId : {
						validators : {
							notEmpty : {
								message : "数字货币账户id不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种不能为空"
							}
						}
					},
					smeMoney : {
						validators : {
							notEmpty : {
								message : "买入平台币金额不能为空"
							}
						}
					},
					usdtMoney : {
						validators : {
							notEmpty : {
								message : "需要支付USDT金额不能为空"
							}
						}
					},
					firstMoney : {
						validators : {
							notEmpty : {
								message : "保证金不能为空"
							}
						}
					},
					lastMoney : {
						validators : {
							notEmpty : {
								message : "尾款不能为空"
							}
						}
					},
					interestMoney : {
						validators : {
							notEmpty : {
								message : "利息不能为空"
							}
						}
					},
					trusteeshipStatus : {
						validators : {
							notEmpty : {
								message : "托管状态：1否 2是不能为空"
							}
						}
					},
					rushOrders : {
						validators : {
							notEmpty : {
								message : "是否抢单：1否 2是不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "订单状态：1排队中 2排队成功待支付 3待收款 4已成交 5已作废 不能为空"
							}
						}
					},
					userId : {
						validators : {
							notEmpty : {
								message : "操作人id不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "remark不能为空"
							}
						}
					},
					timeStamp : {
						validators : {
							notEmpty : {
								message : "排队开始时间戳不能为空"
							}
						}
					}
				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			});
			// 修改提交
			$("#modifySubmit").on("click", function() {
				var options = {
					url : _ctx + "/klg/transaction/klgbuytransaction/modify.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
						
						//重置验证
						jqForm.data("bootstrapValidator").resetForm();
						// 手动触发验证
						var bootstrapValidator = jqForm.data('bootstrapValidator');
						bootstrapValidator.validate();
						if (!bootstrapValidator.isValid()) {
							return false;
						}
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('修改成功!', {icon : 1});
								loadUrl(_ctx+'/v.do?u=/admin/klg/transaction/klgbuytransactionlist')
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}
				};
				$("#form").ajaxSubmit(options);
			});
		},
		//列表页面--初始化方法
		list : function() {
			 //重置按钮
            $("#table_reset").on("click", function () {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });
            //转换日期格式(时间戳转换为datetime格式)
            function changeDateFormat(cellval) {
                var dateVal = cellval + "";
                if (cellval != null) {
                    var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
                    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                    
                    var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
                    var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
                    var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
                    
                    return date.getFullYear() + "-" + month + "-" + currentDate;
                }
            }
			function daysBetween(DateOne, DateTwo) {
				var OneMonth = DateOne.substring(5, DateOne.lastIndexOf('-'));
				var OneDay = DateOne.substring(DateOne.length, DateOne
						.lastIndexOf('-') + 1);
				var OneYear = DateOne.substring(0, DateOne.indexOf('-'));

				var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf('-'));
				var TwoDay = DateTwo.substring(DateTwo.length, DateTwo
						.lastIndexOf('-') + 1);
				var TwoYear = DateTwo.substring(0, DateTwo.indexOf('-'));

				var cha = ((Date.parse(OneMonth + '/' + OneDay + '/' + OneYear) - Date
						.parse(TwoMonth + '/' + TwoDay + '/' + TwoYear)) / 86400000);
				return Math.abs(cha);
			} 
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/transaction/klgbuytransaction/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
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
					title : '邮箱',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手机号',
					field : 'mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '买入平台币金额',
					field : 'smeMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '需要支付USDT',
					field : 'usdtMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '保证金',
					field : 'firstMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '利息',
					field : 'interestMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
					title : '预约时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '完成时间',
					field : 'modified',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						if(row.status==5){
							return value;
						}
                    	if(row.status!=4){
                    		return "-";
                    	}else{
                    		return value;
                    	}
                    }
				},
				{
					title : '排队时长',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                    	var dateBegin = new Date(value);//开始时间
                    	var dateNow=new Date();  //现在时间
                    	if(row.status==5||row.status==4){
                    		dateNow = new Date(row.modified);
                    	}
                    	var date = dateNow.getTime() - dateBegin.getTime();
                    	//return Math.floor(date/(24*3600*1000))+"天";
                    	return daysBetween(changeDateFormat(dateBegin.getTime()),changeDateFormat(dateNow.getTime()))+"天";
                    }
				},
				{
					title : '订单托管状态',
					field : 'trusteeshipStatus',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if (value == "1") {
                            return "否";
                        } else if (value == "2") {
                            return "是";
                        }
                    }
				},
				{
					title : '是否抢单',
					field : 'rushOrders',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if (value == "1") {
                            return "否";
                        } else if (value == "2") {
                            return "是";
                        }
                    }
				},
				{
					title : '订单状态 ',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        var aa = sele.getKeyAndVal("klgBuyTransactionStatus",value)
                        return  aa;

                    }
				},
				{
					title : '操作人id',
					field : 'userId',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '备注',
					field : 'remark',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
		//按天分组查询
		findListGroupByday : function() {
			//修改——转换日期格式(时间戳转换为datetime格式)
	        function changeDateFormat(cellval) {
	            if (cellval != null) {
	                var date = new Date(cellval);
	                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	                var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	                return date.getFullYear() + "-" + month + "-" + currentDate;
	            }
	        }
			 //重置按钮
            $("#table_reset").on("click", function () {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/transaction/klgbuytransaction/findListGroupByday.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},{
					title : '预约单数',
					field : 'count',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '预约买入总金额',
					field : 'smeMoneySum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
					title : '预约时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
	                //——修改——获取日期列的值进行转换
	                formatter: function (value, row, index) {
	                    return changeDateFormat(value)
	                }
				},
				{
					title : '排队时长',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                    	var dateBegin = new Date(value);//开始时间
                    	var dateNow=new Date();  //现在时间
                    	var date = dateNow.getTime() - dateBegin.getTime();
                    	return Math.floor(date/(24*3600*1000))+"天";
                    }
				},
				{
					title : '备注',
					field : 'remark',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
		//列表页面--初始化方法
		interestList : function() {
			
			/*解冻按钮*/
            $("#interestJiedong").on("click", function () {
            	var ids = _table.getIdSelects($("#table"));
            	//alert(ids);
            	if (ids != undefined && ids.length >= 1) {
            		layer.confirm('你确定要将平台奖励的SME进行解冻么？', {
                        btn: ['确定', '取消'],
                        ids: ids
                    }, function () {
                    	layer.confirm('利息解冻后用户就可以正常进行交易使用了，再次跟您确认是否将平台奖励的SME利息进行解冻？', {
                            btn: ['确定', '取消'],
                            ids: ids
                        }, function () {
                        	layer.confirm('延期利息一旦解冻后期再奖励会直接进入可用，解冻后利息将永远无法再次进行冻结，最后一次跟您确认是否将平台奖励的SME利息进行解冻？', {
                                btn: ['确定', '取消'],
                                ids: ids
                            }, function () {
                            	$.ajax({
		                        	url: _ctx + "/klg/transaction/klgbuytransaction/jiedongSubmit.do?ids=" + ids,
		                            type: "post",
		                            data: {},
		                            cache: false,
		                            dataType: "json",
		                            success: function (data) {
		                                if (data) {
		                                    if (data.success) {
		                                        layer.msg("解冻成功", {
		                                            icon: 1,
		                                        })
		                                    } else {
		                                        layer.msg("解冻失败", {
		                                            icon: 2,
		                                        })
		                                    }
							            	loadUrl(_ctx+'/v.do?u=/admin/klg/transaction/klgbuytransactionlist_jiedong');
		                                }
		                            },
		                            error: function (e) {
		
		                            }
		                        });
                            })
                        	
                        })

                    })
            	}else{
            		layer.msg('请选择一条以上数据', {icon: 2});
            	}
            	
			});
			 //重置按钮
            $("#table_reset").on("click", function () {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/transaction/klgbuytransaction/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
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
					title : '邮箱',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手机号',
					field : 'mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '保证金',
					field : 'firstMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '利息(SME)',
					field : 'interestMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '排队时长',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                    	var dateBegin = new Date(value);//开始时间
                    	var dateNow=new Date(row.modified);  //现在时间
                    	var date = dateNow.getTime() - dateBegin.getTime();
                    	return Math.floor(date/(24*3600*1000))+"天";
                    }
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
					title : '成交时间',
					field : 'modified',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '备注',
					field : 'remark',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});