 <!-- Copyright:    -->
 <!-- AppOurAccountAdd.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-13 18:38:16      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加我方账户  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appouraccountlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-8 column">
        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="name">账户类别</label>
                    <@HrySelect key="accounttype"  name="currencyType"  id="currencyType"   > </@HrySelect>
                </div>
            </div>
            <div class="col-md-6 column">

                <div class="form-group">
                    <label for="coinCode">所属银行</label>
                    <@HrySelect key="bankType"  name="bankName"  id="bankName"   > </@HrySelect>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="openAccountType">开户类型</label>
                    <@HrySelect key="openAccountType"  name="openAccountType"  id="openAccountType"   > </@HrySelect>
                </div>
            </div>
            <div class="col-md-6 column">

                <div class="form-group">
                    <label for="isShow">是否主账户</label>
                    <@HrySelect key="yesno"  name="isShow"  id="isShow"   > </@HrySelect>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="accountType">账户类型</label>
                    <@HrySelect key="accountcategory"  name="accountType"  id="accountType"   > </@HrySelect>
                </div>
            </div>
            <div class="col-md-6 column">

                <div class="form-group">
                    <label for="accountNumber">银行卡号</label>
                    <input type="text" class="form-control" name="accountNumber" id="accountNumber"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="accountName">持卡人姓名</label>
                    <input type="text" class="form-control" name="accountName" id="accountName"/>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="bankAddress">开户行支行</label>
                    <input type="text" class="form-control" name="bankAddress" id="bankAddress"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="remark">备注</label>
                    <input type="text" class="form-control" name="remark" id="remark"/>
                </div>
            </div>

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
seajs.use("js/admin/web/AppOurAccount",function(o){
	o.add();
});
</script>




