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
										saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
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
					coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
							}
						}
					},
					fixCoin : {
						validators : {
							notEmpty : {
								message : "fixCoin不能为空"
							}
						}
					},
					openTime : {
						validators : {
							notEmpty : {
								message : "openTime不能为空"
							}
						}
					},
					closeTime : {
						validators : {
							notEmpty : {
								message : "closeTime不能为空"
							}
						}
					},
					buyTotalNum : {
						validators : {
							notEmpty : {
								message : "buyTotalNum不能为空"
							}
						}
					},
					sellTotalNum : {
						validators : {
							notEmpty : {
								message : "sellTotalNum不能为空"
							}
						}
					},
					phone : {
						validators : {
							notEmpty : {
								message : "phone不能为空"
							}
						}
					},
					email : {
						validators : {
							notEmpty : {
								message : "email不能为空"
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
					url : _ctx + "/exchange/exrobotlog/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/exrobotloglist')
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
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
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
					coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
							}
						}
					},
					fixCoin : {
						validators : {
							notEmpty : {
								message : "fixCoin不能为空"
							}
						}
					},
					openTime : {
						validators : {
							notEmpty : {
								message : "openTime不能为空"
							}
						}
					},
					closeTime : {
						validators : {
							notEmpty : {
								message : "closeTime不能为空"
							}
						}
					},
					buyTotalNum : {
						validators : {
							notEmpty : {
								message : "buyTotalNum不能为空"
							}
						}
					},
					sellTotalNum : {
						validators : {
							notEmpty : {
								message : "sellTotalNum不能为空"
							}
						}
					},
					phone : {
						validators : {
							notEmpty : {
								message : "phone不能为空"
							}
						}
					},
					email : {
						validators : {
							notEmpty : {
								message : "email不能为空"
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
					url : _ctx + "/exchange/exrobotlog/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/exrobotloglist')
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
				_table.seeRow($("#table"), _ctx + "/exchange/exrobotlog/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/exrobotlog/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/exchange/exrobotlog/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/exchange/exrobotlog/list.do",
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
					visible : false,
					sortable : false,
					searchable : true
				},

				{
					title : '交易币种',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易区',
					field : 'fixCoin',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '开启时间',
					field : 'openTime',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '关闭时间',
					field : 'closeTime',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '买入成交总量',
					field : 'buyTotalNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '卖出成交总量',
					field : 'sellTotalNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易账号手机号',
					field : 'phone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易账号邮箱',
					field : 'email',
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