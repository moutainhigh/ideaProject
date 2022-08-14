define(function(require, exports, module) {
	this._table = require("js/base/table");
    this.md5 = require("js/base/utils/hrymd5");

	module.exports = {
		//初始化方法
        auditpassword : function() {
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

                    auditpassword : {
						validators : {
							notEmpty : {
								message : "审核密码不能为空"
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
					url : _ctx + "/sys/config/auditpasswordsubmit",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						debugger
						var pwd = formData[0].value
                        formData[0].value=md5.md5(pwd)
						
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
								loadUrl(_ctx+'/sys/config/auditpasswordpage')
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