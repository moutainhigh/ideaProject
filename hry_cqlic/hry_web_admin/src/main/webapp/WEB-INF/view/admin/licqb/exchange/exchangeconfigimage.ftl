<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <h3 class="page-header">项目规则图片添加  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/exchange/exchangeconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
        </div>
    </div>
    <form id="form" >
        <div class="row">
            <div class="col-md-4 column">
                <input type="hidden" id="langKey" value="${langKey}">
                <#list imageList as image>
                    <div class="form-group">
                        <label for="picturePath">${image.langName}</label>
                        <div class="hryUpload" style="width: 100%">
                            <div class="hry_inputBox">
                                <input class="form-control hryUpload_filePath " type="hidden" id="${image.id}" name="${image.languagetype}" <#if image.image != ''> value="${image.image}" </#if> >
                                <button type="button" class="btn btn-primary btn-block" id="${image.languagetype}">上传图片1</button>
                            </div>
                            <div class="hry_imgBox">

                            </div>
                        </div>
                    </div>
                </#list>
            </div>
            <div class="col-md-4 column">
                <#list imageList as image>
                    <div class="form-group">
                        <label for="picturePath">${image.langName}</label>
                        <div class="hryUpload" style="width: 100%">
                            <div class="hry_inputBox">
                                <input class="form-control hryUpload_filePath " type="hidden" id="${image.id}" name="${image.languagetype}" <#if image.imageTwo != ''> value="${image.imageTwo}" </#if> >
                                <button type="button" class="btn btn-primary btn-block" id="${image.languagetype}">上传图片2</button>
                            </div>
                            <div class="hry_imgBox">

                            </div>
                        </div>
                    </div>
                </#list>
            </div>
            <div class="col-md-4 column">
                <#list imageList as image>
                    <div class="form-group">
                        <label for="picturePath">${image.langName}</label>
                        <div class="hryUpload" style="width: 100%">
                            <div class="hry_inputBox">
                                <input class="form-control hryUpload_filePath " type="hidden" id="${image.id}" name="${image.languagetype}" <#if image.imageThree != ''> value="${image.imageThree}" </#if> >
                                <button type="button" class="btn btn-primary btn-block" id="${image.languagetype}">上传图片3</button>
                            </div>
                            <div class="hry_imgBox">

                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2 column">
                <button type="button" id="submitImage" class="btn btn-info-blue btn-block" >提交</button>
            </div>
        </div>
    </form>

</div>
<script type="text/javascript">
    seajs.use(["js/admin/licqb/exchange/ExchangeConfig","js/base/HryUpload"], function(o, HryUpload){
        o.updateImage();
        HryUpload.upload();
    });
</script>




