define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
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
							},
							digits : {
								message : '等级排序必须是正整数'
							}
						}
					},
					directRecommendNum : {
						validators : {
							notEmpty : {
								message : "直推荐人数不能为空"
							},
							digits : {
								message : '人数必须是正整数'
							}
						}
					},
					nextRecommendNum : {
						validators : {
							notEmpty : {
								message : "下级及以上用户数不能为空"
							},
							digits : {
								message : '人数必须是正整数'
							}
						}
					},
					teamPerformance : {
						validators : {
							notEmpty : {
								message : "团队业绩(USDT)不能为空"
							}
						}
					},
					levelAward : {
						validators : {
							notEmpty : {
								message : "等级奖励比例不能为空"

							},
							regexp: {
								regexp: /^(([1-9]{1}\d*)|(0{1}))(\.\d{0,2})?$/,
								message: '请输入正确的等级奖励比例'
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
					url : _ctx + "/licqb/platform/levelconfig/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/platform/levelconfiglist')
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
							},
							digits : {
								message : '等级排序必须是正整数'
							}
						}
					},
					directRecommendNum : {
						validators : {
							notEmpty : {
								message : "直推荐人数不能为空"
							},
							digits : {
								message : '人数必须是正整数'
							}
						}
					},
					nextRecommendNum : {
						validators : {
							notEmpty : {
								message : "下级及以上用户数不能为空"
							},
							digits : {
								message : '人数必须是正整数'
							}
						}
					},
					teamPerformance : {
						validators : {
							notEmpty : {
								message : "团队业绩(USDT)不能为空"
							}
						}
					},
					levelAward : {
						validators : {
							notEmpty : {
								message : "等级奖励比例不能为空"

							},
							regexp: {
								regexp: /^(([1-9]{1}\d*)|(0{1}))(\.\d{0,2})?$/,
								message: '请输入正确的等级奖励比例'
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
					url : _ctx + "/licqb/platform/levelconfig/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/platform/levelconfiglist')
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
				_table.seeRow($("#table"), _ctx + "/licqb/platform/levelconfig/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/licqb/platform/levelconfig/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/licqb/platform/levelconfig/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
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
					title : '直推荐人数',
					field : 'directRecommendNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '下级及以上用户数',
					field : 'nextRecommendNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '团队业绩(USDT)',
					field : 'teamPerformance',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '等级奖励比例(%)',
					field : 'levelAward',
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