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
										customerId : {
						validators : {
							notEmpty : {
								message : "用户Id不能为空"
							}
						}
					},
					customerLevelId : {
						validators : {
							notEmpty : {
								message : "用户等级Id不能为空"
							}
						}
					},
					teamLevelId : {
						validators : {
							notEmpty : {
								message : "社区等级Id不能为空"
							}
						}
					},
					teamLevelName : {
						validators : {
							notEmpty : {
								message : "社区等级名称不能为空"
							}
						}
					},
					teamSort : {
						validators : {
							notEmpty : {
								message : "社区等级排序不能为空"
							}
						}
					},
					startTeamAward : {
						validators : {
							notEmpty : {
								message : "起始团队奖励不能为空"
							}
						}
					},
					startTime : {
						validators : {
							notEmpty : {
								message : "奖励开始时间不能为空"
							}
						}
					},
					endTime : {
						validators : {
							notEmpty : {
								message : "奖励结束时间不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
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
					url : _ctx + "/licqb/record/releaseruledetails/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/record/releaseruledetailslist')
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
					customerId : {
						validators : {
							notEmpty : {
								message : "用户Id不能为空"
							}
						}
					},
					customerLevelId : {
						validators : {
							notEmpty : {
								message : "用户等级Id不能为空"
							}
						}
					},
					teamLevelId : {
						validators : {
							notEmpty : {
								message : "社区等级Id不能为空"
							}
						}
					},
					teamLevelName : {
						validators : {
							notEmpty : {
								message : "社区等级名称不能为空"
							}
						}
					},
					teamSort : {
						validators : {
							notEmpty : {
								message : "社区等级排序不能为空"
							}
						}
					},
					startTeamAward : {
						validators : {
							notEmpty : {
								message : "起始团队奖励不能为空"
							}
						}
					},
					startTime : {
						validators : {
							notEmpty : {
								message : "奖励开始时间不能为空"
							}
						}
					},
					endTime : {
						validators : {
							notEmpty : {
								message : "奖励结束时间不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
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
					url : _ctx + "/licqb/record/releaseruledetails/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/record/releaseruledetailslist')
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
				_table.seeRow($("#table"), _ctx + "/licqb/record/releaseruledetails/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/licqb/record/releaseruledetails/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/licqb/record/releaseruledetails/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/licqb/record/releaseruledetails/list.do",
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
					title : '用户Id',
					field : 'customerId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '用户等级Id',
					field : 'customerLevelId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '社区等级Id',
					field : 'teamLevelId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '社区等级名称',
					field : 'teamLevelName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '社区等级排序',
					field : 'teamSort',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '起始团队奖励',
					field : 'startTeamAward',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '奖励开始时间',
					field : 'startTime',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '奖励结束时间',
					field : 'endTime',
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
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});