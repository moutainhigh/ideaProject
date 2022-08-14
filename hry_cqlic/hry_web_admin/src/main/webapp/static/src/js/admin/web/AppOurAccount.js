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
                    currencyType : {
                        validators : {
                            notEmpty : {
                                message : "请选择账户类别"
                            }
                        }
                    },
                    bankName : {
                        validators : {
                            notEmpty : {
                                message : "请选择所属银行"
                            }
                        }
                    },
                    openAccountType : {
						validators : {
							notEmpty : {
								message : "请选择开户类型"
							}
						}
					},
                    isShow : {
						validators : {
							notEmpty : {
								message : "请选择是否主账户"
							}
						}
					},
                    accountType : {
						validators : {
							notEmpty : {
								message : "请选择账户类型"
							}
						}
					},
                    accountNumber : {
						validators : {
							notEmpty : {
								message : "银行卡号不能为空"
							}
						}
					},
                    accountName : {
						validators : {
							notEmpty : {
								message : "持卡人姓名不能为空"
							}
						}
					},
                    bankAddress : {
						validators : {
							notEmpty : {
								message : "开户行支行不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "备注不能为空"
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
					url : _ctx + "/web/appouraccount/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appouraccountlist')
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
                    currencyType : {
                        validators : {
                            notEmpty : {
                                message : "请选择账户类别"
                            }
                        }
                    },
                    bankName : {
                        validators : {
                            notEmpty : {
                                message : "请选择所属银行"
                            }
                        }
                    },
                    openAccountType : {
                        validators : {
                            notEmpty : {
                                message : "请选择开户类型"
                            }
                        }
                    },
                    isShow : {
                        validators : {
                            notEmpty : {
                                message : "请选择是否主账户"
                            }
                        }
                    },
                    accountType : {
                        validators : {
                            notEmpty : {
                                message : "请选择账户类型"
                            }
                        }
                    },
                    accountNumber : {
                        validators : {
                            notEmpty : {
                                message : "银行卡号不能为空"
                            }
                        }
                    },
                    accountName : {
                        validators : {
                            notEmpty : {
                                message : "持卡人姓名不能为空"
                            }
                        }
                    },
                    bankAddress : {
                        validators : {
                            notEmpty : {
                                message : "开户行支行不能为空"
                            }
                        }
                    },
                    remark : {
                        validators : {
                            notEmpty : {
                                message : "备注不能为空"
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
					url : _ctx + "/web/appouraccount/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appouraccountlist')
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
				_table.seeRow($("#table"), _ctx + "/web/appouraccount/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/appouraccount/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/appouraccount/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/appouraccount/list.do",
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
					searchable : true
				},
				{
					title : '账户类型',
					field : 'accountType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if (value == "0") {
                            return "银行账户"
                        }
                        return "支付宝";
                    }
				},
				{
					title : '账户号',
					field : 'accountNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '开户类型',
					field : 'openAccountType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if (value == "0") {
                            return "企业"
                        }
                        return "个人";
                    }
				},
				{
					title : '开户名称',
					field : 'accountName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '总账户余额',
					field : 'accountMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '所属站点',
					field : 'website',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if (value == "0") {
                            return "其他"
                        }
                        return "中国站";
                    }
				},
				{
					title : '是否主账户',
					field : 'isShow',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if (value == "0") {
                            return "否"
                        }
                        return "是";
                    }
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});