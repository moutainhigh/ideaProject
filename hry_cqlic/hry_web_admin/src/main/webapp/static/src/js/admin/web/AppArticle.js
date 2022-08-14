define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
	    init : function(cid, optType) {
            // 初始化文章类型选择框
            $.ajax({
                type : "post",
                url : _ctx + "/web/appcategory/selectlistByCategory.do",
                cache : false,
                dataType : "json",
                data:{
                    cid : cid
                },
                success : function(data) {
                    if(data){
                        if (data.length == 1) {
                            if (optType == 'list') {
                                $("#articleCategor").append("<option value='"+data[0].categoryId+"' selected=\"selected\">"+data[0].categoryName+"</option>");
                            } else {
                                $("#articleCategor").append("<option value='"+data[0].categoryId+"' selected=\"selected\">"+data[0].langName+"</option>");
                            }
                        } else {
                            $("#articleCategor").append("<option value=''>请选择</option>");
                            $.each(data, function(idx, val){
                                if (optType == 'list') {
                                    $("#articleCategor").append("<option value='" + val.categoryId + "' >" + val.categoryName + "</option>");
                                } else {
                                    var categoryid = $("#categoryId").val();
                                    if (categoryid != undefined && categoryid == val.categoryId) {
                                        $("#articleCategor").append("<option value='" + val.categoryId + "' selected=\"selected\">" + val.langName + "</option>");
                                    } else {
                                        $("#articleCategor").append("<option value='" + val.categoryId + "' >" + val.langName + "</option>");
                                    }
                                }
                            });
                        }
                    }else{
                        layer.msg("请求无响应", {icon: 2});
                    }
                },
                error : function(e) {
                    console.log(e);
                }
            });
        },
		//查看页面--初始化方法
		see : function(){
            $("#sysLangId").attr("disabled",'disabled');
            $("#categoryId").attr("disabled",'disabled');
            $("#isStick").attr("disabled",'disabled');
            $("#isOutLink").attr("disabled",'disabled');
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
					title : {
						validators : {
							notEmpty : {
								message : "文章标题不能为空"
							}
						}
					},
                    sysLangId : {
						validators : {
							notEmpty : {
								message : "请选择语种"
							}
						}
					},
                    categoryId : {
						validators : {
							notEmpty : {
								message : "请选择文章类别"
							}
						}
					},
                    writer : {
						validators : {
							notEmpty : {
								message : "文章作者不能为空"
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
					url : _ctx + "/web/apparticle/add.do",
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

                        var kk = $("#isOutLink").val();
                        var content = UE.getEditor("ueditor_content").getContent();
						if (kk == '0' && content == '') {
                            layer.msg('文章内容不能为空', {icon : 2});
							return false;
						}
						if (kk == 1) {
                            if($("#outLink").val() == ""){
                                layer.msg('请填写外链地址', {icon : 2});
                                return ;
                            }else{
                                // 清空ue编辑器
                                UE.getEditor("ueditor_content").setContent('');
                            }
						}
                        var shortContent = $("#shortContent").val();
                        if (shortContent) {
                            //先把中文替换成两个字节的英文，在计算长度
                            var shortContentLen =  shortContent.replace(/[\u0391-\uFFE5]/g,"aa").length;
                            if (shortContentLen > 300) {
                                layer.msg('文章简介不能超过300字符', {icon : 2});
                                return false;
                            }
                        }

					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('添加成功!', {icon : 1});
                                loadUrl2Div(_ctx+'/v.do?u=/admin/web/apparticlelist', 'tree_right');
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
                    title : {
                        validators : {
                            notEmpty : {
                                message : "文章标题不能为空"
                            }
                        }
                    },
                    sysLangId : {
                        validators : {
                            notEmpty : {
                                message : "请选择语种"
                            }
                        }
                    },
                    categoryId : {
                        validators : {
                            notEmpty : {
                                message : "请选择文章类别"
                            }
                        }
                    },
                    writer : {
                        validators : {
                            notEmpty : {
                                message : "文章作者不能为空"
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
					url : _ctx + "/web/apparticle/modify.do",
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

                        var kk = $("#isOutLink").val();
                        var content = UE.getEditor("ueditor_content").getContent();
                        if (kk == '0' && content == '') {
                            layer.msg('文章内容不能为空', {icon : 2});
                            return false;
                        }
                        if (kk == 1) {
                            if($("#outLink").val() == ""){
                                layer.msg('请填写外链地址', {icon : 2});
                                return ;
                            }else{
                                // 清空ue编辑器
                                UE.getEditor("ueditor_content").setContent('');
                            }
                        }
                        var shortContent = $("#shortContent").val();
                        if (shortContent) {
                            //先把中文替换成两个字节的英文，在计算长度
                            var shortContentLen =  shortContent.replace(/[\u0391-\uFFE5]/g,"aa").length;
                            if (shortContentLen > 300) {
                                layer.msg('文章简介不能超过300字符', {icon : 2});
                                return false;
                            }
                        }
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('修改成功!', {icon : 1});
                                loadUrl2Div(_ctx+'/v.do?u=/admin/web/apparticlelist', 'tree_right');
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
			// 查看页面跳转按钮
			$("#see").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    loadUrl2Div(_ctx + '/web/apparticle/see/' + ids[0], 'tree_right');
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    loadUrl2Div(_ctx + '/web/apparticle/modifysee/' + ids[0], 'tree_right');
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if(ids!=undefined&&ids.length>0){
                    layer.confirm('你确定要删除吗？', {
                        btn: ['确定','取消'],
                        ids : ids
                    }, function(){
                        $.ajax({
                            type : "post",
                            url : _ctx + "/web/apparticle/remove.do",
                            cache : false,
                            dataType : "json",
                            data:{
                                ids : ids.join(",")
                            },
                            success : function(data) {
                                if(data!=undefined){
                                    if(data.success){
                                        layer.msg('删除成功', {icon: 1});
                                        loadUrl2Div(_ctx+'/v.do?u=/admin/web/apparticlelist', 'tree_right');
                                    }else{
                                        layer.msg(data.msg, {icon: 2});
                                    }
                                }else{
                                    layer.msg("请求无响应", {icon: 2});
                                }
                            },
                            error : function(e) {
                                console.log(e);
                            }
                        });
                    });
                }else{
                    layer.msg('请选择数据', {icon: 2});
                    return false;
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
				url : _ctx + "/web/apparticle/list?categoryId=" + $("#tree_selected_id").val() + "&pid=" + $("#tree_selected_pid").val(),
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '语种',
					field : 'langType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '文章类别',
					field : 'langName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '文章标题',
					field : 'title',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '是否置顶',
					field : 'isStick',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if(value == 0){
                            return "不置顶"
                        }if(value == 1){
                            return "置顶";
                        }
                        return "错误"
					}
				},
				{
					title : '是否外链',
					field : 'isOutLink',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if(value == 0){
                            return "不外链"
                        }if(value == 1){
                            return "外链";
                        }
                        return "错误"
                    }
				},
				{
					title : '作者',
					field : 'writer',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '点击量',
					field : 'hits',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '时间',
					field : 'created',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
		tree : function () {
            var zTree;
            var demoIframe;

            var setting = {
                view: {
                    dblClickExpand: false,
                    showLine: true,
                    selectedMulti: false
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "pId",
                        rootPId: ""
                    }
                },
                callback: {
                    beforeClick: function (treeId, treeNode) {
                        $("#tree_selected_id").val(treeNode.trueId);
                        $("#tree_selected_name").val(treeNode.name);
                        $("#tree_selected_pid").val(treeNode.preateId);
                        $("#articleContent").html("【"+$("#tree_selected_name").val()+"】");
                        if (treeNode.isPage != '1') { // 文章列表或父节点
                            loadUrl2Div(_ctx + '/v.do?u=admin/web/apparticlelist', 'tree_right');
                        } else if(treeNode.isPage == '1') { // 单页面
                            loadUrl2Div(_ctx + '/web/appcategory/toSinglePage/' + treeNode.trueId, 'tree_right');
                        }
                    }
                }
            };

            var zNodes = [];

            $.ajax({
                type: 'post',
                url: _ctx + '/web/appcategory/loadtree',
                dataType: "json",
                success: function (data) {
                    // 初始化顶级节点
                    zNodes.push({
                        id: "0",
                        pId: "0",
                        name: "文章分类管理",
                        type: "",
                        isPage: "",
                        preateId: "0",
                        trueId: "0",
                        open: true
                    });
                    if (data != undefined) {
                        for (var i = 0; i < data.length; i++) {
                            var node = {
                                id: data[i].id,
                                pId: data[i].preateId,
                                name: data[i].name,
                                type: data[i].type,
								isPage: data[i].isPage,
                                preateId: data[i].preateId,
                                trueId: data[i].id,
                                open: true
                            };
                            zNodes.push(node);
                        }
                    }
                    // 初始化树
                    var t = $("#tree_field");
                    t = $.fn.zTree.init(t, setting, zNodes);
                },
                error: function () {
                    layer.closeAll();
                }
            });
        }
	}

});