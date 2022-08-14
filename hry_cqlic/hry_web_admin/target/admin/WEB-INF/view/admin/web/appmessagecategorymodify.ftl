<!-- Copyright:    -->
 <!-- AppMessageCategoryModify.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-05 10:20:34      -->

<#include "/base/base.ftl">
   <div class="centerRowBg centerRowBg_admin">
       <div class="row">
           <div class="col-md-12">
               <h3 class="page-header">修改站内信模板
                   <button type="button" class="btn btn-warning pull-right"
                           onclick="loadUrl('${ctx}/web/appmessagecategory/gotoCategory?messageCategory=${messageCategory}')"><i
                           class="fa fa-mail-forward"></i>返回
                   </button>
               </h3>
           </div>
       </div>

       <form id="form">
           <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
           <input type="hidden" class="form-control" tablesearch name="messageCategory" id="languageDic" value="${messageCategory}"/>
           <div class="row">
               <div class="col-md-10 column">
                   <div class="form-group">
                       <label for="messageCategory">消息语种</label>
                       <@HrySelect key="sysLanguage" name="messageCategory" id="messageCategory" value="${model.messageCategory}"></@HrySelect>
                   </div>
                   <div class="form-group">
                       <label for="name">模板名称</label>
                       <input type="text" class="form-control" name="name" id="name" value="${model.name}"/>
                   </div>
                   <div class="form-group">
                       <label for="name">模板内容</label>
                       <div ueditorid="ueditor_content" class="appconfig ueditorparent">
                           <textarea id="ueditor_content"  name="describes"  class="ueditor"></textarea>
                       </div>

                   </div>

                   <div class="form-group">
                       <label for="sort">触发点</label>
                        <@HrySelect  key="trigger"  name="trigger"  id="trigger"  value="${model.trigger}" > </@HrySelect>
                   </div>
               </div>
           </div>

           <div class="row">
               <div class="col-md-2 column">
                   <button type="button" id="modifySubmit" class="btn btn-blue btn-block">保存</button>
               </div>
           </div>
       </form>
   </div>
 <input type="hidden" value="${model.describes}" id="titlecontent">
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
 <script type="text/javascript">
     $(".ueditor").each(function () {
         UE.getEditor($(this).attr("id"), {
             autoHeightEnabled: false
         });
     });
     //初始化ueditor
     var ue = UE.getEditor('ueditor_content');
     var titlecontent=$("#titlecontent").val();
     ue.ready(function() {//编辑器初始化完成再赋值
         ue.setContent(titlecontent);  //赋值给UEditor
     });

 </script>

 <script type="text/javascript">
     seajs.use("js/admin/web/AppMessageCategory", function (o) {
         o.modify();
     });
 </script>






