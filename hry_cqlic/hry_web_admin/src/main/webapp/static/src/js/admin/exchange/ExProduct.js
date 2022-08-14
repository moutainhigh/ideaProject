define(function (require, exports, module) {
    this._table = require("js/base/table");
    this.sele = require("js/base/HrySelect");

    module.exports = {
        //查看页面--初始化方法
        see: function () {
        },
        //添加页面--初始化方法
        add: function () {

            //添加千分位

            $("#totalNum").on("keyup",function(){
                this.value =this.value.replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function(s){
                    return s +',';
                });
                $("#totalNum").val(this.value);
            });

            $("#stock").on("keyup",function(){
                this.value =this.value.replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function(s){
                    return s +',';
                });
                $("#stock").val(this.value);
            });

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
                    name: {
                        validators: {
                            notEmpty: {
                                message: "币种名称不能为空"
                            }
                        }
                    },

                    coinCode: {
                        validators: {
                            notEmpty: {
                                message: "币种代码(英文)不能为空"
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
                $("#totalNum").val($("#totalNum").val().replace(/,/g,''))
                $("#stock").val($("#stock").val().replace(/,/g,''))
                var options = {
                    url: _ctx + "/exchange/exproduct/add",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductlist')
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    }

                };
                $("#form").ajaxSubmit(options);
            });
        },
        //添加页面--初始化方法
        proxyadd: function () {
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
                    name: {
                        validators: {
                            notEmpty: {
                                message: "币种名称不能为空"
                            }
                        }
                    },

                    coinCode: {
                        validators: {
                            notEmpty: {
                                message: "币种代码(英文)不能为空"
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
                    url: _ctx + "/exchange/exproduct/add?isERC20=1",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/proxyexproductlist')
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
            $("#totalNum").on("keyup",function(){
                this.value =this.value.replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function(s){
                    return s +',';
                });
                $("#totalNum").val(this.value);
            });

            $("#stock").on("keyup",function(){
                this.value =this.value.replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function(s){
                    return s +',';
                });
                $("#stock").val(this.value);
            });


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
                    name: {
                        validators: {
                            notEmpty: {
                                message: "币种名称不能为空"
                            }
                        }
                    },

                    coinCode: {
                        validators: {
                            notEmpty: {
                                message: "币种代码(英文)不能为空"
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
                $("#totalNum").val($("#totalNum").val().replace(/,/g,''))
                $("#stock").val($("#stock").val().replace(/,/g,''))


                var options = {
                    url: _ctx + "/exchange/exproduct/modify.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductlist')
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    }
                };
                $("#form").ajaxSubmit(options);
            });
        },
        //交易参数参数修改页面--初始化方法
        modifyparam: function () {
            addpaceType($("#paceType").val());

            $("#paceType").on("change", function () {
                var val = $(this).val();
                var text="提币手续费 (%)";
                if(val == "2"){
                    text="提币手续费";
                }
                $("#paceCurrecy_text").text(text);
                addpaceType(val)
            })

            $("#paceCurrecy1").on("change", function () {
                $("#paceCurrecy").val($("#paceCurrecy1").val())
            })

            function addpaceType(val) {
                if (val == "2") { //手动配置
                    $("#paceCurrecy").hide();
                    $("#paceCurrecy1").show();
                    $(".mycheckbox").show()
                    var fee = $("#paceCurrecy").val().split(",")
                    $.ajax({
                        type: "post",
                        url: _ctx + "/dic/appdic/getKey" ,
                        data: {
                            key:"feeRate"
                        },
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            if (data) {
                                var chebox = "";
                                for(var i=0;i<data.length;i++){
                                    if(fee.indexOf(data[i].value) != -1){
                                        chebox += '<label><input type="checkbox" name="feerate" checked value="'+data[i].value+'">'+data[i].value+'</label>';
                                    }else {
                                        chebox += '<label><input type="checkbox" name="feerate" value="'+data[i].value+'">'+data[i].value+'</label>';
                                    }

                                }
                                $(".mycheckbox").html(chebox)

                            }
                        },
                        error: function (e) {

                        }
                    });



                } else {
                    $("#paceCurrecy").show()
                    $("#paceCurrecy1").hide()
                    $(".mycheckbox").hide()

                }
            }

            $(".mycheckbox").on('click','input',function () {

                var hobbies = document.getElementsByName("feerate");
                var value;
                for (i=0; i<hobbies.length; i++){
                    if (hobbies[i].checked){
                        if (!value){
                            value = hobbies[i].value;
                        } else {
                            value += "," + hobbies[i].value;
                        }
                    }
                }
                $("#paceCurrecy").val(value)
                //alert(value == undefined ? '' : value);



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
                    name: {
                        validators: {
                            notEmpty: {
                                message: "币种名称不能为空"
                            }
                        }
                    },

                    coinCode: {
                        validators: {
                            notEmpty: {
                                message: "币种代码(英文)不能为空"
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
                var fee = $("#paceCurrecy").val();
                if(fee<0){
                    layer.msg('手续费不可小于零!', {icon: 2});
                    return false;
                }
                var options = {
                    url: _ctx + "/exchange/exproduct/modifytwo.do",
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
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/modifysee");
            });


            // parameter修改页面跳转按钮
            $("#parammodify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/parammodifysee");
            });


            // 参数修改页面跳转按钮
            $("#configmodify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/configmodifysee");
            });

            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exproduct/remove.do");
            });

            //发布
            $("#publish").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    var rowData = _table.getRowSelects($("#table"))[0];
                    var issueState = rowData.issueState;
                    if(issueState == "1"){
                        layer.msg("该币种已上架，不用再次添加", {
                            icon: 2,
                        })
                        return;
                    }
                    layer.confirm('你确定要发布吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/exproduct/publishProduct/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("发布成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductlist');
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

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

            //下架
            $("#undercarriage").on("click", function () {
                var rows = _table.getRowSelects($("#table"));
                if (rows != undefined && rows.length == 1) {
                    if (rows[0].issueState == 1) {
                        layer.confirm('你确定要下架吗？', {
                            btn: ['确定', '取消'],
                            ids: rows[0].id
                        }, function () {
                           $.ajax({
                                type: "get",
                                url: _ctx + "/exchange/exproduct/delistProduct/" + rows[0].id,
                                cache: false,
                                dataType: "json",
                                success: function (data) {
                                    if (data) {
                                        if (data.success) {
                                            layer.msg("下架成功", {
                                                icon: 1
                                            });
                                            loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductlist');
                                        } else {
                                            layer.msg(data.msg, {
                                                icon: 2
                                            });
                                        }
                                    }
                                }
                           });
                        });
                    } else if (rows[0].issueState == 2) {
                        layer.msg("已下架币种不能下架", {
                            icon: 2
                        });
                    } else {
                        layer.msg("未上架币种不能下架", {
                            icon: 2
                        });
                    }

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
                        searchable: false
                    },
                    {
                        title: '币的名称',
                        field: 'name',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }, {
                        title: '币的代码',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '发行总量(个)',
                        field: 'totalNum',
                        align: 'right',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (num, row, index) {
                            return (num.toString().indexOf ('.') !== -1) ? num.toLocaleString() : num.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
                        }
                    },

                    {
                        title: '创建时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '发行状态',
                        field: 'issueState',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "已上架"
                            }else if (value == 2) {
                                return "已下架"
                            } else if(value == 0){
                                return "未上架";
                            }
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        //代币列表页面--初始化方法
        proxylist: function () {
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
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/modifysee");
            });


            // parameter修改页面跳转按钮
            $("#parammodify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/parammodifysee");
            });


            // 参数修改页面跳转按钮
            $("#configmodify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/configmodifysee");
            });

            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exproduct/remove.do");
            });

            //发布
            $("#publish").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要发布吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/exproduct/publishProduct/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("发布成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductlist');
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

                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

            //退市
            $("#delistProduct").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要退市吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/exproduct/delistProduct/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("退市成功", {
                                            icon: 1
                                        })
                                        loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductlist');
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
                url: _ctx + "/exchange/exproduct/list?isERC20=1",
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
                        title: '上架币种',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }, {
                        title: '合约地址',
                        field: 'contractAddress',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '精度',
                        field: 'precision',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            var aa = sele.getKeyAndVal("coinprecision",value)
                            debugger
                            return  aa;

                        }
                    },

                    {
                        title: '上币类型',
                        field: 'addCoinType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '操作人',
                        field: 'operator',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    },

                    {
                        title: '操作时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    }
                ]
            }
            _table.initTable($("#table"), conf);
        }

        ,
        //列表页面--初始化方法
        paramlist: function () {

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


            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/modifysee");
            });


            // parameter修改页面跳转按钮
            $("#parammodify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/parammodifysee");
            });


            // 参数修改页面跳转按钮
            $("#configmodify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/configmodifysee");
            });

            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/exchange/exproduct/remove.do");
            });


            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/exchange/exproduct/list?issueState=1",
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
                    title: '名字',
                    field: 'name',
                    align: 'center',
                    visible: true,
                    sortable: false,
                    searchable: true
                }, {
                    title: '代码',
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
                    searchable: true,
                    formatter: function (value, row, index) {
                        if (value == 2) {
                            return "手动配置"
                        }
                        return "固定费率";
                    }
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
                    searchable: true,
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return "开启"
                        }
                        return "关闭";
                    }

                }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        // 币种回收站列表
        callBackList: function() {

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

            //还原按钮
            $("#reduction").on("click", function () {
                var rows = _table.getRowSelects($("#table"))
                if (rows != undefined && rows.length == 1) {
                    layer.confirm('你确定要还原'+rows[0].coinCode+'币种吗？', {
                        btn: ['确定', '取消'],
                        ids: rows[0].id
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/exchange/exproduct/reductionCoin/" + rows[0].id,
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("还原成功", {
                                            icon: 1
                                        });
                                        loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductcallback');
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 2,
                                        });
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
                url: _ctx + "/exchange/exproduct/list?issueState=2",
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
                        title: '币的名称',
                        field: 'name',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }, {
                        title: '币的代码',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '发行总量(个)',
                        field: 'totalNum',
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
                    },
                    {
                        title: '发行状态',
                        field: 'issueState',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "已上架"
                            }else if (value == 2) {
                                return "已下架"
                            } else if(value == 0){
                                return "未上架";
                            }
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        otclist: function(){
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/exchange/exproduct/otcModifySee");
            });

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
                    detail: function (e, index, row, $detail) {
                        var html = [];
                        $.each(row, function (key, value) {
                            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                        });
                        $detail.html(html.join(''));
                    },
                    url: _ctx + "/exchange/exproduct/otcList",
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
                        title: '币的名称',
                        field: 'name',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }, {
                        title: '币的代码',
                        field: 'coinCode',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '币价格百分比',
                        field: 'coinPercent',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '吃单交易手续费类型',
                        field: 'eatFeeType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 0) {
                                return "固定费率";
                            }else if (value == 1) {
                                return "百分比费率";
                            }
                        }
                    },
                    {
                        title: '吃单交易手续费',
                        field: 'eatFee',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '状态',
                        field: 'otcState',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == 0) {
                                return "否";
                            }else if (value == 1) {
                                return "是";
                            }
                        }
                    },
                    {
                        title: 'otc限价最低百分比',
                        field: 'otcMinPercent',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: 'otc限价最高百分比',
                        field: 'otcMaxPercent',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        otcAdd: function(){
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
                	eatFee: {
                        validators: {
                            notEmpty: {
                                message: "吃单交易手续费"
                            }
                        }
                    },
                    coinPercent: {
                        validators: {
                            notEmpty: {
                                message: "币价格百分比"
                            }
                        }
                    },
                    otcMinPercent: {
                        validators: {
                            notEmpty: {
                                message: "otc限价最低百分比"
                            }
                        }
                    },
                    otcMaxPercent: {
                        validators: {
                            notEmpty: {
                                message: "otc限价最高百分比"
                            }
                        }
                    }
                },
                // bv校验通过则提交
                submitHandler: function (validator, form, submitButton) {
                }
            });
            // 添加提交
            $("#addSubmit").on("click", function () {debugger
                var options = {
                    url: _ctx + "/exchange/exproduct/otcAdd",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductotclist')
                            } else {
                                layer.msg("新增失败", {icon: 2});
                            }
                        }
                    }

                };
                $("#form").ajaxSubmit(options);
            });
        },
        otcModify: function(){

            $("#otcState").val($("#otcStateModel").val());
            $("#eatFeeType").val($("#eatFeeTypeModel").val());

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
                    eatFee: {
                        validators: {
                            notEmpty: {
                                message: "吃单交易手续费"
                            }
                        }
                    },
                    coinPercent: {
                        validators: {
                            notEmpty: {
                                message: "币价格百分比"
                            }
                        }
                    },
                    otcMinPercent: {
                        validators: {
                            notEmpty: {
                                message: "otc限价最低百分比"
                            }
                        }
                    },
                    otcMaxPercent: {
                        validators: {
                            notEmpty: {
                                message: "otc限价最高百分比"
                            }
                        }
                    }
                },
                // bv校验通过则提交
                submitHandler: function (validator, form, submitButton) {
                }
            });
            // 添加提交
            $("#modifySubmit").on("click", function () {debugger
                var options = {
                    url: _ctx + "/exchange/exproduct/otcAdd",
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
                                loadUrl(_ctx + '/v.do?u=/admin/exchange/exproductotclist')
                            } else {
                                layer.msg("修改失败", {icon: 2});
                            }
                        }
                    }

                };
                $("#form").ajaxSubmit(options);
            });
        }
    }
});