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
                    messageCategory : {
						validators : {
							notEmpty : {
								message : "请选择系统语种"
							}
						}
					},
                    name : {
						validators : {
							notEmpty : {
								message : "模板名称不能为空"
							}
						}
					},
                    describes : {
                        validators : {
                            notEmpty : {
                                message : "模板内容不能为空"
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
                var ueditor_content = UE.getEditor("ueditor_content").getContent();
				var messageCategory = $("#languageDic").val();
                if (!ueditor_content) {
                    layer.msg("消息正文不能为空", {icon: 2});
                    return;
                }
				var options = {
					url : _ctx + "/web/appmessagecategory/add.do",
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
								loadUrl(_ctx+'/web/appmessagecategory/gotoCategory?messageCategory='+messageCategory)
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
			$("#messageCategory").attr("disabled", "disabled");
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
                                message : "消息类别名称不能为空"
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
                var ueditor_content = UE.getEditor("ueditor_content").getContent();
				var messageCategory = $("#languageDic").val();
                if (!ueditor_content) {
                    layer.msg("消息正文不能为空", {icon: 2});
                    return;
                }
                var messageCategory = $("#languageDic").val();
				var options = {
					url : _ctx + "/web/appmessagecategory/modify.do",
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
								loadUrl(_ctx+'/web/appmessagecategory/gotoCategory?messageCategory='+messageCategory)
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
            //多语种切换查询按钮
            $(".languageQuery").on("click", function() {
                var languageDic=$(this).attr("languageType");
                var params=  {messageCategory :languageDic};
                $("#languageDic").val(languageDic);
                //查询
                _table.tableQuery($("#table"),params);
            });

            // 添加页面跳转
			$("#add").on("click", function() {
				var messageCategory = $("input[name='messageCategory']").val();
				loadUrl(_ctx+"/web/appmessagecategory/gotoAdd?messageCategory="+messageCategory);
			});

			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				var rows = _table.getRowSelects($("#table"));
				var messageCategory = $("input[name='messageCategory']").val();
                if (rows.length == 0) {
                    layer.msg('请选择数据!', {icon : 2});
                    return false;
                } else if (rows.length > 1) {
                    layer.msg('只能选择一条数据!', {icon : 2});
                    return false;
                } else if (rows[0].state == '2') {
                    layer.msg('数据不可编辑!', {icon : 2});
                    return false;
				} else {
					var ids = $.map($("#table").bootstrapTable('getSelections'), function(row) {
						return row.id
					});
					var url = _ctx + "/web/appmessagecategory/modifysee";
					loadUrl(url+"/"+ids[0]+"?messageCategory="+messageCategory);
                }
			});
			// 开启按钮
			$("#open_btn").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                var messageCategory = $("#languageDic").val();
                if (ids.length == 0) {
                    layer.msg('请选择数据', {icon : 2});
                    return false;
                } else {
                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url: _ctx + '/web/appmessagecategory/switchOpen/' + ids,
						data:{
                        	"type" : "open"
						},
                        cache: false,
                        traditional: true,
                        success: function (data) {
                            if (data.success) {
                                layer.msg('开启成功', {icon : 1});
								loadUrl(_ctx+'/web/appmessagecategory/gotoCategory?messageCategory='+messageCategory)
                            } else {
                                layer.msg(data.msg, {icon : 2});
							}
                        }
                    });

                }
			});
			// 关闭按钮
			$("#close_btn").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
				var messageCategory = $("#languageDic").val();
                if (ids.length == 0) {
                    layer.msg('请选择数据', {icon : 2});
                    return false;
                } else {
                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url: _ctx + '/web/appmessagecategory/switchOpen/' + ids,
                        data:{
                            "type" : "close"
                        },
                        cache: false,
                        traditional: true,
                        success: function (data) {
                            if (data.success) {
                                layer.msg('关闭成功', {icon : 1});
								loadUrl(_ctx+'/web/appmessagecategory/gotoCategory?messageCategory='+messageCategory)
                            } else {
                                layer.msg(data.msg, {icon : 2});
                            }
                        }
                    });
                }
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/appmessagecategory/remove.do");
			});
			var conf = {
				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/appmessagecategory/list.do?",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '语种',
					field : 'messageCategory',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '模板名称',
					field : 'name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '开关',
					field : 'isOpen',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, row, index) {
                        if(value == 0){
                            return "关闭"
                        }if(value == 1){
                            return "开启"
                        }
					}
				},
				{
					title : '触发点',
					field : 'triggerPointLan',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,

				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});