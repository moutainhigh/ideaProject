 <!-- Copyright:    -->
 <!-- appBusinessmanSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 13:33:52      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">appBusinessman--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/c2c/appbusinessmanlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="userId">userId</label>
			 <input type="text" class="form-control" name="userId" id="userId" value="${model.userId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" value="${model.trueName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="type">type</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}" disabled/>
		</div>
		<div class="form-group">
			 <label for="feeType">feeType</label>
			 <input type="text" class="form-control" name="feeType" id="feeType" value="${model.feeType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="fee">fee</label>
			 <input type="text" class="form-control" name="fee" id="fee" value="${model.fee}" disabled/>
		</div>
		<div class="form-group">
			 <label for="price">price</label>
			 <input type="text" class="form-control" name="price" id="price" value="${model.price}" disabled/>
		</div>
		<div class="form-group">
			 <label for="nationality">nationality</label>
			 <input type="text" class="form-control" name="nationality" id="nationality" value="${model.nationality}" disabled/>
		</div>
		<div class="form-group">
			 <label for="changeCoin">changeCoin</label>
			 <input type="text" class="form-control" name="changeCoin" id="changeCoin" value="${model.changeCoin}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isLock">isLock</label>
			 <input type="text" class="form-control" name="isLock" id="isLock" value="${model.isLock}" disabled/>
		</div>
	</div>
 </div>

 </form>
</div>
 <script type="text/javascript">
 seajs.use("js/admin/c2c/appBusinessman",function(o){
 	o.see();
 });
 </script>




