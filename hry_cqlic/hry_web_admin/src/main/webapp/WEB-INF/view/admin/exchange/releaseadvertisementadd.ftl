 <!-- Copyright:    -->
 <!-- ReleaseAdvertisementAdd.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-29 13:36:05      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/releaseadvertisementlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="accountId">虚拟账户id</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" />
		</div>
		<div class="form-group">
			 <label for="coinName">币种名称</label>
			 <input type="text" class="form-control" name="coinName" id="coinName" />
		</div>
		<div class="form-group">
			 <label for="coinCode">币种代码</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="transactionMode">交易方式(1在线购买,2在线出售,3本地购买)</label>
			 <input type="text" class="form-control" name="transactionMode" id="transactionMode" />
		</div>
		<div class="form-group">
			 <label for="nationality">国籍</label>
			 <input type="text" class="form-control" name="nationality" id="nationality" />
		</div>
		<div class="form-group">
			 <label for="isFixed">固定价格是否开启 0否 1是</label>
			 <input type="text" class="form-control" name="isFixed" id="isFixed" />
		</div>
		<div class="form-group">
			 <label for="tradeMoney">交易金额</label>
			 <input type="text" class="form-control" name="tradeMoney" id="tradeMoney" />
		</div>
		<div class="form-group">
			 <label for="premium">溢价</label>
			 <input type="text" class="form-control" name="premium" id="premium" />
		</div>
		<div class="form-group">
			 <label for="paymentTerm">付款期限</label>
			 <input type="text" class="form-control" name="paymentTerm" id="paymentTerm" />
		</div>
		<div class="form-group">
			 <label for="payType">交易类型(1银行转账,2支付宝,3微信支付)</label>
			 <input type="text" class="form-control" name="payType" id="payType" />
		</div>
		<div class="form-group">
			 <label for="tradeMoneyMix">最低交易金额</label>
			 <input type="text" class="form-control" name="tradeMoneyMix" id="tradeMoneyMix" />
		</div>
		<div class="form-group">
			 <label for="tradeMoneyMax">最高交易金额</label>
			 <input type="text" class="form-control" name="tradeMoneyMax" id="tradeMoneyMax" />
		</div>
		<div class="form-group">
			 <label for="remark">备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" />
		</div>
		<div class="form-group">
			 <label for="isAutomatic">固定价格是否开启 0否 1是</label>
			 <input type="text" class="form-control" name="isAutomatic" id="isAutomatic" />
		</div>
		<div class="form-group">
			 <label for="automaticReply">自动回复内容</label>
			 <input type="text" class="form-control" name="automaticReply" id="automaticReply" />
		</div>
		<div class="form-group">
			 <label for="isSecurity">是否启用安全选项 0否 1是</label>
			 <input type="text" class="form-control" name="isSecurity" id="isSecurity" />
		</div>
		<div class="form-group">
			 <label for="isBeTrusted">是否启用仅限受信任的交易者 0否 1是</label>
			 <input type="text" class="form-control" name="isBeTrusted" id="isBeTrusted" />
		</div>
		<div class="form-group">
			 <label for="transactionNum">交易次数</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="payTypeRemake">上传的资料</label>
			 <input type="text" class="form-control" name="payTypeRemake" id="payTypeRemake" />
		</div>
		<div class="form-group">
			 <label for="status">广告状态 0关闭 1开启</label>
			 <input type="text" class="form-control" name="status" id="status" />
		</div>
		<div class="form-group">
			 <label for="state">广告状态为0有效，1作废</label>
			 <input type="text" class="form-control" name="state" id="state" />
		</div>
		<div class="form-group">
			 <label for="bankId">支付信息记录id</label>
			 <input type="text" class="form-control" name="bankId" id="bankId" />
		</div>
		<div class="form-group">
			 <label for="bankNumber">银行卡号</label>
			 <input type="text" class="form-control" name="bankNumber" id="bankNumber" />
		</div>
		<div class="form-group">
			 <label for="alipayId">支付信息记录id</label>
			 <input type="text" class="form-control" name="alipayId" id="alipayId" />
		</div>
		<div class="form-group">
			 <label for="alipayAccount">支付宝账号</label>
			 <input type="text" class="form-control" name="alipayAccount" id="alipayAccount" />
		</div>
		<div class="form-group">
			 <label for="alipayThingUrl">支付宝二维码</label>
			 <input type="text" class="form-control" name="alipayThingUrl" id="alipayThingUrl" />
		</div>
		<div class="form-group">
			 <label for="wechatId">支付信息记录id</label>
			 <input type="text" class="form-control" name="wechatId" id="wechatId" />
		</div>
		<div class="form-group">
			 <label for="wechatAccount">微信账号</label>
			 <input type="text" class="form-control" name="wechatAccount" id="wechatAccount" />
		</div>
		<div class="form-group">
			 <label for="wechatThingUrl">微信二维码</label>
			 <input type="text" class="form-control" name="wechatThingUrl" id="wechatThingUrl" />
		</div>
		<div class="form-group">
			 <label for="coinNumMin">数量min</label>
			 <input type="text" class="form-control" name="coinNumMin" id="coinNumMin" />
		</div>
		<div class="form-group">
			 <label for="coinNumMax">数量max</label>
			 <input type="text" class="form-control" name="coinNumMax" id="coinNumMax" />
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
seajs.use("js/admin/exchange/ReleaseAdvertisement",function(o){
	o.add();
});
</script>




