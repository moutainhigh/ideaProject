<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">单页面内容编辑
        </h3>
    </div>
</div>

<form id="form">
    <div class="row">
        <div class="col-md-6 column">
            <div class="form-group">
                <label for="preateId">文章类别ID</label>
                <input type="text" class="form-control" name="categoryId" id="categoryId" value="${categoryId}" readonly/>
            </div>
        </div>
        <div class="col-md-6 column">
            <div class="form-group">
                <label for="name">类别中文名称</label>
                <input type="text" class="form-control" name="langName" id="langName" value="${langName}" readonly/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th>语种名称</th>
                        <th>单页面内容</th>
                    </tr>
                </thead>
                <tbody>
                    <#if content??>
                        <#list content as item>
                            <tr>
                                <td style="width:90px;">${item.langType}</td>
                                <td>
                                    <div name="${item.dicId}" ueditorid="ueditor_${item.dicId}" class="appconfig ueditorparent">
                                        <textarea id="ueditor_${item.dicId}" class="ueditor" style="width:950px;height:300px;">${item.langContent}</textarea>
                                    </div>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 column">
            <input type="hidden" name="sysLanguages" id="sysLanguages" />
            <button type="button" id="addSubmit" class="btn btn-blue btn-block">保存</button>
        </div>
    </div>

</form>

<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
    $(".ueditor").each(function () {
        UE.getEditor($(this).attr("id"),{
            autoHeightEnabled: false
        });
    })
</script>

<script type="text/javascript">
    seajs.use("js/admin/web/AppCategory", function (s) {
        /*$("#articleCategory").html("【"+$("#tree_selected_name").val()+"】");*/
        s.singlePage();
    });
</script>