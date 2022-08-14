<!-- Copyright:    -->
 <!-- AppLanguageAdd.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 17:47:13      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <h3 class="page-header">添加国际化配置
                <button type="button" class="btn btn-success pull-right"
                        onclick="loadUrl('${ctx}/web/applanguage/toPage/${langType}/${wordSource}')"><i class="fa fa-mail-forward"></i>返回
                </button>
            </h3>
        </div>
    </div>

    <form id="form">
        <input type="hidden" id="wordSource" name="wordSource" value="${wordSource}">
        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="langKey">langKey</label>
                    <input type="text" class="form-control" name="langKey" id="langKey"/>
                </div>
                <#if langList??>
                    <#list langList as item>
                        <div class="form-group">
                            <label for="langVal_${item.name}">langVal_${item.name}</label>
                            <input type="text" class="form-control" name="langVal_${item.value}" id="langVal_${item.value}"/>
                        </div>
                    </#list>
                </#if>
                <div class="form-group" style="display: none;">
                    <label for="langType">langType</label>
                    <input type="text" class="form-control" name="langType" id="langType" value="${langType}"/>
                </div>
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
    seajs.use("js/admin/web/AppLanguage", function (o) {
        o.add();
    });
</script>