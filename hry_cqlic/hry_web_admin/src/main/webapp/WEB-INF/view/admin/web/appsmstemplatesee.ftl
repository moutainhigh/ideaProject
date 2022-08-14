 <!-- Copyright:    -->
 <!-- AppSmsTemplateSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-25 17:56:24      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppSmsTemplate--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appsmstemplatelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="mkey">模板KEY</label>
			 <input type="text" class="form-control" name="mkey" id="mkey" value="${model.mkey}" disabled/>
		</div>
		<div class="form-group">
			 <label for="name">模板名称</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}" disabled/>
		</div>
		<div class="form-group">
			 <label for="describes">模板内容</label>
			 <input type="text" class="form-control" name="describes" id="describes" value="${model.describes}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">模板备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isOpen">是否开启（0表示否 1表示是）</label>
			 <input type="text" class="form-control" name="isOpen" id="isOpen" value="${model.isOpen}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="messageCategory">信息类别-存放系统语种的值</label>
			 <input type="text" class="form-control" name="messageCategory" id="messageCategory" value="${model.messageCategory}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/web/AppSmsTemplate",function(o){
 	o.see();
 });
 </script>




