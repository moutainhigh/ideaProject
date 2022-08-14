define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//查看页面--初始化方法
		see : function(){
		},
		//添加页面--初始化方法
		add : function() {
			// 添加提交
			$("#addSubmit").on("click", function() {
                var appMessageTemp = $("#form").serializeArray();

                /* 拼接json数据*/
                var data = [];
                var ids = $("#templateTable td[tid]");
                $.each(ids, function (idx, val) {
                    var id = $(val).attr("tid");
                    var msg = {};
                    msg.templateId = id;
                    for(var i in appMessageTemp){
                        if(appMessageTemp[i].name == 'title_'+id){
                            msg.title = appMessageTemp[i].value;
                        } else if(appMessageTemp[i].name == 'content_'+id){
                            msg.content = appMessageTemp[i].value;
                        }
                    }
                    data.push(msg);
                });

                $.ajax({
                    type: "POST",
                    dataType: "JSON",
                    url: _ctx + '/web/appmessagetemplate/add.do',
                    data: JSON.stringify(data),
                    contentType: 'application/json;charset=utf-8',
                    cache: false,
                    traditional: true,
                    success: function (data) {
                        if (data.success) {
                            layer.msg('添加成功', {icon: 1});
                            loadUrl(_ctx + '/web/appmessagetemplate/toAdd');
                        } else {
                            layer.msg(data.msg, {icon: 2});
                        }
                    }
                });
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
					templateId : {
						validators : {
							notEmpty : {
								message : "templateId不能为空"
							}
						}
					},
					title : {
						validators : {
							notEmpty : {
								message : "title不能为空"
							}
						}
					},
					content : {
						validators : {
							notEmpty : {
								message : "content不能为空"
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
					url : _ctx + "/web/appmessagetemplate/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appmessagetemplatelist')
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
				_table.seeRow($("#table"), _ctx + "/web/appmessagetemplate/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/appmessagetemplate/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/appmessagetemplate/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/appmessagetemplate/list.do",
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
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'templateId',
					field : 'templateId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'title',
					field : 'title',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'content',
					field : 'content',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'saasId',
					field : 'saasId',
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