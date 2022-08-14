define(function(require, exports, module) {
	this._table = require("js/base/table");

	var keysWord = ['break','case','catch','continue','default',
		'delete','do','else','finally','for','function','if',
		'in','instanceof','new','return','switch','this','throw',
		'try','typeof','var','void','while','with','insert','update',
		'abstract','boolean','byte','char','class','const','debugger',
		'double','enum','export','extends','final','float','goto','implements',
		'import','int','interface','long','native','package','private','protected',
		'public','short','static','super','synchronized','throws','transient','volatile'];

	module.exports = {
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
					langKey : {
						validators : {
							notEmpty : {
								message : "langKey不能为空"
							},
                            regexp: {
                                regexp: /^[a-zA-Z\$_][a-zA-Z\d_]*$/,
                                message: "允许包含字母、数字、美元符号($)和下划线，但第一个字符不允许是数字，不允许包含空格和其他标点符号"
                            }
						}
					},
					langVal : {
						validators : {
							notEmpty : {
								message : "langVal不能为空"
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
                var langType = $("#langType").val();
                var wordSource = $("#wordSource").val();
				var options = {
					url : _ctx + "/web/applanguage/add.do",
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
						var langkey = $("#langKey").val();
						if ($.inArray(langkey, keysWord) != -1) {
                            layer.msg('非法字符，请重新输入!', {icon : 2});
                            return false;
						}
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('添加成功!', {icon : 1});
                                loadUrl(_ctx + '/web/applanguage/toPage/'+langType+'/'+wordSource);
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
        page : function() {
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

			// 添加页面跳转按钮
			$("#add").on("click", function() {
                var langType = $("#langType").val();
                var wordSource = $("#wordSource").val();
                loadUrl(_ctx + '/web/applanguage/toAdd/' + langType + "/" + wordSource);
			});

			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/applanguage/remove.do");
			});

			// 获取选中标签的简称
            $(".languageQuery").on("click", function () {
                var languageDic = $(this).attr("languageType");
                $("#languageDic").val(languageDic);
                var langType = $("#langType").val();
                var wordSource = $("#wordSource").val();
                var params = {
                	languageDic: languageDic,
					langType: langType,
                    wordSource: wordSource
                };
                //查询
                _table.tableQuery($("#table"), params);
            });

            // 单元格按钮点击事件
            /*window.operateEvents = {
                // 保存功能
                "click .modified_btn": function(e, value, row, index) {
                    // 获取当前行文本框数据
                    var langvalCell = $("#langVal_" + row.id).val();
                    var langTypeCell = $("#langType_" + row.id).val();
                    var langType = $("#langType").val(); // 跳转类型
                    var wordSource = $("#wordSource").val();
                    if (langvalCell) {
                        $.ajax({
                            type: "POST",
                            dataType: "JSON",
                            url: _ctx + "/web/applanguage/save/"+row.id,
                            data : {
                                langValCell : langvalCell,
                                langTypeCell : langTypeCell
                            },
                            cache: false,
                            success: function (data) {
                                if (data != undefined) {
                                    if (data.success) {
                                        layer.msg('保存成功!', {icon : 1});
                                        loadUrl(_ctx + '/web/applanguage/toPage/'+langType+'/'+wordSource);
                                    } else {
                                        layer.msg(data.msg, {icon : 2});
                                    }
                                }
                            }
                        });
                    }
                }
            };*/

			var conf = {
				detail : function(e, index, row, $detail) {
                    var wordSource = $("#wordSource").val();
                    $.ajax({
                        type: "POST",
                        dataType: "JSON",
                        url: _ctx + "/web/applanguage/getCNData.do",
						data: {
                            wordSource: wordSource
						},
                        cache: false,
                        success: function (data) {
                            $.each(data, function(idx, val){
                                if (val.langKey == row.langKey) {
                                    $detail.html(val.langVal);
                                }
                            });
                        }
                    });
				},
				url : _ctx + "/web/applanguage/list.do",
				columns : [ {
						checkbox : true,
						align : 'center',
						valign : 'middle',
						value : "id",
						searchable : false
					},
					{
						title : 'langKey',
						field : 'langKey',
						align : 'left',
						visible : true,
						sortable : false,
						searchable : true
					},
					{
						title : 'langVal',
						field : 'langVal',
						align : 'left',
						visible : true,
						sortable : false,
						searchable : true,
						formatter : function (value, row, index) {
							return "<input type=\"text\" class=\"form-control\" name=\"langValCell\" id=\"langVal_"+row.id+"\" value=\""+value+"\"/>";
						}
					},
					{
						title : 'langType',
						field : 'langType',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true,
                        formatter : function (value, row, index) {
							if (value == null || value == 'null' || value == '' || value == undefined || value == 'undefined') {
								value = "";
							}
                            return "<input type=\"text\" class=\"form-control\" name=\"langTypeCell\" id=\"langType_"+row.id+"\" value='"+value+"'/>";
                        }
					},
					{
						title : '语种',
						field : 'langCode',
						align : 'center',
						visible : true,
						sortable : false,
						searchable : true
					},
                    {
                        title : '操作',
                        field : '',
                        align : 'center',
                        visible : true,
                        sortable : false,
                        searchable : true,
						formatter : function (value, row, index) {
							var trr = "<button id=\"modified_btn_"+row.id+"\" rowId=\""+row.id+"\" class=\"modified_btn btn btn-info-blue\" ><i class=\"glyphicon glyphicon-edit\"></i>保存</button>";
							var languageDic = $("#languageDic").val();
							if(languageDic == "zh_CN" || languageDic==""){
								trr += "&nbsp<button id=\"translate_btn_"+row.id+"\" rowId=\""+row.id+"\" class=\"translate_btn btn btn-info-blue\" ><i class=\"glyphicon glyphicon-edit\"></i>自动翻译</button>";
							}
							return trr;
						}
                    }
				]
			}
			_table.initTable($("#table"), conf);

            $("#table").on('load-success.bs.table',function(data){
                $("#table .modified_btn").each(function () {
                	$(this).on("click", function(){
                		var rowId = $(this).attr("rowId");
                        // 获取当前行文本框数据
                        var langvalCell = $("#langVal_" + rowId).val();
                        var langTypeCell = $("#langType_" + rowId).val();
                        var langType = $("#langType").val(); // 跳转类型
                        var wordSource = $("#wordSource").val();
                        if (langvalCell) {
                            $.ajax({
                                type: "POST",
                                dataType: "JSON",
                                url: _ctx + "/web/applanguage/save/" + rowId,
                                data : {
                                    langValCell : langvalCell,
                                    langTypeCell : langTypeCell
                                },
                                cache: false,
                                success: function (data) {
                                    if (data != undefined) {
                                        if (data.success) {
                                            layer.msg('保存成功!', {icon : 1});
                                            //loadUrl(_ctx + '/web/applanguage/toPage/'+langType+'/'+wordSource);
                                        } else {
                                            layer.msg(data.msg, {icon : 2});
                                        }
                                    }
                                }
                            });
                        }else {
                            layer.msg("langVal不能为空", {icon : 2});
                        }
					});
				});
			});

			$("#table").on('load-success.bs.table',function(data){
				$("#table .translate_btn").each(function () {
					$(this).on("click", function(){
						var rowId = $(this).attr("rowId");
						// 获取当前行文本框数据
						var langvalCell = $("#langVal_" + rowId).val();
						var langTypeCell = $("#langType_" + rowId).val();
						var langType = $("#langType").val(); // 跳转类型
						var wordSource = $("#wordSource").val();
						if (langvalCell) {
							$.ajax({
								type: "POST",
								dataType: "JSON",
								url: _ctx + "/web/applanguage/translate/" + rowId,
								data : {
									langValCell : langvalCell,
									langTypeCell : langTypeCell
								},
								cache: false,
								success: function (data) {
									if (data != undefined) {
										if (data.success) {
											layer.msg('翻译成功!', {icon : 1});
											//loadUrl(_ctx + '/web/applanguage/toPage/'+langType+'/'+wordSource);
										} else {
											layer.msg(data.msg, {icon : 2});
										}
									}
								}
							});
						}
					});
				});

			});

		}
	}

});