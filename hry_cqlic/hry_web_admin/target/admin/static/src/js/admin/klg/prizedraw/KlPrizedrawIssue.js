define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		// 查看页面--初始化方法
		see : function() {
		},
		// 添加页面--初始化方法
		add : function() {
			$("#startDate").on("change", function(event) {
				var startDate = $("#startDate").val();// 开始时间
				var dayTime = 7 * 24 * 60 * 60 * 1000; // 参数天数的时间戳
				var nowTime = new Date(startDate).getTime(); // 时间戳
				var t = new Date(nowTime + dayTime); // 把两个时间戳转换成普通时间
				$("#endDateText").val(t.Format("yyyy-MM-dd"));
				$("#endDate").val(t.Format("yyyy-MM-dd"));
			});
			Date.prototype.Format = function(fmt) { // author: meizz
				var o = {
					"M+" : this.getMonth() + 1, // 月份
					"d+" : this.getDate(), // 日
					"H+" : this.getHours(), // 小时
					"m+" : this.getMinutes(), // 分
					"s+" : this.getSeconds(), // 秒
					"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
					"S" : this.getMilliseconds()
				// 毫秒
				};
				if (/(y+)/.test(fmt))
					fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
							.substr(4 - RegExp.$1.length));
				for ( var k in o)
					if (new RegExp("(" + k + ")").test(fmt))
						fmt = fmt.replace(RegExp.$1,
								(RegExp.$1.length == 1) ? (o[k])
										: (("00" + o[k])
												.substr(("" + o[k]).length)));
				return fmt;
			};
			
			/**
			 * 判断质数
			 */
			function isPrimeNum(num) {
				if (!isDual(num)) {
					return false;
				}
				for (var i = 2; i < num / 2 + 1; i++) {
					if (num % i == 0) {
						return false;
					}
				}
				return true;
			}
			function isDual(num) {
				var num = num.toString();
				var lastNum = num.substring(num.length - 1, num.length);
				return lastNum % 2 == 0 || lastNum % 5 == 0 ? false : true;
			}
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
					issueNumber : {
						validators : {
							notEmpty : {
								message : "期号不能为空"
							},
							callback : {
								message : "只能输入正整数且序号不能重复",
								callback : function(value, validator) {
									return /^\d+$/.test(value);
								}
							}
						}
					},
					primeNumber : {
						validators : {
							callback : {
								message : "只能输入100-1000以内的质数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									if (value<100||value>1000) {
										return false;
									}
									if(!/^\d+$/.test(value)){
										return false;
									}
									if(!isPrimeNum(value)){
										return false;
									}
									return true;
								}
							}
						}
					},
					startDate : {
						validators : {
							notEmpty : {
								message : "开始时间不能为空"
							},
							callback : {
								message : "开始时间必须选择周一",
								callback : function(value, validator) {
									return new Date(value).getDay() == 1;
								}
							},
							callback : {
								message : "开始时间必须大于当前时间",
								callback : function(value, validator) {
									return new Date(value) >= new Date();
								}
							}
						}
					},
					endDate : {
						validators : {
							notEmpty : {
								message : "结束时间不能为空"
							}
						}
					},
					mondayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					tuesdayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					wednesdayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					thursdayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					fridayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					saturdayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					sundayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					}
				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			});
			// 添加提交
			$("#addSubmit")
					.on(
							"click",
							function() {
								var options = {
									url : _ctx
											+ "/klg/prizedraw/klprizedrawissue/add.do",
									type : "post",
									resetForm : false,// 提交后重置表单
									dataType : 'json',
									beforeSubmit : function(formData, jqForm,
											options) {

										// 重置验证
										jqForm.data("bootstrapValidator")
												.resetForm();
										// 手动触发验证
										var bootstrapValidator = jqForm
												.data('bootstrapValidator');
										bootstrapValidator.validate();
										if (!bootstrapValidator.isValid()) {
											return false;
										}
									},
									success : function(data, statusText) {
										if (data != undefined) {
											if (data.success) {
												layer.msg('添加成功!', {
													icon : 1
												});
												loadUrl(_ctx
														+ '/v.do?u=/admin/klg/prizedraw/klprizedrawissuelist')
											} else {
												layer.msg(data.msg, {
													icon : 2
												});
											}
										}
									}

								};
								$("#form").ajaxSubmit(options);
							});
		},
		// 修改页面--初始化方法
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
					issueNumber : {
						validators : {
							notEmpty : {
								message : "期号不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "状态(1.待生效 2.生效中 3.已生效)不能为空"
							}
						}
					},
					primeNumber : {
						validators : {
							callback : {
								message : "只能输入100-1000以内的质数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									if (value<100||value>1000) {
										return false;
									}
									if(!/^\d+$/.test(value)){
										return false;
									}
									if(!isPrimeNum(value)){
										return false;
									}
									return true;
								}
							}
						}
					},
					mondayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					tuesdayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					wednesdayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					thursdayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					fridayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					saturdayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					},
					sundayNumber : {
						validators : {
							callback : {
								message : "只能输入正整数",
								callback : function(value, validator) {
									if (value == null || value == "") {
										return true;
									}
									return /^\d+$/.test(value);
								}
							}
						}
					}
				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			});
			// 修改提交
			$("#modifySubmit")
					.on(
							"click",
							function() {
								var options = {
									url : _ctx
											+ "/klg/prizedraw/klprizedrawissue/modify.do",
									type : "post",
									resetForm : true,// 提交后重置表单
									dataType : 'json',
									beforeSubmit : function(formData, jqForm,
											options) {

										// 重置验证
										jqForm.data("bootstrapValidator")
												.resetForm();
										// 手动触发验证
										var bootstrapValidator = jqForm
												.data('bootstrapValidator');
										bootstrapValidator.validate();
										if (!bootstrapValidator.isValid()) {
											return false;
										}
									},
									success : function(data, statusText) {
										if (data != undefined) {
											if (data.success) {
												layer.msg('修改成功!', {
													icon : 1
												});
												loadUrl(_ctx
														+ '/v.do?u=/admin/klg/prizedraw/klprizedrawissuelist')
											} else {
												layer.msg(data.msg, {
													icon : 2
												});
											}
										}
									}
								};
								$("#form").ajaxSubmit(options);
							});
		},
		// 列表页面--初始化方法
		list : function() {
			// 重置按钮
			$("#table_reset").on("click", function() {
				$("#table_query_form")[0].reset();
			});
			// 查询按钮
			$("#table_query").on("click", function() {
				var params = $("#table_query_form").serializeJson();
				// 查询
				_table.tableQuery($("#table"), params);
			});
			// 添加页面跳转按钮
			$("#see").on(
					"click",
					function() {
						_table.seeRow($("#table"), _ctx
								+ "/klg/prizedraw/klprizedrawissue/see");
					});
			// 修改页面跳转按钮
			$("#modify").on(
					"click",
					function() {
						var startDate = $.map($("#table").bootstrapTable(
								'getSelections'), function(row) {
							return row.startDate
						});
						// alert(new Date(startDate)<=new Date());
						if (new Date(startDate) <= new Date()) {
							layer.msg('本期大奖已开始,不能进行修改!', {
								icon : 2
							});
							return;
						}

						_table.seeRow($("#table"), _ctx
								+ "/klg/prizedraw/klprizedrawissue/modifysee");
					});
			// 删除按钮点击事件
			$("#remove").on(
					"click",
					function() {
						_table.removeRow($("#table"), _ctx
								+ "/klg/prizedraw/klprizedrawissue/remove.do");
					});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/klg/prizedraw/klprizedrawissue/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				}, {
					title : '期号',
					field : 'issueNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function(value, row, index) {
						return "第" + value + "期";
					}
				}, {
					title : '质数',
					field : 'primeNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '开奖号码',
					field : 'lotteryNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '添加时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '开始时间',
					field : 'startDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '开奖时间',
					field : 'endDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '周一号码',
					field : 'mondayNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '周二号码',
					field : 'tuesdayNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '周三号码',
					field : 'wednesdayNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '周四号码',
					field : 'thursdayNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '周五号码',
					field : 'fridayNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '周六号码',
					field : 'saturdayNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '周日号码',
					field : 'sundayNumber',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '状态',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function(value, row, index) {
						if (value == 1) {
							return "待生效";
						} else if (value == 2) {
							return "生效中";
						} else if (value == 3) {
							return "已生效";
						}
					}
				} ]
			}
			_table.initTable($("#table"), conf);
		}
	}

});