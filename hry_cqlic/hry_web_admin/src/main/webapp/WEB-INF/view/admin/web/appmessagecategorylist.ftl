<!-- Copyright:    -->
 <!-- AppMessageCategoryList.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-05 10:20:34      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-lg-12">
             <h3 class="page-header">系统信件模板</h3>
         </div>
     </div>
     <div class="row">
         <div class="_params">
         </div>
     </div>
     <div class="row">
         <div class="col-md-12">
             <form id="table_query_form">
                 <input type="hidden" class="form-control" tablesearch name="messageCategory" id="languageDic" value="${messageCategory}"/>
             </form>
             <ul class="nav nav-tabs">
 					<#if keyList ??>
	 				<#list keyList as item >
                        <#--<#if item.value==keyList[0].value>
							<li <#if messageCategory == item.value>class="active"</#if>><a href="#${item.name}" data-toggle="tab" class="languageQuery" languageType="${item.value}" >${item.name}</a></li>
                        </#if>
                        <#if item.value!=keyList[0].value>
							<li ><a href="#${item.name}"  data-toggle="tab" class="languageQuery" languageType="${item.value}">${item.name}</a>  </li>
                        </#if>-->
                        <li <#if messageCategory == item.value>class="active"</#if>><a href="#${item.name}" data-toggle="tab" class="languageQuery" languageType="${item.value}" >${item.name}</a></li>
                    </#list>
                    </#if>
             </ul>
             <div id="toolbar">
                 <button id="add" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-plus"></i>添加
                 </button>
                 <button id="modify" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-edit"></i>修改
                 </button>
                 <button id="open_btn" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-share"></i>开启
                 </button>
                 <button id="close_btn" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-share"></i>关闭
                 </button>
            <#--     <button id="remove" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-remove"></i>删除
                 </button>-->
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
     seajs.use("js/admin/web/AppMessageCategory", function (o) {
         o.list();
     });

 </script>

