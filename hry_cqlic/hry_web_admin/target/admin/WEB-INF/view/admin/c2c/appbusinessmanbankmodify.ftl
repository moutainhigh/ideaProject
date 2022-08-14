 <!-- Copyright:    -->
 <!-- appBusinessmanBankModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 13:34:09      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">修改交易商银行卡  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/c2c/appbusinessmanbanklist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="bankname">开户行</label>
                 <input type="text" class="form-control" name="bankname" value="${model.bankname}"  id="bankname"/>
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="bankcard">银行卡号</label>
                 <input type="text" class="form-control" name="bankcard" value="${model.bankcard}"  id="bankcard"/>
             </div>
         </div>
     </div>
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="bankowner">持卡人</label>
                 <input type="text" class="form-control" name="bankowner"  value="${model.bankowner}" id="bankowner"/>
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="businessmanId">交易商</label>
                 <@HrySelect url="${ctx}/c2c/appbusinessman/findall" itemvalue="id" itemname="trueName" name="businessmanId"  id="businessmanId" value="${model.businessmanId}"> </@HrySelect>

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
 seajs.use(["js/admin/c2c/appBusinessmanBank","js/base/HrySelect"],function(o,HrySelect){
     HrySelect.init();
 	o.modify();
 });
 </script>






