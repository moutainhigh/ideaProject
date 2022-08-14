define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
	    // 初始化页面类别
	    initPage : function(type) {
            if (type) {
                if (type == '2') {
                    $("#isPageSelect").append("<option value=''>请选择</option><option value='0'>文章列表</option><option value='1'>单页面</option>");
                } else {
                    $("#isPageSelect").append("<option value='' selected>请选择</option>");
                }
            } else {
                $("#isPageSelect").append("<option value='2' selected >父节点</option>");
            }

        },
		//添加页面--初始化方法
		add : function() {
		    // 排序验证
            $("#sort").blur(function () {
                var sort = $(this).val();
                var categoryPid = $("#preateId").val();
                $.ajax({
                    type: "POST",
                    dataType: "JSON",
                    url: _ctx + "/web/appcategory/validSort.do",
                    cache: false,
                    data : {
                        "sort" : sort,
                        "categoryPid" : categoryPid
                    },
                    success: function (data) {
                        if (data != undefined) {
                            if (!data.success) {
                                $("#sort").attr("errorFlag", "err");
                            } else {
                                $("#sort").removeAttr("errorFlag");
                            }
                        }
                    }
                });
            });

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
								message : "类别名称不能为空"
							}
						}
					},
                    sort : {
						validators : {
							notEmpty : {
								message : "排序不能为空"
							}
						}
					},
                    isPage : {
                        validators : {
                            notEmpty : {
                                message : "请选择页面类别"
                            }
                        }
                    },
                    zh_CN : {
                        validators : {
                            notEmpty : {
                                message : "简体中文内容不能为空"
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
                // 语言参数封装
                var languages = new Array();
                $("input[ltype='categoryLang']").each(function(idx,val){
                    var id = $(val).attr("id");
                    var text = $(val).val();
                    languages.push({"id":id,"text":text});
                });
				$("#sysLanguages").val(JSON.stringify(languages));

				var options = {
					url : _ctx + "/web/appcategory/add.do",
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

                        var reg = /^[1-9]\d*$/;
						var sort = $("#sort").val();
						var errFlag = $("#sort").attr("errorFlag");
						if (!reg.test(sort)) {
                            layer.msg('请输入数字格式的排序', {icon : 2});
                            return false;
                        }

                        if (errFlag == "err") {
                            layer.msg('排序已存在，请重新输入', {icon : 2});
                            return false;
                        }

					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('添加成功!', {icon : 1});
                                loadUrl(_ctx + '/v.do?u=admin/web/appcategorytree');
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
            $("#isPage").attr("disabled","disabled");

            // 排序验证
            $("#sort").blur(function () {
                var sort = $(this).val();
                var categoryPid = $("#preateId").val();
                $.ajax({
                    type: "POST",
                    dataType: "JSON",
                    url: _ctx + "/web/appcategory/validSort.do",
                    cache: false,
                    data : {
                        "sort" : sort,
                        "categoryPid" : categoryPid
                    },
                    success: function (data) {
                        if (data != undefined) {
                            if (!data.success) {
                                //layer.msg(data.msg, {icon : 2});
                                $("#sort").attr("errorFlag", "err");
                            } else {
                                $("#sort").removeAttr("errorFlag");
                            }
                        }
                    }
                });
            });

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
								message : "类别名称不能为空"
							}
						}
					},
                    sort : {
						validators : {
							notEmpty : {
								message : "排序不能为空"
							}
						}
					},
					zh_CN : {
                        validators: {
                            notEmpty: {
                                message: "简体中文不能为空"
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

                // 语言参数封装
                var languages = new Array();
                //ltype="categoryLang"
                $("input[ltype='categoryLang']").each(function(idx,val){
                    var id = $(val).attr("id");
                    var text = $(val).val();
                    languages.push({"id":id,"text":text});
                });
                $("#sysLanguages").val(JSON.stringify(languages));

				var options = {
					url : _ctx + "/web/appcategory/modify.do",
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

                        var reg = /^[1-9]\d*$/;
                        var sort = $("#sort").val();
                        var errFlag = $("#sort").attr("errorFlag");
                        if (!reg.test(sort)) {
                            layer.msg('请输入数字格式的排序', {icon : 2});
                            return false;
                        }

                        if (errFlag == "err") {
                            layer.msg('排序已存在，请重新输入', {icon : 2});
                            return false;
                        }
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('修改成功!', {icon : 1});
                                loadUrl2Div(_ctx + '/v.do?u=/admin/web/appcategorylist', 'tree_right');
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
		list : function(categoryId) {

			/*// 查看页面跳转按钮
			$("#singlePage").on("click", function() {
                var rows = _table.getRowSelects($("#table"));
                var ids = _table.getIdSelects($("#table"));
                if(ids.length==0){
                    layer.msg('请选择数据', {icon : 2});
                    return false;
                }else if(ids.length>1){
                    layer.msg('只能选择一条数据', {icon : 2});
                    return false;
                }else if(rows[0].isPage != '1'){
                    layer.msg('只能选择单页面类型数据', {icon : 2});
                    return false;
                }else{
                    loadUrl2Div(_ctx + '/web/appcategory/toSinglePage/' + ids[0], 'tree_right');
                }
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

			// 修改页面跳转按钮
			$("#modify").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if (ids != undefined && ids.length == 1) {
                    loadUrl2Div(_ctx + '/web/appcategory/modifysee/' + ids[0], 'tree_right');
                } else {
                    layer.msg('请选择一条数据', {icon: 2});
                }
			});

			// 删除按钮点击事件
			$("#remove").on("click", function() {
                var ids = _table.getIdSelects($("#table"));
                if(ids.length==0){
                    layer.msg('请选择数据', {icon : 2});
                    return false;
                }else{
                    layer.confirm("确定修改该条数据？", {
                        btn: ['确定', '取消'], //按钮
                        ids: ids
                    }, function () {
                        layer.closeAll();
                        $.ajax({
                            type: "POST",
                            dataType: "JSON",
                            url: _ctx + "/web/appcategory/remove/"+ids[0],
                            cache: false,
                            success: function (data) {
                                if (data != undefined) {
                                    if (data.success) {
                                        layer.msg('删除成功!', {icon : 1});
                                        loadUrl2Div(_ctx + '/v.do?u=/admin/web/appcategorylist', 'tree_right');
                                    } else {
                                        layer.msg(data.msg, {icon : 2});
                                    }
                                }
							}
                		});
                    });
                }
			});

			// 动态拼接列
            $.ajax({
                type: "POST",
                dataType: "JSON",
                url: _ctx + "/web/appcategory/getSysLanguage.do",
                cache: false,
                success: function (data) {
                    var columns = new Array();
                    if (data) {
                        columns.push({
							checkbox : true,
							align : 'center',
							valign : 'middle',
							value : "id",
							searchable : false
						},
						{
							title : '类型',
							field : 'isPage',
							align : 'center',
							visible : true,
							sortable : false,
							searchable : true,
                            formatter: function (value, row, index) {
                                var str = "";
                                if (row.isPage == '0'){
                                    str = "文章列表";
                                } else if(row.isPage == '1') {
                                    str = "单页面"
                                } else if(row.isPage == '2') {
                                    str = "/"
                                }
                                return str;
                            }
						});
                        $.each(data, function (idx, val) {
                            columns.push({
                                title : val.name,
                                field : val.value,
                                align : 'center',
                                visible : true,
                                sortable : false,
                                searchable : true
                            });
                        });
                        columns.push({
                            title : '创建时间',
                            field : 'created',
                            align : 'center',
                            visible : true,
                            sortable : false,
                            searchable : true
                        });
                        var conf = {
                            detail : function(e, index, row, $detail) {
                                var html = [];
                                $.each(row, function(key, value) {
                                    html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                                });
                                $detail.html(html.join(''));
                            },
                            url : _ctx + "/web/appcategory/list?categoryId=" + categoryId,
                            columns : columns
                        }
                        _table.initTable($("#table"), conf);
                    }
                }
            });
		},
		// 单页面内容
        singlePage : function(){
            // 保存提交
            $("#addSubmit").on("click", function() {
                var jsonData = {};
                $(".appconfig").each(function(){
                    var hasUeditor =  $(this).hasClass("ueditorparent");
                    if(hasUeditor) {
                        var id = $(this).attr("ueditorid");
                        var value = UE.getEditor(id).getContent();
                        //获得ueditor框的值
                        eval("jsonData." + id + " = '" + value + "'");
                    }
                });

                $.ajax({
                    type: "post",
                    url: _ctx + "/web/appcategory/saveLangName.do",
                    data: {
                        categoryId : $("#categoryId").val(),
                        jsonData : JSON.stringify(jsonData)
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                                layer.msg("保存成功", {
                                    icon: 1,
                                });
                                loadUrl2Div(_ctx + '/web/appcategory/toSinglePage/' + $("#categoryId").val(), 'tree_right');
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
            });
		},
        tree : function () {
            var zTree;
            var demoIframe;

            var setting = {
                view: {
                    addHoverDom: addHoverDom,
                    removeHoverDom: removeHoverDom,
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
                        $("#tree_selected_isPage").val(treeNode.isPage);
                        $("#articleCategory").html("【" + treeNode.name + "】");
                        loadUrl2Div(_ctx + '/v.do?u=/admin/web/appcategorylist', 'tree_right');
                    }
                }
            };

            function addHoverDom(treeId, treeNode) {
                if (($("#removeBtn_" + treeNode.tId) != undefined && $("#removeBtn_" + treeNode.tId).length > 0) ||
                    ($("#editBtn_" + treeNode.tId) != undefined && $("#editBtn_" + treeNode.tId).length > 0) ||
                    ($("#addBtn_" + treeNode.tId) != undefined && $("#addBtn_" + treeNode.tId).length > 0)) {
                    return;
                }

                var sObj = $("#" + treeNode.tId + "_span");
                var addStr = "";
                if (treeNode.preateId == '0') {
                    addStr += "<span class='button add' id='addBtn_" + treeNode.tId + "'  trueid='" + treeNode.trueId + "' pId='" + treeNode.preateId + "' isPage='" + treeNode.isPage + "' title='添加' onfocus='this.blur();'></span>";
                }
                if (treeNode.id != '0') {
                    addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "' trueid='" + treeNode.trueId + "' pId='" + treeNode.preateId + "' isPage='" + treeNode.isPage + "' title='编辑' onfocus='this.blur();' ></span>";
                    addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "'  trueid='" + treeNode.trueId + "' pId='" + treeNode.preateId + "' isPage='" + treeNode.isPage + "' title='删除' onfocus='this.blur();'></span>";
                }
                sObj.after(addStr);

                // 删除事件
                var removebtn = $("#removeBtn_" + treeNode.tId);
                if (removebtn) {
                    removebtn.bind("click", function () {
                        var id = $(this).attr("trueid");
                        layer.confirm('你确定要删除吗？', {
                            btn: ['确定', '取消'],
                            id: id
                        }, function () {
                            $.ajax({
                                type: 'post',
                                url: _ctx + '/web/appcategory/remove/' + id,
                                dataType: "json",
                                success: function (data) {
                                    if (data) {
                                        if (data.success) {
                                            layer.msg("删除成功", {
                                                icon: 1
                                            });
                                            loadUrl(_ctx + '/v.do?u=admin/web/appcategorytree');
                                        } else {
                                            layer.msg(data.msg, {
                                                icon: 2
                                            });
                                        }
                                    } else {
                                        layer.msg('删除失败!', {
                                            icon: 2
                                        });
                                    }
                                },
                                error: function () {
                                }
                            });
                        });

                        return false;
                    });
                }

                // 编辑事件
                var editbtn = $("#editBtn_" + treeNode.tId);
                if (editbtn) {
                    editbtn.bind("click", function () {
                        // 跳转到修改页面
                        $("#tree_selected_id").val($(this).attr("trueid"));
                        $("#tree_selected_name").val($("#" + treeNode.tId + "_span").text());
                        $("#tree_selected_pid").val($(this).attr("pId"));
                        $("#tree_selected_isPage").val($(this).attr("isPage"));
                        $("#articleCategory").html("【" + $("#" + treeNode.tId + "_span").text() + "】");
                        loadUrl2Div(_ctx + '/web/appcategory/modifysee/' + $(this).attr("trueid"), 'tree_right');
                        return false;
                    });
                }

                //添加事件
                var addbtn = $("#addBtn_" + treeNode.tId);
                if (addbtn) {
                    addbtn.bind("click", function () {
                        // 跳转到修改页面
                        $("#tree_selected_id").val($(this).attr("trueid"));
                        $("#tree_selected_name").val($(this).prev('span').text());
                        $("#tree_selected_pid").val($(this).attr("pId"));
                        $("#tree_selected_isPage").val($(this).attr("isPage"));
                        $("#articleCategory").html("【" + $(this).prev('span').text() + "】");
                        loadUrl2Div(_ctx + '/web/appcategory/toAdd.do', 'tree_right');
                        return false;
                    });
                }
            }

            function removeHoverDom(treeId, treeNode) {
                $("#addBtn_" + treeNode.tId).unbind().remove();
                $("#removeBtn_" + treeNode.tId).unbind().remove();
                $("#editBtn_" + treeNode.tId).unbind().remove();
            }

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