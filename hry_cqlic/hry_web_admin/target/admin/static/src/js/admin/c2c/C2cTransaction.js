define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//查看页面--初始化方法
		see : function(){
		},
		//列表页面--初始化方法
		list : function(status) {

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
				_table.seeRow($("#table"), _ctx + "/c2c/c2ctransaction/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/c2c/c2ctransaction/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/c2c/c2ctransaction/remove.do");
			});


            //确认到账
            $("#confirm").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要确认到账吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "post",
                            url: _ctx + "/c2c/c2ctransaction/confirm/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("确认成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=/admin/c2c/c2ctransactionlist')
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 2,
                                        })
                                    }

                                }
                            },
                            error: function (e) {

                            }
                        });

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

            //关闭交易
            $("#close").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要关闭交易吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "post",
                            url: _ctx + "/c2c/c2ctransaction/close/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    debugger
                                    if (data.success) {
                                        layer.msg("关闭成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=/admin/c2c/c2ctransactionlist')
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 2,
                                        })
                                    }

                                }
                            },
                            error: function (e) {

                            }
                        });

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });


			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/c2c/c2ctransaction/list?status="+status,
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
					title : '创建时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '邮箱',
					field : 'appPersonInfo.email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手机号',
					field : 'appPersonInfo.mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易商',
					field : 'businessmanTrueName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易类型',
					field : 'transactionType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter:function (value,row,index) {
						if(value==1){
							return "买"
						}
						return "卖"
                    }
				},
				{
					title : '交易币种',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易数量',
					field : 'transactionCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易金额',
					field : 'transactionMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				/*{
					title : '手续费',
					field : 'fee',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},*/
				{
					title : '交易状态',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter:function (value,row,index) {
                        if(value==1){
                            return "待审核"
                        }else if(value==2){
                        	return "已完成"
						}
                        return "已否决"
                    }
				},
				{
					title : '备注码',
					field : 'randomNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '订单号',
					field : 'transactionNum',
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
