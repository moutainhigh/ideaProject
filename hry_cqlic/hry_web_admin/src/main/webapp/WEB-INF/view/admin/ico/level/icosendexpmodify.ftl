 <!-- Copyright:    -->
 <!-- IcoSendExpModify.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-03-20 19:32:24      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">IcoSendExp--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/level/icosendexplist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="id">id</label>
			 <input type="text" class="form-control" name="id" id="id" value="${model.id}"/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
		</div>
		<div class="form-group">
			 <label for="state">state</label>
			 <input type="text" class="form-control" name="state" id="state" value="${model.state}"/>
		</div>
		<div class="form-group">
			 <label for="countingTime">countingTime</label>
			 <input type="text" class="form-control" name="countingTime" id="countingTime" value="${model.countingTime}"/>
		</div>
		<div class="form-group">
			 <label for="number">number</label>
			 <input type="text" class="form-control" name="number" id="number" value="${model.number}"/>
		</div>
		<div class="form-group">
			 <label for="releaseDate">releaseDate</label>
			 <input type="text" class="form-control" name="releaseDate" id="releaseDate" value="${model.releaseDate}"/>
		</div>
		<div class="form-group">
			 <label for="hour">hour</label>
			 <input type="text" class="form-control" name="hour" id="hour" value="${model.hour}"/>
		</div>
		<div class="form-group">
			 <label for="experience">experience</label>
			 <input type="text" class="form-control" name="experience" id="experience" value="${model.experience}"/>
		</div>
		<div class="form-group">
			 <label for="lockId">lockId</label>
			 <input type="text" class="form-control" name="lockId" id="lockId" value="${model.lockId}"/>
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
 seajs.use("js/admin/ico/level/IcoSendExp",function(o){
 	o.modify();
 });
 </script>






