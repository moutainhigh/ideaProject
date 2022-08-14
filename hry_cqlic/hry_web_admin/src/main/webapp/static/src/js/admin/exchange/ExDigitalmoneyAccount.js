define(function (require, exports, module) {
    this._table = require("js/base/table");
    this.HrySelect = require("js/base/HrySelect");
    var HryPopup = require("js/base/HryPopup");
    module.exports = {
        //查看页面--初始化方法
        init: function () {
            // 调用ajax查询释放方式下拉选值
            var list = HrySelect.getKey("lockReleaseMethod");
            if (list) {
                $("#releaseMethod").append("<option value='' >请选择</option>");
                $.each(list, function(idx, val) {
                    $("#releaseMethod").append("<option value='" + val.value + "' >" + val.text + "</option>");
                });
            }
        },
        //列表页面--初始化方法
        list: function () {

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


            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdigitalmoneyaccount/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdigitalmoneyaccount/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exdigitalmoneyaccount/remove.do");
            });
            // 手动锁仓操作
            $("#manualLock").on("click", function () {
                var rows = _table.getRowSelects($("#table"));
                if (rows && rows.length == 1) {
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        area: ['380px', '260px'],
                        content: $("#manualLockDiv")
                    });
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

            $("#manualLockBtn").on("click", function () {
                var rows = _table.getRowSelects($("#table"));
                var hotMoney = rows[0].hotMoney; // 可用余额
                var coinCode = rows[0].coinCode; // 币种类型
                var customerId = rows[0].customerId; // 用户id
                var coldNum = $("#lockNum").val(); // 锁仓数量
                var lockCycle = $("#lockCycle").val(); // 锁仓期
                var releaseMethod = $("#releaseMethod").val(); // 释放方式
                var releaseMethodVal = $("#releaseMethodVal").val(); // 释放方式值
                // 验证锁仓数量
                if (!coldNum) {
                    layer.msg('请输入锁仓数量', {icon: 2});
                    return false;
                }
                var regNum = /^\d+(\.\d+)?$/;
                if(!regNum.test(coldNum)){
                    layer.msg('请输入有效格式的锁仓数量', {icon: 2});
                    return false;
                }
                if (Number(coldNum) > Number(hotMoney)) {
                    layer.msg('请输入有效格式的锁仓数量', {icon: 2});
                    return false;
                }

                // 验证锁仓期
                if (!lockCycle){
                    layer.msg('请输入锁仓期', {icon: 2});
                    return false;
                }
                var cycleNum = /^[0-9]+$/;
                if (!cycleNum.test(lockCycle)) {
                    layer.msg('请输入有效格式的锁仓期', {icon: 2});
                    return false;
                }
                if (cycleNum < 1) {
                    layer.msg("锁仓期不能小于1天", {icon : 2});
                    return false;
                }

                // 验证释放方式值
                if (!releaseMethod){
                    layer.msg('请选择释放方式', {icon: 2});
                    return false;
                }
                if (!releaseMethodVal){
                    layer.msg('请输入释放方式值', {icon: 2});
                    return false;
                }
                var regu = /^[1-9]\d*$/; // 正整数
                if (!regu.test(releaseMethodVal)) {
                    layer.msg("请输入有效的释放方式值", {icon : 2});
                    return false;
                }

                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/exdigitalmoneyaccount/manualLock",
                    data: {
                        accountId: rows[0].id,
                        coinCode: coinCode, // 币种类型
                        customerId: customerId, // 用户id
                        coldNum: coldNum, // 锁仓数量
                        lockCycle: lockCycle, // 锁仓期
                        releaseMethod: releaseMethod, // 释放方式
                        releaseMethodVal: releaseMethodVal
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                                layer.closeAll();
                                layer.msg("手动锁仓成功", {icon: 1});
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exdigitalmoneyaccountlist')
                            } else {
                                layer.closeAll();
                                layer.msg(data.msg, {icon: 2});

                            }
                        } else {
                            layer.closeAll();
                            layer.msg("系统错误，请联系管理员", {icon: 2})

                        }
                    },
                    error: function (e) {

                    }
                });

            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exdigitalmoneyaccount/list.do",
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
                        title: '邮箱号',
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
                        title: '钱包地址',
                        field: 'publicKey',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '虚拟账号',
                        field: 'accountNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '可用币个数',
                        field: 'hotMoney',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '冻结币个数',
                        field: 'coldMoney',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '查看明细',
                        field: 'customerId',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: false,
                        formatter: function (value, row, index) {
                           // return value;
                            return "<button id=\""+row.customerId+row.coinCode+"\" coinCode=\""+row.coinCode+"\"    rowid=\""+row.customerId+"\"  class=\"btnOneNumber btn btn-primary btn-block \" >查看明细</button>";
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);


            //查看明细
            $("#table").on('load-success.bs.table',function(data){
                $("#table .btnOneNumber").each(function () {
                    var id = $(this).attr("id");
                    var rowid = $(this).attr("rowid");
                    var coinCode = $(this).attr("coinCode");
                    HryPopup.init("查看明细",id,function(table){
                        var conf = {
                            url : _ctx + "/licqb/record/dealrecord/findDealRecordList.do?customerId="+rowid+"&coinCode="+coinCode,
                            columns  :[ {
                                field : 'state',
                                checkbox : true,
                                align : 'center',
                                valign : 'middle',
                                value : "id",
                                searchable : false
                            },{
                                title : '流水号',
                                field : 'transactionNum',
                                align : 'center',
                                visible : true,
                                sortable : false,
                                searchable : true
                            },{
                                    title : '币种Code',
                                    field : 'coinCode',
                                    align : 'center',
                                    visible : true,
                                    sortable : false,
                                    searchable : true
                                },
                                {
                                    title : '交易量',
                                    field : 'dealMoney',
                                    align : 'center',
                                    visible : true,
                                    sortable : false,
                                    searchable : true
                                },
                                {
                                    title : '交易方式',
                                    field : 'dealType',
                                    align : 'center',
                                    visible : true,
                                    sortable : false,
                                    searchable : true,
                                    formatter: function (value, row, index) {
                                        if (value == "1") {
                                            return "静态收益"
                                        } else if(value == "2"){
                                        	return "见点奖"
                                        } else if(value == "3"){
                                            return "管理奖"
                                        } else if(value == "4"){
                                            return "级别奖"
                                        } else if(value == "5"){
                                            return "共建社区奖励"
                                        } else if(value == "6"){
                                            return "出局"
                                        } else if(value == "7"){
                                            return "兑换收入"
                                        } else if(value == "8"){
                                            return "充币"
                                        } else if(value == "9"){
                                            return "周释放"
                                        } else if(value == "10"){
                                            return "月释放"
                                        } else if(value == "11"){
                                            return "年释放"
                                        } else if(value == "12"){
                                            return "兑换支出";
                                        } else if(value == "13") {
                                            return "理财"
                                        }  else if(value == "14"){
                                            return ""
                                        } else if(value == "15"){
                                            return "充币"
                                        }
                                        return "-";
                                    }
                                },
                                {
                                    title : '状态',
                                    field : 'dealType',
                                    align : 'center',
                                    visible : true,
                                    sortable : false,
                                    searchable : true,
                                    formatter: function (value, row, index) {
                                        var type1 = "1,2,3,4,7,9,10,11,";
                                        var type2 = "6,12,";
                                        var type3 = "5,8,13,";
                                        value = value+",";
                                        if (type1.indexOf(value) != -1) {
                                            return "可用"
                                        } else if (type2.indexOf(value) != -1){
                                            return "消耗"
                                        } else if (type3.indexOf(value) != -1) {
                                            return "冻结"
                                        }
                                        return "-";
                                    }
                                },
                                {
                                    title : '操作时间',
                                    field : 'created',
                                    align : 'center',
                                    visible : true,
                                    sortable : false,
                                    searchable : true
                                }]
                        }
                        _table.initTable(table,conf);
                        });
                    });
                });



                //手动充币
            $("#recharge").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                $("#recharge_number").html("")
                $("#google_code").html("")
                if (ids != undefined && ids.length == 1) {
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        area: ['380px', '200px'],
                        content: $("#div_recharge")
                    });
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });
            //手动充币提交
            $("#rechargeSubmit").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var recharge_number = $("#recharge_number").val();
                var google_code = $("#google_code").val();
                if(google_code==null&&google_code==''){
                    layer.msg('谷歌验证码不能为空');
                    return false;
                }
                if(isNaN(google_code)){
                    layer.msg('谷歌验证码请输入数字');
                    return false;
                }

                if (recharge_number <= 0) {
                    layer.msg("充币数量必须大于0", {icon: 2});
                    return false;
                }
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/exdigitalmoneyaccount/recharge",
                    data: {
                        id: ids[0],
                        number: recharge_number,
                        google_code:google_code
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                                layer.closeAll();
                                layer.msg(data.msg, {icon: 1, time: 1500});

                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exdigitalmoneyaccountlist')
                            } else {
                                layer.closeAll();
                                layer.msg(data.msg, {icon: 2, time: 1500});

                            }
                        } else {
                            layer.closeAll();
                            layer.msg("充币错误", {icon: 2, time: 1500})

                        }
                    },
                    error: function (e) {

                    }
                });
            })

            //手动提币
            $("#getCoin").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        area: ['380px', '180px'],
                        content: $("#div_getCoin")
                    });
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

            //手动提币提交
            $("#getCoinSubmit").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var getCoin_number = $("#getCoin_number").val();

                if (getCoin_number <= 0) {
                    layer.msg("提币数量必须大于0", {icon: 2});
                    return false;
                }
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/exdigitalmoneyaccount/getcoin",
                    data: {
                        id: ids[0],
                        number: getCoin_number
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                                layer.closeAll();
                                layer.msg(data.msg, {icon: 1, time: 1500});
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exdigitalmoneyaccountlist')
                            } else {
                                layer.closeAll();
                                layer.msg(data.msg, {icon: 2, time: 1500});
                            }
                        } else {
                            layer.closeAll();
                            layer.msg("提币错误", {icon: 2, time: 1500})
                        }
                    },
                    error: function (e) {

                    }
                });
            })


            //刷新冲币记录

            $("#refreshUserCoin").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var row = _table.getRowSelects($("#table"));
                var coincode = row[0].coinCode;
                if (ids != undefined && ids.length == 1) {

                    $.ajax({
                        type: "post",
                        url: _ctx + "/exchange/exdigitalmoneyaccount/refreshUserCoin",
                        data: {
                            id: ids[0],
                            coinCode: coincode,
                            count: 50
                        },
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            if (data) {

                                layer.msg(data.msg, {
                                    icon: 1,
                                })
                            }
                        },
                        error: function (e) {
                            console.log(e)
                        }
                    });
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });


            //刷新以太坊冲币记录
            $("#refreshUserETH").on("click", function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    shadeClose: true, //开启遮罩关闭
                    area: ['380px', '230px'],
                    content: $("#refreshUserEthDiv")
                });
            });

            $("#refreshEthDiv").on("click",function () {
                var hash = $("#hash").val();
                var type = $("#type").val();
                if(hash=="" || type==""){
                    layer.msg('输入不合法', {icon: 2});
                    return;
                }
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/exdigitalmoneyaccount/refreshETC",
                    data: {
                        hash: hash,
                        type: type
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            layer.msg(data.msg, {
                                icon: 1,
                            })

                        }
                    },
                    error: function (e) {
                        console.log(e)
                    }
                });
            })


        }
    }

});