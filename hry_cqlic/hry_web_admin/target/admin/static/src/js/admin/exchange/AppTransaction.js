define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//查看页面--初始化方法
		see : function(){
		},

		//列表页面--初始化方法
		list : function() {

		    $("#back").on("click",function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {

                    var rows = _table.getRowSelects($("#table"))[0];
                    $("#rejectOrderId").val(ids)
                    $("#rejectUsername").html("用户名："+rows.appPersonInfo.surname+rows.appPersonInfo.trueName);
                    $("#rejectpostMoney").html("充值金额："+rows.transactionMoney);
                    $('#fnInvalidDiv').removeClass("hide");
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        area: ['380px', '280px'],
                        content: $("#fnInvalidDiv")
                    });

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            $("#rejectConfirm").on("click",function () {
                var ids = $("#rejectOrderId").val();
                var reason = $("#reason").val();
                if(reason==""){
                    layer.msg("驳回原因必须填写", {
                        icon: 1, time: 1500
                    })
                    return
                }else{
                    $.ajax({
                        type: "post",
                        url: _ctx + "/exchange/apptransaction/invalid",
                        data: {
                            id:ids,
                            reason:reason
                        },
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            if (data) {
                                if (data.success) {
                                    loadUrl(_ctx + '/v.do?u=admin/exchange/apptransactionlist')
                                    layer.closeAll();
                                    layer.msg("确认成功", {
                                        icon: 1, time: 1500
                                    })
                                } else {
                                    layer.closeAll();
                                    layer.msg(data.msg, {
                                        icon: 2, time: 1500
                                    })
                                }

                            }
                        },
                        error: function (e) {

                        }
                    });
                }

            })

            $("#confirm").on("click",function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {

                    var rows = _table.getRowSelects($("#table"))[0];
                    $("#orderId").val(ids)
                    $("#username").html("用户名:"+rows.appPersonInfo.surname+rows.appPersonInfo.trueName);
                    $("#postmoney").html("充值金额："+rows.transactionMoney);
                    $("#fee").html("充值手续费："+rows.fee)
                    $("#factmoney").html("实际到账金额："+(rows.transactionMoney-rows.fee))
                    $('#fnConfirmDiv').removeClass("hide");
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        area: ['380px', '280px'],
                        content: $("#fnConfirmDiv")
                    });

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            $("#confirmSubmit").on("click",function () {
                var ids = $("#orderId").val();
                var google_code = $("#google_code").val();

                if(google_code==null&&google_code==''){
                    layer.msg('谷歌验证码不能为空');
                    return false;
                }
                if(isNaN(google_code)){
                    layer.msg('谷歌验证码请输入数字');
                    return false;
                }
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/apptransaction/confirm",
                    data: {
                        id:ids,
                        google_code:google_code
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                                loadUrl(_ctx + '/v.do?u=admin/exchange/apptransactionlist')
                                layer.closeAll();
                                layer.msg("确认成功", {
                                    icon: 1,
                                    time: 1500
                                })
                            } else {
                                layer.closeAll();
                                layer.msg(data.msg, {
                                    icon: 2,
                                    time: 1500
                                })
                            }

                        }
                    },
                    error: function (e) {

                    }
                });
            })

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
				_table.seeRow($("#table"), _ctx + "/exchange/apptransaction/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/apptransaction/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/exchange/apptransaction/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/exchange/apptransaction/list?status=1",
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
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手机',
					field : 'phone',
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
					title : '交易类型',
					field : 'transactionType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '银行名称',
					field : 'bankNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '银行卡号/账号',
					field : 'custromerAccountNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '我方账号',
					field : 'ourAccountNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '充值金额',
					field : 'transactionMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手续费',
					field : 'fee',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '汇款备注',
					field : 'remark',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易订单号',
					field : 'transactionNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '第三方支付',
					field : 'thirdPayName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '申请时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
        //列表页面--初始化方法
        successlist : function(transactionType) {
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
                _table.seeRow($("#table"), _ctx + "/exchange/apptransaction/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/exchange/apptransaction/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function() {
                _table.removeRow($("#table"), _ctx + "/exchange/apptransaction/remove.do");
            });

            var conf = {

                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/exchange/apptransaction/list?status=2&transactionType_in=" + transactionType,
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
                        title : '用户邮箱',
                        field : 'email',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '用户手机',
                        field : 'phone',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '姓氏',
                        field : 'surname',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '名字',
                        field : 'trueName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易类型',
                        field : 'transactionType',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '银行名称',
                        field : 'bankNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '银行卡号/账号',
                        field : 'custromerAccountNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '我方账号',
                        field : 'ourAccountNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '充值金额',
                        field : 'transactionMoney',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '手续费',
                        field : 'fee',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '用户到账金额',
                        field : '',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true,
                        formatter:function (value,row,index) {
                            
                            if (row.fee != undefined) {
                                return row.transactionMoney - row.fee;
                            } else{
                                return row.transactionMoney;
                            }
                        }
                    },
                    {
                        title : '交易订单号',
                        field : 'transactionNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '充值备注',
                        field : 'remark',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '操作时间',
                        field : 'modified',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    }

                ]
            }
            _table.initTable($("#table"), conf);
        },
        //列表页面--初始化方法
        faillist : function() {
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
                _table.seeRow($("#table"), _ctx + "/exchange/apptransaction/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/exchange/apptransaction/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function() {
                _table.removeRow($("#table"), _ctx + "/exchange/apptransaction/remove.do");
            });

            var conf = {

                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/exchange/apptransaction/list?status=3",
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
                        title : '用户邮箱',
                        field : 'email',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '用户手机',
                        field : 'phone',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '姓氏',
                        field : 'surname',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '名字',
                        field : 'trueName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易类型',
                        field : 'transactionType',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '银行名称',
                        field : 'bankNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '银行卡号/账号',
                        field : 'custromerAccountNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '我方账号',
                        field : 'ourAccountNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '充值金额',
                        field : 'transactionMoney',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '充值备注',
                        field : 'remark',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易订单号',
                        field : 'transactionNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '驳回理由',
                        field : 'rejectionReason',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '操作时间',
                        field : 'modified',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },

        //提现申请审核
        wapplylist : function() {
            $("#confirm").on("click",function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {

                    var rows = _table.getRowSelects($("#table"))[0];
                    $("#orderId").val(ids)
                    $("#username").html("用户名:"+rows.appPersonInfo.surname+rows.appPersonInfo.trueName);
                    $("#postmoney").html("充值金额："+rows.transactionMoney);
                    $("#fee").html("充值手续费："+rows.fee)
                    $("#factmoney").html("实际到账金额："+(rows.transactionMoney-rows.fee))
                    $('#fnConfirmDiv').removeClass("hide");
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        area: ['380px', '280px'],
                        content: $("#fnConfirmDiv")
                    });

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })



