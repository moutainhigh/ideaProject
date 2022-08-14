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
										coinCode : {
						validators : {
							notEmpty : {
								message : "交易对不能为空"
							}
						}
					},
					period : {
						validators : {
							notEmpty : {
								message : "周期不能为空"
							}
						}
					},
					openPrice : {
						validators : {
							notEmpty : {
								message : "开盘价不能为空"
							}
						}
					},
					closePrice : {
						validators : {
							notEmpty : {
								message : "收盘价不能为空"
							}
						}
					},
					highPrice : {
						validators : {
							notEmpty : {
								message : "最高价不能为空"
							}
						}
					},
					lowPrice : {
						validators : {
							notEmpty : {
								message : "最低价不能为空"
							}
						}
					},
					time : {
						validators : {
							notEmpty : {
								message : "时间不能为空"
							}
						}
					},
					timelong : {
						validators : {
							notEmpty : {
								message : "时间戳不能为空"
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
					url : _ctx + "/exchange/klinedata/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/klinedatalist')
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
                    coinCode : {
                        validators : {
                            notEmpty : {
                                message : "交易对不能为空"
                            }
                        }
                    },
                    period : {
                        validators : {
                            notEmpty : {
                                message : "周期不能为空"
                            }
                        }
                    },
                    openPrice : {
                        validators : {
                            notEmpty : {
                                message : "开盘价不能为空"
                            }
                        }
                    },
                    closePrice : {
                        validators : {
                            notEmpty : {
                                message : "收盘价不能为空"
                            }
                        }
                    },
                    highPrice : {
                        validators : {
                            notEmpty : {
                                message : "最高价不能为空"
                            }
                        }
                    },
                    lowPrice : {
                        validators : {
                            notEmpty : {
                                message : "最低价不能为空"
                            }
                        }
                    },
                    time : {
                        validators : {
                            notEmpty : {
                                message : "时间不能为空"
                            }
                        }
                    },
                    timelong : {
                        validators : {
                            notEmpty : {
                                message : "时间戳不能为空"
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
					url : _ctx + "/exchange/klinedata/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/klinedatalist')
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
				_table.seeRow($("#table"), _ctx + "/exchange/klinedata/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/klinedata/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/exchange/klinedata/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/exchange/klinedata/list.do",
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
					title : '交易对',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '周期',
					field : 'period',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '开盘价',
					field : 'openPrice',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '收盘价',
					field : 'closePrice',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '最高价',
					field : 'highPrice',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '最低价',
					field : 'lowPrice',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '时间',
					field : 'time',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '时间戳',
					field : 'timelong',
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