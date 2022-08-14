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
										customer_id : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					oldLevel_id : {
						validators : {
							notEmpty : {
								message : "oldLevel_id不能为空"
							}
						}
					},
					oldLevel : {
						validators : {
							notEmpty : {
								message : "旧等级名称不能为空"
							}
						}
					},
					newLevel_id : {
						validators : {
							notEmpty : {
								message : "newLevel_id不能为空"
							}
						}
					},
					newLevel : {
						validators : {
							notEmpty : {
								message : "新等级名称不能为空"
							}
						}
					},
					upgradeNote : {
						validators : {
							notEmpty : {
								message : "升级备注不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "等级变化类型（1，升级，2 降级）不能为空"
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
					url : _ctx + "/ico/upgraderecord/icoupgraderecord/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/upgraderecord/icoupgraderecordlist')
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
					customer_id : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					oldLevel_id : {
						validators : {
							notEmpty : {
								message : "oldLevel_id不能为空"
							}
						}
					},
					oldLevel : {
						validators : {
							notEmpty : {
								message : "旧等级名称不能为空"
							}
						}
					},
					newLevel_id : {
						validators : {
							notEmpty : {
								message : "newLevel_id不能为空"
							}
						}
					},
					newLevel : {
						validators : {
							notEmpty : {
								message : "新等级名称不能为空"
							}
						}
					},
					upgradeNote : {
						validators : {
							notEmpty : {
								message : "升级备注不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "等级变化类型（1，升级，2 降级）不能为空"
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
					url : _ctx + "/ico/upgraderecord/icoupgraderecord/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/upgraderecord/icoupgraderecordlist')
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
            $("#table_reset").on("click", function() {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function() {
                var params=  $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"),params);
            });

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/ico/upgraderecord/icoupgraderecord/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/ico/upgraderecord/icoupgraderecord/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/ico/upgraderecord/icoupgraderecord/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/ico/upgraderecord/icoupgraderecord/findPageBySql.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '邮箱',
					field : 'customer_email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手机号',
					field : 'customer_mobile',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '现等级名称',
					field : 'newLevel',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '原等级名称',
					field : 'oldLevel',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '更新时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '升级备注',
					field : 'upgradeNote',
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