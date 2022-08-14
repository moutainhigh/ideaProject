<!-- Copyright:    -->
 <!-- AppMessageTemplateAdd.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-05 10:23:25      -->

<#include "/base/base.ftl">
   <div class="centerRowBg centerRowBg_admin">
       <div class="row">
           <div class="col-md-12">
               <h3 class="page-header">站内消息模板配置</h3>
           </div>
       </div>

       <form id="form">
           <div class="row">
               <div class="col-md-12">
                   <table class="table table-striped table-bordered table-hover" id="templateTable">
                       <#if listKey??>
                           <#list listKey as item>
                               <tr>
                                   <td width="15%" name="templateId" tid="${item.id}">${item.name}消息模板</td>
                                   <td width="25%"><input type="text" class="form-control" name="title_${item.id}" value="${item.title}" /></td>
                                   <td width="60%"><input type="text" class="form-control" name="content_${item.id}" value="${item.content}" /></td>
                               </tr>
                           </#list>
                       </#if>
                   </table>
               </div>
           </div>

           <div class="row">
               <div class="col-md-2 column">
                   <button type="button" id="addSubmit" class="btn btn-blue btn-block">提交</button>
               </div>
           </div>

       </form>
   </div>
<script type="text/javascript">
    seajs.use("js/admin/web/AppMessageTemplate", function (o) {
        o.add();
    });
</script>




