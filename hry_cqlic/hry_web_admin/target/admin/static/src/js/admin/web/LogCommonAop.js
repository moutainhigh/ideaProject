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
										userId : {
						validators : {
							notEmpty : {
								message : "userId不能为空"
							}
						}
					},
					userName : {
						validators : {
							notEmpty : {
								message : "userName不能为空"
							}
						}
					},
					ip : {
						validators : {
							notEmpty : {
								message : "ip不能为空"
							}
						}
					},
					name : {
						validators : {
							notEmpty : {
								message : "name不能为空"
							}
						}
					},
					methodName : {
						validators : {
							notEmpty : {
								message : "methodName不能为空"
							}
						}
					},
					args : {
						validators : {
							notEmpty : {
								message : "args不能为空"
							}
						}
					},
					target : {
						validators : {
							notEmpty : {
								message : "target不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "remark不能为空"
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
					url : _ctx + "/web/logcommonaop/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/logcommonaoplist')
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
					userId : {
						validators : {
							notEmpty : {
								message : "userId不能为空"
							}
						}
					},
					userName : {
						validators : {
							notEmpty : {
								message : "userName不能为空"
							}
						}
					},
					ip : {
						validators : {
							notEmpty : {
								message : "ip不能为空"
							}
						}
					},
					name : {
						validators : {
							notEmpty : {
								message : "name不能为空"
							}
						}
					},
					methodName : {
						validators : {
							notEmpty : {
								message : "methodName不能为空"
							}
						}
					},
					args : {
						validators : {
							notEmpty : {
								message : "args不能为空"
							}
						}
					},
					target : {
						validators : {
							notEmpty : {
								message : "target不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "remark不能为空"
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
					url : _ctx + "/web/logcommonaop/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/logcommonaoplist')
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
				_table.seeRow($("#table"), _ctx + "/web/logcommonaop/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/logcommonaop/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/logcommonaop/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					$detail.html(row.args);
				},
				url : _ctx + "/web/logcommonaop/list.do",
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
					title : 'userId',
					field : 'userId',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '用户名',
					field : 'userName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'ip',
					field : 'ip',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '业务名',
					field : 'name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '方法名',
					field : 'methodName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'args',
					field : 'args',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : 'target',
					field : 'target',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '备注',
					field : 'remark',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
		// 前端行为日志
        frontList : function() {

            // 添加页面跳转按钮
            $("#see").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/web/logcommonaop/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/web/logcommonaop/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function() {
                _table.removeRow($("#table"), _ctx + "/web/logcommonaop/remove.do");
            });

            var conf = {

                detail : function(e, index, row, $detail) {
                    $detail.html(row.args);
                },
                url : _ctx + "/web/logcommonaop/list?type=front",
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
                        title : 'userId',
                        field : 'userId',
                        align : 'center',
                        visible : false,
                        sortable : false,
                        searchable : false
                    },
                    {
                        title : '用户名',
                        field : 'userName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : 'ip',
                        field : 'ip',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '业务名',
                        field : 'name',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '方法名',
                        field : 'methodName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : 'args',
                        field : 'args',
                        align : 'center',
                        visible : false,
                        sortable : false,
                        searchable : false
                    },
                    {
                        title : 'target',
                        field : 'target',
                        align : 'center',
                        visible : false,
                        sortable : false,
                        searchable : false
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
                        title: '操作时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
		// 后端行为日志
        backList : function() {

            // 添加页面跳转按钮
            $("#see").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/web/logcommonaop/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/web/logcommonaop/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function() {
                _table.removeRow($("#table"), _ctx + "/web/logcommonaop/remove.do");
            });

            var conf = {

                detail : function(e, index, row, $detail) {
                    $detail.html(row.args);
                },
                url : _ctx + "/web/logcommonaop/list?type=back",
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
                        title : 'userId',
                        field : 'userId',
                        align : 'center',
                        visible : false,
                        sortable : false,
                        searchable : false
                    },
                    {
                        title : '用户名',
                        field : 'userName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : 'ip',
                        field : 'ip',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '业务名',
                        field : 'name',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '方法名',
                        field : 'methodName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : 'args',
                        field : 'args',
                        align : 'center',
                        visible : false,
                        sortable : false,
                        searchable : false
                    },
                    {
                        title : 'target',
                        field : 'target',
                        align : 'center',
                        visible : false,
                        sortable : false,
                        searchable : false
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
                        title: '操作时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        }
	}

});