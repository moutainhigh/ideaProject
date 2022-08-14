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
                    tempName : {
                        validators : {
                            notEmpty : {
                                message : "模板名称不能为空"
                            }
                        }
                    },
                    tempKey : {
                        validators : {
                            notEmpty : {
                                message : "模板类型不能为空"
                            }
                        }
                    },
                    tempContent : {
                        validators : {
                            notEmpty : {
                                message : "模板内容不能为空"
                            }
                        }
                    },

                    tempStatus : {
                        validators : {
                            notEmpty : {
                                message : "启用状态不能为空"
                            }
                        }
                    },
                    mailConfigId : {
                        validators : {
                            notEmpty : {
                                message : "邮箱不能为空"
                            }
                        }
                    },
                    languageDic : {
                        validators : {
                            notEmpty : {
                                message : "语言不能为空"
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
					url : _ctx + "/web/mailtemplate/add.do",
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
								loadUrl(_ctx+'/language.do?u=/admin/web/mailtemplatelist')
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
					tempName : {
						validators : {
							notEmpty : {
								message : "模板名称不能为空"
							}
						}
					},
					tempKey : {
						validators : {
							notEmpty : {
								message : "模板类型不能为空"
							}
						}
					},
					tempContent : {
						validators : {
							notEmpty : {
								message : "模板内容不能为空"
							}
						}
					},

					tempStatus : {
						validators : {
							notEmpty : {
								message : "启用状态不能为空"
							}
						}
					},
					mailConfigId : {
						validators : {
							notEmpty : {
								message : "邮箱不能为空"
							}
						}
					},
                    languageDic : {
						validators : {
							notEmpty : {
								message : "语言不能为空"
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
					url : _ctx + "/web/mailtemplate/modify.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {

                     /*   var value = UE.getEditor("tempContent").getContent();
                        formData.push("tempContent",value);*/

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
								loadUrl(_ctx+'/language.do?u=/admin/web/mailtemplatelist')
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
				_table.seeRow($("#table"), _ctx + "/web/mailtemplate/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/mailtemplate/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/mailtemplate/remove.do");
			});
				//查询按钮
				$(".languageQuery").on("click", function() {
					var languageDic=$(this).attr("languageType");
					var params=  {languageDic :languageDic};
					$("#languageDic").val(languageDic);
					//查询
					_table.tableQuery($("#table"),params);
				});
				//languageDic ='zh_CN'
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/mailtemplate/list.do",
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
					title : '模板名称',
					field : 'tempName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '模板key',
					field : 'tempKey',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(data, type, row) {
                        if(data==1){
                            return "注册模板";
                        }else if(data==2){
                            return "更换邮箱模板";
                        }else if(data==3){
                            return "忘记密码模板";
                        }
                    }
				},
				{
					title : '模板内容',
					field : 'tempContent',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '模板备注',
					field : 'remark',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '启用状态',
					field : 'tempStatus',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(data, type, row) {
                        if(data==1){
                            return "开启";
                        }else{
                            return "关闭";
                        }
                    }
				},
				{
					title : '发送用户',
					field : 'customName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '语言',
					field : 'itemName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : 'saasId',
					field : 'saasId',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}
				]
			}

			_table.initTable($("#table"), conf);
         /*   //初始化查询
           var params=  {languageDic :'zh_CN'};
            _table.tableQuery($("#table"),params);*/
		}
	}

});