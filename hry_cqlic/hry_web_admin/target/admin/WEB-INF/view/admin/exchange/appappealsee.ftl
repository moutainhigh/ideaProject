 <!-- Copyright:    -->
 <!-- AppAppealSee.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-29 13:41:12      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppAppeal--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/appappeallist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="appeal">诉求</label>
			 <input type="text" class="form-control" name="appeal" id="appeal" value="${model.appeal}" disabled/>
		</div>
		<div class="form-group">
			 <label for="content">详细描述</label>
			 <input type="text" class="form-control" name="content" id="content" value="${model.content}" disabled/>
		</div>
		<div class="form-group">
			 <label for="thingUrl">附件url</label>
			 <input type="text" class="form-control" name="thingUrl" id="thingUrl" value="${model.thingUrl}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionNum">交易订单号</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" value="${model.transactionNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="userId">申述人ID</label>
			 <input type="text" class="form-control" name="userId" id="userId" value="${model.userId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="platFormContent">平台意见</label>
			 <input type="text" class="form-control" name="platFormContent" id="platFormContent" value="${model.platFormContent}" disabled/>
		</div>
		<div class="form-group">
			 <label for="releaseAdvertisementId">广告Id</label>
			 <input type="text" class="form-control" name="releaseAdvertisementId" id="releaseAdvertisementId" value="${model.releaseAdvertisementId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="appealSell">卖方 - 诉求</label>
			 <input type="text" class="form-control" name="appealSell" id="appealSell" value="${model.appealSell}" disabled/>
		</div>
		<div class="form-group">
			 <label for="contentSell">卖方 - 详细描述</label>
			 <input type="text" class="form-control" name="contentSell" id="contentSell" value="${model.contentSell}" disabled/>
		</div>
		<div class="form-group">
			 <label for="thingUrlSell">卖方 - 附件url</label>
			 <input type="text" class="form-control" name="thingUrlSell" id="thingUrlSell" value="${model.thingUrlSell}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/AppAppeal",function(o){
 	o.see();
 });
 </script>




