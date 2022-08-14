define(function (require, exports, module) {
    this._table = require("js/base/table");
    this._dic = require("js/base/HrySelect");

    module.exports = {
        //查看页面--初始化方法
        see: function () {
            //编辑
            $('#table_modify').on('click', function () {
                _table.seeRow($("#table"), _ctx + "/web/appmessage/modifysee");
            })

            // 删除按钮点击事件
            $("#remove").on("click", function () {
                removeRow($("#table"), _ctx + "/web/appmessage/remove.do");
            });

            function removeRow(table,url){
                var ids = $.map(table.bootstrapTable('getSelections'), function(row) {
                    return row.id
                });
                if(ids!=undefined&&ids.length>0){

                    layer.confirm('你确定要撤销吗？', {
                        btn: ['确定','取消'],
                        ids : ids
                    }, function(){
                        $.ajax({
                            type : "post",
                            url : url,
                            cache : false,
                            dataType : "json",
                            data:{
                                ids : ids.join(",")
                            },
                            success : function(data) {
                                if(data!=undefined){
                                    if(data.success){
                                        layer.msg('撤销成功', {icon: 1});
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


                }else{
                    layer.msg('请选择数据', {icon: 2});
                    return false;
                }
            }
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

            // 获取选中标签的简称
            $(".languageQuery").on("click", function () {
                var languageDic = $(this).attr("languageType");
                $("#languageDic").val(languageDic);
                var params = {
                    languageDic: languageDic
                };
                //查询
                _table.tableQuery($("#table"), params);
            });

            $("#send_btn").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var row = _table.getRowSelects($("#table"));
                var langCode = $("#languageDic").val();
                if (ids.length == 0) {
                    layer.msg('请选择数据', {icon: 2});
                    return false;
                } else {
                    layer.confirm('你确定发送吗？', {
                        btn: ['确定', '取消'],
                        ids: ids
                    }, function () {
                        if (row[0].categoryName == '0') {
                            layer.msg('发送失败，请添加收件人', {icon: 2});
                            return false;
                        }

                        $.ajax({
                            type: 'post',
                            url: _ctx + "/web/appmessage/send?ids[]=" + ids,
                            async: false,
                            dataType: 'json',
                            success: function (data) {
                                if (data.success) {
                                    layer.msg('发送成功!', {icon: 1});
                                    loadUrl(_ctx + '/web/appmessage/toDraftBox/'+langCode)
                                } else {
                                    layer.msg("发送失败", {icon: 2});
                                }
                            }
                        });
                    })


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
                url: _ctx + "/web/appmessage/messageList/2",
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },

                    {
                        title: '标题',
                        field: 'title',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '收件人数',
                        field: 'categoryName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            /* if(value==0){
                                 return '所有人'
                             }*/
                            return value + '人';
                        }
                    },
                    {
                        title: '创建人',
                        field: 'operator',
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
                        searchable: true,

                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        //添加页面--初始化方法
        add: function () {
            $("#reset").on("click", function () {

                $('#userids').val('');
                $('#selectedUser').html('已选择(0人)')

                layer.msg('重置成功', {icon: 1});
                return;
            });

            //重置按钮
            $("#table_reset1").on("click", function () {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query1").on("click", function () {
                var params = $("#table_query_form1").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });

            //添加发送站内信的用户
            function listtable(table) {
                var conf = {
                    url: _ctx + "/customer/appcustomer/list.do",
                    columns: [{
                        field: 'state',
                        checkbox: true,
                        align: 'center',
                        valign: 'middle',
                        value: "id",
                        searchable: false
                    }, {
                        title: 'ID',
                        field: 'id',
                        align: 'center',
                        visible: false,
                        searchable: false
                    }, {
                        title: '邮箱',
                        field: 'appPersonInfo.email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            return row.appPersonInfo.email;
                        }
                    },
                        {
                            title: '手机号',
                            field: 'appPersonInfo.mobilePhone',
                            align: 'center',
                            visible: true,
                            sortable: false,
                            searchable: true,
                            formatter: function (value, row, index) {
                                return row.appPersonInfo.mobilePhone;
                            }
                        },
                        {
                            title: '姓名',
                            field: 'appPersonInfo.trueName',
                            align: 'center',
                            visible: true,
                            sortable: false,
                            searchable: true,
                            formatter: function (value, row, index) {
                                if(!row.appPersonInfo){
                                    return ""
                                }
                                return row.appPersonInfo.surname + row.appPersonInfo.trueName;
                            }
                        },
                        {
                            title: '常用语种',
                            field: 'commonLanguage',
                            align: 'center',
                            visible: true,
                            sortable: false,
                            searchable: true,
                            /*formatter: function (value, row, index) {
                                return row.appPersonInfo.surname + row.appPersonInfo.trueName;
                            }*/
                        }]
                }
                _table.initTable(table, conf);
            }


            $('#addUser').on('click', function () {
                var tableHtml = "<table   id='table'" +
                    "       data-show-refresh=\"false\"   " +
                    "       data-show-columns=\"false\"   " +
                    "       data-show-export=\"false\"    " +
                    "       data-search=\"false\"  " +
                    "       data-detail-view=\"false\"  " +
                    "       data-minimum-count-columns=\"2\"  " +
                    "       data-pagination=\"true\"  " +
                    "       data-id-field=\"id\"  " +
                    "       data-page-list=\"[10, 25, 50, 100]\"  " +
                    "       data-show-footer=\"false\"    " +
                    "       data-side-pagination=\"server\"  " +
                    "       >  " +
                    "</table>";

                layer.open({
                    type: 1,
                    title: "<strong>添加收件人</strong>",
                    skin: 'layui-layer-rim', //加上边框
                    area: ['70%', '70%'], //宽高,
                    content: $("#searchDiv"),
                    closeBtn: 2,
                    shadeClose: false,
                    shade: 0,
                    success: function (layero, index) {
                        //弹出成功移除隐藏
                        $("#searchDiv").removeClass("hide")
                        $("#tableDiv").empty().html(tableHtml);
                        listtable($("#table"));

                        $("#table").on('click-row.bs.table', function (e, row, $element) {
                            $element.children().eq(0).children().eq(0).click();
                        });
                    },
                    cancel: function () {
                        //关闭回调设置隐藏
                        $("#searchDiv").addClass("hide")
                    }
                });

            })

            //数组去重
            function unique(array) {
                var res = [];
                for (var i = 0, len = array.length; i < len; i++) {
                    var current = array[i];
                    if (res.indexOf(current) === -1) {
                        res.push(current)
                    }
                }
                return res;
            }

            //添加收件人
            $('#searchDiv').on('click', '#addUserbtn', function () {
                var ids = _table.getIdSelects($("#searchDiv" + " #table"));
                if (ids != undefined && ids.length >= 1) {
                    if ($('#userids').val() != '') {
                        var old = $('#userids').val().split(',');
                        Array.prototype.push.apply(old, ids);
                        var newids = unique(old);
                        $('#userids').val(newids);
                    } else {
                        $('#userids').val(ids);
                    }
                    $('#selectedUser').html('已选择(' + $('#userids').val().split(',').length + '人)')
                    layer.closeAll();
                    layer.msg('添加成功', {icon: 1});
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })

            //重置
            $('#table_reset1, #table_reset').on('click', function () {
                 $("input[name='email']").val('');
                 $("input[name='mobilePhone']").val('');
                 $("input[name='surname']").val('');
                 $("input[name='trueName']").val('');
                 $("#commonLanguage1").val('');
                 $("#commonLanguage").val('');
            })

            //删除数组元素
            Array.prototype.indexOf = function (val) {
                for (var i = 0; i < this.length; i++) {
                    if (this[i] == val) return i;
                }
                return -1;
            };
            Array.prototype.remove = function (val) {
                var index = this.indexOf(val);
                if (index > -1) {
                    this.splice(index, 1);
                }
            };

            initMessageCategory("zh_CN");
            // 初始化级联操作
            $("#messageCategory").on("change", function () {
                var langName = $(this).val();
                initMessageCategory(langName);
            });

            // 提交并发送
            $("#addSubmitAndSend").on("click", function () {
                // 表单验证
                var messageCategory = $("#messageCategory").val();
                /*if (!messageCategory) {
                    layer.msg("请选择消息类型", {icon : 2});
                    return;
                }*/
                var companySet = $("#companySet").val();
                /* if (!companySet) {
                     layer.msg("请选择消息类别", {icon : 2});
                     return;
                 }*/
                var title = $("#title").val();
                if (!title) {
                    layer.msg("标题不能为空", {icon: 2});
                    return;
                }
                var sortTitle = $("#sortTitle").val();
                /* if (!sortTitle) {
                     layer.msg("消息简介不能为空", {icon : 2});
                     return;
                 }*/
                var ueditor_content = UE.getEditor("ueditor_content").getContent();
                if (!ueditor_content) {
                    layer.msg("消息正文不能为空", {icon: 2});
                    return;
                }

                var names = $("#userids").val();
                if (names != "" && names != undefined) {
                    names = names.trim();
                    debugger
                    var list = names.split(',');//存放用户名
                    // 校验是否通过
                    window.flag = true;
                    $.ajax({
                        type: 'post',
                        url: _ctx + "/customer/apppersoninfo/finduserId",
                        async: false,
                        dataType: 'json',
                        data: {
                            start: 0,
                            length: 9999,
                            listIds: names
                        },
                        success: function (data) {
                            var arr = names.split(",");
                            window.errUserName = '';
                            if (data.success) {
                                // 获取list中的所有userName
                                for (var i = 0; i < data.obj.length; i++) {
                                    //list.push(data.obj[i]);
                                }
                            } else {
                                layer.open({
                                    title: '提示',
                                    content: "[" + names + "]:以上用户名错误，请检查",
                                    icon: 2
                                });
                                flag = false;
                            }
                        }
                    })

                    if (!flag) {// 如果校验失败，停止代码执行；
                        return false;
                    }
                }

                layer.confirm("确定发送该消息吗？", {
                    btn: ['确定', '取消']
                    // 按钮
                    // ids: ids
                }, function () {
                    layer.closeAll();

                    if (null == list || "" == list || undefined == list) {
                        $("#isAll").val(1);
                        $("#receiveUserIdList").val([0].join(","));
                    } else {
                        names = names.trim();
                        names.split(",");
                        $("#isAll").val(0);
                        $("#receiveUserIdList").val(list.join(","));
                    }

                    $("#categoryName").val($("#companySet").find("option:selected").text());
                    $("#categoryId").val($("#companySet").val());
                    var langCode = $("#langCode").val();

                    var options = {
                        url: _ctx + "/web/appmessage/add/1",
                        type: "post",
                        resetForm: true,// 提交后重置表单
                        dataType: 'json',
                        success: function (data, statusText) {
                            if (data != undefined) {
                                if (data.success) {
                                    layer.msg("发送成功", {icon: 1});
                                    loadUrl(_ctx + '/v.do?u=/admin/web/appmessagelist')
                                } else {
                                    layer.msg(data.msg, {icon: 2});
                                }
                            }
                        }

                    };
                    $("#form").ajaxSubmit(options);
                })
            });

            // 重置按钮
            $("#reset").on("click", function () {
                $("#companySet").val('')
                $("#title").val("");
                $("#sortTitle").val("");
                UE.getEditor("ueditor_content").setContent('');
            });

            // 添加提交
            $("#addSubmit").on("click", function () {
                // 表单验证
                var messageCategory = $("#messageCategory").val();

                var companySet = $("#companySet").val();

                var title = $("#title").val();
                if (!title) {
                    layer.msg("标题不能为空", {icon: 2});
                    return;
                }
                var sortTitle = $("#sortTitle").val();
                /* if (!sortTitle) {
                     layer.msg("消息简介不能为空", {icon : 2});
                     return;
                 }*/
                var ueditor_content = UE.getEditor("ueditor_content").getContent();
                if (!ueditor_content) {
                    layer.msg("消息正文不能为空", {icon: 2});
                    return;
                }

                // 是否全部发送 true 是 false 否
                var flagAll = true;
                var list = [];//存放用户名
                // 表单校验
                // 1、判断用户名是否输入正确
                window.id = $("#userids").val();
                var mes = "确定保存该消息?"
                if (id == "" || id == undefined) {
                    mes = "注意：接收人用户名为空，默认发送消息给所有人"
                   // mes = "确定保存到草稿箱吗"
                } else {
                    id = id.trim()
                    // 校验是否通过
                    window.flag = true;
                    $.ajax({
                        type: 'post',
                        url: _ctx + "/customer/apppersoninfo/finduserName",
                        async: false,
                        dataType: 'json',
                        data: {
                            start: 0,
                            length: 9999,
                            listIds: id
                        },
                        success: function (data) {
                            var arr = id.split(",");
                            window.errUserName = '';
                            if (data.success) {
                                // 获取list中的所有userName
                                flagAll = false;
                                for (var i = 0; i < data.obj.length; i++) {
                                    list.push(data.obj[i]);
                                }
                            } else {
                                layer.open({
                                    title: '提示',
                                    content: "[" + id + "]:以上用户名错误，请检查",
                                    icon: 2
                                });
                                flag = false;
                            }
                        }
                    });

                    if (!flag) {// 如果校验失败，停止代码执行；
                        return false;
                    }
                }

                layer.confirm(mes, {
                    btn: ['确定', '取消']
                    // 按钮
                }, function () {
                    layer.closeAll();

                    if (flagAll) {
                        $("#isAll").val(1);
                        $("#receiveUserIdList").val([0].join(","));
                    } else {
                        $("#isAll").val(0);
                        $("#receiveUserIdList").val(list.join(","));
                    }
                    $("#categoryName").val($("#companySet").find("option:selected").text());
                    $("#categoryId").val($("#companySet").val());
                    var langCode = $("#langCode").val();
                    var options = {
                        url: _ctx + "/web/appmessage/add/2",
                        type: "post",
                        resetForm: true,// 提交后重置表单
                        dataType: 'json',
                        success: function (data, statusText) {
                            if (data != undefined) {
                                if (data.success) {
                                    layer.msg("保存成功", {icon: 1});
                                    loadUrl(_ctx + '/web/appmessage/toDraftBox/'+langCode);
                                } else {
                                    layer.msg(data.msg, {icon: 2});
                                }
                            }
                        }

                    };
                    $("#form").ajaxSubmit(options);
                });
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
                    content: {
                        validators: {
                            notEmpty: {
                                message: "content不能为空"
                            }
                        }
                    },
                    categoryName: {
                        validators: {
                            notEmpty: {
                                message: "categoryName不能为空"
                            }
                        }
                    },
                    categoryId: {
                        validators: {
                            notEmpty: {
                                message: "categoryId不能为空"
                            }
                        }
                    },
                    title: {
                        validators: {
                            notEmpty: {
                                message: "title不能为空"
                            }
                        }
                    },
                    titleImage: {
                        validators: {
                            notEmpty: {
                                message: "titleImage不能为空"
                            }
                        }
                    },
                    isSend: {
                        validators: {
                            notEmpty: {
                                message: "isSend不能为空"
                            }
                        }
                    },
                    isAll: {
                        validators: {
                            notEmpty: {
                                message: "isAll不能为空"
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
                    sortTitle: {
                        validators: {
                            notEmpty: {
                                message: "sortTitle不能为空"
                            }
                        }
                    },
                    sendDate: {
                        validators: {
                            notEmpty: {
                                message: "sendDate不能为空"
                            }
                        }
                    },
                    sendUserName: {
                        validators: {
                            notEmpty: {
                                message: "sendUserName不能为空"
                            }
                        }
                    },
                    messageType: {
                        validators: {
                            notEmpty: {
                                message: "messageType不能为空"
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

            // 添加提交
            $("#addSubmit1").on("click", function () {
                // 表单验证
                // 表单验证
                var messageCategory = $("#messageCategory").val();

                var companySet = $("#companySet").val();

                var title = $("#title").val();
                if (!title) {
                    layer.msg("标题不能为空", {icon: 2});
                    return;
                }
                var sortTitle = $("#sortTitle").val();
                /* if (!sortTitle) {
                     layer.msg("消息简介不能为空", {icon : 2});
                     return;
                 }*/
                var ueditor_content = UE.getEditor("ueditor_content").getContent();
                if (!ueditor_content) {
                    layer.msg("消息正文不能为空", {icon: 2});
                    return;
                }

                // 是否全部发送 true 是 false 否
                var flagAll = true;
                var list = [];//存放用户名
                // 表单校验
                // 1、判断用户名是否输入正确
                window.id = $("#userids").val();
                var mes = "确定保存该消息?"
                if (id == "" || id == undefined) {
                    //mes = "注意：接收人用户名为空，默认发送消息给所有人"
                    mes = "确定发送该消息吗？"
                } else {
                    id = id.trim()
                    // 校验是否通过
                    window.flag = true;
                    $.ajax({
                        type: 'post',
                        url: _ctx + "/customer/apppersoninfo/finduserName",
                        async: false,
                        dataType: 'json',
                        data: {
                            start: 0,
                            length: 9999,
                            listIds: id
                        },
                        success: function (data) {
                            var arr = id.split(",");
                            window.errUserName = '';
                            if (data.success) {
                                // 获取list中的所有userName
                                flagAll = false;
                                for (var i = 0; i < data.obj.length; i++) {
                                    list.push(data.obj[i]);
                                }
                            } else {
                                layer.open({
                                    title: '提示',
                                    content: "[" + id + "]:以上用户名错误，请检查",
                                    icon: 2
                                });
                                flag = false;
                            }
                        }
                    });

                    if (!flag) {// 如果校验失败，停止代码执行；
                        return false;
                    }
                }

                layer.confirm(mes, {
                    btn: ['确定', '取消']
                    // 按钮
                }, function () {
                    layer.closeAll();

                    if (flagAll) {
                        $("#isAll").val(1);
                        $("#receiveUserIdList").val([0].join(","));
                    } else {
                        $("#isAll").val(0);
                        $("#receiveUserIdList").val(list.join(","));
                    }
                    $("#categoryName").val($("#companySet").find("option:selected").text());
                    $("#categoryId").val($("#companySet").val());
                    var langCode = $("#langCode").val();
                    var options = {
                        url: _ctx + "/web/appmessage/modify",
                        type: "post",
                        resetForm: true,// 提交后重置表单
                        dataType: 'json',
                        success: function (data, statusText) {
                            if (data != undefined) {
                                if (data.success) {
                                    layer.msg("保存成功", {icon: 1});
                                    loadUrl(_ctx + '/web/appmessage/toDraftBox/'+langCode);
                                } else {
                                    layer.msg(data.msg, {icon: 2});
                                }
                            }
                        }

                    };
                    $("#form").ajaxSubmit(options);
                });
            });


            // 修改提交
            $("#modifySubmit").on("click", function () {
                var options = {
                    url: _ctx + "/web/appmessage/modify.do",
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
                                loadUrl(_ctx + '/v.do?u=/admin/web/appmessagelist')
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
            //编辑
            $('#table_modify').on('click', function () {
                _table.seeRow($("#table"), _ctx + "/web/appmessage/modifysee2");
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
            var conf = {
                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/web/appmessage/listMessageVo.do?isAuto=0",
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },
                    {
                        title: '收件人数',
                        field: 'categoryName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            var list = row.list;
                            return list.length + '人';
                        }
                    },
                    {
                        title: '标题',
                        field: 'messageTitle',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '语种',
                        field: 'messageCategory',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function(value, row, index) {
                            return _dic.getKeyAndVal("sysLanguage", value);
                        }
                    },
                    {
                        title: '创建人',
                        field: 'operator',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                    ,
                    {
                        title: '创建时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '发送人',
                        field: 'sendUserName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '发送时间',
                        field: 'sendDate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '状态',
                        field: 'isSend',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == '0') {
                                return '未发送';
                            }
                            return '已发送';
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        sysList: function () {
            //编辑
            $('#table_modify').on('click', function () {
                _table.seeRow($("#table"), _ctx + "/web/appmessage/modifysee2");
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
            var conf = {
                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/web/appmessage/listMessageVo.do?isAuto=1",
                columns: [{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    value: "id",
                    searchable: false
                },
                    {
                        title: '标题',
                        field: 'messageTitle',
                        align: 'center',
                        visible: true,
                        visible: true,
                        sortable: false,
                        searchable: true,
                       /* formatter: function (value, row, index) {
                            var list = row.list;
                            return list.length + '人';
                        }*/
                    },
                    {
                        title: '信件语种',
                        field: 'messageCategory',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '状态',
                        field: 'list[0].state',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (row.list[0].state == 1) {
                                return '未阅';
                            }else if(row.list[0].state == 2){
                                return "已阅"
                            }else if(row.list[0].state == 3){
                                return "用户删除"
                            } else{
                                return "已阅"
                            }
                        }
                    }
                    ,
                    {
                        title: '收件人邮箱',
                        field: 'id',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            var row0 = row.list[0];
                            if(row0){
                                return row0.email;
                            }else {
                                return "";
                            }

                        }
                    },
                    {
                        title: '收件人手机',
                        field: 'list[0].mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if(row.list[0]){
                                return row.list[0].mobilePhone;
                            }else {
                                return "";
                            }

                        }
                    },
                    {
                        title: '收件人姓名',
                        field: 'list[0].trueName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if(row.list[0]){
                                return row.list[0].trueName;
                            }else{
                                return "";
                            }

                        }
                    },
                    {
                        title: '发送时间',
                        field: 'sendDate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,

                    },
                    {
                        title: '阅读时间',
                        field: 'list[0].readDate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if(row.list[0]){
                                return row.list[0].readDate;
                            }else{
                                return "-";
                            }
                        }
                    },
                    {
                        title: '删除时间',
                        field: 'list[0].deleteDate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if(row.list[0]){
                                return row.list[0].deleteDate;
                            }else{
                                return "-";
                            }
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        },
        example: function(){
            $('#selectedUser').on('click', function () {
               var tableHtml = "<table   id='table2'" +
                   "       data-show-refresh=\"false\"   " +
                   "       data-show-columns=\"false\"   " +
                   "       data-show-export=\"false\"    " +
                   "       data-search=\"false\"  " +
                   "       data-detail-view=\"false\"  " +
                   "       data-minimum-count-columns=\"2\"  " +
                   "       data-pagination=\"true\"  " +
                   "       data-id-field=\"id\"  " +
                   "       data-page-list=\"[10, 25, 50, 100]\"  " +
                   "       data-show-footer=\"false\"    " +
                   "       data-side-pagination=\"server\"  " +
                   "       >  " +
                   "</table>";

               layer.open({
                   type: 1,
                   title: "<strong>管理接收人<strong>",
                   skin: 'layui-layer-rim', //加上边框
                   area: ['70%', '70%'], //宽高,
                   content: $("#delUserDiv"),
                   closeBtn: 2,
                   shadeClose: false,
                   shade: 0,
                   success: function (layero, index) {
                       //弹出成功移除隐藏
                       $("#delUserDiv").removeClass("hide")
                       $("#delUserTableDiv").empty().html(tableHtml);
                       delUser($("#table2"));

                       $("#table2").on('click-row.bs.table', function (e, row, $element) {
                           $element.children().eq(0).children().eq(0).click();
                       });
                   },
                   cancel: function () {
                       //关闭回调设置隐藏
                       $("#delUserDiv").addClass("hide")
                   }
               });
            })

            //删除接收人
            $('#delUserDiv').on('click', '#delUser', function () {

                var ids = _table.getIdSelects($("#table2"));  //将要删除的
                if (ids != undefined && ids.length >= 1) {
                    layer.confirm("确定删除吗？", {
                        btn: ['确定', '取消']
                        // 按钮
                        // ids: ids
                    }, function () {
                        var idsed = $('#userids').val().split(',');   //已经添加的
                        for (var i = 0; i < ids.length; i++) {
                            idsed.remove(ids[i])
                        }
                        $('#userids').val(idsed);
                        var manNum = $('#userids').val().split(',').length;
                        if($('#userids').val()==""){
                            manNum=0;
                        }
                        $('#selectedUser').html('已选择(' + manNum + '人)')
                        layer.closeAll();
                        layer.msg('删除成功', {icon: 1});
                    })


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            })
        }
    }

    // 根据语种初始化消息类别
    function initMessageCategory(langName) {
        $.ajax({
            type: "POST",
            dataType: "JSON",
            url: _ctx + '/web/appmessage/ajaxSelectType/' + langName,
            cache: false,
            traditional: true,
            success: function (data) {
                $("#companySet").empty();
                $("#companySet").append("<option value=\"\">请选择</option>");
                for (var i = 0; i < data.length; i++) {
                    $("#companySet").append("<option value=\"" + data[i].id + "\">" + data[i].name + "</option>");
                }
            }
        });
    }

    //删除站内信接受人
    function delUser(table1) {
        var ids = $('#userids').val();
        if (ids == '') {
            ids = 1111111111111;// id in 11111111应该查不到，所以不显示
        }
        var conf2 = {
            url: _ctx + "/customer/appcustomer/listByIds?ids=" + ids,
            columns: [{
                field: 'state',
                checkbox: true,
                align: 'center',
                valign: 'middle',
                value: "id",
                searchable: false
            }, {
                title: 'ID',
                field: 'id',
                align: 'center',
                visible: false,
                searchable: false
            }, {
                title: '邮箱',
                field: 'appPersonInfo.email',
                align: 'center',
                visible: true,
                sortable: false,
                searchable: true,
                formatter: function (value, row, index) {
                    return row.appPersonInfo.email;
                }
            },
                {
                    title: '手机号',
                    field: 'appPersonInfo.mobilePhone',
                    align: 'center',
                    visible: true,
                    sortable: false,
                    searchable: true,
                    formatter: function (value, row, index) {
                        return row.appPersonInfo.mobilePhone;
                    }
                },
                {
                    title: '姓名',
                    field: 'receCode',
                    align: 'center',
                    visible: true,
                    sortable: false,
                    searchable: true,
                    formatter: function (value, row, index) {
                        if(!row.appPersonInfo){
                            return "";
                        }
                        return row.appPersonInfo.surname + row.appPersonInfo.trueName;
                    }
                },
                {
                    title: '常用语种',
                    field: 'commonLanguage',
                    align: 'center',
                    visible: true,
                    sortable: false,
                    searchable: true,
                    /*formatter: function (value, row, index) {
                        return row.appPersonInfo.surname + row.appPersonInfo.trueName;
                    }*/
                }
                ]
        }
        _table.initTable(table1, conf2);
    }
});
