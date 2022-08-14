define(function (require, exports, module) {
    this._table = require("js/base/table");
    require("lib/jquery/jquery.peity.min.js");

    module.exports = {
        // 初始化操作
        init: function(){
            //渲染实时数据接口可以请求剩余数量
            $(document).ready(function () {
                var chart1 = new Highcharts.Chart({
                    chart: {
                        renderTo: 'container1',
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie',
                        backgroundColor: '#f5f5f5',
                        borderRadius:'5',
                        height:'210'
                    },
                    credits: {
                        enabled: false
                    },
                    exporting: {
                        enabled:false
                    },
                    lang:{
                        noData:'暂无数据'
                    },
                    title: {
                        text: '交易行情配额',
                        y:5,
                        style:{
                            color:"#000000",
                            fontSize:"16px"
                        }
                    },
                    // 提示
                    tooltip: {
                        pointFormat: '<b>{point.y}</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: false
                            },
                            showInLegend: true
                        }
                    },
                    series: [{
                        data: ''
                    }]
                });
                // 短信接口配额
                var chart2 = new Highcharts.Chart({
                    chart: {
                        renderTo: 'container2',
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie',
                        backgroundColor: '#f5f5f5',
                        borderRadius:'5',
                        height:'210'
                    },
                    credits: {
                        enabled: false
                    },
                    exporting: {
                        enabled:false
                    },
                    lang:{
                        noData:'暂无数据'
                    },
                    title: {
                        text: '短信配额',
                        y:5,
                        style:{
                            color:"#000000",
                            fontSize:"16px"
                        }
                    },
                    tooltip: {
                        pointFormat: '<b>{point.y}</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: false
                            },
                            showInLegend: true
                        }
                    },
                    series: [{
                        data: ''
                    }]
                });
                // 实名认证接口配额
                var chart3 = new Highcharts.Chart({
                    chart: {
                        renderTo: 'container3',
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie',
                        backgroundColor: '#f5f5f5',
                        borderRadius:'5',
                        height:'210'
                    },
                    credits: {
                        enabled: false
                    },
                    exporting: {
                        enabled:false
                    },
                    lang:{
                        noData:'暂无数据'
                    },
                    title: {
                        text: '实名认证配额',
                        y:5,
                        style:{
                            color:"#000000",
                            fontSize:"16px"
                        }
                    },
                    tooltip: {
                        pointFormat: '<b>{point.y}</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: false
                            },
                            showInLegend: true
                        }
                    },
                    series: [{
                        data: ''
                    }]
                });

                // 饼图加载数据
                $.ajax({
                    type: "POST",
                    dataType: "JSON",
                    url: _ctx + "/getPieData",
                    cache: false,
                    success: function (data) {
                        if (data != undefined) {
                            if (data.success) {
                                var obj = data.obj;
                                if (obj) {
                                    var kdata = obj.klineData;
                                    if (kdata && kdata.totalnum) {
                                        var k = [{
                                            name: '已用查询次数',
                                            y: Number(kdata.useNum),
                                            color: "#39afd0",
                                            sliced: true,
                                            selected: true
                                        }, {
                                            name: '可用查询次数',
                                            y: Number(kdata.releaseNum),
                                            color: "#f6836a"
                                        }];
                                        chart1.series[0].setData(k);
                                    }

                                    var tdata = obj.trData;
                                    if (tdata && tdata.totalnum) {
                                        var t = [{
                                            name: '已用认证次数',
                                            y: Number(tdata.useNum),
                                            sliced: true,
                                            selected: true,
                                            color: "#39afd0"
                                        }, {
                                            name: '可用认证次数',
                                            y: Number(tdata.releaseNum),
                                            color: "#f6836a"
                                        }];
                                        chart3.series[0].setData(t);
                                    }

                                    var sdata = obj.smsData;
                                    if (sdata && sdata.balance) {
                                        var s = [{
                                            name: '可用余额',
                                            y: Number(sdata.balance),
                                            sliced: true,
                                            selected: true,
                                            //color: "#39afd0"
                                            color: "#f6836a"
                                        }/*, {
                                            name: '',
                                            y: 0,
                                            color: "#f6836a"
                                        }*/];
                                        chart2.series[0].setData(s);
                                    }
                                }
                            } else {
                                layer.msg("查询异常，请联系管理员", {icon: 2});
                            }
                        }
                    }
                });
            });

            // 重置按钮初始化
            $("div.Recharge a").on("click", function(){
                $(this).attr("href", "http://www.hurongsaas.com/merchant/remitMoney.do");
            });
        },
        //服务监控列表页面--初始化方法
        list: function () {
            var conf = {
                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/web/appservermonitor/list.do",
                columns: [
                    {
                        title: 'id',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        sortable: false,
                        searchable: false
                    },
                    {
                        title: '服务名称',
                        field: 'serverName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            return "<span class='blueColor'>" + value + "</span>";
                        }
                    },
                    {
                        title: '服务地址',
                        field: 'serverHost',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '服务端口',
                        field: 'serverPort',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '是否启用',
                        field: 'isOpen',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "<span class='forOn'>启用中</span>";
                            } else {
                                return "<span class='forClose'>关闭中</span>";
                            }
                        }
                    },
                    {
                        title: '服务状态',
                        field: 'serverStruts',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            // serverStruts 状态 1正常  0 宕机
                            // isOpen 是否开启  1开启   0关闭
                            if (row.isOpen == 1) {
                                if (value == 1) {
                                    return "<img src='/admin/static/" + _version + "/img/serviceNormal.png'/>";
                                } else {
                                    return "<img src='/admin/static/" + _version + "/img/serverStop.png'/>";
                                }
                            } else {
                                return "<img src='/admin/static/" + _version + "/img/noRun.png'/>";
                            }
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        // 定时器监控
        list2: function () {

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/findTimerTask.do",
                columns: [
                    {
                        title: 'id',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        sortable: false,
                        searchable: false
                    },
                    {
                        title: '定时器种类',
                        field: 'timerType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            return "<span class='blueColor'>" + value + "</span>";
                        }
                    },
                    {
                        title: '定时器名称',
                        field: 'timerName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            return "<span class='blueColor'>" + value + "</span>";
                        }
                    },
                    {
                        title: '上次执行时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            }
                            return "-";
                        }
                    },
                    {
                        title: '状态',
                        field: 'isOpen',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "<span class='forOn'>启用中</span>";
                            } else {
                                return "<span class='forClose'>关闭中</span>";
                            }
                        }
                    },
                ]
            }
            _table.initTable($("#table2"), conf);
        },
        // K线机器人监控
        list3: function () {
            var conf = {
                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exrobot/list?robotType=1 & isSratAuto=0",
                columns: [
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
                        title: '当前状态',
                        field: 'isSratAuto',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "<span style='color: green'>运行中</span>"
                            } else {
                                return "<span style='color: red'>已关闭 </span>";
                            }
                        }
                    },
                    {
                        title: '五分钟K线',
                        field: 'klineData',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: false,
                        formatter: function (value, row, index) {
                            return '<span class ="line" data-peity=\'{"stroke": "#3ca316"}\'>' + value + '</span>'
                        }
                    },
                ]
            }
            _table.initTable($("#table3"), conf);

            $("#table3").on('load-success.bs.table', function (data) {
                $('.line').peity('line', {
                    width: 100,
                    height: 20,
                    fill: 'none',
                    strokeWidth: 1,
                    min: 10000,
                    stroke: '#3499da'
                })
            });
        },
        // 深度机器人监控
        list4: function () {

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exrobotdeep/list?robotType=1&isSratAuto=0",
                columns: [
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
                        title: '买1差值率',
                        field: 'buyOneDiffRate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '卖1差值率',
                        field: 'sellOneDiffRate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '当前状态',
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
                ]
            }
            _table.initTable($("#table4"), conf);
        }
    }

    /*以下是暂时不用的功能*/
    /*// 监控服务的开启或关闭操作
    function _changeOnOrOffOfServer(id) {
        $.ajax({
            type: "POST",
            dataType: "JSON",
            url: _ctx + "/web/appservermonitor/openOrClose",
            data: {
                id: id
            },
            cache: false,
            success: function (data) {
                if (data != undefined) {
                    if (data.success) {
                        layer.msg('操作成功!', {icon: 1});
                        loadUrl(_ctx + '/center.do');
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            }
        });
    }

    // 定时任务的开启或关闭操作
    function _changeOnOrOffOfTimer(id, optType) {
        $.ajax({
            type: "POST",
            dataType: "JSON",
            url: _ctx + "/timerOpenOrClose.do",
            data: {
                id: id,
                optType: optType
            },
            cache: false,
            success: function (data) {
                if (data != undefined) {
                    if (data.success) {
                        layer.msg('操作成功!', {icon: 1});
                        loadUrl(_ctx + '/center.do');
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            }
        });
    }*/
});
