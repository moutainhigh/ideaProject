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
			//填写form
			$("#add").on("click", function() {
				layer.open({
					type: 1,
					title:"分红信息",
					shift: 2,
					area: ['800px', '600px'],
					shadeClose: false,
					content: $("#add-main"),
					success: function(layero, index){},
					yes:function(){}
				});

			});
			//分红
			$("#grantDividend").on("click", function() {
				var reason=$("#reason").val();
				var coinCode=$("#add_coinCode").val();
				var levelSort=$("#levelSort").val();
				var dividendNum=$("#dividendNum").val();
				if(reason==undefined||reason==''){
					layer.msg('分红原因不能为空', {icon: 2});
					return false;
				}
				if(coinCode==undefined||coinCode==''){
					layer.msg('币种不能为空', {icon: 2});
					return false;
				}if(dividendNum==undefined||dividendNum==''){
					layer.msg('分红总量不能为空', {icon: 2});
					return false;
				}
				$.ajax({
					type: "post",
					url: _ctx + "/ico/dividend/icodividendmanualrecord/grantDividend.do",
					data: {
						reason: reason,
						coinCode: coinCode,
						levelSort: levelSort,
						dividendNum: dividendNum
					},
					cache: false,
					dataType: "json",
					success: function (data) {
						debugger
						if(data!=undefined){
							if(data.success){
								layer.msg(data.msg, {icon: 1});
								//table刷新
								$("#table").bootstrapTable('refresh');
							}else{
								layer.msg(data.msg, {icon: 2});
							}
						}else{
							layer.msg(data, {icon: 2});
						}
					}
				});
			});
			var conf = {
				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/ico/dividend/icodividendmanualrecord/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},{
					title : '手机',
					field : 'mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
					title : '邮箱',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},

				{
					title : '币种Code',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '分红总基数',
					field : 'dividendNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '获得数量',
					field : 'number',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '锁仓比（%）',
					field : 'accountAtio',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
						title : '时间',
						field : 'created',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},{
						title : '分红原因',
						field : 'reason',
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