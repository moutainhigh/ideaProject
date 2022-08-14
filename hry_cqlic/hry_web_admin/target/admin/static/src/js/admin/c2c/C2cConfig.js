define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//查看页面--初始化方法
		rule : function(){
            // 表单验证
            /*$('#form').bootstrapValidator({
                submitButtons : "button[id=addSubmit]",
                message : '不能为空',
                feedbackIcons : {
                    valid : 'glyphicon glyphicon-ok',
                    invalid : 'glyphicon glyphicon-remove',
                    validating : 'glyphicon glyphicon-refresh'
                },
                fields : {

                    c2crule : {
                        validators : {
                            notEmpty : {
                                message : "匹配规则不能为空"
                            }
                        }
                    }
                },
                // bv校验通过则提交
                submitHandler : function(validator, form, submitButton) {
                }
            });*/
			//选中对勾样式添加
			var c2cruleValue=$('#c2cruleValue').val();
			if(c2cruleValue==0){
				$('#iconRule1').show();
			}if(c2cruleValue==1){
                $('#iconRule2').show();
			}else if(c2cruleValue==2){
                $('#iconRule3').show();
			}
            $("label").on("click",function(){
            	$(".form-control-feedback").hide();
            	$(this).parent().siblings("i").show();
			})
            // 提交
            $("#addSubmit").on("click", function() {
                var options = {
                    url : _ctx + "/c2c/c2cconfig/rulesubmit",
                    type : "post",
                    resetForm : true,// 提交后重置表单
                    dataType : 'json',
                    
                    success : function(data, statusText) {
                        if (data != undefined) {
                            if (data.success) {
                                layer.msg('设置成功!', {icon : 1});
                                loadUrl(_ctx+'/c2c/c2cconfig/rulepage')
                            } else {
                                layer.msg(data.msg, {icon : 2});
                            }
                        }
                    }

                };
                $("#form").ajaxSubmit(options);
            });
		},
		//添加页面--初始化方法
        timeout : function() {
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

                    c2cTimeOut : {
						validators : {
							notEmpty : {
								message : "超时时间不能为空"
							},
							callback : {
                                message: "超时时间必须大于0",
                                callback: function(value, validator) {
                                	if(value<=0){
                                		return false;
									}
									return true;
                                }
                            }
						}
					}
				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}

			});
			// 提交
			$("#addSubmit").on("click", function() {
				var options = {
					url : _ctx + "/c2c/c2cconfig/timeoutsubmit",
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
								layer.msg('设置成功!', {icon : 1});
								loadUrl(_ctx+'/c2c/c2cconfig/timeoutpage')
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}

				};
				$("#form").ajaxSubmit(options);
			});
		},
	}

});