define(function (require, exports, module) {
    this._table = require("js/base/table");

    module.exports = {
        //查看页面--初始化方法
        see: function () {
        },
        //添加页面--初始化方法
        add: function () {
            $("#exchange").on('change',function () {
                //alert($(this).val())
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/exlawcoin/findByName",
                    data: {
                        name:$(this).val()
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                               $("#coinSymbol").val(data.obj.coinSymbol)
                                $("#coinCode").val(data.obj.coinCode)
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
                    lan: {
                        validators: {
                            notEmpty: {
                                message: "语种不能为空"
                            }
                        }
                    },
                    exchange: {
                        validators: {
                            notEmpty: {
                                message: "法币不能为空"
                            }
                        }
                    },
                    rate: {
                        validators: {
                            notEmpty: {
                                message: "费率不能为空"
                            }
                        }
                    },
                    coinSymbol: {
                        validators: {
                            notEmpty: {
                                message: "coinSymbol不能为空"
                            }
                        }
                    },
                    coinCode: {
                        validators: {
                            notEmpty: {
                                message: "coinCode不能为空"
                            }
                        }
                    },
                    isSynC2C: {
                        validators: {
                            notEmpty: {
                                message: "isSynC2C不能为空"
                            }
                        }
                    },
                    state: {
                        validators: {
                            notEmpty: {
                                message: "state不能为空"
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
                    url: _ctx + "/exchange/excointomoney/add.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/excointomoneylist')
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
            $("#exchange").on('change',function () {
                //alert($(this).val())
                $.ajax({
                    type: "post",
                    url: _ctx + "/exchange/exlawcoin/findByName",
                    data: {
                        name:$(this).val()
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                                $("#coinSymbol").val(data.obj.coinSymbol)
                                $("#coinCode").val(data.obj.coinCode)
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
                    lan: {
                        validators: {
                            notEmpty: {
                                message: "lan不能为空"
                            }
                        }
                    },
                    exchange: {
                        validators: {
                            notEmpty: {
                                message: "exchange不能为空"
                            }
                        }
                    },
                    rate: {
                        validators: {
                            notEmpty: {
                                message: "rate不能为空"
                            }
                        }
                    },
                    coinSymbol: {
                        validators: {
                            notEmpty: {
                                message: "coinSymbol不能为空"
                            }
                        }
                    },
                    coinCode: {
                        validators: {
                            notEmpty: {
                                message: "coinCode不能为空"
                            }
                        }
                    },
                    isSynC2C: {
                        validators: {
                            notEmpty: {
                                message: "isSynC2C不能为空"
                            }
                        }
                    },
                    state: {
                        validators: {
                            notEmpty: {
                                message: "state不能为空"
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
                    url: _ctx + "/exchange/excointomoney/modify.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/excointomoneylist')
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
            $("#close,#open").on('click',function () {
                //alert(this.id)

                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {
                    var thisid = this.id;

                    var msg = "" ;
                    var state ="";
                    if(thisid=="open"){
                        msg ="启用",
                        state="1";
                    }else{
                        msg = "禁用";
                        state="0";
                    }
                    layer.confirm('你确定要'+msg+'吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {

                        $.ajax({
                            type: "post",
                            url: _ctx + "/exchange/excointomoney/setState/" + ids,
                            data: {
                                state:state
                            },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("成功", {
                                            icon: 1,
                                        })
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 2,
                                        })
                                    }

                                    loadUrl(_ctx + '/v.do?u=admin/exchange/excointomoneylist')
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


            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/excointomoney/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/excointomoney/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/excointomoney/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/excointomoney/list.do",
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
                        title: '语种',
                        field: 'lan',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '货币兑换',
                        field: 'exchange',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {

                            return "人民币兑"+value;
                        }
                    },
                    {
                        title: '汇率',
                        field: 'rate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '币种符号',
                        field: 'coinSymbol',
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
                        title: 'C2C是否同步',
                        field: 'isSynC2C',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "否"
                            }
                            return "是";
                        }
                    },
                    {
                        title: '状态',
                        field: 'state',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "禁用"
                            }
                            return "启用";
                        }
                    },
                    {
                        title: '创建人',
                        field: 'creator',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '创建时间',
                        field: 'created',
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