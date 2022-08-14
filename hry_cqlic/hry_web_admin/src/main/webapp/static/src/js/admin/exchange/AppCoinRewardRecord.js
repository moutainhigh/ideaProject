define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//查看页面--初始化方法
		see : function(){
		},
		//列表页面--初始化方法
		list : function() {
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
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/appcoinrewardrecord/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/appcoinrewardrecord/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/exchange/appcoinrewardrecord/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/exchange/appcoinrewardrecord/list.do",
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
					visible : true,
					sortable : false,
					searchable : false
				},
				{
					title : '用户名',
					field : 'customerName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手机号',
					field : 'customerMobil',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '收币类型',
					field : 'recordType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '奖励量',
					field : 'recordNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '被推荐人id',
					field : 'coverCustomerId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '被推荐人用户名',
					field : 'coverCustomerName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '被推荐人手机号',
					field : 'coverCustomerMobile',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '状态',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '备注',
					field : 'failMsg',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '时间',
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