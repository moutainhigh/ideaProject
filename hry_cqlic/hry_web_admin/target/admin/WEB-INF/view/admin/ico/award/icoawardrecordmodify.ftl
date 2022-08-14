 <!-- Copyright:    -->
 <!-- IcoAwardRecordModify.html     -->
 <!-- @author:      houz  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-14 17:16:18      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">IcoAwardRecord--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/award/icoawardrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customer_id">账户id</label>
			 <input type="text" class="form-control" name="customer_id" id="customer_id" value="${model.customer_id}"/>
		</div>
		<div class="form-group">
			 <label for="customer_email">账户邮箱</label>
			 <input type="text" class="form-control" name="customer_email" id="customer_email" value="${model.customer_email}"/>
		</div>
		<div class="form-group">
			 <label for="customer_mobile">账户手机</label>
			 <input type="text" class="form-control" name="customer_mobile" id="customer_mobile" value="${model.customer_mobile}"/>
		</div>
		<div class="form-group">
			 <label for="account_id">流水id</label>
			 <input type="text" class="form-control" name="account_id" id="account_id" value="${model.account_id}"/>
		</div>
		<div class="form-group">
			 <label for="award_type">推荐类型（1 首持奖励， 2  推荐奖励）</label>
			 <input type="text" class="form-control" name="award_type" id="award_type" value="${model.award_type}"/>
		</div>
		<div class="form-group">
			 <label for="coid_id">币id</label>
			 <input type="text" class="form-control" name="coid_id" id="coid_id" value="${model.coid_id}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">币种（CNY）</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="coinName">虚拟币种汉语名称（ 比特币 莱特币...）</label>
			 <input type="text" class="form-control" name="coinName" id="coinName" value="${model.coinName}"/>
		</div>
		<div class="form-group">
			 <label for="referrals_id">下级id</label>
			 <input type="text" class="form-control" name="referrals_id" id="referrals_id" value="${model.referrals_id}"/>
		</div>
		<div class="form-group">
			 <label for="referrals_email">被推荐人邮箱</label>
			 <input type="text" class="form-control" name="referrals_email" id="referrals_email" value="${model.referrals_email}"/>
		</div>
		<div class="form-group">
			 <label for="referrals_mobile">被推荐人手机号</label>
			 <input type="text" class="form-control" name="referrals_mobile" id="referrals_mobile" value="${model.referrals_mobile}"/>
		</div>
		<div class="form-group">
			 <label for="award_radix">奖励基数</label>
			 <input type="text" class="form-control" name="award_radix" id="award_radix" value="${model.award_radix}"/>
		</div>
		<div class="form-group">
			 <label for="award_quantity">奖励数量</label>
			 <input type="text" class="form-control" name="award_quantity" id="award_quantity" value="${model.award_quantity}"/>
		</div>
		<div class="form-group">
			 <label for="award_num">奖励流水号</label>
			 <input type="text" class="form-control" name="award_num" id="award_num" value="${model.award_num}"/>
		</div>
		<div class="form-group">
			 <label for="status">状态(1 奖励发放中，2 成功 ，3 失败)</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/ico/award/IcoAwardRecord",function(o){
 	o.modify();
 });
 </script>






