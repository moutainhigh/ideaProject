define(function (require, exports, module) {
    this._table = require("js/base/table");

    module.exports = {
        //查看页面--初始化方法
        see: function () {
        },
        //添加页面--初始化方法
        add: function () {
            $("#fixPriceType").on("change",function () {

               var type = $(this).val();
                $.ajax({
                    type: "get",
                    url: _ctx + "/exchange/excointocoin/getFixPriceCoinList?fixPriceType="+type,
                    data: { },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            $("#fixPriceCoinCode").empty();
                            for( var i=0 ; i<data.length ;i++){
                                var a = data[i];
                                $("#fixPriceCoinCode").append("<option value=\""+a.tradingArea+"\">"+a.tradingArea+"</option>")
                            }

                        }
                    },
                    error: function (e) {
                        console.log(e)
                    }
                });

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
                                message: "交易币种不能为空"
                            }
                        }
                    },
                    averagePrice: {
                        validators: {
                            notEmpty: {
                                message: "初始定价不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    fixPriceType: {
                        validators: {
                            notEmpty: {
                                message: "请选择交易区类型"
                            }
                        }
                    },
                    fixPriceCoinCode: {
                        validators: {
                            notEmpty: {
                                message: "交易区不能为空"
                            }
                        }
                    },
                    keepDecimalFixPrice: {
                        validators: {
                            notEmpty: {
                                message: "价格小数位不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$/,
                                message: "请填写有效的正整数"
                            }
                        }
                    },
                    keepDecimalCoinCode: {
                        validators: {
                            notEmpty: {
                                message: "数量小数位不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$/,
                                message: "请填写有效的正整数"
                            }
                        }
                    },
                    rose: {
                        validators: {
                            notEmpty: {
                                message: "限价涨幅不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    decline: {
                        validators: {
                            notEmpty: {
                                message: "限价跌幅不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    buyFeeRate: {
                        validators: {
                            notEmpty: {
                                message: "买方手续费率不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    sellFeeRate: {
                        validators: {
                            notEmpty: {
                                message: "卖方手续费率不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    sellMinCoin: {
                        validators: {
                            notEmpty: {
                                message: "单笔最小下单数量不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    oneTimeOrderNum: {
                        validators: {
                            notEmpty: {
                                message: "单笔最大下单数量不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
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
                var tradeArea = $("#tradeArea").val();
                var options = {
                    url: _ctx + "/exchange/excointocoin/add.do",
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
                                loadUrl(_ctx + '/exchange/excointocoin/toList/'+tradeArea);
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
                                message: "交易币种不能为空"
                            }
                        }
                    },
                    averagePrice: {
                        validators: {
                            notEmpty: {
                                message: "初始定价不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    fixPriceType: {
                        validators: {
                            notEmpty: {
                                message: "请选择交易区类型"
                            }
                        }
                    },
                    fixPriceCoinCode: {
                        validators: {
                            notEmpty: {
                                message: "交易区不能为空"
                            }
                        }
                    },
                    keepDecimalFixPrice: {
                        validators: {
                            notEmpty: {
                                message: "价格小数位不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$/,
                                message: "请填写有效的正整数"
                            }
                        }
                    },
                    keepDecimalCoinCode: {
                        validators: {
                            notEmpty: {
                                message: "数量小数位不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$/,
                                message: "请填写有效的正整数"
                            }
                        }
                    },
                    rose: {
                        validators: {
                            notEmpty: {
                                message: "限价涨幅不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    decline: {
                        validators: {
                            notEmpty: {
                                message: "限价跌幅不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    buyFeeRate: {
                        validators: {
                            notEmpty: {
                                message: "买方手续费率不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    sellFeeRate: {
                        validators: {
                            notEmpty: {
                                message: "卖方手续费率不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    sellMinCoin: {
                        validators: {
                            notEmpty: {
                                message: "单笔最小下单数量不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
                            }
                        }
                    },
                    oneTimeOrderNum: {
                        validators: {
                            notEmpty: {
                                message: "单笔最大下单数量不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                                message: "请填写有效的正数"
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
                var tradeArea = $("#tradeArea").val();
                var options = {
                    url: _ctx + "/exchange/excointocoin/modify.do",
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
                                loadUrl(_ctx + '/exchange/excointocoin/toList/'+tradeArea)
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    }
                };
                $("#form").ajaxSubmit(options);
            });
        },
        //列表页面--初始化方法
        list: function () {
            $("#testInterface").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var rows = _table.getRowSelects($("#table"))


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
                        type: "post",
                        url: _ctx + "/exchange/exrobot/testApi/" + ids,
                        data: {
                            coinCode : rows[0].coinCode,
                            fixPriceCoinCode : rows[0].fixPriceCoinCode
                        },
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg(data.msg, {
                                    icon: 1,
                                })
                            } else {
                                layer.msg(data.msg, {
                                    icon: 2,
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

            // 查看页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/excointocoin/see");
            });
            // 添加页面跳转按钮
            $("#add").on("click", function () {
                var tradeArea = $("#tradeArea").val();
                loadUrl(_ctx + '/exchange/excointocoin/toAdd/' + tradeArea);
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                var rows = _table.getRowSelects($("#table"))
                if (rows != undefined && rows.length == 1) {
                    var tradeArea = $("#tradeArea").val();
                    loadUrl(_ctx + '/exchange/excointocoin/modifysee/' + rows[0].id + "/" + tradeArea);
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

            //添加K线机器人
            $("#addKlineRobot").on('click',function () {
                var ids = _table.getIdSelects($("#table"));
                var tradeArea = $("#tradeArea").val();
                var rows = _table.getRowSelects($("#table"))
                if(ids!=undefined&&ids.length==1) {

                    layer.confirm('你确定要添加'+rows[0].coinCode+'/'+rows[0].fixPriceCoinCode+'K线机器人吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/excointocoin/addKlineRobot/"+ids,
                            data: { },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("添加成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx+'/exchange/excointocoin/toList/'+tradeArea);
                                    }else {
                                        layer.msg(data.msg, {
                                            icon: 2
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
            })

            //添加深度
            $("#AddDeepRobot").on('click',function () {
                var ids = _table.getIdSelects($("#table"));
                var tradeArea = $("#tradeArea").val();
                var rows = _table.getRowSelects($("#table"))
                if(ids!=undefined&&ids.length==1) {

                    layer.confirm('你确定要添加'+rows[0].coinCode+'/'+rows[0].fixPriceCoinCode+'深度机器人吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/excointocoin/addDeepRobot/"+ids,
                            data: { },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("添加成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx+'/exchange/excointocoin/toList/'+tradeArea);
                                    }else {
                                        layer.msg(data.msg, {
                                            icon: 2
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
            })


            //开启

            $("#open").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var tradeArea = $("#tradeArea").val();
                if(ids!=undefined&&ids.length==1) {

                    layer.confirm('昨日收盘价自动设置为发行价，你确定开启交易吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/excointocoin/open/"+ids,
                            data: { },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("开启成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx+'/exchange/excointocoin/toList/'+tradeArea);
                                    }else {
                                        layer.msg(data.msg, {
                                            icon: 2
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



            //关闭

            $("#close").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var tradeArea = $("#tradeArea").val();
                if(ids!=undefined&&ids.length==1) {

                    layer.confirm('你确定关闭交易吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/excointocoin/close/"+ids,
                            data: { },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("关闭成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx+'/exchange/excointocoin/toList/'+tradeArea);
                                    }else {
                                        layer.msg(data.msg, {
                                            icon: 2
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


            //删除

            $("#remove").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var tradeArea = $("#tradeArea").val();
                if(ids!=undefined&&ids.length==1) {
                    var rows = _table.getRowSelects($("#table"))
                    if(rows[0].state == 1){
                        layer.msg("请先关闭交易对再执行删除", {
                            icon: 1,
                        })
                        return;
                    }

                    layer.confirm('你确定删除'+rows[0].coinCode+'_'+rows[0].fixPriceCoinCode+'交易对吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/excointocoin/remove/"+ids,
                            data: { },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("删除成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx+'/exchange/excointocoin/toList/'+tradeArea);
                                    }else {
                                        layer.msg(data.msg, {
                                            icon: 2
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

            // 获取选中标签的交易区
            $("#tradeArea_tab .tradeAreaQuery").on("click", function () {
                var tradeArea = $(this).attr("tradeArea");
                $("#tradeArea").val(tradeArea);
                var params = {
                    tradeArea: tradeArea,
                    unstate:2
                };
                //查询
                _table.tableQuery($("#table"), params);
            });

            var conf = {
                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/excointocoin/list.do?unstate=2",
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
                        formatter : function(value, row, index) {
                            if (value=="0") {
                                return "真实货币"
                            }
                            return "虚拟货币";
                        }
                    },



                    {
                        title: '初始价格',
                        field: 'averagePrice',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    },  {
                        title: '价格小数位',
                        field: 'keepDecimalFixPrice',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    },  {
                        title: '数量小数位',
                        field: 'keepDecimalCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '限价涨幅(%)',
                        field: 'rose',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '限价跌幅(%)',
                        field: 'decline',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '买方手续费率(%)',
                        field: 'buyFeeRate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '卖方手续费率(%)',
                        field: 'sellFeeRate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '单笔最小下单数量',
                        field: 'sellMinCoin',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '单笔最大下单数量',
                        field: 'oneTimeOrderNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    },

                    {
                        title: '运行状态',
                        field: 'state',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter : function(value, row, index) {
                                if (value=="0") {
                                    return "<span style='color: red'>已关闭</span>"
                                } else if(value=="2"){
                                    return "<span style='color: grey'>已删除</span>";
                                }
                                return "<span style='color: green'>交易中</span>";
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        //交易对回收站
        trashlist: function () {

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

            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/excointocoin/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/excointocoin/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/excointocoin/remove.do");
            });


            //开启

            $("#open").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if(ids!=undefined&&ids.length==1) {
                    var rows = _table.getRowSelects($("#table"))

                    layer.confirm('你确定要还原'+rows[0].coinCode+'_'+rows[0].fixPriceCoinCode+'交易吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/excointocoin/close/"+ids,
                            data: { },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("开启成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx+'/v.do?u=/admin/exchange/excointocoinlist');
                                    }else {
                                        layer.msg(data.msg, {
                                            icon: 2
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



            //关闭

            $("#close").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if(ids!=undefined&&ids.length==1) {
                    var rows = _table.getRowSelects($("#table"));
                    layer.confirm('你确定要还原'+rows[0].coinCode+'_'+rows[0].fixPriceCoinCode+'交易吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/excointocoin/reset/"+ids,
                            data: { },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("还原成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx+'/v.do?u=admin/exchange/excointocointrashlist');
                                    }else {
                                        layer.msg(data.msg, {
                                            icon: 2
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


            //删除

            $("#remove").on("click", function () {
                var ids = _table.getIdSelects($("#table"));


                if(ids!=undefined&&ids.length==1) {

                    layer.confirm('你确定删除该交易对吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/excointocoin/remove/"+ids,
                            data: { },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("删除成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx+'/v.do?u=/admin/exchange/excointocoinlist');
                                    }else {
                                        layer.msg(data.msg, {
                                            icon: 2
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

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/excointocoin/list.do?state=2",
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
                        formatter : function(value, row, index) {
                            if (value=="0") {
                                return "真实货币"
                            }
                            return "虚拟货币";
                        }
                    },



                    {
                        title: '初始价格',
                        field: 'averagePrice',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    },  {
                        title: '保留小数位数',
                        field: 'keepDecimalFixPrice',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '限价涨幅(%)',
                        field: 'rose',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '限价跌幅(%)',
                        field: 'decline',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '买方手续费率(%)',
                        field: 'buyFeeRate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '卖方手续费率(%)',
                        field: 'sellFeeRate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '单笔最小下单数量',
                        field: 'sellMinCoin',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }, {
                        title: '单笔最大下单数量',
                        field: 'oneTimeOrderNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    },




                    {
                        title: '运行状态',
                        field: 'state',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter : function(value, row, index) {
                            if (value=="0") {
                                return "<span style='color: red'>已关闭</span>"
                            } else if(value=="2"){
                                return "<span style='color: grey'>已删除</span>";
                            }
                            return "<span style='color: green'>交易中</span>";
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        }
    }

});
