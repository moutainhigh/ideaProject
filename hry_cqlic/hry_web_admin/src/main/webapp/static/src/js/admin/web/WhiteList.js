define(function (require, exports, module) {
    this._table = require("js/base/table");

    module.exports = {
        //添加白名单页面--初始化方法
        addWhite: function () {
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

            // 添加按钮
            $("#addWhite").on("click", function () {
                var rows = _table.getRowSelects($("#table"));
                if (rows.length == 0) {
                    layer.msg("请选择数据", {icon : 2});
                    return false;
                } else {
                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url : _ctx + '/web/whitelist/addWhithOfCustom',
                        cache: false,
                        data:{
                            whiteData: JSON.stringify(rows)
                        },
                        success: function (data) {
                            if (data.success) {
                                layer.msg("添加成功", {icon: 1});
                                loadUrl(_ctx+'/v.do?u=/admin/web/whitelistlist')
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    });
                }
            });

            var conf = {
                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/web/whitelist/customList",
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
                        searchable: true
                    },
                    {
                        title: '邮箱',
                        field: 'email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '手机号',
                        field: 'mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '姓名',
                        field: 'trueName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '证件类型',
                        field: 'cardType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function(value, row, index) {
                            if (value == '0') {
                                return "身份证";
                            } else if (value == "1") {
                                return "港澳身份证/护照";
                            }
                        }
                    },
                    {
                        title: '证件号码',
                        field: 'cardId',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: 'ip',
                        field: 'loginIp',
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
        list: function () {

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
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/web/whitelist/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/web/whitelist/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/web/whitelist/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/web/whitelist/list",
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
                        searchable: true
                    },
                    {
                        title: '邮箱',
                        field: 'email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '手机号',
                        field: 'tel',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '姓名',
                        field: 'trueName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: 'ip',
                        field: 'ip',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '登录次数',
                        field: 'loginNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '最后一次登录时间',
                        field: 'loginLast',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '添加方式',
                        field: 'type',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "手动添加";
                            } else {
                                return "自动添加";
                            }
                        }
                    },
                    {
                        title: '加入时间',
                        field: 'created',
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