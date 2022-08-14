<!-- Copyright:    -->
 <!-- ExDmLockRecordList.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-29 16:05:59      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
      <div class="row">
          <div class="col-lg-12">
              <h3 class="page-header">锁仓记录</h3>
          </div>
      </div>
      <div class="row">
          <div class="_params">
          </div>
      </div>
      <div class="row">
          <div class="col-md-12">
              <form id="table_query_form">
                  <div class="row">
                      <div class="col-md-2 column">
                          <div class="form-group">
                              <label for="email">邮箱</label>
                              <input type="text" class="form-control" name="email" id="email" >
                          </div>
                      </div>
                      <div class="col-md-2 column">
                          <div class="form-group">
                              <label for="mobilePhone">手机号</label>
                              <input type="text" class="form-control" name="mobilePhone" id="mobilePhone" >
                          </div>
                      </div>
                      <div class="col-md-2 column">
                          <div class="form-group">
                              <label for="coinCode">币种代码</label>
                              <@HrySelect url="${ctx}/exchange/exproduct/findall" itemvalue="coinCode" itemname="coinCode" name="coinCode" id="coinCode"></@HrySelect>
                          </div>
                      </div>
                      <div class="col-md-2 column">
                          <div class="form-group">
                              <label for="unlockState">解锁状态</label>
                              <@HrySelect key="unlockState" name="unlockState" id="unlockState"></@HrySelect>
                          </div>
                      </div>
                      <div class="col-md-2 column">
                          <div style="margin-top: 26px;">
                              <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                              <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                          </div>
                      </div>
                  </div>
              </form>
              <div id="toolbar">
                  <button id="downloadTemp" class="btn btn-info-blue">
                      <i class="glyphicon glyphicon-edit"></i>excel模板下载
                  </button>
                  <button id="import" class="btn btn-info-blue">
                      <i class="glyphicon glyphicon-plus"></i>导入excel
                  </button>
                  <button id="unLock" class="btn btn-info-blue">
                      <i class="glyphicon glyphicon-edit"></i>手动解锁
                  </button>
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
     seajs.use(["js/admin/lock/ExDmLockRecord","js/base/HrySelect"], function (o, HrySelect) {
         HrySelect.init();
         o.lockList();
     });
 </script>
<#--释放数量弹出层-->
<div id="div_unlock" style="display:none; top:20px;">
    <form id="unlockForm" style="text-align: center;">
        <div class="form-group">
            <label for="unlock">解锁数量：</label>
            <input type="text" id="unlock_number" autocomplete="off" style="margin-top: 20px;"/><br>
        </div>
        <div class="col-md-2 column" style="margin: 0px 135px -100px;">
            <button type="button" id="unlockSubmit" class="btn btn-info-blue btn-warning"
                    style="margin-top: 18px;margin-left: 8px;">提交
            </button>
        </div>
    </form>
</div>

<#--导入excel弹出层-->
<div id="div_excel" style="display:none; top:20px;">
    <form id="excelForm" style="text-align: center;">
        <div class="form-group">
            <input type="file" name="file" id="excel_file" style="margin-top: 20px;"/><br>
            <button type="button" id="excelSubmit" class="btn btn-info-blue btn-warning"
                    style="margin-top: 18px;margin-left: 8px;">上传
            </button>
        </div>
    </form>
</div>
