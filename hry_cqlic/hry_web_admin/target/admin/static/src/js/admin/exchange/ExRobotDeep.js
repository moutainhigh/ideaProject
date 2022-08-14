define(function(require, exports, module) {
	this._table = require("js/base/table");
    this.md5 = require("js/base/utils/hrymd5");
	module.exports = {
		//查看页面--初始化方法
		see : function(){
		},

        //深度机器人参数配置
        deepparamlist: function () {
            //重置按钮
            $("#table_reset").on("click", function () {
                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotdeepparamlist');
            });
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exrobotdeep/modifysee");
            });
            $("#test").on("click", function () {
                var ids = _table.getIdSelects($("#table"));


                if (ids != undefined && ids.length == 1) {

                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        title:"正在连接。。。",
                        shadeClose: true, //开启遮罩关闭
                        area: ['280px', '222px'],
                        content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/'+_version+'/img/testCoin.gif" style="margin-top:65px;"></span>'
                    });

                    $.ajax({
                        type: "get",
                        url: _ctx + "/exchange/exrobot/testApi/" + ids,
                        data: {},
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            layer.closeAll();
                            if (data) {
                                layer.msg(data.msg, {
                                    icon: 1,
                                })
                            }
                        },
                        error: function (e) {

                        }
                    });

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }




            })

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exrobotdeep/list?robotType=1",
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },
                    {
                        title: 'id',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        sortable: false,
                        searchable: false
                    },
                    {
                        title: '交易币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易区',
                        field: 'fixPriceCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '委买目标档数',
                        field: 'buyDeep',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '委卖目标档数',
                        field: 'sellDeep',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '委量百分比上限（%）',
                        field: 'everyEntrustCount',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '委量绝对值上限',
                        field: 'stopLine',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                    ,
                    {
                        title: '买1差值率（%）',
                        field: 'buyOneDiffRate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '卖1差值率（%）',
                        field: 'sellOneDiffRate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }

                ]
            }
            _table.initTable($("#table"), conf);
        },
        //深度机器人监控
        deepmonitorlist: function () {
            //重置按钮
            $("#table_reset").on("click", function () {
                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotdeepmonitorlist');
            });
            $("#resetdata").on("click", function () {
                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotdeepmonitorlist');
            });

            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });
            $("#cleanKline").on("click",function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length > 0) {
                    layer.confirm('你确定清空吗?', {
                        btn: ['确定', '取消'],
                        ids: ids
                    }, function () {


                        $.ajax({
                            type: "post",
                            url: _ctx + "/exchange/exrobot/cleanKline?ids="+ids,
                            data: {

                            },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.closeAll();
                                    if (data.success) {
                                        layer.msg("清空成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotmonitorlist');
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 1,
                                        })
                                    }
                                }
                            },
                            error: function (e) {

                            }
                        });
                    });


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            $("#open").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length > 0) {
                    var rows = _table.getRowSelects($("#table"))
                    var coin = rows[0].coinCode+"_"+rows[0].fixPriceCoinCode
                    layer.confirm('你确定开启'+coin+'自动交易吗?', {
                        btn: ['确定', '取消'],
                        ids: ids
                    }, function () {
                        layer.open({
                            type: 1,
                            skin: 'layui-layer-demo', //样式类名
                            closeBtn: 1, //不显示关闭按钮
                            anim: 2,
                            title:"正在开启。。。",
                            shadeClose: true, //开启遮罩关闭
                            area: ['280px', '222px'],
                            content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/'+_version+'/img/testCoin.gif" style="margin-top:65px;"></span>'
                        });

                        $.ajax({
                            type: "post",
                            url: _ctx + "/exchange/exrobotdeep/startAuto?ids="+ids,
                            data: {

                            },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.closeAll();
                                    if (data.success) {
                                        layer.msg("开启成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotdeepmonitorlist');
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 2,
                                        })
                                    }
                                }
                            },
                            error: function (e) {

                            }
                        });
                    });


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            $("#confirmOpen").on("click", function () {
                var ids = $("#ids").val();
                var pwd = $("#password").val();


            })

            $("#close").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length > 0) {
                    var rows = _table.getRowSelects($("#table"))
                    var coin = rows[0].coinCode+"_"+rows[0].fixPriceCoinCode
                    layer.confirm('你确定关闭'+coin+'自动交易吗?', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/exrobotdeep/closeAuto/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("关闭成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotdeepmonitorlist');
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 2,
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

            })



            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exrobot/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exrobot/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exrobot/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exrobotdeep/list?robotType=1",
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },
                    {
                        title: 'id',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        sortable: false,
                        searchable: false
                    },
                    {
                        title: '交易币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易区',
                        field: 'fixPriceCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '运行状态',
                        field: 'isSratAuto',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "<span style='color: green'>运行中</span>"
                            } else {
                                return "<span style='color: red'>已关闭</span>";
                            }
                        }
                    },
                    {
                        title: '盘口买1差值率',
                        field: 'buyOneDiffRate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '盘口卖1差值率',
                        field: 'sellOneDiffRate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '机器人委买待成交量',
                        field: 'buyingNumer',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '机器人委卖待成交量',
                        field: 'sellingNumer',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true


                    },
                    {
                        title: '机器人委买已成交量',
                        field: 'buyedNumer',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true


                    },
                    {
                        title: '机器人委卖已成交量',
                        field: 'selledNumer',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);

            $("#table").on('load-success.bs.table',function(data){

            });

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
										coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
							}
						}
					},
					fixPriceCoinCode : {
						validators : {
							notEmpty : {
								message : "fixPriceCoinCode不能为空"
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
					robotType : {
						validators : {
							notEmpty : {
								message : "robotType不能为空"
							}
						}
					},
					isSratAuto : {
						validators : {
							notEmpty : {
								message : "isSratAuto不能为空"
							}
						}
					},
					autoUsername : {
						validators : {
							notEmpty : {
								message : "autoUsername不能为空"
							}
						}
					},
					customerId : {
						validators : {
							notEmpty : {
								message : "customerId不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					buyDeep : {
						validators : {
							notEmpty : {
								message : "buyDeep不能为空"
							}
						}
					},
					sellDeep : {
						validators : {
							notEmpty : {
								message : "sellDeep不能为空"
							}
						}
					},
					everyEntrustCount : {
						validators : {
							notEmpty : {
								message : "everyEntrustCount不能为空"
							}
						}
					},
					stopLine : {
						validators : {
							notEmpty : {
								message : "stopLine不能为空"
							}
						}
					},
					buyOneDiffRate : {
						validators : {
							notEmpty : {
								message : "buyOneDiffRate不能为空"
							}
						}
					},
					sellOneDiffRate : {
						validators : {
							notEmpty : {
								message : "sellOneDiffRate不能为空"
							}
						}
					},
					openTime : {
						validators : {
							notEmpty : {
								message : "openTime不能为空"
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
					url : _ctx + "/exchange/exrobotdeep/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/exrobotdeeplist')
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
			//下拉框默认选项
			function initSelected(){
                $("#buyDeep option[value='"+$("#buyDeep").attr("value")+"']").attr("selected",true)
                $("#sellDeep option[value='"+$("#sellDeep").attr("value")+"']").attr("selected",true)
                $("#everyEntrustCount option[value='"+$("#everyEntrustCount").attr("value")+"']").attr("selected",true)
                $("#stopLine option[value='"+$("#stopLine").attr("value")+"']").attr("selected",true)
                $("#buyOneDiffRate option[value='"+$("#buyOneDiffRate").attr("value")+"']").attr("selected",true)
                $("#sellOneDiffRate option[value='"+$("#sellOneDiffRate").attr("value")+"']").attr("selected",true)
			}
            initSelected();



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
					coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
							}
						}
					},
					fixPriceCoinCode : {
						validators : {
							notEmpty : {
								message : "fixPriceCoinCode不能为空"
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
					robotType : {
						validators : {
							notEmpty : {
								message : "robotType不能为空"
							}
						}
					},
					isSratAuto : {
						validators : {
							notEmpty : {
								message : "isSratAuto不能为空"
							}
						}
					},
					autoUsername : {
						validators : {
							notEmpty : {
								message : "autoUsername不能为空"
							}
						}
					},
					customerId : {
						validators : {
							notEmpty : {
								message : "customerId不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					buyDeep : {
						validators : {
							notEmpty : {
								message : "buyDeep不能为空"
							}
						}
					},
					sellDeep : {
						validators : {
							notEmpty : {
								message : "sellDeep不能为空"
							}
						}
					},
					everyEntrustCount : {
						validators : {
							notEmpty : {
								message : "everyEntrustCount不能为空"
							}
						}
					},
					stopLine : {
						validators : {
							notEmpty : {
								message : "stopLine不能为空"
							}
						}
					},
					buyOneDiffRate : {
						validators : {
							notEmpty : {
								message : "buyOneDiffRate不能为空"
							}
						}
					},
					sellOneDiffRate : {
						validators : {
							notEmpty : {
								message : "sellOneDiffRate不能为空"
							}
						}
					},
					openTime : {
						validators : {
							notEmpty : {
								message : "openTime不能为空"
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
					url : _ctx + "/exchange/exrobotdeep/modify.do",
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
								loadUrl(_ctx+'/v.do?u=admin/exchange/exrobotdeepparamlist')
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}
				};
				$("#form").ajaxSubmit(options);
			});
		},

        //交易账号设置
        accountinfolist: function (robotType) {
            //重置按钮
            $("#table_reset").on("click", function () {
                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotdeepinfolist');
            });
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });

            //批量设定账号
            $("#setMoreAccount").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length>0) {
                    $("#ids").val(ids)
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        title:"交易账号设定",
                        shadeClose: true, //开启遮罩关闭
                        area: ['480px', '222px'],
                        content: $("#setAccountDiv")
                    });


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            //提交设定账号
            $("#confirmset").on("click", function () {
                var ids = $("#ids").val();
                var account = $("#account").val();
                var pwd = $("#accountpwd").val();
                if(account==""){
                    layer.msg("交易账号不能为空", {
                        icon: 2,
                    })
                    return ;
                }
                if(pwd==""){
                    layer.msg("密码不能为空", {
                        icon: 2,
                    })
                    return ;
                }
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/exrobotdeep/setAccount",
                    data: {
                        id: ids,
                        account: account,
                        pwd:md5.md5(pwd)
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("设定成功", {
                                    icon: 1,
                                })
                                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotdeepinfolist');
                            } else {
                                layer.msg(data.msg, {
                                    icon: 2,
                                })
                            }
                        }
                    },
                    error: function (e) {

                    }
                });

            })


            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exrobotdeep/accountInfoList?robotType="+robotType,
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },
                    {
                        title: 'id',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        sortable: false,
                        searchable: false
                    },
                    {
                        title: '交易币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易币种余额',
                        field: 'coinCodeNumer',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                    ,
                    {
                        title: '交易区',
                        field: 'fixPriceCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }

                    ,
                    {
                        title: '交易区余额',
                        field: 'fixCoinCodeNumer',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },

                    {
                        title: '交易账号手机号',
                        field: 'mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易账号邮箱',
                        field: 'email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },

        //历史k线构建
        historybuild: function (robotType) {
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });

            $("#table").on("click",'.build',function () {
                $("#ids").val($(this).attr("buildCoin"))
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    title:"构建历史"+$(this).attr("buildCoin")+"数据",
                    shadeClose: true, //开启遮罩关闭
                    area: ['380px', '322px'],
                    content: $("#buildHistoryDiv")
                });
            })

            $("#confirmBuild").on("click",function () {
                var start = $("#startTime").val()
                var end = $("#endTime").val()
                var id = $("#ids").val()
                if(start==""){
                    layer.msg("开始时间必选", {
                        icon: 2,
                    })
                    return false;
                }
                if(end==""){
                    layer.msg("结束时间必选", {
                        icon: 2,
                    })
                    return false;
                }

                $.ajax({
                    type: "post",
                    url: _ctx + "/klineSectionSave",
                    data: {
                        startTime : start,
                        endTime : end,
                        tradeCoinCode : id.split("/")[0],
                        priceCoinCode : id.split("/")[1],
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            //layer.closeAll();
                            layer.msg(data.msg, {
                                icon: 1,
                            })
                        }
                    },
                    error: function (e) {
                    }
                });

            })


            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exrobotdeep/accountInfoList?robotType="+robotType,
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },
                    {
                        title: 'id',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        sortable: false,
                        searchable: false
                    },
                    {
                        title: '交易币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },

                    {
                        title: '交易区',
                        field: 'fixPriceCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }

                    ,

                    {
                        title: '构建',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            return "<button class='build' buildCoin='"+row.coinCode+"/"+row.fixPriceCoinCode+"'>确认构建</button>"
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },

		//列表页面--初始化方法
		list : function() {

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/exrobotdeep/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/exrobotdeep/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/exchange/exrobotdeep/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/exchange/exrobotdeep/list.do",
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
					title : 'coinCode',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'fixPriceCoinCode',
					field : 'fixPriceCoinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'fixPriceType',
					field : 'fixPriceType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'robotType',
					field : 'robotType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'isSratAuto',
					field : 'isSratAuto',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'autoUsername',
					field : 'autoUsername',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'customerId',
					field : 'customerId',
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
				},
				{
					title : 'buyDeep',
					field : 'buyDeep',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'sellDeep',
					field : 'sellDeep',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'everyEntrustCount',
					field : 'everyEntrustCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'stopLine',
					field : 'stopLine',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'buyOneDiffRate',
					field : 'buyOneDiffRate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'sellOneDiffRate',
					field : 'sellOneDiffRate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'openTime',
					field : 'openTime',
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