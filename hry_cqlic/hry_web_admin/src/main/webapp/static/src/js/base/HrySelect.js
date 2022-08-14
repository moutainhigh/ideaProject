define(function(require, exports, module) {

	module.exports = {
		// 渲染URL加载的select
		init : function() {

			var allSelect = $("select[hry=true]");

			for (var i = 0; i < allSelect.length; i++) {
				var select = $(allSelect[i]);
				var id = select.attr("id");
				var url = select.attr("url");
				var itemname = select.attr("itemname");
				var itemvalue = select.attr("itemvalue");
				var value = select.attr("value");

				$.ajax({
					type : "get",
					url : url,
					cache : false,
					id : id,// 把下拉框ID传进来，解决异步问题
                    itemname : itemname,
                    itemvalue :itemvalue,
                    value : value,
					dataType : "json",
					success : function(data) {
                        itemvalue = this.itemvalue;
                        itemname = this.itemname;
                        value = this.value;
                        $("#" + this.id).attr('tablesearch',true);
						$("#" + this.id).append("<option value='' >请选择</option>");
						var i=data.length;
						 for ( var obj in data) {
						 	if(i<1){
								continue;
							} i--;
							// 判断数据类型是否是对象类型
							if (typeof (data[0]) != "object") {//如果是基本类型
								$("#" + this.id).append("<option value='" + eval("data[obj]") + "' >" + eval("data[obj]") + "</option>");
							} else {
								if (value != undefined && value != "") {
									if (eval("data[obj]." + itemvalue) == value) {
										$("#" + this.id).append("<option  selected=\"selected\"  value='" + eval("data[obj]." + itemvalue) + "' >" + eval("data[obj]." + itemname) + "</option>");
									} else {
										$("#" + this.id).append("<option value='" + eval("data[obj]." + itemvalue) + "' >" + eval("data[obj]." + itemname) + "</option>");
									}

								} else {
									// 进行渲染
									$("#" + this.id).append("<option value='" + eval("data[obj]." + itemvalue) + "' >" + eval("data[obj]." + itemname) + "</option>");
								}
							}
						}

					},
					error : function(e) {

					}
				});
			}

		},
		getKey : function(key) {
			var list = [];
			$.ajax({
                type : "get",
                url : _ctx + "/dic/appdic/getKey.do?key=" + key,
                cache : false,
                async : false,
                dataType : "json",
                success : function(data) {
                    list = data;
                },
                error : function(e) {
                }
            });
			return list;
		},

		//@ by tianpengyu
		//根据数据字典键值返回数据字典标识， key 数据字典标识 , nowdata 数据字典键值
        getKeyAndVal : function(key,nowdata) {
			var val="";
            $.ajax({
                type : "post",
                url : _ctx + "/dic/appdic/findkeyAndVal?pkey=" + key,
                cache : false,
                async : false,
                dataType : "json",
                success : function(data) {
					for(var i=0;i<data.length;i++){
                		var appDic = data[i]
						if(appDic.value == nowdata){
                			val =  appDic.name
						}
					}

                },
                error : function(e) {
                }
            });

            return val;
        }
	}

});