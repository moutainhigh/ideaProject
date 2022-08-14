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
										functionName : {
						validators : {
							notEmpty : {
								message : "{name:'方法名称'}不能为空"
							}
						}
					},
					isException : {
						validators : {
							notEmpty : {
								message : "{name:'是否出现异常 0否 1是'}不能为空"
							}
						}
					},
					ipaddress : {
						validators : {
							notEmpty : {
								message : "{name:'ip地址'}不能为空"
							}
						}
					},
					lasttime : {
						validators : {
							notEmpty : {
								message : "{name:'持续时间'}不能为空"
							}
						}
					},
					startDate : {
						validators : {
							notEmpty : {
								message : "{name:'开始时间'}不能为空"
							}
						}
					},
					endDate : {
						validators : {
							notEmpty : {
								message : "{name:'结束时间'}不能为空"
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
					url : _ctx + "/klg/log/klgtasklog/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/log/klgtaskloglist')
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
					functionName : {
						validators : {
							notEmpty : {
								message : "{name:'方法名称'}不能为空"
							}
						}
					},
					isException : {
						validators : {
							notEmpty : {
								message : "{name:'是否出现异常 0否 1是'}不能为空"
							}
						}
					},
					ipaddress : {
						validators : {
							notEmpty : {
								message : "{name:'ip地址'}不能为空"
							}
						}
					},
					lasttime : {
						validators : {
							notEmpty : {
								message : "{name:'持续时间'}不能为空"
							}
						}
					},
					startDate : {
						validators : {
							notEmpty : {
								message : "{name:'开始时间'}不能为空"
							}
						}
					},
					endDate : {
						validators : {
							notEmpty : {
								message : "{name:'结束时间'}不能为空"
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
					url : _ctx + "/klg/log/klgtasklog/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/log/klgtaskloglist')
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
				_table.seeRow($("#table"), _ctx + "/klg/log/klgtasklog/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/log/klgtasklog/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/klg/log/klgtasklog/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/log/klgtasklog/list.do",
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
					title : '{name:'方法名称'}',
					field : 'functionName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '{name:'是否出现异常 0否 1是'}',
					field : 'isException',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '{name:'ip地址'}',
					field : 'ipaddress',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '{name:'持续时间'}',
					field : 'lasttime',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '{name:'开始时间'}',
					field : 'startDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '{name:'结束时间'}',
					field : 'endDate',
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