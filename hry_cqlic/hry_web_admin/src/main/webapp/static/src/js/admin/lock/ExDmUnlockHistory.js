define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//列表页面--初始化方法
		list : function() {

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

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/lock/exdmlockrecord/unlockList.do",
                columns : [ {
                    checkbox : true,
                    align : 'center',
                    valign : 'middle',
                    value : "id",
                    searchable : false
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
                    searchable : true,
                    formatter : function (value, row, index) {
                        if (row.appPersonInfo.mobilePhone != undefined && row.appPersonInfo.mobilePhone != "") {
                            return row.appPersonInfo.country + row.appPersonInfo.mobilePhone;
                        }
                        return "";
                    }
                },
                {
                    title : '币种类型',
                    field : 'coinCode',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
                {
                    title : '本次释放数量',
                    field : 'transactionMoney',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
                {
                    title : '操作人',
                    field : 'optUser',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
                {
                    title : '释放时间',
                    field : 'created',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                }
                ]
			};
			_table.initTable($("#table"), conf);
		}
	}

});