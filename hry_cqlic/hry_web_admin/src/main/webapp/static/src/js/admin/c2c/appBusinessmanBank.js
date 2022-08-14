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
                    bankcard: {
                        validators: {
                            notEmpty: {
                                message: "银行卡号不能为空"
                            }
                        }
                    },
                    bankname: {
                        validators: {
                            notEmpty: {
                                message: "开户行不能为空"
                            }
                        }
                    },
                    bankowner: {
                        validators: {
                            notEmpty: {
                                message: "持卡人不能为空"
                            }
                        }
                    },
                    businessmanId: {
                        validators: {
                            notEmpty: {
                                message: "请选择交易商"
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
                    url: _ctx + "/c2c/appbusinessmanbank/add.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/c2c/appbusinessmanbanklist')
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
                    bankcard: {
                        validators: {
                            notEmpty: {
                                message: "银行卡号不能为空"
                            }
                        }
                    },
                    bankname: {
                        validators: {
                            notEmpty: {
                                message: "开户行不能为空"
                            }
                        }
                    },
                    bankowner: {
                        validators: {
                            notEmpty: {
                                message: "持卡人不能为空"
                            }
                        }
                    },
                    businessmanId: {
                        validators: {
                            notEmpty: {
                                message: "请选择交易商"
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
                    url: _ctx + "/c2c/appbusinessmanbank/modify.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/c2c/appbusinessmanbanklist')
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
                _table.seeRow($("#table"), _ctx + "/c2c/appbusinessmanbank/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                var ids = $.map($("#table").bootstrapTable('getSelections'), function(row) {
                    return row.id
                });
                if(ids!=undefined&&ids.length==1){
                    $.get(_ctx+ "/c2c/appbusinessmanbank/getC2cList?type=2&id=" +ids[0],function(data) {
                        if(data.length>0){
                            layer.msg('银行卡已产生数据，不可修改!', {icon: 2});
                        }else {
                            _table.seeRow($("#table"), _ctx + "/c2c/appbusinessmanbank/modifysee");
                        }
                    },"json");
                }else{
                    layer.msg('请选择一条数据', {icon: 2});
                }


            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                var ids = $.map($("#table").bootstrapTable('getSelections'), function(row) {
                    return row.id
                });
                if(ids!=undefined&&ids.length==1){
                    $.get(_ctx+ "/c2c/appbusinessmanbank/getC2cList?type=2&id=" +ids[0],function(data) {
                        if(data.length>0){
                            layer.msg('银行卡已产生数据，不可删除!', {icon: 2});
                        }else {
                            _table.removeRow($("#table"), _ctx + "/c2c/appbusinessmanbank/remove.do");
                        }
                    },"json");
                }else{
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
                url: _ctx + "/c2c/appbusinessmanbank/list.do",
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
                        title: '交易商',
                        field: 'businessName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '开户行',
                        field: 'bankname',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '银行卡号',
                        field: 'bankcard',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '持卡人',
                        field: 'bankowner',
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