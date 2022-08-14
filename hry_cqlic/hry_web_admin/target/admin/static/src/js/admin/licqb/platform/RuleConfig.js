define(function(require, exports, module) {
    module.exports = {
        base: function () {
            // 保存提交
            $("#saveSubmit").on("click", function () {
                var rulesAwardSum = 0;
                $('.rulesAwardKey').each(function(i,n){
                    rulesAwardSum = Number(rulesAwardSum)+Number($(this).val());
                });

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
                    url: _ctx + "/platform/config/save",
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
                                loadUrl(_ctx + "/platform/config/getRuleConfig");
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