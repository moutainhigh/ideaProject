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
								message : "用户id不能为空"
							}
						}
					},
					levelId : {
						validators : {
							notEmpty : {
								message : "等级id不能为空"
							}
						}
					},
					levelName : {
						validators : {
							notEmpty : {
								message : "等级名称不能为空"
							}
						}
					},
					sort : {
						validators : {
							notEmpty : {
								message : "等级排序不能为空"
							}
						}
					},
					gradation : {
						validators : {
							notEmpty : {
								message : "级别差不能为空"
							}
						}
					},
					pointAlgebra : {
						validators : {
							notEmpty : {
								message : "见点代数不能为空"
							}
						}
					},
					rewardNum : {
						validators : {
							notEmpty : {
								message : "奖金额度不能为空"
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
					url : _ctx + "/klg/limit/klgcustomerlevel/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/limit/klgcustomerlevellist')
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
								message : "用户id不能为空"
							}
						}
					},
					levelId : {
						validators : {
							notEmpty : {
								message : "等级id不能为空"
							}
						}
					},
					levelName : {
						validators : {
							notEmpty : {
								message : "等级名称不能为空"
							}
						}
					},
					sort : {
						validators : {
							notEmpty : {
								message : "等级排序不能为空"
							}
						}
					},
					gradation : {
						validators : {
							notEmpty : {
								message : "级别差不能为空"
							}
						}
					},
					pointAlgebra : {
						validators : {
							notEmpty : {
								message : "见点代数不能为空"
							}
						}
					},
					rewardNum : {
						validators : {
							notEmpty : {
								message : "奖金额度不能为空"
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
					url : _ctx + "/klg/limit/klgcustomerlevel/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/limit/klgcustomerlevellist')
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
				url : _ctx + "/klg/limit/klgcustomerlevel/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '邮箱',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
					{
						title : '手机号',
						field : 'mobilePhone',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
				{
					title : '等级名称',
					field : 'levelName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '等级排序',
					field : 'sort',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '级别差',
					field : 'gradation',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						var fixedGradation=row.fixedGradation;
						if(fixedGradation!==null&&fixedGradation!=''){
							return fixedGradation;
						}
						return value;
					}
				},
				{
					title : '见点代数',
					field : 'pointAlgebra',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '奖金额度',
					field : 'rewardNum',
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
