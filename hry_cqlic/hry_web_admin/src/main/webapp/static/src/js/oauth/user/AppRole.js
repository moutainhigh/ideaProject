define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		// 查看页面--初始化方法
		see : function() {

			// 初始化树
			var loadtree = function() {

				$.ajax({
					type : 'post',
					url : _ctx + '/user/approle/loadmenubyroleid.do?roleid=' + $("#id").val(),
					dataType : "json",
					success : function(data) {
						if (data != undefined) {
							appnames.forEach(function(obj) {
								
								var zTree;
								var setting = {
									view : {
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
									}
								};

								var zNodes = [];
								
								var ndata = eval("data.obj."+obj);
								if (ndata) {
									for (var i = 0; i < ndata.length; i++) {
										var node = {
											id : ndata[i].mkey,
											pId : ndata[i].pkey,
											name : ndata[i].name,
											type : ndata[i].type,
											trueId : ndata[i].id,
											open : true
										}
										zNodes.push(node);
									}
								}

								// 初始化树
								var t = $("#" + obj + "_tree");
								zTree = $.fn.zTree.init(t, setting, zNodes);
							});
						}
					},
					error : function() {
						layer.closeAll();
					}
				});

			}
			loadtree();

		},
		// 添加页面--初始化方法
		add : function() {

			// 初始化树
			var loadtree = function() {
				appnames.forEach(function(obj) {

					$.ajax({
						type : 'post',
						url : _ctx + '/menu/appmenu/loadtreeowner.do?appname=' + obj,
						dataType : "json",
						success : function(data) {
							if (data != undefined) {

								var zTree;
								var setting = {
									view : {
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
										beforeCheck : function(treeId, treeNode) {
										}
									},
									check : {
										autoCheckTrigger : false,
										chkboxType : {
											"Y" : "ps",
											"N" : "s"
										},
										chkStyle : "checkbox",
										enable : true,
										nocheckInherit : false,
										chkDisabledInherit : false,
										radioType : "level"
									}
								};

								var zNodes = [];

								for (var i = 0; i < data.length; i++) {
									var node = {
										id : data[i].mkey,
										pId : data[i].pkey,
										name : data[i].name,
										type : data[i].type,
										trueId : data[i].id,
										open : true
									}
									zNodes.push(node);
								}
							}
							// 初始化树
							var t = $("#" + obj + "_tree");
							zTree = $.fn.zTree.init(t, setting, zNodes);
						},
						error : function() {
							layer.closeAll();
						}
					});

				});
			}
			loadtree();

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
								message : "角色名称不能为空"
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
					url : _ctx + "/user/approle/add.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {

						var oauthids = "";
						apptrees.forEach(function(obj) {
							var treeObj = $.fn.zTree.getZTreeObj(obj);
							var nodes = treeObj.getCheckedNodes(true);
							for (var i = 0; i < nodes.length; i++) {
								oauthids += nodes[i].id;
								//console.log(nodes[i].name)
								if (i != nodes.length - 1) {
									oauthids += ",";
								}
							}
							//不同应用之间加逗号
							if(oauthids!=""){
								oauthids += ",";
							}
						})
						if (oauthids == undefined || oauthids == "") {
							layer.msg('请选择权限!', {
								icon : 2
							});
							return false;
						} else {
							formData.push({
								name : "oauthids",
								required : false,
								type : "text",
								value : oauthids
							});
						}

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
								loadUrl(_ctx+'/v.do?u=oauth/user/approlelist')
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
			
			// 初始化树
			var loadtree = function() {

				$.ajax({
					type : 'post',
					url : _ctx + '/user/approle/findMyResourceAndNohasResource.do?roleid=' + $("#id").val(),
					dataType : "json",
					success : function(data) {
						if (data != undefined) {
							appnames.forEach(function(obj) {
								
								var zTree;
								var setting = {
									view : {
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
									check : {
										autoCheckTrigger : false,
										chkboxType : {
											"Y" : "ps",
											"N" : "s"
										},
										chkStyle : "checkbox",
										enable : true,
										nocheckInherit : false,
										chkDisabledInherit : false,
										radioType : "level"
									}
								};

								var zNodes = [];
								
								var hasdata = eval("data.obj.has."+obj); 
								var nohasdata = eval("data.obj.nohas."+obj);

                                if (nohasdata) {
                                    for (var x = 0; x < nohasdata.length; x++) {
                                        var nhdata = nohasdata[x];
                                        var node = {
                                            id: nhdata.mkey,
                                            pId: nhdata.pkey,
                                            name: nhdata.name,
                                            type: nhdata.type,
                                            trueId: nhdata.id,
                                            open: true
                                        }
                                        if (hasdata) {
                                            for (var i = 0; i < hasdata.length; i++) {
                                                var hdata = hasdata[i];
                                                if (nhdata.mkey == hdata.mkey) {
                                                    node = {
                                                        id: hdata.mkey,
                                                        pId: hdata.pkey,
                                                        name: hdata.name,
                                                        type: hdata.type,
                                                        trueId: hdata.id,
                                                        checked: true,
                                                        open: true
                                                    }
                                                    continue;
                                                }
                                            }
                                        }
                                        zNodes.push(node);
                                    }
                                }
								// 初始化树
								var t = $("#" + obj + "_tree");
								zTree = $.fn.zTree.init(t, setting, zNodes);
							});
						}
					},
					error : function() {
						layer.closeAll();
					}
				});

			}
			loadtree();
			
			
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
								message : "角色名称不能为空"
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
					url : _ctx + "/user/approle/modify.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
						var oauthids = "";
						apptrees.forEach(function(obj) {
							var treeObj = $.fn.zTree.getZTreeObj(obj);
							var nodes = treeObj.getCheckedNodes(true);
							for (var i = 0; i < nodes.length; i++) {
								oauthids += nodes[i].id;
								console.log(nodes[i].name)
								if (i != nodes.length - 1) {
									oauthids += ",";
								}
							}
							//不同应用之间加逗号
							if(oauthids!=""){
								oauthids += ",";
							}
							
						})
						if (oauthids == undefined || oauthids == "") {
							layer.msg('请选择权限!', {
								icon : 2
							});
							return false;
						} else {
							formData.push({
								name : "oauthids",
								required : false,
								type : "text",
								value : oauthids
							});
						}

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
								loadUrl(_ctx+'/v.do?u=/oauth/user/approlelist')
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
				_table.seeRow($("#table"), _ctx + "/user/approle/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/user/approle/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				var ids = _table.getIdSelects($("#table"));
				if(ids!=undefined&&ids.length==1){
					_table.removeRow($("#table"), _ctx + "/user/approle/remove.do");
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
				url : _ctx + "/user/approle/list.do",
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
					title : '角色名称',
					field : 'name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '角色描述',
					field : 'remark',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}, {
					title : '创建时间',
					field : 'created',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}, {
					title : '修改时间',
					field : 'modified',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				} ]
			}
			_table.initTable($("#table"), conf);
		}
	}

});