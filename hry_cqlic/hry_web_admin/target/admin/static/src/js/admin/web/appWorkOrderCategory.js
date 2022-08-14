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
					typeName : {
						validators : {
							notEmpty : {
								message : "类别名称不能为空"
							}
						}
					},
					sort : {
						validators : {
							notEmpty : {
								message : "序号不能为空"
							},
                            between: {
                                min: 0,
                                max: 100,
                                message: '请输入0到100的序号'
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
					url : _ctx + "/web/appworkordercategory/add.do",
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
								loadUrl(_ctx+'/language.do?u=/admin/web/appworkordercategorylist')
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
                    typeName : {
                        validators : {
                            notEmpty : {
                                message : "类别名称不能为空"
                            }
                        }
                    },
                    sort : {
                        validators : {
                            notEmpty : {
                                message : "序号不能为空"
                            },
                            between: {
                                min: 0,
                                max: 100,
                                message: '请输入0到100的序号'
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
					url : _ctx + "/web/appworkordercategory/modify.do",
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
								loadUrl(_ctx+'/language.do?u=/admin/web/appworkordercategorylist')
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
				_table.seeRow($("#table"), _ctx + "/web/appworkordercategory/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
                var rows = _table.getRowSelects($("#table"));

                if (rows[0].state == 1) {
                    _table.seeRow($("#table"), _ctx + "/web/appworkordercategory/modifysee");
                }else{
                    layer.msg("数据不可编辑", {icon: 2 })
				}
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
                var rows = _table.getRowSelects($("#table"));
				var flag=true;
				for(var i=0;i<rows.length;i++){
                    if (rows[i].state == 0) {
						flag=false;
						break;
					}
				}
                if (flag) {
                    _table.removeRow($("#table"), _ctx + "/web/appworkordercategory/remove.do");
                }else{
                    layer.msg("系统默认类别不可删除", {icon: 2 })
                }

			});
            //语言切换
            $(".languageQuery").on("click", function() {
                var languageDic=$(this).attr("languageType");
                var params=  {languageDic :languageDic};
                $("#languageDic").val(languageDic);
                //查询
                _table.tableQuery($("#table"),params);
            });
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/appworkordercategory/list.do",
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
				/*{
					title : '排序',
					field : 'sort',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},*/
				{
					title : '类别名称',
					field : 'typeName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '描述',
					field : 'describes',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},

				{
					title : '状态',
					field : 'state',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(data, type, row) {
                        if(data==1){
                            return "可编辑";
                        }else{
                            return "不可编辑";
                        }
                    }
				},

				{
					title : 'languageDic',
					field : 'languageDic',
					align : 'center',
					visible : false,
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
		}
	}

});