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
										commendName : {
						validators : {
							notEmpty : {
								message : "commendName不能为空"
							}
						}
					},
					RankRatio : {
						validators : {
							notEmpty : {
								message : "RankRatio不能为空"
							}
						}
					},
					StandardValue : {
						validators : {
							notEmpty : {
								message : "StandardValue不能为空"
							}
						}
					},
					transactionNumber : {
						validators : {
							notEmpty : {
								message : "transactionNumber不能为空"
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
					costName : {
						validators : {
							notEmpty : {
								message : "costName不能为空"
							}
						}
					},
					states : {
						validators : {
							notEmpty : {
								message : "states不能为空"
							}
						}
					},
					MinHierarchy : {
						validators : {
							notEmpty : {
								message : "MinHierarchy不能为空"
							}
						}
					},
					MaxHierarchy : {
						validators : {
							notEmpty : {
								message : "MaxHierarchy不能为空"
							}
						}
					},
					reserveMoney : {
						validators : {
							notEmpty : {
								message : "reserveMoney不能为空"
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
					url : _ctx + "/commend/appcommenddeploy/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/commend/appcommenddeploylist')
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
					commendName : {
						validators : {
							notEmpty : {
								message : "commendName不能为空"
							}
						}
					},
					RankRatio : {
						validators : {
							notEmpty : {
								message : "RankRatio不能为空"
							}
						}
					},
					StandardValue : {
						validators : {
							notEmpty : {
								message : "StandardValue不能为空"
							}
						}
					},
					transactionNumber : {
						validators : {
							notEmpty : {
								message : "transactionNumber不能为空"
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
					costName : {
						validators : {
							notEmpty : {
								message : "costName不能为空"
							}
						}
					},
					states : {
						validators : {
							notEmpty : {
								message : "states不能为空"
							}
						}
					},
					MinHierarchy : {
						validators : {
							notEmpty : {
								message : "MinHierarchy不能为空"
							}
						}
					},
					MaxHierarchy : {
						validators : {
							notEmpty : {
								message : "MaxHierarchy不能为空"
							}
						}
					},
					reserveMoney : {
						validators : {
							notEmpty : {
								message : "reserveMoney不能为空"
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
					url : _ctx + "/commend/appcommenddeploy/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/commend/appcommenddeploylist')
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
				_table.seeRow($("#table"), _ctx + "/commend/appcommenddeploy/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/commend/appcommenddeploy/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/commend/appcommenddeploy/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/commend/appcommenddeploy/list.do",
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
					title : 'commendName',
					field : 'commendName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'RankRatio',
					field : 'RankRatio',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'StandardValue',
					field : 'StandardValue',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'transactionNumber',
					field : 'transactionNumber',
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
					title : 'costName',
					field : 'costName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'states',
					field : 'states',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'MinHierarchy',
					field : 'MinHierarchy',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'MaxHierarchy',
					field : 'MaxHierarchy',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'reserveMoney',
					field : 'reserveMoney',
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