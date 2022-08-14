 <!-- Copyright:    -->
 <!-- AppSmsSendModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 14:42:05      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppSmsSend--Modify  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appsmssendlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="type">type</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}"/>
		</div>
		<div class="form-group">
			 <label for="serverUrl">serverUrl</label>
			 <input type="text" class="form-control" name="serverUrl" id="serverUrl" value="${model.serverUrl}"/>
		</div>
		<div class="form-group">
			 <label for="postParam">postParam</label>
			 <input type="text" class="form-control" name="postParam" id="postParam" value="${model.postParam}"/>
		</div>
		<div class="form-group">
			 <label for="receiveStatus">receiveStatus</label>
			 <input type="text" class="form-control" name="receiveStatus" id="receiveStatus" value="${model.receiveStatus}"/>
		</div>
		<div class="form-group">
			 <label for="sendStatus">sendStatus</label>
			 <input type="text" class="form-control" name="sendStatus" id="sendStatus" value="${model.sendStatus}"/>
		</div>
		<div class="form-group">
			 <label for="thirdPartyResult">thirdPartyResult</label>
			 <input type="text" class="form-control" name="thirdPartyResult" id="thirdPartyResult" value="${model.thirdPartyResult}"/>
		</div>
		<div class="form-group">
			 <label for="sendContent">sendContent</label>
			 <input type="text" class="form-control" name="sendContent" id="sendContent" value="${model.sendContent}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/web/AppSmsSend",function(o){
 	o.modify();
 });
 </script>






