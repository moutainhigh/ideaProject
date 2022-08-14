define(function(require, exports, module) {
    // require("lib/jdate/css/jedate.css");

	module.exports = {
        base: function () {

            // 保存提交
            $("#saveSubmit").on("click", function () {
            	var rulesAwardSum = 0;
            	$('.rulesAwardKey').each(function(i,n){
            		//console.log($(this).val());
            		rulesAwardSum = Number(rulesAwardSum)+Number($(this).val());
        		});
            	if(rulesAwardSum!=100){
            		layer.msg("糖果奖励分配总比例必须等于100", {
                        icon: 2,
                    })
                    return;
            	}
            	
                var typeKey = $("li.active .languageQuery").attr("languageType");
                var jsonData = {}
                $(".appconfig").each(function () {
                    var name = $(this).attr("name");
                    var value = $(this).val();
                    //获得input框的值
                    eval("jsonData." + name + " = '" + value + "'");
                });

                $.ajax({
                    type: "post",
                    url: _ctx + "/klg/level/klglevelconfig//save",
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
                                loadUrl(_ctx + "/klg/level/klglevelconfig/getRuleConfig");
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
});