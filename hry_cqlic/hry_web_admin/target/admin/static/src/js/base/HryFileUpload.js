/**
 * hry上传
 */
define(function (require, exports, module) {

    module.exports = {

        upload:function(){




            $(".hryUpload").each(function () {

                var imgSrc = []; //文件路径
                var imgFile = []; //文件流
                var imgName = []; //文件名字
                var index;

                var imgBox = $($(this).children(".hry_imgBox")[0]);

                var filePath = $($($(this).children(".hry_inputBox")[0]).children()[0]);
                var hryButton = $($($(this).children(".hry_inputBox")[0]).children()[1]);



            /*    if(filePath.val()!=null&&filePath.val()!=""){
                    imgSrc.push(_fileUrl+filePath.val());
                    addNewContent(imgBox);
                }*/



                new AjaxUpload(hryButton, {
                    action : _ctx+"/file/fileUpload",
                    data : {},
                    name : 'file',
                    onSubmit : function(file, ext) {
                       /* if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF|bpm|BPM|ico)$/.test(ext))) {
                            alert("您上传的图片格式不对，请重新选择！");
                            return false;
                        }*/
                        //删除
                         index = layer.load(1, {
                            shade: [0.1,'#fff'] //0.1透明度的白色背景
                        });
                        imgSrc = [];
                        imgFile=[];
                        imgName=[];
                        // addNewContent(imgBox);
                    },
                    onComplete : function(file, response) {

                        var resp = JSON.parse(response);
                        if(resp!=undefined&&resp.success){
                            filePath.val(resp.obj);
                            $(".appFilePath").val(resp.obj);
                            $.ajax({
                                type : "post",
                                url : _ctx+"/file/fileQrUpload",
                                data:{
                                    "fileUrl" : _fileUrl+resp.obj
                                },
                                cache : false,
                                async : false,
                                dataType : "json",
                                success : function(data) {
                                    if(data.obj!=null&&data.success){

                                        layer.close(index);
                                        layer.msg("上传成功", {icon : 1});
                                        imgSrc.push(_fileUrl+data.obj);
                                        $(".appCodePath").val(data.obj);
                                        addNewContent(imgBox);
                                    }
                                },
                                error : function(e) {
                                    layer.msg("上传失败", {
                                        icon: 2,
                                        time: 2000
                                    });
                                }
                            });

                        }else{
                            layer.msg("上传失败", {
                                icon: 2,
                                time: 2000
                            });
                        }
                    }
                });





                //查看
                $(this).on("click", ".imgDisplay", function removeImg(obj) {
                    var src = $(this).attr("src");
                    var imgHtml = '<div style="width: 100%;height: 100vh;overflow: auto;background: rgba(0,0,0,0.5);text-align: center;position: fixed;top: 0;left: 0;z-index: 1000;"><img src=' + src + ' style="margin-top: 100px;width: 70%;margin-bottom: 100px;"/><p style="font-size: 50px;position: fixed;top: 30px;right: 30px;color: white;cursor: pointer;" class="closePicture">×</p></div>'
                    $('body').append(imgHtml);
                })
                //查看
                $("body").on("click", ".closePicture", function removeImg(obj) {
                    $(this).parent("div").remove();
                })

                //图片展示
                function addNewContent(imgBox) {debugger
                    if(imgBox!=undefined){
                        imgBox.html("");
                        for (var a = 0; a < imgSrc.length; a++) {
                            var oldBox = imgBox.html();
                            imgBox.html(oldBox + '<div  style="height: 130px; width: 130px " class="imgContainer"><img  style="height: 130px; width: 130px " title=' + imgName[a] + ' alt=' + imgName[a] + ' src=' + imgSrc[a] + ' class="imgDisplay"></div>');
                        }
                    }
                }


            })

        }
    }

});

