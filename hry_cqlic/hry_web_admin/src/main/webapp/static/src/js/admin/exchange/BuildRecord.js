define(function (require, exports, module) {
    this._table = require("js/base/table");

    module.exports = {
        //查看页面--初始化方法
        see: function () {
        },
        //添加页面--初始化方法
        add: function () {
            $("#confirmBuild").on("click", function () {
                var start = $("#startTime").val()
                var end = $("#endTime").val()
                var coinCode = $("#coinCode").val()
                if (start == "") {
                    layer.msg("开始时间必选", {
                        icon: 2,
                    })
                    return false;
                }
                if (end == "") {
                    layer.msg("结束时间必选", {
                        icon: 2,
                    })
                    return false;
                }
                var time = new Date(end).getTime() - new Date(start).getTime();
                if(time /1000/60 - 1440 > 0){
                    layer.msg("时间区间不可超过一天", {
                        icon: 2,
                    })
                    return false;
                }

                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    title: "正在修复。。。",
                    shadeClose: true, //开启遮罩关闭
                    area: ['280px', '222px'],
                    content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/' + _version + '/img/testCoin.gif" style="margin-top:65px;"></span>'
                });
                $.ajax({
                    type: "post",
                    url: _ctx + "/klineSectionSave",
                    data: {
                        startTime: start,
                        endTime: end,
                        tradeCoinCode: coinCode.split("_")[0],
                        priceCoinCode: coinCode.split("_")[1],
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg('修复成功!', {icon: 1});
                                loadUrl(_ctx + '/v.do?u=admin/exchange/buildrecordlist')
                            } else {
                                layer.msg(data.msg, {icon: 2});
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
                    startTime: {
                        validators: {
                            notEmpty: {
                                message: "startTime不能为空"
                            }
                        }
                    },
                    endTime: {
                        validators: {
                            notEmpty: {
                                message: "endTime不能为空"
                            }
                        }
                    },
                    pullTime: {
                        validators: {
                            notEmpty: {
                                message: "pullTime不能为空"
                            }
                        }
                    },
                    tradeCoinCode: {
                        validators: {
                            notEmpty: {
                                message: "tradeCoinCode不能为空"
                            }
                        }
                    },
                    priceCoinCode: {
                        validators: {
                            notEmpty: {
                                message: "priceCoinCode不能为空"
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
                    url: _ctx + "/exchange/buildrecord/add.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/buildrecordlist')
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
                    startTime: {
                        validators: {
                            notEmpty: {
                                message: "startTime不能为空"
                            }
                        }
                    },
                    endTime: {
                        validators: {
                            notEmpty: {
                                message: "endTime不能为空"
                            }
                        }
                    },
                    pullTime: {
                        validators: {
                            notEmpty: {
                                message: "pullTime不能为空"
                            }
                        }
                    },
                    tradeCoinCode: {
                        validators: {
                            notEmpty: {
                                message: "tradeCoinCode不能为空"
                            }
                        }
                    },
                    priceCoinCode: {
                        validators: {
                            notEmpty: {
                                message: "priceCoinCode不能为空"
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
                    url: _ctx + "/exchange/buildrecord/modify.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/buildrecordlist')
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
            //重置按钮
            $("#table_reset").on("click", function () {
                //$("#table_query_form")[0].reset();
                loadUrl(_ctx + '/v.do?u=/admin/exchange/buildrecordlist')
            });
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });

            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/buildrecord/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/buildrecord/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/buildrecord/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/buildrecord/list.do",
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
                        title: '交易币种',
                        field: 'tradeCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '交易区',
                        field: 'priceCoinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '构建时间',
                        field: 'pullTime',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }

                    ,
                    {
                        title: '修复起点时间',
                        field: 'startTime',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '修复终点时间',
                        field: 'endTime',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '1m修复量',
                        field: 'm1',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '5m修复量',
                        field: 'm5',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '15m修复量',
                        field: 'm15',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '30m修复量',
                        field: 'm30',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '60m修复量',
                        field: 'm60',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '24h修复量',
                        field: 'day1',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '1w修复量',
                        field: 'week1',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }

                ]
            }
            _table.initTable($("#table"), conf);
        },
        upload: function(){
            $("#uploadExcel").on("click", function(){
                $("#efile").click();
            })

            function isFloat(num){debugger
                var reg = /^(-?\d+)(\.\d+)?$/;
                if(reg.test(num)){
                    return true;
                }
                return false;
            }

            $("#excelOk").on("click", function(){debugger
                var formData = new FormData();

                var coinCode = $("#coinCode").val().split('_');

                if($("#coinCode").val() == ''){
                    layer.msg("请选择币种", {icon: 2});
                    return false;
                }
                var coinCode = $("#coinCode").val().split('_');

                var start = $("#start").val(); // 开
                var high = $("#high").val(); // 高
                var low = $("#low").val(); // 低
                var end = $("#end").val(); // 收
                var amount = $("#amount").val(); // 收
                var time = $("#time").val(); // 时间

                if(start == ''){
                    layer.msg("开盘价不能为空", {icon: 2});
                    return false;
                }
                if(!isFloat(start)){
                    layer.msg("开盘价必须为数字", {icon: 2});
                    return false;
                }
                if(high == ''){
                    layer.msg("最高价不能为空", {icon: 2});
                    return false;
                }
                if(!isFloat(high)){
                    layer.msg("最高价必须为数字", {icon: 2});
                    return false;
                }
                if(low == ''){
                    layer.msg("最低价不能为空", {icon: 2});
                    return false;
                }
                if(!isFloat(low)){
                    layer.msg("最低价必须为数字", {icon: 2});
                    return false;
                }
                if(end == ''){
                    layer.msg("收盘价不能为空", {icon: 2});
                    return false;
                }
                if(!isFloat(end)){
                    layer.msg("收盘价必须为数字", {icon: 2});
                    return false;
                }
                if(amount == ''){
                    layer.msg("数量不能为空", {icon: 2});
                    return false;
                }
                if(!isFloat(amount)) {
                    layer.msg("数量必须为数字", {icon: 2});
                    return false;
                }
                if(time == ''){
                    layer.msg("时间不能为空", {icon: 2});
                    return false;
                }
                var reg = /^[1-2][0-9][0-9][0-9]-([1][0-2]|0?[1-9])-([12][0-9]|3[01]|0?[1-9]) ([01][0-9]|[2][0-3]):[0-5][0-9]$/;
                if(!reg.test(time)){
                    layer.msg("时间格式不正确，请看下方红色小字提示", {icon: 2});
                    return false;
                }

//^[1-2][0-9][0-9][0-9]-([1][0-2]|0?[1-9])-([12][0-9]|3[01]|0?[1-9]) ([01][0-9]|[2][0-3]):[0-5][0-9]$


                /*if($('#efile').val() == ''){
                    layer.msg("请上传文件", {icon: 2});
                    return false;
                }

                var name = $('#efile')[0].files[0].name.split('.')[1];
                if(name.indexOf("xls") == -1){
                    layer.msg("请上传xlsx或者xls为后缀的文件", {icon: 2});
                    return false;
                }

                var efile = $('#efile')[0].files[0]; 
                var coinCode = $("#coinCode").val().split('_');
                formData.append("filePath", $('#efile').val());
                formData.append("efile", efile);*/

                formData.append("tradeCoinCode", coinCode[0]);
                formData.append("priceCoinCode", coinCode[1]);
                formData.append("start", start);
                formData.append("high", high);
                formData.append("low", low);
                formData.append("end", end);
                formData.append("amount", amount);
                formData.append("time", time);



                $.ajax({
                    type: "post",
                    url: _ctx + "/kLineUpdateExcel.do",
                    data: formData,
                    processData: false,
                    contentType: false,
                    dataType: "json",
                    success: function (data) {debugger
                        if(data.success){
                            layer.msg(data.msg, {icon: 1}, function(){
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/buildrecordlist');
                            });
                        }else{
                            layer.msg(data.msg, {icon: 2});
                        }
                    }
                });
            })
        }
    }

});