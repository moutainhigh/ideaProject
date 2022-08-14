 <!-- Copyright:    -->
 <!-- AppTransactionList.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-06 14:36:50      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">提现申请审核</h3>
     </div>
 </div>
 <div class="row">
     <div class="col-md-12">
        <div class="_params">
            <form id="table_query_form">
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">用户账号</label>
                        <input type="text" class="form-control" tablesearch name="userName"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">姓</label>
                        <input type="text" class="form-control" tablesearch name="surname"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">名</label>
                        <input type="text" class="form-control" tablesearch name="trueName"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="mobilePhone">交易订单号</label>
                        <input type="text" class="form-control" tablesearch name="transactionNum"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">银行卡号</label>
                        <input type="text" class="form-control" tablesearch name="custromerAccountNumber"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="entrustTime_GT">申请时间起</label>
                        <input type="text" class="form-control" name="created_GT" id="entrustTime_GT" readonly tablesearch
                               data-date-format="yyyy-mm-dd" data-min-view="month"
                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="entrustTime_GT">申请时间止</label>
                        <input type="text" class="form-control" name="created_LT" id="entrustTime_LT" readonly tablesearch
                               data-date-format="yyyy-mm-dd" data-min-view="month"
                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
                    </div>
                </div>
               <#-- <div class="col-md-4 column">
                    <div class="form-group">
                        <label for="changeCoin">交易币种</label>
                        <@HrySelect url="${ctx}//exchange/exproduct/findall"  itemvalue="coinCode"  itemname="coinCode"  name="changeCoin"  id="changeCoin"   > </@HrySelect>

                    </div>
                </div>-->
                <div class="col-md-2 column">
                    <div style="margin-top: 26px;">
                        <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                        <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                    </div>
                </div>
            </form>
        </div>
 	    <div id="toolbar">
             <#--<button id="add" class="btn btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/apptransactionadd')" >
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>-->
          <#--   <button id="see" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-share"></i>查看
 	        </button>-->
            <#--<button id="stop" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-edit"></i>驳回
 	        </button>
             <button id="confirm" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-remove"></i>通过
 	        </button>-->
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



     <div id="div_stop" style="display:none; top:20px;">
         <form style="text-align: center;">
             <input type="hidden" id="orderId">
             <input type="text" id="reason" placeholder="驳回原因" style="text-align: center;margin-top: 20px;"/>
             <div class="col-md-2 column" style="margin: 0px 135px -100px;">
                 <button type="button" id="confirmstop" class="btn  btn-info-blue btn-warning"
                         style="margin-top: 18px;margin-left: 8px;">提交
                 </button>
             </div>
         </form>
     </div>

     <#--  确认通过 -->
     <div class="row hide" id="fnConfirmDiv">
         <div class="col s8 ">
             <form style="text-align: left;margin-left: 40px;">
                 <input type="hidden" id="orderId"></input>
                 <p class="center mb-20" id="username">用户名：</p>
                 <p class="center mb-20" id="postmoney">充值金额：</p>
                 <p class="center mb-20" id="fee">充值手续费：</p>
                 <p class="center mb-20" id="factmoney">实际到账金额：</p>
                 谷歌验证码：<input type="text" class="center mb-20" id="google_code"/>
                 <p class="center mb-20">
                     <button class="btn" id="confirmSubmit" type="button">到账确认</button>
                     <button class="btn" onclick="layer.closeAll();">取消</button>
                 </p>
                 <span style="color:red">TIPS:到账确认操作不可逆，请确认充值金额已到账</span>

             </form>
         </div>
     </div>

     <#--  驳回 -->
     <div class="row hide" id="fnInvalidDiv">
         <div class="col s8 ">
             <form style="text-align: left;margin-left: 40px;">
                 <input type="hidden" id="rejectOrderId">
                 <p class="center mb-20" id="rejectUsername">用户名：</p>
                 <p class="center mb-20" id="rejectpostMoney">充值金额：</p>
                 驳回理由：<textarea id="reason" class="materialize-textarea" length="120"></textarea>

                 <br>
                 <p class="center mb-20">
                     <button class="btn" id="rejectConfirm">确认驳回</button>
                     <button class="btn" onclick="layer.closeAll();">取消</button>
                 </p>
                 <span style="color:red">TIPS:驳回操作不可逆，请确认充值操作无效</span>
             </form>
         </div>
     </div>
 </div>
 <script type="text/javascript">
 seajs.use(["js/admin/exchange/AppTransaction","js/base/HrySelect","js/base/HryDateTop"],function(o,HrySelect,t){
 	o.wapplylist();
     HrySelect.init();
     t.init();
 });

 </script>

