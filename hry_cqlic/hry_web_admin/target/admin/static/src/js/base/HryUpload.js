/**
 * hry上传
 */
define(function (require, exports, module) {


//选择图片
    function imgUpload() {
        $(".hryUpload").each(function () {

            var imgSrc = []; //图片路径
            var imgFile = []; //文件流
            var imgName = []; //图片名字

            var uploadForm = $(this);

            var oInput = $($($(this).children(".hry_inputBox")[0]).children()[0]);
            var imgBox = $($(this).children(".hry_imgBox")[0]);
            var btn = $($(this).children(".hryUpload_btn")[0]);
            var filePath = $($($(this).children(".hry_inputBox")[0]).children()[1]);

            
            if(filePath.val()!=null&&filePath.val()!=""){
                imgSrc.push("/"+filePath.val());
                addNewContent(imgBox);
            }

            //添加图片
            $(this).on("change", "input[type='file']", function () {

                var fileImg = oInput[0];
                var fileList = fileImg.files;
                for (var i = 0; i < fileList.length; i++) {
                    //删除
                    imgSrc = [];
                    imgFile=[];
                    imgName=[];
                    addNewContent(imgBox);

                    var imgSrcI = getObjectURL(fileList[i]);
                    imgName.push(fileList[i].name);
                    imgSrc.push(imgSrcI);
                    imgFile.push(fileList[i]);
                }
                addNewContent(imgBox);
            })

            //上传
            btn.on('click', function () {
                
                //用formDate对象上传
                var fd = new FormData(uploadForm);
                for (var i = 0; i < imgFile.length; i++) {
                    fd.append("file[]", imgFile[i]);
                }
                submitPicture("/file/upload", fd);
            })

            //删除
            $(this).on("click", ".imgDelete", function removeImg(obj) {
                layer.msg("删除成功", {icon : 1});
                imgSrc = [];
                imgFile=[];
                imgName=[];
                oInput.val('');
                addNewContent(imgBox);
            })

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
            function addNewContent(imgBox) {
                imgBox.html("");
                for (var a = 0; a < imgSrc.length; a++) {
                    var oldBox = imgBox.html();
                    imgBox.html(oldBox + '<div class="imgContainer"><img title=' + imgName[a] + ' alt=' + imgName[a] + ' src=' + imgSrc[a] + ' class="imgDisplay"><p class="imgDelete">删除</p></div>');
                }
            }


//上传(将文件流数组传到后台)
            function submitPicture(url, data) {
                
                // for (var p of data) {
                //     console.log(p);
                // }
                if (url && data) {
                    $.ajax({
                        type: "post",
                        url: _ctx+url,
                        async: true,
                        data: data,
                        processData: false,
                        contentType: false,
                        dataType : "json",
                        success: function (result) {
                            
                            filePath.val(result.obj);
                            layer.msg("上传成功", {icon : 1});
                        }
                    });
                } else {
                    alert('请打开控制台查看传递参数！');
                }
            }

//图片预览路径
            function getObjectURL(file) {
                var url = null;
                if (window.createObjectURL != undefined) { // basic
                    url = window.createObjectURL(file);
                } else if (window.URL != undefined) { // mozilla(firefox)
                    url = window.URL.createObjectURL(file);
                } else if (window.webkitURL != undefined) { // webkit or chrome
                    url = window.webkitURL.createObjectURL(file);
                }
                return url;
            }
        })


    }



    module.exports = {
        // 渲染URL加载的select
        imgUpload: function (obj) {
            imgUpload(obj);
        },
        upload:function(isReadOnly){
            $(".hryUpload").each(function () {
                var imgSrc = []; //图片路径
                var imgFile = []; //文件流
                var imgName = []; //图片名字
                var imgBox = $($(this).children(".hry_imgBox")[0]);
                var filePath = $($($(this).children(".hry_inputBox")[0]).children()[0]);
                var hryButton = $($($(this).children(".hry_inputBox")[0]).children()[1]);
                if(filePath.val()!=null&&filePath.val()!=""){
                    imgSrc.push(_fileUrl+filePath.val());
                    addNewContent(imgBox);
                }
                new AjaxUpload(hryButton, {
                    action : _ctx+"/file/upload",
                    data : {},
                    name : 'file',
                    onSubmit : function(file, ext) {
                        if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF|bpm|BPM|ico)$/.test(ext))) {
                            alert("您上传的图片格式不对，请重新选择！");
                            return false;
                        }
                        //删除
                        imgSrc = [];
                        imgFile=[];
                        imgName=[];
                        addNewContent(imgBox);
                    },
                    onComplete : function(file, response) {

                        var resp = JSON.parse(response)
                        if(resp!=undefined&&resp.success){

                            filePath.val(resp.obj);
                            layer.msg("上传成功", {icon : 1});

                            imgSrc.push(_fileUrl+resp.obj);
                            addNewContent(imgBox);
                        }else{
                            layer.msg("上传失败", {
                                icon: 1,
                                time: 2000
                            });
                        }
                    }
                });



                //删除
                $(this).on("click", ".imgDelete", function removeImg(obj) {
                    imgSrc = [];
                    imgFile=[];
                    imgName=[];
                    filePath.val('');
                    addNewContent(imgBox);
                })


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
                function addNewContent(imgBox) {
                    imgBox.html("");
                    for (var a = 0; a < imgSrc.length; a++) {
                        var oldBox = imgBox.html();
                        if(isReadOnly){
                            imgBox.html(oldBox + '<div class="imgContainer"><img title=' + imgName[a] + ' alt=' + imgName[a] + ' src=' + imgSrc[a] + ' class="imgDisplay"></div>');
                        }else{
                            imgBox.html(oldBox + '<div class="imgContainer"><img title=' + imgName[a] + ' alt=' + imgName[a] + ' src=' + imgSrc[a] + ' class="imgDisplay"><p class="imgDelete">删除</p></div>');
                        }
                    }
                }


//上传(将文件流数组传到后台)
                function submitPicture(url, data) {

                    // for (var p of data) {
                    //     console.log(p);
                    // }
                    if (url && data) {
                        $.ajax({
                            type: "post",
                            url: _ctx+url,
                            async: true,
                            data: data,
                            processData: false,
                            contentType: false,
                            dataType : "json",
                            success: function (result) {

                                filePath.val(result.obj);
                                layer.msg("上传成功", {icon : 1});
                            }
                        });
                    } else {
                        alert('请打开控制台查看传递参数！');
                    }
                }

//图片预览路径
                function getObjectURL(file) {
                    var url = null;
                    if (window.createObjectURL != undefined) { // basic
                        url = window.createObjectURL(file);
                    } else if (window.URL != undefined) { // mozilla(firefox)
                        url = window.URL.createObjectURL(file);
                    } else if (window.webkitURL != undefined) { // webkit or chrome
                        url = window.webkitURL.createObjectURL(file);
                    }
                    return url;
                }
            })

        }
    }

});

