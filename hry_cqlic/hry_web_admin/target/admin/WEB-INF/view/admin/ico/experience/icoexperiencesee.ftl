 <!-- Copyright:    -->
 <!-- IcoExperienceSee.html     -->
 <!-- @author:      houz  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-14 10:10:45      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">IcoExperience--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/experience/icoexperiencelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customer_id">账户id</label>
			 <input type="text" class="form-control" name="customer_id" id="customer_id" value="${model.customer_id}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customer_email">账户邮箱</label>
			 <input type="text" class="form-control" name="customer_email" id="customer_email" value="${model.customer_email}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customer_mobile">账户手机</label>
			 <input type="text" class="form-control" name="customer_mobile" id="customer_mobile" value="${model.customer_mobile}" disabled/>
		</div>
		<div class="form-group">
			 <label for="type">1收入 ，2 支出</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}" disabled/>
		</div>
		<div class="form-group">
			 <label for="account_type">交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）</label>
			 <input type="text" class="form-control" name="account_type" id="account_type" value="${model.account_type}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinNum">持币数量</label>
			 <input type="text" class="form-control" name="coinNum" id="coinNum" value="${model.coinNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="experience">经验值（无正负）</label>
			 <input type="text" class="form-control" name="experience" id="experience" value="${model.experience}" disabled/>
		</div>
		<div class="form-group">
			 <label for="experienceNum">流水号</label>
			 <input type="text" class="form-control" name="experienceNum" id="experienceNum" value="${model.experienceNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oldLevel_id">oldLevel_id</label>
			 <input type="text" class="form-control" name="oldLevel_id" id="oldLevel_id" value="${model.oldLevel_id}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oldLevel">原等级</label>
			 <input type="text" class="form-control" name="oldLevel" id="oldLevel" value="${model.oldLevel}" disabled/>
		</div>
		<div class="form-group">
			 <label for="newLevel_id">newLevel_id</label>
			 <input type="text" class="form-control" name="newLevel_id" id="newLevel_id" value="${model.newLevel_id}" disabled/>
		</div>
		<div class="form-group">
			 <label for="newLevel">newLevel</label>
			 <input type="text" class="form-control" name="newLevel" id="newLevel" value="${model.newLevel}" disabled/>
		</div>
		<div class="form-group">
			 <label for="upgradeNote">升级备注</label>
			 <input type="text" class="form-control" name="upgradeNote" id="upgradeNote" value="${model.upgradeNote}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/ico/experience/IcoExperience",function(o){
 	o.see();
 });
 </script>




