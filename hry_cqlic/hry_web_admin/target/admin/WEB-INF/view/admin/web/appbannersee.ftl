 <!-- Copyright:    -->
 <!-- AppBannerSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 14:34:44      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppBanner--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appbannerlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="name">name</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}" disabled/>
		</div>
		<div class="form-group">
			 <label for="picturePath">picturePath</label>
			 <input type="text" class="form-control" name="picturePath" id="picturePath" value="${model.picturePath}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isTop">isTop</label>
			 <input type="text" class="form-control" name="isTop" id="isTop" value="${model.isTop}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isShow">isShow</label>
			 <input type="text" class="form-control" name="isShow" id="isShow" value="${model.isShow}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sort">sort</label>
			 <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark1">remark1</label>
			 <input type="text" class="form-control" name="remark1" id="remark1" value="${model.remark1}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark2">remark2</label>
			 <input type="text" class="form-control" name="remark2" id="remark2" value="${model.remark2}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark3">remark3</label>
			 <input type="text" class="form-control" name="remark3" id="remark3" value="${model.remark3}" disabled/>
		</div>
		<div class="form-group">
			 <label for="applicationType">applicationType</label>
			 <input type="text" class="form-control" name="applicationType" id="applicationType" value="${model.applicationType}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/web/AppBanner",function(o){
 	o.see();
 });
 </script>




