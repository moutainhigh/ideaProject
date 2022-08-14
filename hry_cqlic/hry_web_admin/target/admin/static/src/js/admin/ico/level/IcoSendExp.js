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
										id : {
						validators : {
							notEmpty : {
								message : "id不能为空"
							}
						}
					},
					customerId : {
						validators : {
							notEmpty : {
								message : "customerId不能为空"
							}
						}
					},
					state : {
						validators : {
							notEmpty : {
								message : "state不能为空"
							}
						}
					},
					countingTime : {
						validators : {
							notEmpty : {
								message : "countingTime不能为空"
							}
						}
					},
					number : {
						validators : {
							notEmpty : {
								message : "number不能为空"
							}
						}
					},
					releaseDate : {
						validators : {
							notEmpty : {
								message : "releaseDate不能为空"
							}
						}
					},
					hour : {
						validators : {
							notEmpty : {
								message : "hour不能为空"
							}
						}
					},
					experience : {
						validators : {
							notEmpty : {
								message : "experience不能为空"
							}
						}
					},
					lockId : {
						validators : {
							notEmpty : {
								message : "lockId不能为空"
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
					url : _ctx + "/ico/level/icosendexp/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/level/icosendexplist')
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
					id : {
						validators : {
							notEmpty : {
								message : "id不能为空"
							}
						}
					},
					customerId : {
						validators : {
							notEmpty : {
								message : "customerId不能为空"
							}
						}
					},
					state : {
						validators : {
							notEmpty : {
								message : "state不能为空"
							}
						}
					},
					countingTime : {
						validators : {
							notEmpty : {
								message : "countingTime不能为空"
							}
						}
					},
					number : {
						validators : {
							notEmpty : {
								message : "number不能为空"
							}
						}
					},
					releaseDate : {
						validators : {
							notEmpty : {
								message : "releaseDate不能为空"
							}
						}
					},
					hour : {
						validators : {
							notEmpty : {
								message : "hour不能为空"
							}
						}
					},
					experience : {
						validators : {
							notEmpty : {
								message : "experience不能为空"
							}
						}
					},
					lockId : {
						validators : {
							notEmpty : {
								message : "lockId不能为空"
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
					url : _ctx + "/ico/level/icosendexp/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/level/icosendexplist')
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
				_table.seeRow($("#table"), _ctx + "/ico/level/icosendexp/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/ico/level/icosendexp/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/ico/level/icosendexp/remove.do");
			});
			$("#xiufu").on("click", function() {
				$.ajax({
					type : "post",
					url : _ctx + "/ico/level/icosendexp/repairData",
					cache : false,
					async : false,
					dataType : "json",
					success : function(data) {
						if(data.success) {
							layer.msg(data.msg, {icon: 1});
						}else{
							layer.msg(data.msg, {icon: 2});
						}
					},
					error : function(e) {
					}
				});
			});

			// 发经验按钮点击事件
			$("#sendExp").on("click", function() {
				$.ajax({
					type : "post",
					url : _ctx + "/ico/level/icosendexp/sendExp",
					cache : false,
					async : false,
					dataType : "json",
					success : function(data) {
							if(data.success) {
								layer.msg(data.msg, {icon: 1});
								$("#table").bootstrapTable('refresh');
							}else{
								layer.msg(data.msg, {icon: 2});
							}
						},
					error : function(e) {
					}
				});
			});
			$("#send").on("click", function() {
				var ids = $.map($("#table").bootstrapTable('getSelections'), function(row) {
					return row.id
				});
				if(ids!=undefined&&ids.length==1){
					$.ajax({
						type : "post",
						url : _ctx + "/ico/level/icosendexp/send",
						cache : false,
						dataType : "json",
						data:{
							id : ids[0]
						},
						success : function(data) {
							if(data!=undefined){
								if(data.success){
									layer.msg('操作成功', {icon: 1});
									//table刷新
									$("#table").bootstrapTable('refresh');
								}else{
									layer.msg(data.msg, {icon: 2});
								}
							}else{
								layer.msg("请求无响应", {icon: 2});
							}
						},
						error : function(e) {
							$("#page-wrapper").html("<div class='row'><h1>此路径不存在："+url.substring(url.indexOf("u=")+2)+"</h1></div>");
						}
					});
				}else{
					layer.msg('请选择一条数据', {icon: 2});
				}
			});



			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/ico/level/icosendexp/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},

				{
					title : '用户Id',
					field : 'customerId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '状态',
					field : 'state',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						if(value=='0'){
							return "待发放";
						}else if(value='1'){
							return "已发放";
						}return value;
					}
				},
				{
					title : '上次更新时间',
					field : 'countingTime',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						return new Date(value);
					}
				},
				{
					title : '数量',
					field : 'number',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '释放时间',
					field : 'releaseDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						return new Date(value);
					}
				},
				{
					title : '小时数',
					field : 'hour',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '要发的经验',
					field : 'experience',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '锁仓Id',
					field : 'lockId',
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