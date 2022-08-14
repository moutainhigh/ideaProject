<!-- Copyright:    -->
 <!-- AppMessageCategoryAdd.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-05 10:20:34      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
      <div class="row">
          <div class="col-md-12">
              <h3 class="page-header">添加站内信模板
                  <button type="button" class="btn btn-success pull-right"
                          onclick="loadUrl('${ctx}/web/appmessagecategory/gotoCategory?messageCategory=${messageCategory}')"><i
                          class="fa fa-mail-forward"></i>返回
                  </button>
              </h3>
          </div>
      </div>

      <input type="hidden" class="form-control" tablesearch name="messageCategory" id="languageDic" value="${messageCategory}"/>
      <form id="form">
          <div class="row">
              <div class="col-md-10 column">
                  <div class="form-group">
                      <label for="messageCategory">系统语种</label>
                      <@HrySelect key="sysLanguage" name="messageCategory" id="messageCategory" value="${messageCategory}"></@HrySelect>
                  </div>
                  <div class="form-group">
                      <label for="name">模板名称</label>
                      <input type="text" class="form-control" name="name" id="name"/>
                  </div>
                  <div class="form-group">
                      <label for="describes">模板内容</label>
                      <div ueditorid="ueditor_content" class="appconfig ueditorparent">
                          <textarea id="ueditor_content" name="describes" class="ueditor"></textarea>
                      </div>
                  </div>
                  <div class="form-group">
                      <label for="sort">模板触发点</label>
                      <@HrySelect key="trigger"  name="trigger"  id="trigger"   > </@HrySelect>
                  </div>
              </div>
          </div>

          <div class="row">
              <input type="hidden" name="appResourceSet" id="appResourceSet"  />
              <div class="col-md-2 column">
                  <button type="button" id="addSubmit" class="btn btn-blue btn-block">保存</button>
              </div>
          </div>

      </form>
  </div>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript">
    $(".ueditor").each(function () {
        UE.getEditor($(this).attr("id"), {
            autoHeightEnabled: false
        });
    });
</script>
<script type="text/javascript">
    seajs.use("js/admin/web/AppMessageCategory", function (o) {
        o.add();
    });
</script>




