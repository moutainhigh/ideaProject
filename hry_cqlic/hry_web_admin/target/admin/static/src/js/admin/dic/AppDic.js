define(function(require, exports, module) {
	this._table = require("js/base/table");
	
	module.exports = {
		
		//添加页面提交方法
		add : function(){
			
			//添加提交
			$("#addSubmit").on("click",function(){
				 var options = {  
						url : _ctx+"/dic/appdic/add.do",
						type : "post",
						resetForm: true,//提交后重置表单 
					    dataType:  'json' ,
				        beforeSubmit:  function (formData, jqForm, options){
				        	
				        	var name = $("#name").val();
				        	if(name==undefined||name==""){
				        		layer.msg('名称不能为空', {icon: 1});
				        		return false;
				        	}
				        	
				        	
				        	var mkey = $("#mkey").val();
				        	if(mkey==undefined||mkey==""){
				        		layer.msg('标识不能为空', {icon: 1});
				        		return false;
				        	}
				        	
				        	formData.push({
				        		name : "pkey",
				        		value : $("#dic_selected_mkey").val()
				        	})
				        	
				        },
				        success:function(data, statusText)  { 
				        	if(data!=undefined){
				        		if(data.success){
				        			layer.msg('添加成功!', {icon: 1});
				        			loadUrl(_ctx+'/v.do?u=admin/dic/appdictree')
				        		}else{
				        			layer.msg(data.msg, {icon: 1});
				        		}
				        	}
				 	    } 
				         
				 };
				 $("#form").ajaxSubmit(options);  
			});
		},
		addItemSubmit : function(){
			
			//添加提交
			$("#addSubmit").on("click",function(){
				 var options = {  
						url : _ctx+"/dic/appdic/addItem.do",
						type : "post",
						resetForm: true,//提交后重置表单 
					    dataType:  'json' ,
				        beforeSubmit:  function (formData, jqForm, options){
				        	
				        	
				        	
				        	var name = $("#name").val();
				        	if(name==undefined||name==""){
				        		layer.msg('名称不能为空', {icon: 1});
				        		return false;
				        	}
				        	
				        	
				        	var value = $("#value").val();
				        	if(value==undefined||value==""){
				        		layer.msg('键值不能为空', {icon: 1});
				        		return false;
				        	}
				        	
				        	formData.push({
				        		name : "pkey",
				        		value : $("#dic_selected_mkey").val()
				        	})
				        	
				        },
				        success:function(data, statusText)  { 
				        	if(data!=undefined){
				        		if(data.success){
				        			layer.msg('添加成功!', {icon: 1});
				        			loadUrl2Div(_ctx+'/v.do?u=/admin/dic/appdiclist','dicList_right')
				        		}
				        	}
				 	    } 
				         
				 };
				 $("#form").ajaxSubmit(options);  
			});
		},
		//修改页面提交方法
		modify : function(){
			
			//修改提交
			$("#modifySubmit").on("click",function(){
				 var options = {  
						url : _ctx+"/dic/appdic/modify.do",
						type : "post",
						resetForm: true,//提交后重置表单 
					    dataType:  'json' ,
				        beforeSubmit:  function (formData, jqForm, options){
				        	
				        	var name = $("#name").val();
				        	if(name==undefined||name==""){
				        		layer.msg('名称不能为空', {icon: 1});
				        		return false;
				        	}
				        	
				        	
				        	var mkey = $("#mkey").val();
				        	if(mkey==undefined||mkey==""){
				        		layer.msg('标识不能为空', {icon: 1});
				        		return false;
				        	}
				        	
				        },
				        success:function(data, statusText)  { 
				        	if(data!=undefined){
				        		if(data.success){
				        			layer.msg('修改成功!', {icon: 1});
				        			loadUrl(ctx+'/v.do?u=admin/dic/appdictree')
				        		}
				        	}
				 	    } 
				         
				 };
				 $("#form").ajaxSubmit(options);  
			});
		},
		//修改页面提交方法
		modifyItemSubmit : function(){
			
			//修改提交
			$("#modifySubmit").on("click",function(){
				 var options = {  
						url : _ctx+"/dic/appdic/modify.do",
						type : "post",
						resetForm: true,//提交后重置表单 
					    dataType:  'json' ,
				        beforeSubmit:  function (formData, jqForm, options){
				        	
				        	var name = $("#name").val();
				        	if(name==undefined||name==""){
				        		layer.msg('名称不能为空', {icon: 1});
				        		return false;
				        	}
				        	
				        	
				        	var value = $("#value").val();
				        	if(value==undefined||value==""){
				        		layer.msg('键值不能为空', {icon: 1});
				        		return false;
				        	}
				        	
				        },
				        success:function(data, statusText)  { 
				        	if(data!=undefined){
				        		if(data.success){
				        			layer.msg('修改成功!', {icon: 1});
				        			loadUrl2Div(_ctx+'/v.do?u=/admin/dic/appdiclist','dicList_right');
				        		}
				        	}
				 	    } 
				         
				 };
				 $("#form").ajaxSubmit(options);  
			});
		},
		//列表页面初始化方法
		list : function(pkey){
			
			//添加页面跳转按钮
			$("#see").on("click",function(){
				//_table.seeRow($("#table"),_ctx+"/admin/sys/sysdic/see");
				var ids = _table.getIdSelects($("#table"));
				if(ids!=undefined&&ids.length==1){
					loadUrl2Div(_ctx+'/dic/appdic/see/'+ids[0],'dicList_right')
				}else{
					layer.msg('请选择一条数据', {icon: 2});
				}
			});
			//修改页面跳转按钮
			$("#modify").on("click",function(){
				//_table.seeRow($("#table"),_ctx+"/admin/sys/sysdic/modifysee");
				
				var ids = _table.getIdSelects($("#table"));
				if(ids!=undefined&&ids.length==1){
					loadUrl2Div(_ctx+'/dic/appdic/modifyItemSee/'+ids[0],'dicList_right')
				}else{
					layer.msg('请选择一条数据', {icon: 2});
				}
				
			});
			//删除按钮点击事件
			$("#remove").on("click",function(){
				_table.removeRow($("#table"),_ctx+"/dic/appdic/remove");
			});
			
			
			var conf = {
					
					detail :function(e, index, row, $detail) {
						var html = [];
						$.each(row, function(key, value) {
							html.push('<p><b>' + key + ':</b> ' + value + '</p>');
						});
						$detail.html(html.join(''));
					},
					url : _ctx+"/dic/appdic/list.do?pkey="+pkey,
					columns  :[ {
						field : 'state',
						checkbox : true,
						align : 'center',
						valign : 'middle',
						value : "id",
						searchable : false
					},  {
						title : 'ID',
						field : 'id',
						align : 'center',
						visible : false,
						searchable : false
					},{
						title : '字典标识',
						field : 'pkey',
						align : 'center',
						searchable : false
					},{
						field : 'name',
						title : '名称',
						sortable : true,
						align : 'center'
					},{
						field : 'value',
						title : '键值',
						sortable : true,
						align : 'center'
					},{
                        field : 'remark1',
                        title : 'remark1',
                        sortable : true,
                        align : 'center'
                    }, {
                        field : 'remark2',
                        title : 'remark2',
                        sortable : true,
                        align : 'center'
                    }, {
                        field : 'remark3',
                        title : 'remark3',
                        sortable : true,
                        align : 'center'
                    },  {
						field : 'created',
						title : '创建时间',
                        visible: false,
						sortable : true,
						align : 'center'
					}, {
						field : 'modifyed',
						title : '修改时间',
                        visible: false,
						sortable : true,
						align : 'center'
					} ]
			}
			_table.initTable($("#table"),conf);
		},		
		//初始化树
		tree : function(){
			var zTree;
		    
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
			                enable:true,
			                idKey: "id",
			                pIdKey: "pId",
			                rootPId: ""
			            }
			        },
			        callback: {
			            beforeClick: function(treeId, treeNode) {
			            	if(treeNode.type==3){//如果是项值,没有事件
					    		return ;
					    	}
			            	
			            	$("#dic_selected").val(treeNode.trueId);
			            	$("#dic_selected_mkey").val(treeNode.id);
			            	
			            	if(treeNode.pId!=undefined&&treeNode.pId!=""){//如果是字典，加载列表
			            		loadUrl2Div(_ctx+'/v.do?u=/admin/dic/appdiclist','dicList_right');
			            	}
			            	
			                //var zTree = $.fn.zTree.getZTreeObj("tree");
			            }
			        }
		    };

		    function addHoverDom(treeId, treeNode) {
		    	if(treeNode.type==3){//如果是项值,不显示
		    		return ;
		    	}
		        var sObj = $("#" + treeNode.tId + "_span");
		        if (treeNode.editNameFlag || $("#removeBtn_"+treeNode.tId).length>0) return;
		        var addStr = "";
		        if(treeNode.pId==""){//如果是分类，显示添加按钮
		        	addStr += "<span class='button add' id='addBtn_" + treeNode.tId + "' trueid='"+treeNode.trueId+"'  title='添加' onfocus='this.blur();' ></span>";
		        	
		        }
		        
		        addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "' trueid='"+treeNode.trueId+"'  title='编辑' onfocus='this.blur();' ></span>";
		        addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "'  trueid='"+treeNode.trueId+"' title='删除' onfocus='this.blur();'></span>";
		        sObj.after(addStr);
		        
		        //删除事件
		        var removebtn = $("#removeBtn_"+treeNode.tId);
		        if (removebtn) removebtn.bind("click", function(){
		        	
		        	var id = $(this).attr("trueid");
		        	layer.confirm('你确定要删除吗？', {
		    			btn: ['确定','取消'],
		    			id : id
					}, function(){
						$.ajax({
							type : 'post',
							url : _ctx+'/dic/appdic/removeGroup.do?id='+id,
							dataType : "json",
							success : function(data) {
								if(data){
									if(data.success){
										layer.msg("删除成功", {icon: 1});
										loadUrl(_ctx+'/v.do?u=admin/dic/appdictree');
									}else{
										layer.msg(data.msg, {icon: 1});
									}
								}else{
									layer.msg('删除失败!', {icon: 1});
								}
							},
							error : function() {
								
							}
						});
					})
		        	
		            return false;
		        });
		        
		        //编辑事件
		        var editbtn = $("#editBtn_"+treeNode.tId);
		        if (editbtn) editbtn.bind("click", function(){
		        	if(treeNode.pId==""){
		        		loadUrl2Div(_ctx+'/dic/appdic/modifyGroupSee/'+$(this).attr("trueid")+".do",'dicList_right');
		        	}else{
		        		loadUrl2Div(_ctx+'/dic/appdic/modifysee/'+$(this).attr("trueid")+".do",'dicList_right');
		        	}
		        	//跳转到修改页面
		            return false;
		        });
		        
		        if(treeNode.pId==""){
			        //添加事件
			        var addbtn = $("#addBtn_"+treeNode.tId);
			        if (addbtn) addbtn.bind("click", function(){
			        	$("#dic_selected").val(treeNode.trueId);
		            	$("#dic_selected_mkey").val(treeNode.id);
			        	//跳转到添加页面
			            loadUrl2Div(_ctx+"/v.do?u=admin/dic/appdicadd",'dicList_right');
			            return false;
			        });
		        }
		        
		      
		    };

		    function removeHoverDom(treeId, treeNode) {
		        $("#addBtn_"+treeNode.tId).unbind().remove();
		        $("#removeBtn_"+treeNode.tId).unbind().remove();
		        $("#editBtn_"+treeNode.tId).unbind().remove();
		    };

		    
		    var zNodes = [];
		    
		    $.ajax({
				type : 'post',
				url : _ctx+'/dic/appdic/listTree.do',
				dataType : "json",
				success : function(data) {
					if(data!=undefined){
						for(var i = 0 ; i < data.length; i++){
							
							var node = {
									id : data[i].mkey,
									pId : data[i].pkey,
									name : data[i].name,
									trueId : data[i].id,
									value : data[i].value,
									type : data[i].type
							}
							zNodes.push(node);
							
						}
					}
					//初始化树
					var t = $("#tree_dic");
					t = $.fn.zTree.init(t, setting, zNodes);
					//t.expandAll(true); 
				},
				error : function() {
					layer.closeAll();
				}
			});
		    
		}
	}
	
	
	

});