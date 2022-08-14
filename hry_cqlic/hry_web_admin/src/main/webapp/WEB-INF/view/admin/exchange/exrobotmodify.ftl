 <!-- Copyright:    -->
 <!-- ExRobotModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 16:33:44      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">k线机器人配置<button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotparamlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="coinCode">交易币/交易区 </label>
                 <input class="form-control" type="input" readonly name="coinCode" value="${model.coinCode}/${model.fixPriceCoinCode}" id="coinCode">
             </div>
         </div>

         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="autoPrice">下单类型 </label>
                 <select class="form-control" id="atuoPriceType" name="atuoPriceType" value="${model.atuoPriceType}">
                     <option  value = "3">外部行情下单</option>
                     <#--<option  value = "1">定价下单</option>-->
                     <option  value = "2">市价下单</option>

                 </select>
             </div>
         </div>



     </div>

     <div class="row" id="autoPricediv">


         <div class="col-md-4 column" >
             <div class="form-group">
                 <label for="autoPrice">基准下单价格 </label>
                 <input type="text" class="form-control" name="autoPrice" value="${model.autoPrice}"id="autoPrice"/>
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="autoPriceFloat">下单价格浮动(%) </label>
                 <input type="text" class="form-control" name="autoPriceFloat" value="${model.autoPriceFloat}"id="autoPriceFloat"/>
             </div>
         </div>

     </div>


     <div class="row">


         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="autoCount">基准下单量 </label>
                <input type="number" class="form-control" name="autoCount" value="${model.autoCount}"id="autoCount" />

             </div>
         </div>
      <#-- <div class="col-md-4 column">
             <div class="form-group">
                 <label for="autoCountFloat">下单量浮动(%) </label>
                 <input type="text" class="form-control" name="autoCountFloat"value="${model.autoCountFloat}" id="autoCountFloat"/>
             </div>
         </div>-->

     </div>





     <div class="row hidden">
         <div class="col-md-4 column">
             <div class="form-group">
                 <select class="form-control" id="priceSource"  name="priceSource">
                     <option  value = "hry">互融云</option>
                 </select>
             </div>
         </div>

     </div>

 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>

</div>
 <script type="text/javascript">
     seajs.use(["js/admin/exchange/ExRobot","js/base/HrySelect"],function(o,HrySelect){
         o.modify();
         HrySelect.init();
     });
 </script>






