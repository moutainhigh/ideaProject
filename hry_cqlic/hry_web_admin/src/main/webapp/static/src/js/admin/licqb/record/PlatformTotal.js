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
					todayAddNum : {
						validators : {
							notEmpty : {
								message : "今日新增量不能为空"
							}
						}
					},
					ayerAllNum : {
						validators : {
							notEmpty : {
								message : "昨日总量不能为空"
							}
						}
					},
					ayerConvertNum : {
						validators : {
							notEmpty : {
								message : "昨日兑换量不能为空"
							}
						}
					},
					ayerSurplusNum : {
						validators : {
							notEmpty : {
								message : "昨日剩余量（昨日总量-昨日已兑换量）不能为空"
							}
						}
					},
					todayAllNum : {
						validators : {
							notEmpty : {
								message : "今日总量（今日新增+昨日剩余量）不能为空"
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
					url : _ctx + "/licqb/record/platformtotal/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/record/platformtotallist')
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
					todayAddNum : {
						validators : {
							notEmpty : {
								message : "今日新增量不能为空"
							}
						}
					},
					ayerAllNum : {
						validators : {
							notEmpty : {
								message : "昨日总量不能为空"
							}
						}
					},
					ayerConvertNum : {
						validators : {
							notEmpty : {
								message : "昨日兑换量不能为空"
							}
						}
					},
					ayerSurplusNum : {
						validators : {
							notEmpty : {
								message : "昨日剩余量（昨日总量-昨日已兑换量）不能为空"
							}
						}
					},
					todayAllNum : {
						validators : {
							notEmpty : {
								message : "今日总量（今日新增+昨日剩余量）不能为空"
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
					url : _ctx + "/licqb/record/platformtotal/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/record/platformtotallist')
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
				url : _ctx + "/licqb/record/platformtotal/list.do",
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
					title : '今日新增量',
					field : 'todayAddNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '昨日总量',
					field : 'ayerAllNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '昨日兑换量',
					field : 'ayerConvertNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '昨日剩余量',
					field : 'ayerSurplusNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
						title : '今日总量',
						field : 'todayAllNum',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '创建时间',
						field : 'created',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});