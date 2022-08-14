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
								message : "账户id不能为空"
							}
						}
					},
					customer_email : {
						validators : {
							notEmpty : {
								message : "账户邮箱不能为空"
							}
						}
					},
					customer_mobile : {
						validators : {
							notEmpty : {
								message : "账户手机不能为空"
							}
						}
					},
					customer_level : {
						validators : {
							notEmpty : {
								message : "账户等级不能为空"
							}
						}
					},
					hedgeQuantity : {
						validators : {
							notEmpty : {
								message : "锁仓量不能为空"
							}
						}
					},
					grossCommission : {
						validators : {
							notEmpty : {
								message : "手续费总量不能为空"
							}
						}
					},
					coid_id : {
						validators : {
							notEmpty : {
								message : "币id不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种(CNY)不能为空"
							}
						}
					},
					coinName : {
						validators : {
							notEmpty : {
								message : "虚拟币种汉语名称(比特币 莱特币..)不能为空"
							}
						}
					},
					dividend_radix : {
						validators : {
							notEmpty : {
								message : "分红数量不能为空"
							}
						}
					},
					dividend_num : {
						validators : {
							notEmpty : {
								message : "分红流水号不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "状态(1 分红发放中，2 成功 ，3 失败)不能为空"
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
					url : _ctx + "/ico/dividend/icodividendrecord/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/dividend/icodividendrecordlist')
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
								message : "账户id不能为空"
							}
						}
					},
					customer_email : {
						validators : {
							notEmpty : {
								message : "账户邮箱不能为空"
							}
						}
					},
					customer_mobile : {
						validators : {
							notEmpty : {
								message : "账户手机不能为空"
							}
						}
					},
					customer_level : {
						validators : {
							notEmpty : {
								message : "账户等级不能为空"
							}
						}
					},
					hedgeQuantity : {
						validators : {
							notEmpty : {
								message : "锁仓量不能为空"
							}
						}
					},
					grossCommission : {
						validators : {
							notEmpty : {
								message : "手续费总量不能为空"
							}
						}
					},
					coid_id : {
						validators : {
							notEmpty : {
								message : "币id不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种(CNY)不能为空"
							}
						}
					},
					coinName : {
						validators : {
							notEmpty : {
								message : "虚拟币种汉语名称(比特币 莱特币..)不能为空"
							}
						}
					},
					dividend_radix : {
						validators : {
							notEmpty : {
								message : "分红数量不能为空"
							}
						}
					},
					dividend_num : {
						validators : {
							notEmpty : {
								message : "分红流水号不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "状态(1 分红发放中，2 成功 ，3 失败)不能为空"
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
					url : _ctx + "/ico/dividend/icodividendrecord/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/dividend/icodividendrecordlist')
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
            //重置按钮
            $("#table_reset").on("click", function() {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function() {
                var params=  $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"),params);
            });

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/ico/dividend/icodividendrecord/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/ico/dividend/icodividendrecord/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/ico/dividend/icodividendrecord/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/ico/dividend/icodividendrecord/findPageBySql.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},

				{
					title : '邮箱',
					field : 'customer_email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手机号',
					field : 'customer_mobile',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '等级',
					field : 'customer_level',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '锁仓量',
					field : 'hedgeQuantity',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手续费总量',
					field : 'grossCommission',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},

				{
					title : '币种',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},

				{
					title : '分红数量',
					field : 'dividend_radix',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '流水号',
					field : 'dividend_num',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title: '操作时间',
					field: 'created',
					align: 'center',
					visible: true,
					sortable: false,
					searchable: true,

				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});