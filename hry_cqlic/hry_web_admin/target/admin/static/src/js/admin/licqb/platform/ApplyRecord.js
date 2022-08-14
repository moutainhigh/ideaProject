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
										applyId : {
						validators : {
							notEmpty : {
								message : "社区奖励申请Id不能为空"
							}
						}
					},
					customerId : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					email : {
						validators : {
							notEmpty : {
								message : "用户邮箱不能为空"
							}
						}
					},
					mobliePhone : {
						validators : {
							notEmpty : {
								message : "用户手机号不能为空"
							}
						}
					},
					socialType : {
						validators : {
							notEmpty : {
								message : "社交类型: 1 QQ 2 微信 3 facebook不能为空"
							}
						}
					},
					socialAccount : {
						validators : {
							notEmpty : {
								message : "社交账户不能为空"
							}
						}
					},
					socialGroupImg : {
						validators : {
							notEmpty : {
								message : "社交群图片不能为空"
							}
						}
					},
					applyStaus : {
						validators : {
							notEmpty : {
								message : "申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成不能为空"
							}
						}
					},
					auditStaus : {
						validators : {
							notEmpty : {
								message : "审核状态：1 申请通过 2申请拒绝不能为空"
							}
						}
					},
					auditExplain : {
						validators : {
							notEmpty : {
								message : "审核说明不能为空"
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
					url : _ctx + "/licqb/platform/applyrecord/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/platform/applyrecordlist')
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
					applyId : {
						validators : {
							notEmpty : {
								message : "社区奖励申请Id不能为空"
							}
						}
					},
					customerId : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					email : {
						validators : {
							notEmpty : {
								message : "用户邮箱不能为空"
							}
						}
					},
					mobliePhone : {
						validators : {
							notEmpty : {
								message : "用户手机号不能为空"
							}
						}
					},
					socialType : {
						validators : {
							notEmpty : {
								message : "社交类型: 1 QQ 2 微信 3 facebook不能为空"
							}
						}
					},
					socialAccount : {
						validators : {
							notEmpty : {
								message : "社交账户不能为空"
							}
						}
					},
					socialGroupImg : {
						validators : {
							notEmpty : {
								message : "社交群图片不能为空"
							}
						}
					},
					applyStaus : {
						validators : {
							notEmpty : {
								message : "申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成不能为空"
							}
						}
					},
					auditStaus : {
						validators : {
							notEmpty : {
								message : "审核状态：1 申请通过 2申请拒绝不能为空"
							}
						}
					},
					auditExplain : {
						validators : {
							notEmpty : {
								message : "审核说明不能为空"
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
					url : _ctx + "/licqb/platform/applyrecord/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/platform/applyrecordlist')
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
				_table.seeRow($("#table"), _ctx + "/licqb/platform/applyrecord/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/licqb/platform/applyrecord/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/licqb/platform/applyrecord/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/licqb/platform/applyrecord/list.do",
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
					title : '社区奖励申请Id',
					field : 'applyId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '用户id',
					field : 'customerId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '用户邮箱',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '用户手机号',
					field : 'mobliePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '社交类型: 1 QQ 2 微信 3 facebook',
					field : 'socialType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '社交账户',
					field : 'socialAccount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '社交群图片',
					field : 'socialGroupImg',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成',
					field : 'applyStaus',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '审核状态：1 申请通过 2申请拒绝',
					field : 'auditStaus',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '审核说明',
					field : 'auditExplain',
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