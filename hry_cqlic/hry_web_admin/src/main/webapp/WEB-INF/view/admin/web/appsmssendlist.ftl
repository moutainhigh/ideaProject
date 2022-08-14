<!-- Copyright:    -->
 <!-- AppSmsSendList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 14:42:05      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
      <div class="row">
          <div class="col-lg-12">
              <h3 class="page-header">短信发送记录</h3>
          </div>
      </div>
      <div class="row">
          <div class="col-md-12">
              <form id="table_query_form">
                  <div class="row">
                      <div class="col-md-4 column">
                          <div class="form-group">
                              <label for="postParam_like">请求参数(手机号)</label>
                              <input type="text" class="form-control" name="postParam_like" id="postParam_like" tablesearch/>
                          </div>
                      </div>
                      <div class="col-md-4 column">
                          <div class="form-group">
                              <label for="sendStatus_EQ">请选择状态</label>
                              <select class="form-control" id="sendStatus_EQ" name="sendStatus_EQ">
                                  <option value="">不限</option>
                                  <option value="0">未发送</option>
                                  <option value="1">已发送</option>
                              </select>
                          </div>
                      </div>
                      <div class="col-md-4 column">
                          <div style="margin-top: 26px;">
                              <button type="button" id="table_query" class="btn  btn-primary"><i
                                      class="glyphicon glyphicon-search"></i>查询
                              </button>
                              <button type="button" id="table_reset" class="btn  btn-primary"><i
                                      class="glyphicon glyphicon-refresh"></i>重置
                              </button>
                          </div>
                      </div>
                  </div>
              </form>

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


  </div>

 <script type="text/javascript">
     seajs.use("js/admin/web/AppSmsSend", function (o) {
         o.list();
     });
 </script>

