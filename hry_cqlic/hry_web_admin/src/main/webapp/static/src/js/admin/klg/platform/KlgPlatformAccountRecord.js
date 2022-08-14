define(function(require, exports, module) {
	this._table = require("js/base/table");
	this.sele = require("js/base/HrySelect");
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
										serialNumber : {
						validators : {
							notEmpty : {
								message : "serialNumber不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "类型不能为空"
							}
						}
					},
					money : {
						validators : {
							notEmpty : {
								message : "金额不能为空"
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
					nowMoney : {
						validators : {
							notEmpty : {
								message : "nowMoney不能为空"
							}
						}
					},
					accountId : {
						validators : {
							notEmpty : {
								message : "accountId不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "remark不能为空"
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
					url : _ctx + "/klg/platform/klgplatformaccountrecord/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/platform/klgplatformaccountrecordlist')
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
					serialNumber : {
						validators : {
							notEmpty : {
								message : "serialNumber不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "类型不能为空"
							}
						}
					},
					money : {
						validators : {
							notEmpty : {
								message : "金额不能为空"
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
					nowMoney : {
						validators : {
							notEmpty : {
								message : "nowMoney不能为空"
							}
						}
					},
					accountId : {
						validators : {
							notEmpty : {
								message : "accountId不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "remark不能为空"
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
					url : _ctx + "/klg/platform/klgplatformaccountrecord/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/platform/klgplatformaccountrecordlist')
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
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/platform/klgplatformaccountrecord/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '流水号',
					field : 'serialNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},/*
				{
					title : '操作类型',
					field : 'type',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},*/
				{
					title : '交易量',
					field : 'money',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},/*
				{
					title : '币种',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'nowMoney',
					field : 'nowMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},*/
				{
					title : '账户类型',
					field : 'accountId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						var aa = sele.getKeyAndVal("account_type",value)
						return  aa;
					}
				},
				{
					title : '操作类型',
					field : 'remark',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
					{
						title : '操作时间',
						field : 'created',
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