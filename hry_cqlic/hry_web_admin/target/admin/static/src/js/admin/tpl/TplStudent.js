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
								message : "mkey不能为空"
							}
						}
					},
					pkey : {
						validators : {
							notEmpty : {
								message : "pkey不能为空"
							}
						}
					},
					name : {
						validators : {
							notEmpty : {
								message : "名称不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "文件类型不能为空"
							}
						}
					},
					value : {
						validators : {
							notEmpty : {
								message : "内容不能为空"
							}
						}
					},
					path : {
						validators : {
							notEmpty : {
								message : "path不能为空"
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
					url : _ctx + "/tpl/tplstudent/add.do",
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
								loadUrl('/admin/tpl/tplstudentlist')
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
								message : "mkey不能为空"
							}
						}
					},
					pkey : {
						validators : {
							notEmpty : {
								message : "pkey不能为空"
							}
						}
					},
					name : {
						validators : {
							notEmpty : {
								message : "名称不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "文件类型不能为空"
							}
						}
					},
					value : {
						validators : {
							notEmpty : {
								message : "内容不能为空"
							}
						}
					},
					path : {
						validators : {
							notEmpty : {
								message : "path不能为空"
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
					url : _ctx + "/tpl/tplstudent/modify.do",
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
								loadUrl('/admin/tpl/tplstudentlist')
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
				_table.seeRow($("#table"), _ctx + "/tpl/tplstudent/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/tpl/tplstudent/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/tpl/tplstudent/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/tpl/tplstudent/list.do",
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
					title : 'mkey',
					field : 'mkey',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'pkey',
					field : 'pkey',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '名称',
					field : 'name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '文件类型',
					field : 'type',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '内容',
					field : 'value',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'path',
					field : 'path',
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