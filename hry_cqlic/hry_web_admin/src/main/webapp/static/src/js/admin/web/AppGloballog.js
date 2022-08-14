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
										log_level : {
						validators : {
							notEmpty : {
								message : "log_level不能为空"
							}
						}
					},
					log_time : {
						validators : {
							notEmpty : {
								message : "log_time不能为空"
							}
						}
					},
					app_id : {
						validators : {
							notEmpty : {
								message : "app_id不能为空"
							}
						}
					},
					log_detail : {
						validators : {
							notEmpty : {
								message : "log_detail不能为空"
							}
						}
					},
					server_ip : {
						validators : {
							notEmpty : {
								message : "server_ip不能为空"
							}
						}
					},
					log_err_location : {
						validators : {
							notEmpty : {
								message : "log_err_location不能为空"
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
					url : _ctx + "/web/appgloballog/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appgloballoglist')
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
					log_level : {
						validators : {
							notEmpty : {
								message : "log_level不能为空"
							}
						}
					},
					log_time : {
						validators : {
							notEmpty : {
								message : "log_time不能为空"
							}
						}
					},
					app_id : {
						validators : {
							notEmpty : {
								message : "app_id不能为空"
							}
						}
					},
					log_detail : {
						validators : {
							notEmpty : {
								message : "log_detail不能为空"
							}
						}
					},
					server_ip : {
						validators : {
							notEmpty : {
								message : "server_ip不能为空"
							}
						}
					},
					log_err_location : {
						validators : {
							notEmpty : {
								message : "log_err_location不能为空"
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
					url : _ctx + "/web/appgloballog/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appgloballoglist')
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
		list : function(name) {

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/appgloballog/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/appgloballog/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/appgloballog/remove.do");
			});



			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/appgloballog/list.do?appName="+name,
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
					searchable : true
				},
				{
					title : '日志级别',
					field : 'log_level',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '时间',
					field : 'log_time',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '应用id',
					field : 'app_id',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
					title : '应用名称',
					field : 'app_name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '日志详情',
					field : 'log_detail',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        return value.substring(0,20)
                    }
				},
				{
					title : '服务器ip',
					field : 'server_ip',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '错误位置',
					field : 'log_err_location',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
                    {
                        title : '查看详情',
                        field : 'log_err_location',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true,
                        formatter: function (value, row, index) {
                            return "<button class='mydetail' id='"+row.log_detail+"'>"+'查看详情';
                        }
                    }
				]
			}
			_table.initTable($("#table"), conf);

            $("#table ").on('click','.mydetail',function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    title: "错误详情",
                    shadeClose: true, //开启遮罩关闭
                    area: ['580px', '222px'],
                    content: $(this).attr('id')
                });

            })
		}


	}

});