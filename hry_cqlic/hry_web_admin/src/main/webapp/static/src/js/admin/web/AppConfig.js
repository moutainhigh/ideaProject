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
										configkey : {
						validators : {
							notEmpty : {
								message : "configkey不能为空"
							}
						}
					},
					configname : {
						validators : {
							notEmpty : {
								message : "configname不能为空"
							}
						}
					},
					configdescription : {
						validators : {
							notEmpty : {
								message : "configdescription不能为空"
							}
						}
					},
					datatype : {
						validators : {
							notEmpty : {
								message : "datatype不能为空"
							}
						}
					},
					value : {
						validators : {
							notEmpty : {
								message : "value不能为空"
							}
						}
					},
					typekey : {
						validators : {
							notEmpty : {
								message : "typekey不能为空"
							}
						}
					},
					typename : {
						validators : {
							notEmpty : {
								message : "typename不能为空"
							}
						}
					},
					description : {
						validators : {
							notEmpty : {
								message : "description不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					ishidden : {
						validators : {
							notEmpty : {
								message : "ishidden不能为空"
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
					url : _ctx + "/web/appconfig/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appconfiglist')
							} else {
								layer.msg(data.msg, {icon : 2});
							}
						}
					}

				};
				$("#form").ajaxSubmit(options);
			});
		},
        page : function() {

			//测试实时价格接口
            $("#testRealTimePriceInterface").on("click",function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    title:"正在连接。。。",
                    shadeClose: true, //开启遮罩关闭
                    area: ['280px', '222px'],
                    content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/'+_version+'/img/testCoin.gif" style="margin-top:65px;"></span>'
                });

                $.ajax({
                    type: "post",
                    url: _ctx + "/web/appconfig/testInterfaceRealTime",
                    data: {

                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("接口返回成功: <br />"+data.msg, {
                                    icon: 1,
                                })
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
            })

			//测试多币种实时价格接口
            $("#testMoreRealTime").on("click",function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    title:"正在连接。。。",
                    shadeClose: true, //开启遮罩关闭
                    area: ['280px', '222px'],
                    content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/'+_version+'/img/testCoin.gif" style="margin-top:65px;"></span>'
                });

                $.ajax({
                    type: "post",
                    url: _ctx + "/web/appconfig/testMoreRealTime",
                    data: {

                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("接口返回成功: <br />"+data.msg, {
                                    icon: 1,
                                })
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
            })


			//测试历史数据接口
            $("#hisklinedataconfig").on("click",function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    title:"正在连接。。。",
                    shadeClose: true, //开启遮罩关闭
                    area: ['280px', '222px'],
                    content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/'+_version+'/img/testCoin.gif" style="margin-top:65px;"></span>'
                });

                $.ajax({
                    type: "post",
                    url: _ctx + "/web/appconfig/testHisKlineData",
                    data: {

                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("接口返回成功: <br />"+data.msg, {
                                    icon: 1,
                                })
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
            })




			//测试实时交易对价格接口
			$("#testInterface").on("click",function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    title:"正在连接。。。",
                    shadeClose: true, //开启遮罩关闭
                    area: ['280px', '222px'],
                    content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/'+_version+'/img/testCoin.gif" style="margin-top:65px;"></span>'
                });

                $.ajax({
                    type: "post",
                    url: _ctx + "/web/appconfig/testInterfaceOauth",
                    data: {

                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("接口返回成功: <br />"+data.msg, {
                                    icon: 1,
                                })
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
            })

			//测试非历史数据接口
			$("#testHistoryInterface").on("click",function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    title:"正在连接。。。",
                    shadeClose: true, //开启遮罩关闭
                    area: ['280px', '222px'],
                    content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/'+_version+'/img/testCoin.gif" style="margin-top:65px;"></span>'
                });

                $.ajax({
                    type: "post",
                    url: _ctx + "/web/appconfig/testInterfaceHistoryOauth",
                    data: {

                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("接口返回成功: <br />"+data.msg, {
                                    icon: 1,
                                })
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
            })



            //测试短信接口
            $("#smsConfigTest").on("click",function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    title:"正在连接。。。",
                    shadeClose: true, //开启遮罩关闭
                    area: ['280px', '222px'],
                    content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/'+_version+'/img/testCoin.gif" style="margin-top:65px;"></span>'
                });

                $.ajax({
                    type: "post",
                    url: _ctx + "/web/appconfig/smsConfigTest",
                    data: {

                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("接口返回成功: <br />"+data.msg, {
                                    icon: 1,
                                })
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
            })



            //测试实名认证接口
            $("#juheConfigTest").on("click",function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-demo', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    title:"正在连接。。。",
                    shadeClose: true, //开启遮罩关闭
                    area: ['280px', '222px'],
                    content: '<span style="width:100%;text-align:center;display: inline-block;"><img src="/admin/static/'+_version+'/img/testCoin.gif" style="margin-top:65px;"></span>'
                });

                $.ajax({
                    type: "post",
                    url: _ctx + "/web/appconfig/juheConfigTest",
                    data: {

                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            layer.closeAll();
                            if (data.success) {
                                layer.msg("接口返回成功: <br />"+data.msg, {
                                    icon: 1,
                                })
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
            })

            // 添加提交
            $("#saveSubmit").on("click", function() {

            	var typeKey=  $("#typeKey").val();
                var jsonData = {}
                $(".appconfig").each(function(){

                	var hasUeditor =  $(this).hasClass("ueditorparent");
                	if(hasUeditor){
                        var name = $(this).attr("name");
                        var id = $(this).attr("ueditorid");
                        var value = UE.getEditor(id).getContent();
                        //获得ueditor框的值
                        eval("jsonData."+name+" = '"+value+"'");

					}else{
                        var name = $(this).attr("name");
                        var value = $(this).val();
                        //获得input框的值
                        eval("jsonData."+name+" = '"+value+"'");
					}


                });




                $.ajax({
                    type: "post",
                    url: _ctx + "/web/appconfig/save",
                    data: {
                        jsonData : JSON.stringify(jsonData),
                        typeKey : typeKey
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                                layer.msg("保存成功", {
                                    icon: 1,
                                })
                                loadUrl(_ctx+"/web/appconfig/page/"+typeKey);
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
					configkey : {
						validators : {
							notEmpty : {
								message : "configkey不能为空"
							}
						}
					},
					configname : {
						validators : {
							notEmpty : {
								message : "configname不能为空"
							}
						}
					},
					configdescription : {
						validators : {
							notEmpty : {
								message : "configdescription不能为空"
							}
						}
					},
					datatype : {
						validators : {
							notEmpty : {
								message : "datatype不能为空"
							}
						}
					},
					value : {
						validators : {
							notEmpty : {
								message : "value不能为空"
							}
						}
					},
					typekey : {
						validators : {
							notEmpty : {
								message : "typekey不能为空"
							}
						}
					},
					typename : {
						validators : {
							notEmpty : {
								message : "typename不能为空"
							}
						}
					},
					description : {
						validators : {
							notEmpty : {
								message : "description不能为空"
							}
						}
					},
					saasId : {
						validators : {
							notEmpty : {
								message : "saasId不能为空"
							}
						}
					},
					ishidden : {
						validators : {
							notEmpty : {
								message : "ishidden不能为空"
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
					url : _ctx + "/web/appconfig/modify",
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
								loadUrl(_ctx+'/v.do?u=/admin/web/appconfiglist')
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
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/appconfig/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/appconfig/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/appconfig/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/appconfig/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : 'configid',
					field : 'configid',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'configkey',
					field : 'configkey',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'configname',
					field : 'configname',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'configdescription',
					field : 'configdescription',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'datatype',
					field : 'datatype',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'value',
					field : 'value',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'typekey',
					field : 'typekey',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'typename',
					field : 'typename',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'description',
					field : 'description',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'saasId',
					field : 'saasId',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'ishidden',
					field : 'ishidden',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
        base: function () {
			/*var tkFlag = $("#typeKeyFlag").val();
			$.each($("#lang_tab a.languageQuery"), function(idx, val) {
                $(this).parent('li').removeClass("active");
				var langType = $(val).attr("languagetype");
				if (tkFlag == langType) {
					$(this).parent('li').addClass("active");
                    getDataByLang(tkFlag);
				}
			});*/

            // 获取选中标签的简称
            $(".languageQuery").on("click", function () {
                var typeKey = $(this).attr("languageType");
                getDataByLang(typeKey);
            });

            // 保存提交
            $("#saveSubmit").on("click", function () {
                var typeKey = $("li.active .languageQuery").attr("languageType");
                var jsonData = {}
                $(".appconfig").each(function () {
                    var hasUeditor = $(this).hasClass("ueditorparent");
                    if (hasUeditor) {
                        var name = $(this).attr("name");
                        var id = $(this).attr("ueditorid");
                        var value = UE.getEditor(id).getContent();
                        //获得ueditor框的值
                        eval("jsonData." + name + " = '" + value + "'");
                    } else {
                        var name = $(this).attr("name");
                        var value = $(this).val();
                        //获得input框的值
                        eval("jsonData." + name + " = '" + value + "'");
                    }
                });

                $.ajax({
                    type: "post",
                    url: _ctx + "/web/appconfig/save",
                    data: {
                        jsonData: JSON.stringify(jsonData),
                        typeKey: typeKey
                    },
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            if (data.success) {
                                layer.msg("保存成功", {
                                    icon: 1,
                                })
                                loadUrl(_ctx + "/web/appconfig/toBasePage/" + typeKey);
                            } else {
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
        }
	}

	function getDataByLang(typeKey){
		//查询
        $.ajax({
            type: "post",
            url: _ctx + "/web/appconfig/getBaseConfigData",
            data: {
                typeKey : typeKey
            },
            cache: false,
            dataType: "json",
            success: function (data) {
                if (data) {
                    $("#configTable").empty();
                    var html = "<thead>\n" +
                        "                <tr>\n" +
                        "                    <th>配置名称</th>\n" +
                        "                    <th>配置参数</th>\n" +
                        "                </tr>\n" +
                        "                </thead><tbody>";
                    $.each(data, function (idx, val) {
                    	var configkey = val.configkey;
                        var configval = val.value == null ? "" : val.value;
                        html += "<tr>"
                            + "	<td>" + val.configname + "</td>"
                            + "	<td>";
                        if (val.datatype == '1') {
                            html += "<input class=\"form-control appconfig\" type=\"text\" name=\"" + configkey + "\" value=\"" + configval + "\">";
                        } else if (val.datatype == '2') {
                            var selectHtml = "<select name=\"" + configkey + "\" id=\"" + configkey + "\" class=\"form-control appconfig\">";
                            if (configval == '1') {
                                selectHtml += "<option selected=\"selected\" value=\"1\">否</option><option value=\"0\">是</option>";
                            } else {
                                selectHtml += "<option value=\"1\">否</option><option selected=\"selected\" value=\"0\">是</option>";
                            }
                            selectHtml += "</select>";
                            html += selectHtml;
                        } else if (val.datatype == '3') {
                            html += "<div class=\"hryUpload\">"
                                + "	<div class=\"hry_inputBox\">"
                                + "		<input class=\"form-control hryUpload_filePath appconfig\" type=\"hidden\" name=\"" + configkey + "\" value=\"" + configval + "\">"
                                + "		<button type=\"button\" class=\"btn btn-primary btn-block\">上传图片</button>"
                                + "	</div>"
                                + "	<div class=\"hry_imgBox\"></div>"
                                + "</div>";
                        } else if (val.datatype == '4') {
                            html += "<div name=\"" + configkey + "\" ueditorid=\"ueditor_" + configkey + "_" + val.configid + "\" class=\"appconfig ueditorparent\">"
                                + "	<textarea id=\"ueditor_" + configkey + "_" + val.configid + "\" class=\"ueditor\" style=\"width:900px;height:300px;\">" + configval + "</textarea>"
                                + "</div>";
                        }
                        html += "</td></tr>";
                    });
                    html += "</tbody>";
                    $("#configTable").append(html);
                    $(".ueditor").each(function () {
                        UE.delEditor($(this).attr("id"));
                        UE.getEditor($(this).attr("id"), {
                            autoHeightEnabled: false
                        });
                    })
                }
            }
        });
	}
});