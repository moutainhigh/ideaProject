 <!-- Copyright:    -->
 <!-- AppCustomerAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 09:43:00      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/customer/appcustomer/toCustomer.do?isReal=0')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="username">username</label>
			 <input type="text" class="form-control" name="username" id="username" />
		</div>
		<div class="form-group">
			 <label for="passWord">passWord</label>
			 <input type="text" class="form-control" name="passWord" id="passWord" />
		</div>
		<div class="form-group">
			 <label for="accountPassWord">accountPassWord</label>
			 <input type="text" class="form-control" name="accountPassWord" id="accountPassWord" />
		</div>
		<div class="form-group">
			 <label for="isLock">isLock</label>
			 <input type="text" class="form-control" name="isLock" id="isLock" />
		</div>
		<div class="form-group">
			 <label for="lockTime">lockTime</label>
			 <input type="text" class="form-control" name="lockTime" id="lockTime" />
		</div>
		<div class="form-group">
			 <label for="isDelete">isDelete</label>
			 <input type="text" class="form-control" name="isDelete" id="isDelete" />
		</div>
		<div class="form-group">
			 <label for="isReal">isReal</label>
			 <input type="text" class="form-control" name="isReal" id="isReal" />
		</div>
		<div class="form-group">
			 <label for="isRealUsd">isRealUsd</label>
			 <input type="text" class="form-control" name="isRealUsd" id="isRealUsd" />
		</div>
		<div class="form-group">
			 <label for="salt">salt</label>
			 <input type="text" class="form-control" name="salt" id="salt" />
		</div>
		<div class="form-group">
			 <label for="userCode">userCode</label>
			 <input type="text" class="form-control" name="userCode" id="userCode" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="referralCode">referralCode</label>
			 <input type="text" class="form-control" name="referralCode" id="referralCode" />
		</div>
		<div class="form-group">
			 <label for="hasEmail">hasEmail</label>
			 <input type="text" class="form-control" name="hasEmail" id="hasEmail" />
		</div>
		<div class="form-group">
			 <label for="integral">integral</label>
			 <input type="text" class="form-control" name="integral" id="integral" />
		</div>
		<div class="form-group">
			 <label for="type">type</label>
			 <input type="text" class="form-control" name="type" id="type" />
		</div>
		<div class="form-group">
			 <label for="isChange">isChange</label>
			 <input type="text" class="form-control" name="isChange" id="isChange" />
		</div>
		<div class="form-group">
			 <label for="googleKey">googleKey</label>
			 <input type="text" class="form-control" name="googleKey" id="googleKey" />
		</div>
		<div class="form-group">
			 <label for="googleState">googleState</label>
			 <input type="text" class="form-control" name="googleState" id="googleState" />
		</div>
		<div class="form-group">
			 <label for="isproving">isproving</label>
			 <input type="text" class="form-control" name="isproving" id="isproving" />
		</div>
		<div class="form-group">
			 <label for="messIp">messIp</label>
			 <input type="text" class="form-control" name="messIp" id="messIp" />
		</div>
		<div class="form-group">
			 <label for="phoneState">phoneState</label>
			 <input type="text" class="form-control" name="phoneState" id="phoneState" />
		</div>
		<div class="form-group">
			 <label for="phone">phone</label>
			 <input type="text" class="form-control" name="phone" id="phone" />
		</div>
		<div class="form-group">
			 <label for="googleDate">googleDate</label>
			 <input type="text" class="form-control" name="googleDate" id="googleDate" />
		</div>
		<div class="form-group">
			 <label for="passDate">passDate</label>
			 <input type="text" class="form-control" name="passDate" id="passDate" />
		</div>
		<div class="form-group">
			 <label for="openPhone">openPhone</label>
			 <input type="text" class="form-control" name="openPhone" id="openPhone" />
		</div>
		<div class="form-group">
			 <label for="states">states</label>
			 <input type="text" class="form-control" name="states" id="states" />
		</div>
		<div class="form-group">
			 <label for="uuid">uuid</label>
			 <input type="text" class="form-control" name="uuid" id="uuid" />
		</div>
		<div class="form-group">
			 <label for="commendCode">commendCode</label>
			 <input type="text" class="form-control" name="commendCode" id="commendCode" />
		</div>
		<div class="form-group">
			 <label for="receCode">receCode</label>
			 <input type="text" class="form-control" name="receCode" id="receCode" />
		</div>
		<div class="form-group">
			 <label for="isAdmin">isAdmin</label>
			 <input type="text" class="form-control" name="isAdmin" id="isAdmin" />
		</div>
		<div class="form-group">
			 <label for="isGag">isGag</label>
			 <input type="text" class="form-control" name="isGag" id="isGag" />
		</div>
		<div class="form-group">
			 <label for="gagDate">gagDate</label>
			 <input type="text" class="form-control" name="gagDate" id="gagDate" />
		</div>
		<div class="form-group">
			 <label for="gagTime">gagTime</label>
			 <input type="text" class="form-control" name="gagTime" id="gagTime" />
		</div>
		<div class="form-group">
			 <label for="safeLoginType">safeLoginType</label>
			 <input type="text" class="form-control" name="safeLoginType" id="safeLoginType" />
		</div>
		<div class="form-group">
			 <label for="safeTixianType">safeTixianType</label>
			 <input type="text" class="form-control" name="safeTixianType" id="safeTixianType" />
		</div>
		<div class="form-group">
			 <label for="safeTradeType">safeTradeType</label>
			 <input type="text" class="form-control" name="safeTradeType" id="safeTradeType" />
		</div>
		<div class="form-group">
			 <label for="isBlacklist">isBlacklist</label>
			 <input type="text" class="form-control" name="isBlacklist" id="isBlacklist" />
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
seajs.use("js/admin/customer/AppCustomer",function(o){
	o.add();
});
</script>




