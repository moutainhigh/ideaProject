define(function(require, exports, module) {
	this._table = require("js/base/table");
	
	
	require("lib/codeonline/lib/codemirror.css");
	require("lib/codeonline/addon/fold/foldgutter.css");
	
	require("lib/codeonline/lib/codemirror.js");
	require("lib/codeonline/addon/fold/foldcode.js");
	require("lib/codeonline/addon/fold/foldgutter.js");
	require("lib/codeonline/addon/fold/brace-fold.js");
	require("lib/codeonline/addon/fold/indent-fold.js");
	require("lib/codeonline/addon/fold/markdown-fold.js");
	require("lib/codeonline/addon/fold/comment-fold.js");
	require("lib/codeonline/mode/javascript/javascript.js");
	require("lib/codeonline/mode/xml/xml.js");
	require("lib/codeonline/mode/css/css.js");
	require("lib/codeonline/mode/htmlmixed/htmlmixed.js");

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
					name : {
						validators : {
							notEmpty : {
								message : "名称不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "文件类型不能为空"
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
					url : _ctx + "/tpl/tplfile/add.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
						
						//附值pkey
			        	formData.push({
			        		name : "pkey",
			        		required : false,
			        		type : "text",
			        		value : $("#tree_selected_mkey").val()
			        	})
						
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
								loadUrl('/admin/tpl/tplfiletree')
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
					mkey : {
						validators : {
							notEmpty : {
								message : "mkey不能为空"
							}
						}
					},
					pkey : {
						validators : {
							notEmpty : {
								message : "pkey不能为空"
							}
						}
					},
					name : {
						validators : {
							notEmpty : {
								message : "名称不能为空"
							}
						}
					},
					type : {
						validators : {
							notEmpty : {
								message : "文件类型不能为空"
							}
						}
					},
					value : {
						validators : {
							notEmpty : {
								message : "内容不能为空"
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
					url : _ctx + "/tpl/tplfile/modify.do",
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
								layer.msg('修改成功!', {icon : 1});
								loadUrl('/admin/tpl/tplfilelist')
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
				_table.seeRow($("#table"), _ctx + "/tpl/tplfile/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/tpl/tplfile/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/tpl/tplfile/remove.do");
			});

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : _ctx + "/tpl/tplfile/list.do",
				columns : [ {
					field : 'state',
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
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'mkey',
					field : 'mkey',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : 'pkey',
					field : 'pkey',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '名称',
					field : 'name',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '文件类型',
					field : 'type',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '内容',
					field : 'value',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				}
				]
			}
			_table.initTable($("#table"), conf);
		},
		tree : function(){
			
			/**
			 * 由于button是动态增加上去的
			 */
			$("#tree_right_header").on("click","button",function(){
				var codevalue =  window.editor_html.getValue()
				var id = $(this).attr("fileid");
				
				$.ajax({
					type : "post",
					url : _ctx + "/tpl/tplfile/write.do",
					data : {
						id : id,
						value : codevalue
					},
					cache : false,
					dataType : "json",
					success : function(data){
						
						if(data!=undefined){
							if(data.success){
								layer.msg("保存成功",{icon:1});
							}else{
								layer.msg(data.msg, {icon : 1});
							}
						}else{
							layer.msg("请求无响应", {icon : 1});
						}
						
					},
					error : function(e){
						layer.msg("请求失败", {icon : 1});
					}
				});
				
			});
			
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
						if(treeNode.type=="file"){
							$("#tree_selected_mkey").val(treeNode.id);
							//附值右边头部
							$("#tree_right_header").html("<font color='red' >【"+treeNode.name+"】正在编辑...</font>" +
									"<button type='button'  class='btn btn-success pull-right'  fileid = '"+treeNode.trueId+"'  > <i class='fa fa-mail-forward'></i>保存</button>")
							//清空右边，再加上textarea
							$("#tree_right").empty().append("<textarea id='code-html' name='code-html'></textarea>");
							
							$.ajax({
								type : "post",
								url : _ctx + "/tpl/tplfile/read.do?id="+treeNode.trueId,
								cache : false,
								dataType : "json",
								success : function(data){
									
									if(data!=undefined){
										if(data.success){
											var te_html = document.getElementById("code-html");
											te_html.value = data.obj.value;
										
											window.editor_html = CodeMirror.fromTextArea(te_html, {
												mode : "text/html",
												lineNumbers : true,
												lineWrapping : true 
											});
										}else{
											layer.msg(data.msg, {icon : 1});
										}
									}else{
										layer.msg("请求无响应", {icon : 1});
									}
									
								},
								error : function(e){
									layer.msg("请求失败", {icon : 1});
								}
							});
							

							
						}
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
				if(treeNode.type!="file"){
					addStr += "<span class='button add' id='addBtn_" + treeNode.tId + "'  trueid='" + treeNode.trueId + "'  mkey='" + treeNode.id + "' title='添加' onfocus='this.blur();'></span>";
				}
				addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "' trueid='" + treeNode.trueId + "' mkey='" + treeNode.id + "'  title='编辑' onfocus='this.blur();' ></span>";
				if(treeNode.id!="view"){
					addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "'  trueid='" + treeNode.trueId + "' mkey='" + treeNode.id + "' title='删除' onfocus='this.blur();'></span>";
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
								url : _ctx + '/tpl/tplfile/remove.do?id=' + id,
								dataType : "json",
								success : function(data) {
									if (data) {
										if (data.success) {
											layer.msg("删除成功", {
												icon : 1
											});
											loadUrl('/admin/tpl/tplfiletree');
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
						$("#tree_selected_mkey").val($(this).attr("mkey"));
						loadUrl2Div(_ctx + '/user/apporganization/modifysee/' + $(this).attr("trueid") + ".do", 'tree_right');
						return false;
					});
				}
				//添加事件
				var addbtn = $("#addBtn_" + treeNode.tId);
				if (addbtn){
					addbtn.bind("click", function() {
						// 跳转到修改页面
						$("#tree_selected_mkey").val($(this).attr("mkey"));
						loadUrl2Div('admin/tpl/tplfileadd', 'tree_right');
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
				url : _ctx + '/tpl/tplfile/listtree.do',
				dataType : "json",
				success : function(data) {
					if (data != undefined) {
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
					var t = $("#tree");
					t = $.fn.zTree.init(t, setting, zNodes);
				},
				error : function() {
					layer.closeAll();
				}
			});

			
	
		}
	}

});