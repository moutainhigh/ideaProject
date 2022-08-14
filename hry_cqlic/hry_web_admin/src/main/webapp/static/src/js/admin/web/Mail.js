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
										saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					title : {
						validators : {
							notEmpty : {
								message : "title不能为空"
							}
						}
					},
					content : {
						validators : {
							notEmpty : {
								message : "content不能为空"
							}
						}
					},
					address : {
						validators : {
							notEmpty : {
								message : "address不能为空"
							}
						}
					},
					errorCode : {
						validators : {
							notEmpty : {
								message : "errorCode不能为空"
							}
						}
					},
					errorContent : {
						validators : {
							notEmpty : {
								message : "errorContent不能为空"
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
					url : _ctx + "/web/mail/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/maillist')
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
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					title : {
						validators : {
							notEmpty : {
								message : "title不能为空"
							}
						}
					},
					content : {
						validators : {
							notEmpty : {
								message : "content不能为空"
							}
						}
					},
					address : {
						validators : {
							notEmpty : {
								message : "address不能为空"
							}
						}
					},
					errorCode : {
						validators : {
							notEmpty : {
								message : "errorCode不能为空"
							}
						}
					},
					errorContent : {
						validators : {
							notEmpty : {
								message : "errorContent不能为空"
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
					url : _ctx + "/web/mail/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/maillist')
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
			// 重新发送按钮
			$("#resend").on("click", function() {
                var arrId = _table.getIdSelects($("#table"));
                if (arrId.length == 0) {
                    layer.msg('请选择数据!', {icon : 2});
                    return false;
                }
                var ids = arrId.join(",");
                layer.confirm("你确定要重发吗？", {
                    btn: ['确定', '取消'], // 按钮
                    ids: ids
                }, function () {
                    layer.closeAll();

                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url: _ctx + "/web/mail/reSend/" + ids,
                        cache: false,
                        success: function (data) {
                            if (data != undefined) {
                                if (data.success) {
                                    layer.msg('重发成功!', {icon : 1});
                                    loadUrl(_ctx+'/v.do?u=/admin/web/maillist')
                                } else {
                                    layer.msg(data.msg, {icon : 2});
                                }
                            }
                        }
                    });
                });
			});
			// 删除按钮
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/mail/remove.do");
			});

			var conf = {
				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/mail/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '时间',
					field : 'created',
					align : 'left',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '邮件标题',
					field : 'title',
					align : 'left',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '邮件内容',
					field : 'content',
					align : 'left',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '接收人',
					field : 'address',
					align : 'left',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '是否成功',
					field : 'errorCode',
					align : 'left',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '错误内容',
					field : 'errorContent',
					align : 'left',
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