define(function(require, exports, module) {
	this._table = require("js/base/table");
	this.sele = require("js/base/HrySelect");
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
				url : _ctx + "/klg/limit/klgamountlimitationrecord/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
					{
						title : '限制类型',
						field : 'type',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true,
						formatter: function (value, row, index) {
							var aa = sele.getKeyAndVal("limit_type",value)
							return  aa;
						}
					},
					{
						title : '限制总额度/天',
						field : 'money',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
				{
					title : '是否限制',
					field : 'state',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						var aa = sele.getKeyAndVal("yesno",value)
						return  aa;
					}
				},
				{
					title : '操作人',
					field : 'operatorName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
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