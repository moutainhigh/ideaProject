<!-- Copyright:    -->
 <!-- AppMessageSee.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-05 10:21:55      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-lg-12">
             <h3 class="page-header">站内信草稿箱</h3>
         </div>
     </div>
     <div class="row">
         <div class="_params">
         </div>
     </div>
     <div class="row">
         <div class="col-md-12">
             <form id="table_query_form">
                 <input type="hidden" name="languageDic" id="languageDic" value="${langCode}" tablesearch>
                 <div class="row">
                     <div class="col-md-2 column">
                         <div class="form-group">
                             <label for="categoryName">消息标题</label>
                             <input type="text" class="form-control" name="title_like" id="content" tablesearch/>
                         </div>
                     </div>
                     <div class="col-md-2 column">
                         <div style="margin-top: 26px;">
                             <button type="button" id="table_query" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                             <button type="button" id="table_reset" class="btn btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>

                         </div>
                     </div>
                 </div>
             </form>
             <ul class="nav nav-tabs" id="lang_tab">
                <#if langList??>
                    <#list langList as item >
                        <#if (langCode == "none" && item_index==0) || langCode == item.value>
                            <li class="active"><a href="#${item.name}" data-toggle="tab" class="languageQuery" languageType="${item.value}" >${item.name}</a></li>
                        <#else>
                            <li ><a href="#${item.name}" data-toggle="tab" class="languageQuery" languageType="${item.value}">${item.name}</a>  </li>
                        </#if>
                    </#list>
                </#if>
             </ul>
             <div id="toolbar">
                 <button id="send_btn" class="btn btn-info-blue"">
                     <i class="glyphicon glyphicon-plus"></i>发送
                 </button>
                 <button type="button" id="table_modify" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-plus"></i>编辑
                 </button>
                 <button id="remove" class="btn  btn-info-blue">
                     <i class="glyphicon glyphicon-remove"></i>撤销
                 </button>
             </div>
             <table id="table"
                    data-toolbar="#toolbar"
                    data-show-refresh="true"
                    data-show-columns="false"
                    data-show-export="false"
                    data-search="false"
                    data-detail-view="false"
                    data-minimum-count-columns="2"
                    data-pagination="true"
                    data-id-field="id"
                    data-page-size="20"
                    data-page-list="[10, 25, 50, 100]"
                    data-show-footer="false"
                    data-side-pagination="server"
             >
             </table>
         </div>
     </div>

     <div class="row">
     </div>
 </div>
 <script type="text/javascript">
     seajs.use(["js/admin/web/AppMessage","js/base/HrySelect"], function (o, HrySelect) {
         HrySelect.init();
         o.see();
     });
 </script>




