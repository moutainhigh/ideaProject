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
					ip : {
						validators : {
							notEmpty : {
								message : "ip不能为空"
							}
						}
					},
					url : {
						validators : {
							notEmpty : {
								message : "url不能为空"
							}
						}
					},
					account : {
						validators : {
							notEmpty : {
								message : "account不能为空"
							}
						}
					},
					params : {
						validators : {
							notEmpty : {
								message : "params不能为空"
							}
						}
					},
					result : {
						validators : {
							notEmpty : {
								message : "result不能为空"
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
					url : _ctx + "/exchange/applogthirdinterface/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/applogthirdinterfacelist')
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
					ip : {
						validators : {
							notEmpty : {
								message : "ip不能为空"
							}
						}
					},
					url : {
						validators : {
							notEmpty : {
								message : "url不能为空"
							}
						}
					},
					account : {
						validators : {
							notEmpty : {
								message : "account不能为空"
							}
						}
					},
					params : {
						validators : {
							notEmpty : {
								message : "params不能为空"
							}
						}
					},
					result : {
						validators : {
							notEmpty : {
								message : "result不能为空"
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
					url : _ctx + "/exchange/applogthirdinterface/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/applogthirdinterfacelist')
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
				_table.seeRow($("#table"), _ctx + "/exchange/applogthirdinterface/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/applogthirdinterface/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/exchange/applogthirdinterface/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/exchange/applogthirdinterface/list.do",
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
					title : 'saasId',
					field : 'saasId',
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
					title : 'url',
					field : 'url',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'account',
					field : 'account',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'params',
					field : 'params',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'result',
					field : 'result',
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