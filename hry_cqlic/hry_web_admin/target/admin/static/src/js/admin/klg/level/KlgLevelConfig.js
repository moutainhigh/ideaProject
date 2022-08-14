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
					levelName : {
						validators : {
							notEmpty : {
								message : "等级名称不能为空"
							}
						}
					},
					sort : {
						validators : {
							notEmpty : {
								message : "等级排序不能为空"
							},
							callback : {
                                message: "只能输入正整数且序号不能重复",
                                callback: function(value, validator) {
                                	return /^\d+$/.test(value);
                                }
                            }
						}
					},
					buyNums : {
						validators : {
							notEmpty : {
								message : "购买限制不能为空"
							}
						}
					},
					bonusMultiple : {
						validators : {
							notEmpty : {
								message : "奖金倍数不能为空"
							}
						}
					},
					pointAlgebra : {
						validators : {
							notEmpty : {
								message : "见点代数不能为空"
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
					url : _ctx + "/klg/level/klglevelconfig/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/level/klglevelconfiglist')
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
					levelName : {
						validators : {
							notEmpty : {
								message : "等级名称不能为空"
							}
						}
					},
					sort : {
						validators : {
							notEmpty : {
								message : "等级排序不能为空"
							},
							callback : {
                                message: "只能输入正整数且序号不能重复",
                                callback: function(value, validator) {
                                	return /^\d+$/.test(value);
                                }
                            }
						}
					},
					buyNums : {
						validators : {
							notEmpty : {
								message : "购买限制不能为空"
							}
						}
					},
					bonusMultiple : {
						validators : {
							notEmpty : {
								message : "奖金倍数不能为空"
							}
						}
					},
					pointAlgebra : {
						validators : {
							notEmpty : {
								message : "见点代数不能为空"
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
					url : _ctx + "/klg/level/klglevelconfig/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/level/klglevelconfiglist')
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}
				};
				$("#form").ajaxSubmit(options);
			});
		},

		//卖出奖励-修改页面--初始化方法
		sellmodify : function() {
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
					sellTime : {
						validators : {
							notEmpty : {
								message : "基础卖出时长"
							}
						}
					},
					candyNum : {
						validators : {
							notEmpty : {
								message : "基础糖果比率"
							}
						}
					},
					addCandyNum : {
						validators : {
							notEmpty : {
								message : "递增糖果比率"
							}
						}
					},
					maxSellTime : {
						validators : {
							notEmpty : {
								message : "最高卖出时长"
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
					url : _ctx + "/klg/level/klglevelconfig/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/level/klglevelsellconfiglist')
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}
				};
				$("#form").ajaxSubmit(options);
			});
		},
		//升级规则-修改页面--初始化方法
		uplevelmodify : function() {
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
					recommendNum : {
						validators : {
							notEmpty : {
								message : "推荐个数"
							}
						}
					},
					recommendSort : {
						validators : {
							notEmpty : {
								message : "推荐星级别"
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
					url : _ctx + "/klg/level/klglevelconfig/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/level/klguplevelconfiglist')
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

			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/level/klglevelconfig/modifysee");
			});
			/*// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/klg/level/klglevelconfig/remove.do");
			});*/

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/level/klglevelconfig/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '等级名称',
					field : 'levelName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '等级排序',
					field : 'sort',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '购买限制',
					field : 'buyNums',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '奖金倍数',
					field : 'bonusMultiple',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '见点代数',
					field : 'pointAlgebra',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '抽奖次数限制',
					field : 'luckDrawCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
		//卖出奖励列表页面--初始化方法
		list1 : function() {

			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/level/klglevelconfig/sellmodify");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/level/klglevelconfig/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
					{
						title : '等级名称',
						field : 'levelName',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '等级排序',
						field : 'sort',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '基础卖出时长',
						field : 'sellTime',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '基础糖果比率',
						field : 'candyNum',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '递增糖果比率',
						field : 'addCandyNum',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '最高卖出时长',
						field : 'maxSellTime',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '最高奖励时长',
						field : 'maxRewardTime',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					}
				]
			}
			_table.initTable($("#table"), conf);
		},
		//会员升级规则--列表页面--初始化方法
		list2 : function() {
			$("#recommendSort").hide();
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/level/klglevelconfig/uplevelmodify");
			});
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/level/klglevelconfig/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
					{
						title : '等级名称',
						field : 'levelName',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '等级排序',
						field : 'sort',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '升级条件',
						field : 'recommendNum',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true,
						formatter: function (value, row, index) {
							if(value==undefined||value==null){
								return "-";
							}
							var sort=row.recommendSort;
							var text=$("#recommendSort").find("option[value='"+sort+"']").text();
							return "推荐"+value+"个"+text;
						}
					}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});
