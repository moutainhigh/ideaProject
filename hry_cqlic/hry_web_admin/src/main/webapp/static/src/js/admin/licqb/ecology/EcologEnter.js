define(function(require, exports, module) {
	this._table = require("js/base/table");

	// 图片上传
	function uploadingImage() {
		$("#uploading").on("change", function(){
			var image = $(this);
			//用formDate对象上传
			var data = new FormData();
			data.append("file" , $("#uploading")[0].files[0])
			$.ajax({
				type: "post",
				url: _ctx+"/file/upload",
				async: true,
				data: data,
				processData: false,
				contentType: false,
				dataType : "json",
				success: function (result) {
					$("#enterLogo").val(result.obj)
					$("img[name='img']").attr("src", _fileUrl+result.obj);
					layer.msg("上传成功", {icon : 1});
				}
			});
		});
	}


	// 审核相关
	function ajax(formData, status) {
		$.ajax({
			type: "post",
			url: _ctx + "/licqb/ecology/ecologenter/handleStatus",
			data: formData,
			processData: false,
			contentType: false,
			dataType: "json",
			success: function (data) {
				if(data.success){
					layer.msg("操作成功", {icon: 1,time: 1500});
					loadUrl(_ctx + '/licqb/ecology/ecologenter/gotoEcoLogEnterList?status='+status);
				}else{
					layer.msg(data.msg, {icon: 2});
				}
			}
		});
	}

	function columns(status) {
		var columns = [];
		columns.push({
			checkbox : true,
			align : 'center',
			valign : 'middle',
			value : "id",
			searchable : false
		});
		columns.push({
			title : '申请单号',
			field : 'orderNum',
			align : 'center',
			visible : true,
			sortable : false,
			searchable : true
		});
		columns.push({
			title : '手机号',
			field : 'mobilePhone',
			align : 'center',
			visible : true,
			sortable : false,
			searchable : true
		});
		columns.push({
			title : '邮箱',
			field : 'email',
			align : 'center',
			visible : true,
			sortable : false,
			searchable : true
		});
		columns.push({
			title : '板块名称',
			field : 'plateName',
			align : 'center',
			visible : true,
			sortable : false,
			searchable : true
		});
		columns.push({
			title : '入驻等级',
			field : 'enterLevel',
			align : 'center',
			visible : true,
			sortable : false,
			searchable : true
		});
		columns.push({
			title : '入驻名称',
			field : 'enterName',
			align : 'center',
			visible : true,
			sortable : false,
			searchable : true
		});
		columns.push({
			title : '申请时间',
			field : 'created',
			align : 'center',
			visible : true,
			sortable : false,
			searchable : true
		});
		if (status == 5) {
			columns.push({
				title : '状态',
				field : 'enterStatus',
				align : 'center',
				visible : true,
				sortable : false,
				searchable : true,
				formatter: function(value, rows, index) {
					if (value == 5) {
						return "待核实";
					} else if (value == 6 && rows.renewStatus == 1) {
						return "核实通过-续费待核实"
					}
				}
			});
		}
		if (status == 0) {
			columns.push({
				title : '保证金有效期(天)',
				field : 'validityDay',
				align : 'center',
				visible : true,
				sortable : false,
				searchable : true
			});
			columns.push({
				title : '核实时间',
				field : 'verifyDate',
				align : 'center',
				visible : true,
				sortable : false,
				searchable : true
			});
			columns.push({
				title : '到期时间',
				field : 'expireDate',
				align : 'center',
				visible : true,
				sortable : false,
				searchable : true
			});
			columns.push({
				title : '状态',
				field : 'enterStatus',
				align : 'center',
				visible : true,
				sortable : false,
				searchable : true,
				formatter: function(value, rows, index) {
					if (value == 1) {
						return "申请中";
					} else if (value == 2) {
						return "审核拒绝";
					} else if (value == 3) {
						return "待付款";
					} else if (value == 4) {
						return "用户拒绝";
					} else if (value == 5) {
						return "待核实";
					} else if (value == 6) {
						if (rows.renewStatus == 1) {
							return "核实通过-续费待核实"
						} else {
							return "核实通过";
						}
					} else if (value == 7) {
						return "核实未通过";
					} else if (value == 8) {
						return "已到期";
					}
				}
			});
		}
		return columns;
	}

	function onCheck(status){
		var ids = $.map($("#table").bootstrapTable('getSelections'), function(row) {
			return row.id
		});
		if(ids != undefined && ids.length == 1){
			var url = _ctx + "/licqb/ecology/ecologenter/modifysee";
			loadUrl(url+"/"+ids[0]+"?status="+status);
		}else{
			layer.msg('请选择一条数据', {icon: 2});
		}
	}
	module.exports = {

		//修改页面--初始化方法
		modify : function() {
			uploadingImage();
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
								message : "用户Id不能为空"
							}
						}
					},
					orderNum : {
						validators : {
							notEmpty : {
								message : "单号不能为空"
							}
						}
					},
					plateId : {
						validators : {
							notEmpty : {
								message : "板块Id不能为空"
							}
						}
					},
					enterLevel : {
						validators : {
							notEmpty : {
								message : "入驻等级 A(前三) B 不能为空"
							}
						}
					},
					enterName : {
						validators : {
							notEmpty : {
								message : "入驻名称不能为空"
							}
						}
					},
					enterLogo : {
						validators : {
							notEmpty : {
								message : "入驻logo不能为空"
							}
						}
					},
					downloadLink : {
						validators : {
							notEmpty : {
								message : "下载链接不能为空"
							}
						}
					},
					enterApplyIntro : {
						validators : {
							notEmpty : {
								message : "申请入驻简介不能为空"
							}
						}
					},
					validityDay : {
						validators : {
							notEmpty : {
								message : "实际保证期有效天数不能为空"
							}
						}
					},
					expireDate : {
						validators : {
							notEmpty : {
								message : "到期时间不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
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
					url : _ctx + "/licqb/ecology/ecologenter/modify.do",
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
								loadUrl(_ctx+'/licqb/ecology/ecologenter/gotoEcoLogEnterList')
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}
				};
				$("#form").ajaxSubmit(options);
			});

			// 审核提交
			$("#applyAudit").on("click", function() {
				var formData = new FormData();
				var id = $("#id").val();
				var status = $("#status").val();
				var enterStatus = $("input[name='enterStatus']:checked").val();
				var enterReply = $('#enterReply').val();
				if (enterReply == '') {
					layer.msg('请填写回复内容!', {icon : 2});
					return;
				}
				formData.append("id", id);
				formData.append("enterStatus", enterStatus);
				formData.append("enterReply", enterReply);
				ajax(formData,status);
			});

			$("#applyCheck").on("click", function() {
				var formData = new FormData();
				var id = $("#id").val();
				var status = $("#status").val();
				var enterStatus = $("input[name='verifyRadio']:checked").val();
				formData.append("id", id);
				formData.append("enterStatus", enterStatus);
				ajax(formData,status);
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

			var status = $("#status").val();
			// 查看详情跳转按钮
			$("#applySee").on("click", function() {
				onCheck(status);
			});
			// 编辑
			$("#edit").on("click", function() {
				onCheck(3);
			});
			var conf = {
				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/licqb/ecology/ecologenter/list.do?status="+status,
				columns : columns(status)
			}
			_table.initTable($("#table"), conf);
		},
		//缴费记录
		enterPayList: function() {
			var orderNum = $("#orderNum").val();
			var conf = {
				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/licqb/ecology/ecologpay/list.do?orderNum="+orderNum,
				columns : [
					{
						title : '缴费时间',
						field : 'payDate',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '收款地址',
						field : 'acceptAddress',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '付款地址',
						field : 'paymentAddress',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '核实时间',
						field : 'verifyDate',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '保证金有效期（天）',
						field : 'validityDay',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : '备注',
						field : 'remark',
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