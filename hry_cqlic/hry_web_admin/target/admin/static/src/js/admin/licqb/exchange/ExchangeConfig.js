define(function(require, exports, module) {
	this._table = require("js/base/table");
	function handleConfig(type) {
		var ids = $.map($("#table").bootstrapTable('getSelections'), function(row) {
			return row.id
		});
		if (ids != undefined && ids.length == 1) {
			$.ajax({
				type : "post",
				url :  _ctx + "/licqb/exchange/exchangeconfig/handleConfig/"+ids[0]+"?type="+type,
				cache : false,
				//async :false,
				dataType : "json",
				success : function(data) {
					if (data.success){
						layer.msg("操作成功！", {icon : 1, time: 1500});
						loadUrl( _ctx+'/v.do?u=/admin/licqb/exchange/exchangeconfiglist')
					} else {
						layer.msg(data.msg, {icon : 2});
					}
				}
			});
		}else{
			layer.msg('请选择一条数据', {icon: 2});
		}
	}
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
										itemName : {
						validators : {
							notEmpty : {
								message : "项目名称不能为空"
							}
						}
					},
					payCoinCode : {
						validators : {
							notEmpty : {
								message : "支付币种--平台币不能为空"
							}
						}
					},
					gainCoinCode : {
						validators : {
							notEmpty : {
								message : "兑换币种不能为空"
							}
						}
					},
					ratio : {
						validators : {
							notEmpty : {
								message : "兑换汇率不能为空"
							}
						}
					},
					totalNum : {
						validators : {
							notEmpty : {
								message : "本局项目兑换总数不能为空"
							}
						}
					},
					singleMinNum : {
						validators : {
							notEmpty : {
								message : "单次购买最低数量不能为空"
							}
						}
					},
					soloMaxNum : {
						validators : {
							notEmpty : {
								message : "单人本局最大购买值不能为空"
							}
						}
					},
					itemStartDate : {
						validators : {
							notEmpty : {
								message : "项目启动时间不能为空"
							}
						}
					},
					gainStartDate : {
						validators : {
							notEmpty : {
								message : "兑换开始时间不能为空"
							}
						}
					},
					gainEndDate : {
						validators : {
							notEmpty : {
								message : "兑换结束时间不能为空"
							}
						}
					},
					hasChangeRatio : {
						validators : {
							notEmpty : {
								message : "已兑换比例不能为空"
							}
						}
					},
				},
				// bv校验通过则提交
				submitHandler : function(validator, form, submitButton) {
				}
			});
			// 添加提交
			$("#addSubmit").on("click", function() {
				var options = {
					url : _ctx + "/licqb/exchange/exchangeconfig/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/exchange/exchangeconfiglist')
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
					itemName : {
						validators : {
							notEmpty : {
								message : "项目名称不能为空"
							}
						}
					},
					payCoinCode : {
						validators : {
							notEmpty : {
								message : "平台币不能为空"
							}
						}
					},
					gainCoinCode : {
						validators : {
							notEmpty : {
								message : "兑换币种不能为空"
							}
						}
					},
					ratio : {
						validators : {
							notEmpty : {
								message : "兑换汇率不能为空"
							}
						}
					},
					totalNum : {
						validators : {
							notEmpty : {
								message : "本局项目兑换总数不能为空"
							}
						}
					},
					singleMinNum : {
						validators : {
							notEmpty : {
								message : "单次购买最低数量不能为空"
							}
						}
					},
					soloMaxNum : {
						validators : {
							notEmpty : {
								message : "单人本局最大购买值不能为空"
							}
						}
					},
					itemStartDate : {
						validators : {
							notEmpty : {
								message : "项目启动时间不能为空"
							}
						}
					},
					gainStartDate : {
						validators : {
							notEmpty : {
								message : "兑换开始时间不能为空"
							}
						}
					},
					gainEndDate : {
						validators : {
							notEmpty : {
								message : "兑换结束时间不能为空"
							}
						}
					},
					hasChangeRatio : {
						validators : {
							notEmpty : {
								message : "已兑换比例不能为空"
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
					url : _ctx + "/licqb/exchange/exchangeconfig/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/licqb/exchange/exchangeconfiglist')
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
			$("#add").on("click", function() {
				loadUrl(_ctx + "/licqb/exchange/exchangeconfig/addConfigFtl");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/licqb/exchange/exchangeconfig/modifysee");
			});
			/*// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/licqb/exchange/exchangeconfig/remove.do");
			});*/
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
			// 开启
			$("#open").on("click", function () {
				handleConfig(1);
			});
			// 关闭
			$("#close").on("click", function () {
				handleConfig(2);
			});


			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/licqb/exchange/exchangeconfig/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '项目名称',
					field : 'itemName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '平台币',
					field : 'payCoinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '兑换币种',
					field : 'gainCoinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '兑换汇率',
					field : 'ratio',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '兑换总数',
					field : 'totalNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '单次购买最低数量',
					field : 'singleMinNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '单人本局最大购买值',
					field : 'soloMaxNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '项目预热时间',
					field : 'itemStartDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '兑换开始时间',
					field : 'gainStartDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '兑换结束时间',
					field : 'gainEndDate',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '已兑换比例',
					field : 'hasChangeRatio',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value) {
						return parseFloat(value)
					}
				},
				{
					title : '排序',
					field : 'sort',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '状态',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						if(0 == value){
							return "关闭";
						}else if(1 == value){
							return "开启";
						}
						return "-";
					}
				},
				{
					title : '上传图片',
					field : 'id',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
						var html = '<a class="btn btn-default btn-xs glyphicon glyphicon-upload" href="javascript:void(0);" name="uploading" id="'+value+'" title="上传">上传</a>';
						return html;
					}
				},
				],
				success : function() {
					$("a[name='uploading']").on("click", function () {
						var id = $(this).attr("id");
						loadUrl(_ctx+"/licqb/exchange/exchangeconfig/uploadingImage/"+id);
					});
				}
			}
			_table.initTable($("#table"), conf);
		},
		// 保存配置图片
		updateImage : function() {
			$("#submitImage").on("click", function() {
				// 创建json集合
				var arrayList = [];
				var langKey = $("#langKey").val();
				$.each(langKey.split(","), function(i,val){
					var item = {}
					var id = '';
					var image = '';
					var imageTwo = '';
					var imageThree = '';
					$("input[name='"+val+"']").each(function(j,b){
						var img = $(this).attr("value");
						id = $(this).attr("id");
						if (j == 0 && img != undefined) {
							image = img;
						} else if (j == 1 && img != undefined) {
							imageTwo = img;
						} else if (j == 2 && img != undefined) {
							imageThree = img;
						}
					})
					item.id = id;
					item.image = image;
					item.imageTwo = imageTwo;
					item.imageThree = imageThree;
					arrayList.push(item);
				});
				var imageList = JSON.stringify(arrayList)
				$.ajax({
					type : "post",
					url :  _ctx + "/licqb/exchange/exchangeconfig/updateImage?imageList="+imageList,
					cache : false,
					//async :false,
					dataType : "json",
					success : function(data) {
						if (data.success){
							layer.msg("操作成功！", {icon : 1});
						} else {
							layer.msg("操作失败！", {icon : 2});
						}
					}
				});
			})
		}
	}

});