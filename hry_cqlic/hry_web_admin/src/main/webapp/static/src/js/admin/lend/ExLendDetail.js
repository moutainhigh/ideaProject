define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//列表页面--初始化方法
		list : function() {

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/lend/exlenddetail/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/lend/exlenddetail/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/lend/exlenddetail/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/lend/exlenddetail/list.do",
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
					title : '借款单号',
					field : 'lendNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易对',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '借款币种',
					field : 'lendCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '申请的数量',
					field : 'lendCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '利率',
					field : 'ratio',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '计算天数',
					field : 'dayCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '已还利息',
					field : 'repayInerest',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '未偿还利息',
					field : 'nopayInerest',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '已偿还本金',
					field : 'repayMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '还款状态',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value) {
						switch (value) {
							case 1:
								return '还款中'
							case 2:
								return '已结清'
							case 3:
								return '爆仓中'
							case 4:
								return '已爆仓'
							default:
								return '---'
                        }
                    }
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});