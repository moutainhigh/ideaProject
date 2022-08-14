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
										name : {
						validators : {
							notEmpty : {
								message : "合约名称不能为空"
							}
						}
					},
					cycle : {
						validators : {
							notEmpty : {
								message : "周期 1当周 2次周 3季度不能为空"
							}
						}
					},
					multiple : {
						validators : {
							notEmpty : {
								message : "倍数不能为空"
							}
						}
					},
					calculateWeek : {
						validators : {
							notEmpty : {
								message : "清算/交割时间  星期几不能为空"
							}
						}
					},
					calculateTime : {
						validators : {
							notEmpty : {
								message : "具体 清算/交割 时间不能为空"
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
					url : _ctx + "/contract/contractscheme/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/contract/contractschemelist')
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
					name : {
						validators : {
							notEmpty : {
								message : "合约名称不能为空"
							}
						}
					},
					cycle : {
						validators : {
							notEmpty : {
								message : "周期 1当周 2次周 3季度不能为空"
							}
						}
					},
					multiple : {
						validators : {
							notEmpty : {
								message : "倍数不能为空"
							}
						}
					},
					calculateWeek : {
						validators : {
							notEmpty : {
								message : "清算/交割时间  星期几不能为空"
							}
						}
					},
					calculateTime : {
						validators : {
							notEmpty : {
								message : "具体 清算/交割 时间不能为空"
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
					url : _ctx + "/contract/contractscheme/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/contract/contractschemelist')
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
				_table.seeRow($("#table"), _ctx + "/contract/contractscheme/modifysee");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/contract/contractscheme/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '合约名称',
					field : 'name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '周期',
					field : 'cycle',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter:function (value,row,index) {
						switch (value) {
							case 1:
								return '当周'
							case 2:
								return '次周'
							case 3:
								return '季度'
                        }
                    }
				},
				{
					title : '倍数',
					field : 'multiple',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '清算/交割时间 星期几',
					field : 'calculateWeek',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '具体清算/交割 时间',
					field : 'calculateTime',
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