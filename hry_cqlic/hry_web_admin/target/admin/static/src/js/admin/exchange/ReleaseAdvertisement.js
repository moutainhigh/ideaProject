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
										customerId : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					accountId : {
						validators : {
							notEmpty : {
								message : "虚拟账户id不能为空"
							}
						}
					},
					coinName : {
						validators : {
							notEmpty : {
								message : "币种名称不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种代码不能为空"
							}
						}
					},
					transactionMode : {
						validators : {
							notEmpty : {
								message : "交易方式(1在线购买,2在线出售,3本地购买)不能为空"
							}
						}
					},
					nationality : {
						validators : {
							notEmpty : {
								message : "国籍不能为空"
							}
						}
					},
					isFixed : {
						validators : {
							notEmpty : {
								message : "固定价格是否开启 0否 1是不能为空"
							}
						}
					},
					tradeMoney : {
						validators : {
							notEmpty : {
								message : "交易金额不能为空"
							}
						}
					},
					premium : {
						validators : {
							notEmpty : {
								message : "溢价不能为空"
							}
						}
					},
					paymentTerm : {
						validators : {
							notEmpty : {
								message : "付款期限不能为空"
							}
						}
					},
					payType : {
						validators : {
							notEmpty : {
								message : "交易类型(1银行转账,2支付宝,3微信支付)不能为空"
							}
						}
					},
					tradeMoneyMix : {
						validators : {
							notEmpty : {
								message : "最低交易金额不能为空"
							}
						}
					},
					tradeMoneyMax : {
						validators : {
							notEmpty : {
								message : "最高交易金额不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "备注不能为空"
							}
						}
					},
					isAutomatic : {
						validators : {
							notEmpty : {
								message : "固定价格是否开启 0否 1是不能为空"
							}
						}
					},
					automaticReply : {
						validators : {
							notEmpty : {
								message : "自动回复内容不能为空"
							}
						}
					},
					isSecurity : {
						validators : {
							notEmpty : {
								message : "是否启用安全选项 0否 1是不能为空"
							}
						}
					},
					isBeTrusted : {
						validators : {
							notEmpty : {
								message : "是否启用仅限受信任的交易者 0否 1是不能为空"
							}
						}
					},
					transactionNum : {
						validators : {
							notEmpty : {
								message : "交易次数不能为空"
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
					payTypeRemake : {
						validators : {
							notEmpty : {
								message : "上传的资料不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "广告状态 0关闭 1开启不能为空"
							}
						}
					},
					state : {
						validators : {
							notEmpty : {
								message : "广告状态为0有效，1作废不能为空"
							}
						}
					},
					bankId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					bankNumber : {
						validators : {
							notEmpty : {
								message : "银行卡号不能为空"
							}
						}
					},
					alipayId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					alipayAccount : {
						validators : {
							notEmpty : {
								message : "支付宝账号不能为空"
							}
						}
					},
					alipayThingUrl : {
						validators : {
							notEmpty : {
								message : "支付宝二维码不能为空"
							}
						}
					},
					wechatId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					wechatAccount : {
						validators : {
							notEmpty : {
								message : "微信账号不能为空"
							}
						}
					},
					wechatThingUrl : {
						validators : {
							notEmpty : {
								message : "微信二维码不能为空"
							}
						}
					},
					coinNumMin : {
						validators : {
							notEmpty : {
								message : "数量min不能为空"
							}
						}
					},
					coinNumMax : {
						validators : {
							notEmpty : {
								message : "数量max不能为空"
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
					url : _ctx + "/exchange/releaseadvertisement/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/releaseadvertisementlist')
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
					accountId : {
						validators : {
							notEmpty : {
								message : "虚拟账户id不能为空"
							}
						}
					},
					coinName : {
						validators : {
							notEmpty : {
								message : "币种名称不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种代码不能为空"
							}
						}
					},
					transactionMode : {
						validators : {
							notEmpty : {
								message : "交易方式(1在线购买,2在线出售,3本地购买)不能为空"
							}
						}
					},
					nationality : {
						validators : {
							notEmpty : {
								message : "国籍不能为空"
							}
						}
					},
					isFixed : {
						validators : {
							notEmpty : {
								message : "固定价格是否开启 0否 1是不能为空"
							}
						}
					},
					tradeMoney : {
						validators : {
							notEmpty : {
								message : "交易金额不能为空"
							}
						}
					},
					premium : {
						validators : {
							notEmpty : {
								message : "溢价不能为空"
							}
						}
					},
					paymentTerm : {
						validators : {
							notEmpty : {
								message : "付款期限不能为空"
							}
						}
					},
					payType : {
						validators : {
							notEmpty : {
								message : "交易类型(1银行转账,2支付宝,3微信支付)不能为空"
							}
						}
					},
					tradeMoneyMix : {
						validators : {
							notEmpty : {
								message : "最低交易金额不能为空"
							}
						}
					},
					tradeMoneyMax : {
						validators : {
							notEmpty : {
								message : "最高交易金额不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "备注不能为空"
							}
						}
					},
					isAutomatic : {
						validators : {
							notEmpty : {
								message : "固定价格是否开启 0否 1是不能为空"
							}
						}
					},
					automaticReply : {
						validators : {
							notEmpty : {
								message : "自动回复内容不能为空"
							}
						}
					},
					isSecurity : {
						validators : {
							notEmpty : {
								message : "是否启用安全选项 0否 1是不能为空"
							}
						}
					},
					isBeTrusted : {
						validators : {
							notEmpty : {
								message : "是否启用仅限受信任的交易者 0否 1是不能为空"
							}
						}
					},
					transactionNum : {
						validators : {
							notEmpty : {
								message : "交易次数不能为空"
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
					payTypeRemake : {
						validators : {
							notEmpty : {
								message : "上传的资料不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "广告状态 0关闭 1开启不能为空"
							}
						}
					},
					state : {
						validators : {
							notEmpty : {
								message : "广告状态为0有效，1作废不能为空"
							}
						}
					},
					bankId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					bankNumber : {
						validators : {
							notEmpty : {
								message : "银行卡号不能为空"
							}
						}
					},
					alipayId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					alipayAccount : {
						validators : {
							notEmpty : {
								message : "支付宝账号不能为空"
							}
						}
					},
					alipayThingUrl : {
						validators : {
							notEmpty : {
								message : "支付宝二维码不能为空"
							}
						}
					},
					wechatId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					wechatAccount : {
						validators : {
							notEmpty : {
								message : "微信账号不能为空"
							}
						}
					},
					wechatThingUrl : {
						validators : {
							notEmpty : {
								message : "微信二维码不能为空"
							}
						}
					},
					coinNumMin : {
						validators : {
							notEmpty : {
								message : "数量min不能为空"
							}
						}
					},
					coinNumMax : {
						validators : {
							notEmpty : {
								message : "数量max不能为空"
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
					url : _ctx + "/exchange/releaseadvertisement/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/releaseadvertisementlist')
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
			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/releaseadvertisement/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/releaseadvertisement/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/exchange/releaseadvertisement/remove.do");
			});
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });
            //重置
            $("#table_reset").on('click', function(){
                $("#table_query_form")[0].reset();
            })

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/exchange/releaseadvertisement/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '订单编号',
					field : 'id',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
                    title : '发布方手机号',
                    field : 'email',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
                {
                    title : '发布方邮箱号',
                    field : 'mobilePhone',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
				{
					title : '币种名称',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易方式',
					field : 'transactionMode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if (value == 2) {
                            return "在线购买";
                        }else if (value == 1) {
                            return "在线出售";
                        }
                        return "";
                    }
				},
				{
					title : '法币类型',
					field : 'legalCurrency',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '国籍',
					field : 'nationality',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易金额',
					field : 'tradeMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '溢价',
					field : 'premium',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '付款期限',
					field : 'paymentTerm',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易类型',
					field : 'payType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
                        if(value != null && value != ''){
                            var v = value.split(',');
                            var t = '';
                            for(var i=0;i<v.length;i++){
                                if (v[i] == 1) {
                                    t += '银行转账,'
                                }else if (v[i] == 2) {
                                    t += '支付宝,'
                                }else if (v[i] == 3) {
                                    t += '微信支付,'
                                }
                            }
                            if(t !=''){
                                t = t.substr(0, t.length - 1);
                            }
                            return t;
                        }
                        return "";
                    }
				},
				{
                    title : '发布总数',
                    field : 'initialCoinNumMax',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
                {
                    title : '已成交',
                    field : 'initialCoinNumMax',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true,
                    formatter: function (value, row, index) {
                        if( row.initialCoinNumMax != null){
                            return row.initialCoinNumMax - row.coinNumMax;
                        }
                        return "";
                    }
                },
                {
                    title : '未成交',
                    field : 'coinNumMax',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
				{
					title : '备注',
					field : 'remark',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '广告状态',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
                        if (value == 0) {
                            return "关闭";
                        }else if (value == 2) {
                            return "开启";
                        }
                        return "";
                    }
				},
                {
                    title : '发布时间',
                    field : 'created',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                }
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});