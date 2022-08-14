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
					exceptionText : {
						validators : {
							notEmpty : {
								message : "{name:'异常原因'}不能为空"
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
					customerMinerId : {
						validators : {
							notEmpty : {
								message : "{name:'矿机ID'}不能为空"
							}
						}
					},
					mobile : {
						validators : {
							notEmpty : {
								message : "mobile不能为空"
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
					url : _ctx + "/klg/log/klgexceptionlog/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/log/klgexceptionloglist')
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
					exceptionText : {
						validators : {
							notEmpty : {
								message : "{name:'异常原因'}不能为空"
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
					customerMinerId : {
						validators : {
							notEmpty : {
								message : "{name:'矿机ID'}不能为空"
							}
						}
					},
					mobile : {
						validators : {
							notEmpty : {
								message : "mobile不能为空"
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
					url : _ctx + "/klg/log/klgexceptionlog/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/log/klgexceptionloglist')
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
				_table.seeRow($("#table"), _ctx + "/klg/log/klgexceptionlog/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/log/klgexceptionlog/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/klg/log/klgexceptionlog/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/log/klgexceptionlog/list.do",
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
					title : '{name:'异常原因'}',
					field : 'exceptionText',
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
					title : '{name:'矿机ID'}',
					field : 'customerMinerId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'mobile',
					field : 'mobile',
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