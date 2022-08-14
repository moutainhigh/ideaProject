 <!-- Copyright:    -->
 <!-- chatSensitiveModify.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-29 11:30:51      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">chatSensitive--Modify  <button type="button"  class="btn btn-warning pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/chatsensitivelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="sensitiveWords">sensitiveWords</label>
			 <input type="text" class="form-control" name="sensitiveWords" id="sensitiveWords" value="${model.sensitiveWords}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-success btn-warning" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/web/chatSensitive",function(o){
 	o.modify();
 });
 </script>






