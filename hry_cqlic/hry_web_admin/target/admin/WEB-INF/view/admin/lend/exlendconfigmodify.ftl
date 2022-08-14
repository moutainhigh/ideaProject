 <!-- Copyright:    -->
 <!-- ExLendConfigModify.html     -->
 <!-- @author:      HeC  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-18 14:47:26      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">杠杆配置修改<button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/lend/exlendconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>

     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="coinCode">交易对</label>
                 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" readonly/>
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="status">状态</label>
                 <select class="form-control" id="status" name="status">
                     <option value="0" <#if model.status==0>selected</#if>>关闭</option>
                     <option value="1" <#if model.status==1>selected</#if>>开启</option>
                 </select>
             </div>
         </div>

     </div>

     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="multiple">杠杆倍数</label>
                 <input type="text" class="form-control" name="multiple" id="multiple" value="${model.multiple}" />
             </div>
         </div>

         <div class="col-md-4 column">

         </div>
     </div>

     <div class="row">

         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="dayRatio">杠杆日利率%</label>
                 <input type="text" class="form-control" name="dayRatio" id="dayRatio" value="${model.dayRatio}"/>
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="warningRatio">预警线%</label>
                 <input type="text" class="form-control" name="warningRatio" id="warningRatio" value="${model.warningRatio}" />
             </div>
         </div>

     </div>

     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="pingRatio">平仓线%</label>
                 <input type="text" class="form-control" name="pingRatio" id="pingRatio" value="${model.pingRatio}" />
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="outRatio">转出线%</label>
                 <input type="text" class="form-control" name="outRatio" id="outRatio" value="${model.outRatio}" />
             </div>
         </div>
     </div>

     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="coinLendMax">交易币借款上限</label>
                 <input type="text" class="form-control" name="coinLendMax" id="coinLendMax" value="${model.coinLendMax}" />
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="coinLendMin">交易币借款下限</label>
                 <input type="text" class="form-control" name="coinLendMin" id="coinLendMin" value="${model.coinLendMin}" />
             </div>
         </div>
     </div>

     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="priLendMax">定价币借款上限</label>
                 <input type="text" class="form-control" name="priLendMax" id="priLendMax" value="${model.priLendMax}" />
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="priLendMin">定价币借款下限</label>
                 <input type="text" class="form-control" name="priLendMin" id="priLendMin" value="${model.priLendMin}" />
             </div>
         </div>
     </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/lend/ExLendConfig",function(o){
 	o.modify();
 });
 </script>






