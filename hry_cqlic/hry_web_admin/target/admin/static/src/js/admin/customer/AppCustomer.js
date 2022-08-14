define(function (require, exports, module) {
    this._table = require("js/base/table");
    this.validate = require("js/base/validate");
    this.md5 = require("js/base/utils/hrymd5");

    module.exports = {
        //查看页面--初始化方法
        see: function () {
        },

        realname: function () {

            $("#auditback").on("click", function () {
                var ids = $("#customerid").val();
                layer.confirm('你确定要提交审核吗？', {
                    btn: ['确定', '取消'],
                    ids: ids[0]
                }, function () {
                    $.ajax({
                        type: "get",
                        url: _ctx + "/customer/appcustomer/auditback/" + ids,
                        data: {},
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            if (data) {
                                if (data.success) {
                                    layer.msg("审核成功", {
                                        icon: 1,
                                    })
                                } else {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    })
                                }

                                loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
                            }


                        },
                        error: function (e) {

                        }
                    });

                })
            })


            $("#audit").on("click", function () {

                var ids = $("#customerid").val();
                layer.confirm('你确定要提交审核吗？', {
                    btn: ['确定', '取消'],
                    ids: ids[0]
                }, function () {
                    $.ajax({
                        type: "get",
                        url: _ctx + "/customer/appcustomer/audit/" + ids,
                        data: {},
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            if (data) {
                                if (data.success) {
                                    layer.msg("审核成功", {
                                        icon: 1,
                                    })
                                } else {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    })
                                }

                                loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
                            }
                        },
                        error: function (e) {

                        }
                    });

                })

            });
        },

        //列表页面--初始化方法
        list: function () {

            $("#moreAudit").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                var idstr = ids.toString();
                debugger
                if (ids != undefined) {

                    layer.confirm('你确定要批量通过吗？', {
                        btn: ['确定', '取消'],
                        ids: ids
                    }, function () {
                        $.ajax({
                            type: "post",
                            url: _ctx + "/customer/appcustomer/moreAudit",
                            data: {
                                ids: idstr
                            },
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("审核成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
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

            // 添加页面跳转按钮
            $("#see").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/customer/appcustomer/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/customer/appcustomer/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/customer/appcustomer/remove.do");
            });


            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/customer/appcustomer/list.do?unstates=2",
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
                        title: '邮箱',
                        field: 'appPersonInfo.email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '手机号',
                        field: 'appPersonInfo.mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    /*{
                        title: '姓氏',
                        field: 'appPersonInfo.surname',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '名字',
                        field: 'appPersonInfo.trueName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '证件类型',
                        field: 'appPersonInfo.cardType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "身份证"
                            }
                            return "其他";
                        }
                    },
                    {
                        title: '证件号',
                        field: 'appPersonInfo.cardId',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },*/
                    {
                        title: '谷歌认证',
                        field: 'googleState',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "未认证";
                            } else if (value == "1") {
                                return "已认证";
                            }
                        }
                    },
                    {
                        title: '手机认证',
                        field: 'phoneState',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "未认证";
                            } else if (value == "1") {
                                return "已认证";
                            }
                        }
                    },
                    /*
                    {
                        title: '是否实名',
                        field: 'isReal',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data != null && data == 1) {
                                return "已实名"
                            }
                            return "未实名"
                        }
                    }*/
                    /*{
                        title: '实名状态',
                        field: 'states',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data != null && data == "2") {
                                return "已实名"
                            } else if (data == "1") {
                                return "待审核"
                            } else if (data == "3") {
                                return "已拒绝"
                            }
                            return "未实名"
                        }
                    },*/
                    {
                        title: '是否禁用',
                        field: 'isDelete',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "未禁用";
                            } else if (value == "1") {
                                return "已禁用";
                            }
                        }
                    },
                    {
                        title: '是否开启交易',
                        field: 'isChange',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "已开启";
                            } else if (value == "1") {
                                return "未开启";
                            }
                        }
                    },
                    {
                        title: '动态收益',
                        field: 'isGag',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "已开启";
                            } else if (value == "1") {
                                return "未开启";
                            }
                        }
                    },
                    {
                        title: '父级用户',
                        field: 'pEmail',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '注册时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                    /*{
                        title: '是否激活',
                        field: 'hasEmail',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "未激活";
                            } else if (value == "1") {
                                return "已激活";
                            }
                        }
                    }*/
                    /*,
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '申请key',
                        field: 'id',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }*/
                ]
            }
            _table.initTable($("#table"), conf);


            //激活邮箱
            $("#activeEmail").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {
                    var rows = _table.getRowSelects($("#table"));

                    if (rows[0].hasEmail == "0") {
                        layer.confirm('你确定要激活吗？', {
                            btn: ['确定', '取消'],
                            ids: ids[0]
                        }, function () {
                            $.ajax({
                                type: "get",
                                url: _ctx + "/customer/appcustomer/isHasemail/" + ids,
                                data: {},
                                cache: false,
                                dataType: "json",
                                success: function (data) {
                                    if (data) {
                                        if (data.success) {
                                            layer.msg("激活成功", {
                                                icon: 1,
                                            })
                                        } else {
                                            layer.msg(data.msg, {
                                                icon: 2,
                                            })
                                        }

                                        loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
                                    }
                                },
                                error: function (e) {

                                }
                            });

                        })
                    } else {
                        layer.msg('已激活，无需再次激活', {icon: 2});
                    }


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });


            //重置安全测略
            $("#resetSecurity").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要重置安全策略吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/resetSecurity/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    debugger
                                    if (data.success) {
                                        layer.msg("重置成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
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

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });


            //
            $("#audit").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    _table.seeRow($("#table"), _ctx + "/customer/appcustomer/auditsee");

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

            $("#resetPassword").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    $("#customerId").val(ids);
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        area: ['380px', '180px'],
                        content: $("#div_resetloginpassword")
                    });
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

            $("#modifyPassWord").on("click", function () {
                var customerId = $("#customerId").val();
                var password = $("#password").val();
                if (!validate.check_password(password)) {
                    layer.msg("密码为8-12位且包含大写字母、小写字母以及数字", {icon: 2});
                    return false;
                }
                //md5
                password = md5.md5(password);
                //rsa
                var encrypt = new JSEncrypt();
                encrypt.setPublicKey($("#publicKey").val());
                password = encrypt.encrypt(password);
                $.ajax({
                    type: "post",
                    url: _ctx + "/customer/appcustomer/setpw.do",
                    data: {
                        id: customerId,
                        password: password,
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {

                        if (data) {
                            if (data.success) {
                                layer.closeAll();
                                layer.msg("修改成功", {icon: 1, time: 1500});
                                loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
                            } else {
                                layer.msg(data.msg, {icon: 2, time: 1500});
                                layer.closeAll();
                            }
                        } else {
                            layer.msg("修改登录密码错误", {icon: 2, time: 1500})
                            layer.closeAll();
                        }
                    },
                    error: function (e) {

                    }
                });
            })


            $("#auditall").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要清除实名信息吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/auditall/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("清除成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
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


            $("#forbidden").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要禁用吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/forbidden/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    })
                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
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


            $("#permissible").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要解除禁用吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/permissible/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    })
                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
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


            $("#fnOpenChange").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要开启交易吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/openChange/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("开启成功", {
                                            icon: 1,
                                        })
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 2,
                                        })
                                    }
                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
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


            $("#fnCloseChange").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要禁止交易吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/fnCloseChange/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("关闭交易成功", {
                                            icon: 1,
                                        })
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 1,
                                        })
                                    }
                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
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

            $("#openDynamic").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    layer.confirm('你确定要开启吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/openDynamic/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    })
                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
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

            $("#closeDynamic").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要关闭吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/closeDynamic/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    })
                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=0')
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
        },
        //已经实名的列表
        auditlist: function () {

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
                _table.seeRow($("#table"), _ctx + "/customer/appcustomer/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/customer/appcustomer/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/customer/appcustomer/remove.do");
            });
            layer.closeAll();


            //查看实名信息
            $("#table").on('click', '.seeCustomer', function () {
                //alert($(this).attr("customerId"))
                var customerId = $(this).attr("customerId");

                $.ajax({
                    type: "get",
                    url: _ctx + "/customer/appcustomer/auditedsee/"+customerId,
                    data: {
                        id: customerId,
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                debugger
                                var personBank = data.obj.appPersonInfo.personBank;
                                var personCard = data.obj.appPersonInfo.personCard;
                                var frontpersonCard = data.obj.appPersonInfo.frontpersonCard;
                                $("#personBank").attr("src",personBank)
                                $("#personCard").attr("src",personCard)
                                $("#frontpersonCard").attr("src",frontpersonCard)
                                layer.open({
                                    type: 1,
                                    skin: 'layui-layer-demo', //样式类名
                                    closeBtn: 1, //不显示关闭按钮
                                    anim: 2,
                                    shadeClose: true, //开启遮罩关闭
                                    area: ['980px', '580px'],
                                    content: $("#seeInfo")
                                });

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

            //申请api
            $("#table").on('click', '.applyApi', function () {
                //alert($(this).attr("customerId"))
                var customerId = $(this).attr("customerId");
                $("#apiCustomerId").val(customerId)
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    shadeClose: true, //开启遮罩关闭
                    area: ['380px', '180px'],
                    content: $("#getApiDiv")
                });
            })
            //确定申请
            $("#confirmset").on("click", function () {
                var customerId = $("#apiCustomerId").val();
                var ip = $("#ip").val();
                if (ip == "") {
                    layer.msg("ip不能为空", {
                        icon: 1,
                    })
                    return false;
                }

                $.ajax({
                    type: "get",
                    url: _ctx + "/customer/appcustomer/applyApi/",
                    data: {
                        id: customerId,
                        ip: ip
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.open({
                                    type: 1,
                                    skin: 'layui-layer-demo', //样式类名
                                    closeBtn: 1, //不显示关闭按钮
                                    anim: 2,
                                    shadeClose: true, //开启遮罩关闭
                                    area: ['380px', '180px'],
                                    content: "您的apikey为:<br>" + data.msg
                                });

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



            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/customer/appcustomer/list?states=2",
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
                        title: '邮箱',
                        field: 'appPersonInfo.email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '手机号',
                        field: 'appPersonInfo.mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '姓氏',
                        field: 'appPersonInfo.surname',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '名字',
                        field: 'appPersonInfo.trueName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '证件类型',
                        field: 'appPersonInfo.cardType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "身份证";
                            }
                            return "其他";
                        }
                    },
                    {
                        title: '证件号',
                        field: 'appPersonInfo.cardId',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '注册时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '谷歌认证',
                        field: 'googleState',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "未开启";
                            } else if (value == "1") {
                                return "已开启";
                            }
                        }
                    },
                    {
                        title: '手机认证',
                        field: 'phoneState',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "未开启";
                            } else if (value == "1") {
                                return "已开启";
                            }
                        }
                    }/*,
                    {
                        title: '是否实名',
                        field: 'isReal',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data != null && data == 1) {
                                return "已实名"
                            }
                            return "未实名"
                        }
                    }*/,
                    {
                        title: '实名状态',
                        field: 'states',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data != null && data == "2") {
                                return "已实名"
                            } else if (data == "1") {
                                return "待审核"
                            } else if (data == "3") {
                                return "已拒绝"
                            }
                            return "未实名"
                        }
                    },
                    {
                        title: '是否禁用',
                        field: 'isDelete',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "未禁用";
                            } else if (value == "1") {
                                return "已禁用";
                            }
                        }
                    },
                    {
                        title: '是否开启交易',
                        field: 'isChange',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "已开启";
                            } else if (value == "1") {
                                return "未开启";
                            }
                        }
                    },
                    {
                        title: '是否激活',
                        field: 'hasEmail',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "未激活";
                            } else if (value == "1") {
                                return "已激活";
                            }
                        }
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            return "<button class='seeCustomer'  personCard='"+row.appPersonInfo.personCard+"' frontpersonCard='"+row.appPersonInfo.frontpersonCard+"' personBank='"+row.appPersonInfo.personBank+"' appPersonInfo='"+row.appPersonInfo+"' customerId='" + value + "'>查看</button>";
                        }
                    },
                    {
                        title: '申请key',
                        field: 'id',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {

                            return "<button class='applyApi' customerId='" + value + "'>申请</button>";
                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);


            //重置安全测略
            $("#resetSecurity").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要重置安全策略吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/resetSecurity/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    debugger
                                    if (data.success) {
                                        layer.msg("重置成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=1')
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

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });


            //
            $("#audit").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要提交审核吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/audit/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("审核成功", {
                                            icon: 1,
                                        })
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 1,
                                        })
                                    }

                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=1')
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

            $("#resetPassword").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    $("#customerId").val(ids);
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        area: ['380px', '180px'],
                        content: $("#div_resetloginpassword")
                    });
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });


            $("#modifyPassWord").on("click", function () {
                var customerId = $("#customerId").val();
                var password = $("#password").val();

                if (!validate.check_password(password)) {
                    layer.msg("密码为8-12位且包含大写字母、小写字母以及数字", {icon: 2});
                    return false;
                }
                //md5
                password = md5.md5(password);
                //rsa
                var encrypt = new JSEncrypt();
                encrypt.setPublicKey($("#publicKey").val());
                password = encrypt.encrypt(password);
                $.ajax({
                    type: "post",
                    url: _ctx + "/customer/appcustomer/setpw.do",
                    data: {
                        id: customerId,
                        password: password,
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {

                        if (data) {
                            if (data.success) {
                                layer.closeAll();
                                layer.msg("修改成功", {icon: 1});
                                loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=1')
                            } else {
                                layer.msg(data.msg, {icon: 2, time: 1500});
                                layer.closeAll();
                            }
                        } else {
                            layer.msg("修改登录密码错误", {icon: 2, time: 1500})
                            layer.closeAll();
                        }
                    },
                    error: function (e) {

                    }
                });
            })


            $("#auditall").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要清除实名信息吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/auditall/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("清除成功", {
                                            icon: 1,
                                        })
                                        loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=1')
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


            $("#forbidden").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要禁用吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/forbidden/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    })
                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=1')
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


            $("#permissible").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要解除禁用吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/permissible/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                    })
                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=1')
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


            $("#fnOpenChange").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要开启交易吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/openChange/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("开启成功", {
                                            icon: 1,
                                        })
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 1,
                                        })
                                    }
                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=1')
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


            $("#fnCloseChange").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {

                    layer.confirm('你确定要禁止交易吗？', {
                        btn: ['确定', '取消'],
                        ids: ids[0]
                    }, function () {
                        $.ajax({
                            type: "get",
                            url: _ctx + "/customer/appcustomer/fnCloseChange/" + ids,
                            data: {},
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                if (data) {
                                    if (data.success) {
                                        layer.msg("关闭成功", {
                                            icon: 1,
                                        })
                                    } else {
                                        layer.msg(data.msg, {
                                            icon: 1,
                                        })
                                    }
                                    loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=1')
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


        },
        chatcustomerlist: function () {

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
                _table.seeRow($("#table"), _ctx + "/customer/appcustomer/see");
            });
            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                _table.seeRow($("#table"), _ctx + "/customer/appcustomer/modifysee");
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/customer/appcustomer/remove.do");
            });

            var conf = {

                detail: function (e, index, row, $detail) {
                    var html = [];
                    $.each(row, function (key, value) {
                        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                    });
                    $detail.html(html.join(''));
                },
                url: _ctx + "/customer/appcustomer/list?states=2",
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
                        title: '邮箱',
                        field: 'appPersonInfo.email',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '手机号',
                        field: 'appPersonInfo.mobilePhone',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '姓氏',
                        field: 'appPersonInfo.surname',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '名字',
                        field: 'appPersonInfo.trueName',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '证件类型',
                        field: 'appPersonInfo.cardType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "身份证";
                            }
                            return "其他";
                        }
                    },
                    {
                        title: '证件号',
                        field: 'appPersonInfo.cardId',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '注册时间',
                        field: 'created',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '是否实名',
                        field: 'states',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (data, row, index) {
                            if (data != null && data == "2") {
                                return "已实名"
                            } else if (data == "1") {
                                return "待审核"
                            } else if (data == "3") {
                                return "已拒绝"
                            }
                            return "未实名"
                        }
                    },
                    {
                        title: '是否禁言',
                        field: 'isGag',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "否";
                            } else if (value == "1") {
                                return "是";
                            }
                        }
                    },
                    {
                        title: '是否激活',
                        field: 'hasEmail',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "否";
                            } else if (value == "1") {
                                return "是";
                            }
                        }
                    },

                    {
                        title: '是否管理员',
                        field: 'isAdmin',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if (value == "0") {
                                return "否";
                            } else if (value == "1") {
                                return "是";
                            }
                        }
                    },
                    {
                        title: '禁言时间',
                        field: 'gagDate',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '禁言时长',
                        field: 'gagTime',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    }
                ]
            }
            _table.initTable($("#table"), conf);


            $("#setGag").on("click", function () {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    var rowData = _table.getRowSelects($("#table"))[0];
                    var isGag = rowData.isGag;
                    if(isGag == "1"){
                        layer.msg("该用户已被禁言",
                            {icon: 2, time: 1500});
                        return;
                    }

                    $("#customerId").val(ids);
                    $("#gagDiv").show();
                    // layer.open({
                    //     type: 1,
                    //     skin: 'layui-layer-demo', //样式类名
                    //     closeBtn: 1, //不显示关闭按钮
                    //     anim: 2,
                    //     shadeClose: true, //开启遮罩关闭
                    //     area: ['380px', '180px'],
                    //     content: $("#gagDiv")
                    // });
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });


            $("#fnGag").on("click", function () {
                var customerId = $("#customerId").val();
                var gagTime = $("#gagTime").val();
                var type = 1;
                if (gagTime == '') {
                    layer.msg('时长不能为空', {icon: 2});
                    return false;
                }
                var reg = /^[1-9]\d*$/;
                if (!reg.test(gagTime)) {
                    layer.msg('只能为数字', {icon: 2});
                    return false;
                }
                $.ajax({
                    type: "POST",
                    dataType: "JSON",
                    url: _ctx + '/web/chatrecord/isGag',
                    data: {gagTime: gagTime, id: customerId, type: type},
                    cache: false,
                    success: function (data) {
                        if (data.success) {
                            if (data.success) {
                                layer.msg(data.msg, {icon: 1, time: 1500});
                                //layer.closeAll();
                                loadUrl(_ctx + '/v.do?u=/admin/web/chatcustomerlist')
                            } else {
                                layer.msg(data.msg, {icon: 2, time: 1500});
                                //layer.closeAll();
                            }
                        } else {
                            layer.msg(data.msg, {icon: 2, time: 1500});
                        }
                    }
                });
                $('#gagDiv').hide();
            });

            $('#popClose').on("click",function(){
                $('#gagDiv').hide();
            })
            $(document).mouseup(function(e) {
                var  pop = $('#popBox');
                if(!pop.is(e.target) && pop.has(e.target).length === 0) {
                    $('#gagDiv').hide();
                }
            });


            $("#releaseGag").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {
                    var rowData = _table.getRowSelects($("#table"))[0];
                    var isGag = rowData.isGag;
                    if(isGag == "0"){
                        layer.msg("该用户未被禁言",
                            {icon: 2, time: 1500});
                        return;
                    }

                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url: _ctx + '/web/chatrecord/isGag',
                        data: {id: ids[0], type: 0},
                        cache: false,
                        success: function (data) {
                            if (data.success) {
                                layer.msg("解除禁言成功",
                                    {icon: 1, time: 1500});
                                loadUrl(_ctx + '/v.do?u=/admin/web/chatcustomerlist')
                            } else {
                                layer.msg(data.msg, {icon: 2, time: 1500});
                            }
                        }
                    })


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });


            $("#setAdmin").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {
                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url: _ctx + '/web/chatrecord/isAdmin',
                        data: {id: ids[0], type: 1},
                        success: function (data) {
                            if (data.success) {
                                layer.msg(data.msg, {icon: 1, time: 1500}, function () {
                                    loadUrl(_ctx + '/v.do?u=/admin/web/chatcustomerlist')
                                });
                            } else {
                                layer.msg(data.msg, {icon: 2, time: 1500});
                            }
                        }
                    })


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });
            $("#removeAdmin").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if (ids != undefined && ids.length == 1) {
                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url: _ctx + '/web/chatrecord/isAdmin',
                        data: {id: ids[0], type: 0},
                        success: function (data) {
                            if (data.success) {
                                layer.msg(data.msg, {icon: 1, time: 1500}, function () {
                                    loadUrl(_ctx + '/v.do?u=/admin/web/chatcustomerlist')
                                });
                            } else {
                                layer.msg(data.msg, {icon: 2, time: 1500});
                            }
                        }
                    })


                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });

        }
    }

});