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
					customerId : {
						validators : {
							notEmpty : {
								message : "用户不能为空"
							}
						}
					},
					code : {
						validators : {
							notEmpty : {
								message : "交易币种不能为空"
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
					count : {
						validators : {
							notEmpty : {
								message : "操作数量不能为空"
							}
						}
					},
					beforeCount : {
						validators : {
							notEmpty : {
								message : "交易前币余额不能为空"
							}
						}
					},
					afterCount : {
						validators : {
							notEmpty : {
								message : "交易后币余额不能为空"
							}
						}
					},
					beforeColdCount : {
						validators : {
							notEmpty : {
								message : "交易前冻结余额不能为空"
							}
						}
					},
					afterColdCount : {
						validators : {
							notEmpty : {
								message : "交易后冻结余额不能为空"
							}
						}
					},
					remark1 : {
						validators : {
							notEmpty : {
								message : "remark1不能为空"
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
					url : _ctx + "/lend/exlendaccountrecord/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/lend/exlendaccountrecordlist')
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
					customerId : {
						validators : {
							notEmpty : {
								message : "用户不能为空"
							}
						}
					},
					code : {
						validators : {
							notEmpty : {
								message : "交易币种不能为空"
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
					count : {
						validators : {
							notEmpty : {
								message : "操作数量不能为空"
							}
						}
					},
					beforeCount : {
						validators : {
							notEmpty : {
								message : "交易前币余额不能为空"
							}
						}
					},
					afterCount : {
						validators : {
							notEmpty : {
								message : "交易后币余额不能为空"
							}
						}
					},
					beforeColdCount : {
						validators : {
							notEmpty : {
								message : "交易前冻结余额不能为空"
							}
						}
					},
					afterColdCount : {
						validators : {
							notEmpty : {
								message : "交易后冻结余额不能为空"
							}
						}
					},
					remark1 : {
						validators : {
							notEmpty : {
								message : "remark1不能为空"
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
					url : _ctx + "/lend/exlendaccountrecord/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/lend/exlendaccountrecordlist')
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
				_table.seeRow($("#table"), _ctx + "/lend/exlendaccountrecord/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/lend/exlendaccountrecord/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/lend/exlendaccountrecord/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/lend/exlendaccountrecord/list.do",
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
					visible : true,
					sortable : false,
					searchable : true
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
					title : '用户',
					field : 'customerId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易币种',
					field : 'code',
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
					title : '操作数量',
					field : 'count',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易前币余额',
					field : 'beforeCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易后币余额',
					field : 'afterCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易前冻结余额',
					field : 'beforeColdCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易后冻结余额',
					field : 'afterColdCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'remark1',
					field : 'remark1',
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