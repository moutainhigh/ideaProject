 <!-- Copyright:    -->
 <!-- EcologPlateSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-05 16:37:08      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">EcologPlate--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/ecology/ecologplatelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="plateName">板块名称</label>
			 <input type="text" class="form-control" name="plateName" id="plateName" value="${model.plateName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sort">排序字段 越小越靠前</label>
			 <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isOpen">1 开启 2 关闭</label>
			 <input type="text" class="form-control" name="isOpen" id="isOpen" value="${model.isOpen}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/licqb/ecology/EcologPlate",function(o){
 	o.see();
 });
 </script>




