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
				url : _ctx + "/ico/account/icotransactionrecord/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '流水号',
					field : 'projectNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},	{
                        title : '邮箱',
                        field : 'email',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : false
                    },{
                        title : '手机号',
                        field : 'mobilePhone',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : false
                    },
				{
					title : '交易方式',
					field : 'type',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : false,
                    formatter: function (value, row, index) {
						var aa=$("#type").find("option[value='"+value+"']").text();
                       // var aa = sele.getKeyAndVal("transaction_type",value)
                        return  aa;
                    }
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
					title : '交易量',
					field : 'transactionCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '收支类型',
					field : 'state',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        //var aa = sele.getKeyAndVal("fundStatus",value)
						var aa=$("#state").find("option[value='"+value+"']").text();
                        return  aa;
                    }
				},
				{
					title : '备注',
					field : 'remark',
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