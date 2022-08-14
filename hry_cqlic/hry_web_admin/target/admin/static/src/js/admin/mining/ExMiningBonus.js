define(function(require, exports, module) {
    this._table = require("js/base/table");

    module.exports = {


        //分红列表页面--初始化方法
        list : function() {


            // 手动发放按钮点击事件
            $("#manua").on("click", function() {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定发放吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "POST",
                            url: _ctx + "/mining/exminingbonus/manual",
                            data: {ids:ids[0]},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    });
                                    loadUrl(_ctx + '/mining/exminingbonus/toExminingbonusList?status=0')
                                }else{
                                    layer.msg(data.msg, {
                                        icon: 2,
                                    });
                                }
                            },
                            error: function (e) {

                            }
                        });

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });
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
            var conf = {

                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/mining/exminingbonus/list.do",
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
                        title : '身份证号',
                        field : 'cardNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '持平台币数量',
                        field : 'holdPlatformCount',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '持平台币占比',
                        field : 'holdRatio',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '手续费币种',
                        field : 'coinCode',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
					{
                        title : '手续费数量',
                        field : 'totalFee',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },

                    {
                        title : '应分数量',
                        field : 'bonusNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '生成时间',
                        field : 'created',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '分红时间',
                        field : 'modified',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : false
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        }
    }

});