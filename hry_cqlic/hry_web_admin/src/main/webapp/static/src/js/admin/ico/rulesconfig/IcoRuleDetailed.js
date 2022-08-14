define(function(require, exports, module) {
	this._table = require("js/base/table");
    this.sele = require("js/base/HrySelect");
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
										periods : {
						validators : {
							notEmpty : {
								message : "期数不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种code不能为空"
							}
						}
					},
					saleNum : {
						validators : {
							notEmpty : {
								message : "可售数量不能为空"
							},regexp: {
                                regexp: /^\+?[1-9][0-9]*$/,
                                message: '可售数量参数错误'
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
					url : _ctx + "/ico/rulesconfig/icoruledetailed/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/ico/rulesconfig/icoruledetailedlist')
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
				_table.seeRow($("#table"), _ctx + "/ico/rulesconfig/icoruledetailed/see");
			});

            //发行
            $("#issue").on("click",function(){
                var ids = _table.getIdSelects($("#table"));
                if(ids!=undefined&&ids.length==1){
                    layer.alert('',
                        {icon:2,
                            title:'操作确认',
                            content:'您确定要这样操作吗？',
                            closeBtn:1},function(index){
                            $.ajax({
                                type : "post",
                                url : _ctx + "/ico/rulesconfig/icoruledetailed/issue.do",
                                data : {
                                    id : ids[0],
                                    issue:1
                                },
                                cache : false,
                                dataType : "json",
                                success : function(data) {
                                    if(data){
                                        if(data.success){
                                            layer.msg(data.msg, {icon: 1,time:1500});
                                            var opt = {
                                                url : _ctx + "/ico/rulesconfig/icoruledetailed/list.do"
                                            };
                                            _table.refreshTable($("#table"), opt);
                                        }else{
                                            layer.msg(data.msg, {icon: 2,time:1500});
                                        }
                                    }else{
                                        layer.msg("操作失败", {icon: 2,time:1500})
                                    }
                                },
                                error : function(e) {

                                }
                            });

                        });
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
				url : _ctx + "/ico/rulesconfig/icoruledetailed/list.do",
				columns : [ {
					checkbox : true,
					align : 'center',
					valign : 'middle',
					value : "id",
					searchable : false
				},
				{
					title : '期数',
					field : 'periods',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '总数量',
					field : 'totalNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '可售剩余数量',
					field : 'saleSurplusNum',
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
                    formatter: function (value, row, index) {
                        var aa = sele.getKeyAndVal("yesno",value);
						return aa;
                    }
				}
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});