define(function (require, exports, module) {
    this._table = require("js/base/table");
    this.sele = require("js/base/HrySelect");

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
                    name: {
                        validators: {
                            notEmpty: {
                                message: "Banner名称不能为空"
                            }
                        }
                    },
                    picturePath: {
                        validators: {
                            notEmpty: {
                                message: "图片不能为空"
                            }
                        }
                    },
                    isShow: {
                        validators: {
                            notEmpty: {
                                message: "是否显示不能为空"
                            }
                        }
                    },
                    sort: {
                        validators: {
                            notEmpty: {
                                message: "sort不能为空"
                            }
                        }
                    },
                    applicationType: {
                        validators: {
                            notEmpty: {
                                message: "应用类型不能为空"
                            }
                        }
                    },
                    langCode: {
                        validators: {
                            notEmpty: {
                                message: "请选择系统语种"
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
                    url: _ctx + "/web/appbanner/add",
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
                                var toFlag = $("#whereUsehide").val();
                                loadUrl(_ctx + '/web/appbanner/toList/'+toFlag);
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    }

                };
                $("#form").ajaxSubmit(options);
            });
            $("#applicationType").on("change", function () {
                if($("#applicationType").val()==="0"){
                    $("#submitpicture").text("上传图片(电脑端尺寸1920px*785px)");
                }else{
                    $("#submitpicture").text("上传图片(移动端尺寸400px*190px)");
                }
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
                    name: {
                        validators: {
                            notEmpty: {
                                message: "BannerName不能为空"
                            }
                        }
                    },
                    picturePath: {
                        validators: {
                            notEmpty: {
                                message: "图片不能为空"
                            }
                        }
                    },
                    isShow: {
                        validators: {
                            notEmpty: {
                                message: "是否显示不能为空"
                            }
                        }
                    },
                    sort: {
                        validators: {
                            notEmpty: {
                                message: "sort不能为空"
                            }
                        }
                    },
                    applicationType: {
                        validators: {
                            notEmpty: {
                                message: "应用类型不能为空"
                            }
                        }
                    },
                    langCode: {
                        validators: {
                            notEmpty: {
                                message: "请选择系统语种"
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
                    url: _ctx + "/web/appbanner/modify",
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
                                var toFlag = $("#whereUsehide").val();
                                loadUrl(_ctx + '/web/appbanner/toList/'+toFlag);
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    }
                };
                $("#form").ajaxSubmit(options);
            });
            $("#applicationType").on("change", function () {
               if($("#applicationType").val()==="0"){
                   $("#submitpicture").text("上传图片(电脑端尺寸1920px*785px)");
               }else{
                   $("#submitpicture").text("上传图片(移动端尺寸400px*190px)");
               }
            });
        },
        //列表页面--初始化方法
        list: function () {

            // 修改页面跳转按钮
            $("#modify").on("click", function () {
                var toFlag = $("#whereUsehide").val();
                _table.seeRow($("#table"), _ctx + "/web/appbanner/modifysee/"+toFlag);
            });
            // 删除按钮点击事件
            $("#remove").on("click", function () {
                _table.removeRow($("#table"), _ctx + "/web/appbanner/remove.do");
            });
            //语言切换
            $(".languageQuery").on("click", function () {
                var languageDic = $(this).attr("languageType");
                var params = {langCode: languageDic};
                $("#languageDic").val(languageDic);
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
                url: _ctx + "/web/appbanner/list",
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
                        title: '图片名称',
                        field: 'name',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '图片路径',
                        field: 'picturePath',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '外链',
                        field: 'remark2',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '是否显示',
                        field: 'isShow',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if(value==1){
                                return "显示";
                            }else {
                                return "不显示";
                            }
                        }
                    },
                    {
                        title: '排序',
                        field: 'sort',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true
                    },
                    {
                        title: '应用类型',
                        field: 'applicationType',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {
                            if(value==0){
                                return "电脑版";
                            }else {
                                return "手机版";
                            }
                        }
                    },
                    {
                        title: '配图类型',
                        field: 'whereUse',
                        align: 'center',
                        visible: true,
                        sortable: false,
                        searchable: true,
                        formatter: function (value, row, index) {

                            var aa = sele.getKeyAndVal("whereuse",value)
                            return  aa;

                        }
                    }
                ]
            }
            _table.initTable($("#table"), conf);
        }
    }

});