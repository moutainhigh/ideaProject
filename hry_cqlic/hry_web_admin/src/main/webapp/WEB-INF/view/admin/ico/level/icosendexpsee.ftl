 <!-- Copyright:    -->
 <!-- IcoSendExpSee.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-03-20 19:32:24      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">IcoSendExp--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/level/icosendexplist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="id">id</label>
			 <input type="text" class="form-control" name="id" id="id" value="${model.id}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="state">state</label>
			 <input type="text" class="form-control" name="state" id="state" value="${model.state}" disabled/>
		</div>
		<div class="form-group">
			 <label for="countingTime">countingTime</label>
			 <input type="text" class="form-control" name="countingTime" id="countingTime" value="${model.countingTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="number">number</label>
			 <input type="text" class="form-control" name="number" id="number" value="${model.number}" disabled/>
		</div>
		<div class="form-group">
			 <label for="releaseDate">releaseDate</label>
			 <input type="text" class="form-control" name="releaseDate" id="releaseDate" value="${model.releaseDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="hour">hour</label>
			 <input type="text" class="form-control" name="hour" id="hour" value="${model.hour}" disabled/>
		</div>
		<div class="form-group">
			 <label for="experience">experience</label>
			 <input type="text" class="form-control" name="experience" id="experience" value="${model.experience}" disabled/>
		</div>
		<div class="form-group">
			 <label for="lockId">lockId</label>
			 <input type="text" class="form-control" name="lockId" id="lockId" value="${model.lockId}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/ico/level/IcoSendExp",function(o){
 	o.see();
 });
 </script>




