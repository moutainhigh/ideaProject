<!-- Copyright:    -->
 <!-- AppArticleSee.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-26 14:55:28      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <h3 class="page-header">文章基础信息
                <button type="button" class="btn btn-info-blue pull-right"
                        onclick="loadUrl2Div(_ctx + '/v.do?u=admin/web/apparticlelist', 'tree_right');"><i class="fa fa-mail-forward"></i>返回
                </button>
            </h3>
        </div>
    </div>

    <form id="form">
        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="sysLangId">选择语种</label>
                    <@HrySelect key="sysLanguage" name="sysLangId" id="sysLangId" value="${sysLangId}"></@HrySelect>
                </div>
                <div class="form-group">
                    <label for="title">文章标题</label>
                    <input type="text" class="form-control" name="title" id="title" value="${model.title}" readonly/>
                </div>
            </div>
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="companySet">文章类别</label>
                    <input type="hidden" id="categoryId" value="${model.categoryId}"/>
                    <select class="form-control" id="articleCategor" name="categoryId" disabled tablesearch></select>
                </div>
                <div class="form-group">
                    <label for="writer">文章作者</label>
                    <input type="text" class="form-control" name="writer" id="writer" value="${model.writer}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label>是否置顶</label>
                    <@HrySelect key="yesno" name="isStick" id="isStick" value="${model.isStick}"></@HrySelect>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label>是否外链</label>
                    <@HrySelect key="yesno" name="isOutLink" id="isOutLink" value="${model.isOutLink}"></@HrySelect>
                </div>
            </div>
        </div>
        <div class="row ">
            <div class="col-md-12">
                <div class="form-group">
                    <label for="outLink">外链地址</label>
                    <input type="text" class="form-control" name="outLink" id="outLink" value="${model.outLink}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="source">文章来源</label>
                    <input type="text" class="form-control" name="source" id="source" value="${model.source}" readonly/>
                </div>
            </div>
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="hits">文章点击量</label>
                    <input type="number" class="form-control" name="hits" id="hits" value="${model.hits}" readonly/>
                </div>
            </div>
        </div>
        <div class="row" style="display: none;">
            <div class="col-md-6">
                <div class="form-group">
                    <div class="hryUpload">
                        <div class="hry_inputBox">
                            <input class="form-control hryUpload_filePath appconfig" type="hidden"
                                   name="titleImage" value="${model.titleImage}">
                            <button type="button" class="btn btn-primary btn-block">上传图片(规格:130px*130px)
                            </button>
                        </div>
                        <div class="hry_imgBox">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 column">
                <div class="form-group">
                    <label for="shortContent">文章简介</label>
                    <div>
                        <textarea type="text" id="shortContent" name="shortContent" readonly
                                  style="width: 100%;height: 100px">${model.shortContent}</textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 column">
                <div class="form-group">
                    <label for="content">文章内容</label>
                    <div ueditorid="ueditor_content" class="appconfig ueditorparent">
                        <textarea id="ueditor_content" class="ueditor">${model.content}</textarea>
                    </div>
                </div>
            </div>
        </div>
        <!--内容-->
        <div class="row ">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="seoTitle">SEO标题</label>
                    <input type="text" class="form-control" name="seoTitle" id="seoTitle" value="${model.seoTitle}" readonly/>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="seoKeyword">SEO关键字</label>
                    <input type="text" class="form-control" name="seoKeyword" id="seoKeyword" value="${model.seoKeyword}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="form-group">
                    <label for="seoDescribe">SEO描述</label>
                    <div>
                        <textarea type="text" id="seoDescribe" name="seoDescribe" readonly
                                  style="width: 100%;height: 100px">${model.seoDescribe}</textarea>
                    </div>
                </div>
            </div>
        </div>
        <!--/内容-->
    </form>
</div>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
    $(".ueditor").each(function () {
        UE.getEditor($(this).attr("id"), {
            autoHeightEnabled: false,
            initialFrameHeight: 600
        });
    })
</script>

<script type="text/javascript">
    seajs.use(["js/admin/web/AppArticle", "js/base/HryUpload", "js/base/HrySelect"], function (o, HryUpload, HrySelect) {
        //获得分类id 和 父级id
        var categoryId = $("#tree_selected_id").val();
        var pid = $("#tree_selected_pid").val();
        o.init(categoryId, "see");
        HryUpload.upload();
        HrySelect.init();
        o.see();
    });
</script>