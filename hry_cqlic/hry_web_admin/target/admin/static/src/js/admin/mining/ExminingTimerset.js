define(function(require, exports, module) {

	module.exports = {
		//定时器方法
		init : function(){
            // 表单验证
            $('#processForm').bootstrapValidator({
                submitButtons : "button[id=processSubmit]",
                message : '不能为空',
                feedbackIcons : {
                    valid : 'glyphicon glyphicon-ok',
                    invalid : 'glyphicon glyphicon-remove',
                    validating : 'glyphicon glyphicon-refresh'
                },
                fields : {
                    platformAvgTimer : {
                        validators : {
                            notEmpty : {
                                message : "平台币奖励计算不能为空"
                            }
                        }
                    },
                    platformReturnTimer : {
                        validators : {
                            notEmpty : {
                                message : "平台币发放时间不能为空"
                            }
                        }
                    },
                    platformStartTimer : {
                        validators : {
                            notEmpty : {
                                message : "发币开始时间不能为空"
                            }
                        }
                    }
                },
                // bv校验通过则提交
                submitHandler : function(validator, form, submitButton) {
                }
            });
            // 挖矿提交
            $("#processSubmit").on("click", function() {
                var options = {
                    url : _ctx + "/mining/exmining/setTimer",
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
                                loadUrl(_ctx+'/mining/exmining/toTimer')
                            } else {
                                layer.msg(data.msg, {icon : 2});
                            }
                        }
                    }

                };
                $("#processForm").ajaxSubmit(options);
            });

            // 分红表单验证
            $('#processBonusForm').bootstrapValidator({
                submitButtons : "button[id=processBonusSubmit]",
                message : '不能为空',
                feedbackIcons : {
                    valid : 'glyphicon glyphicon-ok',
                    invalid : 'glyphicon glyphicon-remove',
                    validating : 'glyphicon glyphicon-refresh'
                },
                fields : {
                    bonusRecordTimer : {
                        validators : {
                            notEmpty : {
                                message : "持有者记录不能为空"
                            }
                        }
                    },
                    bonusReturnTimer : {
                        validators : {
                            notEmpty : {
                                message : "分红时间不能为空"
                            }
                        }
                    },
                    bonusReturnStart : {
                        validators : {
                            notEmpty : {
                                message : "分红开始时间不能为空"
                            }
                        }
                    }
                },
                // bv校验通过则提交
                submitHandler : function(validator, form, submitButton) {
                }
            });
            // 分红提交
            $("#processBonusSubmit").on("click", function() {
                var options = {
                    url : _ctx + "/mining/exmining/setTimer",
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
                                loadUrl(_ctx+'/mining/exmining/toTimer')
                            } else {
                                layer.msg(data.msg, {icon : 2});
                            }
                        }
                    }

                };
                $("#processBonusForm").ajaxSubmit(options);
            });

            // 锁仓表单验证
            $('#processLockForm').bootstrapValidator({
                submitButtons : "button[id=processLockSubmit]",
                message : '不能为空',
                feedbackIcons : {
                    valid : 'glyphicon glyphicon-ok',
                    invalid : 'glyphicon glyphicon-remove',
                    validating : 'glyphicon glyphicon-refresh'
                },
                fields : {
                    lockfrequency : {
                        validators : {
                            notEmpty : {
                                message : "释放频率不能为空"
                            }
                        }
                    }
                },
                // bv校验通过则提交
                submitHandler : function(validator, form, submitButton) {
                }
            });
            // 锁仓提交
            $("#processLockSubmit").on("click", function() {
                // 验证锁仓释放频率
                var freq_test = /^[1-9]\\d*$/;
                var freq_val = $("#lockfrequency").val();
                if (freq_test.test(freq_val)) {
                    layer.msg('请输入正确的天数!', {icon : 2});
                    return;
                }

                var options = {
                    url : _ctx + "/mining/exmining/setTimer",
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
                                loadUrl(_ctx+'/mining/exmining/toTimer')
                            } else {
                                layer.msg(data.msg, {icon : 2});
                            }
                        }
                    }

                };
                $("#processLockForm").ajaxSubmit(options);
            });


		}


	}

});