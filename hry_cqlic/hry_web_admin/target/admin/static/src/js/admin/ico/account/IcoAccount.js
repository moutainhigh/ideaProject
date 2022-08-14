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
				url : _ctx + "/ico/account/icoaccount/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '邮箱',
					field : 'eamil',
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
					title : '锁仓量',
					field : 'storageMoney',
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
				},
				{
					title : '等级名称',
					field : 'gradeName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
        //列表页面--初始化方法
        expenditureAccount : function() {
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
                url : _ctx + "/ico/dividend/icodividendrecord/expenditureAccount.do",
                columns : [ {
                    checkbox : true,
                    align : 'center',
                    valign : 'middle',
                    value : "id",
                    searchable : false
                },
                    {
                        title : '币种名称',
                        field : 'coinCode',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '数量量',
                        field : 'num',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '更新时间',
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