define(function(require, exports, module) {
	this._table = require("js/base/table");
    this.md5 = require("js/base/utils/hrymd5");

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
					email : {
						validators : {
							notEmpty : {
								message : "账号不能为空"
							}
//							emailAddress: {
//		                        message: '邮箱格式不正确'
//		                    }
						}
					},
					password : {
						validators : {
							notEmpty : {
								message : "密码不能为空"
							}
						}
					},
					department_id : {
						validators : {
							notEmpty : {
								message : "请选择部门"
							}
						}
					},
					roleid : {
						validators : {
							notEmpty : {
								message : "请选择角色"
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
                $("#password").val(md5.md5($("#password").val()));
				var options = {
					url : _ctx + "/user/appuser/add",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {

						// 手动触发验证
						var bootstrapValidator = jqForm.data('bootstrapValidator');
						bootstrapValidator.validate();
						if (!bootstrapValidator.isValid()) {
                            $("#password").val("");
							return false;
						}
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('添加成功!', {icon : 1});
								loadUrl2Div(_ctx+'/v.do?u=oauth/user/appuserlist','tree_right');
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
					department_id : {
						validators : {
							notEmpty : {
								message : "请选择部门"
							}
						}
					},
					roleid : {
						validators : {
							notEmpty : {
								message : "请选择角色"
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
					url : _ctx + "/user/appuser/modify",
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
								loadUrl2Div(_ctx+'/v.do?u=oauth/user/appuserlist','tree_right');
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}
				};
				$("#form").ajaxSubmit(options);
			});
		},
        //部门调动方法
        modifyOrganization : function() {
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
					department_id : {
						validators : {
							notEmpty : {
								message : "请选择部门"
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
					url : _ctx + "/user/appuser/modifyOrganizationSubmit",
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
								loadUrl2Div(_ctx+'/v.do?u=oauth/user/appuserlist','tree_right');
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}
				};
				$("#form").ajaxSubmit(options);
			});
		},
		//重置密码页面--初始化方法
		resetpw : function() {
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
					password : {
						validators : {
							notEmpty : {
								message : "密码不能为空"
							}
						}
					}

				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			});
			// 修改提交
			$("#resetpwSubmit").on("click", function() {
                $("#password").val(md5.md5($("#password").val()));
				var options = {
					url : _ctx + "/user/appuser/resetpw",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						// 手动触发验证
						var bootstrapValidator = jqForm.data('bootstrapValidator');
						bootstrapValidator.validate();
						if (!bootstrapValidator.isValid()) {
                            $("#password").val("");
							return false;
						}
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('修改成功!', {icon : 1});
								loadUrl2Div(_ctx+'/v.do?u=oauth/user/appuserlist','tree_right');
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}
				};
				$("#form").ajaxSubmit(options);
			});
		},
		//退出旁边的重围密码
        resetpw2 : function() {
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
                    oldpassword : {
                        validators : {
                            notEmpty : {
                                message : "原密码不能为空"
                            }
                        }
                    },
                    password : {
                        validators : {
                            notEmpty : {
                                message : "密码不能为空"
                            }
                        }
                    },
                    repassword : {
                        validators : {
                            notEmpty : {
                                message : "重复密码不能为空"
                            }
                        }
                    }

                },
                // bv校验通过则提交
                submitHandler : function(validator, form, submitButton) {
                }
            });
            // 添加提交
            $("#resetpwSubmit").on("click", function() {
                $("#oldpassword").val(md5.md5($("#oldpassword").val()));
                $("#password").val(md5.md5($("#password").val()));
                $("#repassword").val(md5.md5($("#repassword").val()));
                var options = {
                    url : _ctx + "/user/appuser/resetpw2",
                    type : "post",
                    resetForm : true,// 提交后重置表单
                    dataType : 'json',
                    beforeSubmit : function(formData, jqForm, options) {

                        // 手动触发验证
                        var bootstrapValidator = jqForm.data('bootstrapValidator');
                        bootstrapValidator.validate();
                        if (!bootstrapValidator.isValid()) {
                            $("#oldpassword").val("");
                            $("#password").val("");
                            $("#repassword").val("");
                            return false;
                        }
                    },
                    success : function(data, statusText) {
                        if (data != undefined) {
                            if (data.success) {
                                layer.msg('修改成功!', {icon : 1});
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
		list : function(organizationid) {

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				var ids = _table.getIdSelects($("#table"));
				if(ids!=undefined&&ids.length==1){
					loadUrl2Div(_ctx+'/user/appuser/see/'+ids[0],'tree_right')
				}else{
					layer.msg('请选择一条数据', {icon: 2});
				}
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				var ids = _table.getIdSelects($("#table"));
				if(ids!=undefined&&ids.length==1){
					loadUrl2Div(_ctx+'/user/appuser/modifysee/'+ids[0],'tree_right')
				}else{
					layer.msg('请选择一条数据', {icon: 2});
				}
			});
            // 部门调动
            $("#modifyOrganization").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if(ids!=undefined&&ids.length==1){
                    loadUrl2Div(_ctx+'/user/appuser/modifyOrganization/'+ids[0],'tree_right')
                }else{
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });
            // 重置密码
            $("#resetpw").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if(ids!=undefined&&ids.length==1){
                    loadUrl2Div(_ctx+'/user/appuser/resetpwview/'+ids[0],'tree_right')
                }else{
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				var ids = _table.getIdSelects($("#table"));
				if(ids!=undefined&&ids.length==1){
				
					layer.confirm('你确定要删除吗？', {
		    			btn: ['确定','取消'],
		    			ids : ids[0]
					}, function(){
						$.ajax({
							type : "post",
							url : _ctx+"/user/appuser/remove",
							cache : false,
							dataType : "json",
							data:{
								ids : this.ids
							},
							success : function(data) {
								if(data!=undefined){
									if(data.success){
										layer.msg('删除成功', {icon: 1});
										loadUrl2Div(_ctx+'/v.do?u=oauth/user/appuserlist','tree_right');
									}else{
										layer.msg(data.msg, {icon: 2});
									}
								}else{
									layer.msg("请求无响应", {icon: 2});
								}
							},
							error : function(e) {
								$("#page-wrapper").html("<div class='row'><h1>此路径不存在："+url.substring(url.indexOf("u=")+2)+"</h1></div>");
							}
						});
						
					})
					
				}else{
					layer.msg('请选择一条数据', {icon: 2});
				}
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/user/appuser/list?organizationid="+organizationid,
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
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '账号',
					field : 'username',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '角色',
					field : 'roleName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '部门',
					field : 'departmentName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				
				{
					title : '创建时间',
					field : 'created',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '修改时间',
					field : 'modified',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});