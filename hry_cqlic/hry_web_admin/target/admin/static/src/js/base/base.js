/**
 * 默认渲染到page_wrapper
 */
var loadUrl = function(url){

	if(url==null||url==""){
		return false;
	}
	//加载对应的页面
	$.ajax({
		type : "get",
		url : url,
		cache : false,
		dataType : "html",
		success : function(data) {
			if(data!=undefined&&data!=''&&data.indexOf("402880e3604021992ac3a50005index")==-1) {
                //清空主体
                $("#page-wrapper").empty();
                //进行渲染
                $("#page-wrapper").html(data);
                permissions();
                var bdHeight = $(window).height();
                $("#page-wrapper .centerRowBg").height(bdHeight - 148);
                $("#page-wrapper .centerRowBg.centerRowBg_write").height(bdHeight - 78);
            }
		},
		error : function(e) {
            layer.msg("此路径不存在："+url.substring(url.indexOf("u=")+1), {icon: 2,time:5000});
			//$("#page-wrapper").html("<div class='row'><h1>此路径不存在："+url.substring(url.indexOf("u=")+2)+"</h1></div>");
		}
	});
}


/**
 * 自定义渲染到指定DIV上
 */
var loadUrl2Div = function(url,divId){

    if(url==null||url==""){
        return false;
    }
	//加载对应的页面
	$.ajax({
		type : "get",
		url : url,
		cache : false,
		dataType : "html",
		success : function(data) {
            //清空主体
            $("#"+divId).empty();
			//进行渲染
			$("#"+divId).html(data);
            permissions();
		},
		error : function(e) {
			$("#"+divId).html("<div class='row'><h1>此路径不存在："+url.substring(url.indexOf("u=")+2)+"</h1></div>");
		}
	});
}

/**
 * ajax全局成功拦截
 */
$(document).ajaxSuccess(function(event,xhr,options){
	if(xhr.responseText.indexOf("402880e3604021992ac3a50005login")!=-1){
		//window.location.href=_ctx+"/login"
		//window.location.reload();
		window.location.replace(_ctx+"/login");
	}
});

/**
 * 鉴权方法
 */
var permissions = function(){



    function Encrypt(word){
        var key = CryptoJS.enc.Utf8.parse("bootstrap-tables");
        var srcs = CryptoJS.enc.Utf8.parse(word);
        var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
        return encrypted.toString();
    }
    function Decrypt(word){
        var key = CryptoJS.enc.Utf8.parse("bootstrap-tables");
        var decrypt = CryptoJS.AES.decrypt(word, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
        return CryptoJS.enc.Utf8.stringify(decrypt).toString();
    }

	//HRY_permissions从base.ftl中取出
	var HRY_permissions_array = Decrypt(HRY_permissions).split(",")
	var elements = $("[permissions]");
	if(elements.length>0){
		for(var i = 0 ; i < elements.length ; i++){
			var obj = $(elements[i]);
			var key = obj.attr("permissions");
			if(HRY_permissions_array.indexOf(key)==-1){
				obj.remove();
			}
		}
	}
}


define(function(require,exports,module){
	module.exports = {
		loadUrl : loadUrl
	}
	
})


var guid = function() {
    function S4() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}

$.fn.serializeJson = function () {
    var serializeObj = {};
    $(this.serializeArray()).each(function () {
        serializeObj[this.name] = this.value;
    });
    return serializeObj;
};

	
