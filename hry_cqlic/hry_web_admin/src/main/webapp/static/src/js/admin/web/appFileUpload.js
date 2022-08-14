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
										appType : {
						validators : {
							notEmpty : {
								message : "应用类型不能为空"
							}
						}
					},
					appVersion : {
						validators : {
							notEmpty : {
								message : "版本号不能为空"
							}
						}
					},

					appFilePath : {
						validators : {
							notEmpty : {
								message : "上传文件不能为空"
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
					url : _ctx + "/web/appfileupload/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appfileuploadlist')
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



            //一件还原
            $("#modify").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if(ids!=undefined&&ids.length==1) {
                    var rows = _table.getRowSelects($("#table"));

                    layer.confirm('你确定要还原为'+rows[0].appVersion+'版本吗？一旦确定当前版本将失效', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        layer.prompt({title: '请填写还原原因', formType: 2},function(val, index){
                            $.ajax({
                                type: "get",
                                url: _ctx + "/web/appfileupload/modify/"+ids,
                                data: {"remark": val},
                                cache: false,
                                dataType: "json",
                                success: function (data) {
                                    if (data) {
                                        if (data.success) {
                                            layer.msg("还原成功", {
                                                icon: 1,
                                            });
                                            var params = $("#table_query_form").serializeJson();
                                            _table.tableQuery($("#table"), params);
                                        }else {
                                            layer.msg(data.msg, {
                                                icon: 2,
                                            })
                                        }
                                    }
                                },
                                error: function (e) {

                                }
                            });
                            layer.close(index);
                        });


                    })

                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
            });
            //修改版本号
            $("#modifyVeision").on("click", function () {
                var ids = _table.getIdSelects($("#table"));

                if(ids!=undefined&&ids.length==1) {
                    var rows = _table.getRowSelects($("#table"));

                    layer.confirm('你确定要对版本号'+rows[0].appVersion+'进行修改吗？', {
                        btn: ['确定','取消'],
                        ids : ids[0]
                    }, function(){
                        layer.prompt({title: '请填写新版本号', formType: 2},function(val, index){
                            $.ajax({
                                type: "get",
                                url: _ctx + "/web/appfileupload/modifyVeision/"+ids,
                                data: {"appVersion": val},
                                cache: false,
                                dataType: "json",
                                success: function (data) {
                                    if (data) {
                                        if (data.success) {
                                            layer.msg("修改成功", {
                                                icon: 1,
                                            });
                                            var params = $("#table_query_form").serializeJson();
                                            _table.tableQuery($("#table"), params);
                                        }else {
                                            layer.msg(data.msg, {
                                                icon: 2,
                                            })
                                        }
                                    }
                                },
                                error: function (e) {

                                }
                            });
                            layer.close(index);
                        });


                    })

                } else {
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
				url : _ctx + "/web/appfileupload/list.do",
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
				},
				{
					title : '应用类型',
					field : 'appType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(data, type, row) {
                        if(data==1){
                            return "IOS";
                        }else{
                            return "Android";
                        }
                    }
				},
				{
					title : '版本号',
					field : 'appVersion',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},

				{
					title : '文件路径',
					field : 'appFilePath',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '二维码',
					field : 'appCodePath',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(data, type, row) {
						return '<div class="imgContainer" style="height: 135px; width: 135px "><img style="height: 130px; width: 130px " src=' + _fileUrl+data + ' class="imgDisplay"></div>';
                    }
				},
				{
					title : '状态',
					field : 'appStatus',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(data, type, row) {
                        if(data==1){
                            return "使用中";
                        }else{
                            return "失效";
                        }
                    }
				},
				{
					title : '操作人',
					field : 'operationUser',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '上传时间',
					field : 'created',
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