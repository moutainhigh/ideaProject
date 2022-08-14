 <!-- Copyright:    -->
 <!-- AppLanguageList.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 17:47:13      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-lg-12">
                <#if langType == 'frontPageName'><h3 class="page-header">前台页面名称</h3>
                <#elseif langType == 'navMenuName'><h3 class="page-header">导航菜单名称</h3>
                <#elseif langType == 'registLoginTerms'><h3 class="page-header">注册登录词汇</h3>
                <#elseif langType == 'tradingVocabulary'><h3 class="page-header">交易相关词汇</h3>
                <#elseif langType == 'personalCenter'><h3 class="page-header">个人中心词汇</h3>
                <#elseif langType == 'other'><h3 class="page-header">其他</h3>
                <#elseif langType == 'otc'><h3 class="page-header">OTC词汇</h3>
                <#elseif langType == 'wallet'><h3 class="page-header">钱包词汇</h3>
                <#else><h3 class="page-header">全部</h3>
            </#if>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="_params">
                <form id="table_query_form">
                    <input type="hidden" class="form-control" tablesearch name="languageDic" id="languageDic" tablesearch/>
                    <input type="hidden" name="langType" id="langType" value="${langType}" tablesearch>
                    <input type="hidden" name="wordSource" id="wordSource" value="${wordSource}" tablesearch>
                    <div class="row">
                        <div class="col-md-2 column">
                            <div class="form-group">
                                <label for="langKey_like">langKey</label>
                                <input type="text" class="form-control" name="langKey_like" id="langKey" tablesearch/>
                            </div>
                        </div>
                        <div class="col-md-2 column">
                            <div class="form-group">
                                <label for="langVal_like">langVal</label>
                                <input type="text" class="form-control" name="langVal_like" id="langVal" tablesearch/>
                            </div>
                        </div>
                        <#if langType == "" || langType == "all" || langType == "appAll">
                            <div class="col-md-2 column">
                                <div class="form-group">
                                    <label for="langType_like">langType</label>
                                    <input type="text" class="form-control" name="langType_like" id="langType" tablesearch/>
                                </div>
                            </div>
                        </#if>
                        <div class="col-md-2 column">
                            <div style="margin-top: 26px;">
                                <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                                <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                            </div>
                        </div>
                    </div>
                </form>
                <ul class="nav nav-tabs" id="lang_tab">
 					<#if langList??>
                        <#list langList as item >
                            <#if item_index==0>
                                <li class="active"><a href="#${item.name}" data-toggle="tab" class="languageQuery" languageType="${item.value}" >${item.name}</a></li>
                            </#if>
                            <#if item_index!=0>
                                <li ><a href="#${item.name}" data-toggle="tab" class="languageQuery" languageType="${item.value}">${item.name}</a>  </li>
                            </#if>
                        </#list>
					</#if>
                </ul>
            </div>
            <div id="toolbar">
                <button id="add" class="btn btn-info-blue" >
                    <i class="glyphicon glyphicon-plus"></i>添加
                </button>
                <#--<button id="remove" class="btn btn-info-blue" >
                    <i class="glyphicon glyphicon-remove"></i>删除
                </button>-->
            </div>
            <table id="table"
                   data-toolbar="#toolbar"
                   data-show-refresh="true"
                   data-show-columns="false"
                   data-show-export="false"
                   data-search="false"
                   data-detail-view="true"
                   data-minimum-count-columns="2"
                   data-pagination="true"
                   data-id-field="id"
                   data-page-size="100"
                   data-page-list="[10, 25, 50, 100]"
                   data-show-footer="false"
                   data-side-pagination="server"
            >
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
	seajs.use("js/admin/web/AppLanguage",function(l){
		l.page();
	});
</script>


