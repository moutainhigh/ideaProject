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
										projectid : {
						validators : {
							notEmpty : {
								message : "projectid不能为空"
							}
						}
					},
					period : {
						validators : {
							notEmpty : {
								message : "期数不能为空"
							}
						}
					},
					principalmoney : {
						validators : {
							notEmpty : {
								message : "回款本金 不能为空"
							}
						}
					},
					interestmoney : {
						validators : {
							notEmpty : {
								message : "利息不能为空"
							}
						}
					},
					servicemoney : {
						validators : {
							notEmpty : {
								message : "服务费不能为空"
							}
						}
					},
					startdate : {
						validators : {
							notEmpty : {
								message : "开始日期不能为空"
							}
						}
					},
					enddate : {
						validators : {
							notEmpty : {
								message : "结束日期不能为空"
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
					url : _ctx + "/pro/proincomerecord/add.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
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
								loadUrl('/admin/pro/proincomerecordlist')
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
					projectid : {
						validators : {
							notEmpty : {
								message : "projectid不能为空"
							}
						}
					},
					period : {
						validators : {
							notEmpty : {
								message : "期数不能为空"
							}
						}
					},
					principalmoney : {
						validators : {
							notEmpty : {
								message : "回款本金 不能为空"
							}
						}
					},
					interestmoney : {
						validators : {
							notEmpty : {
								message : "利息不能为空"
							}
						}
					},
					servicemoney : {
						validators : {
							notEmpty : {
								message : "服务费不能为空"
							}
						}
					},
					startdate : {
						validators : {
							notEmpty : {
								message : "开始日期不能为空"
							}
						}
					},
					enddate : {
						validators : {
							notEmpty : {
								message : "结束日期不能为空"
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
					url : _ctx + "/pro/proincomerecord/modify.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
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
								loadUrl('/admin/pro/proincomerecordlist')
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
				_table.seeRow($("#table"), _ctx + "/pro/proincomerecord/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proincomerecord/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/pro/proincomerecord/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/pro/proincomerecord/list.do",
				columns : [ {
					field : 'state',
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
					title : 'projectid',
					field : 'projectid',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '期数',
					field : 'period',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '回款本金 ',
					field : 'principalmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '利息',
					field : 'interestmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '服务费',
					field : 'servicemoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '开始日期',
					field : 'startdate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '结束日期',
					field : 'enddate',
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
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});