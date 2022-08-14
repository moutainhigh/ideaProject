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
										appAccountId : {
						validators : {
							notEmpty : {
								message : "appAccountId不能为空"
							}
						}
					},
					appAccountNum : {
						validators : {
							notEmpty : {
								message : "appAccountNum不能为空"
							}
						}
					},
					customerId : {
						validators : {
							notEmpty : {
								message : "customerId不能为空"
							}
						}
					},
					customerName : {
						validators : {
							notEmpty : {
								message : "customerName不能为空"
							}
						}
					},
					recordType : {
						validators : {
							notEmpty : {
								message : "recordType不能为空"
							}
						}
					},
					source : {
						validators : {
							notEmpty : {
								message : "source不能为空"
							}
						}
					},
					transactionMoney : {
						validators : {
							notEmpty : {
								message : "transactionMoney不能为空"
							}
						}
					},
					transactionNum : {
						validators : {
							notEmpty : {
								message : "transactionNum不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "status不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "remark不能为空"
							}
						}
					},
					currencyName : {
						validators : {
							notEmpty : {
								message : "currencyName不能为空"
							}
						}
					},
					currencyType : {
						validators : {
							notEmpty : {
								message : "currencyType不能为空"
							}
						}
					},
					auditor : {
						validators : {
							notEmpty : {
								message : "auditor不能为空"
							}
						}
					},
					operationTime : {
						validators : {
							notEmpty : {
								message : "operationTime不能为空"
							}
						}
					},
					customerAccount : {
						validators : {
							notEmpty : {
								message : "customerAccount不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					factorage : {
						validators : {
							notEmpty : {
								message : "factorage不能为空"
							}
						}
					},
					website : {
						validators : {
							notEmpty : {
								message : "website不能为空"
							}
						}
					},
					trueName : {
						validators : {
							notEmpty : {
								message : "trueName不能为空"
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
					url : _ctx + "/exchange/appaccountrecord/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/appaccountrecordlist')
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
					appAccountId : {
						validators : {
							notEmpty : {
								message : "appAccountId不能为空"
							}
						}
					},
					appAccountNum : {
						validators : {
							notEmpty : {
								message : "appAccountNum不能为空"
							}
						}
					},
					customerId : {
						validators : {
							notEmpty : {
								message : "customerId不能为空"
							}
						}
					},
					customerName : {
						validators : {
							notEmpty : {
								message : "customerName不能为空"
							}
						}
					},
					recordType : {
						validators : {
							notEmpty : {
								message : "recordType不能为空"
							}
						}
					},
					source : {
						validators : {
							notEmpty : {
								message : "source不能为空"
							}
						}
					},
					transactionMoney : {
						validators : {
							notEmpty : {
								message : "transactionMoney不能为空"
							}
						}
					},
					transactionNum : {
						validators : {
							notEmpty : {
								message : "transactionNum不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "status不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "remark不能为空"
							}
						}
					},
					currencyName : {
						validators : {
							notEmpty : {
								message : "currencyName不能为空"
							}
						}
					},
					currencyType : {
						validators : {
							notEmpty : {
								message : "currencyType不能为空"
							}
						}
					},
					auditor : {
						validators : {
							notEmpty : {
								message : "auditor不能为空"
							}
						}
					},
					operationTime : {
						validators : {
							notEmpty : {
								message : "operationTime不能为空"
							}
						}
					},
					customerAccount : {
						validators : {
							notEmpty : {
								message : "customerAccount不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					factorage : {
						validators : {
							notEmpty : {
								message : "factorage不能为空"
							}
						}
					},
					website : {
						validators : {
							notEmpty : {
								message : "website不能为空"
							}
						}
					},
					trueName : {
						validators : {
							notEmpty : {
								message : "trueName不能为空"
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
					url : _ctx + "/exchange/appaccountrecord/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/appaccountrecordlist')
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
				_table.seeRow($("#table"), _ctx + "/exchange/appaccountrecord/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/appaccountrecord/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/exchange/appaccountrecord/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/exchange/appaccountrecord/list.do",
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
					title : 'appAccountId',
					field : 'appAccountId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'appAccountNum',
					field : 'appAccountNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'customerId',
					field : 'customerId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'customerName',
					field : 'customerName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'recordType',
					field : 'recordType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'source',
					field : 'source',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'transactionMoney',
					field : 'transactionMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'transactionNum',
					field : 'transactionNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'status',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'remark',
					field : 'remark',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'currencyName',
					field : 'currencyName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'currencyType',
					field : 'currencyType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'auditor',
					field : 'auditor',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'operationTime',
					field : 'operationTime',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'customerAccount',
					field : 'customerAccount',
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
					title : 'factorage',
					field : 'factorage',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'website',
					field : 'website',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'trueName',
					field : 'trueName',
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