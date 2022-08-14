define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
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
                },
                // bv校验通过则提交
                submitHandler : function(validator, form, submitButton) {
                }
            });
            // 添加提交
            $("#addSubmit").on("click", function() {
                var options = {
                    url : _ctx + "/web/loginaop/add.do",
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
                                loadUrl(_ctx+'/v.do?u=/admin/web/loginaoplist')
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
				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			});
			// 修改提交
			$("#modifySubmit").on("click", function() {
				var options = {
					url : _ctx + "/web/loginaop/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/loginaoplist')
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

			// 查看页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/loginaop/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/loginaop/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/loginaop/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/loginaop/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '用户ID',
					field : 'userId',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '用户名',
					field : 'userName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'ip',
					field : 'ip',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '登录时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '方法名',
					field : 'methodName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '参数',
					field : 'args',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '织入增强处理的目标对象',
					field : 'target',
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