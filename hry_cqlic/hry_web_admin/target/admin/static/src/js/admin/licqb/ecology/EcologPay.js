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
					enterId : {
						validators : {
							notEmpty : {
								message : "入驻订单Id不能为空"
							}
						}
					},
					orderNum : {
						validators : {
							notEmpty : {
								message : "单号不能为空"
							}
						}
					},
					payDate : {
						validators : {
							notEmpty : {
								message : "缴费时间不能为空"
							}
						}
					},
					acceptAddress : {
						validators : {
							notEmpty : {
								message : "收款地址不能为空"
							}
						}
					},
					paymentAddress : {
						validators : {
							notEmpty : {
								message : "付款地址不能为空"
							}
						}
					},
					verifyDate : {
						validators : {
							notEmpty : {
								message : "核实时间不能为空"
							}
						}
					},
					baseValidityDay : {
						validators : {
							notEmpty : {
								message : "当前规则中有效期天数不能为空"
							}
						}
					},
					residueValidityDay : {
						validators : {
							notEmpty : {
								message : "上笔剩余天数不能为空"
							}
						}
					},
					validityDay : {
						validators : {
							notEmpty : {
								message : "实际保证期有效天数不能为空"
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
					url : _ctx + "/licqb/ecology/ecologpay/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/ecology/ecologpaylist')
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
					enterId : {
						validators : {
							notEmpty : {
								message : "入驻订单Id不能为空"
							}
						}
					},
					orderNum : {
						validators : {
							notEmpty : {
								message : "单号不能为空"
							}
						}
					},
					payDate : {
						validators : {
							notEmpty : {
								message : "缴费时间不能为空"
							}
						}
					},
					acceptAddress : {
						validators : {
							notEmpty : {
								message : "收款地址不能为空"
							}
						}
					},
					paymentAddress : {
						validators : {
							notEmpty : {
								message : "付款地址不能为空"
							}
						}
					},
					verifyDate : {
						validators : {
							notEmpty : {
								message : "核实时间不能为空"
							}
						}
					},
					baseValidityDay : {
						validators : {
							notEmpty : {
								message : "当前规则中有效期天数不能为空"
							}
						}
					},
					residueValidityDay : {
						validators : {
							notEmpty : {
								message : "上笔剩余天数不能为空"
							}
						}
					},
					validityDay : {
						validators : {
							notEmpty : {
								message : "实际保证期有效天数不能为空"
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
					url : _ctx + "/licqb/ecology/ecologpay/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/ecology/ecologpaylist')
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
				_table.seeRow($("#table"), _ctx + "/licqb/ecology/ecologpay/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/licqb/ecology/ecologpay/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/licqb/ecology/ecologpay/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/licqb/ecology/ecologpay/list.do",
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
					title : '入驻订单Id',
					field : 'enterId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '单号',
					field : 'orderNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '缴费时间',
					field : 'payDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '收款地址',
					field : 'acceptAddress',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '付款地址',
					field : 'paymentAddress',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '核实时间',
					field : 'verifyDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '当前规则中有效期天数',
					field : 'baseValidityDay',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '上笔剩余天数',
					field : 'residueValidityDay',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '实际保证期有效天数',
					field : 'validityDay',
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