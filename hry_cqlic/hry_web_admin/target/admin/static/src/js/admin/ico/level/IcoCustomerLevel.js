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
										customer_id : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					level_id : {
						validators : {
							notEmpty : {
								message : "等级id不能为空"
							}
						}
					},
					gradeName : {
						validators : {
							notEmpty : {
								message : "等级名称不能为空"
							}
						}
					},
					experience : {
						validators : {
							notEmpty : {
								message : "经验值不能为空"
							}
						}
					},
					vesion : {
						validators : {
							notEmpty : {
								message : "版本不能为空"
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
					url : _ctx + "/ico/level/icocustomerlevel/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/level/icocustomerlevellist')
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
					customer_id : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					level_id : {
						validators : {
							notEmpty : {
								message : "等级id不能为空"
							}
						}
					},
					gradeName : {
						validators : {
							notEmpty : {
								message : "等级名称不能为空"
							}
						}
					},
					experience : {
						validators : {
							notEmpty : {
								message : "经验值不能为空"
							}
						}
					},
					vesion : {
						validators : {
							notEmpty : {
								message : "版本不能为空"
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
					url : _ctx + "/ico/level/icocustomerlevel/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/level/icocustomerlevellist')
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
				_table.seeRow($("#table"), _ctx + "/ico/level/icocustomerlevel/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/ico/level/icocustomerlevel/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/ico/level/icocustomerlevel/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/ico/level/icocustomerlevel/list.do",
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
					title : '用户id',
					field : 'customer_id',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '等级id',
					field : 'level_id',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '等级名称',
					field : 'gradeName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '经验值',
					field : 'experience',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '版本',
					field : 'vesion',
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