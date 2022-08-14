define(function(require, exports, module) {
	this._table = require("js/base/table");
	this.sele = require("js/base/HrySelect");
	var SMEArry=["101","102","103"];
	var USDTArry=["104","105","106","107","108"];
	var totalAccountID="101";//SME总帐户
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
										type : {
						validators : {
							notEmpty : {
								message : "账户类型不能为空"
							}
						}
					},
					money : {
						validators : {
							notEmpty : {
								message : "金额不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
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
					url : _ctx + "/klg/platform/klgplatformaccount/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/platform/klgplatformaccountlist')
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
					type : {
						validators : {
							notEmpty : {
								message : "账户类型不能为空"
							}
						}
					},
					money : {
						validators : {
							notEmpty : {
								message : "金额不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "coinCode不能为空"
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
					url : _ctx + "/klg/platform/klgplatformaccount/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/platform/klgplatformaccountlist')
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
			$("#recharge").on("click", function() {
				var  params=_table.getRowSelects($("#table"));
				if(params==undefined||params.length!=1){
					layer.msg('请选择一条数据', {icon: 2});
					return;
				}
				var type=params[0].type;
				if(totalAccountID!=type){
					layer.msg('此账户不可手动充值', {icon: 2});
					return;
				}
				$("#addAccount").find("option[value="+type+"]").attr("selected",true);
				layer.open({
					type: 1,
					title:"充值",
					shift: 2,
					area: ['800px', '600px'],
					shadeClose: false,
					content: $("#recharge-main"),
					success: function(layero, index){},
					yes:function(){},
					end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
						//$('#addAccount option').each(function(){this.style='display:block'});
						// $("#addAccount").val("");
					}
				});

			});

			// 添加页面跳转按钮
			$("#transfer").on("click", function() {
				var  params=_table.getRowSelects($("#table"));
				if(params==undefined||params.length!=1){
					layer.msg('请选择一条数据', {icon: 2});
					return;
				}
				var type=params[0].type;
				$("#account").find("option[value="+type+"]").attr("selected",true);
				var coinCode=params[0].coinCode;
				if(coinCode=="SMEC"){//SME SMEArry
					$.each(USDTArry, function(i,val){

						$('#toAccount option').each(function(){
							if($(this).val()==val||$(this).val()==type){
								this.style='display:none'
							}
						});
					});
				}else{//USDT USDTArry
					$.each(SMEArry, function(i,val){
						$('#toAccount option').each(function(){
							if($(this).val()==val||$(this).val()==type){
								this.style='display:none'
							}
						});
					});
				}
				$("#toAccount").find("option[value="+type+"]").style='display:none';
				layer.open({
					type: 1,
					title:"转账",
					shift: 2,
					area: ['800px', '600px'],
					shadeClose: false,
					content: $("#add-main"),
					success: function(layero, index){},
					yes:function(){},
					end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
						$('#toAccount option').each(function(){this.style='display:block'});
						$("#toAccount").val("");
					}
				});

			});
			//转账
			$("#submitTransfer").on("click", function() {
				var account=$("#account").val();
				var toAccount=$("#toAccount").val();
				var money=$("#number").val();
				if(account==undefined||account==''){
					layer.msg('转出账户不能为空', {icon: 2});
					return false;
				}
				if(toAccount==undefined||toAccount==''){
					layer.msg('转入账户不能为空', {icon: 2});
					return false;
				}if(money==undefined||money==''){
					layer.msg('转账数量不能为空', {icon: 2});
					return false;
				}
				var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
				if(!reg.test(money)){
					layer.msg('转账数量不正确', {icon: 2});
					return false;
				}
				$.ajax({
					type: "post",
					url: _ctx + "/klg/platform/klgplatformaccount/transfer",
					data: {
						account: account,
						toAccount: toAccount,
						money: money
					},
					cache: false,
					dataType: "json",
					success: function (data) {
						if(data!=undefined){
							if(data.success){
								layer.msg("成功", {icon: 1,time:2000});
								setTimeout(function () {
									//table刷新
									$("#table").bootstrapTable('refresh');
									layer.closeAll('page');
								}, 2000);
							}else{
								layer.msg(data.msg, {icon: 2});
							}
						}else{
							layer.msg(data, {icon: 2});
						}
					}
				});
			});

			//充值
			$("#submitRecharge").on("click", function() {
				var account=$("#addAccount").val();
				var money=$("#money").val();
				if(account==undefined||account==''){
					layer.msg('账户不能为空', {icon: 2});
					return false;
				}
				if(money==undefined||money==''){
					layer.msg('充值金额不能为空', {icon: 2});
					return false;
				}
				var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
				if(!reg.test(money)){
					layer.msg('充值金额不正确', {icon: 2});
					return false;
				}
				$.ajax({
					type: "post",
					url: _ctx + "/klg/platform/klgplatformaccount/recharge",
					data: {
						account: account,
						money: money
					},
					cache: false,
					dataType: "json",
					success: function (data) {
						if(data!=undefined){
							if(data.success){
								layer.msg("成功", {icon: 1});
								setTimeout(function () {
									//table刷新
									$("#table").bootstrapTable('refresh');
									layer.closeAll('page');
								}, 2000);
							}else{
								layer.msg(data.msg, {icon: 2});
							}
						}else{
							layer.msg(data, {icon: 2});
						}
					}
				});
			});
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/platform/klgplatformaccount/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '账户类型',
					field : 'type',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						var aa = sele.getKeyAndVal("account_type",value)
						return  aa;
					}
				},
				{
					title : '账户余额',
					field : 'money',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '币种',
					field : 'coinCode',
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