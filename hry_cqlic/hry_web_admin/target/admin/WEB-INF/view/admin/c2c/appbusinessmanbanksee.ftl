 <!-- Copyright:    -->
 <!-- appBusinessmanBankSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 13:34:09      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">appBusinessmanBank--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/c2c/appbusinessmanbanklist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bankcard">bankcard</label>
			 <input type="text" class="form-control" name="bankcard" id="bankcard" value="${model.bankcard}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bankname">bankname</label>
			 <input type="text" class="form-control" name="bankname" id="bankname" value="${model.bankname}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bankowner">bankowner</label>
			 <input type="text" class="form-control" name="bankowner" id="bankowner" value="${model.bankowner}" disabled/>
		</div>
		<div class="form-group">
			 <label for="businessmanId">businessmanId</label>
			 <input type="text" class="form-control" name="businessmanId" id="businessmanId" value="${model.businessmanId}" disabled/>
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
 seajs.use("js/admin/c2c/appBusinessmanBank",function(o){
 	o.see();
 });
 </script>




