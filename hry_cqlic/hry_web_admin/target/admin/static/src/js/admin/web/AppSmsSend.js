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
										type : {
						validators : {
							notEmpty : {
								message : "type不能为空"
							}
						}
					},
					serverUrl : {
						validators : {
							notEmpty : {
								message : "serverUrl不能为空"
							}
						}
					},
					postParam : {
						validators : {
							notEmpty : {
								message : "postParam不能为空"
							}
						}
					},
					receiveStatus : {
						validators : {
							notEmpty : {
								message : "receiveStatus不能为空"
							}
						}
					},
					sendStatus : {
						validators : {
							notEmpty : {
								message : "sendStatus不能为空"
							}
						}
					},
					thirdPartyResult : {
						validators : {
							notEmpty : {
								message : "thirdPartyResult不能为空"
							}
						}
					},
					sendContent : {
						validators : {
							notEmpty : {
								message : "sendContent不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
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
					url : _ctx + "/web/appsmssend/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appsmssendlist')
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
					type : {
						validators : {
							notEmpty : {
								message : "type不能为空"
							}
						}
					},
					serverUrl : {
						validators : {
							notEmpty : {
								message : "serverUrl不能为空"
							}
						}
					},
					postParam : {
						validators : {
							notEmpty : {
								message : "postParam不能为空"
							}
						}
					},
					receiveStatus : {
						validators : {
							notEmpty : {
								message : "receiveStatus不能为空"
							}
						}
					},
					sendStatus : {
						validators : {
							notEmpty : {
								message : "sendStatus不能为空"
							}
						}
					},
					thirdPartyResult : {
						validators : {
							notEmpty : {
								message : "thirdPartyResult不能为空"
							}
						}
					},
					sendContent : {
						validators : {
							notEmpty : {
								message : "sendContent不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
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
					url : _ctx + "/web/appsmssend/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appsmssendlist')
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
				url : _ctx + "/web/appsmssend/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '类型',
					field : 'type',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '是否发送',
					field : 'sendStatus',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, row, index) {
                        if (value == "1") {
                            return "已发送"
                        } else {
                            return "<font color='red'>未发送</font>"
                        }
					}
				},
				{
					title : '短信内容',
					field : 'sendContent',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '请求参数',
					field : 'postParam',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '第三方返回信息',
					field : 'thirdPartyResult',
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