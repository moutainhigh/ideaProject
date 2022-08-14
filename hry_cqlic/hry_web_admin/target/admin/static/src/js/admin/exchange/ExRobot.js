define(function (require, exports, module) {
    this._table = require("js/base/table");
    this.md5 = require("js/base/utils/hrymd5");
    require("lib/jquery/jquery.peity.min.js");
    module.exports = {
        //查看页面--初始化方法
        see: function () {
        },
        //添加页面--初始化方法
        add: function () {
            $("#priceSource").hide();
            $("#atuoPriceType").on("change", function () {
                var val = $(this).val();
                if (val == "1") {
                    $("#autoPrice").attr("readonly", false)
                    $("#priceSource").hide();
                } else if (val == "2") {
                    $("#autoPrice").attr("readonly", true)
                    $("#priceSource").hide();
                } else if (val == "3") {
                    $("#autoPrice").attr("readonly", true)
                    $("#priceSource").show();
                }
            })


            // 表单验证
            $('#form').bootstrapValidator({
                submitButtons: "button[id=addSubmit]",
                message: '不能为空',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    coinCode: {
                        validators: {
                            notEmpty: {
                                message: "交易币/定价币不能为空"
                            }
                        }
                    },
                    autoUsername: {
                        validators: {
                            notEmpty: {
                                message: "交易账号不能为空"
                            }
                        }
                    },
                    /* autoPrice : {
                         validators : {
                             notEmpty : {
                                 message : "价格基准不能为空"
                             }
                         }
                     },*/
                    autoPriceFloat: {
                        validators: {
                            notEmpty: {
                                message: "价格浮动不能为空"
                            }
                        }
                    },
                    autoCount: {
                        validators: {
                            notEmpty: {
                                message: "数量基准不能为空"
                            }
                        }
                    },
                    autoCountFloat: {
                        validators: {
                            notEmpty: {
                                message: "数量浮动不能为空"
                            }
                        }
                    }

                },
                // bv校验通过则提交
                submitHandler: function (validator, form, submitButton) {
                }
            });
            // 添加提交
            $("#addSubmit").on("click", function () {
                var options = {
                    url: _ctx + "/exchange/exrobot/add.do",
                    type: "post",
                    resetForm: true,// 提交后重置表单
                    dataType: 'json',
                    beforeSubmit: function (formData, jqForm, options) {


                        //重置验证
                        jqForm.data("bootstrapValidator").resetForm();
                        // 手动触发验证
                        var bootstrapValidator = jqForm.data('bootstrapValidator');
                        bootstrapValidator.validate();
                        if (!bootstrapValidator.isValid()) {
                            return false;
                        }
                    },
                    success: function (data, statusText) {
                        if (data != undefined) {
                            if (data.success) {
                                layer.msg('添加成功!', {icon: 1});
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exrobotlist')
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    }

                };
                $("#form").ajaxSubmit(options);
            });
        },
        //修改页面--初始化方法
        modify: function () {


            $("#atuoPriceType option[value='" + $("#atuoPriceType").attr("value") + "']").attr("selected", true)
            initFormData($("#atuoPriceType").val())

            $("#atuoPriceType").on("change", function () {
                var val = $(this).val();
                initFormData(val)
            })


            function initFormData(val) {
                if (val == "1") {
                    //.removeAttr("readonly")
                    $("#autoPrice").removeAttr("readonly")
                    $("#autoPriceFloat").removeAttr("readonly")
                    $("#autoPrice").val("")
                    $("#autoPriceFloat").val("")
                } else if (val == "2") {
                    $("#autoPrice").val("市价")
                    $("#autoPrice").attr("readonly", "readonly")
                    $("#autoPriceFloat").removeAttr("readonly")
                } else if (val == "3") {
                    $("#autoPrice").attr("readonly", "readonly")
                    $("#autoPriceFloat").attr("readonly", "readonly")
                    $("#autoPrice").val("实时行情")
                    $("#autoPriceFloat").val("微量随机浮动")
                }
            }

            // 表单验证
            $('#form').bootstrapValidator({
                submitButtons: "button[id=modifySubmit]",
                message: '不能为空',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    coinCode: {
                        validators: {
                            notEmpty: {
                                message: "交易币/定价币不能为空"
                            }
                        }
                    },
                    autoUsername: {
                        validators: {
                            notEmpty: {
                                message: "交易账号不能为空"
                            }
                        }
                    },
                    /* autoPrice : {
                         validators : {
                             notEmpty : {
                                 message : "价格基准不能为空"
                             }
                         }
                     },*/
                    autoPriceFloat: {
                        validators: {
                            notEmpty: {
                                message: "价格浮动不能为空"
                            }
                        }
                    },
                    autoCount: {
                        validators: {
                            notEmpty: {
                                message: "数量基准不能为空"
                            }
                        }
                    },
                    autoCountFloat: {
                        validators: {
                            notEmpty: {
                                message: "数量浮动不能为空"
                            }
                        }
                    }

                },
                // bv校验通过则提交
                submitHandler: function (validator, form, submitButton) {
                }
            });
            // 修改提交
            $("#modifySubmit").on("click", function () {
                if ($("#autoPrice").val() == "实时行情" || $("#autoPrice").val() == "市价") {
                    $("#autoPrice").val("0")
                }
                if ($("#autoPriceFloat").val() == "微量随机浮动") {
                    $("#autoPriceFloat").val("0")
                }


                var options = {
                    url: _ctx + "/exchange/exrobot/modify.do",
                    type: "post",
                    resetForm: true,// 提交后重置表单
                    dataType: 'json',
                    beforeSubmit: function (formData, jqForm, options) {


                        //重置验证
                        jqForm.data("bootstrapValidator").resetForm();
                        // 手动触发验证
                        var bootstrapValidator = jqForm.data('bootstrapValidator');
                        bootstrapValidator.validate();
                        if (!bootstrapValidator.isValid()) {
                            return false;
                        }
                    },
                    success: function (data, statusText) {
                        if (data != undefined) {
                            if (data.success) {
                                layer.msg('修改成功!', {icon: 1});
                                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotparamlist')
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    }
                };
                $("#form").ajaxSubmit(options);
            });

            $("#table").on('load-success.bs.table', function (data) {

            });
        },
        //列表页面--初始化方法
        list: function () {

            $("#open").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {
                    $("#ids").val(ids)
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        area: ['380px', '180px'],
                        content: $("#opentrade")
                    });


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            $("#confirmOpen").on("click", function () {
                var ids = $("#ids").val();
                var pwd = $("#password").val();
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/exrobot/startAuto",
                    data: {
                        id: ids,
                        password: md5.md5(pwd)
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("自动交易已开启", {
                                    icon: 1,
                                })
                                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotlist');
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

            $("#close").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                layer.confirm('你确定关闭自动交易吗?', {
                    btn: ['确定', '取消'],
                    ids: ids[0]
                }, function () {
                    $.ajax({
                        type: "get",
                        url: _ctx + "/exchange/exrobot/closeAuto/" + ids,
                        data: {},
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            if (data) {
                                if (data.success) {
                                    layer.msg("自动交易已关闭", {
                                        icon: 1,
                                    })
                                    loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotlist');
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
            })

            $("#test").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                layer.confirm('你确定测试接口吗?', {
                    btn: ['确定', '取消'],
                    ids: ids[0]
                }, function () {
                    $.ajax({
                        type: "get",
                        url: _ctx + "/exchange/exrobot/testApi/" + ids,
                        data: {},
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            if (data) {
                                layer.msg(data.msg, {
                                    icon: 1,
                                })
                            }
                        },
                        error: function (e) {

                        }
                    });

                })
            })

            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exrobot/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exrobot/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exrobot/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exrobot/list?robotType=1",
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },
                    {
                        title: 'id',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        sortable: false,
                        searchable: false
                    },
                    {
                        title: '交易币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易区',
                        field: 'fixPriceCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '定价类型',
                        field: 'fixPriceType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "虚拟货币"
                            } else {
                                return "真实货币";
                            }
                        }
                    },
                    {
                        title: '自动交易开启/关闭',
                        field: 'isSratAuto',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "自动交易已开启"
                            } else {
                                return "自动交易关闭";
                            }
                        }
                    },
                    {
                        title: '交易账号手机号',
                        field: 'mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易账号邮箱',
                        field: 'email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '下单类型',
                        field: 'atuoPriceType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data == 1) {
                                return "定价下单"
                            }
                            if (data == 2) {
                                return "市价下单"
                            }
                            if (data == 3) {
                                return "第三方价格下单"
                            }
                        }
                    },
                    {
                        title: '价格基准',
                        field: 'autoPrice',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '价格上下浮动(%)',
                        field: 'autoPriceFloat',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '基准数量',
                        field: 'autoCount',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '数量上下浮动(%)',
                        field: 'autoCountFloat',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '下单价格来源',
                        field: 'priceSource',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        //k线机器人监控
        monitorlist: function () {
            $("#resetData").on("click", function () {
                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotmonitorlist');

            })


            $("#test").on("click", function () {
                var ids = _table.getIdSelects($("#table"));


                if (ids != undefined && ids.length == 1) {

                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        title: "正在连接。。。",
                        shadeClose: true, //开启遮罩关闭
                        area: ['280px', '222px'],
                        content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/' + _version + '/img/testCoin.gif" style="margin-top:65px;"></span>'
                    });

                    $.ajax({
                        type: "get",
                        url: _ctx + "/exchange/exrobot/testApi/" + ids,
                        data: {},
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            layer.closeAll();
                            if (data) {
                                layer.msg(data.msg, {
                                    icon: 1,
                                })
                            }
                        },
                        error: function (e) {

                        }
                    });

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }


            })


            $("#table_reset").on("click", function () {
                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotmonitorlist');
            });

            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });
            $("#cleanKline").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length > 0) {
                    var row = _table.getRowSelects($("#table"));

                    layer.confirm('你确定清空' + row[0].coinCode + '_' + row[0].fixPriceCoinCode + '吗?', {
                        btn: ['确定', '取消'],
                        ids: ids
                    }, function () {


                        $.ajax({
                            type: "post",
                            url: _ctx + "/exchange/exrobot/cleanKline?ids=" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.closeAll();
                                    if (data.success) {
                                        layer.msg("清空成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotmonitorlist');
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
                    });


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            $("#open").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {
                    var row = _table.getRowSelects($("#table"));

                    layer.confirm('你确定开启' + row[0].coinCode + '_' + row[0].fixPriceCoinCode + '交易吗?', {

                        btn: ['确定', '取消'],
                        ids: ids
                    }, function () {
                        layer.open({
                            type: 1,
                            skin: 'layui-layer-demo', //样式类名
                            closeBtn: 1, //不显示关闭按钮
                            anim: 2,
                            title: "正在开启。。。",
                            shadeClose: true, //开启遮罩关闭
                            area: ['280px', '222px'],
                            content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/' + _version + '/img/testCoin.gif" style="margin-top:65px;"></span>'
                        });

                        $.ajax({
                            type: "post",
                            url: _ctx + "/exchange/exrobot/startAuto?ids=" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.closeAll();
                                    if (data.success) {
                                        layer.msg("开启成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotmonitorlist');
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
                    });


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            $("#confirmOpen").on("click", function () {
                var ids = $("#ids").val();
                var pwd = $("#password").val();


            })

            $("#close").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var row = _table.getRowSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    layer.confirm('你确定关闭' + row[0].coinCode + '_' + row[0].fixPriceCoinCode + '吗?', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/exrobot/closeAuto/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("关闭成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotmonitorlist');
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
                }else {
                    layer.msg('请选择一条数据', {icon: 2});
                }

            })


            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exrobot/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exrobot/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exrobot/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                pageSize: 50,
                url: _ctx + "/exchange/exrobot/list?robotType=1",
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },
                    {
                        title: 'id',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        sortable: false,
                        searchable: false
                    },
                    {
                        title: '交易币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易区',
                        field: 'fixPriceCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '开启状态',
                        field: 'isSratAuto',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "<span style='color: green'>运行中</span>"
                            } else {
                                return "<span style='color: red'>已关闭 </span>";
                            }
                        }
                    },
                    {
                        title: '当前价格',
                        field: 'nowPrice',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '买方累计成交量',
                        field: 'buyEntNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '卖方累计成交量',
                        field: 'sellEntNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '5分钟k线图',
                        field: 'klineData',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            return '<span class ="line" data-peity=\'{"stroke": "#3ca316"}\'>' + value + '</span>'
                        }

                    }
                ],
                queryParams: function queryParams(params) {
                    return {
                        limit: 50,

                    };
                }
            }
            _table.initTable($("#table"), conf);

            $("#table").on('load-success.bs.table', function (data) {
                $('.line').peity('line', {
                    width: 100,
                    height: 25,
                    fill: 'none',
                    strokeWidth: 1,
                    min: 10000,
                    stroke: '#3499da'
                })
            });

        },


        //k线机器人参数配置
        paramlist: function () {
            //重置按钮
            $("#table_reset").on("click", function () {
                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotparamlist');
            });

            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exrobot/modifysee");
            });


            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                pageSize: 50,
                url: _ctx + "/exchange/exrobot/list?robotType=1",
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },
                    {
                        title: 'id',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        sortable: false,
                        searchable: false
                    },
                    {
                        title: '交易币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易区',
                        field: 'fixPriceCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '下单类型',
                        field: 'atuoPriceType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data == 1) {
                                return "定价下单"
                            }
                            if (data == 2) {
                                return "市价下单"
                            }
                            if (data == 3) {
                                return "外部行情下单"
                            }
                        }
                    },
                    {
                        title: '基准下单价格',
                        field: 'autoPrice',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (row.atuoPriceType == 3) {
                                return "实时行情"
                            } else if(row.atuoPriceType == 2){
                                return "市价"
                            }
                            return data;

                        }
                    },
                    {
                        title: '下单价格浮动(%)',
                        field: 'autoPriceFloat',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data == 0) {
                                return "微量随机浮动"
                            }
                            return data;

                        }
                    },
                    {
                        title: '基准下单量',
                        field: 'autoCount',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data == 0) {
                                return "账户可成交最大额"
                            }
                            return data;

                        }
                    }/*,
                    {
                        title: '下单量浮动(%)',
                        field: 'autoCountFloat',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data == 0) {
                                return "无浮动"
                            }
                            return data;

                        }
                    }*/,
                    {
                        title: '下单频率',
                        field: 'autoCountFloat',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {

                            return "<span style='color: red'>5秒</span>";

                        }
                    }
                ],
                queryParams: function queryParams(params) {
                    return {
                        limit: 50,

                    };
                }
            }
            _table.initTable($("#table"), conf);
        },

        //交易账号设置
        accountinfolist: function (robotType) {
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });

            //重置按钮
            $("#table_reset").on("click", function () {
                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotinfolist');
            });
            //批量设定账号
            $("#setMoreAccount").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length > 0) {
                    $("#ids").val(ids)
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        title: "交易账号设定",
                        shadeClose: true, //开启遮罩关闭
                        area: ['480px', '222px'],
                        content: $("#setAccountDiv")
                    });


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            //提交设定账号
            $("#confirmset").on("click", function () {
                var ids = $("#ids").val();
                var account = $("#account").val();
                var pwd = $("#accountpwd").val();

                if (account == "") {
                    layer.msg("交易账号不能为空", {
                        icon: 2,
                    })
                    return;
                }
                if (pwd == "") {
                    layer.msg("密码不能为空", {
                        icon: 2,
                    })
                    return;
                }
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/exrobot/setAccount",
                    data: {
                        id: ids,
                        account: account,
                        pwd: md5.md5(pwd)
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("设定成功", {
                                    icon: 1,
                                })
                                loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotinfolist');
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


            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                pageSize: 50,
                url: _ctx + "/exchange/exrobot/accountInfoList?robotType=" + robotType,
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },
                    {
                        title: 'id',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        sortable: false,
                        searchable: false
                    },
                    {
                        title: '交易币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易币种余额',
                        field: 'coinCodeNumer',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                    ,
                    {
                        title: '交易区',
                        field: 'fixPriceCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }

                    ,
                    {
                        title: '交易区余额',
                        field: 'fixCoinCodeNumer',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },

                    {
                        title: '交易账号手机号',
                        field: 'mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易账号邮箱',
                        field: 'email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ],
                queryParams: function (params) {
                    return {
                        offset: params.offset,
                        limit: 50,
                        name: $('#queryNameText').val(),
                        age: $('#queryAgeText').val()
                    }
                }


            }
            _table.initTable($("#table"), conf);
        }
    }

});