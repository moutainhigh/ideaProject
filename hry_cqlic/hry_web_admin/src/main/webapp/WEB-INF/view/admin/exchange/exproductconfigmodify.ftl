<!-- Copyright:    -->
 <!-- ExProductParameterModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:49:05      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-md-12">
         <h3 class="page-header">ExProductParameter--Modify  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exproductparameterlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
     </div>
 </div>


 <form id="form" >
     <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="name">币种名称</label>
                 <input type="text" class="form-control" name="name" value="${model.name}" id="name"/>
             </div>
         </div>
         <div class="col-md-4 column">

             <div class="form-group">
                 <label for="keepDecimalForCoin">币的保留几位小数(个)</label>
                 <input type="text" class="form-control" name="keepDecimalForCoin" value="${model.keepDecimalForCoin}"id="keepDecimalForCoin"/>
             </div>
         </div>
     </div>
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="isShow">手续费类型</label>
			 <@HrySelect key="yesno"  name="isShow"  id="isShow"   > </@HrySelect>
             </div>
         </div>
         <div class="col-md-4 column">

             <div class="form-group">
                 <label for="paceCurrecy">提币手续费</label>
                 <input type="text" class="form-control" name="paceCurrecy" value="${model.paceCurrecy}"id="paceCurrecy"/>
             </div>
         </div>
     </div>
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="oneDayPaceNum">单日提币的最大数量  (个)</label>
                 <input type="text" class="form-control" name="oneDayPaceNum" value="${model.oneDayPaceNum}" id="oneDayPaceNum"/>
             </div>
         </div>
         <div class="col-md-4 column">

             <div class="form-group">
                 <label for="leastPaceNum">单次最小提币数量 (个)</label>
                 <input type="text" class="form-control" name="leastPaceNum" value="${model.leastPaceNum}"id="leastPaceNum"/>
             </div>
         </div>
     </div>
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="giveCoin"> 实名送币数量</label>
                 <input type="text" class="form-control" name="giveCoin" value="${model.giveCoin}" id="giveCoin"/>
             </div>
         </div>
         <div class="col-md-4 column">

             <div class="form-group">
                 <label for="commendCoin">邀请送币数量</label>
                 <input type="text" class="form-control" name="commendCoin" value="${model.commendCoin}"id="commendCoin"/>
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
     seajs.use("js/admin/exchange/ExProductParameter",function(o){
         o.modify();
     });
 </script>






