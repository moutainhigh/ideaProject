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
				url : _ctx + "/klg/limit/klgdesignatedrewardecord/list.do",
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
					title : '级别差',
					field : 'gradation',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '见点代数',
					field : 'pointAlgebra',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '奖金额度',
					field : 'rewardNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '操作人',
					field : 'operatorIdName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '操作时间',
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