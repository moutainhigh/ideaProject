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
										taskName : {
						validators : {
							notEmpty : {
								message : "任务名称不能为空"
							}
						}
					},
					taskTypeId : {
						validators : {
							notEmpty : {
								message : "任务属性id不能为空"
							}
						}
					},
					taskTypeName : {
						validators : {
							notEmpty : {
								message : "任务属性名称不能为空"
							}
						}
					},
					isOpen : {
						validators : {
							notEmpty : {
								message : "是否公开 1 是，2 否不能为空"
							}
						}
					},
					taskGrade : {
						validators : {
							notEmpty : {
								message : "任务级别 1 一般，2 紧急不能为空"
							}
						}
					},
					initiatorId : {
						validators : {
							notEmpty : {
								message : "发起人id不能为空"
							}
						}
					},
					initiatorName : {
						validators : {
							notEmpty : {
								message : "发起人姓名不能为空"
							}
						}
					},
					undertakerIds : {
						validators : {
							notEmpty : {
								message : "承办人id集合（用逗号分隔）不能为空"
							}
						}
					},
					undertakerNames : {
						validators : {
							notEmpty : {
								message : "承办人姓名（用逗号分隔）不能为空"
							}
						}
					},
					copyIds : {
						validators : {
							notEmpty : {
								message : "抄送人id集合（用逗号分隔）不能为空"
							}
						}
					},
					copyNames : {
						validators : {
							notEmpty : {
								message : "抄送人姓名集合（用逗号分隔）不能为空"
							}
						}
					},
					taskStartDate : {
						validators : {
							notEmpty : {
								message : "任务计划开始时间不能为空"
							}
						}
					},
					taskEndDate : {
						validators : {
							notEmpty : {
								message : "任务计划结束时间不能为空"
							}
						}
					},
					taskContent : {
						validators : {
							notEmpty : {
								message : "任务内容不能为空"
							}
						}
					},
					taskAffix : {
						validators : {
							notEmpty : {
								message : "任务附件不能为空"
							}
						}
					},
					taskPlanContent : {
						validators : {
							notEmpty : {
								message : "任务完成进度内容不能为空"
							}
						}
					},
					taskPlanAffix : {
						validators : {
							notEmpty : {
								message : "任务完成进度附件不能为空"
							}
						}
					},
					taskState : {
						validators : {
							notEmpty : {
								message : "任务状态 0 未分配 1 待办 2 承办 3 已办（未确认）4 任务结束（已确认）不能为空"
							}
						}
					},
					isDel : {
						validators : {
							notEmpty : {
								message : "是否删除 0 否 1 是不能为空"
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
					url : _ctx + "/xzsn/task/task/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/xzsn/task/tasklist')
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
					taskName : {
						validators : {
							notEmpty : {
								message : "任务名称不能为空"
							}
						}
					},
					taskTypeId : {
						validators : {
							notEmpty : {
								message : "任务属性id不能为空"
							}
						}
					},
					taskTypeName : {
						validators : {
							notEmpty : {
								message : "任务属性名称不能为空"
							}
						}
					},
					isOpen : {
						validators : {
							notEmpty : {
								message : "是否公开 1 是，2 否不能为空"
							}
						}
					},
					taskGrade : {
						validators : {
							notEmpty : {
								message : "任务级别 1 一般，2 紧急不能为空"
							}
						}
					},
					initiatorId : {
						validators : {
							notEmpty : {
								message : "发起人id不能为空"
							}
						}
					},
					initiatorName : {
						validators : {
							notEmpty : {
								message : "发起人姓名不能为空"
							}
						}
					},
					undertakerIds : {
						validators : {
							notEmpty : {
								message : "承办人id集合（用逗号分隔）不能为空"
							}
						}
					},
					undertakerNames : {
						validators : {
							notEmpty : {
								message : "承办人姓名（用逗号分隔）不能为空"
							}
						}
					},
					copyIds : {
						validators : {
							notEmpty : {
								message : "抄送人id集合（用逗号分隔）不能为空"
							}
						}
					},
					copyNames : {
						validators : {
							notEmpty : {
								message : "抄送人姓名集合（用逗号分隔）不能为空"
							}
						}
					},
					taskStartDate : {
						validators : {
							notEmpty : {
								message : "任务计划开始时间不能为空"
							}
						}
					},
					taskEndDate : {
						validators : {
							notEmpty : {
								message : "任务计划结束时间不能为空"
							}
						}
					},
					taskContent : {
						validators : {
							notEmpty : {
								message : "任务内容不能为空"
							}
						}
					},
					taskAffix : {
						validators : {
							notEmpty : {
								message : "任务附件不能为空"
							}
						}
					},
					taskPlanContent : {
						validators : {
							notEmpty : {
								message : "任务完成进度内容不能为空"
							}
						}
					},
					taskPlanAffix : {
						validators : {
							notEmpty : {
								message : "任务完成进度附件不能为空"
							}
						}
					},
					taskState : {
						validators : {
							notEmpty : {
								message : "任务状态 0 未分配 1 待办 2 承办 3 已办（未确认）4 任务结束（已确认）不能为空"
							}
						}
					},
					isDel : {
						validators : {
							notEmpty : {
								message : "是否删除 0 否 1 是不能为空"
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
					url : _ctx + "/xzsn/task/task/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/xzsn/task/tasklist')
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
				_table.seeRow($("#table"), _ctx + "/xzsn/task/task/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/xzsn/task/task/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/xzsn/task/task/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/xzsn/task/task/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '主键id',
					field : 'id',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务名称',
					field : 'taskName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务属性id',
					field : 'taskTypeId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务属性名称',
					field : 'taskTypeName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '是否公开 1 是，2 否',
					field : 'isOpen',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务级别 1 一般，2 紧急',
					field : 'taskGrade',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '发起人id',
					field : 'initiatorId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '发起人姓名',
					field : 'initiatorName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '承办人id集合（用逗号分隔）',
					field : 'undertakerIds',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '承办人姓名（用逗号分隔）',
					field : 'undertakerNames',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '抄送人id集合（用逗号分隔）',
					field : 'copyIds',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '抄送人姓名集合（用逗号分隔）',
					field : 'copyNames',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务计划开始时间',
					field : 'taskStartDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务计划结束时间',
					field : 'taskEndDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务内容',
					field : 'taskContent',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务附件',
					field : 'taskAffix',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务完成进度内容',
					field : 'taskPlanContent',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务完成进度附件',
					field : 'taskPlanAffix',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '任务状态 0 未分配 1 待办 2 承办 3 已办（未确认）4 任务结束（已确认）',
					field : 'taskState',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '是否删除 0 否 1 是',
					field : 'isDel',
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