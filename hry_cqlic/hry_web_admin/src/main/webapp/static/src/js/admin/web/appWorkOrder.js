define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		see : function(){

		},
		// 初始化UE
		initUE: function(){
            $(".ueditor").each(function () {
                UE.getEditor($(this).attr("id"),{
                    autoHeightEnabled: false
                });
            });
		},
		//初始化方法
		init : function(){
            setTimeout(function(){
                // 回复方式为系统消息
                if ($("#replyMode").val() == '0') {
                    UE.getEditor('replyContent_ue').execCommand('insertHtml', $('#sysHtml').html());
                } else {
                    // 回复方式为电话回复
                    UE.getEditor('replyContent_ue').execCommand('insertHtml', $('#telHtml').html());
                }
			}, 500);

            $("#replyMode").on("change", function(){
                UE.getEditor('replyContent_ue').setContent('');
            	if ($(this).val() == '0') {
                    UE.getEditor('replyContent_ue').execCommand('insertHtml', $('#sysHtml').html());
				} else {
                    UE.getEditor('replyContent_ue').execCommand('insertHtml', $('#telHtml').html());
				}
			});
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
                    replyMode : {
                        validators : {
                            notEmpty : {
                                message : "回复方式不能为空"
                            }
                        }
                    },

                    languageDic : {
                        validators : {
                            notEmpty : {
                                message : "语言不能为空"
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
					url : _ctx + "/web/appworkorder/add.do",
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
								loadUrl(_ctx+'/language.do?u=/admin/web/appworkorderlist')
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
					replyMode : {
						validators : {
							notEmpty : {
								message : "回复方式不能为空"
							}
						}
					},
				/*	replyContent : {
						validators : {
							notEmpty : {
								message : "回复内容不能为空"
							}
						}
					},*/
					languageDic : {
						validators : {
							notEmpty : {
								message : "语言不能为空"
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
					url : _ctx + "/web/appworkorder/modify.do",
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
						if(UE.getEditor('replyContent_ue').getContent() == ''){
                            layer.msg('回复内容不能为空!', {icon : 2});
                            return false;
						}
						$("#replyContent_hide").val(UE.getEditor('replyContent_ue').getContent());
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('受理成功!', {icon : 1});
								loadUrl(_ctx+'/language.do?u=/admin/web/appworkorderlist')
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
            $("#table_reset").on("click", function() {
                $("#table_query_form")[0].reset();
            });
            //查询按钮
            $("#table_query").on("click", function() {
                var params=  $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"),params);
            });
			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/web/appworkorder/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
                var rows = _table.getRowSelects($("#table"));
                if (rows[0].state == 1) {
                    layer.msg("工单已被受理", {icon: 2 })
                }else{
                    _table.seeRow($("#table"), _ctx + "/web/appworkorder/modifysee");
                }

			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/web/appworkorder/remove.do");
			});
			//语言切换
            $(".languageQuery").on("click", function() {
                var languageDic=$(this).attr("languageType");
                var params=  {languageDic :languageDic};
                $("#languageDic").val(languageDic);
                //查询
                _table.tableQuery($("#table"),params);
            });
			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/web/appworkorder/list.do",
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
					title : 'customerId',
					field : 'customerId',
					align : 'center',
					visible : false,
					sortable : false,
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
				/*{
					title : '排序',
					field : 'sort',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},*/
				{
					title : '工单编号',
					field : 'workOrderNo',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'categoryId',
					field : 'categoryId',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : '类型',
					field : 'categoryName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '工单内容',
					field : 'workContent',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '状态',
					field : 'state',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter : function(data, type, row) {
                        if(data==1){
                            return "已受理";
                        }else{
                            return "未受理";
                        }
                    }
				},
				{
					title : 'replyMode',
					field : 'replyMode',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},

				{
					title : 'replyContent',
					field : 'replyContent',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : 'language',
					field : 'language',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : 'processTime',
					field : 'processTime',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				},
				{
					title : 'languageDic',
					field : 'languageDic',
					align : 'center',
					visible : false,
					sortable : false,
					searchable : false
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});
