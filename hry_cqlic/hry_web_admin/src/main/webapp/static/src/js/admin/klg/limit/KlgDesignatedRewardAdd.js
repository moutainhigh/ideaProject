define(function (require, exports, module) {
    this._table = require("js/base/table");
    this._dic = require("js/base/HrySelect");

    module.exports = {
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
                    title: "<strong>添加指定人</strong>",
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

            //添加指定人
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
            // 提交并发送
            $("#addSubmitAndSend").on("click", function () {
                // 表单验证
                var gradation = $("#gradation").val();
                var pointAlgebra = $("#pointAlgebra").val();
                var rewardNum = $("#rewardNum").val();
                if(rewardNum!=undefined&&rewardNum!=''){
                    var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
                    if(!reg.test(rewardNum)){
                        layer.msg('奖金额度不正确', {icon: 2});
                        return false;
                    }
                }
                var names = $("#userids").val();
                if (names != "" && names != undefined) {
                    names = names.trim();
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
                              /*  for (var i = 0; i < data.obj.length; i++) {
                                    //list.push(data.obj[i]);
                                }*/
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
                var lock=false;//默认未锁定
                layer.confirm("确定保存吗？", {
                    btn: ['确定', '取消']
                }, function () {
                    layer.closeAll();
                    if (null == list || "" == list || undefined == list) {
                    } else {
                        names = names.trim();
                        names.split(",");
                    }
                    if(!lock){
                        lock=true;//锁定
                        $.ajax({
                            type: 'post',
                            url: _ctx + "/klg/limit/klgcustomerlevel/updateReward",
                            async: false,
                            dataType: 'json',
                            data: {
                                listIds: names,
                                gradation:gradation,
                                rewardNum:rewardNum,
                                pointAlgebra:pointAlgebra
                            },
                            success: function (data) {
                                if (data != undefined) {
                                    if (data.success) {
                                        $('#userids').val("");
                                        $('#selectedUser').html('已选择(0人)')
                                        layer.msg("成功", {icon: 1});
                                    } else {
                                        layer.msg(data.msg, {icon: 2});
                                    }
                                }
                                lock=false;//解除锁定
                            }
                        })
                    }
                })
            });
            // 重置按钮
            $("#reset").on("click", function () {
                $("#gradation").val('')
                $("#pointAlgebra").val("");
                $("#rewardNum").val("");
            });
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
                   title: "<strong>已选指定人<strong>",
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
                }
                ]
        }
        _table.initTable(table1, conf2);
    }
});
