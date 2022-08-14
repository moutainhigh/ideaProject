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
				url : _ctx + "/licqb/platform/usdttotal/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '币种代码',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '充值总数',
					field : 'payMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '提取总数',
					field : 'extractMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '统计时间',
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
		userList: function() {
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
				url : _ctx + "/licqb/platform/usdttotal/userTotalList.do",
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
						title : '币种代码',
						field : 'coinCode',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true,
						formatter: function(value) {
							return "USDT";
						}
					},
					{
						title : '充值总数',
						field : 'payMoney',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '提取总数',
						field : 'extractMoney',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '剩余数量',
						field : 'surplusMoney',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '操作',
						field : 'customerId',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true,
						formatter : function(value){
							var html = '-';
							html = '<a class="btn btn-default btn-xs changeJobStatus changeGreen" href="javascript:void(0);" name="see" id="'+value+'" title="查看明细"><i class="glyphicon glyphicon-edit"></i>查看明细</a>';
							return html;
						}
					}
				],
				success : function () {
					$("a[name='see']").on("click", function(){
						var customerId = $(this).attr("id");
						$.ajax({
							type : "post",
							url : _ctx + "/licqb/platform/usdttotal/toUserUsdtDetail?customerId="+customerId,
							cache : false,
							dataType : "html",
							success : function(data) {
								layer.open({
									type: 1,
									title :'查看明细',
									skin: 'layui-layer-rim', //加上边框
									area: ['70%', '70%'], //宽高,
									content: data,
									closeBtn : 2,
									shadeClose : false,
									success: function(layero, index){
									},
									cancel : function(){
									},
									end: function () {
										layer.closeAll();
									}
								});
							}
						});
					});
				}
			}
			_table.initTable($("#table"), conf);
		},
		userListTwo: function() {
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
				url : _ctx + "/licqb/platform/usdttotal/userTotalList.do",
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
						title : '币种代码',
						field : 'coinCode',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true,
						formatter: function(value) {
							return "USDT";
						}
					},
					{
						title : '充值总数',
						field : 'payMoney',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '提取总数',
						field : 'extractMoney',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					}
				]
			}
			_table.initTable($("#table"), conf);
		},
		userDetailList : function () {
			//重置按钮
			$("#userTable_reset").on("click", function () {
				$("#userTable_query_form")[0].reset();
			});
			//查询按钮
			$("#userTable_query").on("click", function () {
				var params = $("#userTable_query_form").serializeJson();
				//查询
				_table.tableQuery($("#userTable"), params);
			});
			var customerId = $("#customerId").val();
			var conf = {
				url : _ctx + "/licqb/platform/usdttotal/userDetailList?customerId="+customerId,
				columns  :[ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				}, {
					title : '交易订单号',
					field : 'transactionNum',
					align : 'center',
					visible : false,
					searchable : false
				}, {
					title : '交易类型',
					field : 'transactionType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value) {
						if (value == 1) {
							return '充币'
						} else if (value == 2) {
							return '提币'
						}
					}
				},
					{
						title : '交易金额',
						field : 'transactionMoney',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title: '地址备注',
						field: 'remark2',
						align: 'center',
						visible: true,
						sortable: false,
						searchable: true
					},
					{
						title: '备注',
						field: 'remark',
						align: 'center',
						visible: true,
						sortable: false,
						searchable: true
					},
					{
						field : 'created',
						title : '交易时间',
						sortable : true,
						align : 'center'
					}]
			}
			_table.initTable($("#userTable"),conf);
		}
	}

});