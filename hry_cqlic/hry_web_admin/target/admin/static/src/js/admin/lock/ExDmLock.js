define(function(require, exports, module) {
	this._table = require("js/base/table");
    this.sele = require("js/base/HrySelect");

	module.exports = {
		// 初始化操作
		init: function(){
            var platCoin = $("#platCoin").val();
            var lockfrequency = $("#lockfrequency").val();
			$("#coinCode").on("change", function(){
				// 获取平台币币种
				// 如果是非平台币，则释放规则最后三个禁用
				if ($(this).val() == platCoin) {
				    /*if (lockfrequency && Number(lockfrequency) >= 30) {
                        $("#dailyReleaseOfUserRatio").removeAttr("disabled");
                        $("#releaseSortMethod").removeAttr("disabled").val("1");
                    } else {
                        $("#isCirculation").attr("disabled","disabled").val("");
                        $("#dailyReleaseOfUserRatio").attr("disabled","disabled").val("");
                    }*/
                    $("#isCirculation").removeAttr("disabled");
				} else {
                    $("#isCirculation").attr("disabled","disabled").val("");
                    /*$("#dailyReleaseOfUserRatio").attr("disabled","disabled").val("");
                    $("#releaseSortMethod").attr("disabled","disabled").val("");*/
				}
			});
		},
		// 查看方法
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
					coinCode : {
						validators : {
							notEmpty : {
								message : "请选择币种代码"
							}
						}
					},
                    lockStartLimit : {
                        validators : {
                            notEmpty : {
                                message : "锁仓起点额度不能为空"
                            },
							regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '请输入有效的锁仓起点额度'
                            }
                        }
                    },
                    lockMethod : {
                        validators : {
                            notEmpty : {
                                message : "请选择锁仓方式"
                            }
                        }
                    },
                    lockRatio : {
                        validators : {
                            notEmpty : {
                                message : "锁仓比例不能为空"
                            }
                        }
                    },
                    lockDuration : {
                        validators : {
                            notEmpty : {
                                message : "规则持续周期不能为空"
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '请输入有效的规则持续周期'
                            }
                        }
                    },
                    lockCycle : {
                        validators : {
                            notEmpty : {
                                message : "锁仓周期不能为空"
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '请输入有效的锁仓周期'
                            }
                        }
                    },
                    releaseFrequency : {
                        validators : {
                            notEmpty : {
                                message : "释放频率不能为空"
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '请输入有效的释放频率'
                            }
                        }
                    },
                    releaseMethod : {
						validators : {
							notEmpty : {
								message : "请选择释放方式"
							}
						}
					},
                    releaseMethodVal : {
						validators : {
							notEmpty : {
								message : "释放方式值不能为空"
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
					url : _ctx + "/lock/exdmlock/add.do",
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
                        // 规则时间校验
                        var startTime = $("#lockStartTime").val();
                        if (!startTime) {
                            layer.msg("请选择规则开始时间", {icon : 2});
                            return false;
                        }

                        var lockCycle = $("#lockCycle").val();
                        if (lockCycle < 1) {
                            layer.msg("锁仓周期不能小于1天", {icon : 2});
                            return false;
                        }

                        // 对释放方式的值进行校验，次数和数量只能是整数，比例可以是小数
                        var releaseMethod = $("#releaseMethod").val();
                        var releaseMethodVal = $("#releaseMethodVal").val();
                        var regu = /^[1-9]\d*$/; // 正整数
                        if (releaseMethod == '1' || releaseMethod == '2') {
                            if (!regu.test(releaseMethodVal)) {
                                layer.msg("请输入有效的释放方式值", {icon : 2});
                                return false;
                            }
                        }
                        // 对平台币进行校验
                        var platCoin = $("#platCoin").val();
                        var isCirculation = $("#isCirculation").val();
                        var releaseSortMethod = $("#releaseSortMethod").val();
                        var coinCode = $("#coinCode").val();
                        if (coinCode == platCoin) {
                            if (!isCirculation) {
                                layer.msg("请选择是否流通", {icon : 2});
                                return false;
                            }
                            /*if (!releaseSortMethod) {
                                layer.msg("请选择释放排序方式", {icon : 2});
                                return false;
                            }*/
                        }
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('添加成功!', {icon : 1});
								loadUrl(_ctx+'/v.do?u=/admin/lock/exdmlocklist')
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
                    coinCode : {
                        validators : {
                            notEmpty : {
                                message : "请选择币种代码"
                            }
                        }
                    },
                    lockStartLimit : {
                        validators : {
                            notEmpty : {
                                message : "锁仓起点额度不能为空"
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '请输入有效的锁仓起点额度'
                            }
                        }
                    },
                    lockMethod : {
                        validators : {
                            notEmpty : {
                                message : "请选择锁仓方式"
                            }
                        }
                    },
                    lockRatio : {
                        validators : {
                            notEmpty : {
                                message : "锁仓比例不能为空"
                            }
                        }
                    },
                    lockDuration : {
                        validators : {
                            notEmpty : {
                                message : "规则持续周期不能为空"
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '请输入有效的规则持续周期'
                            }
                        }
                    },
                    lockCycle : {
                        validators : {
                            notEmpty : {
                                message : "锁仓周期不能为空"
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '请输入有效的锁仓周期'
                            }
                        }
                    },
                    releaseFrequency : {
                        validators : {
                            notEmpty : {
                                message : "释放频率不能为空"
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '请输入有效的释放频率'
                            }
                        }
                    },
                    releaseMethod : {
                        validators : {
                            notEmpty : {
                                message : "请选择释放方式"
                            }
                        }
                    },
                    releaseMethodVal : {
                        validators : {
                            notEmpty : {
                                message : "释放方式值不能为空"
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
					url : _ctx + "/lock/exdmlock/modify.do",
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
                        // 规则时间校验
                        var startTime = $("#lockStartTime").val();
                        if (!startTime) {
                            layer.msg("请选择规则开始时间", {icon : 2});
                            return false;
                        }
                        // 对释放方式的值进行校验，次数和数量只能是整数，比例可以是小数
                        var releaseMethod = $("#releaseMethod").val();
                        var regu = /^[1-9]\d*$/; // 正整数
                        if (releaseMethod == '1' || releaseMethod == '2') {
                            if (!regu.test(releaseMethod)) {
                                layer.msg("请输入有效的释放方式值", {icon : 2});
                                return false;
                            }
                        }
                        // 对平台币进行校验
                        var platCoin = $("#platCoin").val();
						var isCirculation = $("#isCirculation").val();
						var releaseSortMethod = $("#releaseSortMethod").val();
                        var coinCode = $("#coinCode").val();
						if (coinCode == platCoin) {
							if (!isCirculation) {
                                layer.msg("请选择是否流通", {icon : 2});
                                return false;
							}
                            /*if (!releaseSortMethod) {
                                layer.msg("请选择释放排序方式", {icon : 2});
                                return false;
                            }*/
						}
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('修改成功!', {icon : 1});
								loadUrl(_ctx+'/v.do?u=/admin/lock/exdmlocklist')
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
            // 添加按钮
            $("#add").on("click", function() {
                loadUrl(_ctx + "/lock/exdmlock/toAdd");
            });
			// 修改按钮
			$("#modify").on("click", function() {
                var rows = _table.getRowSelects($("#table"));
                if (rows.length === 0) {
                    layer.msg("请选择数据", {icon : 2});
                    return false;
                } else if (rows.length > 1) {
                    layer.msg("只能选择一条数据", {icon : 2});
                    return false;
                } else if (rows[0].isLock == 1){
                    layer.msg("开启的规则不能修改", {icon : 2});
                    return false;
				} else {
                    _table.seeRow($("#table"), _ctx + "/lock/exdmlock/modifysee");
				}
			});
            // 查看按钮
            $("#see").on("click", function() {
                _table.seeRow($("#table"), _ctx + "/lock/exdmlock/see");
            });
			// 开启按钮
			$("#lock_open").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if (ids.length === 0) {
                    layer.msg("请选择数据", {icon : 2});
                    return false;
                } else if (ids.length > 1) {
                    layer.msg("只能选择一条数据", {icon : 2});
                    return false;
                } else {
                    $.ajax({
						type: "POST",
                        dataType: "JSON",
                        url : _ctx + '/lock/exdmlock/open/'+ids[0],
                        cache: false,
                        success: function (data) {
							if (data.success) {
								layer.msg("开启成功", {icon: 1});
								loadUrl(_ctx + '/v.do?u=/admin/lock/exdmlocklist')
							} else {
								layer.msg(data.msg, {icon: 2});
							}
						}
                    });
                }
			});
			// 关闭按钮
			$("#lock_close").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if (ids && ids.length === 0) {
                    layer.msg("请选择数据", {icon : 2});
                    return false;
                } else if (ids.length > 1) {
                    layer.msg("只能选择一条数据", {icon : 2});
                    return false;
                } else {
                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url : _ctx + '/lock/exdmlock/close/'+ids[0],
                        cache: false,
                        success: function (data) {
                            if (data.success) {
                                layer.msg("关闭成功", {icon: 1});
                                loadUrl(_ctx + '/v.do?u=/admin/lock/exdmlocklist')
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    });
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
				url : _ctx + "/lock/exdmlock/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '币种代码',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '账户锁仓起点额度（个）',
					field : 'lockStartLimit',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '释放方式',
					field : 'releaseMethod',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function(value, row, index) {
						return sele.getKeyAndVal("lockReleaseMethod", value);
					}
				},
				{
					title : '释放值',
					field : 'releaseMethodVal',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '锁仓开关',
					field : 'isLock',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, row, index) {
                        if(value != null){
                            if(row.isLock === 0){
                                return "<font color='red'>关闭</font>";
                            } else {
                                return "开启";
                            }
                        }
					}
				},
				{
					title : '操作人',
					field : 'optUser',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '操作时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			};
			_table.initTable($("#table"), conf);
		}
	}

});