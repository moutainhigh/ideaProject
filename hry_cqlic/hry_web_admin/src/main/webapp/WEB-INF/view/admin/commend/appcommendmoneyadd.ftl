 <!-- Copyright:    -->
 <!-- AppCommendMoneyAdd.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 19:49:22      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommendmoneylist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="custromerId">custromerId</label>
			 <input type="text" class="form-control" name="custromerId" id="custromerId" />
		</div>
		<div class="form-group">
			 <label for="custromerName">custromerName</label>
			 <input type="text" class="form-control" name="custromerName" id="custromerName" />
		</div>
		<div class="form-group">
			 <label for="refecode">refecode</label>
			 <input type="text" class="form-control" name="refecode" id="refecode" />
		</div>
		<div class="form-group">
			 <label for="moneyNum">moneyNum</label>
			 <input type="text" class="form-control" name="moneyNum" id="moneyNum" />
		</div>
		<div class="form-group">
			 <label for="paidMoney">paidMoney</label>
			 <input type="text" class="form-control" name="paidMoney" id="paidMoney" />
		</div>
		<div class="form-group">
			 <label for="fixPriceType">fixPriceType</label>
			 <input type="text" class="form-control" name="fixPriceType" id="fixPriceType" />
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="dealType">dealType</label>
			 <input type="text" class="form-control" name="dealType" id="dealType" />
		</div>
		<div class="form-group">
			 <label for="userCode">userCode</label>
			 <input type="text" class="form-control" name="userCode" id="userCode" />
		</div>
		<div class="form-group">
			 <label for="commendOne">commendOne</label>
			 <input type="text" class="form-control" name="commendOne" id="commendOne" />
		</div>
		<div class="form-group">
			 <label for="commendTwo">commendTwo</label>
			 <input type="text" class="form-control" name="commendTwo" id="commendTwo" />
		</div>
		<div class="form-group">
			 <label for="commendThree">commendThree</label>
			 <input type="text" class="form-control" name="commendThree" id="commendThree" />
		</div>
		<div class="form-group">
			 <label for="commendLater">commendLater</label>
			 <input type="text" class="form-control" name="commendLater" id="commendLater" />
		</div>
		<div class="form-group">
			 <label for="lastPaidTime">lastPaidTime</label>
			 <input type="text" class="form-control" name="lastPaidTime" id="lastPaidTime" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>

<script type="text/javascript">
seajs.use("js/admin/commend/AppCommendMoney",function(o){
	o.add();
});
</script>




