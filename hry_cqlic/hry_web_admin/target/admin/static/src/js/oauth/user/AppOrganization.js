define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		// 查看页面--初始化方法
		see : function() {
		},
		// 添加页面--初始化方法
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
					name : {
						validators : {
							notEmpty : {
								message : "名称不能为空"
							}
						}
					},
					orderno : {
						validators : {
							notEmpty : {
								message : "排序号不能为空"
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
					url : _ctx + "/user/apporganization/add",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
						formData.push({
							name : "pid",
							required : false,
							type : "text",
							value : $("#tree_selected_id").val()
						});
						
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
								layer.msg('添加成功!', {
									icon : 1
								});
								loadUrl(_ctx+'/v.do?u=oauth/user/apporganizationtree')
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
					name : {
						validators : {
							notEmpty : {
								message : "名称不能为空"
							}
						}
					},
					orderno : {
						validators : {
							notEmpty : {
								message : "排序号不能为空"
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
					url : _ctx + "/user/apporganization/modify",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
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
								layer.msg('修改成功!', {
									icon : 1
								});
								loadUrl(_ctx+'/v.do?u=oauth/user/apporganizationtree')
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

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/oauth/user/apporganiztion/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/oauth/user/apporganiztion/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/oauth/user/apporganiztion/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/oauth/user/apporganiztion/list" +
				"",
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
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : 'username',
					field : 'username',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : 'password',
					field : 'password',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : 'salt',
					field : 'salt',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : 'isdelete',
					field : 'isdelete',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : 'islock',
					field : 'islock',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : 'created',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : 'modified',
					field : 'modified',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				} ]
			}
			_table.initTable($("#table"), conf);
		},
		// 组织部门页面
		tree : function() {

			var zTree;
			var demoIframe;

			var setting = {
				view : {
					addHoverDom : addHoverDom,
					removeHoverDom : removeHoverDom,
					dblClickExpand : false,
					showLine : true,
					selectedMulti : false
				},
				data : {
					simpleData : {
						enable : true,
						idKey : "id",
						pIdKey : "pId",
						rootPId : ""
					}
				},
				callback : {
					beforeClick : function(treeId, treeNode) {
						$("#tree_selected_id").val(treeNode.trueId);
						$("#tree_selected_name").val(treeNode.name);
						loadUrl2Div(_ctx+'/v.do?u=/oauth/user/appuserlist', 'tree_right');
					}
				}
			};

			function addHoverDom(treeId, treeNode) {
				if (
					($("#removeBtn_"+treeNode.tId)!=undefined&&$("#removeBtn_"+treeNode.tId).length>0)||
					($("#editBtn_"+treeNode.tId)!=undefined&&$("#editBtn_"+treeNode.tId).length>0)||
					($("#addBtn_"+treeNode.tId)!=undefined&&$("#addBtn_"+treeNode.tId).length>0)) {return;}
				
				var sObj = $("#" + treeNode.tId + "_span");
				var addStr = "";
				if(treeNode.type!="department"){
					addStr += "<span class='button add' id='addBtn_" + treeNode.tId + "'  trueid='" + treeNode.trueId + "' title='添加' onfocus='this.blur();'></span>";
				}
				addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "' trueid='" + treeNode.trueId + "'  title='编辑' onfocus='this.blur();' ></span>";
				if(treeNode.type!="root"){
					addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "'  trueid='" + treeNode.trueId + "' title='删除' onfocus='this.blur();'></span>";
				}
				sObj.after(addStr);

				// 删除事件
				var removebtn = $("#removeBtn_" + treeNode.tId);
				if (removebtn) {
					removebtn.bind("click", function() {

						var id = $(this).attr("trueid");
						layer.confirm('你确定要删除吗？', {
							btn : [ '确定', '取消' ],
							id : id
						}, function() {
							$.ajax({
								type : 'post',
								url : _ctx + '/user/apporganization/remove?id=' + id,
								dataType : "json",
								success : function(data) {
									if (data) {
										if (data.success) {
											layer.msg("删除成功", {
												icon : 1
											});
											loadUrl(_ctx+'/v.do?u=/oauth/user/apporganizationtree');
										} else {
											layer.msg(data.msg, {
												icon : 1
											});
										}
									} else {
										layer.msg('删除失败!', {
											icon : 1
										});
									}
								},
								error : function() {

								}
							});
						})

						return false;
					});
				}
				// 编辑事件
				var editbtn = $("#editBtn_" + treeNode.tId);
				if (editbtn){
					editbtn.bind("click", function() {
						// 跳转到修改页面
						$("#tree_selected_id").val($(this).attr("trueid"));
						loadUrl2Div(_ctx + '/user/apporganization/modifysee/' + $(this).attr("trueid"), 'tree_right');
						return false;
					});
				}
				//添加事件
				var addbtn = $("#addBtn_" + treeNode.tId);
				if (addbtn){
					addbtn.bind("click", function() {
						// 跳转到修改页面
						$("#tree_selected_id").val($(this).attr("trueid"));
						loadUrl2Div(_ctx+'/v.do?u=oauth/user/apporganizationadd', 'tree_right');
						return false;
					});
				}
			}
			;

			function removeHoverDom(treeId, treeNode) {
				$("#addBtn_" + treeNode.tId).unbind().remove();
				$("#removeBtn_" + treeNode.tId).unbind().remove();
				$("#editBtn_" + treeNode.tId).unbind().remove();
			}
			;

			var zNodes = [];

			$.ajax({
				type : 'post',
				url : _ctx + '/user/apporganization/loadtree',
				dataType : "json",
				success : function(data) {
					if (data != undefined) {
						for (var i = 0; i < data.length; i++) {

							var node = {
								id : data[i].id,
								pId : data[i].pid,
								name : data[i].name,
								type : data[i].type,
								trueId : data[i].id,
								open : true
							}
							zNodes.push(node);

						}
					}
					// 初始化树
					var t = $("#tree_field");
					t = $.fn.zTree.init(t, setting, zNodes);
				},
				error : function() {
					layer.closeAll();
				}
			});

		}
	}

});