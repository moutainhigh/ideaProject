define(function(require, exports, module) {
	this._table = require("js/base/table");
	module.exports = {
		//列表页面--初始化方法
		list : function () {
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
				url : _ctx + "/licqb/record/dealrecord/findDealRecordList.do",
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
						searchable : true,
						formatter : function (value) {
							if (value == '') {
								return '-';
							} else {
								return value;
							}
						}
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
						title : '交易方式',
						field : 'dealType',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true,
						formatter: function (value, row, index) {
							if (value == "1") {
								return "静态收益"
							} else if(value == "2"){
								return "见点奖"
							} else if(value == "3"){
								return "管理奖"
							} else if(value == "4"){
								return "级别奖"
							} else if(value == "5"){
								return "共建社区奖励"
							} else if(value == "6"){
								return "出局"
							} else if(value == "7"){
								return "兑换收入"
							} else if(value == "8"){
								return "充币"
							} else if(value == "9"){
								return "周释放"
							} else if(value == "10"){
								return "月释放"
							} else if(value == "11"){
								return "年释放"
							} else if(value == "12") {
								return "兑换支出"
							} else if(value == "13") {
								return "理财"
							} else if(value == "14"){
								return "提币"
							} else if(value == "15"){
								return "充币"
							}
							return "-";
						}
					},
					{
						title : '币种',
						field : 'coinCode',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '数量',
						field : 'dealMoney',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '状态',
						field : 'dealType',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true,
						formatter: function (value, row, index) {
							var type1 = "1,2,3,4,7,9,10,11,15,";
							var type2 = "6,12,14,";
							var type3 = "5,8,13,";
							value = value+",";
							if (type1.indexOf(value) != -1) {
								return "可用"
							} else if (type2.indexOf(value) != -1){
								return "消耗"
							} else if (type3.indexOf(value) != -1) {
								return "冻结"
							}
							return "-";
						}
					},
					{
						title : '流水号',
						field : 'transactionNum',
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
		},
		teamAwardList : function() {
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
				url : _ctx + "/licqb/record/dealrecord/findDealRecordList.do?dealType=5",
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
					searchable : true,
					formatter : function (value) {
						if (value == '') {
							return '-';
						} else {
							return value;
						}
					}
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
					title : '奖励币种',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '奖励数量',
					field : 'dealMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '流水号',
					field : 'transactionNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '奖励时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
		teamAwardReleaseList : function() {
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
				url : _ctx + "/licqb/record/dealrecord/findDealRecordList.do?dealType=9,10,11",
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
						searchable : true,
						formatter : function (value) {
							if (value == '') {
								return '-';
							} else {
								return value;
							}
						}
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
						title : '释放类型',
						field : 'dealType',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true,
						formatter : function (value) {
							var html = '-'
							if (value == 9) {
								html = '周释放'
							} else if(value == 10) {
								html = '月释放'
							} else if(value == 11) {
								html = '年释放'
							}
							return html;
						}
					},
					{
						title : '释放基数',
						field : 'actualMoney',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '释放比例',
						field : 'ratio',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '释放数量',
						field : 'dealMoney',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '流水号',
						field : 'transactionNum',
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
			}
			_table.initTable($("#table"), conf);
		},
		awardList : function (type) {
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
				url : _ctx + "/licqb/record/dealrecord/findDealRecordList.do?dealType=1,2,3,4,9,10,11",
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
						searchable : true,
						formatter : function (value) {
							if (value == '') {
								return '-';
							} else {
								return value;
							}
						}
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
						title : '奖励类型',
						field : 'dealType',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true,
						formatter : function (value) {
							var html = '-'
							if (value == "1") {
								return "静态收益"
							} else if(value == "2"){
								return "见点奖"
							} else if(value == "3"){
								return "管理奖"
							} else if(value == "4"){
								return "级别奖"
							} else if(value == "9"){
								return "周释放"
							} else if(value == "10"){
								return "月释放"
							} else if(value == "11"){
								return "年释放"
							}
							return html;
						}
					},
					{
						title : '奖励币种',
						field : 'coinCode',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '奖励比例%',
						field : 'ratio',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '奖励数量',
						field : 'dealMoney',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '流水号',
						field : 'transactionNum',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '奖励时间',
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