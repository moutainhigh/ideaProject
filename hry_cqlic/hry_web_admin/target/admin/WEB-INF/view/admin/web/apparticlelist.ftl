<!-- Copyright:    -->
 <!-- AppArticleList.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-26 14:55:28      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <#--<div class="row">
        <div class="col-lg-12">
            <h3 class="page-header"><span id="articleContent"></span></h3>
        </div>
    </div>-->

    <div class="row">
        <div class="col-md-12">
            <form id="table_query_form">
                <div class="row">
                    <div class="col-md-3 column">
                        <div class="form-group">
                            <label for="sysLangId">选择语种</label>
						    <@HrySelect key="sysLanguage" name="sysLangId" id="sysLangId" ></@HrySelect>
                        </div>
                    </div>
                    <div class="col-md-3 column">
                        <div class="form-group">
                            <label for="title">文章标题</label>
                            <input type="text" class="form-control" name="title" id="title" tablesearch/>
                        </div>
                    </div>
                    <div class="col-md-3 column">
                        <div class="form-group">
                            <label for="articleCategor">文章类别</label>
                            <select class="form-control" id="articleCategor" name="langName" tablesearch></select>
                        </div>
                    </div>
                    <div class="col-md-3 column">
                        <div style="margin-top: 26px;">
                            <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                            <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                        </div>
                    </div>
                </div>
            </form>

            <div id="toolbar">
                <button id="add" class="btn btn-info-blue" onclick="loadUrl2Div(_ctx + '/v.do?u=/admin/web/apparticleadd', 'tree_right');">
                    <i class="glyphicon glyphicon-plus"></i>添加
                </button>
                <button id="see" class="btn btn-info-blue">
                    <i class="glyphicon glyphicon-share"></i>查看
                </button>
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
</div>
 <script type="text/javascript">
     seajs.use(["js/admin/web/AppArticle", "js/base/HrySelect"], function (o, HrySelect) {
         //获得分类id 和 父级id
         var categoryId = $("#tree_selected_id").val();
         var pid = $("#tree_selected_pid").val();
         /*$("#articleContent").html("【"+$("#tree_selected_name").val()+"】");*/
         HrySelect.init();
         o.init(categoryId, 'list');
         o.list();
     });
 </script>

