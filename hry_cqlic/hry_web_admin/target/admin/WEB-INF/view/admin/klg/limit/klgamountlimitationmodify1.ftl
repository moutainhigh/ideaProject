 <!-- Copyright:    -->
 <!-- KlgAmountLimitationModify.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-16 16:55:42      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">抢单额度限制 </h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <input type="hidden" class="form-control" name="type" id="type" value="${model.type}"/>
 <input type="hidden" class="form-control" name="state" id="state" value="${model.state}"/>
     <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="money">抢单总金额</label>
			 <input type="text" class="form-control" name="money" id="money" value="${model.money}"/>
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
 seajs.use("js/admin/klg/limit/KlgAmountLimitation",function(o){
 	o.modify();
 });
 </script>






