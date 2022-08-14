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


                },
                // bv校验通过则提交
                submitHandler: function (validator, form, submitButton) {
                }
            });
            // 添加提交
            $("#addSubmit").on("click", function () {
                var options = {
                    url: _ctx + "/exchange/exproductparameter/add.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductparameterlist')
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
                    buyFeeRate: {
                        validators: {
                            notEmpty: {
                                message: "buyFeeRate不能为空"
                            }
                        }
                    },
                    sellFeeRate: {
                        validators: {
                            notEmpty: {
                                message: "sellFeeRate不能为空"
                            }
                        }
                    },
                    buyMinMoney: {
                        validators: {
                            notEmpty: {
                                message: "buyMinMoney不能为空"
                            }
                        }
                    },
                    sellMinCoin: {
                        validators: {
                            notEmpty: {
                                message: "sellMinCoin不能为空"
                            }
                        }
                    },
                    state: {
                        validators: {
                            notEmpty: {
                                message: "state不能为空"
                            }
                        }
                    },
                    name: {
                        validators: {
                            notEmpty: {
                                message: "name不能为空"
                            }
                        }
                    },
                    prepaidFeeRate: {
                        validators: {
                            notEmpty: {
                                message: "prepaidFeeRate不能为空"
                            }
                        }
                    },
                    paceFeeRate: {
                        validators: {
                            notEmpty: {
                                message: "paceFeeRate不能为空"
                            }
                        }
                    },
                    oneTimePaceNum: {
                        validators: {
                            notEmpty: {
                                message: "oneTimePaceNum不能为空"
                            }
                        }
                    },
                    oneDayPaceNum: {
                        validators: {
                            notEmpty: {
                                message: "oneDayPaceNum不能为空"
                            }
                        }
                    },
                    leastPaceNum: {
                        validators: {
                            notEmpty: {
                                message: "leastPaceNum不能为空"
                            }
                        }
                    },
                    saasId: {
                        validators: {
                            notEmpty: {
                                message: "saasId不能为空"
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
                    url: _ctx + "/exchange/exproductparameter/modify.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductparameterlist')
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

            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproductparameter/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproductparameter/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exproductparameter/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exproduct/list",
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
                        title: '币的名称',
                        field: 'name',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '币种代码',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '提币费率类型',
                        field: 'paceType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '提币费率',
                        field: 'paceCurrecy',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '单日提币限额(个)',
                        field: 'oneDayPaceNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '最小提币个数(个)',
                        field: 'leastPaceNum',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '实名送币数量',
                        field: 'giveCoin',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '是否可以提币',
                        field: 'openTibi',
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