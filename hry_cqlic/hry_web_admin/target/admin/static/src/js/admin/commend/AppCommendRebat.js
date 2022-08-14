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
										transactionNum : {
						validators : {
							notEmpty : {
								message : "transactionNum不能为空"
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
					trueName : {
						validators : {
							notEmpty : {
								message : "trueName不能为空"
							}
						}
					},
					rebatMoney : {
						validators : {
							notEmpty : {
								message : "rebatMoney不能为空"
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
					status : {
						validators : {
							notEmpty : {
								message : "status不能为空"
							}
						}
					},
					userCode : {
						validators : {
							notEmpty : {
								message : "userCode不能为空"
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
					url : _ctx + "/commend/appcommendrebat/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/commend/appcommendrebatlist')
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
					transactionNum : {
						validators : {
							notEmpty : {
								message : "transactionNum不能为空"
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
					trueName : {
						validators : {
							notEmpty : {
								message : "trueName不能为空"
							}
						}
					},
					rebatMoney : {
						validators : {
							notEmpty : {
								message : "rebatMoney不能为空"
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
					status : {
						validators : {
							notEmpty : {
								message : "status不能为空"
							}
						}
					},
					userCode : {
						validators : {
							notEmpty : {
								message : "userCode不能为空"
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
					url : _ctx + "/commend/appcommendrebat/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/commend/appcommendrebatlist')
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
            $("#table_reset").on("click", function () {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/commend/appcommendrebat/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/commend/appcommendrebat/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/commend/appcommendrebat/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/commend/appcommendrebat/list.do",
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
					searchable : false
				},
				{
					title : '代理商邮箱',
					field : 'appPersonInfo.email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '代理商手机号',
					field : 'appPersonInfo.mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '返佣金额',
					field : 'rebatMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '返佣币种',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '返佣时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});