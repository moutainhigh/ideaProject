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
					serverHost : {
						validators : {
							notEmpty : {
								message : "serverHost不能为空"
							}
						}
					},

					type : {
						validators : {
							notEmpty : {
								message : "type不能为空"
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
                        if($("#sendEmails").val().trim()!=""){
                            if($("#mailConfigId").val()==""){
                                layer.msg("请选择发送邮箱", {icon : 2});
                                return false;
                            }
                        }
				var options = {
					url : _ctx + "/web/appservermonitor/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appservermonitorlist')
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
					serverHost : {
						validators : {
							notEmpty : {
								message : "serverHost不能为空"
							}
						}
					},

					type : {
						validators : {
							notEmpty : {
								message : "type不能为空"
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
				if($("#sendEmails").val().trim()!=""){
                    if($("#mailConfigId").val()==""){
                        layer.msg("请选择发送邮箱", {icon : 2});
                        return false;
					}
				}
				var options = {
					url : _ctx + "/web/appservermonitor/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appservermonitorlist')
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
				_table.seeRow($("#table"), _ctx + "/web/appservermonitor/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/appservermonitor/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/appservermonitor/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/appservermonitor/list.do",
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
						title : '服务名称',
						field : 'serverName',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
				{
					title : '服务地址',
					field : 'serverHost',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '服务端口',
					field : 'serverPort',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '是否启用',
					field : 'isOpen',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(data, type, row) {
                        if(data==1){
                            return "启用中";
                        }else{
                            return "关闭中";
                        }
                    }
				},
				{
					title : '被通知邮箱账号',
					field : 'sendEmails',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'sendPhone',
					field : 'sendPhone',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
                    {
                        title : '上次宕机时间',
                        field : 'failTime',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '服务状态',
                        field : 'serverStruts',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : false,
                        formatter : function(data, row, index) {
                        	if(row.isOpen==1){
                                if(data==1){
                                    return "<image style='width: 20px' src='/admin/static/"+_version+"/img/aaa.png'/>";
                                }else{
                                    return "<image src='/admin/static/"+_version+"/img/serverStop.png'/>";
                                }
							}else{
                                return "<image src='/admin/static/"+_version+"/img/serviceNormal.png'/>";
							}

                        }
                    }
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});