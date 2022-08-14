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

					trueName : {
						validators : {
							notEmpty : {
								message : "真实姓名不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "类别不能为空"
							}
						}
					},
				/*	price : {
						validators : {
							notEmpty : {
								message : "交易定价不能为空"
							}
						}
					},*/
					changeCoin : {
						validators : {
							notEmpty : {
								message : "交易币种不能为空"
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
                $(this).addClass("disabled","disabled")
				var options = {
					url : _ctx + "/c2c/appbusinessman/add",
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
								loadUrl(_ctx+'/v.do?u=/admin/c2c/appbusinessmanlist')
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

                    trueName : {
                        validators : {
                            notEmpty : {
                                message : "真实姓名不能为空"
                            }
                        }
                    },
                    type : {
                        validators : {
                            notEmpty : {
                                message : "类别不能为空"
                            }
                        }
                    },
                   /* price : {
                        validators : {
                            notEmpty : {
                                message : "交易定价不能为空"
                            }
                        }
                    },*/
                    changeCoin : {
                        validators : {
                            notEmpty : {
                                message : "交易币种不能为空"
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
					url : _ctx + "/c2c/appbusinessman/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/c2c/appbusinessmanlist')
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

			$('#lock').on('click',function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要禁用该交易商吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "post",
                            url: _ctx + "/c2c/appbusinessman/lock",
                            data: {
                                isLock:1,
                                id:ids[0]
							},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                	debugger
                                    if (data.success) {
                                        layer.msg("禁用成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx+'/v.do?u=/admin/c2c/appbusinessmanlist')
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 2,
                                        })
                                    }

                                }
                            },
                            error: function (e) {

                            }
                        });

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            $('#unlock').on('click',function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要解除禁用吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "post",
                            url: _ctx + "/c2c/appbusinessman/lock" ,
                            data: {
                            	id:ids[0],
                                isLock:0
                            },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    debugger
                                    if (data.success) {
                                        layer.msg("解禁成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx+'/v.do?u=/admin/c2c/appbusinessmanlist')
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 2,
                                        })
                                    }

                                }
                            },
                            error: function (e) {

                            }
                        });

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/c2c/appbusinessman/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/c2c/appbusinessman/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/c2c/appbusinessman/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/c2c/appbusinessman/list.do",
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
					title : '真实姓名',
					field : 'trueName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '商家类型',
					field : 'type',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
						if(value==0){
							return "A类";
						}else {
							return "B类";
						}
					}
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
					title : '交易币种',
					field : 'changeCoin',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '是否禁用',
					field : 'isLock',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if(value==0){
                            return "未禁用";
                        }else {
                            return "已禁用";
                        }
                    }
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});