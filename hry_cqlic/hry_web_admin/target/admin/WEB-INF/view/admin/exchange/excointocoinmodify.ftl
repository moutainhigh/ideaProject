<!-- Copyright:    -->
 <!-- ExCointoCoinModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:52:02      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-md-12">
         <h3 class="page-header">修改币币交易
             <button type="button" class="btn  btn-info-blue pull-right"
                     onclick="loadUrl('${ctx}/exchange/excointocoin/toList/${tradeArea}')"><i
                     class="fa fa-mail-forward"></i>返回
             </button>
         </h3>
     </div>
 </div>


 <form id="form">
     <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
     <input type="hidden" name="tradeArea" id="tradeArea" value="${tradeArea}">
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="coinCode">交易币种</label>
                 <input type="text" class="form-control"readonly="readonly" name="coinCode" value="${model.coinCode}" id="coinCode"/>
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="averagePrice">初始价格</label>
                 <input type="text" class="form-control" name="averagePrice" value="${model.averagePrice}" id="averagePrice"/>
             </div>
         </div>
     </div>

     <div class="row">


         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="fixPriceCoinCode">交易区</label>
                 <input type="text" readonly="readonly" class="form-control" name="fixPriceCoinCode" value="${model.fixPriceCoinCode}" id="fixPriceCoinCode"/>
             </div>
         </div>

     </div>
     <div class="row">

         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="averagePrice">价格小数位</label>
                 <input type="text" class="form-control" name="keepDecimalFixPrice" value="${model.keepDecimalFixPrice}" id="keepDecimalFixPrice"/>
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="averagePrice">数量小数位</label>
                 <input type="text" class="form-control" name="keepDecimalCoinCode" value="${model.keepDecimalCoinCode}"id="keepDecimalCoinCode"/>
             </div>
         </div>

     </div>

     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="rose">限价涨幅(%)</label>
                 <input type="text" class="form-control" name="rose" value="${model.rose}" id="rose"/>
             </div>
         </div>

         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="decline">限价跌幅(%)</label>
                 <input type="text" class="form-control" name="decline" value="${model.decline}" id="decline"/>
             </div>
         </div>

     </div>

     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="buyFeeRate">买方手续费率(%)</label>
                 <input type="text" class="form-control" name="buyFeeRate" value="${model.buyFeeRate}" id="buyFeeRate"/>
             </div>
         </div>

         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="sellFeeRate">卖方手续费率(%)</label>
                 <input type="text" class="form-control" name="sellFeeRate" value="${model.sellFeeRate}" id="sellFeeRate"/>
             </div>
         </div>

     </div>

     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="sellMinCoin">单笔最小下单数量(个) </label>
                 <input type="text" class="form-control" name="sellMinCoin" value="${model.sellMinCoin}" id="sellMinCoin"/>
             </div>
         </div>

         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="oneTimeOrderNum">单笔最大下单数量(个) </label>
                 <input type="text" class="form-control" name="oneTimeOrderNum" value="${model.oneTimeOrderNum}" id="oneTimeOrderNum"/>
             </div>
         </div>

     </div>

     <div class="row">
         <div class="col-md-2 column">
             <button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning">提交</button>
         </div>
     </div>
 </form>
</div>
 <script type="text/javascript">
     seajs.use("js/admin/exchange/ExCointoCoin", function (o) {
         o.modify();
     });
 </script>






