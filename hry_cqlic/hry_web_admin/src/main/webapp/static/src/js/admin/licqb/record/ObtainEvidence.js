define(function(require, exports, module) {
    this._table = require("js/base/table");
    module.exports = {
        //列表页面--初始化方法
        userlist : function () {
            var conf = {
                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/licqb/record/dealrecord/getUserTotal.do",
                columns : [ {
                    checkbox : true,
                    align : 'center',
                    valign : 'middle',
                    value : "id",
                    searchable : false
                },
                    {
                        title : '会员总人数',
                        field : 'userTotal',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '入金总人数',
                        field : 'upUserTotal',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '未入金总人数',
                        field : 'notUpUserTotal',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '最大层级数',
                        field : 'maxTier',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        userInfoList : function () {
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
                url : _ctx + "/licqb/record/dealrecord/findAllUserInfo.do",
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
                        title : '所在层级',
                        field : 'belongLevel',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '上级推荐人邮箱',
                        field : 'parentEmail',
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
                        title : '上级推荐人手机号',
                        field : 'parentMobile',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '顶级推荐人邮箱',
                        field : 'oneParentEmail',
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
                        title : '顶级推荐人手机号',
                        field : 'oneParentMobile',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '总层级数',
                        field : 'levelTotal',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },

                ]
            }
            _table.initTable($("#table"), conf);
        },
        userTeamInfoList : function () {
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
                url : _ctx + "/licqb/record/dealrecord/findUserTeamInfo.do",
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
                        title : '个人等级名称',
                        field : 'levelName',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '社区等级名称',
                        field : 'teamLevelName',
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
                        title : '社区层级总数',
                        field : 'teamTierNum',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '社区总人数',
                        field : 'teamUserNum',
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