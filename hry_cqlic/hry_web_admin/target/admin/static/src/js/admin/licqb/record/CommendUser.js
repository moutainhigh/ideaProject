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
                url : _ctx + "/licqb/award/outinfo/findUserList.do",
                columns : [
                    {
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
                        title : '个人等级',
                        field : 'levelName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '社区等级',
                        field : 'teamLevelName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '邀请码',
                        field : 'receCode',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '个人资产(USDT)',
                        field : 'baseMoney',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '奖金额度(USDT)',
                        field : 'allMoney',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '社区总资产(USDT)',
                        field : 'userCommendAsset.teamAllAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '直推总资产（USDT）',
                        field : 'userCommendAsset.oneAllAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '二代总资产（USDT）',
                        field : 'userCommendAsset.twoAllAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '三代以上总资产（USDT）',
                        field : 'userCommendAsset.threeAllAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '上月业绩（USDT）',
                        field : 'userCommendAsset.lastMonthAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '本月业绩（USDT）',
                        field : 'userCommendAsset.thisMonthAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    /*{
                        title : '更新时间',
                        field : 'created',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },*/
                    {
                        title : '操作',
                        field : 'customerId',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true,
                        formatter : function(value){
                            var html = '-';
                            //html = '<a class="btn btn-default btn-xs changeJobStatus changeGreen" href="javascript:void(0);" name="'+value+'" id="seeAsset" title="资产明细"><i class="glyphicon glyphicon-edit"></i>资产明细</a>';
                            html = '<a class="btn btn-default btn-xs changeJobStatus changeGreen" href="javascript:void(0);" name="see" id="'+value+'" title="推荐明细"><i class="glyphicon glyphicon-edit"></i>推荐明细</a>';
                            return html;
                        }
                    }
                ],
                success : function () {
                    $("#seeAsset").on("click", function(){
                        var customerId = $(this).attr("name");
                        $.ajax({
                            type : "post",
                            url : _ctx + "/licqb/award/outinfo/toUserAssetFtl?customerId="+customerId,
                            cache : false,
                            dataType : "html",
                            success : function(data) {
                                layer.open({
                                    type: 1,
                                    title :'用户资产明细',
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
                    $("a[name='see']").on("click", function(){
                        var customerId = $(this).attr("id");
                        $.ajax({
                            type : "post",
                            url : _ctx + "/licqb/award/outinfo/toSonUserFtl?customerId="+customerId,
                            cache : false,
                            dataType : "html",
                            success : function(data) {
                                layer.open({
                                    type: 1,
                                    title :'推荐用户列表',
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
        sonList : function () {
            //重置按钮
            $("#sonTable_reset").on("click", function () {
                $("#sonTable_query_form")[0].reset();
            });
            //查询按钮
            $("#sonTable_query").on("click", function () {
                var params = $("#sonTable_query_form").serializeJson();
                //查询
                _table.tableQuery($("#sonTable"), params);
            });
            var customerId = $("#customerId").val();
            var conf = {
                url : _ctx + "/licqb/award/outinfo/findSonUserList?customerId="+customerId,
                columns  :[ {
                    field : 'state',
                    checkbox : true,
                    align : 'center',
                    valign : 'middle',
                    value : "id",
                    searchable : false
                }, {
                    title : 'ID',
                    field : 'id',
                    align : 'center',
                    visible : false,
                    searchable : false
                }, {
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
                        title : '个人等级',
                        field : 'levelName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '层级',
                        field : 'level',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '个人资产(USDT)',
                        field : 'baseMoney',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '社区等级',
                        field : 'teamLevelName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        field : 'created',
                        title : '注册时间',
                        sortable : true,
                        align : 'center'
                    }]
            }
            _table.initTable($("#sonTable"),conf);
        },
        userAsset : function () {
            var customerId = $("#customerId").val();
            var conf = {
                url : _ctx + "/licqb/award/outinfo/findUserAsset?customerId="+customerId,
                columns  :[ {
                    field : 'state',
                    checkbox : true,
                    align : 'center',
                    valign : 'middle',
                    value : "id",
                    searchable : false
                },{
                    title : '社区总资产(USDT)',
                    field : 'teamAllAsset',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
                    {
                        title : '直推总资产（USDT）',
                        field : 'oneAllAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '二代总资产（USDT）',
                        field : 'twoAllAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '三代以上总资产（USDT）',
                        field : 'threeAllAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '上月业绩（USDT）',
                        field : 'lastMonthAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '本月业绩（USDT）',
                        field : 'thisMonthAsset',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    }]
            }
            _table.initTable($("#userTable"),conf);
        }
    }

});