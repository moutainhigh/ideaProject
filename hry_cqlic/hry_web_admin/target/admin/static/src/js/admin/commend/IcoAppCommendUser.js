define(function(require, exports, module) {
	this._table = require("js/base/table");
	var HryPopup = require("js/base/HryPopup");

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
										pid : {
						validators : {
							notEmpty : {
								message : "pid不能为空"
							}
						}
					},
					pname : {
						validators : {
							notEmpty : {
								message : "pname不能为空"
							}
						}
					},
					uid : {
						validators : {
							notEmpty : {
								message : "uid不能为空"
							}
						}
					},
					username : {
						validators : {
							notEmpty : {
								message : "username不能为空"
							}
						}
					},
					receCode : {
						validators : {
							notEmpty : {
								message : "receCode不能为空"
							}
						}
					},
					sid : {
						validators : {
							notEmpty : {
								message : "sid不能为空"
							}
						}
					},
					maxId : {
						validators : {
							notEmpty : {
								message : "maxId不能为空"
							}
						}
					},
					isFrozen : {
						validators : {
							notEmpty : {
								message : "isFrozen不能为空"
							}
						}
					},
					aloneProportion : {
						validators : {
							notEmpty : {
								message : "aloneProportion不能为空"
							}
						}
					},
					oneNumber : {
						validators : {
							notEmpty : {
								message : "oneNumber不能为空"
							}
						}
					},
					twoNumber : {
						validators : {
							notEmpty : {
								message : "twoNumber不能为空"
							}
						}
					},
					threeNumber : {
						validators : {
							notEmpty : {
								message : "threeNumber不能为空"
							}
						}
					},
					laterNumber : {
						validators : {
							notEmpty : {
								message : "laterNumber不能为空"
							}
						}
					},
					isCulCommend : {
						validators : {
							notEmpty : {
								message : "isCulCommend不能为空"
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
					url : _ctx + "/commend/appcommenduser/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/commend/appcommenduserlist')
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
					pid : {
						validators : {
							notEmpty : {
								message : "pid不能为空"
							}
						}
					},
					pname : {
						validators : {
							notEmpty : {
								message : "pname不能为空"
							}
						}
					},
					uid : {
						validators : {
							notEmpty : {
								message : "uid不能为空"
							}
						}
					},
					username : {
						validators : {
							notEmpty : {
								message : "username不能为空"
							}
						}
					},
					receCode : {
						validators : {
							notEmpty : {
								message : "receCode不能为空"
							}
						}
					},
					sid : {
						validators : {
							notEmpty : {
								message : "sid不能为空"
							}
						}
					},
					maxId : {
						validators : {
							notEmpty : {
								message : "maxId不能为空"
							}
						}
					},
					isFrozen : {
						validators : {
							notEmpty : {
								message : "isFrozen不能为空"
							}
						}
					},
					aloneProportion : {
						validators : {
							notEmpty : {
								message : "aloneProportion不能为空"
							}
						}
					},
					oneNumber : {
						validators : {
							notEmpty : {
								message : "oneNumber不能为空"
							}
						}
					},
					twoNumber : {
						validators : {
							notEmpty : {
								message : "twoNumber不能为空"
							}
						}
					},
					threeNumber : {
						validators : {
							notEmpty : {
								message : "threeNumber不能为空"
							}
						}
					},
					laterNumber : {
						validators : {
							notEmpty : {
								message : "laterNumber不能为空"
							}
						}
					},
					isCulCommend : {
						validators : {
							notEmpty : {
								message : "isCulCommend不能为空"
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
					url : _ctx + "/commend/appcommenduser/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/commend/appcommenduserlist')
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

			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/commend/appcommenduser/icoSee");
			});


			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/commend/appcommenduser/listByIco.do",
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
					title : '邮箱',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手机号',
					field : 'mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '等级',
					field : 'level',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},

				{
					title : '个人资产',
					field : 'asset',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '推荐人数',
					field : 'recommended',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '团队资产',
					field : 'allAsset',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '昨日新增',
					field : 'buyNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false,
                    formatter: function (value, row, index) {
                        return "<button id=\""+row.id+"threeNumber\" rowid=\""+row.id+"\"  class=\"btnOneNumber btn btn-primary btn-block \" >"+value+"</button>";
                    }
				},
				{
					field : 'created',
					title : '记录时间',
					sortable : true,
					align : 'center'
				}
				]
			}
			_table.initTable($("#table"), conf);
		
            $("#table").on('load-success.bs.table',function(data){
                $("#table .btnOneNumber").each(function () {
                    var id = $(this).attr("id");
                    var rowid = $(this).attr("rowid");
                    HryPopup.init("推荐用户个数",id,function(table){
                            var conf = {
                                url : _ctx + "/commend/appcommenduser/newResultsList?customerId="+rowid,
                                columns  :[ {
                                    field : 'state',
                                    checkbox : true,
                                    align : 'center',
                                    valign : 'middle',
                                    value : "id",
                                    searchable : false
                                }, {
                                    title : 'ID',
                                    field : 'id',
                                    align : 'center',
                                    visible : false,
                                    searchable : false
                                }, {
                                    title : '邮箱',
                                    field : 'email',
                                    align : 'center',
                                    visible : true,
                                    sortable : false,
                                    searchable : true
                                },
								{
									title : '手机号',
									field : 'mobilePhone',
									align : 'center',
									visible : true,
									sortable : false,
									searchable : true
								},
								{
									title : '等级',
									field : 'level',
									align : 'center',
									visible : true,
									sortable : false,
									searchable : true
								},
								{
									title : '新增',
									field : 'buyNumber',
									align : 'center',
									visible : true,
									sortable : false,
									searchable : true
								}, {
								field : 'created',
								title : '时间',
								sortable : true,
								align : 'center'
							}]
                            }
                            _table.initTable(table,conf);
                        }

                    );
                })
            });




		},

        //下级资产列表页面--初始化方法
        details : function() {
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

			var customerId=$("#customerId").val();
            var conf = {
                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/commend/appcommenduser/assetsList.do?customerId="+customerId,
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
                        title : '邮箱',
                        field : 'email',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '手机号',
                        field : 'mobilePhone',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '等级',
                        field : 'level',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },

                    {
                        title : '个人资产',
                        field : 'asset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },

                    {
                        field : 'created',
                        title : '注册时间',
                        sortable : true,
                        align : 'center'
                    }
                ]
            }
            _table.initTable($("#table"), conf);

        }


	}

});