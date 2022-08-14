<!-- Copyright:    -->
 <!-- AppCategoryModify.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-26 14:46:48      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">修改类别信息
            <button type="button" class="btn btn-info-blue pull-right"
                    onclick="loadUrl2Div(_ctx + '/v.do?u=/admin/web/appcategorylist', 'tree_right');"><i class="fa fa-mail-forward"></i>返回
            </button>
        </h3>
    </div>
</div>

<form id="form">
    <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="preateId">文章类别ID</label>
                <input type="text" class="form-control" name="cid" id="cid" value="${model.id}" readonly/>
            </div>
            <div class="form-group">
                <label for="preateName">父级分类名称</label>
                <input type="text" class="form-control" name="pname" id="pname" value="${model.preateName}" readonly/>
            </div>
            <div class="form-group">
                <label for="name">类别名称</label>
                <input type="text" class="form-control" name="name" id="name" value="${model.name}"/>
            </div>
            <#if model.langInfo??>
                <#list model.langInfo as item>
                    <div class="form-group">
                        <label for="${item.langType}">${item.langType}</label>
                        <input type="text" class="form-control" ltype="categoryLang" name="${item.dicKey}" id="${item.dicKey}" value="${item.langName}"/>
                    </div>
                </#list>
            </#if>
            <div class="form-group">
                <label for="sort">排序</label>
                <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}"/>
            </div>
            <div class="form-group">
                <label for="isPage">页面类别</label>
                 <@HrySelect key="pageType" name="isPage" id="isPage" value="${model.isPage}"></@HrySelect>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-2 column">
            <input type="hidden" name="sysLanguages" id="sysLanguages" />
            <button type="button" id="modifySubmit" class="btn btn-blue btn-block">提交</button>
        </div>
    </div>
</form>

<script type="text/javascript">
    seajs.use(["js/admin/web/AppCategory","js/base/HrySelect"], function (o, HrySelect) {
        HrySelect.init();
        o.modify();
    });
</script>






