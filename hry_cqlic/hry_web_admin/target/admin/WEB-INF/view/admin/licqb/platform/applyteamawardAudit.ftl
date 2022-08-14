<!-- Copyright:    -->
<!-- ApplyTeamAwardModify.html     -->
<!-- @author:      zhouming  -->
<!-- @version:     V1.0             -->
<!-- @Date:        2019-08-12 17:44:07      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <h3 class="page-header">社区奖励审核  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/platform/applyteamawardlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
        </div>
    </div>
    <form id="form" >
        <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="email">用户邮箱</label>
                    <input type="text" class="form-control" readonly name="email" id="email" value="${model.email}"/>
                </div>
                <div class="form-group">
                    <label for="mobilePhone">用户手机号</label>
                    <input type="text" class="form-control" readonly name="mobilePhone" id="mobilePhone" value="${model.mobilePhone}"/>
                </div>
                <div class="form-group">
                    <label for="socialType">社交类型</label>
                    <select class="form-control" name="socialType" readonly disabled="disabled">
                        <option value="1" <#if socialType == 1>selected</#if>>QQ</option>
                        <option value="2" <#if socialType == 2>selected</#if>>微信</option>
                        <option value="3" <#if socialType == 3>selected</#if>>facebook</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="socialAccount">社交账户</label>
                    <input type="text" class="form-control" readonly name="socialAccount" id="socialAccount" value="${model.socialAccount}"/>
                </div>
                <div class="form-group">
                    <label for="socialGroupImg">社交群图片</label>
                    <div class="hryUpload" name="hryUpload" style="width: 100%">
                        <div class="hry_inputBox" style="height:0px">
                            <input class="form-control hryUpload_filePath" type="hidden" name="socialGroupImg" id="socialGroupImg" value="${model.socialGroupImg}"/>
                        </div>
                        <div class="hry_imgBox">

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="auditExplain">审核说明</label>
                    <textarea type="text" class="form-control" <#if type==2>readonly</#if> name="auditExplain" id="auditExplain" value="${model.auditExplain}">${model.auditExplain}</textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <#if type == 1>
                <div class="col-md-2 column">
                    <button type="button" id="passSubmit" name="submit"  value="1" style="margin-right: 20px" class="btn btn-info-blue btn-warning" >通过</button>
                    <button type="button" id="rejectSubmit" name="submit" value="2"  class="btn btn-info-blue btn-warning" >驳回</button>
                </div>
            </#if>
        </div>
    </form>
</div>

<script type="text/javascript">
    seajs.use(["js/admin/licqb/platform/ApplyTeamAward","js/base/HryUpload"],function(o,HryUpload){
        o.init();
        HryUpload.upload(true);
    });
    $(function(){
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
    })
</script>






