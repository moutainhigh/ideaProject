<!-- Copyright:    -->
 <!-- AppBannerModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 14:34:44      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-md-12">
         <h3 class="page-header">
             <#if toFlag == 'banner'>
                修改banner
             <#else>
                修改配图
             </#if>
             <button type="button" class="btn  btn-info-blue pull-right"
                     onclick="loadUrl('${ctx}/web/appbanner/toList/${toFlag}')"><i class="fa fa-mail-forward"></i>返回
             </button>
         </h3>
     </div>
 </div>


 <form id="form">
     <input type="hidden" name="whereUse" id="whereUsehide" value="${toFlag}">
     <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="isShow">配图类型</label>
			     <#--<@HrySelect key="whereuse"  name="whereUse"  id="whereUse" value="${model.whereUse}"   > </@HrySelect>-->
                 <#if toFlag == 'banner'>
                     <@HrySelect url="${ctx}/web/appbanner/whereUseList/${toFlag}" itemname="name" itemvalue="value" name="whereUse" id="whereUse" value="1" readonly="readonly"></@HrySelect>
                 <#else>
                     <@HrySelect url="${ctx}/web/appbanner/whereUseList/${toFlag}" itemname="name" itemvalue="value" name="whereUse1" id="whereUse1" value="${model.whereUse}"></@HrySelect>
                 </#if>
             </div>
             <div class="form-group">
                 <label for="name">图片名称</label>
                 <input type="text" class="form-control" name="name" id="name" value="${model.name}"/>
             </div>
             <div class="form-group">
                 <label for="applicationType">应用类型</label>
			     <@HrySelect key="applicationType"  name="applicationType"  id="applicationType"  value="${model.applicationType}"  > </@HrySelect>
             </div>
             <div class="form-group">
                 <label for="languageDic">系统语种</label>
			     <@HrySelect key="sysLanguage" name="langCode" id="languageDic" value="${model.langCode}"></@HrySelect>
             </div>
             <div class="form-group">
                 <label for="picturePath">上传图片</label>
                 <div class="hryUpload" style="width: 100%">
                     <div class="hry_inputBox">
                         <input class="form-control hryUpload_filePath " type="hidden" name="picturePath" value="${model.picturePath}"  >
                        <#if model.applicationType==0>
                             <button type="button" class="btn btn-primary btn-block" id="submitpicture">上传图片(电脑端尺寸1920px*785px)</button>
                        </#if>
                       <#if model.applicationType==1>
                             <button type="button" class="btn btn-primary btn-block" id="submitpicture">上传图片(移动端尺寸400px*190px)</button>
                       </#if>
                     </div>
                     <div class="hry_imgBox">

                     </div>
                 </div>
             </div>
             <div class="form-group">
                 <label for="sort">排序值</label>
                 <input type="number" class="form-control" name="sort" id="sort" value="${model.sort}"/>
             </div>
             <div class="form-group">
                 <label for="remark2">外链URL</label>
                 <input type="text" class="form-control" name="remark2" id="remark2" value="${model.remark2}"/>
             </div>
             <div class="form-group">
                 <label for="isShow">是否显示</label>
			     <@HrySelect key="yesno"  name="isShow"  id="isShow"  value="${model.isShow}"  > </@HrySelect>
             </div>
         </div>
     </div>


     <div class="row">
         <div class="col-md-2 column">
             <button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning">提交</button>
         </div>
     </div>
 </form>
     </div>

 <script type="text/javascript">
     seajs.use(["js/admin/web/AppBanner","js/base/HryUpload","js/base/HrySelect"], function (o,HryUpload,HrySelect) {
         HrySelect.init();
         HryUpload.upload();
         o.modify();
     });
 </script>