//取现确认
            $("#confirmSubmit").on("click",function () {
                var ids = $("#orderId").val();
                var google_code = $("#google_code").val();

                if(google_code==null&&google_code==''){
                    layer.msg('谷歌验证码不能为空');
                    return false;
                }
                if(isNaN(google_code)){
                    layer.msg('谷歌验证码请输入数字');
                    return false;
                }
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/apptransaction/confirm",
                    data: {
                        id:ids,
                        google_code:google_code
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                                loadUrl(_ctx + '/v.do?u=admin/exchange/apptransactionlist')
                                layer.closeAll();
                                layer.msg("确认成功", {
                                    icon: 1,
                                    time: 1500
                                })
                            } else {
                                layer.closeAll();
                                layer.msg(data.msg, {
                                    icon: 2,
                                    time: 1500
                                })
                            }

                        }
                    },
                    error: function (e) {

                    }
                });
            })





            //驳回弹出
		    $("#stop").on('click',function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {
                    var rows = _table.getRowSelects($("#table"))
                    $("#orderId").val(rows[0].id);
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        area: ['380px', '180px'],
                        content: $("#div_stop")
                    });




                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })


            //驳回
            $("#confirmstop").on('click',function () {
                var orderId=$("#orderId").val();
                var reason = $("#reason").val();
                if(reason == ""){
                    layer.msg('请输入驳回原因', {icon: 2});
                    return false
                }
                $.ajax({
                    type: "get",
                    url: _ctx + "/exchange/apptransaction/invalid/",
                    data: {
                        id:orderId,
                        reason:reason
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                                layer.msg("驳回成功", {
                                    icon: 1,
                                })
                                loadUrl(_ctx + '/exchange/apptransaction/list?status=1&transactionType_in=4,2')
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
                _table.seeRow($("#table"), _ctx + "/exchange/apptransaction/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/exchange/apptransaction/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function() {
                _table.removeRow($("#table"), _ctx + "/exchange/apptransaction/remove.do");
            });

            var conf = {

                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/exchange/apptransaction/list?status=1&transactionType_in=4,2",
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
                        title : '用户账号',
                        field : 'userName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '姓氏',
                        field : 'surname',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '名字',
                        field : 'trueName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易类型',
                        field : 'transactionType',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '银行名称',
                        field : 'bankNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '银行卡号/账号',
                        field : 'custromerAccountNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '我方账号',
                        field : 'ourAccountNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '充值金额',
                        field : 'transactionMoney',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '充值备注',
                        field : 'remark',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易订单号',
                        field : 'transactionNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '驳回理由',
                        field : 'rejectionReason',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '操作时间',
                        field : 'modified',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },

        //取现成功查询
        wsuccesslist : function() {
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
                _table.seeRow($("#table"), _ctx + "/exchange/apptransaction/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/exchange/apptransaction/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function() {
                _table.removeRow($("#table"), _ctx + "/exchange/apptransaction/remove.do");
            });

            var conf = {

                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/exchange/apptransaction/list?status=2&transactionType_in=4,2",
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
                        title : '用户账号',
                        field : 'userName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '姓氏',
                        field : 'surname',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '名字',
                        field : 'trueName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '账户类型',
                        field : 'currencyType',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '提现银行',
                        field : 'bankName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '提现银行卡号',
                        field : 'custromerAccountNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '所在省',
                        field : 'bankProvince',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '所在市',
                        field : 'bankAddress',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '开户行',
                        field : 'subBank',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '申请时间',
                        field : 'created',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '提现金额',
                        field : 'transactionMoney',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '手续费',
                        field : 'fee',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '用户到账金额',
                        field : 'transactionNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true,
                        formatter:function (value,row,index) {
                            
                            if (row.fee != undefined) {
                                return row.transactionMoney - row.fee;
                            } else{
                                return row.transactionMoney;
                            }
                        }
                    },
                    {
                        title : '交易订单号',
                        field : 'transactionNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '操作时间',
                        field : 'modified',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
//取现失败查询
        wfaillist : function() {
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
                _table.seeRow($("#table"), _ctx + "/exchange/apptransaction/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/exchange/apptransaction/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function() {
                _table.removeRow($("#table"), _ctx + "/exchange/apptransaction/remove.do");
            });

            var conf = {

                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/exchange/apptransaction/list?status=3&transactionType_in=4,2",
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
                        title : '用户账号',
                        field : 'userName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '姓氏',
                        field : 'surname',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '名字',
                        field : 'trueName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '账户类型',
                        field : 'currencyType',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '提现银行卡号',
                        field : 'custromerAccountNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '银行机构代码',
                        field : 'subBankNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '提现金额',
                        field : 'transactionMoney',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易订单号',
                        field : 'transactionNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '驳回理由',
                        field : 'rejectionReason',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '操作时间',
                        field : 'modified',
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