define(function(require, exports, module) {
	this._table = require("js/base/table");

	module.exports = {
		//查看页面--初始化方法
		see : function(){

			$("#reply").on("click",function(){
				var html = "<div id=\"content\" >"+
							"<textarea  class=\"form-control\" name=\"sketch\" id=\"sketch\" ></textarea>"+
							"<input class=\"button theme-button\" type=\"button\" value=\"通过\" id=\"adopt\">"+
							"<input class=\"button theme-button\" type=\"button\" value=\"驳回\" id=\"reject\">"+
							"</div>";
				var index=layer.open({
				  type: 1,
				  title :"处理意见",
				  skin: 'layui-layer-rim', //加上边框
				  area: ['420px', '200px'], //宽高,
				  content: html,
				  resize:false,
				  shadeClose : false,
				  success: function(layero, index){
					  //弹出成功移除隐藏

				  },
				  cancel : function(){
					  //关闭回调设置隐藏
					 // $("#"+fileid).addClass("hide")
				  }
				  });
				//通过
				$("#adopt").on("click",function(){
				var sketch=	$("#sketch").val();
				var tradeNum=$("#tradeNum").val();
				layer.confirm("确定通过吗？", {
          		   btn: ['确定','取消'] //按钮
      			}, function(){
				$.ajax({
	    			type : "post",
	    			url : _ctx + "/exchange/otcapptransaction/examineAndreject.do",
	    			data:{sketch:sketch,transactionNum:tradeNum,type:"11"},
	    			dataType : "json",
	    			success : function(data) {
	    				if (data != undefined) {
							if (data.success) {
								layer.msg(data.msg, {icon: 1,time:1500},function(){
     								loadUrl(_ctx+"/v.do?u=admin/exchange/otcapptransactionlist_shensuzhong");
 								})
							}else{
								layer.msg("操作失败", {icon: 2});
							}
						}
	    			}
	    		   });
      			 });
				});
				//驳回
				$("#reject").on("click",function(){
					var sketch=	$("#sketch").val();
					var tradeNum=$("#tradeNum").val();
					layer.confirm("确定驳回吗？", {
              		   btn: ['确定','取消'] //按钮
          			}, function(){
					$.ajax({
		    			type : "post",
		    			url : _ctx + "/exchange/otcapptransaction/examineAndreject.do",
		    			data:{sketch:sketch,transactionNum:tradeNum,type:"3"},
		    			dataType : "json",
		    			success : function(data) {
		    				if (data != undefined) {
								if (data.success) {
									layer.msg(data.msg, {icon: 1,time:1500},function(){
         								loadUrl(_ctx+"/v.do?u=admin/exchange/otcapptransactionlist_shensuzhong");
     								})
								}else{
									layer.msg("操作失败", {icon: 2});
								}
							}
		    			}
		    		  });
          			});
				});
			});


			//查看页面的回复按钮
			$("#order_reply").on("click",function(){
				var tradeNum=$("#tradeNum").val();
				var platFormContent = $("#platFormContent").val();
				if(tradeNum != null && tradeNum != "" && tradeNum != undefined){
					$.ajax({
		    			type : "post",
		    			url : _ctx + "/exchange/otcapptransaction/orderReplay.do",
		    			data:{transactionNum:tradeNum,platFormContent:platFormContent},
		    			dataType : "json",
		    			success : function(data) {
		    				if (data != undefined) {
								if (data.success) {
									layer.msg(data.msg, {icon: 1,time:1500},function(){
	     								loadUrl(_ctx+"/v.do?u=admin/exchange/otcapptransactionlist_shensuzhong");
	 								})
								}else{
									layer.msg("操作失败", {icon: 2});
								}
							}
		    			}
		    		  });
				}
			});


			//查看页面的驳回按钮
			$("#order_reject").on("click",function(){
				var tradeNum=$("#tradeNum").val();
				layer.confirm("确定驳回吗？", {
          		   btn: ['确定','取消'] //按钮
      			}, function(){
				$.ajax({
	    			type : "post",
	    			url : _ctx + "/exchange/otcapptransaction/examineAndreject.do",
	    			data:{transactionNum:tradeNum,type:"12"},
	    			dataType : "json",
	    			success : function(data) {
	    				if (data != undefined) {
							if (data.success) {
								layer.msg(data.msg, {icon: 1,time:1500},function(){
     								loadUrl(_ctx+"/v.do?u=admin/exchange/otcapptransactionlist_shensuzhong");
 								})
							}else{
								layer.msg("操作失败", {icon: 2});
							}
						}
	    			}
	    		  });
      			});
			});

			//查看页面的取消订单按钮
			$("#order_cancel").on("click",function(){
				var tradeNum=$("#tradeNum").val();
				layer.confirm("确定取消订单吗？", {
          		   btn: ['确定','取消'] //按钮
      			}, function(){
				$.ajax({
	    			type : "post",
	    			url : _ctx + "/exchange/otcapptransaction/cancleOrder.do",
	    			data:{transactionNum:tradeNum,type:"5"},
	    			dataType : "json",
	    			success : function(data) {
	    				if (data != undefined) {
							if (data.success) {
								layer.msg(data.msg, {icon: 1,time:1500},function(){
     								loadUrl(_ctx+"/v.do?u=admin/exchange/otcapptransactionlist_shensuzhong");
 								})
							}else{
								layer.msg("操作失败", {icon: 2});
							}
						}
	    			}
	    		  });
      			});
			});

			//查看页面的订单成立按钮
			$("#order_pass").on("click",function(){
				var tradeNum=$("#tradeNum").val();
				layer.confirm("确定完成订单吗？", {
          		   btn: ['确定','取消'] //按钮
      			}, function(){
				$.ajax({
	    			type : "post",
	    			url : _ctx + "/exchange/otcapptransaction/orderCompleted.do",
	    			data:{transactionNum:tradeNum,type:"11"},
	    			dataType : "json",
	    			success : function(data) {
	    				if (data != undefined) {
							if (data.success) {
								layer.msg(data.msg, {icon: 1,time:1500},function(){
     								loadUrl(_ctx+"/v.do?u=admin/exchange/otcapptransactionlist_shensuzhong");
 								})
							}else{
								layer.msg("操作失败", {icon: 2});
							}
						}
	    			}
	    		  });
      			});
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
										transactionNum : {
						validators : {
							notEmpty : {
								message : "交易订单号不能为空"
							}
						}
					},
					customerId : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					accountId : {
						validators : {
							notEmpty : {
								message : "虚拟账户id不能为空"
							}
						}
					},
					buyUserId : {
						validators : {
							notEmpty : {
								message : "买方ID不能为空"
							}
						}
					},
					buyUserName : {
						validators : {
							notEmpty : {
								message : "买方用户名不能为空"
							}
						}
					},
					buyfee : {
						validators : {
							notEmpty : {
								message : "买方手续费不能为空"
							}
						}
					},
					sellUserId : {
						validators : {
							notEmpty : {
								message : "卖方ID不能为空"
							}
						}
					},
					sellUserName : {
						validators : {
							notEmpty : {
								message : "卖方用户名不能为空"
							}
						}
					},
					sellfee : {
						validators : {
							notEmpty : {
								message : "卖方手续费不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种代码不能为空"
							}
						}
					},
					transactionMode : {
						validators : {
							notEmpty : {
								message : "交易方式(1在线购买,2在线出售,3本地购买)不能为空"
							}
						}
					},
					payType : {
						validators : {
							notEmpty : {
								message : "交易类型(1银行转账,2支付宝,3微信支付)不能为空"
							}
						}
					},
					tradeNum : {
						validators : {
							notEmpty : {
								message : "交易数量不能为空"
							}
						}
					},
					tradeMoney : {
						validators : {
							notEmpty : {
								message : "交易金额不能为空"
							}
						}
					},
					transactionType : {
						validators : {
							notEmpty : {
								message : "交易类型(1定价交易 2市价交易)不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "1待支付 2已付款待确认 3已完成 4申诉中待回复 5已取消 6申请退款中 7退款已驳回 8申诉完成 9申诉成功,待确认 10申诉失败,待确认 11平台通过申诉 12平台驳回申诉 13退款成功不能为空"
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
					remark : {
						validators : {
							notEmpty : {
								message : "备注不能为空"
							}
						}
					},
					payTime : {
						validators : {
							notEmpty : {
								message : "transactionMode为1时,为买家购买时间,为2时,为卖家购买时间不能为空"
							}
						}
					},
					confirmMoney : {
						validators : {
							notEmpty : {
								message : "transactionMode为1时,为买家确认收款时间,为2时,为卖家确认收款时间不能为空"
							}
						}
					},
					appealTime : {
						validators : {
							notEmpty : {
								message : "transactionMode为1时,为买家申诉时间,为2时,为卖家申诉时间不能为空"
							}
						}
					},
					advertisementId : {
						validators : {
							notEmpty : {
								message : "广告ID不能为空"
							}
						}
					},
					referenceNum : {
						validators : {
							notEmpty : {
								message : "付款参考号不能为空"
							}
						}
					},
					sellIsDeleted : {
						validators : {
							notEmpty : {
								message : "卖方是否删除不能为空"
							}
						}
					},
					buyIsDeleted : {
						validators : {
							notEmpty : {
								message : "买方是否删除不能为空"
							}
						}
					},
					bankId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					bankNumber : {
						validators : {
							notEmpty : {
								message : "银行卡号不能为空"
							}
						}
					},
					alipayId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					alipayAccount : {
						validators : {
							notEmpty : {
								message : "支付宝账号不能为空"
							}
						}
					},
					alipayThingUrl : {
						validators : {
							notEmpty : {
								message : "支付宝二维码不能为空"
							}
						}
					},
					wechatId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					wechatAccount : {
						validators : {
							notEmpty : {
								message : "微信账号不能为空"
							}
						}
					},
					wechatThingUrl : {
						validators : {
							notEmpty : {
								message : "微信二维码不能为空"
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
					url : _ctx + "/exchange/otcapptransaction/add.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/otcapptransactionlist')
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
					transactionNum : {
						validators : {
							notEmpty : {
								message : "交易订单号不能为空"
							}
						}
					},
					customerId : {
						validators : {
							notEmpty : {
								message : "用户id不能为空"
							}
						}
					},
					accountId : {
						validators : {
							notEmpty : {
								message : "虚拟账户id不能为空"
							}
						}
					},
					buyUserId : {
						validators : {
							notEmpty : {
								message : "买方ID不能为空"
							}
						}
					},
					buyUserName : {
						validators : {
							notEmpty : {
								message : "买方用户名不能为空"
							}
						}
					},
					buyfee : {
						validators : {
							notEmpty : {
								message : "买方手续费不能为空"
							}
						}
					},
					sellUserId : {
						validators : {
							notEmpty : {
								message : "卖方ID不能为空"
							}
						}
					},
					sellUserName : {
						validators : {
							notEmpty : {
								message : "卖方用户名不能为空"
							}
						}
					},
					sellfee : {
						validators : {
							notEmpty : {
								message : "卖方手续费不能为空"
							}
						}
					},
					coinCode : {
						validators : {
							notEmpty : {
								message : "币种代码不能为空"
							}
						}
					},
					transactionMode : {
						validators : {
							notEmpty : {
								message : "交易方式(1在线购买,2在线出售,3本地购买)不能为空"
							}
						}
					},
					payType : {
						validators : {
							notEmpty : {
								message : "交易类型(1银行转账,2支付宝,3微信支付)不能为空"
							}
						}
					},
					tradeNum : {
						validators : {
							notEmpty : {
								message : "交易数量不能为空"
							}
						}
					},
					tradeMoney : {
						validators : {
							notEmpty : {
								message : "交易金额不能为空"
							}
						}
					},
					transactionType : {
						validators : {
							notEmpty : {
								message : "交易类型(1定价交易 2市价交易)不能为空"
							}
						}
					},
					status : {
						validators : {
							notEmpty : {
								message : "1待支付 2已付款待确认 3已完成 4申诉中待回复 5已取消 6申请退款中 7退款已驳回 8申诉完成 9申诉成功,待确认 10申诉失败,待确认 11平台通过申诉 12平台驳回申诉 13退款成功不能为空"
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
					remark : {
						validators : {
							notEmpty : {
								message : "备注不能为空"
							}
						}
					},
					payTime : {
						validators : {
							notEmpty : {
								message : "transactionMode为1时,为买家购买时间,为2时,为卖家购买时间不能为空"
							}
						}
					},
					confirmMoney : {
						validators : {
							notEmpty : {
								message : "transactionMode为1时,为买家确认收款时间,为2时,为卖家确认收款时间不能为空"
							}
						}
					},
					appealTime : {
						validators : {
							notEmpty : {
								message : "transactionMode为1时,为买家申诉时间,为2时,为卖家申诉时间不能为空"
							}
						}
					},
					advertisementId : {
						validators : {
							notEmpty : {
								message : "广告ID不能为空"
							}
						}
					},
					referenceNum : {
						validators : {
							notEmpty : {
								message : "付款参考号不能为空"
							}
						}
					},
					sellIsDeleted : {
						validators : {
							notEmpty : {
								message : "卖方是否删除不能为空"
							}
						}
					},
					buyIsDeleted : {
						validators : {
							notEmpty : {
								message : "买方是否删除不能为空"
							}
						}
					},
					bankId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					bankNumber : {
						validators : {
							notEmpty : {
								message : "银行卡号不能为空"
							}
						}
					},
					alipayId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					alipayAccount : {
						validators : {
							notEmpty : {
								message : "支付宝账号不能为空"
							}
						}
					},
					alipayThingUrl : {
						validators : {
							notEmpty : {
								message : "支付宝二维码不能为空"
							}
						}
					},
					wechatId : {
						validators : {
							notEmpty : {
								message : "支付信息记录id不能为空"
							}
						}
					},
					wechatAccount : {
						validators : {
							notEmpty : {
								message : "微信账号不能为空"
							}
						}
					},
					wechatThingUrl : {
						validators : {
							notEmpty : {
								message : "微信二维码不能为空"
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
					url : _ctx + "/exchange/otcapptransaction/modify.do",
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
								loadUrl(_ctx+'/v.do?u=/admin/exchange/otcapptransactionlist')
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
		list : function(state) {

			// 添加页面跳转按钮
			$("#see").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/otcapptransaction/see");
			});
			// 修改页面跳转按钮
			$("#modify").on("click", function() {
				_table.seeRow($("#table"), _ctx + "/exchange/otcapptransaction/modifysee");
			});
			// 删除按钮点击事件
			$("#remove").on("click", function() {
				_table.removeRow($("#table"), _ctx + "/exchange/otcapptransaction/remove.do");
			});
			$("#look").on("click",function(){
                _table.seeRow($("#table"), _ctx + "/exchange/otcapptransaction/look");
            });

            //查询按钮
            $("#table_query").on("click", function () {
                var params = $("#table_query_form").serializeJson();
                //查询
                _table.tableQuery($("#table"), params);
            });
            //重置
            $("#table_reset").on('click', function(){
                $("#table_query_form")[0].reset();
			})

            var url = "";
            if(state == 0){ // 全部
                url = _ctx + "/exchange/otcapptransaction/list.do";
            }else if(state == 1){ // 进行中
                url = _ctx + "/exchange/otcapptransaction/listing.do";
            }else if(state == 2){ // 已完成
                url = _ctx + "/exchange/otcapptransaction/listOver.do";
            }else if(state == 3){ // 已取消
                url = _ctx + "/exchange/otcapptransaction/listCancel.do";
            }else if(state == 4){ // 申诉中
                url = _ctx + "/exchange/otcapptransaction/listAppeal.do";
            }

			var conf = {

				detail : function(e, index, row, $detail) {
					var html = [];
					$.each(row, function(key, value) {
						html.push('<p><b>' + key + ':</b> ' + value + '</p>');
					});
					$detail.html(html.join(''));
				},
				url : url,
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
					searchable : true
				},
				{
					title : '交易订单号',
					field : 'transactionNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '币种代码',
					field : 'coinCode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '法币代码',
					field : 'legalCurrency',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '买方用户名',
					field : 'buyUserName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
                {
                    title : '买方手机号',
                    field : 'buyMobile',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
                {
                    title : '买方邮箱',
                    field : 'buyEmail',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
				{
					title : '卖方用户名',
					field : 'sellUserName',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
                    title : '卖方手机号',
                    field : 'sellMobile',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
                {
                    title : '卖方邮箱',
                    field : 'sellEmail',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true
                },
				{
					title : '交易方式',
					field : 'transactionMode',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return "在线购买";
                        }else if (value == 2) {
                            return "在线出售";
                        }else if (value == 3) {
                            return "本地购买";
                        }
                        return "";
                    }
				},
				{
					title : '交易类型',
					field : 'payType',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
                    formatter: function (value, row, index) {
						if(value != null && value != ''){
                            var v = value.split(',');
                            var t = '';
                            for(var i=0;i<v.length;i++){
                                if (v[i] == 1) {
                                    t += '银行转账,'
                                }else if (v[i] == 2) {
                                    t += '支付宝,'
                                }else if (v[i] == 3) {
                                    t += '微信支付,'
                                }
							}
							if(t !=''){
                            	t = t.substr(0, t.length - 1);
							}
							return t;
						}
                        return "";
                    }
				},
				{
					title : '交易数量',
					field : 'tradeNum',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '交易金额',
					field : 'tradeMoney',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '买方手续费',
					field : 'buyfee',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '卖方手续费',
					field : 'sellfee',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true
				},
				{
					title : '状态',
					field : 'status',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function(value, row, index){
					    if (value == "1") {
                            return "待支付"
                        }else if(value == '2'){
                            return "已付款待确认";
                        }else if(value == '3'){
                            return "已支付";
                        }else if(value == '4'){
                            return "申诉中待回复";
                        }else if(value == '5'){
                            return "已取消";
                        }else if(value == '6'){
                            return "申请退款中";
                        }else if(value == '7'){
                            return "退款已驳回";
                        }else if(value == '8'){
                            return "申诉完成";
                        }else if(value == '9'){
                            return "申诉成功,待确认";
                        }else if(value == '10'){
                            return "申诉失败,待确认";
                        }else if(value == '11'){
                            return "平台通过申诉";
                        }else if(value == '12'){
                            return "平台驳回申诉";
                        }else if(value == '13'){
                            return "退款成功";
                        }else if(value == '14'){
                            return "已完成";
                        }else if(value == '15'){
                            return "买家申诉中";
                        }else if(value == '16'){
                            return "卖家申诉中";
                        }
                        return "";
					}
				},
				{
					title : '买方备注',
					field : 'remark',
					align : 'center',
					visible : true,
					sortable : false,
					searchable : true,
					formatter: function (value, row, index) {
                        if (row.transactionMode == "1") {
                            //在线购买
                            return row.remark;
                        }else if (row.transactionMode == '2') {
                            return row.advertisementRemark;
                        } else if (row.transactionMode == '3') {
                            return row.advertisementRemark;
                        }
                        return "";
                    }
				},{
                    title : '卖方备注',
                    field : 'remark',
                    align : 'center',
                    visible : true,
                    sortable : false,
                    searchable : true,
                    formatter: function (value, row, index) {
                          if (row.transactionMode == "1") {
                              //在线购买
                              return row.remark;
                          }else if (row.transactionMode == '2') {
                              return row.advertisementRemark;
                          } else if (row.transactionMode == '3') {
                              return row.advertisementRemark;
                          }
                          return "";
                      }
                }
				]
			}
			_table.initTable($("#table"), conf);
		}
	}

});