define(function (require, exports, module) {
    this._table = require("js/base/table");

    module.exports = {
        //查看页面--初始化方法
        see: function () {
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
                _table.seeRow($("#table"), _ctx + "/exchange/exdmcustomerpublickey/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmcustomerpublickey/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exdmcustomerpublickey/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exdmcustomerpublickey/list.do",
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
                        field: 'currencyType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '取币地址',
                        field: 'publicKey',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '地址备注',
                        field: 'remark',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter : function(data, type, row) {
                            if(data=="“”"){
                                return "-";
                            }else{
                                return data;
                            }
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        }
    }

});