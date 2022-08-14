<!-- Copyright:    -->
 <!-- AppCategoryList.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-26 14:46:47      -->

<#include "/base/base.ftl">

<div class="row">
    <div class="col-md-12">
        <div id="toolbar">
            <button id="add" class="btn btn-info-blue" onclick="loadUrl2Div(_ctx+'/web/appcategory/toAdd.do','tree_right')">
                <i class="glyphicon glyphicon-plus"></i>添加
            </button>
            <#--<button id="singlePage" class="btn btn-info-blue">
                <i class="glyphicon glyphicon-share"></i>单页面内容编辑
            </button>-->
            <button id="modify" class="btn btn-info-blue">
                <i class="glyphicon glyphicon-edit"></i>编辑
            </button>
            <button id="remove" class="btn btn-info-blue">
                <i class="glyphicon glyphicon-remove"></i>删除
            </button>
        </div>
        <table id="table"
               data-toolbar="#toolbar"
               data-show-refresh="false"
               data-show-columns="false"
               data-show-export="false"
               data-search="false"
               data-detail-view="false"
               data-minimum-count-columns="2"
               data-pagination="true"
               data-id-field="id"
               <#--data-page-size="20"-->
               data-page-list="[10, 25, 50, 100]"
               data-show-footer="false"
               data-side-pagination="server"
        >
        </table>
    </div>
</div>

<div class="row">
</div>

 <script type="text/javascript">
     seajs.use("js/admin/web/AppCategory", function (o) {
         //获得分类id 和 父级id
         var categoryId = $("#tree_selected_id").val();
         var pid = $("#tree_selected_pid").val();
         //$("#articleCategory").html("【"+$("#tree_selected_name").val()+"】");
         if (pid != '0') {
             $("#add").hide();
         } else {
             $("#add").show();
         }
         o.list(categoryId);
     });
 </script>

