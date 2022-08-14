define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		checklist : function() {
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
            // 余额详情
            $("#yueinfo").on("click", function() {
                var row = _table.getRowSelects($("#table"));
                if (row != undefined && row.length == 1) {
                	loadUrl(_ctx+"/reidscheck/yueinfoview/"+row[0].customerId);
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });
            // 余额详情
            $("#yueinfoerror").on("click", function() {
                var row = _table.getRowSelects($("#table"));
                if (row != undefined && row.length == 1) {
                	loadUrl(_ctx+"/reidscheck/yueinfoerrorview/"+row[0].customerId);
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

			//余额核算到数据库
            $("#yue2mysql").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要余额核算到数据库吗?请慎重!', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/reidscheck/culAccountByCustomersureold/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("余额核算到数据库成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=admin/customer/checkaccount')
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
                        layer.closeAll();

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

            //全部错误余额核算
            $("#yueallcheck").on("click", function () {
				layer.open({
					type: 1,
					skin: 'layui-layer-demo', //样式类名
					closeBtn: 1, //不显示关闭按钮
					anim: 2,
					shadeClose: true, //开启遮罩关闭
					area: ['380px', '180px'],
					content: $("#div_yueallcheck")
				});
            });
            //全部错误余额核算 提交
            $("#yueallcheck_submit").on("click", function () {
                	var days = $("#days").val();
                	if(days==undefined||days<1){
                        layer.msg("天数不能小于1", {
                            icon: 2
                        })
                		return ;
					}

					layer.closeAll();
					layer.confirm('可能时间有点长，请耐心等待', {
						btn : [ ] // 按钮
					}, function() {

					});
					$.ajax({
						type: "get",
						url: _ctx + "/reidscheck/culSureOldAccountAllCustomerErrorInfoToRedis?days=" + days,
						data: {},
						cache: false,
						dataType: "json",
						success: function (data) {
                            layer.closeAll();
							if (data) {
								if (data.success) {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    })
									loadUrl(_ctx + '/v.do?u=admin/customer/checkaccount');
								} else {
									layer.msg(data.msg, {
										icon: 1,
									})
								}
							}
						},
						error: function (e) {
                            layer.msg("请求错误", {
                                icon: 2
                            },function () {
                                layer.closeAll();
                            })

						}
					});


            });


			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/customer/appaccount/checklist",
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
					title : '邮箱',
					field : 'appPersonInfo.email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手机号',
					field : 'appPersonInfo.mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '姓氏',
					field : 'appPersonInfo.surname',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '名字',
					field : 'appPersonInfo.trueName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '可用金额',
					field : 'hotMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '冻结金额',
					field : 'coldMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '杠杆金额',
					field : 'lendMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
		//余额详细
        yueinfo : function(customerId){

            $.ajax({
                type: "get",
                url: _ctx + "/reidscheck/culAccountByCustomerErrorAndRightInfosureold/" + customerId,
                data: {},
                cache: false,
                dataType: "json",
                success: function (data) {
                    if(data!=undefined){
                        $("#old_body").empty();
                        $("#new_body").empty();
                        $("#old_body").html(template("tmpl-old-record",{list:data[0].oldAccountFundInfolist}));
                        $("#new_body").html(template("tmpl-new-record",{list:data[0].newAccountFundInfolist}));

                    }
                },
                error: function (e) {

                }
            });
			//资金详情按钮
            $("#openinfo").on("click",function () {

                $.ajax({
                    type: "get",
                    url: _ctx + "/check/culAccountByCustomerErrorAndRightInfosureold/" + customerId,
                    data: {},
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if(data!=undefined){
                            $("#info_old_body").empty();
                            $("#info_new_body").empty();
                            $("#info_old_body").html(template("tmpl-old-record",{list:data[0].oldAccountFundInfolist}));
                            $("#info_new_body").html(template("tmpl-new-record",{list:data[0].newAccountFundInfolist}));

                            $("#info_sureoldlist_body").html(template("tmpl-sureoldlist-record",{hisorylist:data[0].hisorylist,sureoldlist:data[0].sureoldlist}));

                            layer.open({
								title:"资金详情",
                                type: 1,
                                skin: 'layui-layer-demo', //样式类名
                                closeBtn: 1, //不显示关闭按钮
                                anim: 2,
                                shadeClose: true, //开启遮罩关闭
                                area: ['99%', '100%'],
                                content: $("#div_info")
                            });
                        }
                    },
                    error: function (e) {

                    }
                });

            });

        },
		//余额核算错误信息
        yueinfoerror : function(customerId){

            $.ajax({
                type: "get",
                url: _ctx + "/check/culAccountByCustomerErrorInfosureold/" + customerId,
                data: {},
                cache: false,
                dataType: "json",
                success: function (data) {
                    if(data!=undefined){
                        if(data.length == 0){
                            layer.msg('账户无误!', {icon : 1});
                            return false;
                        }
                        debugger
                        $("#old_body").empty();
                        $("#new_body").empty();
                        $("#old_body").html(template("tmpl-old-record",{list:data[0].oldAccountFundInfolist}));
                        $("#new_body").html(template("tmpl-new-record",{list:data[0].newAccountFundInfolist}));

                    }
                },
                error: function (e) {

                }
            });


            //资金详情按钮
            $("#openinfo").on("click",function () {

                $.ajax({
                    type: "get",
                    url: _ctx + "/check/culAccountByCustomerErrorInfosureold/" + customerId,
                    data: {},
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if(data!=undefined){
                            $("#info_old_body").empty();
                            $("#info_new_body").empty();
                            $("#info_old_body").html(template("tmpl-old-record",{list:data[0].oldAccountFundInfolist}));
                            $("#info_new_body").html(template("tmpl-new-record",{list:data[0].newAccountFundInfolist}));

                            $("#info_sureoldlist_body").html(template("tmpl-sureoldlist-record",{hisorylist:data[0].hisorylist,sureoldlist:data[0].sureoldlist}));

                            layer.open({
                                title:"资金详情",
                                type: 1,
                                skin: 'layui-layer-demo', //样式类名
                                closeBtn: 1, //不显示关闭按钮
                                anim: 2,
                                shadeClose: true, //开启遮罩关闭
                                area: ['99%', '100%'],
                                content: $("#div_info")
                            });
                        }
                    },
                    error: function (e) {

                    }
                });

            });

        },
		//全部错误余额信息
        yueinfoallerror : function(){

            $.ajax({
                type: "get",
                url: _ctx + "/check/culAccountAllCustomerErrorInfo",
                data: {},
                cache: false,
                dataType: "json",
                success: function (data) {
                    if(data!=undefined){
                        $("#all_body").empty();
                        $("#all_body").html(template("tmpl-all-record",{list:data}));

                    }
                },
                error: function (e) {

                }
            });


            //资金详情按钮
            $("#all_body").on("click","button[customerid]",function () {
				var customerId = $(this).attr("customerid");
                $.ajax({
                    type: "get",
                    url: _ctx + "/check/culAccountByCustomerErrorInfosureold/" + customerId,
                    data: {},
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if(data!=undefined){
                            $("#info_old_body").empty();
                            $("#info_new_body").empty();
                            $("#info_old_body").html(template("tmpl-old-record",{list:data[0].oldAccountFundInfolist}));
                            $("#info_new_body").html(template("tmpl-new-record",{list:data[0].newAccountFundInfolist}));

                            $("#info_sureoldlist_body").html(template("tmpl-sureoldlist-record",{hisorylist:data[0].hisorylist,sureoldlist:data[0].sureoldlist}));

                            layer.open({
                                title:"资金详情",
                                type: 1,
                                skin: 'layui-layer-demo', //样式类名
                                closeBtn: 1, //不显示关闭按钮
                                anim: 2,
                                shadeClose: true, //开启遮罩关闭
                                area: ['99%', '100%'],
                                content: $("#div_info")
                            });
                        }
                    },
                    error: function (e) {

                    }
                });

            });

        }
	}

});