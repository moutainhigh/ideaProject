define(function(require, exports, module) {
	this._table = require("js/base/table");
	function ajax(formData, status) {
		$.ajax({
			type: "post",
			url: _ctx + "/licqb/ecology/ecofund/modify.do",
			data: formData,
			processData: false,
			contentType: false,
			dataType: "json",
			success: function (data) {
				if(data.success){
					layer.msg("操作成功", {icon: 1,time: 1500});
					loadUrl(_ctx + '/licqb/ecology/ecofund/gotoEcoList?status='+status);
				}else{
					layer.msg(data.msg, {icon: 2});
				}
			}
		});
	}
	function imageList () {
		var activityImage = $("#activityImage").val();
		var imgList = activityImage.split(",");
		var html = "";
		for (var i = 0; i < imgList.length; i++) {
			html += '<img style="width: 82px;height: 75px; margin-right: 10px" name="img" src="'+_fileUrl+imgList[i]+'" onclick="showPic(this)">'
		}
		$("#activityImageDiv").html(html);

		var againActivityImage = $("#againActivityImage").val();
		if (againActivityImage != undefined) {
			var againImgList = againActivityImage.split(",");
			var html = "";
			for (var i = 0; i < againImgList.length; i++) {
				html += '<img style="width: 82px;height: 75px; margin-right: 10px" name="img" src="'+_fileUrl+againImgList[i]+'" onclick="showPic(this)">'
			}
			$("#againActivityImageDiv").html(html);
		}
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

			var status = $("#status").val();
			// 查看详情跳转按钮
			$("#applySee").on("click", function() {
				var ids = $.map($("#table").bootstrapTable('getSelections'), function(row) {
					return row.id
				});
				if(ids != undefined && ids.length == 1){
					var url = _ctx + "/licqb/ecology/ecofund/modifysee";
					loadUrl(url+"/"+ids[0]+"?status="+status);
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
				url : _ctx + "/licqb/ecology/ecofund/list.do?status="+status,
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '申请单号',
					field : 'orderNum',
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
					title : '邮箱',
					field : 'email',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '团队名称',
					field : 'activityName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '活动时间',
					field : 'activityDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, rows, index) {
						if (rows.activityStatus == 6 || rows.activityStatus == 7) {
							return rows.againActivityDate;
						} else {
							return value;
						}
					}
				},
				{
					title : '活动地址',
					field : 'activityAddress',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, rows, index) {
						if (rows.activityStatus == 6 || rows.activityStatus == 7) {
							return rows.againActivityAddress;
						} else {
							return value;
						}
					}
				},
				{
					title : '参会人数',
					field : 'peopleCount',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, rows, index) {
						if (rows.activityStatus == 6 || rows.activityStatus == 7) {
							return rows.againPeopleCount;
						} else {
							return value;
						}
					}
				},
				{
					title : '活动简介',
					field : 'activityIntro',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, rows, index) {
						if (rows.activityStatus == 6 || rows.activityStatus == 7) {
							var str = rows.againActivityIntro;
							if (str.length > 10) {
								return str.substring(0,10)+"......";
							}
							return str;
						} else {
							if (value.length > 10) {
								return value.substring(0,10)+"......";
							}
							return value;
						}
					}
				},
				{
					title : '申请时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '申请状态',
					field : 'activityStatus',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter : function (value, rows) {
						if (value == 1) {
							return '申请中';
						} else if (value == 2) {
							return '申请拒绝';
						} else if (value == 3) {
							if (rows.itAgain == 1) {
								return '资料核实拒绝-用户待确认';
							} else {
								return '平台通过-用户待确认';
							}
						} else if (value == 4) {
							return '平台通过-用户拒绝';
						} else if (value == 5) {
							return '平台通过-资料待补充';
						} else if (value == 6) {
							return '补充申请-待核实';
						} else if (value == 7) {
							return '申请已完成';
						}
					}

				}
				]
			}
			_table.initTable($("#table"), conf);
		},
		modify: function () {
			imageList();
			// 审核提交
			$("#applyAudit").on("click", function() {
				var formData = new FormData();
				var id = $("#id").val();
				var status = $("#status").val();
				var activityStatus = $("input[name='activityStatus']:checked").val();
				var activityReply = $('#activityReply').val();
				if (activityReply == '') {
					layer.msg('请填写回复内容!', {icon : 2});
					return;
				}
				formData.append("id", id);
				formData.append("activityStatus", activityStatus);
				formData.append("activityReply", activityReply);
				ajax(formData,status);
			});

			$("button[name='verify']").on("click", function() {
				var formData = new FormData();
				var id = $("#id").val();
				var status = $("#status").val();
				var activityStatus = $(this).val();
				var againActivityReply = $('#againActivityReply').val();
				if (againActivityReply == '') {
					layer.msg('请填写回复内容!', {icon : 2});
					return;
				}
				formData.append("againActivityReply", againActivityReply);
				formData.append("id", id);
				formData.append("activityStatus", activityStatus);
				formData.append("itAgain", 1);
				ajax(formData,status);
			});

		}
	}

});