define(function (require, exports, module) {
    this._table = require("js/base/table");

    module.exports = {
        //查看页面--初始化方法
        see: function () {
        },
        //添加页面--初始化方法
        add: function () {
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
                    saasId: {
                        validators: {
                            notEmpty: {
                                message: "saasId不能为空"
                            }
                        }
                    },
                    fromAddress: {
                        validators: {
                            notEmpty: {
                                message: "fromAddress不能为空"
                            }
                        }
                    },
                    toAddress: {
                        validators: {
                            notEmpty: {
                                message: "toAddress不能为空"
                            }
                        }
                    },
                    amount: {
                        validators: {
                            notEmpty: {
                                message: "amount不能为空"
                            }
                        }
                    },
                    operator: {
                        validators: {
                            notEmpty: {
                                message: "operator不能为空"
                            }
                        }
                    },
                    tx: {
                        validators: {
                            notEmpty: {
                                message: "tx不能为空"
                            }
                        }
                    },
                    coinCode: {
                        validators: {
                            notEmpty: {
                                message: "coinCode不能为空"
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
                    url: _ctx + "/exchange/exdmtransfcolddetail/add.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exdmtransfcolddetaillist')
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
                    saasId: {
                        validators: {
                            notEmpty: {
                                message: "saasId不能为空"
                            }
                        }
                    },
                    fromAddress: {
                        validators: {
                            notEmpty: {
                                message: "fromAddress不能为空"
                            }
                        }
                    },
                    toAddress: {
                        validators: {
                            notEmpty: {
                                message: "toAddress不能为空"
                            }
                        }
                    },
                    amount: {
                        validators: {
                            notEmpty: {
                                message: "amount不能为空"
                            }
                        }
                    },
                    operator: {
                        validators: {
                            notEmpty: {
                                message: "operator不能为空"
                            }
                        }
                    },
                    tx: {
                        validators: {
                            notEmpty: {
                                message: "tx不能为空"
                            }
                        }
                    },
                    coinCode: {
                        validators: {
                            notEmpty: {
                                message: "coinCode不能为空"
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
                var options = {
                    url: _ctx + "/exchange/exdmtransfcolddetail/modify.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exdmtransfcolddetaillist')
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    }
                };
                $("#form").ajaxSubmit(options);
            });
        },
        //归集币种冷账户明细
        list: function () {

            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransfcolddetail/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransfcolddetail/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exdmtransfcolddetail/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exdmtransfcolddetail/list.do",
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
                        searchable: true
                    },
                    {
                        title: '转入地址',
                        field: 'toAddress',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '金额',
                        field: 'amount',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '操作人员',
                        field: 'operator',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '流水号',
                        field: 'tx',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },

        //归集币种冷账户
        operationlist: function () {
            $("#toCold").on("click",function () {
                var ids = _table.getIdSelects($("#table"));
                var row = _table.getRowSelects($("#table"))

                if(ids!=undefined&&ids.length==1) {
                    $("#type").val(row[0].coinCode);
                    $("#amount").val(row[0].amount)
                    layer.open({
                        type : 1,
                        title : "请输入转出金额：",
                        closeBtn : 2,
                        area : [ '450px', '205px' ],
                        shadeClose : true,
                        zIndex :778,
                        content : $('#toColdAccount')
                    });

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            $("#confirmBtn").on("click",function () {
                var toMoney=$('#toMoney').val();
                var type=$('#type').val();
                var amount=$('#amount').val();
                toMoney=parseFloat(toMoney);
                amount=parseFloat(amount);

                if(toMoney<amount){
                    $.ajax({
                        type: "post",
                        url: _ctx + "/exchange/exdmtransfcolddetail/toColdAccount",
                        data: {
                            type:type,
                            amount:amount
                        },
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            if (data) {
                                if (data.success) {
                                    layer.msg("取消成功", {
                                        icon: 1,
                                    })
                                    loadUrl(_ctx + '/v.do?u=admin/exchange/exentrustlistnow');
                                } else {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    })
                                }
                            }
                        },
                        error: function (e) {

                        }
                    });
                }else{
                    layer.msg("余额不足", {
                        icon: 1,
                    })
                }

            })



            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransfcolddetail/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransfcolddetail/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exdmtransfcolddetail/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exdmtransfcolddetail/listWalletBalance",
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
                        searchable: true
                    },
                    {
                        title: '币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '总余额',
                        field: 'totalMoney',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '提币地址余额',
                        field: 'withdrawalsAddressMoney',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '提币地址',
                        field: 'withdrawalsAddress',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '冷钱包地址',
                        field: 'coldwalletAddress',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },

        //代币资产信息
        listToken: function () {
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

            $("#collect").on("click",function () {
                var ids = _table.getIdSelects($("#table"));
                var rows = _table.getRowSelects($("#table"));

                if(ids!=undefined&&ids.length==1) {
                    //代币余额
                    var tokenAssets = rows[0].tokenAssets;
                    //地址
                    var address = rows[0].address;
                    //币种
                    var coinType = $("#input_lname").val();
                    $.ajax({
                        type: 'post',
                        url:_ctx + "/exchange/exdmtransfcolddetail/tokenCollect",
                        data: {
                            coinType: coinType,
                            amount: tokenAssets,
                            address: address
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data != null && data.success) {
                                layer.msg('操作成功', {icon: 1});
                            } else if (data.msg != null) {
                                layer.msg(data.msg, {icon: 1});
                                return false;
                            } else {
                                layer.msg('后台处理异常！', {icon: 1});
                                return false;
                            }
                        }
                    });

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            $("#addfee").on("click",function () {
                var ids = _table.getIdSelects($("#table"));

                if(ids!=undefined&&ids.length==1) {
                    layer.open({
                        type : 1,
                        title : "请输入转出金额：",
                        closeBtn : 2,
                        area : [ '450px', '205px' ],
                        shadeClose : true,
                        zIndex :778,
                        content : $('#toColdAccount')
                    });
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }

            })

            $("#confirmBtn").on("click",function () {
                var amount=$('#amount').val();
                var address=$("#address").val();
                var coinType=$('#input_lname').val();
                if(amount>=0&&address!=''&&address!=undefined){
                    $.ajax({
                        type: 'post',
                        url: _ctx + "/exchange/exdmtransfcolddetail/rechargeTxFee2TokenAddress",
                        data: {
                            amount:amount,
                            address:address,
                            coinType:coinType
                        },
                        dataType: "json",
                        success: function(data){
                            if(data!=null&&data.success){
                                layer.msg('操作成功!', {icon: 1});
                            }else if(data.msg!=null){
                                layer.msg(data.msg, {icon: 1});
                            }else{
                                layer.msg('后台处理异常', {icon: 1});
                            }
                            layer.closeAll();
                        }
                    });
                }else{
                    layer.closeAll();
                    layer.msg('余额不足!', {icon: 1});
                    return false;
                }
            })


            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransfcolddetail/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exdmtransfcolddetail/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exdmtransfcolddetail/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exdmtransfcolddetail/listTokenAssets",
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
                        searchable: true
                    },
                    {
                        title: '地址',
                        field: 'address',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '代币余额',
                        field: 'tokenAssets',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '以太坊余额',
                        field: 'etherAssets',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '是否可归集',
                        field: 'abledCollect',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        }
    }

});