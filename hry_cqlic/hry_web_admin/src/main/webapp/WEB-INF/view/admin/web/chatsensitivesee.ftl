 <!-- Copyright:    -->
 <!-- chatSensitiveSee.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-29 11:30:51      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">chatSensitive--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/chatsensitivelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="sensitiveWords">sensitiveWords</label>
			 <input type="text" class="form-control" name="sensitiveWords" id="sensitiveWords" value="${model.sensitiveWords}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/web/chatSensitive",function(o){
 	o.see();
 });
 </script>




