<!-- Copyright:    -->
 <!-- ExDigitalmoneyAccountList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 10:56:33      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">用户货币账户管理</h3>
     </div>
 </div>

 <div class="row">
     <div class="col-md-12">
         <form id="table_query_form">
         <div class="row">
             <div class="_params">
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="email">邮箱</label>
                         <input type="text" class="form-control" tablesearch name="email"/>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="mobilePhone">手机号</label>
                         <input type="text" class="form-control" tablesearch name="mobilePhone"/>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="email">姓氏</label>
                         <input type="text" class="form-control" tablesearch name="surname"/>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="email">名字</label>
                         <input type="text" class="form-control" tablesearch name="trueName"/>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="email">钱包地址</label>
                         <input type="text" class="form-control" tablesearch name="publicKey"/>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="coinCode">交易币种</label>
                         <@HrySelect url="${ctx}/exchange/exproduct/findall"  itemvalue="coinCode"  itemname="coinCode"  name="coinCode"  id="coinCode"   > </@HrySelect>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                     <div style="margin-top: 26px;">
                         <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                         <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                     </div>
                 </div>
             </div>
         </div>
         </form>

         <div id="toolbar">
             <button id="manualLock" class="btn btn-info-blue" permissions="/admin/exchange/exdigitalmoneyaccount/manualLock">
                 <i class="glyphicon glyphicon-share"></i>手动锁仓
             </button>
             <button id="recharge" class="btn btn-info-blue" permissions="/admin/exchange/exdigitalmoneyaccount/recharge">
                 <i class="glyphicon glyphicon-share"></i>手动充币
             </button>
             <button id="getCoin" class="btn btn-info-blue" permissions="/admin/exchange/exdigitalmoneyaccount/getCoin">
                 <i class="glyphicon glyphicon-share"></i>手动提币
             </button>
             <button id="refreshUserCoin" class="btn btn-info-blue" permissions="/admin/exchange/exdigitalmoneyaccount/refreshUserCoin">
                 <i class="glyphicon glyphicon-share"></i>刷新充币记录
             </button>
             <button id="refreshUserETH" class="btn btn-info-blue" permissions="/admin/exchange/exdigitalmoneyaccount/refreshUserETH">
                 <i class="glyphicon glyphicon-share"></i>刷新以太坊充币记录
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
     seajs.use(["js/admin/exchange/ExDigitalmoneyAccount","js/base/HrySelect"], function (o,HrySelect) {
         HrySelect.init();
         o.init();
         o.list();
     });

 </script>


 <div id="div_recharge" style="display:none; top:20px;">
     <form style="text-align: center;">
         充币数量:
         <input type="number" id="recharge_number" value="" placeholder="" style="text-align: center;margin-top: 20px;"/><br>
         谷歌验证码:
         <input type="number" id="google_code" value=""  style="text-align: center;margin-top: 20px;"/>
         <div class="col-md-2 column" style="margin: 0px 135px -100px;">
             <button type="button" id="rechargeSubmit" class="btn  btn-info-blue btn-warning"
                     style="margin-top: 18px;margin-left: 8px;">提交
             </button>
         </div>
     </form>
 </div>


 <div id="div_getCoin" style="display:none; top:20px;">
     <form style="text-align: center;">
         <input type="text" id="getCoin_number" placeholder="提币数量" style="text-align: center;margin-top: 20px;"/>
         <div class="col-md-2 column" style="margin: 0px 135px -100px;">
             <button type="button" id="getCoinSubmit" class="btn  btn-info-blue btn-warning"
                     style="margin-top: 18px;margin-left: 8px;">提交
             </button>
         </div>
     </form>
 </div>

<div id="refreshUserEthDiv" style="display:none; top:20px;">
    <form style="text-align: center;">
        <input type="input" id="hash" placeholder="请输入hash值" style="text-align: center;margin-top: 20px;"><br>
        <input type="input" id="type" placeholder="请输入币种" style="text-align: center;margin-top: 20px;"/><br>
        <div class="col-md-2 column" style="margin: 0px 135px -100px;">
            <button type="button" id="refreshEthDiv" class="btn  btn-info-blue btn-warning"
                    style="margin-top: 18px;margin-left: 8px;">提交
            </button>
        </div>
    </form>
</div>

<div id="manualLockDiv" style="display:none;top: 20px">
    <form style="text-align: center;">
        <label>锁仓数量：</label><input type="input" id="lockNum" autocomplete="off" style="text-align: center;margin-top: 20px;"><br>
        <label>锁仓期(天)：</label><input type="input" id="lockCycle" autocomplete="off" style="text-align: center;margin-top: 20px;"/><br>
        <label>释放方式：</label><select id="releaseMethod" style="text-align: center;margin-top: 20px;width: 88px;height: 28px;"></select>
        <input type="input" id="releaseMethodVal" autocomplete="off" style="text-align: center;margin-top: 20px;width: 88px;"/><br>
        <div class="col-md-2 column" style="margin: 0px 135px -100px;">
            <button type="button" id="manualLockBtn" class="btn  btn-info-blue btn-warning"
                    style="margin-top: 18px;margin-left: 8px;">提交
            </button>
        </div>
    </form>
</div>
