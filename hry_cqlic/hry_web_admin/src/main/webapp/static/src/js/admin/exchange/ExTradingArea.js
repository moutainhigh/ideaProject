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
										tradingArea : {
						validators : {
							notEmpty : {
								message : "交易区不能为空"
							}
						}
					},
					struts : {
						validators : {
							notEmpty : {
								message : "状态不能为空"
							}
						}
					},
					sort : {
						validators : {
							notEmpty : {
								message : "排序不能为空"
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
					url : _ctx + "/exchange/extradingarea/add.do",
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
						if($("#struts").val()==''){
                            layer.msg('状态不能为空!', {icon : 2});
                            return false;
						}
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('添加成功!', {icon : 1});
								loadUrl(_ctx+'/v.do?u=/admin/exchange/extradingarealist')
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
					tradingArea : {
						validators : {
							notEmpty : {
								message : "交易区不能为空"
							}
						}
					},
					struts : {
						validators : {
							notEmpty : {
								message : "状态不能为空"
							}
						}
					},
					sort : {
						validators : {
							notEmpty : {
								message : "排序不能为空"
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
					url : _ctx + "/exchange/extradingarea/modify.do",
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
                        if($("#struts").val()==''){
                            layer.msg('状态不能为空!', {icon : 2});
                            return false;
                        }
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('修改成功!', {icon : 1});
								loadUrl(_ctx+'/v.do?u=/admin/exchange/extradingarealist')
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
				_table.seeRow($("#table"), _ctx + "/exchange/extradingarea/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/extradingarea/modifysee");
			});
            //开启

            $("#open").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if(ids!=undefined&&ids.length==1) {

                    layer.confirm('你确定开启交易区吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/extradingarea/openChange/"+ids,
                            data: { },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("开启成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx+'/v.do?u=/admin/exchange/extradingarealist');
                                    }else {
                                        layer.msg(data.msg, {
                                            icon: 2
                                        })
                                    }
                                }
                            },
                            error: function (e) {

                            }
                        });

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });



            //关闭

            $("#close").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if(ids!=undefined&&ids.length==1) {

                    layer.confirm('你确定关闭交易区吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/extradingarea/closeChange/"+ids,
                            data: { },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("关闭成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx+'/v.do?u=/admin/exchange/extradingarealist');
                                    }else {
                                        layer.msg(data.msg, {
                                            icon: 2
                                        })
                                    }
                                }
                            },
                            error: function (e) {

                            }
                        });

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
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
				url : _ctx + "/exchange/extradingarea/list.do",
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
					title : '交易区',
					field : 'tradingArea',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '状态',
					field : 'struts',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false,
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return "开启"
                        } else {
                            return "关闭";
                        }
                    }
				},
				{
					title : '排序',
					field : 'sort',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});