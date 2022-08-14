define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//查看页面--初始化方法
		see : function(){
		},
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
										appeal : {
						validators : {
							notEmpty : {
								message : "诉求不能为空"
							}
						}
					},
					content : {
						validators : {
							notEmpty : {
								message : "详细描述不能为空"
							}
						}
					},
					thingUrl : {
						validators : {
							notEmpty : {
								message : "附件url不能为空"
							}
						}
					},
					transactionNum : {
						validators : {
							notEmpty : {
								message : "交易订单号不能为空"
							}
						}
					},
					userId : {
						validators : {
							notEmpty : {
								message : "申述人ID不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					platFormContent : {
						validators : {
							notEmpty : {
								message : "平台意见不能为空"
							}
						}
					},
					releaseAdvertisementId : {
						validators : {
							notEmpty : {
								message : "广告Id不能为空"
							}
						}
					},
					appealSell : {
						validators : {
							notEmpty : {
								message : "卖方 - 诉求不能为空"
							}
						}
					},
					contentSell : {
						validators : {
							notEmpty : {
								message : "卖方 - 详细描述不能为空"
							}
						}
					},
					thingUrlSell : {
						validators : {
							notEmpty : {
								message : "卖方 - 附件url不能为空"
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
					url : _ctx + "/exchange/appappeal/add.do",
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
								layer.msg('添加成功!', {icon : 1});
								loadUrl(_ctx+'/v.do?u=/admin/exchange/appappeallist')
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
					appeal : {
						validators : {
							notEmpty : {
								message : "诉求不能为空"
							}
						}
					},
					content : {
						validators : {
							notEmpty : {
								message : "详细描述不能为空"
							}
						}
					},
					thingUrl : {
						validators : {
							notEmpty : {
								message : "附件url不能为空"
							}
						}
					},
					transactionNum : {
						validators : {
							notEmpty : {
								message : "交易订单号不能为空"
							}
						}
					},
					userId : {
						validators : {
							notEmpty : {
								message : "申述人ID不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					platFormContent : {
						validators : {
							notEmpty : {
								message : "平台意见不能为空"
							}
						}
					},
					releaseAdvertisementId : {
						validators : {
							notEmpty : {
								message : "广告Id不能为空"
							}
						}
					},
					appealSell : {
						validators : {
							notEmpty : {
								message : "卖方 - 诉求不能为空"
							}
						}
					},
					contentSell : {
						validators : {
							notEmpty : {
								message : "卖方 - 详细描述不能为空"
							}
						}
					},
					thingUrlSell : {
						validators : {
							notEmpty : {
								message : "卖方 - 附件url不能为空"
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
					url : _ctx + "/exchange/appappeal/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/appappeallist')
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

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/appappeal/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/appappeal/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/exchange/appappeal/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/exchange/appappeal/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : 'id',
					field : 'id',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '诉求',
					field : 'appeal',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '详细描述',
					field : 'content',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '附件url',
					field : 'thingUrl',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易订单号',
					field : 'transactionNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '申述人ID',
					field : 'userId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'saasId',
					field : 'saasId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '平台意见',
					field : 'platFormContent',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '广告Id',
					field : 'releaseAdvertisementId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '卖方 - 诉求',
					field : 'appealSell',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '卖方 - 详细描述',
					field : 'contentSell',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '卖方 - 附件url',
					field : 'thingUrlSell',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});