define(function(require, exports, module) {
    this._table = require("js/base/table");

    module.exports = {
        //添加页面--初始化方法
        add : function() {
            // 表单验证
            $('#form').bootstrapValidator({
                submitButtons : "button[id=addSubmit]",
                message : '不能为空',
                feedbackIcons : {
                    valid : 'glyphicon glyphicon-ok',
                    invalid : 'glyphicon glyphicon-remove',
                    validating : 'glyphicon glyphicon-refresh'
                },
                fields : {
                    multiple : {
                        validators : {
                            notEmpty : {
                                message : "杠杆倍数不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$/,
                                message: "请填写有效的正整数"
                            }
                        }
                    },
                    coinCode : {
                        validators : {
                            notEmpty : {
                                message : "交易对不能为空"
                            }
                        }
                    },
                    initPrice : {
                        validators : {
                            notEmpty : {
                                message : "初始价格不能为空"
                            }
                        },
                        regexp: {
                            regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                            message: "请填写有效的正数"
                        }
                    },
                    dayRatio : {
                        validators : {
                            notEmpty : {
                                message : "杠杆日利率%不能为空"
                            }
                        }
                    },
                    warningRatio : {
                        validators : {
                            notEmpty : {
                                message : "预警线%不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$/,
                                message: "请填写有效的正整数"
                            }
                        }
                    },
                    pingRatio : {
                        validators : {
                            notEmpty : {
                                message : "平仓线%不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$/,
                                message: "请填写有效的正整数"
                            }
                        }
                    },
                    outRatio : {
                        validators : {
                            notEmpty : {
                                message : "转出线%不能为空"
                            },
                            regexp: {
                                regexp: /^[+]{0,1}(\d+)$/,
                                message: "请填写有效的正整数"
                            }
                        }
                    },
                    coinLendMax : {
                        validators : {
                            notEmpty : {
                                message : "交易币借款上限不能为空"
                            }
                        }
                    },
                    coinLendMin : {
                        validators : {
                            notEmpty : {
                                message : "交易币借款下限不能为空"
                            }
                        }
                    },
                    priLendMax : {
                        validators : {
                            notEmpty : {
                                message : "定价币借款上限不能为空"
                            }
                        }
                    },
                    priLendMin : {
                        validators : {
                            notEmpty : {
                                message : "定价币借款下限不能为空"
                            }
                        }
                    },
                    status : {
                        validators : {
                            notEmpty : {
                                message : "状态不能为空"
                            }
                        }
                    }
                },
                // bv校验通过则提交
                submitHandler : function(validator, form, submitButton) {
                }
            });
            // 添加提交
            $("#addSubmit").on("click", function() {
                var options = {
                    url : _ctx + "/lend/exlendconfig/add.do",
                    type : "post",
                    resetForm : true,// 提交后重置表单
                    dataType : 'json',
                    beforeSubmit : function(formData, jqForm, options) {
                        //重置验证
                        jqForm.data("bootstrapValidator").resetForm();
                        // 手动触发验证
                        const bootstrapValidator = jqForm.data('bootstrapValidator');
                        bootstrapValidator.validate();
                        if (!bootstrapValidator.isValid()) {
                            return false;
                        }
                    },
                    success : function(data, statusText) {
                        if (data != undefined) {
                            if (data.success) {
                                layer.msg('添加成功!', {icon : 1});
                                loadUrl(_ctx+'/v.do?u=/admin/lend/exlendconfiglist')
                            } else {
                                layer.msg(data.msg, {icon : 2});
                            }
                        }
                    }

                };
                $("#form").ajaxSubmit(options);
            });
        },
        //修改页面--初始化方法
        modify : function() {
            // 表单验证
            $('#form').bootstrapValidator({
                submitButtons : "button[id=modifySubmit]",
                message : '不能为空',
                feedbackIcons : {
                    valid : 'glyphicon glyphicon-ok',
                    invalid : 'glyphicon glyphicon-remove',
                    validating : 'glyphicon glyphicon-refresh'
                },
                fields : {
                    multiple : {
                        validators : {
                            notEmpty : {
                                message : "杠杆倍数不能为空"
                            }
                        }
                    },
                    coinCode : {
                        validators : {
                            notEmpty : {
                                message : "交易对不能为空"
                            }
                        }
                    },
                    dayRatio : {
                        validators : {
                            notEmpty : {
                                message : "杠杆日利率%不能为空"
                            }
                        }
                    },
                    warningRatio : {
                        validators : {
                            notEmpty : {
                                message : "预警线%不能为空"
                            }
                        }
                    },
                    pingRatio : {
                        validators : {
                            notEmpty : {
                                message : "平仓线%不能为空"
                            }
                        }
                    },
                    outRatio : {
                        validators : {
                            notEmpty : {
                                message : "转出线%不能为空"
                            }
                        }
                    },
                    coinLendMax : {
                        validators : {
                            notEmpty : {
                                message : "交易币借款上限不能为空"
                            }
                        }
                    },
                    coinLendMin : {
                        validators : {
                            notEmpty : {
                                message : "交易币借款下限不能为空"
                            }
                        }
                    },
                    priLendMax : {
                        validators : {
                            notEmpty : {
                                message : "定价币借款上限不能为空"
                            }
                        }
                    },
                    priLendMin : {
                        validators : {
                            notEmpty : {
                                message : "定价币借款下限不能为空"
                            }
                        }
                    },
                    status : {
                        validators : {
                            notEmpty : {
                                message : "状态不能为空"
                            }
                        }
                    }
                },
                // bv校验通过则提交
                submitHandler : function(validator, form, submitButton) {
                }
            });
            // 修改提交
            $("#modifySubmit").on("click", function() {
                var options = {
                    url : _ctx + "/lend/exlendconfig/modify.do",
                    type : "post",
                    resetForm : true,// 提交后重置表单
                    dataType : 'json',
                    beforeSubmit : function(formData, jqForm, options) {


                        //重置验证
                        jqForm.data("bootstrapValidator").resetForm();
                        // 手动触发验证
                        var bootstrapValidator = jqForm.data('bootstrapValidator');
                        bootstrapValidator.validate();
                        if (!bootstrapValidator.isValid()) {
                            return false;
                        }
                    },
                    success : function(data, statusText) {
                        if (data != undefined) {
                            if (data.success) {
                                layer.msg('修改成功!', {icon : 1});
                                loadUrl(_ctx+'/v.do?u=/admin/lend/exlendconfiglist')
                            } else {
                                layer.msg(data.msg, {icon : 2});
                            }
                        }
                    }
                };
                $("#form").ajaxSubmit(options);
            });
        },
        //列表页面--初始化方法
        list : function() {

            //重置按钮
            $("#table_reset").on("click", function () {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function () {
                //var params = $("#table_query_form").serializeJson();
                var opt = {
                    url: _ctx + "/lend/exlendconfig/list.do",
                    queryParams:function(params){
                        var query = $.extend( true, params, $("#table_query_form").serializeJson() );
                        return query;
                    }
                };
                _table.refreshTable($("#table"), opt);
            });

            // 添加页面跳转按钮
            $("#see").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/lend/exlendconfig/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/lend/exlendconfig/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function() {
                _table.removeRow($("#table"), _ctx + "/lend/exlendconfig/remove.do");
            });

            var conf = {

                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/lend/exlendconfig/list.do",
                columns : [ {
                    checkbox : true,
                    align : 'center',
                    valign : 'middle',
                    value : "id",
                    searchable : false
                },
                    {
                        title : '杠杆倍数',
                        field : 'multiple',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易对',
                        field : 'coinCode',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '杠杆日利率%',
                        field : 'dayRatio',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '预警线%',
                        field : 'warningRatio',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '平仓线%',
                        field : 'pingRatio',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '转出线%',
                        field : 'outRatio',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易币借款上限',
                        field : 'coinLendMax',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易币借款下限',
                        field : 'coinLendMin',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '定价币借款上限',
                        field : 'priLendMax',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '定价币借款下限',
                        field : 'priLendMin',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '状态',
                        field : 'status',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true,
                        formatter : function (value) {
                            if(value===0){
                                return "关闭"
                            }else{
                                return "开启"
                            }
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        //列表页面--初始化方法
        rubbishlist : function() {

            // 移出回收站
            $("#outClick").on("click", function() {
                layer.confirm('你确定要移出回收站吗？', {
                    btn: ['确定','取消'],
                }, function(){
                    var table = $("#table");
                    var ids = $.map(table.bootstrapTable('getSelections'), function(row) {
                        return row.id
                    });
                    if(ids===undefined||ids===''){
                        layer.msg('请选择数据', {icon: 2});
                        return;
                    }
                    $.ajax({
                        type :  'get',
                        url : _ctx + "/lend/exlendconfig/outRubbish",
                        cache : false,
                        dataType : "json",
                        data:{
                            ids : ids.join(",")
                        },
                        success : function(data) {
                            if(data!=undefined){
                                if(data.success){
                                    layer.msg('移出成功', {icon: 1});
                                    //table刷新
                                    table.bootstrapTable('refresh');
                                }else{
                                    layer.msg(data.msg, {icon: 2});
                                }
                            }else{
                                layer.msg("请求无响应", {icon: 2});
                            }
                        },
                        error : function(e) {
                            $("#page-wrapper").html("<div class='row'><h1>此路径不存在："+url.substring(url.indexOf("u=")+2)+"</h1></div>");
                        }
                    });

                })

            });
            var conf = {

                detail : function(e, index, row, $detail) {
                    var html = [];
                    $.each(row, function(key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url : _ctx + "/lend/exlendconfig/rubbishList.do",
                columns : [ {
                    checkbox : true,
                    align : 'center',
                    valign : 'middle',
                    value : "id",
                    searchable : false
                },
                    {
                        title : '杠杆倍数',
                        field : 'multiple',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易对',
                        field : 'coinCode',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '杠杆日利率%',
                        field : 'dayRatio',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '预警线%',
                        field : 'warningRatio',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '平仓线%',
                        field : 'pingRatio',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '转出线%',
                        field : 'outRatio',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易币借款上限',
                        field : 'coinLendMax',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '交易币借款下限',
                        field : 'coinLendMin',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '定价币借款上限',
                        field : 'priLendMax',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '定价币借款下限',
                        field : 'priLendMin',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true
                    },
                    {
                        title : '状态',
                        field : 'status',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true,
                        formatter : function (value) {
                            if(value===0){
                                return "<font color='red'><strong>关闭</strong></font>"
                            }else{
                                return "<font color='green'><strong>开启</strong></font>"
                            }
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        }
    }

});