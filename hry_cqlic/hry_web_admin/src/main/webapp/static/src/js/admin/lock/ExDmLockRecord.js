define(function(require, exports, module) {
	this._table = require("js/base/table");
    this.sele = require("js/base/HrySelect");

	module.exports = {
		//锁仓记录列表页面--初始化方法
        lockList : function() {

            //重置按钮
            $("#table_reset").on("click", function () {
                $("#table_query_form")[0].reset();
            });

            // 下载模板
            $("#downloadTemp").on("click", function() {
                window.location.href = _ctx + '/lock/exdmlockrecord/downloadExcel.do';
            });

            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });

            // 导入按钮
            $("#import").on("click", function() {
                $("#excel_file").val("");
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    shadeClose: false, //开启遮罩关闭
                    area: ['380px', '200px'],
                    content: $("#div_excel"),
                    end:function(){
                        $(".layui-layer-shade").remove();
                    }
                });
            });

            // 上传操作
			$("#excelSubmit").on("click", function(){
                var filePath = $("#excel_file").val();
                if (filePath) {
                    // 加载动画
                    var index = layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        title:"导入文件过大，请耐心等待",
                        shadeClose: true, //开启遮罩关闭
                        area: ['280px', '222px'],
                        content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/'+_version+'/img/testCoin.gif" style="margin-top:65px;"></span>',
                        end:function(){
                            $(".layui-layer-shade").remove();
                        }
                    });

                    $('#excelForm').ajaxSubmit({
                        url: _ctx + "/lock/exdmlockrecord/importExcel.do",
                        type: 'POST',
                        success: function (data) {
                            if (data) {
                                data = JSON.parse(data);
                                if (data.success) {
                                    layer.msg("导入成功", {icon: 1}, function(){
                                        layer.close(index);
                                    });
                                    loadUrl(_ctx + '/v.do?u=/admin/lock/exdmlockrecordlist')
                                } else {
                                    layer.msg(data.msg, {icon: 2}, function(){
                                        layer.close(index);
                                    });
                                }
                            } else {
                                layer.msg("导入失败", {icon: 2}, function(){
                                    layer.close(index);
                                });
                            }
                        }
                    });
                } else {
                    layer.msg("未选择文件", {icon: 2});
                }
			});

           // 手动解锁按钮
            $("#unLock").on("click", function() {
                var ids = _table.getRowSelects($("#table"));
                if (ids.length == 0) {
                    layer.msg("请选择数据", {icon : 2});
                    return false;
                } else if (ids.length > 1) {
                    layer.msg("只能选择一条数据", {icon : 2});
                    return false;
                } else if(ids[0].unlockState == '4') {
                    layer.msg("释放已完成，不能手动解锁", {icon : 2});
                    return false;
				} else {
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: false, //开启遮罩关闭
                        area: ['380px', '200px'],
                        content: $("#div_unlock")
                    });
                }
            });

            // 手动解锁确认提交
            $("#unlockSubmit").on("click", function () {
                var rows = _table.getRowSelects($("#table"));
                var coldNum = rows[0].remainingRelease;
                var unlockNum = $("#unlock_number").val();
                if ((coldNum && unlockNum) && Number(coldNum) >= Number(unlockNum)) {
                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url : _ctx + '/lock/exdmlockrecord/unlockByManual.do',
                        data : {
                            id: rows[0].id,
                            unlockNum: unlockNum
                        },
                        cache: false,
                        success: function (data) {
                            if (data.success) {
                                layer.msg("手动解锁成功", {icon: 1});
                                loadUrl(_ctx + '/v.do?u=/admin/lock/exdmlockrecordlist')
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                            layer.closeAll();
                        }
                    });
                } else {
                    layer.msg("释放数量大于剩余冻结数量", {icon: 2});
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
				url : _ctx + "/lock/exdmlockrecord/list",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
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
					searchable : true,
					formatter : function (value, row, index) {
                        if (row.appPersonInfo.mobilePhone != undefined && row.appPersonInfo.mobilePhone != "") {
                            return row.appPersonInfo.country + row.appPersonInfo.mobilePhone;
                        }
                        return "";
					}
				},
				{
					title : '币种类型',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '账户余额',
					field : 'accountBalance',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '冻结数量',
					field : 'coldNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '释放方式',
					field : 'releaseMethod',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(value, row, index) {
					    if (value) {
                            return sele.getKeyAndVal("lockReleaseMethod", value);
                        } else {
					        return "-";
                        }
                    }
				},
				{
					title : '释放值',
					field : 'releaseMethodVal',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
                {
                    title : '已释放量',
                    field : 'amountReleased',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
                {
                    title : '剩余释放量',
                    field : 'remainingRelease',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
                {
                    title : '解锁方式',
                    field : 'unlockType',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true,
                    formatter : function(value, row, index) {
                        return sele.getKeyAndVal("unlockType", value);
                    }
                },
				{
					title : '解锁状态',
					field : 'unlockState',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(value, row, index) {
                        return sele.getKeyAndVal("unlockState", value);
                    }
				},
				{
					title : '锁仓时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			};
			_table.initTable($("#table"), conf);
		}
	}

});
