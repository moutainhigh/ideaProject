 <!-- Copyright:    -->
 <!-- ExRobotLogAdd.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-13 11:25:18      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exrobotloglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="fixCoin">fixCoin</label>
			 <input type="text" class="form-control" name="fixCoin" id="fixCoin" />
		</div>
		<div class="form-group">
			 <label for="openTime">openTime</label>
			 <input type="text" class="form-control" name="openTime" id="openTime" />
		</div>
		<div class="form-group">
			 <label for="closeTime">closeTime</label>
			 <input type="text" class="form-control" name="closeTime" id="closeTime" />
		</div>
		<div class="form-group">
			 <label for="buyTotalNum">buyTotalNum</label>
			 <input type="text" class="form-control" name="buyTotalNum" id="buyTotalNum" />
		</div>
		<div class="form-group">
			 <label for="sellTotalNum">sellTotalNum</label>
			 <input type="text" class="form-control" name="sellTotalNum" id="sellTotalNum" />
		</div>
		<div class="form-group">
			 <label for="phone">phone</label>
			 <input type="text" class="form-control" name="phone" id="phone" />
		</div>
		<div class="form-group">
			 <label for="email">email</label>
			 <input type="text" class="form-control" name="email" id="email" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
seajs.use("js/admin/exchange/ExRobotLog",function(o){
	o.add();
});
</script>




