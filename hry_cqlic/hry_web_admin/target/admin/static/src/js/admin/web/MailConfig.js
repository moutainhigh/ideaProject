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
					sort : {
						validators : {
							notEmpty : {
								message : "sort不能为空"
							},
                            between: {
                                min: 0,
                                max: 100,
                                message: '请输入0到100的序号'
                            }
						}
					},

					host : {
						validators : {
							notEmpty : {
								message : "服务器不能为空"
							}
						}
					},
					protocol : {
						validators : {
							notEmpty : {
								message : "协议不能为空"
							}
						}
					},

					emailUser : {
						validators : {
							notEmpty : {
								message : "发送邮箱不能为空"
							},
                            emailAddress: {
                                message: '发送邮箱地址格式有误'
                            }
						}
					},
					agreedPwd : {
						validators : {
							notEmpty : {
								message : "认证密码不能为空"
							}
						}
					},
					customName : {
						validators : {
							notEmpty : {
								message : "发送用户不能为空"
							}
						}
					},

					status : {
						validators : {
							notEmpty : {
								message : "开启状态不能为空"
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
					url : _ctx + "/web/mailconfig/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/mailconfiglist')
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
                    sort : {
                        validators : {
                            notEmpty : {
                                message : "sort不能为空"
                            },
                            between: {
                                min: 0,
                                max: 100,
                                message: '请输入0到100的序号'
                            }
                        }
                    },
                    host : {
                        validators : {
                            notEmpty : {
                                message : "服务器不能为空"
                            }
                        }
                    },
                    protocol : {
                        validators : {
                            notEmpty : {
                                message : "协议不能为空"
                            }
                        }
                    },

                    emailUser : {
                        validators : {
                            notEmpty : {
                                message : "发送邮箱不能为空"
                            },
                            emailAddress: {
                                message: '发送邮箱地址格式有误'
                            }
                        }
                    },
                    agreedPwd : {
                        validators : {
                            notEmpty : {
                                message : "认证密码不能为空"
                            }
                        }
                    },
                    customName : {
                        validators : {
                            notEmpty : {
                                message : "发送用户不能为空"
                            }
                        }
                    },

                    status : {
                        validators : {
                            notEmpty : {
                                message : "开启状态不能为空"
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
					url : _ctx + "/web/mailconfig/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/mailconfiglist')
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
				_table.seeRow($("#table"), _ctx + "/web/mailconfig/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/mailconfig/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/mailconfig/remove.do");
			});
            $("#table_reset").on("click", function() {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function() {
                var params=  $("#table_query_form").serializeJson();
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
				url : _ctx + "/web/mailconfig/list.do",
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
					title : '排序',
					field : 'sort',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '协议',
					field : 'protocol',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '服务器',
					field : 'host',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '端口',
					field : 'port',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '发送用户',
					field : 'customName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '发送人',
					field : 'emailUser',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '认证密码',
					field : 'agreedPwd',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '邮箱标题前缀',
					field : 'prefix',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'auth',
					field : 'auth',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},

				{
					title : 'agreedUser',
					field : 'agreedUser',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},


				{
					title : 'sslflag',
					field : 'sslflag',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : 'starttls',
					field : 'starttls',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},

				{
					title : '启用状态',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(data, type, row) {
                        if(data==1){
                            return "开启";
                        }else{
                            return "关闭";
                        }
                    }
				},
				{
					title : 'saasId',
					field : 'saasId',
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