define(function (require, exports, module) {
    this._table = require("js/base/table");
    this.md5 = require("js/base/utils/hrymd5");

    module.exports = {
        //查看页面--初始化方法
        see: function () {
        },

        //列表页面--初始化方法
        list: function () {
            //查询按钮
            $("#table_query").on("click", function() {
                var params=  $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"),params);
            });

            //重置按钮
            $("#table_reset").on("click", function() {
                $("#table_query_form")[0].reset();
            });

            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransaction/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransaction/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exdmtransaction/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exdmtransaction/list?status=2&transactionType=1",
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
                        title: '邮箱',
                        field: 'appPersonInfo.email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '手机号',
                        field: 'appPersonInfo.mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '姓氏',
                        field: 'appPersonInfo.surname',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '名字',
                        field: 'appPersonInfo.trueName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '币种名称',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '转入钱包地址',
                        field: 'inAddress',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '充币时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '转入个数',
                        field: 'transactionMoney',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value) {
                            var num = Number(value);
                            return Math.round(num*Math.pow(10,4))/Math.pow(10,4);
                        }
                    },
                    {
                        title: '交易订单号',
                        field: 'transactionNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                    	title: '地址备注',
                    	field: 'remark2',
                    	align: 'center',
                    	visible: true,
                    	sortable: false,
                    	searchable: true
                    },
                    {
                        title: '备注',
                        field: 'remark',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        //列表页面--初始化方法
        wapplylist: function () {
            //查询按钮
            $("#table_query").on("click", function() {
                var params=  $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"),params);
            });

            //重置按钮
            $("#table_reset").on("click", function() {
                $("#table_query_form")[0].reset();
            });
            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransaction/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransaction/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exdmtransaction/remove.do");
            });
            
            $("#post").on('click',function () {
                var ids = _table.getIdSelects($("#table"));
                var rows = _table.getRowSelects($("#table"));
                var row = rows[0];
                if (ids != undefined && ids.length == 1) {
                    $("#postcontent").html('<br>提币个数：' + row.transactionMoney + '个' + '<br>提币手续费：' + row.fee + '个' + '<br>实际到账个数：' + (row.transactionMoney-row.fee) + '个' + '<br>TIPS:提币确认操作不可逆，请确认提币个数。');
                    $("#orderId").val(ids[0])
                    $('#postDiv').removeClass("hide");

                   // $("#postcontent").html("提币确认操作不可逆，请确认无误！");
                    layer.open({
                        type : 1,
                        title : "批量确认通过",
                        closeBtn : 2,
                        area : [ '500px', '300px' ],
                        shadeClose : true,
                        content : $('#postDiv')
                    });
                }else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })


            //批量驳回
            //驳回弹出窗
            $("#stop").on('click',function () {
                var ids = _table.getIdSelects($("#table"));
                var rows = _table.getRowSelects($("#table"));
                var row = rows[0];
                if (ids != undefined) {
                    $("#stopallid").val(ids)
                    var content = "TIPS:驳回操作不可逆，请确认提币操作无效。<br>驳回理由<input type='text' id='allreason'> "
                    $("#stopcontent").html(content)
                    $("#stopDiv").removeClass("hide");
                    layer.open({
                        type : 1,
                        title : "提币驳回",
                        closeBtn : 2,
                        area : [ '500px', '300px' ],
                        shadeClose : true,
                        content : $("#stopDiv")
                    });
                }else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })



            //驳回弹出窗
            $("#stop").on('click',function () {
                var ids = _table.getIdSelects($("#table"));
                var rows = _table.getRowSelects($("#table"));
                var row = rows[0];
                if (ids != undefined && ids.length == 1) {
                    $("#stopid").val(ids[0])
                var content = "<br>用户账号:"+row.username+"<br>提币个数:"+row.transactionMoney+"<br>驳回理由<input type='text' id='reason'> <br>TIPS:驳回操作不可逆，请确认提币操作无效。"
                $("#stopcontent").html(content)
                    $("#stopDiv").removeClass("hide");
                    layer.open({
                        type : 1,
                        title : "提币驳回",
                        closeBtn : 2,
                        area : [ '500px', '300px' ],
                        shadeClose : true,
                        content : $("#stopDiv")
                    });
                }else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            //驳回
            $("#confirmstop").on('click',function () {
                var ids = $("#stopid").val()
                var reason = $('#reason').val();
                if(reason==''){
                    layer.msg('驳回原因不能为空');
                    return false;
                }
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/exdmtransaction/stop/"+ids,
                    data: {
                        reason:reason
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("审核成功", {
                                    icon: 1,
                                })
                                loadUrl(_ctx + '/v.do?u=admin/exchange/exdmtransactionWapplyList')
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

            $("#confirmpost").on("click",function () {
                var google_code = $("#google_code").val();

                if(google_code==null&&google_code==''){
                    layer.msg('谷歌验证码不能为空');
                    return false;
                }
                if(isNaN(google_code)){
                    layer.msg('谷歌验证码请输入数字');
                    return false;
                }
                var id = $("#orderId").val();

                $.ajax({
                    type: "get",
                    url: _ctx + "/exchange/exdmtransaction/post",
                    data: {
                        ids : id,
                        google_code : google_code
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("审核成功", {
                                    icon: 1,
                                })
                                loadUrl(_ctx + '/v.do?u=admin/exchange/exdmtransactionWapplyList')
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
                url: _ctx + "/exchange/exdmtransaction/list?status=1&transactionType=2",
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
                        title: '邮箱',
                        field: 'appPersonInfo.email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '手机号',
                        field: 'appPersonInfo.mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '姓氏',
                        field: 'appPersonInfo.surname',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '名字',
                        field: 'appPersonInfo.trueName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '币种名称',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '提币钱包地址',
                        field: 'inAddress',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '申请时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '提币个数',
                        field: 'transactionMoney',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value) {
                            var num = Number(value);
                            return Math.round(num*Math.pow(10,4))/Math.pow(10,4);
                        }
                    },
                    {
                        title: '手续费',
                        field: 'fee',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value) {
                            var num = Number(value);
                            return Math.round(num*Math.pow(10,4))/Math.pow(10,4);
                        }
                    },
                    {
                        title: '交易订单号',
                        field: 'transactionNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '备注',
                        field: 'remark',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        //列表页面--初始化方法
        faillist: function () {
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });

            //重置按钮
            $("#table_reset").on("click", function () {
                $("#table_query_form")[0].reset();
            });
            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransaction/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransaction/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exdmtransaction/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exdmtransaction/list?status=3&transactionType=2",
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
                        title: '邮箱',
                        field: 'appPersonInfo.email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '手机号',
                        field: 'appPersonInfo.mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if(row.appPersonInfo != null && row.appPersonInfo != undefined && row.appPersonInfo != ""){
                                if (row.appPersonInfo.mobilePhone != undefined && row.appPersonInfo.mobilePhone != "" && row.appPersonInfo.mobilePhone != null) {
                                    return row.appPersonInfo.country + row.appPersonInfo.mobilePhone;
                                }
                            }
                            return "";
                        }
                    },
                    {
                        title: '姓氏',
                        field: 'appPersonInfo.surname',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '名字',
                        field: 'appPersonInfo.trueName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '币种名称',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '提币钱包地址',
                        field: 'inAddress',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '申请时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '提币个数',
                        field: 'transactionMoney',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value) {
                            var num = Number(value);
                            return Math.round(num*Math.pow(10,4))/Math.pow(10,4);
                        }
                    },
                    {
                        title: '交易订单号',
                        field: 'transactionNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '备注',
                        field: 'remark',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },

        //列表页面--初始化方法
        successlist: function () {
            //查询按钮
            $("#table_query").on("click", function() {
                var params=  $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"),params);
            });

            //重置按钮
            $("#table_reset").on("click", function() {
                $("#table_query_form")[0].reset();
            });
            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransaction/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransaction/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exdmtransaction/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exdmtransaction/list?status=2&transactionType=2",
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
                        title: '邮箱',
                        field: 'appPersonInfo.email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '手机号',
                        field: 'appPersonInfo.mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '姓氏',
                        field: 'appPersonInfo.surname',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '名字',
                        field: 'appPersonInfo.trueName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '币种名称',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '提币钱包地址',
                        field: 'inAddress',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '申请时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '提币个数',
                        field: 'transactionMoney',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value) {
                            var num = Number(value);
                            return Math.round(num*Math.pow(10,4))/Math.pow(10,4);
                        }
                    },
                    {
                        title: '手续费',
                        field: 'fee',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value) {
                            var num = Number(value);
                            return Math.round(num*Math.pow(10,4))/Math.pow(10,4);
                        }
                    },
                    {
                        title: '交易订单号',
                        field: 'transactionNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '地址备注',
                        field: 'remark2',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '备注',
                        field: 'remark',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        }
    }

});