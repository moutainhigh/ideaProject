<!-- Copyright:    -->
 <!-- AppMessageList.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-05 10:21:55      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
      <div class="row">
          <div class="col-lg-12">
              <h3 class="page-header">手动发送记录</h3>
          </div>
      </div>
      <div class="row">
          <div class="col-md-12">
              <form id="table_query_form">

                      <div class="col-md-2 column">
                          <div class="form-group">
                              <label for="title">标题关键词</label>
                              <input type="text" class="form-control" name="title_like" id="title" tablesearch/>
                          </div>
                      </div>
                      <div class="col-md-2 column">
                          <div class="form-group">
                              <label for="langCode">语种</label>
                              <@HrySelect key="sysLanguage" name="messageCategory" id="langCode"></@HrySelect>
                          </div>
                      </div>
                      <div class="col-md-2 column">
                          <div class="form-group">
                              <label for="messageCategory">发送时间起始</label>
                              <input type="text" class="form-control" name="sendDate_GT" id="sendDate_GT" readonly  tablesearch  data-date-format="yyyy-mm-dd" data-min-view="month" value="${(info.birthday?string("yyyy-MM-dd"))!}">
                          </div>
                      </div>
                      <div class="col-md-2 column">
                          <div class="form-group">
                              <label for="categoryName">发送时间截止</label>
                              <input type="text" class="form-control" name="sendDate_LT" id="sendDate_LT" readonly tablesearch  data-date-format="yyyy-mm-dd" data-min-view="month" value="${(info.birthday?string("yyyy-MM-dd"))!}">
                          </div>
                      </div>
                      <div class="col-md-2 column">
                          <div class="form-group">
                              <label for="title">状态</label>
                              <@HrySelect key="messageStates" name="isSend" id="isSend" ></@HrySelect>
                          </div>
                      </div>
                      <div class="col-md-2 column">
                          <div style="margin-top: 26px;">
                              <button type="button" id="table_query" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                              <button type="button" id="table_reset" class="btn btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>

                          </div>
                      </div>
              </form>
              <div id="toolbar">
                  <button type="button" id="table_modify" class="btn btn-info-blue"><i class="glyphicon glyphicon-plus"></i>查看</button>
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
     seajs.use(["js/admin/web/AppMessage","js/base/HrySelect","js/base/HryDate"], function (o, HrySelect,HryDate) {
         HrySelect.init();
         HryDate.init();
         o.list();
     });
 </script>

