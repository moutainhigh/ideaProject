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
								message : "账户id不能为空"
							}
						}
					},
					customer_email : {
						validators : {
							notEmpty : {
								message : "账户邮箱不能为空"
							}
						}
					},
					customer_mobile : {
						validators : {
							notEmpty : {
								message : "账户手机不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "1收入 ，2 支出不能为空"
							}
						}
					},
					account_type : {
						validators : {
							notEmpty : {
								message : "交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）不能为空"
							}
						}
					},
					coinNum : {
						validators : {
							notEmpty : {
								message : "持币数量不能为空"
							}
						}
					},
					experience : {
						validators : {
							notEmpty : {
								message : "经验值（无正负）不能为空"
							}
						}
					},
					experienceNum : {
						validators : {
							notEmpty : {
								message : "流水号不能为空"
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
								message : "原等级不能为空"
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
								message : "newLevel不能为空"
							}
						}
					},
					upgradeNote : {
						validators : {
							notEmpty : {
								message : "升级备注不能为空"
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
					url : _ctx + "/ico/experience/icoexperience/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/experience/icoexperiencelist')
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
								message : "账户id不能为空"
							}
						}
					},
					customer_email : {
						validators : {
							notEmpty : {
								message : "账户邮箱不能为空"
							}
						}
					},
					customer_mobile : {
						validators : {
							notEmpty : {
								message : "账户手机不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "1收入 ，2 支出不能为空"
							}
						}
					},
					account_type : {
						validators : {
							notEmpty : {
								message : "交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）不能为空"
							}
						}
					},
					coinNum : {
						validators : {
							notEmpty : {
								message : "持币数量不能为空"
							}
						}
					},
					experience : {
						validators : {
							notEmpty : {
								message : "经验值（无正负）不能为空"
							}
						}
					},
					experienceNum : {
						validators : {
							notEmpty : {
								message : "流水号不能为空"
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
								message : "原等级不能为空"
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
								message : "newLevel不能为空"
							}
						}
					},
					upgradeNote : {
						validators : {
							notEmpty : {
								message : "升级备注不能为空"
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
					url : _ctx + "/ico/experience/icoexperience/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/experience/icoexperiencelist')
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
				_table.seeRow($("#table"), _ctx + "/ico/experience/icoexperience/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/ico/experience/icoexperience/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/ico/experience/icoexperience/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/ico/experience/icoexperience/findPageBySql.do",
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
					title : '收支类型',
					field : 'type',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function (value) {

						var aa=$("#type").find("option[value='"+value+"']").text();
						return aa;
                     /*   if(value==1){
                            return "收入"
                        }else{
                            return "支出"
                        }*/
                    }

				},
				{
					title : '交易类型',
					field : 'account_type',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function (value) {
						var aa=$("#account_type").find("option[value='"+value+"']").text();
						return aa;
                    /*    if(value=="0101"){
                            return "锁仓奖励"
                        }else if(value=="0102"){
                            return "注册赠送"
                        }else if(value=="0201"){
                            return "锁仓扣除"
                        }else if(value=="0202"){
                            return "释放扣除"
                        }
*/
                    }
				},
				{
					title : '持币数量',
					field : 'coinNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '经验值',
					field : 'experience',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '流水号',
					field : 'experienceNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '操作时间',
					field : 'created',
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