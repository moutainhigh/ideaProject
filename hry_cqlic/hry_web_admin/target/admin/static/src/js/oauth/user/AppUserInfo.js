define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//查看页面--初始化方法
		see : function(){
		},
		info : function() {
		
			// 修改提交
			$("#modifySubmit").on("click", function() {
				var options = {
					url : _ctx + "/user/appuserinfo/modify.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('修改成功!', {icon : 1});
								loadUrl(_ctx+'/oauth/user/appuserinfo/info.do');
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}
				};
				$("#form").ajaxSubmit(options);
			});
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
										userid : {
						validators : {
							notEmpty : {
								message : "userid不能为空"
							}
						}
					},
					age : {
						validators : {
							notEmpty : {
								message : "age不能为空"
							}
						}
					},
					birthday : {
						validators : {
							notEmpty : {
								message : "birthday不能为空"
							}
						}
					},
					email : {
						validators : {
							notEmpty : {
								message : "email不能为空"
							}
						}
					},
					homeAddress : {
						validators : {
							notEmpty : {
								message : "homeAddress不能为空"
							}
						}
					},
					truename : {
						validators : {
							notEmpty : {
								message : "truename不能为空"
							}
						}
					},
					picturePath : {
						validators : {
							notEmpty : {
								message : "picturePath不能为空"
							}
						}
					},
					qqNumber : {
						validators : {
							notEmpty : {
								message : "qqNumber不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "remark不能为空"
							}
						}
					},
					sex : {
						validators : {
							notEmpty : {
								message : "sex不能为空"
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
					url : _ctx + "/user/appuserinfo/add.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
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
								loadUrl('/oauth/user/appuserinfolist')
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
					userid : {
						validators : {
							notEmpty : {
								message : "userid不能为空"
							}
						}
					},
					age : {
						validators : {
							notEmpty : {
								message : "age不能为空"
							}
						}
					},
					birthday : {
						validators : {
							notEmpty : {
								message : "birthday不能为空"
							}
						}
					},
					email : {
						validators : {
							notEmpty : {
								message : "email不能为空"
							}
						}
					},
					homeAddress : {
						validators : {
							notEmpty : {
								message : "homeAddress不能为空"
							}
						}
					},
					truename : {
						validators : {
							notEmpty : {
								message : "truename不能为空"
							}
						}
					},
					picturePath : {
						validators : {
							notEmpty : {
								message : "picturePath不能为空"
							}
						}
					},
					qqNumber : {
						validators : {
							notEmpty : {
								message : "qqNumber不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "remark不能为空"
							}
						}
					},
					sex : {
						validators : {
							notEmpty : {
								message : "sex不能为空"
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
					url : _ctx + "/user/appuserinfo/modify.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
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
								loadUrl('/oauth/user/appuserinfolist')
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
				_table.seeRow($("#table"), _ctx + "/user/appuserinfo/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/user/appuserinfo/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/user/appuserinfo/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/user/appuserinfo/list.do",
				columns : [ {
					field : 'state',
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
					title : 'userid',
					field : 'userid',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'age',
					field : 'age',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'birthday',
					field : 'birthday',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'email',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'homeAddress',
					field : 'homeAddress',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'truename',
					field : 'truename',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'picturePath',
					field : 'picturePath',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'qqNumber',
					field : 'qqNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'remark',
					field : 'remark',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'sex',
					field : 'sex',
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