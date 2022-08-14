 <!-- Copyright:    -->
 <!-- EcofundSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-04 11:06:02      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">Ecofund--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/ecology/ecofundlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户Id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="orderNum">单号</label>
			 <input type="text" class="form-control" name="orderNum" id="orderNum" value="${model.orderNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="activityDate">活动时间</label>
			 <input type="text" class="form-control" name="activityDate" id="activityDate" value="${model.activityDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="activityAddress">活动地址</label>
			 <input type="text" class="form-control" name="activityAddress" id="activityAddress" value="${model.activityAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="peopleCount">活动人数</label>
			 <input type="text" class="form-control" name="peopleCount" id="peopleCount" value="${model.peopleCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="activityIntro">活动简介</label>
			 <input type="text" class="form-control" name="activityIntro" id="activityIntro" value="${model.activityIntro}" disabled/>
		</div>
		<div class="form-group">
			 <label for="activityImge">活动图片</label>
			 <input type="text" class="form-control" name="activityImge" id="activityImge" value="${model.activityImge}" disabled/>
		</div>
		<div class="form-group">
			 <label for="activityStatus">1 申请中 2 后台审核拒绝 3 后台审核通过 4 用户拒绝 5 用户通过（资料待补充） 6 补充材料待审核 7 后台通过补充材料审核</label>
			 <input type="text" class="form-control" name="activityStatus" id="activityStatus" value="${model.activityStatus}" disabled/>
		</div>
		<div class="form-group">
			 <label for="activityReply">活动平台回复</label>
			 <input type="text" class="form-control" name="activityReply" id="activityReply" value="${model.activityReply}" disabled/>
		</div>
		<div class="form-group">
			 <label for="againActivityDate">补充活动时间</label>
			 <input type="text" class="form-control" name="againActivityDate" id="againActivityDate" value="${model.againActivityDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="againActivityAddress">补充活动地址</label>
			 <input type="text" class="form-control" name="againActivityAddress" id="againActivityAddress" value="${model.againActivityAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="againPeopleCount">补充活动人数</label>
			 <input type="text" class="form-control" name="againPeopleCount" id="againPeopleCount" value="${model.againPeopleCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="againActivityIntro">补充活动简介</label>
			 <input type="text" class="form-control" name="againActivityIntro" id="againActivityIntro" value="${model.againActivityIntro}" disabled/>
		</div>
		<div class="form-group">
			 <label for="againActivityImge">补充活动图片</label>
			 <input type="text" class="form-control" name="againActivityImge" id="againActivityImge" value="${model.againActivityImge}" disabled/>
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
 seajs.use("js/admin/licqb/ecology/Ecofund",function(o){
 	o.see();
 });
 </script>




