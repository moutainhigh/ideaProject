define(function(require, exports, module) {
	this._table = require("js/base/table");
	// 批量审核
	function allAudit() {
		$("#allAudit").on("click", function(){
			var ids = $.map($("#table").bootstrapTable('getSelections'), function(row) {
				return row.id
			});
			if(ids!=undefined&&ids.length>0){
				layer.confirm('你确定要审核批量通过吗？', {
					btn: ['确定','取消']
				}, function(){
					$.ajax({
						type : "post",
						url : _ctx+"/licqb/platform/applyteamaward/allAudit?ids="+ids,
						cache : false,
						dataType : "json",
						success : function(data) {
							if (data != undefined) {
								if (data.success) {
									layer.msg('审核成功!', {icon : 1});
									loadUrl(_ctx+'/v.do?u=/admin/licqb/platform/applyteamawardlist')
								}
							}
						}
					});

				})
			} else {
				layer.msg('请选择数据', {icon: 2});
				return false;
			}
		});
	}
	// 单个审核
	function audit() {
		$("#audit").on("click", function(){
			var ids = $.map($("#table").bootstrapTable('getSelections'), function(row) {
				return row.id
			});
			if(ids != undefined && ids.length==1){
				var applyStatus = $.map($("#table").bootstrapTable('getSelections'), function(row) {
					return row.applyStatus
				});
				var auditStatus = $.map($("#table").bootstrapTable('getSelections'), function(row) {
					return row.auditStatus
				});
				if (applyStatus[0] == 3 && auditStatus[0] == 0 ) {
					loadUrl(_ctx+"/licqb/platform/applyteamaward/gotoAuditFtl?id="+ids[0]+"&type=1");
				} else {
					if (applyStatus[0] != 3) {
						layer.msg("用户未完成申请，无法审核",{icon: 2})
					}
					if (auditStatus[0] != 0) {
						layer.msg("已审核",{icon: 2})
					}
				}
			}else{
				layer.msg('请选择一条数据', {icon: 2});
			}
		});
	}
	// 查看
	function see() {
		$("#see").on("click", function(){
			var ids = $.map($("#table").bootstrapTable('getSelections'), function(row) {
				return row.id
			});
			if(ids != undefined && ids.length==1){
				loadUrl(_ctx+"/licqb/platform/applyteamaward/gotoAuditFtl?id="+ids[0]+"&type=2");
			}else{
				layer.msg('请选择一条数据', {icon: 2});
			}
		});
	}

	// 操作按钮
	function headButton() {
		$("button[name='submit']").on("click",function () {
			var id = $("#id").val();
			var type = $(this).val();
			if (type == 2) {
				var auditExplain = $("#auditExplain").val();
				if (auditExplain == '') {
					layer.msg("驳回操作，审核说明不能为空")
					return
				}
			}
			$.ajax({
				type : "post",
				url : _ctx+"/licqb/platform/applyteamaward/audit?id="+id+"&type="+type+"&auditExplain="+auditExplain,
				cache : false,
				dataType : "json",
				success : function(data) {
					if (data != undefined) {
						if (data.success) {
							layer.msg('操作成功!', {icon : 1});
							loadUrl(_ctx+'/v.do?u=/admin/licqb/platform/applyteamawardlist')
						}
					}
				}
			});
		})
	}

	module.exports = {
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
			var conf = {
				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/licqb/platform/applyteamaward/list.do",
				columns : [
				{
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
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
					title : '社交类型',
					field : 'socialType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, row, index) {
						var html = '-';
						if (value = 1) {
							html = 'QQ'
						} else if (value = 2) {
							html = '微信'
						} else if (value = 3) {
							html = 'facebook'
						} else if (value = 4) {
							html = 'Twitter'
						} else if (value = 5) {
							html = 'Telegram'
						}
						return html;
					}

				},
				{
					title : '社交账户',
					field : 'socialAccount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '社交群图片',
					field : 'socialGroupImg',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						if(value != null){
							var html = "";
							html = '<img style="width: 30px;height: 30px;" name="img" src="'+_fileUrl+value+'" onclick="showPic(this)">';
							return html;
						} else {
							return "-";
						}
					}
				},
				{
					title : '申请状态',
					field : 'applyStatus',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, row, index) {
						var html = '-';
						if (value == 1) {
							html = '填写账户中'
						} else if (value == 2) {
							html = '上传图片中'
						} else if (value == 3) {
							html = '申请完成'
						}
						return html;
					}
				},
				{
					title : '审核状态',
					field : 'auditStatus',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value) {
						var html = '-';
						if (value == 1) {
							html = '通过'
						} else if (value == 2) {
							html = '驳回'
						} else {
							html = '审核中'
						}
						return html;
					}
				},
				{
					title : '审核通过时间',
					field : 'modified',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value,row, index) {
						var html = '-';
						if (row.auditStatus == 1) {
							html = value.split(' ')[0]
						}
						return html;
					}
				},
				/*{
					title : '操作',
					field : 'id',
					align : 'center',
					visible : true,
					formatter: function (value, row, index) {
						var html = '-';
						if ( row.applyStatus == 3 && row.auditStatus == 0) {
							html = '<a class="btn btn-default btn-xs changeJobStatus changeGreen" href="javascript:void(0);" name="audit" id="'+value+'" title="审核"><i class="glyphicon glyphicon-edit"></i>审核</a>'+'&nbsp;';
						}
						if (row.auditStatus == 2) {
							$("#auditExplain").val(row.auditExplain);
							html = '<a class="btn btn-default btn-xs changeJobStatus changeGreen" href="javascript:void(0);" name="see" id="'+value+'" title="查看原因"><i class="glyphicon glyphicon-edit"></i>查看原因</a>'+'&nbsp;';
						}
						return html;
					}
				}*/
				],
				success : function () {
					$("a[name='audit']").on("click", function(){
						var id = $(this).attr("id");
						loadUrl(_ctx+"/licqb/platform/applyteamaward/gotoAuditFtl?id="+id);
					});
					$("a[name='see']").on("click", function(){
						layer.open({
							type: 1,
							title : '驳回原因',
							skin: 'layui-layer-demo', //样式类名
							closeBtn: 1, //不显示关闭按钮
							anim: 2,
							shadeClose: true, //开启遮罩关闭
							area: ['400px', '230px'],
							content: $("#auditDiv")
						});
					});

				}
			}
			_table.initTable($("#table"), conf);
		},
		init : function () {
			see();
			audit();
			allAudit();
			headButton();
		}
	}

});