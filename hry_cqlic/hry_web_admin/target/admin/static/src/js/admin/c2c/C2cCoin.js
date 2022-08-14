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

                    // email : {
                    //     validators : {
                    //         notEmpty : {
                    //             message : "公司邮箱不能为空"
                    //         },
                    //         regexp: {
                    //             regexp:  /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/,
                    //             message: '邮箱格式不正确'
                    //         }
                    //     }
                    // },
                    // mobile : {
                    //     validators : {
                    //         notEmpty : {
                    //             message : "手机号不能为空"
                    //         },
                    //         callback : {
                    //             message: "手机号格式不正确",
                    //             callback: function(value, validator) {
                    //                 return validate.check_mobile(value);
                    //             }
                    //         }
                    //     }
                    // },
                    // password : {
                    //     validators : {
                    //         notEmpty : {
                    //             message : "密码不能为空"
                    //         },
                    //         callback : {
                    //             message: "必须是8-20位英文字母、数字或符号，不能是纯数字或纯字母!",
                    //             callback: function(value, validator) {
                    //                 return validate.check_Password(value);
                    //             }
                    //         }
                    //     }
                    // },
					coinCode : {
						validators : {
							notEmpty : {
								message : "交易币种不能为空"
							}
						}
					},
					isOpen : {
						validators : {
							notEmpty : {
								message : "是否开启不能为空"
							}
						}
					},
					buyPrice : {
						validators : {
							notEmpty : {
								message : "买入价不能为空"
							},
                            callback : {
                                message: "请输入大于0且最多带两位小数的数字",
                                callback: function(value, validator) {
                                    return /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/.test(value);
                                }
                            }
						}
					},
					sellPrice : {
						validators : {
							notEmpty : {
								message : "卖出价不能为空"
							},
							callback : {
                                message: "请输入大于0且最多带两位小数的数字",
                                callback: function(value, validator) {
                                	return /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/.test(value);
                                }
                            }
						}
					},
					minCount : {
						validators : {
							notEmpty : {
								message : "交易最小数量不能为空"
							},

                            callback : {
                                message: "请输入大于0的整数",
                                callback: function(value, validator) {
                                    return /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)?$/.test(value);
                                }
                            }
						}
					},
					maxCount : {
						validators : {
							notEmpty : {
								message : "交易最大数量不能为空"
							},
                            callback : {
                                message: "请输入大于0的整数",
                                callback: function(value, validator) {
                                    return /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)?$/.test(value);
                                }
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
					url : _ctx + "/c2c/c2ccoin/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/c2c/c2ccoinlist')
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
                                message : "交易币种不能为空"
                            }
                        }
                    },
                    isOpen : {
                        validators : {
                            notEmpty : {
                                message : "是否开启不能为空"
                            }
                        }
                    },
                    buyPrice : {
                        validators : {
                            notEmpty : {
                                message : "买入价不能为空"
                            },
                            callback : {
                                message: "请输入大于0且最多带两位小数的数字",
                                callback: function(value, validator) {
                                    return /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/.test(value);
                                }
                            }
                        }
                    },
                    sellPrice : {
                        validators : {
                            notEmpty : {
                                message : "卖出价不能为空"
                            },
                            callback : {
                                message: "请输入大于0且最多带两位小数的数字",
                                callback: function(value, validator) {
                                    return /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/.test(value);
                                }
                            }
                        }
                    },
                    minCount : {
                        validators : {
                            notEmpty : {
                                message : "交易最小数量不能为空"
                            },
                            callback : {
                                message: "请输入大于0的整数",
                                callback: function(value, validator) {
                                    return /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)?$/.test(value);
                                }
                            }
                        }
                    },
                    maxCount : {
                        validators : {
                            notEmpty : {
                                message : "交易最大数量不能为空"
                            },
                            callback : {
                                message: "请输入大于0的整数",
                                callback: function(value, validator) {
                                    return /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)?$/.test(value);
                                }
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
					url : _ctx + "/c2c/c2ccoin/modify",
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
								loadUrl(_ctx+'/v.do?u=/admin/c2c/c2ccoinlist')
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
				_table.seeRow($("#table"), _ctx + "/c2c/c2ccoin/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/c2c/c2ccoin/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
                _table.removeRow($("#table"), _ctx + "/c2c/c2ccoin/remove.do");
            });

            $("#close").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var row = _table.getRowSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    layer.confirm('你确定关闭' + row[0].coinCode + '吗?', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/c2c/c2ccoin/close/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("关闭成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=admin/c2c/c2ccoinlist');
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 1,
                                        })
                                    }
                                }
                            },
                            error: function (e) {

                            }
                        });

                    })
                }else {
                    layer.msg('请选择一条数据', {icon: 2});
                }

            })

            $("#open").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var row = _table.getRowSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    layer.confirm('你确定开启' + row[0].coinCode + '吗?', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/c2c/c2ccoin/open/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("开启成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=admin/c2c/c2ccoinlist');
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 1,
                                        })
                                    }
                                }
                            },
                            error: function (e) {

                            }
                        });

                    })
                }else {
                    layer.msg('请选择一条数据', {icon: 2});
                }

            })

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/c2c/c2ccoin/list.do",
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
					title : '交易币种',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '开启交易',
					field : 'isOpen',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value,row,index) {
                        if(value==1){
                            return "开启";
                        }else {
                            return "未开启";
                        }
                    }
				},
				{
					title : '买入定价',
					field : 'buyPrice',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '卖出定价',
					field : 'sellPrice',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易最小数量',
					field : 'minCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易最大数量',
					field : 'maxCount',
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