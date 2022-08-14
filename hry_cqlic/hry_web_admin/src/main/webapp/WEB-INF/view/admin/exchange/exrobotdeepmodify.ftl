<!-- Copyright:    -->
 <!-- exRobotDeepModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-12 20:31:39      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
      <div class="row">
          <div class="col-md-12">
              <h3 class="page-header">深度机器人参数配置
                  <button type="button" class="btn btn-info-blue pull-right"
                          onclick="loadUrl('${ctx}/v.do?u=admin/exchange/exrobotdeepparamlist')"><i
                          class="fa fa-mail-forward"></i>返回
                  </button>
              </h3>
          </div>
      </div>


      <form id="form">
          <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
          <div class="row">
              <div class="col-md-4 column">
                  <div class="form-group">
                      <label for="coinCode">交易币/交易区 </label>
                      <input class="form-control" type="input" readonly name="coinCode"
                             value="${model.coinCode}/${model.fixPriceCoinCode}" id="coinCode">
                  </div>
              </div>
          </div>

          <div class="row">
              <div class="col-md-4 column">
                  <div class="form-group">
                      <label for="buyDeep">委买目标档数</label>
                      <select class="form-control" id="buyDeep" name="buyDeep" value="${model.buyDeep}">
                          <option  value = "5">5</option>
                          <option  value = "10">10</option>
                          <option  value = "15">15</option>
                          <option  value = "20">20</option>

                      </select>
                  </div>
              </div>
              <div class="col-md-4 column">
                  <div class="form-group">
                      <label for="sellDeep">委卖目标档数</label>
                      <select class="form-control" id="sellDeep" name="sellDeep" value="${model.sellDeep}">
                          <option  value = "5">5</option>
                          <option  value = "10">10</option>
                          <option  value = "15">15</option>
                          <option  value = "20">20</option>

                      </select>
                  </div>
              </div>
          </div>

          <div class="row">
              <div class="col-md-4 column">
                  <div class="form-group">
                      <label for="everyEntrustCount">委量百分比上限</label>
                      <select class="form-control" id="everyEntrustCount" name="everyEntrustCount" value="${model.everyEntrustCount}">
                          <option  value = "1">1%</option>
                          <option  value = "2">2%</option>
                          <option  value = "3">3%</option>

                      </select>
                  </div>
              </div>
              <div class="col-md-4 column">
                  <div class="form-group">
                      <label for="stopLine">委量绝对值上限</label>

                      <input class="form-control" type="input"  name="stopLine"   value="${model.stopLine}" id="stopLine">
                  </div>
              </div>
          </div>
          <div class="row">
              <div class="col-md-4 column">
                  <div class="form-group">
                      <label for="buyOneDiffRate">买1差值率</label>

                      <select class="form-control" id="buyOneDiffRate" name="buyOneDiffRate" value="${model.buyOneDiffRate}">
                          <option  value = "0.5">0.5%</option>
                          <option  value = "0.75">0.75%</option>
                          <option  value = "1">1%</option>
                      </select>
                  </div>
              </div>
              <div class="col-md-4 column">
                  <div class="form-group">
                      <label for="sellOneDiffRate">卖1差值率</label>

                      <select class="form-control" id="sellOneDiffRate" name="sellOneDiffRate" value="${model.sellOneDiffRate}">
                          <option  value = "0.5">0.5%</option>
                          <option  value = "0.75">0.75%</option>
                          <option  value = "1">1%</option>
                      </select>
                  </div>
              </div>
          </div>


          <div class="row">
              <div class="col-md-2 column">
                  <button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning">提交</button>
              </div>
          </div>
      </form>
  </div>

 <script type="text/javascript">
     seajs.use("js/admin/exchange/ExRobotDeep", function (o) {
         o.modify();
     });
 </script>






