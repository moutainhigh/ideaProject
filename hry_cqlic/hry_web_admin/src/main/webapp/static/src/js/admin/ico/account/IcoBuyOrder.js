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
				url : _ctx + "/ico/account/icobuyorder/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '邮箱',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false
				},{
                        title : '手机号',
                        field : 'mobilePhone',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : false
                    },
				{
					title : '交易币种',
					field : 'payCoinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '支付数量',
					field : 'payNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '购买定价',
					field : 'buyPrice',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '购买币种名称',
					field : 'buyCoinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '购买数量',
					field : 'buyNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},/*
				{
					title : '手续费',
					field : 'poundage',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},*/
                    {
                        title : '交易单号',
                        field : 'orderNumber',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易时间',
                        field : 'created',
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