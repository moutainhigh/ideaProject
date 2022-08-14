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
										accountName : {
						validators : {
							notEmpty : {
								message : "账户名称不能为空"
							}
						}
					},
					changeMoney : {
						validators : {
							notEmpty : {
								message : "变动金额不能为空"
							}
						}
					},
					changeType : {
						validators : {
							notEmpty : {
								message : "变动类型：1加 2减不能为空"
							}
						}
					},
					triggered : {
						validators : {
							notEmpty : {
								message : "触发点不能为空"
							}
						}
					},
					orderNum : {
						validators : {
							notEmpty : {
								message : "触发点订单号不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "备注不能为空"
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
					url : _ctx + "/klg/buysellaccount/klgbuysellaccountrecord/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/buysellaccount/klgbuysellaccountrecordlist')
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
					accountName : {
						validators : {
							notEmpty : {
								message : "账户名称不能为空"
							}
						}
					},
					changeMoney : {
						validators : {
							notEmpty : {
								message : "变动金额不能为空"
							}
						}
					},
					changeType : {
						validators : {
							notEmpty : {
								message : "变动类型：1加 2减不能为空"
							}
						}
					},
					triggered : {
						validators : {
							notEmpty : {
								message : "触发点不能为空"
							}
						}
					},
					orderNum : {
						validators : {
							notEmpty : {
								message : "触发点订单号不能为空"
							}
						}
					},
					remark : {
						validators : {
							notEmpty : {
								message : "备注不能为空"
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
					url : _ctx + "/klg/buysellaccount/klgbuysellaccountrecord/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/buysellaccount/klgbuysellaccountrecordlist')
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
			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/buysellaccount/klgbuysellaccountrecord/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/buysellaccount/klgbuysellaccountrecord/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/klg/buysellaccount/klgbuysellaccountrecord/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/buysellaccount/klgbuysellaccountrecord/list.do",
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
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '账户名称',
					field : 'accountName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
                    	if(value=="buySurplusAccount"){
                    		return "支付剩余账户";
                    	}else if(value=="sellSurplusAccount"){
                    		return "卖出剩余账户";
                    	}
                    	return "-";
                    }
				},
				{
					title : '变动金额',
					field : 'changeMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
                    	if(row.accountName=="buySurplusAccount"){
                    		return value+"USDT";
                    	}else if(row.accountName=="sellSurplusAccount"){
                    		return value+"SME";
                    	}
                    	return "-";
                    }
				},
				{
					title : '变动类型',
					field : 'changeType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
                    	if(value==1){
                    		return "增加";
                    	}else if(value==2){
                    		return "减少";
                    	}
                    	return "--";
                    }
				},
				{
					title : '触发点',
					field : 'triggered',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '触发点订单号',
					field : 'orderNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '备注',
					field : 'remark',
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