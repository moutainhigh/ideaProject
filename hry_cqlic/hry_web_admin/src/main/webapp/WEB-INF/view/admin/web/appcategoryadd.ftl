<!-- Copyright:    -->
 <!-- AppCategoryAdd.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-26 14:46:47      -->

<#include "/base/base.ftl">

 <div class="row">
     <div class="col-md-12">
         <h3 class="page-header">添加文章类别
             <button type="button" class="btn btn-info-blue pull-right"
                     onclick="loadUrl2Div(_ctx + '/v.do?u=/admin/web/appcategorylist', 'tree_right');"><i
                     class="fa fa-mail-forward"></i>返回
             </button>
         </h3>
     </div>
 </div>

 <form id="form">
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="preateId">父级分类</label>
                 <input type="text" class="form-control" name="preateName" id="preateName" readonly/>
                 <input type="hidden" name="preateId" id="preateId">
             </div>
             <div class="form-group">
                 <label for="isPage">页面类别</label>
                 <select class="form-control" id="isPageSelect" name="isPage"></select>
             </div>
             <div class="form-group">
                 <label for="sort">排序</label>
                 <input type="text" class="form-control" name="sort" id="sort"/>
             </div>
             <div class="form-group">
                 <label for="name">类别名称</label>
                 <input type="text" class="form-control" name="name" id="name"/>
             </div>
             <#if langList??>
                <#list langList as item>
                    <div class="form-group">
                        <label for="${item.name}">${item.name}</label>
                        <input type="text" class="form-control" ltype="categoryLang" name="${item.value}" id="${item.value}"/>
                    </div>
                </#list>
             </#if>
         </div>
     </div>

     <div class="row">
         <div class="col-md-2 column">
             <input type="hidden" name="appResourceSet" id="appResourceSet" />
             <input type="hidden" name="sysLanguages" id="sysLanguages" />
             <button type="button" id="addSubmit" class="btn btn-blue btn-block">提交</button>
         </div>
     </div>

 </form>

<script type="text/javascript">
    seajs.use(["js/admin/web/AppCategory","js/base/HrySelect"], function (o, HrySelect) {
        var categoryId = $("#tree_selected_id").val();
        var parentName = $("#tree_selected_name").val();
        var isPage = $("#tree_selected_isPage").val();
        $("#preateName").val(parentName);
        $("#preateId").val(categoryId);
        o.initPage(isPage);
        HrySelect.init();
        o.add();
    });
</script>




