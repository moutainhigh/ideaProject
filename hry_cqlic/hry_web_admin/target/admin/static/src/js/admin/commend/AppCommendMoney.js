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
										custromerId : {
						validators : {
							notEmpty : {
								message : "custromerId不能为空"
							}
						}
					},
					custromerName : {
						validators : {
							notEmpty : {
								message : "custromerName不能为空"
							}
						}
					},
					refecode : {
						validators : {
							notEmpty : {
								message : "refecode不能为空"
							}
						}
					},
					moneyNum : {
						validators : {
							notEmpty : {
								message : "moneyNum不能为空"
							}
						}
					},
					paidMoney : {
						validators : {
							notEmpty : {
								message : "paidMoney不能为空"
							}
						}
					},
					fixPriceType : {
						validators : {
							notEmpty : {
								message : "fixPriceType不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
							}
						}
					},
					dealType : {
						validators : {
							notEmpty : {
								message : "dealType不能为空"
							}
						}
					},
					userCode : {
						validators : {
							notEmpty : {
								message : "userCode不能为空"
							}
						}
					},
					commendOne : {
						validators : {
							notEmpty : {
								message : "commendOne不能为空"
							}
						}
					},
					commendTwo : {
						validators : {
							notEmpty : {
								message : "commendTwo不能为空"
							}
						}
					},
					commendThree : {
						validators : {
							notEmpty : {
								message : "commendThree不能为空"
							}
						}
					},
					commendLater : {
						validators : {
							notEmpty : {
								message : "commendLater不能为空"
							}
						}
					},
					lastPaidTime : {
						validators : {
							notEmpty : {
								message : "lastPaidTime不能为空"
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
					url : _ctx + "/commend/appcommendmoney/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/commend/appcommendmoneylist')
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
					custromerId : {
						validators : {
							notEmpty : {
								message : "custromerId不能为空"
							}
						}
					},
					custromerName : {
						validators : {
							notEmpty : {
								message : "custromerName不能为空"
							}
						}
					},
					refecode : {
						validators : {
							notEmpty : {
								message : "refecode不能为空"
							}
						}
					},
					moneyNum : {
						validators : {
							notEmpty : {
								message : "moneyNum不能为空"
							}
						}
					},
					paidMoney : {
						validators : {
							notEmpty : {
								message : "paidMoney不能为空"
							}
						}
					},
					fixPriceType : {
						validators : {
							notEmpty : {
								message : "fixPriceType不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
							}
						}
					},
					dealType : {
						validators : {
							notEmpty : {
								message : "dealType不能为空"
							}
						}
					},
					userCode : {
						validators : {
							notEmpty : {
								message : "userCode不能为空"
							}
						}
					},
					commendOne : {
						validators : {
							notEmpty : {
								message : "commendOne不能为空"
							}
						}
					},
					commendTwo : {
						validators : {
							notEmpty : {
								message : "commendTwo不能为空"
							}
						}
					},
					commendThree : {
						validators : {
							notEmpty : {
								message : "commendThree不能为空"
							}
						}
					},
					commendLater : {
						validators : {
							notEmpty : {
								message : "commendLater不能为空"
							}
						}
					},
					lastPaidTime : {
						validators : {
							notEmpty : {
								message : "lastPaidTime不能为空"
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
					url : _ctx + "/commend/appcommendmoney/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/commend/appcommendmoneylist')
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
				_table.seeRow($("#table"), _ctx + "/commend/appcommendmoney/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/commend/appcommendmoney/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/commend/appcommendmoney/remove.do");
			});

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

			$("#distribut").on('click',function () {
                var ids = _table.getIdSelects($("#table"));
                var row = _table.getRowSelects($("#table"))

                if (ids != undefined && ids.length == 1) {
					if(row[0].moneyNum - row[0].paidMoney <= 0){
                        layer.msg('已经派发所有佣金', {icon: 2});
                        return false;
					}
                    layer.confirm('你确定要派发吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/commend/appcommendmoney/postMoney",
                            data: {
                            	"ids":ids[0]
							},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("派发成功", {
                                            icon: 1,
                                        })
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 2,
                                        })
                                    }

                                    //loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
                                }
                            },
                            error: function (e) {

                            }
                        });

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/commend/appcommendmoney/list.do",
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
					title : '代理商邮箱',
					field : 'appPersonInfo.email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '代理商手机号',
					field : 'appPersonInfo.mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '一级推荐佣金',
					field : 'commendOne',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '二级推荐佣金',
					field : 'commendTwo',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '三级推荐佣金',
					field : 'commendThree',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '三级后推荐佣金',
					field : 'commendLater',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '总佣金',
					field : 'moneyNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '已派发佣金',
					field : 'paidMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '待派发佣金',
					field : 'paidMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false,
                    formatter: function (value, row, index) {
                        return row.moneyNum - row.paidMoney;
                    }

				},
				{
					title : '返佣币种',
					field : 'coinCode',
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