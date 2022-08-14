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
										gradeName : {
						validators : {
							notEmpty : {
								message : "等级名称不能为空"
							}
						}
					},
					conditionPara : {
						validators : {
							notEmpty : {
								message : "条件值设定"
							},regexp: {
                                regexp: /^[0-9]*$/,
                                message: '条件值设定参数错误'
                            }
						}
					},
					additionRatio : {
						validators : {
							notEmpty : {
								message : "加成比例不能为空"
							},  regexp: {
                                regexp: /^\d+(\.\d+)?$/,
                                message: '加成比例参数错误'
                            }
						}
					},
					sort : {
						validators : {
							notEmpty : {
								message : "等级排序不能为空"
							}, regexp: {
								regexp: /^\d+(\.\d+)?$/,
								message: '等级排序参数错误'
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
					url : _ctx + "/ico/rulesconfig/icoupgradeconfig/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/rulesconfig/icoupgradeconfiglist')
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
					gradeName : {
						validators : {
							notEmpty : {
								message : "等级名称不能为空"
							}
						}
					},
					conditionPara : {
						validators : {
							notEmpty : {
								message : "条件值设定不能为空"
							}, regexp: {
                                regexp: /^[0-9]*$/,
                                message: '条件值设定参数错误'
                            }
						}
					},
					additionRatio : {
						validators : {
							notEmpty : {
								message : "加成比例不能为空"
							}, regexp: {
                                regexp: /^\d+(\.\d+)?$/,
                                message: '加成比例参数错误'
                            }
						}
					},
					sort : {
						validators : {
							notEmpty : {
								message : "等级排序不能为空"
							}, regexp: {
								regexp: /^\d+(\.\d+)?$/,
								message: '等级排序参数错误'
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
					url : _ctx + "/ico/rulesconfig/icoupgradeconfig/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/rulesconfig/icoupgradeconfiglist')
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
				_table.seeRow($("#table"), _ctx + "/ico/rulesconfig/icoupgradeconfig/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/ico/rulesconfig/icoupgradeconfig/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/ico/rulesconfig/icoupgradeconfig/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/ico/rulesconfig/icoupgradeconfig/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
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
					title : '条件值设定（大于等于)',
					field : 'conditionPara',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
						title : '加成比例(%)',
						field : 'additionRatio',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '等级排序',
						field : 'sort',
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