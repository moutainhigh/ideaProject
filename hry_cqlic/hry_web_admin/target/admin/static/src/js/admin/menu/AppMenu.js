define(function(require, exports, module) {
	this._table = require("js/base/table");

	/**
	 * 应用模块单击事件
	 */
	var clickload = function(key) {
		var zTree;
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
					loadUrl2Div(_ctx + "/menu/appmenu/modifyview/" + treeNode.trueId , 'tree_right');
				}
			}
		};

		function addHoverDom(treeId, treeNode) {
			if (($("#removeBtn_" + treeNode.tId) != undefined && $("#removeBtn_" + treeNode.tId).length > 0) || ($("#editBtn_" + treeNode.tId) != undefined && $("#editBtn_" + treeNode.tId).length > 0) || ($("#addBtn_" + treeNode.tId) != undefined && $("#addBtn_" + treeNode.tId).length > 0)) {
				return;
			}

			var sObj = $("#" + treeNode.tId + "_span");
			var addStr = "";
			if(treeNode.type!="button"){
				addStr += "<span class='button add' id='addBtn_" + treeNode.tId + "' mkey='" + treeNode.id+"' trueid='" + treeNode.trueId + "'  title='添加' onfocus='this.blur();'></span>";
			}
			if(treeNode.name!="root"){
				addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "' mkey='" + treeNode.id+"' trueid='" + treeNode.trueId + "' title='删除' onfocus='this.blur();'></span>";
			}
			sObj.after(addStr);

			// 删除事件
			var removebtn = $("#removeBtn_" + treeNode.tId);
			if (removebtn) {
				removebtn.bind("click", function() {

                    var nodeName = $.fn.zTree.getZTreeObj("tree_center").getNodeByTId($(this).attr("id")).name

					var id = $(this).attr("trueid");
					layer.confirm('你确定要删除【'+nodeName+'】吗？', {
						btn : [ '确定', '取消' ],
						id : id
					}, function() {

						$.ajax({
							type : 'post',
							url : _ctx + '/menu/appmenu/remove.do?id=' + id,
							dataType : "json",
							success : function(data) {
								if (data) {
									if (data.success) {
										layer.msg("删除成功", {
											icon : 1
										});
										//刷新树
										clickload($("#treeview_selected_key").val());

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
			// 添加事件
			var addbtn = $("#addBtn_" + treeNode.tId);
			if (addbtn) {
				addbtn.bind("click", function() {
					// 跳转到修改页面
					$("#tree_selected_mkey").val($(this).attr("mkey"));
					$("#tree_selected_name").val($.fn.zTree.getZTreeObj("tree_center").getNodeByTId($(this).attr("id")).name);

					loadUrl2Div(_ctx+"/v.do?u=/oauth/menu/appmenuadd", 'tree_right');
					return false;
				});
			}
		}
		;

		function removeHoverDom(treeId, treeNode) {
			if(treeNode.type!="button"){
				$("#addBtn_" + treeNode.tId).unbind().remove();
			}
			if(treeNode.name!="root"){
				$("#removeBtn_" + treeNode.tId).unbind().remove();
			}
		}
		;

		var zNodes = [];

		$.ajax({
			type : 'post',
			url : _ctx + '/menu/appmenu/loadtree.do?appname=' + key,
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
				zTree = $.fn.zTree.init($("#tree_center"), setting, zNodes);
			},
			error : function() {
				layer.closeAll();
			}
		});

	}

    /**
	 * 选择图标
     */
	var selectIcon =function () {
        $("#selectIcon").on("click",function(){

            //$("#detailBody").append(detail_tr)

            layer.open({
                type: 1,
                title :"双击请选择字段",
                skin: 'layui-layer-rim', //加上边框
                area: ['80%', '100%'], //宽高,
                content: $("#selectIcon_div"),
                closeBtn : 1,
                maxmin: true,
                shadeClose : false,
                success: function(layero, index){
                    //弹出成功移除隐藏
                    $("#selectIcon_div").empty().removeClass("hide")
                    //ajax加载页面进行渲染
                    $.ajax({
                        type : 'get',
                        url : _ctx+'/v.do?u=/oauth/menu/appmenuselecticondiv',
                        dataType : "html",
                        success : function(data) {
                            //1，渲染弹出面板
                            $("#selectIcon_div").append($(data));

                            //2,提交按钮单击事件
                            $(".bs-glyphicons-list li").on("click",function(){
                                $("#icon").val($(this).children().first().attr("class"));

                                //$("#iconview").removeClass("glyphicon-th-large");
                                $("#iconview").attr("class",$(this).children().first().attr("class"));


                                layer.closeAll();
                            })


                        },
                        error : function() {
                            layer.closeAll();
                        }
                    });
                },
                cancel : function(){
                    //关闭回调设置隐藏
                    $("#selectIcon_div").addClass("hide")
                }
            });

        });
    }

	module.exports = {
		// 查看页面--初始化方法
		see : function() {
		},
		// 添加页面--初始化方法
		add : function() {

            selectIcon();



			// 添加提交
			$("#addSubmit").on("click", function() {
				var options = {
					url : _ctx + "/menu/appmenu/add.do",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
						//附值pkey
					 	formData.push({
			        		name  : "pkey",
			        		required : false,
			        		type : "text",
			        		value : $("#tree_selected_mkey").val()
			        	})
			        	//附值appname
			        	formData.push({
			        		name : "appname",
			        		required : false,
			        		type : "text",
			        		value : $("#treeview_selected_key").val()
			        	})
						
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('添加成功!', {
									icon : 1
								});
								//刷新树
								clickload($("#treeview_selected_key").val());
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
            selectIcon();

			// 修改提交
			$("#modifySubmit").on("click", function() {
				var options = {
					url : _ctx + "/menu/appmenu/modify",
					type : "post",
					resetForm : true,// 提交后重置表单
					dataType : 'json',
					beforeSubmit : function(formData, jqForm, options) {
						
					},
					success : function(data, statusText) {
						if (data != undefined) {
							if (data.success) {
								layer.msg('修改成功!', {
									icon : 1
								});
								//刷新树
								clickload($("#treeview_selected_key").val());
								//重新加载修改页面
                                loadUrl2Div(_ctx + "/menu/appmenu/modifyview/" + data.obj , 'tree_right');
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

			var defaultData = [ {
                text : '委托交易',
                icon : 'glyphicon glyphicon-lock',
                tags : [ '0' ],
                key : "entrust"
            },{
                text : 'C2C',
                icon : 'glyphicon glyphicon-subtitles',
                tags : [ '0' ],
                key : "c2ctrade"
            },{
                text : '平台币',
                icon : 'glyphicon glyphicon-gift',
                tags : [ '0' ],
                key : "dividendMin"
            },{
				text : '客服',
				icon : 'glyphicon glyphicon-home',
				tags : [ '0' ],
				key : "admin"
			},  /*{
				text : '业务规则',
				icon : 'glyphicon glyphicon-list-alt',
				tags : [ '0' ],
				key : "rule"
			},*/ {
                text : '全球化',
                icon : 'fa fa-user',
                tags : [ '0' ],
                key : "oauth"
            } ,  {
                text : '站点',
                icon : 'glyphicon glyphicon-flag',
                tags : [ '0' ],
                key : "website"
            }];
			$('#treeview').treeview({
				showTags : false,
				data : defaultData,
				onNodeSelected : function(event, node) {
					$("#tree_center").html("");
					$("#tree_right").html("");
					
					$("#treeview_selected_key").val(node.key);
					clickload(node.key);
				}

			});

		}
	}

});