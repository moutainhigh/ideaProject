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
								message : "用户id不能为空"
							}
						}
					},
					issueNumber : {
						validators : {
							notEmpty : {
								message : "期号不能为空"
							}
						}
					},
					primeNumber : {
						validators : {
							notEmpty : {
								message : "质数不能为空"
							}
						}
					},
					lotteryNumber : {
						validators : {
							notEmpty : {
								message : "开奖号码不能为空"
							}
						}
					},
					prizedrawNumber : {
						validators : {
							notEmpty : {
								message : "抽奖号码不能为空"
							}
						}
					},
					startDate : {
						validators : {
							notEmpty : {
								message : "开奖时间不能为空"
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
					status : {
						validators : {
							notEmpty : {
								message : "奖金状态(1.未发放 2.已发放)不能为空"
							}
						}
					},
					prizedrawGrade : {
						validators : {
							notEmpty : {
								message : "中奖等级(1.一等奖 2.二等奖 3.三等奖 ......)不能为空"
							}
						}
					},
					money : {
						validators : {
							notEmpty : {
								message : "中奖金额不能为空"
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
					url : _ctx + "/klg/prizedraw/klprizedrawselection/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/prizedraw/klprizedrawselectionlist')
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
								message : "用户id不能为空"
							}
						}
					},
					issueNumber : {
						validators : {
							notEmpty : {
								message : "期号不能为空"
							}
						}
					},
					primeNumber : {
						validators : {
							notEmpty : {
								message : "质数不能为空"
							}
						}
					},
					lotteryNumber : {
						validators : {
							notEmpty : {
								message : "开奖号码不能为空"
							}
						}
					},
					prizedrawNumber : {
						validators : {
							notEmpty : {
								message : "抽奖号码不能为空"
							}
						}
					},
					startDate : {
						validators : {
							notEmpty : {
								message : "开奖时间不能为空"
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
					status : {
						validators : {
							notEmpty : {
								message : "奖金状态(1.未发放 2.已发放)不能为空"
							}
						}
					},
					prizedrawGrade : {
						validators : {
							notEmpty : {
								message : "中奖等级(1.一等奖 2.二等奖 3.三等奖 ......)不能为空"
							}
						}
					},
					money : {
						validators : {
							notEmpty : {
								message : "中奖金额不能为空"
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
					url : _ctx + "/klg/prizedraw/klprizedrawselection/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/klg/prizedraw/klprizedrawselectionlist')
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
			
			//重置按钮
            $("#table_reset").on("click", function () {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });
            /*调控按钮*/
            $("#seeModel").on("click", function () {
            	var issueNumber = $.map($("#table").bootstrapTable('getSelections'), function(row) {
    				return row.issueNumber
    			});
            	//alert(issueNumber);
            	if(issueNumber!=undefined&&issueNumber.length==1){
            		$.ajax({
    					type : "post",
    					url : _ctx + "/klg/prizedraw/klprizedrawselection/seeMobile/"+issueNumber+".do",
    					cache : false,
    					dataType : "html",
    					success : function(data) {
    						layer.open({
 							   type: 1,
 							   skin: 'layui-layer-rim', //加上边框
 							   closeBtn: 1, //不显示关闭按钮
 							   title:"显示",
 							   area: ['600px', '400px'], //宽高
 							   content: data,
 							   resize:false,
 							   shadeClose : false,
 							   success: function(layero, index){
 								   
 							   },
 							   end: function () {
 									layer.closeAll();
 							   }
 							});
    						
    					}
    				});
            	}else{
            		layer.msg('请选择一条数据', {icon: 2});
    				return false;
    			}
			});
			/*// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/prizedraw/klprizedrawselection/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/klg/prizedraw/klprizedrawselection/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/klg/prizedraw/klprizedrawselection/remove.do");
			});*/

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/prizedraw/klprizedrawselection/list.do",
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
					visible : false,
					sortable : false,
					searchable : false
				},{
					title : '邮箱',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '手机号',
					field : 'mobilePhone',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '期号',
					field : 'issueNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '质数',
					field : 'primeNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '开奖号码',
					field : 'lotteryNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '开奖时间',
					field : 'startDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '添加时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '抽奖号码',
					field : 'prizedrawNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '中奖等级',
					field : 'prizedrawGrade',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						return value+"等奖";
                    }
				},
				{
					title : '中奖金额',
					field : 'money',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '奖金状态',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
                    	if(value==1){
                    		return "未发放";
                    	}else if(value==2){
                    		return "已发放";
                    	}
                    }
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});