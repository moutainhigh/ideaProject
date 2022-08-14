define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {

		//返还详情列表页面
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
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/mining/exmininginfo/list.do",
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
					visible : false,
					sortable : false,
					searchable : false
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
					title : '日期',
					field : 'modified',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '产生的总手续费',
					field : 'feeSum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '返还的手续费',
					field : 'returnFee',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
                /*{
                    title : '返还比例（%）',
                    field : 'returnFee',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : false,
                    formatter:function (value,row,tyep) {
                        if(row.feeSum!=0){
                            return Number(value)/Number(row.feeSum)*100;
                        }else{
                            return "0";
                        }

                    }
                },*/
				{
					title : '平台币均价',
					field : 'platformCoinAvg',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
        //分红详情列表页面
        incomelist : function() {
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
            var conf = {

                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/mining/exmininginfo/bonusInfoList.do",
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
                        visible : false,
                        sortable : false,
                        searchable : false
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
                        title : '日期',
                        field : 'created',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
					{
                        title : '产生的总手续费',
                        field : 'feeSum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '返还的手续费',
                        field : 'returnFee',
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