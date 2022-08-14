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
				url : _ctx + "/licqb/exchange/exchangerecord/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '项目期数',
					field : 'periodsNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '订单号',
					field : 'exchangeSn',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '项目名称',
					field : 'itemName',
					align : 'center',
					visible : true,
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
					title : '手机号',
					field : 'mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '平台币',
					field : 'payCoinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '兑换币种',
					field : 'gainCoinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '比例',
					field : 'ratio',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '支付数量',
					field : 'payNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '兑换数量',
					field : 'gainNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '兑换时间',
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