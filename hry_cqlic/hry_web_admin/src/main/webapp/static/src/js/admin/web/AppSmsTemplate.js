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
					mkey : {
						validators : {
							notEmpty : {
								message : "模板KEY不能为空"
							}
						}
					},
					name : {
						validators : {
							notEmpty : {
								message : "模板名称不能为空"
							}
						}
					},
					describes : {
						validators : {
							notEmpty : {
								message : "模板内容不能为空"
							}
						}
					},
                    messageCategory : {
                        validators : {
                            notEmpty : {
                                message : "请选择语种"
                            }
                        }
                    }
					/*remark : {
						validators : {
							notEmpty : {
								message : "模板备注不能为空"
							}
						}
					},*/
					/*isOpen : {
						validators : {
							notEmpty : {
								message : "是否开启（0表示否 1表示是）不能为空"
							}
						}
					},*/
					/*saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},*/
					/*messageCategory : {
						validators : {
							notEmpty : {
								message : "信息类别-存放系统语种的值不能为空"
							}
						}
					}*/
				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			});
			// 添加提交
			$("#addSubmit").on("click", function() {
				var options = {
					url : _ctx + "/web/appsmstemplate/add.do",
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
								loadUrl(_ctx+'/language.do?u=/admin/web/appsmstemplatelist')
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
                    mkey : {
                        validators : {
                            notEmpty : {
                                message : "模板KEY不能为空"
                            }
                        }
                    },
                    name : {
                        validators : {
                            notEmpty : {
                                message : "模板名称不能为空"
                            }
                        }
                    },
                    describes : {
                        validators : {
                            notEmpty : {
                                message : "模板内容不能为空"
                            }
                        }
                    },
                    messageCategory : {
                        validators : {
                            notEmpty : {
                                message : "请选择语种"
                            }
                        }
                    }
                    /*remark : {
                        validators : {
                            notEmpty : {
                                message : "模板备注不能为空"
                            }
                        }
                    },*/
                    /*isOpen : {
                        validators : {
                            notEmpty : {
                                message : "是否开启（0表示否 1表示是）不能为空"
                            }
                        }
                    },*/
                    /*saasId : {
                        validators : {
                            notEmpty : {
                                message : "saasId不能为空"
                            }
                        }
                    },*/
                    /*messageCategory : {
                        validators : {
                            notEmpty : {
                                message : "信息类别-存放系统语种的值不能为空"
                            }
                        }
                    }*/
                },
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			});
			// 修改提交
			$("#modifySubmit").on("click", function() {
				var options = {
					url : _ctx + "/web/appsmstemplate/modify.do",
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
								loadUrl(_ctx+'/language.do?u=/admin/web/appsmstemplatelist')
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
				_table.seeRow($("#table"), _ctx + "/web/appsmstemplate/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/appsmstemplate/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/appsmstemplate/remove.do");
			});
            // 开启按钮
            $("#open_btn").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if (ids.length == 0) {
                    layer.msg('请选择数据', {icon : 2});
                    return false;
                } else {
                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url: _ctx + '/web/appsmstemplate/switchOpen/' + ids,
                        data:{
                            "type" : "open"
                        },
                        cache: false,
                        traditional: true,
                        success: function (data) {
                            if (data.success) {
                                layer.msg('开启成功', {icon : 1});
                                loadUrl(_ctx+'/language.do?u=admin/web/appsmstemplatelist');
                            } else {
                                layer.msg(data.msg, {icon : 2});
                            }
                        }
                    });

                }
            });
            // 关闭按钮
            $("#close_btn").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if (ids.length == 0) {
                    layer.msg('请选择数据', {icon : 2});
                    return false;
                } else {
                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url: _ctx + '/web/appsmstemplate/switchOpen/' + ids,
                        data:{
                            "type" : "close"
                        },
                        cache: false,
                        traditional: true,
                        success: function (data) {
                            if (data.success) {
                                layer.msg('关闭成功', {icon : 1});
                                loadUrl(_ctx+'/language.do?u=admin/web/appsmstemplatelist');
                            } else {
                                layer.msg(data.msg, {icon : 2});
                            }
                        }
                    });
                }
            });
            //语种切换查询
            $(".languageQuery").on("click", function() {
                var languageDic=$(this).attr("languageType");
                var params=  {messageCategory :languageDic};
                $("#languageDic").val(languageDic);
                //查询
                _table.tableQuery($("#table"),params);
            });
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/appsmstemplate/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '语种',
					field : 'messageCategory',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '模板KEY',
					field : 'mkey',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '模板名称',
					field : 'name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '模板内容',
					field : 'describes',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '模板备注',
					field : 'remark',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '开关',
					field : 'isOpen',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, row, index) {
						if(value == 0){
							return "关闭"
						}if(value == 1){
							return "开启"
						}
					}
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});