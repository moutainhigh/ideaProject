define(function(require, exports, module) {
	this._table = require("js/base/table");
	HrySelect = require("js/base/HrySelect");
	HryPopup = require("js/base/HryPopup");

	module.exports = {
		// 查看页面--初始化方法
		see : function(projectid,userid) {
			
			// ----------------------------联系人表start-------------------------------
			// 联系人表格
			var conf = {
				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx +"/cust/custrelation/findbyuserid.do?userid="+userid,
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : true
				}, {
					title : '姓名',
					field : 'name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '手机号',
					field : 'mobile',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '身份证号码',
					field : 'cardid',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '关系',
					field : 'relationtype',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'select',
						disabled: true, 
						source : HrySelect.getKey("relationtype"),
						validate : function(value) {
							return "";
						}
					}
				}

				]
			}
			_table.initTable($("#relation_table"), conf);
			// ----------------------------联系人表end-------------------------------

			// ----------------------------放款收息表start-------------------------------

			var conf = {
				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx +"/pro/proincomerecord/findbyprojectid.do?projectid="+projectid,
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false,
					width : "30PX",
					footerFormatter : function(value) {
						return "合计"
					}
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : true
				},

				{
					title : '期数',
					field : 'period',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '本金',
					field : 'principalmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].principalmoney) {
									count += parseFloat(data[i].principalmoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '利息',
					field : 'interestmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].interestmoney) {
									count += parseFloat(data[i].interestmoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '服务费',
					field : 'servicemoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].servicemoney) {
									count += parseFloat(data[i].servicemoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '应收合计',
					field : 'totalmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].totalmoney) {
									count += parseFloat(data[i].totalmoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '计划到账日',
					field : 'enddate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				} ]
			}
			_table.initTable($("#table"), conf);
			// ----------------------------放款收息表end-------------------------------
		
			
			
		},
		// 添加页面--初始化方法
		add : function() {
			//姓名放大镜
			HryPopup.init("选择客户","user_truename_search",function(table){
				var conf = {
						url : _ctx+"/cust/custuser/list.do",
						columns  :[ {
							field : 'state',
							checkbox : true,
							align : 'center',
							valign : 'middle',
							value : "id",
							searchable : false
						}, {
							title : 'ID',
							field : 'id',
							align : 'center',
							visible : false,
							searchable : false
						}, {
							field : 'truename',
							title : '姓名',
							sortable : true,
							align : 'center'
						}, {
							field : 'mobile',
							title : '手机号',
							sortable : true,
							align : 'center'
						}]
				}
				_table.initTable(table,conf);
				//表格点击事件
				table.on('dbl-click-row.bs.table', function (e, row, $element) {
					//附值
					$("#info_marrytype").val(1);
					$.ajax({
						type : "post",
						url : _ctx + "/cust/custuser/getuserinfo.do?id="+row.id,
						cache : false,
						dataType : "json",
						success : function(data) {
							if (data) {
								$("#user_truename").val(data.user.truename)
								$("#user_number").val(data.user.number)
								$("#user_cardtype").val(data.user.cardtype)
								$("#user_cardid").val(data.user.cardid)
								$("#user_mobile").val(data.user.mobile)
								$("#info_marrytype").val(data.info.marrytype)
								$("#info_mobile2").val(data.info.mobile2)
								$("#info_homeaddress").val(data.info.homeaddress)
								$("#info_qqnumber").val(data.info.qqnumber)
								$("#info_wxnumber").val(data.info.wxnumber)
								$("#info_wbnumber").val(data.info.wbnumber)
								$("#info_hascar").val(data.info.hascar)
								$("#info_hashouse").val(data.info.hashouse)
								$("#info_company").val(data.info.company)
								$("#info_companyphone").val(data.info.companyphone)
								$("#info_companyadress").val(data.info.companyadress)
								$("#info_remark").val(data.info.remark)
								HryPopup.close("user_truename_search");
							}
						},
						error : function(e) {

						}
					});
				});
				
			});
			
			// ----------------------------联系人表start-------------------------------
			// 添加按钮
			$("#relation_add").on("click", function() {

				$("#relation_table").bootstrapTable('insertRow', {
					index : 0,
					row : {
						id : guid(),
						name : "",
						mobile : "",
						cardid : "",
						relationtype : "friend"
					}
				});

			});

			// 删除按钮
			$("#relation_remove").on("click", function() {
				var ids = _table.getIdSelects($("#relation_table"));
				$("#relation_table").bootstrapTable('remove', {
					field : 'id',
					values : ids
				});
			});

			// 联系人表格
			var conf = {
				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				data : [],
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : true
				}, {
					title : '姓名',
					field : 'name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					}
				}, {
					title : '手机号',
					field : 'mobile',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					}
				}, {
					title : '身份证号码',
					field : 'cardid',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					}
				}, {
					title : '关系',
					field : 'relationtype',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'select',
						source : HrySelect.getKey("relationtype"),
						validate : function(value) {
							return "";
						}
					}
				}

				]
			}
			_table.initTable($("#relation_table"), conf);
			// ----------------------------联系人表end-------------------------------

			// ----------------------------放款收息表start-------------------------------
			// 生成按钮
			$("#auto").on("click", function() {
				
				//重置validate;
				$("#project_form").data("bootstrapValidator").resetForm();
				$('#project_form').bootstrapValidator('enableFieldValidators', 'project_projectname', false);
				$('#project_form').bootstrapValidator('enableFieldValidators', 'project_projectnumber', false);
				
				// 手动触发验证
				// 手动触发验证
				var bootstrapValidator = $('#project_form').data('bootstrapValidator');
				bootstrapValidator.validate();
				if (!bootstrapValidator.isValid()) {
					return false;
				}

				$.ajax({
					type : "post",
					url : _ctx + "/pro/proincomerecord/auto.do",
					data : {
						project_projectmoney : $("#project_projectmoney").val(),
						project_interesttype : $("#project_interesttype").val(),
						project_repaymentperiod : $("#project_repaymentperiod").val(),
						project_loadperiod : $("#project_loadperiod").val(),
						project_startdate : $("#project_startdate").val(),
						project_loadrate : $("#project_loadrate").val(),
						project_servicerate : $("#project_servicerate").val(),
					},
					cache : false,
					dataType : "json",
					success : function(data) {
						if (data) {
							// 清空全部
							$("#table").bootstrapTable('removeAll');
							// 循环生成
							for (var i = 0; i < data.length; i++) {
								$("#table").bootstrapTable('insertRow', {
									index : i,
									row : {
										id : data[i].id,
										period : data[i].period,
										principalmoney : data[i].principalmoney,
										interestmoney : data[i].interestmoney,
										servicemoney : data[i].servicemoney,
										totalmoney : data[i].totalmoney,
										enddate : data[i].enddate
									}
								});
							}
						}
					},
					error : function(e) {

					}
				});

			});

			// 删除按钮
			$("#remove").on("click", function() {
				var ids = _table.getIdSelects($("#table"));
				$("#table").bootstrapTable('remove', {
					field : 'id',
					values : ids
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
				data : [],
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false,
					width : "30PX",
					footerFormatter : function(value) {
						return "合计"
					}
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : true
				},

				{
					title : '期数',
					field : 'period',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '本金',
					field : 'principalmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					},
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].principalmoney) {
									count += parseFloat(data[i].principalmoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '利息',
					field : 'interestmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					},
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].interestmoney) {
									count += parseFloat(data[i].interestmoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '服务费',
					field : 'servicemoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					},
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].servicemoney) {
									count += parseFloat(data[i].servicemoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '应收合计',
					field : 'totalmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					},
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].totalmoney) {
									count += parseFloat(data[i].totalmoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '计划到账日',
					field : 'enddate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				} ]
			}
			_table.initTable($("#table"), conf);
			// ----------------------------放款收息表end-------------------------------
			
			
			//初始化验证
			$('#project_form').bootstrapValidator({
				submitButtons : "button[id=addSubmit]",
				message : '不能为空',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					project_projectmoney : {
						validators : {
							notEmpty : {
								message : "债权金额不能为空"
							},
							numeric : {
								message : '债权金额只能输入数字'
							}
						}
					},
					project_interesttype : {
						validators : {
							notEmpty : {
								message : "计息方式不能为空"
							}
						}
					},
					project_repaymentperiod : {
						validators : {
							notEmpty : {
								message : "还款周期不能为空"
							}
						}
					},
					project_startdate : {
						validators : {
							notEmpty : {
								message : "放款日期不能为空"
							}
						}
					},
					project_loadperiod : {
						validators : {
							notEmpty : {
								message : "贷款期限不能为空"
							},
							numeric : {
								message : '贷款期限只能输入数字'
							}
						}
					},
					project_loadrate : {
						validators : {
							notEmpty : {
								message : "贷款利率不能为空"
							},
							numeric : {
								message : '贷款利率只能输入数字'
							}
						}
					},
					project_servicerate : {
						validators : {
							numeric : {
								message : '服务费率只能输入数字'
							}
						}
					},
					project_projectnumber : {
						
						validators : {
							notEmpty : {
								message : "合同编号不能为空"
							}
						}
					},
					project_projectname : {
						enabled: false,
						validators : {
							notEmpty : {
								message : "产品名称不能为空"
							}
						}
					}
				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			})
			
			// 表单验证
			$('#info_form').bootstrapValidator({
				submitButtons : "button[id=addSubmit]",
				message : '不能为空',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					user_truename : {
						validators : {
							notEmpty : {
								message : "姓名不能为空"
							}
						}
					},
					user_cardtype : {
						validators : {
							notEmpty : {
								message : "证件类型不能为空"
							}
						}
					},
					user_cardid : {
						validators : {
							notEmpty : {
								message : "证件号码不能为空"
							},
							cardid : {
								message : "证件号格式不正确"
							}
						}
						
					},
					user_mobile : {
						validators : {
							phone : {
								message : "手机号格式不正确",
								country : "CN"
							}
						}
					},
					info_mobile2 : {
						validators : {
							phone : {
								message : "手机号格式不正确",
								country : "CN"
							}
						}
					}

				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			})
			
			
			// 添加提交
			$("#addSubmit").on("click", function() {


				// 手动触发验证
				var bootstrapValidator = $('#info_form').data('bootstrapValidator');
				bootstrapValidator.validate();
				if (!bootstrapValidator.isValid()) {
					return false;
				}
				
				//重置validate
				$("#project_form").data("bootstrapValidator").resetForm();
				$('#project_form').bootstrapValidator('enableFieldValidators', 'project_projectname', true);
				$('#project_form').bootstrapValidator('enableFieldValidators', 'project_projectnumber', true);
				// 手动触发验证
				var bootstrapValidator = $('#project_form').data('bootstrapValidator');
				bootstrapValidator.validate();
				if (!bootstrapValidator.isValid()) {
					return false;
				}
			

				// 获得联系人数据
				var relations = $("#relation_table").bootstrapTable('getData');
				var newrelations = [];
				relations.forEach(function(value, index, array) {//过滤姓名为空的列
					if (value.name != undefined || value.name != "") {
						newrelations.push(value);
					}
				})

				// 获得款项数据
				var incomerecords = $("#table").bootstrapTable('getData');

				$.ajax({
					type : "post",
					url : _ctx + "/pro/proproject/add.do",
					data : {
						user_truename : $("#user_truename").val(),
						user_number : $("#user_number").val(),
						user_cardtype : $("#user_cardtype").val(),
						user_cardid : $("#user_cardid").val(),
						user_mobile : $("#user_mobile").val(),
						info_marrytype : $("#info_marrytype").val(),
						info_mobile2 : $("#info_mobile2").val(),
						info_homeaddress : $("#info_homeaddress").val(),
						info_qqnumber : $("#info_qqnumber").val(),
						info_wxnumber : $("#info_wxnumber").val(),
						info_wbnumber : $("#info_wbnumber").val(),
						info_hascar : $("#info_hascar").val(),
						info_hashouse : $("#info_hashouse").val(),
						info_company : $("#info_company").val(),
						info_companyphone : $("#info_companyphone").val(),
						info_companyadress : $("#info_companyadress").val(),
						info_remark : $("#info_remark").val(),
						project_projectmoney : $("#project_projectmoney").val(),
						project_interesttype : $("#project_interesttype").val(),
						project_repaymentperiod : $("#project_repaymentperiod").val(),
						project_loadperiod : $("#project_loadperiod").val(),
						project_startdate : $("#project_startdate").val(),
						project_enddate : $("#project_enddate").val(),
						project_loadrate : $("#project_loadrate").val(),
						project_servicerate : $("#project_servicerate").val(),
						project_projectnumber : $("#project_projectnumber").val(),
						project_projectname : $("#project_projectname").val(),
						project_hasguarantee : $("#project_hasguarantee").val(),
						project_hasmortgage : $("#project_hasmortgage").val(),
						project_remark : $("#project_remark").val(),
						relations :JSON.stringify(newrelations) ,
						incomerecords : JSON.stringify(incomerecords)
					},
					cache : false,
					dataType : "json",
					success : function(data) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('添加成功!', {
									icon : 1
								});
								loadUrl('/admin/pro/proprojectlist')
							} else {
								layer.msg(data.msg, {
									icon : 2
								});
							}
						}
					},
					error : function(e) {

					}
				});

			});
			
			
			$.ajax({
				type : "post",
				url : _ctx + "/cust/custuser/getnumber.do",
				cache : false,
				dataType : "json",
				success : function(data) {
					if (data) {
						if(data.success){
							$("#user_number").val(data.obj);
						}
					}
				},
				error : function(e) {

				}
			});
			
		},
		// 修改页面--初始化方法
		modify : function(projectid,userid) {
			
			//姓名放大镜
			HryPopup.init("选择客户","user_truename_search",function(table){
				var conf = {
						url : _ctx+"/cust/custuser/list.do",
						columns  :[ {
							field : 'state',
							checkbox : true,
							align : 'center',
							valign : 'middle',
							value : "id",
							searchable : false
						}, {
							title : 'ID',
							field : 'id',
							align : 'center',
							visible : false,
							searchable : false
						}, {
							field : 'truename',
							title : '姓名',
							sortable : true,
							align : 'center'
						}, {
							field : 'mobile',
							title : '手机号',
							sortable : true,
							align : 'center'
						}]
				}
				_table.initTable(table,conf);
				//表格点击事件
				table.on('dbl-click-row.bs.table', function (e, row, $element) {
					//附值
					$("#info_marrytype").val(1);
					$.ajax({
						type : "post",
						url : _ctx + "/cust/custuser/getuserinfo.do?id="+row.id,
						cache : false,
						dataType : "json",
						success : function(data) {
							if (data) {
								$("#user_truename").val(data.user.truename)
								$("#user_number").val(data.user.number)
								$("#user_cardtype").val(data.user.cardtype)
								$("#user_cardid").val(data.user.cardid)
								$("#user_mobile").val(data.user.mobile)
								$("#info_marrytype").val(data.info.marrytype)
								$("#info_mobile2").val(data.info.mobile2)
								$("#info_homeaddress").val(data.info.homeaddress)
								$("#info_qqnumber").val(data.info.qqnumber)
								$("#info_wxnumber").val(data.info.wxnumber)
								$("#info_wbnumber").val(data.info.wbnumber)
								$("#info_hascar").val(data.info.hascar)
								$("#info_hashouse").val(data.info.hashouse)
								$("#info_company").val(data.info.company)
								$("#info_companyphone").val(data.info.companyphone)
								$("#info_companyadress").val(data.info.companyadress)
								$("#info_remark").val(data.info.remark)
								HryPopup.close("user_truename_search");
							}
						},
						error : function(e) {

						}
					});
				});
				
			});
			
			// ----------------------------联系人表start-------------------------------
			// 添加按钮
			$("#relation_add").on("click", function() {

				$("#relation_table").bootstrapTable('insertRow', {
					index : 0,
					row : {
						id : guid(),
						name : "",
						mobile : "",
						cardid : "",
						relationtype : "friend"
					}
				});

			});

			// 删除按钮
			$("#relation_remove").on("click", function() {
				var ids = _table.getIdSelects($("#relation_table"));
				$("#relation_table").bootstrapTable('remove', {
					field : 'id',
					values : ids
				});
			});

			// 联系人表格
			var conf = {
				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx +"/cust/custrelation/findbyuserid.do?userid="+userid,
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : true
				}, {
					title : '姓名',
					field : 'name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					}
				}, {
					title : '手机号',
					field : 'mobile',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					}
				}, {
					title : '身份证号码',
					field : 'cardid',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					}
				}, {
					title : '关系',
					field : 'relationtype',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'select',
						source : HrySelect.getKey("relationtype"),
						validate : function(value) {
							return "";
						}
					}
				}

				]
			}
			_table.initTable($("#relation_table"), conf);
			// ----------------------------联系人表end-------------------------------

			// ----------------------------放款收息表start-------------------------------
			// 生成按钮
			$("#auto").on("click", function() {
				
				//重置validate;
				$("#project_form").data("bootstrapValidator").resetForm();
				$('#project_form').bootstrapValidator('enableFieldValidators', 'project_projectname', false);
				$('#project_form').bootstrapValidator('enableFieldValidators', 'project_projectnumber', false);
				
				// 手动触发验证
				// 手动触发验证
				var bootstrapValidator = $('#project_form').data('bootstrapValidator');
				bootstrapValidator.validate();
				if (!bootstrapValidator.isValid()) {
					return false;
				}

				$.ajax({
					type : "post",
					url : _ctx + "/pro/proincomerecord/auto.do",
					data : {
						project_projectmoney : $("#project_projectmoney").val(),
						project_interesttype : $("#project_interesttype").val(),
						project_repaymentperiod : $("#project_repaymentperiod").val(),
						project_loadperiod : $("#project_loadperiod").val(),
						project_startdate : $("#project_startdate").val(),
						project_loadrate : $("#project_loadrate").val(),
						project_servicerate : $("#project_servicerate").val(),
					},
					cache : false,
					dataType : "json",
					success : function(data) {
						if (data) {
							// 清空全部
							$("#table").bootstrapTable('removeAll');
							// 循环生成
							for (var i = 0; i < data.length; i++) {
								$("#table").bootstrapTable('insertRow', {
									index : i,
									row : {
										id : data[i].id,
										period : data[i].period,
										principalmoney : data[i].principalmoney,
										interestmoney : data[i].interestmoney,
										servicemoney : data[i].servicemoney,
										totalmoney : data[i].totalmoney,
										enddate : data[i].enddate
									}
								});
							}
						}
					},
					error : function(e) {

					}
				});

			});

			// 删除按钮
			$("#remove").on("click", function() {
				var ids = _table.getIdSelects($("#table"));
				$("#table").bootstrapTable('remove', {
					field : 'id',
					values : ids
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
				url : _ctx +"/pro/proincomerecord/findbyprojectid.do?projectid="+projectid,
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false,
					width : "30PX",
					footerFormatter : function(value) {
						return "合计"
					}
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : true
				},

				{
					title : '期数',
					field : 'period',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '本金',
					field : 'principalmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					},
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].principalmoney) {
									count += parseFloat(data[i].principalmoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '利息',
					field : 'interestmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					},
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].interestmoney) {
									count += parseFloat(data[i].interestmoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '服务费',
					field : 'servicemoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					},
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].servicemoney) {
									count += parseFloat(data[i].servicemoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '应收合计',
					field : 'totalmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					editable : {
						type : 'text',
						validate : function(value) {
							return "";
						}
					},
					footerFormatter : function(data) {
						var count = 0;
						if (data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].totalmoney) {
									count += parseFloat(data[i].totalmoney);
								}
							}
						}
						return count.toFixed(2);
					}
				}, {
					title : '计划到账日',
					field : 'enddate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				} ]
			}
			_table.initTable($("#table"), conf);
			// ----------------------------放款收息表end-------------------------------
			
			
			//初始化验证
			$('#project_form').bootstrapValidator({
				submitButtons : "button[id=addSubmit]",
				message : '不能为空',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					project_projectmoney : {
						validators : {
							notEmpty : {
								message : "债权金额不能为空"
							},
							numeric : {
								message : '债权金额只能输入数字'
							}
						}
					},
					project_interesttype : {
						validators : {
							notEmpty : {
								message : "计息方式不能为空"
							}
						}
					},
					project_repaymentperiod : {
						validators : {
							notEmpty : {
								message : "还款周期不能为空"
							}
						}
					},
					project_startdate : {
						validators : {
							notEmpty : {
								message : "放款日期不能为空"
							}
						}
					},
					project_loadperiod : {
						validators : {
							notEmpty : {
								message : "贷款期限不能为空"
							},
							numeric : {
								message : '贷款期限只能输入数字'
							}
						}
					},
					project_loadrate : {
						validators : {
							notEmpty : {
								message : "贷款利率不能为空"
							},
							numeric : {
								message : '贷款利率只能输入数字'
							}
						}
					},
					project_servicerate : {
						validators : {
							numeric : {
								message : '服务费率只能输入数字'
							}
						}
					},
					project_projectnumber : {
						
						validators : {
							notEmpty : {
								message : "合同编号不能为空"
							}
						}
					},
					project_projectname : {
						enabled: false,
						validators : {
							notEmpty : {
								message : "产品名称不能为空"
							}
						}
					}
				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			})
			
			// 表单验证
			$('#info_form').bootstrapValidator({
				submitButtons : "button[id=addSubmit]",
				message : '不能为空',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					user_truename : {
						validators : {
							notEmpty : {
								message : "姓名不能为空"
							}
						}
					},
					user_cardtype : {
						validators : {
							notEmpty : {
								message : "证件类型不能为空"
							}
						}
					},
					user_cardid : {
						validators : {
							notEmpty : {
								message : "证件号码不能为空"
							},
							cardid : {
								message : "证件号格式不正确"
							}
						}
						
					},
					user_mobile : {
						validators : {
							phone : {
								message : "手机号格式不正确",
								country : "CN"
							}
						}
					},
					info_mobile2 : {
						validators : {
							phone : {
								message : "手机号格式不正确",
								country : "CN"
							}
						}
					}

				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			});
			
			
			// 编辑提交
			$("#modifySubmit").on("click", function() {


				// 手动触发验证
				var bootstrapValidator = $('#info_form').data('bootstrapValidator');
				bootstrapValidator.validate();
				if (!bootstrapValidator.isValid()) {
					return false;
				}
				
				//重置validate
				$("#project_form").data("bootstrapValidator").resetForm();
				$('#project_form').bootstrapValidator('enableFieldValidators', 'project_projectname', true);
				$('#project_form').bootstrapValidator('enableFieldValidators', 'project_projectnumber', true);
				// 手动触发验证
				var bootstrapValidator = $('#project_form').data('bootstrapValidator');
				bootstrapValidator.validate();
				if (!bootstrapValidator.isValid()) {
					return false;
				}
			

				// 获得联系人数据
				var relations = $("#relation_table").bootstrapTable('getData');
				var newrelations = [];
				relations.forEach(function(value, index, array) {//过滤姓名为空的列
					if (value.name != undefined || value.name != "") {
						newrelations.push(value);
					}
				})

				// 获得款项数据
				var incomerecords = $("#table").bootstrapTable('getData');

				$.ajax({
					type : "post",
					url : _ctx + "/pro/proproject/modify.do",
					data : {
						user_id : $("#user_id").val(),
						user_truename : $("#user_truename").val(),
						user_number : $("#user_number").val(),
						user_cardtype : $("#user_cardtype").val(),
						user_cardid : $("#user_cardid").val(),
						user_mobile : $("#user_mobile").val(),
						info_id : $("#info_id").val(),
						info_marrytype : $("#info_marrytype").val(),
						info_mobile2 : $("#info_mobile2").val(),
						info_homeaddress : $("#info_homeaddress").val(),
						info_qqnumber : $("#info_qqnumber").val(),
						info_wxnumber : $("#info_wxnumber").val(),
						info_wbnumber : $("#info_wbnumber").val(),
						info_hascar : $("#info_hascar").val(),
						info_hashouse : $("#info_hashouse").val(),
						info_company : $("#info_company").val(),
						info_companyphone : $("#info_companyphone").val(),
						info_companyadress : $("#info_companyadress").val(),
						info_remark : $("#info_remark").val(),
						project_id : $("#project_id").val(),
						project_projectmoney : $("#project_projectmoney").val(),
						project_interesttype : $("#project_interesttype").val(),
						project_repaymentperiod : $("#project_repaymentperiod").val(),
						project_loadperiod : $("#project_loadperiod").val(),
						project_startdate : $("#project_startdate").val(),
						project_enddate : $("#project_enddate").val(),
						project_loadrate : $("#project_loadrate").val(),
						project_servicerate : $("#project_servicerate").val(),
						project_projectnumber : $("#project_projectnumber").val(),
						project_projectname : $("#project_projectname").val(),
						project_hasguarantee : $("#project_hasguarantee").val(),
						project_hasmortgage : $("#project_hasmortgage").val(),
						project_remark : $("#project_remark").val(),
						relations :JSON.stringify(newrelations) ,
						incomerecords : JSON.stringify(incomerecords)
					},
					cache : false,
					dataType : "json",
					success : function(data) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('添加成功!', {
									icon : 1
								});
								loadUrl('/admin/pro/proprojectlist')
							} else {
								layer.msg(data.msg, {
									icon : 2
								});
							}
						}
					},
					error : function(e) {

					}
				});

			});
		
			
		},
		// 列表页面--初始化方法
		list : function() {

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proproject/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proproject/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				
				var ids = _table.getIdSelects($("#table"));
				if(ids!=undefined&&ids.length==1){
					
					layer.confirm('你确定要删除吗？', {
		    			btn: ['确定','取消'],
		    			ids : ids
					}, function(){
						$.ajax({
							type : "post",
							url : _ctx + "/pro/proproject/remove.do",
							data : {
								id :  ids[0]
							},
							cache : false,
							dataType : "json",
							success : function(data) {
								if (data != undefined) {
									if (data.success) {
										layer.msg('删除成功!', {
											icon : 1
										});
										loadUrl('/admin/pro/proprojectlist')
									} else {
										layer.msg(data.msg, {
											icon : 2
										});
									}
								}
							},
							error : function(e) {
	
							}
							})
					})
					
					
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
				url : _ctx + "/pro/proproject/list.do",
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}, {
					title : '债权名称',
					field : 'projectname',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '合同编号',
					field : 'projectnumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},  {
					title : '债权本金',
					field : 'projectmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '债务人',
					field : 'truename',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
					title : '债权开始日间',
					field : 'startdate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '债权到期时间',
					field : 'enddate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				} ]
			}
			_table.initTable($("#table"), conf);
		},
		overdue : function() {

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proproject/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proproject/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/pro/proproject/list.do?state_EQ=1",
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}, {
					title : '债权名称',
					field : 'projectname',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '合同编号',
					field : 'projectnumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},  {
					title : '债权本金',
					field : 'projectmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '债务人',
					field : 'truename',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
					title : '债权开始日间',
					field : 'startdate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '债权到期时间',
					field : 'enddate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				} ]
			}
			_table.initTable($("#table"), conf);
		}
		,
		normal : function() {//正常结清

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proproject/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proproject/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/pro/proproject/list.do?state_EQ=2",
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}, {
					title : '债权名称',
					field : 'projectname',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '合同编号',
					field : 'projectnumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},  {
					title : '债权本金',
					field : 'projectmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '债务人',
					field : 'truename',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
					title : '债权开始日间',
					field : 'startdate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '债权到期时间',
					field : 'enddate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				} ]
			}
			_table.initTable($("#table"), conf);
		},
		press : function() {//催收结清

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proproject/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proproject/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/pro/proproject/list.do?state_EQ=3",
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}, {
					title : '债权名称',
					field : 'projectname',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '合同编号',
					field : 'projectnumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},  {
					title : '债权本金',
					field : 'projectmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '债务人',
					field : 'truename',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
					title : '债权开始日间',
					field : 'startdate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '债权到期时间',
					field : 'enddate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				} ]
			}
			_table.initTable($("#table"), conf);
		},
		bad : function() {//坏账债权

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proproject/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/pro/proproject/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/pro/proproject/list.do?state_EQ=4",
				columns : [ {
					field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				}, {
					title : 'id',
					field : 'id',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}, {
					title : '债权名称',
					field : 'projectname',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '合同编号',
					field : 'projectnumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},  {
					title : '债权本金',
					field : 'projectmoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '债务人',
					field : 'truename',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},{
					title : '债权开始日间',
					field : 'startdate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '债权到期时间',
					field : 'enddate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				} ]
			}
			_table.initTable($("#table"), conf);
		}
	}

});