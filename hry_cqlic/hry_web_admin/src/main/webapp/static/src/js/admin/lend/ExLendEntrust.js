define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//列表页面--初始化方法
		list : function(type) {

            //重置按钮
            $("#table_reset").on("click", function () {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function () {
                //var params = $("#table_query_form").serializeJson();
                var opt = {
                    url: _ctx + "/exchange/exentrust/lendList.do?type="+type,
                    queryParams:function(params){
                        var query = $.extend( true, params, $("#table_query_form").serializeJson() );
                        return query;
                    }
                };
                _table.refreshTable($("#table"), opt);
            });

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/exchange/exentrust/lendList.do?type="+type,
				columns : [ {
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
                        title: '委托时间',
                        field: 'entrustTime',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
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
                        title: '委托类型',
                        field: 'type',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "买入"
                            } else {
                                return "卖出";
                            }
                        }
                    },
                    {
                        title: '出价类型',
                        field: 'entrustWay',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "限价"
                            } else {
                                return "市价";
                            }
                        }
                    },
                    {
                        title: '委托价格',
                        field: 'entrustPrice',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '委托数量',
                        field: 'entrustCount',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '委托总金额',
                        field: 'entrustSum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '成交数量',
                        field: 'surplusEntrustCount',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '成交均价',
                        field: 'processedPrice',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '委托单号',
                        field: 'entrustNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '状态',
                        field: 'status',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data == "0") {
                                return "全部待成交";
                            } else if (data == "1") {
                                return "部分待成交";
                            } else if (data == "2") {
                                return "已全部成交";
                            } else if (data == "3") {
                                return "部分成交已撤销";
                            } else if (data == "4") {
                                return "已全部撤销";
                            }
                        }
                    },
                    {
                        title: '来源',
                        field: 'source',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data == 1) {
                                return "人工"
                            } else if (data == 2) {
                                return "深度机器人"
                            } else {
                                return "--"
                            }
                        }
                    },
                    {
                        title: '交易明细',
                        field: 'status',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data == "0" || data == "4") {
                                return "--";
                            } else {
                                return "<input type=\"button\" entrusttype=" + row.type + " entrustid=" + row.entrustNum + " coinCode=" + row.coinCode + " currencyType=" + row.currencyType + " class=\"btn btn-small\" value=\"明细\" />"
                            }
                        }
                    },
                    {
                        title: '客户IP',
                        field: 'customerIp',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
				]
			}
			_table.initTable($("#table"), conf);

            //取消委托
            $("#cancel").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {

                    var rows = _table.getRowSelects($("#table"));
                    var num = "";
                    for(var i=0;i<rows.length;i++){
                        num += rows[i].entrustNum + ","
                    }
                    layer.confirm('你确定要取消吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/exentrust/cancelExEntrust.do?entrustNums=" + num,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("取消成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=admin/exchange/exentrustlistnow');
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

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });
		}
	}

});