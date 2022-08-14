 <!-- Copyright:    -->
 <!-- AppSmsSendSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 14:42:05      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppSmsSend--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appsmssendlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="type">type</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}" disabled/>
		</div>
		<div class="form-group">
			 <label for="serverUrl">serverUrl</label>
			 <input type="text" class="form-control" name="serverUrl" id="serverUrl" value="${model.serverUrl}" disabled/>
		</div>
		<div class="form-group">
			 <label for="postParam">postParam</label>
			 <input type="text" class="form-control" name="postParam" id="postParam" value="${model.postParam}" disabled/>
		</div>
		<div class="form-group">
			 <label for="receiveStatus">receiveStatus</label>
			 <input type="text" class="form-control" name="receiveStatus" id="receiveStatus" value="${model.receiveStatus}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sendStatus">sendStatus</label>
			 <input type="text" class="form-control" name="sendStatus" id="sendStatus" value="${model.sendStatus}" disabled/>
		</div>
		<div class="form-group">
			 <label for="thirdPartyResult">thirdPartyResult</label>
			 <input type="text" class="form-control" name="thirdPartyResult" id="thirdPartyResult" value="${model.thirdPartyResult}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sendContent">sendContent</label>
			 <input type="text" class="form-control" name="sendContent" id="sendContent" value="${model.sendContent}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/web/AppSmsSend",function(o){
 	o.see();
 });
 </script>




