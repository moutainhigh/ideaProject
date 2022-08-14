define(function(require, exports, module) {
	this._table = require("js/base/table");

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
										custromerId : {
						validators : {
							notEmpty : {
								message : "custromerId不能为空"
							}
						}
					},
					custromerName : {
						validators : {
							notEmpty : {
								message : "custromerName不能为空"
							}
						}
					},
					ordertNum : {
						validators : {
							notEmpty : {
								message : "ordertNum不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
							}
						}
					},
					fixPriceCoinCode : {
						validators : {
							notEmpty : {
								message : "fixPriceCoinCode不能为空"
							}
						}
					},
					fixPriceType : {
						validators : {
							notEmpty : {
								message : "fixPriceType不能为空"
							}
						}
					},
					rewardmoney : {
						validators : {
							notEmpty : {
								message : "rewardmoney不能为空"
							}
						}
					},
					deliveryName : {
						validators : {
							notEmpty : {
								message : "deliveryName不能为空"
							}
						}
					},
					deliveryId : {
						validators : {
							notEmpty : {
								message : "deliveryId不能为空"
							}
						}
					},
					hierarchy : {
						validators : {
							notEmpty : {
								message : "hierarchy不能为空"
							}
						}
					},
					userMoney : {
						validators : {
							notEmpty : {
								message : "userMoney不能为空"
							}
						}
					},
					ratetype : {
						validators : {
							notEmpty : {
								message : "ratetype不能为空"
							}
						}
					},
					personType : {
						validators : {
							notEmpty : {
								message : "personType不能为空"
							}
						}
					},
					basemoney : {
						validators : {
							notEmpty : {
								message : "basemoney不能为空"
							}
						}
					},
					changeMoney : {
						validators : {
							notEmpty : {
								message : "changeMoney不能为空"
							}
						}
					},
					currentMoney : {
						validators : {
							notEmpty : {
								message : "currentMoney不能为空"
							}
						}
					},
					feeamout : {
						validators : {
							notEmpty : {
								message : "feeamout不能为空"
							}
						}
					},
					transactionTime : {
						validators : {
							notEmpty : {
								message : "transactionTime不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
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
					url : _ctx + "/commend/appcommendtrade/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/commend/appcommendtradelist')
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
					custromerId : {
						validators : {
							notEmpty : {
								message : "custromerId不能为空"
							}
						}
					},
					custromerName : {
						validators : {
							notEmpty : {
								message : "custromerName不能为空"
							}
						}
					},
					ordertNum : {
						validators : {
							notEmpty : {
								message : "ordertNum不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
							}
						}
					},
					fixPriceCoinCode : {
						validators : {
							notEmpty : {
								message : "fixPriceCoinCode不能为空"
							}
						}
					},
					fixPriceType : {
						validators : {
							notEmpty : {
								message : "fixPriceType不能为空"
							}
						}
					},
					rewardmoney : {
						validators : {
							notEmpty : {
								message : "rewardmoney不能为空"
							}
						}
					},
					deliveryName : {
						validators : {
							notEmpty : {
								message : "deliveryName不能为空"
							}
						}
					},
					deliveryId : {
						validators : {
							notEmpty : {
								message : "deliveryId不能为空"
							}
						}
					},
					hierarchy : {
						validators : {
							notEmpty : {
								message : "hierarchy不能为空"
							}
						}
					},
					userMoney : {
						validators : {
							notEmpty : {
								message : "userMoney不能为空"
							}
						}
					},
					ratetype : {
						validators : {
							notEmpty : {
								message : "ratetype不能为空"
							}
						}
					},
					personType : {
						validators : {
							notEmpty : {
								message : "personType不能为空"
							}
						}
					},
					basemoney : {
						validators : {
							notEmpty : {
								message : "basemoney不能为空"
							}
						}
					},
					changeMoney : {
						validators : {
							notEmpty : {
								message : "changeMoney不能为空"
							}
						}
					},
					currentMoney : {
						validators : {
							notEmpty : {
								message : "currentMoney不能为空"
							}
						}
					},
					feeamout : {
						validators : {
							notEmpty : {
								message : "feeamout不能为空"
							}
						}
					},
					transactionTime : {
						validators : {
							notEmpty : {
								message : "transactionTime不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
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
					url : _ctx + "/commend/appcommendtrade/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/commend/appcommendtradelist')
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
            $("#table_reset").on("click", function() {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function() {
                var params=  $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"),params);
            });

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/commend/appcommendtrade/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/commend/appcommendtrade/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/commend/appcommendtrade/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/commend/appcommendtrade/list.do",
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
					title : '代理商邮箱',
					field : 'appPersonInfo.email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '代理商手机号',
					field : 'appPersonInfo.mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '订单号',
					field : 'ordertNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '交易方',
					field : 'personType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false,
                    formatter: function (value, row, index) {
                        if (value == "1") {
                            return "买方";
                        }
                        return "卖方";
                    }
				},
				{
					title : '返佣币种',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易总量',
					field : 'feeamout',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '返佣基数',
					field : 'basemoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '平台返佣',
					field : 'rewardmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '所属级数',
					field : 'hierarchy',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '交款人邮箱',
					field : 'deliveryEmail',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交款人手机号',
					field : 'deliveryMobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '用户返佣',
					field : 'userMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '交易时间',
					field : 'transactionTime',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '返佣状态',
					field : 'status',
					align : 'center',
					visible : _appName,
					sortable : false,
					searchable : false,
					formatter: function (value, row, index) {
                        if (value == 0) {
                            return "未发放";
                        }else if(value == 1){
                        	 return "已发放";
                        }else if(value == 2){
                        	 return "已作废";
                        }else if(value == 3){
                        	return "发放失败";
                        }
                        
                    }
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});