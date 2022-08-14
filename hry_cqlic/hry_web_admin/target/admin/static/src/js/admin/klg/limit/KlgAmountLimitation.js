define(function(require, exports, module) {
	this._table = require("js/base/table");
	this.sele = require("js/base/HrySelect");
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
										state : {
						validators : {
							notEmpty : {
								message : "是否限额不能为空"
							}
						}
					},
					money : {
						validators : {
							notEmpty : {
								message : "金额不能为空"
							},
							callback : {
								message: "金额不正确",
								callback: function(value, validator) {
									return /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/.test(value);
								}
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "类型不能为空"
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
					url : _ctx + "/klg/limit/klgamountlimitation/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/limit/klgamountlimitationlist')
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
			var lock=false;//默认未锁定
			// 修改提交
			$("#modifySubmit").on("click", function() {
				if(!lock){
					lock=true;//锁定
				var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
				var type=$("#type").val();
				if(type==3){
					if(!reg.test($("#morningLimit").val())){
						layer.msg('上午预约金额不正确', {icon: 2});
						lock=false;
						return false;
					}
					if(!reg.test($("#afternoonLimit").val())){
						layer.msg('下午预约金额不正确', {icon: 2});
						lock=false;
						return false;
					}
				}else{
					if(!reg.test($("#money").val())){
						layer.msg('金额不正确', {icon: 2});
						lock=false;
						return false;
					}
				}

				var options = {
					url : _ctx + "/klg/limit/klgamountlimitation/modify.do",
					type : "post",
					resetForm : false,// 提交后重置表单
					dataType : 'json',
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('修改成功!', {icon : 1});
							//	loadUrl(_ctx+'/v.do?u=/admin/klg/limit/klgamountlimitationlist')
							} else {
								layer.msg(data.msg, {icon : 2});
							}
							lock=false;
						}
					}
				};
				$("#form").ajaxSubmit(options);
				}
			});
		},
		//列表页面--初始化方法
		list : function() {

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/limit/klgamountlimitation/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/limit/klgamountlimitation/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/klg/limit/klgamountlimitation/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/limit/klgamountlimitation/list.do",
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
					title : '是否限额',
					field : 'state',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						var aa = sele.getKeyAndVal("yesno",value)
						return  aa;
					}
				},
				{
					title : '金额',
					field : 'money',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'coinCode',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '类型',
					field : 'type',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
		//列表页面--初始化方法
		listCustomer : function() {

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/customer/appcustomer/findListGroupByDay.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '注册人数',
					field : 'saasId',
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