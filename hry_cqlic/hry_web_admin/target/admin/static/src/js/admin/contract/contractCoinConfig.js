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
										coinCode : {
						validators : {
							notEmpty : {
								message : "币种代码不能为空"
							}
						}
					},
					faceValue : {
						validators : {
							notEmpty : {
								message : "合约面值不能为空"
							}
						}
					},
					maxBuyIn : {
						validators : {
							notEmpty : {
								message : "最大买入不能为空"
							}
						}
					},
					buyFeeRate : {
						validators : {
							notEmpty : {
								message : "买入手续费不能为空"
							}
						}
					},
					sellFeeRate : {
						validators : {
							notEmpty : {
								message : "卖出手续费不能为空"
							}
						}
					},
					keepDecimal : {
						validators : {
							notEmpty : {
								message : "保留小数位不能为空"
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
					url : _ctx + "/contract/contractcoinconfig/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/contract/contractcoinconfiglist')
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
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种代码不能为空"
							}
						}
					},
					faceValue : {
						validators : {
							notEmpty : {
								message : "合约面值不能为空"
							}
						}
					},
					maxBuyIn : {
						validators : {
							notEmpty : {
								message : "最大买入不能为空"
							}
						}
					},
					buyFeeRate : {
						validators : {
							notEmpty : {
								message : "买入手续费不能为空"
							}
						}
					},
					sellFeeRate : {
						validators : {
							notEmpty : {
								message : "卖出手续费不能为空"
							}
						}
					},
					keepDecimal : {
						validators : {
							notEmpty : {
								message : "保留小数位不能为空"
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
					url : _ctx + "/contract/contractcoinconfig/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/contract/contractcoinconfiglist')
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

			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/contract/contractcoinconfig/modifysee");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/contract/contractcoinconfig/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '币种代码',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '合约面值',
					field : 'faceValue',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '最大买入',
					field : 'maxBuyIn',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '买入手续费%',
					field : 'buyFeeRate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '卖出手续费%',
					field : 'sellFeeRate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '保留小数位',
					field : 'keepDecimal',
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