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
										customerId : {
						validators : {
							notEmpty : {
								message : "customerId不能为空"
							}
						}
					},
					customerType : {
						validators : {
							notEmpty : {
								message : "customerType不能为空"
							}
						}
					},
					mobilePhone : {
						validators : {
							notEmpty : {
								message : "mobilePhone不能为空"
							}
						}
					},
					email : {
						validators : {
							notEmpty : {
								message : "email不能为空"
							}
						}
					},
					trueName : {
						validators : {
							notEmpty : {
								message : "trueName不能为空"
							}
						}
					},
					sex : {
						validators : {
							notEmpty : {
								message : "sex不能为空"
							}
						}
					},
					birthday : {
						validators : {
							notEmpty : {
								message : "birthday不能为空"
							}
						}
					},
					country : {
						validators : {
							notEmpty : {
								message : "country不能为空"
							}
						}
					},
					cardType : {
						validators : {
							notEmpty : {
								message : "cardType不能为空"
							}
						}
					},
					cardId : {
						validators : {
							notEmpty : {
								message : "cardId不能为空"
							}
						}
					},
					customerSource : {
						validators : {
							notEmpty : {
								message : "customerSource不能为空"
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
					realTime : {
						validators : {
							notEmpty : {
								message : "realTime不能为空"
							}
						}
					},
					emailCode : {
						validators : {
							notEmpty : {
								message : "emailCode不能为空"
							}
						}
					},
					cardIdUsd : {
						validators : {
							notEmpty : {
								message : "cardIdUsd不能为空"
							}
						}
					},
					cardIdValidPeriod : {
						validators : {
							notEmpty : {
								message : "cardIdValidPeriod不能为空"
							}
						}
					},
					postalAddress : {
						validators : {
							notEmpty : {
								message : "postalAddress不能为空"
							}
						}
					},
					postCode : {
						validators : {
							notEmpty : {
								message : "postCode不能为空"
							}
						}
					},
					contacts : {
						validators : {
							notEmpty : {
								message : "contacts不能为空"
							}
						}
					},
					stage : {
						validators : {
							notEmpty : {
								message : "stage不能为空"
							}
						}
					},
					contactsTel : {
						validators : {
							notEmpty : {
								message : "contactsTel不能为空"
							}
						}
					},
					handIdCardUrl : {
						validators : {
							notEmpty : {
								message : "handIdCardUrl不能为空"
							}
						}
					},
					idCardFrontUrl : {
						validators : {
							notEmpty : {
								message : "idCardFrontUrl不能为空"
							}
						}
					},
					idCardBackUrl : {
						validators : {
							notEmpty : {
								message : "idCardBackUrl不能为空"
							}
						}
					},
					handDealUrl : {
						validators : {
							notEmpty : {
								message : "handDealUrl不能为空"
							}
						}
					},
					isExamine : {
						validators : {
							notEmpty : {
								message : "isExamine不能为空"
							}
						}
					},
					eamineRefusalReason : {
						validators : {
							notEmpty : {
								message : "eamineRefusalReason不能为空"
							}
						}
					},
					isCancle : {
						validators : {
							notEmpty : {
								message : "isCancle不能为空"
							}
						}
					},
					cancleReason : {
						validators : {
							notEmpty : {
								message : "cancleReason不能为空"
							}
						}
					},
					withdrawCheckMoney : {
						validators : {
							notEmpty : {
								message : "withdrawCheckMoney不能为空"
							}
						}
					},
					personBank : {
						validators : {
							notEmpty : {
								message : "personBank不能为空"
							}
						}
					},
					personCard : {
						validators : {
							notEmpty : {
								message : "personCard不能为空"
							}
						}
					},
					frontpersonCard : {
						validators : {
							notEmpty : {
								message : "frontpersonCard不能为空"
							}
						}
					},
					surname : {
						validators : {
							notEmpty : {
								message : "surname不能为空"
							}
						}
					},
					papersType : {
						validators : {
							notEmpty : {
								message : "papersType不能为空"
							}
						}
					},
					btcDate : {
						validators : {
							notEmpty : {
								message : "btcDate不能为空"
							}
						}
					},
					btcCount : {
						validators : {
							notEmpty : {
								message : "btcCount不能为空"
							}
						}
					},
					lendTimes : {
						validators : {
							notEmpty : {
								message : "lendTimes不能为空"
							}
						}
					},
					lengPing : {
						validators : {
							notEmpty : {
								message : "lengPing不能为空"
							}
						}
					},
					lengRiskRate : {
						validators : {
							notEmpty : {
								message : "lengRiskRate不能为空"
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
					url : _ctx + "/customer/apppersoninfo/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/customer/apppersoninfolist')
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
					customerId : {
						validators : {
							notEmpty : {
								message : "customerId不能为空"
							}
						}
					},
					customerType : {
						validators : {
							notEmpty : {
								message : "customerType不能为空"
							}
						}
					},
					mobilePhone : {
						validators : {
							notEmpty : {
								message : "mobilePhone不能为空"
							}
						}
					},
					email : {
						validators : {
							notEmpty : {
								message : "email不能为空"
							}
						}
					},
					trueName : {
						validators : {
							notEmpty : {
								message : "trueName不能为空"
							}
						}
					},
					sex : {
						validators : {
							notEmpty : {
								message : "sex不能为空"
							}
						}
					},
					birthday : {
						validators : {
							notEmpty : {
								message : "birthday不能为空"
							}
						}
					},
					country : {
						validators : {
							notEmpty : {
								message : "country不能为空"
							}
						}
					},
					cardType : {
						validators : {
							notEmpty : {
								message : "cardType不能为空"
							}
						}
					},
					cardId : {
						validators : {
							notEmpty : {
								message : "cardId不能为空"
							}
						}
					},
					customerSource : {
						validators : {
							notEmpty : {
								message : "customerSource不能为空"
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
					realTime : {
						validators : {
							notEmpty : {
								message : "realTime不能为空"
							}
						}
					},
					emailCode : {
						validators : {
							notEmpty : {
								message : "emailCode不能为空"
							}
						}
					},
					cardIdUsd : {
						validators : {
							notEmpty : {
								message : "cardIdUsd不能为空"
							}
						}
					},
					cardIdValidPeriod : {
						validators : {
							notEmpty : {
								message : "cardIdValidPeriod不能为空"
							}
						}
					},
					postalAddress : {
						validators : {
							notEmpty : {
								message : "postalAddress不能为空"
							}
						}
					},
					postCode : {
						validators : {
							notEmpty : {
								message : "postCode不能为空"
							}
						}
					},
					contacts : {
						validators : {
							notEmpty : {
								message : "contacts不能为空"
							}
						}
					},
					stage : {
						validators : {
							notEmpty : {
								message : "stage不能为空"
							}
						}
					},
					contactsTel : {
						validators : {
							notEmpty : {
								message : "contactsTel不能为空"
							}
						}
					},
					handIdCardUrl : {
						validators : {
							notEmpty : {
								message : "handIdCardUrl不能为空"
							}
						}
					},
					idCardFrontUrl : {
						validators : {
							notEmpty : {
								message : "idCardFrontUrl不能为空"
							}
						}
					},
					idCardBackUrl : {
						validators : {
							notEmpty : {
								message : "idCardBackUrl不能为空"
							}
						}
					},
					handDealUrl : {
						validators : {
							notEmpty : {
								message : "handDealUrl不能为空"
							}
						}
					},
					isExamine : {
						validators : {
							notEmpty : {
								message : "isExamine不能为空"
							}
						}
					},
					eamineRefusalReason : {
						validators : {
							notEmpty : {
								message : "eamineRefusalReason不能为空"
							}
						}
					},
					isCancle : {
						validators : {
							notEmpty : {
								message : "isCancle不能为空"
							}
						}
					},
					cancleReason : {
						validators : {
							notEmpty : {
								message : "cancleReason不能为空"
							}
						}
					},
					withdrawCheckMoney : {
						validators : {
							notEmpty : {
								message : "withdrawCheckMoney不能为空"
							}
						}
					},
					personBank : {
						validators : {
							notEmpty : {
								message : "personBank不能为空"
							}
						}
					},
					personCard : {
						validators : {
							notEmpty : {
								message : "personCard不能为空"
							}
						}
					},
					frontpersonCard : {
						validators : {
							notEmpty : {
								message : "frontpersonCard不能为空"
							}
						}
					},
					surname : {
						validators : {
							notEmpty : {
								message : "surname不能为空"
							}
						}
					},
					papersType : {
						validators : {
							notEmpty : {
								message : "papersType不能为空"
							}
						}
					},
					btcDate : {
						validators : {
							notEmpty : {
								message : "btcDate不能为空"
							}
						}
					},
					btcCount : {
						validators : {
							notEmpty : {
								message : "btcCount不能为空"
							}
						}
					},
					lendTimes : {
						validators : {
							notEmpty : {
								message : "lendTimes不能为空"
							}
						}
					},
					lengPing : {
						validators : {
							notEmpty : {
								message : "lengPing不能为空"
							}
						}
					},
					lengRiskRate : {
						validators : {
							notEmpty : {
								message : "lengRiskRate不能为空"
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
					url : _ctx + "/customer/apppersoninfo/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/customer/apppersoninfolist')
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
				_table.seeRow($("#table"), _ctx + "/customer/apppersoninfo/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/customer/apppersoninfo/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/customer/apppersoninfo/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/customer/apppersoninfo/list.do",
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
					title : 'customerId',
					field : 'customerId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'customerType',
					field : 'customerType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'mobilePhone',
					field : 'mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'email',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'trueName',
					field : 'trueName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'sex',
					field : 'sex',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'birthday',
					field : 'birthday',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'country',
					field : 'country',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'cardType',
					field : 'cardType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'cardId',
					field : 'cardId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'customerSource',
					field : 'customerSource',
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
					title : 'realTime',
					field : 'realTime',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'emailCode',
					field : 'emailCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'cardIdUsd',
					field : 'cardIdUsd',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'cardIdValidPeriod',
					field : 'cardIdValidPeriod',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'postalAddress',
					field : 'postalAddress',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'postCode',
					field : 'postCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'contacts',
					field : 'contacts',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'stage',
					field : 'stage',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'contactsTel',
					field : 'contactsTel',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'handIdCardUrl',
					field : 'handIdCardUrl',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'idCardFrontUrl',
					field : 'idCardFrontUrl',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'idCardBackUrl',
					field : 'idCardBackUrl',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'handDealUrl',
					field : 'handDealUrl',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'isExamine',
					field : 'isExamine',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'eamineRefusalReason',
					field : 'eamineRefusalReason',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'isCancle',
					field : 'isCancle',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'cancleReason',
					field : 'cancleReason',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'withdrawCheckMoney',
					field : 'withdrawCheckMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'personBank',
					field : 'personBank',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'personCard',
					field : 'personCard',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'frontpersonCard',
					field : 'frontpersonCard',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'surname',
					field : 'surname',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'papersType',
					field : 'papersType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'btcDate',
					field : 'btcDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'btcCount',
					field : 'btcCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'lendTimes',
					field : 'lendTimes',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'lengPing',
					field : 'lengPing',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'lengRiskRate',
					field : 'lengRiskRate',
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