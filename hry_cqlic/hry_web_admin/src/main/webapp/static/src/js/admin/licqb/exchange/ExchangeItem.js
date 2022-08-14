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
				url : _ctx + "/licqb/exchange/exchangeitem/list.do",
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
					title : '项目名称',
					field : 'itemName',
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
					title : '兑换汇率',
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
					title : '单次最低兑换数量',
					field : 'singleMinNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '最高兑换数量',
					field : 'soloMaxNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '本局项目兑换总数',
					field : 'totalNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '已兑换数量',
					field : 'hasBuyNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '项目启动时间',
					field : 'itemStartDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '项目结束时间',
					field : 'itemEndDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '是否进行',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						if (value == 0) {
							return "进行中";
						} else {
							return "已结束";
						}
					}
				},
				{
					title : '实际兑换比例',
					field : 'percentage',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value,row) {
						var ratio = (row.hasBuyNum/row.totalNum)*100;
						return parseFloat(ratio) + "%";
					}
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});