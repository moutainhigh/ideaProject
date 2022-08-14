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
					accountId : {
						validators : {
							notEmpty : {
								message : "币账户Id不能为空"
							}
						}
					},
					serialNumber : {
						validators : {
							notEmpty : {
								message : "流水号不能为空"
							}
						}
					},
					accountType : {
						validators : {
							notEmpty : {
								message : "账户类型 1.热账户 2.冷账户不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种Code不能为空"
							}
						}
					},
					changeCount : {
						validators : {
							notEmpty : {
								message : "交易量不能为空"
							}
						}
					},
					changeType : {
						validators : {
							notEmpty : {
								message : "变动类型 1加 2减不能为空"
							}
						}
					},
					triggerPoint : {
						validators : {
							notEmpty : {
								message : "触发点不能为空"
							}
						}
					},
					triggerSerialNumber : {
						validators : {
							notEmpty : {
								message : "触发点流水号不能为空"
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
					url : _ctx + "/klg/assetsrecord/klgassetsrecord/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/assetsrecord/klgassetsrecordlist')
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
					accountId : {
						validators : {
							notEmpty : {
								message : "币账户Id不能为空"
							}
						}
					},
					serialNumber : {
						validators : {
							notEmpty : {
								message : "流水号不能为空"
							}
						}
					},
					accountType : {
						validators : {
							notEmpty : {
								message : "账户类型 1.热账户 2.冷账户不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种Code不能为空"
							}
						}
					},
					changeCount : {
						validators : {
							notEmpty : {
								message : "交易量不能为空"
							}
						}
					},
					changeType : {
						validators : {
							notEmpty : {
								message : "变动类型 1加 2减不能为空"
							}
						}
					},
					triggerPoint : {
						validators : {
							notEmpty : {
								message : "触发点不能为空"
							}
						}
					},
					triggerSerialNumber : {
						validators : {
							notEmpty : {
								message : "触发点流水号不能为空"
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
					url : _ctx + "/klg/assetsrecord/klgassetsrecord/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/assetsrecord/klgassetsrecordlist')
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
				_table.seeRow($("#table"), _ctx + "/klg/assetsrecord/klgassetsrecord/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/assetsrecord/klgassetsrecord/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/klg/assetsrecord/klgassetsrecord/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/assetsrecord/klgassetsrecord/list.do",
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
					title : '币账户Id',
					field : 'accountId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '流水号',
					field : 'serialNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '账户类型 1.热账户 2.冷账户',
					field : 'accountType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '币种Code',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易量',
					field : 'changeCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '变动类型 1加 2减',
					field : 'changeType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '触发点',
					field : 'triggerPoint',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '触发点流水号',
					field : 'triggerSerialNumber',
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