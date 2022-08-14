 <!-- Copyright:    -->
 <!-- OtcAppTransactionSee.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-26 18:12:38      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">OtcAppTransaction--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/otcapptransactionlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="transactionNum">交易订单号</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" value="${model.transactionNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountId">虚拟账户id</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyUserId">买方ID</label>
			 <input type="text" class="form-control" name="buyUserId" id="buyUserId" value="${model.buyUserId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyUserName">买方用户名</label>
			 <input type="text" class="form-control" name="buyUserName" id="buyUserName" value="${model.buyUserName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyfee">买方手续费</label>
			 <input type="text" class="form-control" name="buyfee" id="buyfee" value="${model.buyfee}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellUserId">卖方ID</label>
			 <input type="text" class="form-control" name="sellUserId" id="sellUserId" value="${model.sellUserId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellUserName">卖方用户名</label>
			 <input type="text" class="form-control" name="sellUserName" id="sellUserName" value="${model.sellUserName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellfee">卖方手续费</label>
			 <input type="text" class="form-control" name="sellfee" id="sellfee" value="${model.sellfee}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">币种代码</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionMode">交易方式(1在线购买,2在线出售,3本地购买)</label>
			 <input type="text" class="form-control" name="transactionMode" id="transactionMode" value="${model.transactionMode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="payType">交易类型(1银行转账,2支付宝,3微信支付)</label>
			 <input type="text" class="form-control" name="payType" id="payType" value="${model.payType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="tradeNum">交易数量</label>
			 <input type="text" class="form-control" name="tradeNum" id="tradeNum" value="${model.tradeNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="tradeMoney">交易金额</label>
			 <input type="text" class="form-control" name="tradeMoney" id="tradeMoney" value="${model.tradeMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionType">交易类型(1定价交易 2市价交易)</label>
			 <input type="text" class="form-control" name="transactionType" id="transactionType" value="${model.transactionType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">1待支付 2已付款待确认 3已完成 4申诉中待回复 5已取消 6申请退款中 7退款已驳回 8申诉完成 9申诉成功,待确认 10申诉失败,待确认 11平台通过申诉 12平台驳回申诉 13退款成功</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="payTime">transactionMode为1时,为买家购买时间,为2时,为卖家购买时间</label>
			 <input type="text" class="form-control" name="payTime" id="payTime" value="${model.payTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="confirmMoney">transactionMode为1时,为买家确认收款时间,为2时,为卖家确认收款时间</label>
			 <input type="text" class="form-control" name="confirmMoney" id="confirmMoney" value="${model.confirmMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="appealTime">transactionMode为1时,为买家申诉时间,为2时,为卖家申诉时间</label>
			 <input type="text" class="form-control" name="appealTime" id="appealTime" value="${model.appealTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="advertisementId">广告ID</label>
			 <input type="text" class="form-control" name="advertisementId" id="advertisementId" value="${model.advertisementId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="referenceNum">付款参考号</label>
			 <input type="text" class="form-control" name="referenceNum" id="referenceNum" value="${model.referenceNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellIsDeleted">卖方是否删除</label>
			 <input type="text" class="form-control" name="sellIsDeleted" id="sellIsDeleted" value="${model.sellIsDeleted}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyIsDeleted">买方是否删除</label>
			 <input type="text" class="form-control" name="buyIsDeleted" id="buyIsDeleted" value="${model.buyIsDeleted}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bankId">支付信息记录id</label>
			 <input type="text" class="form-control" name="bankId" id="bankId" value="${model.bankId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bankNumber">银行卡号</label>
			 <input type="text" class="form-control" name="bankNumber" id="bankNumber" value="${model.bankNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="alipayId">支付信息记录id</label>
			 <input type="text" class="form-control" name="alipayId" id="alipayId" value="${model.alipayId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="alipayAccount">支付宝账号</label>
			 <input type="text" class="form-control" name="alipayAccount" id="alipayAccount" value="${model.alipayAccount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="alipayThingUrl">支付宝二维码</label>
			 <input type="text" class="form-control" name="alipayThingUrl" id="alipayThingUrl" value="${model.alipayThingUrl}" disabled/>
		</div>
		<div class="form-group">
			 <label for="wechatId">支付信息记录id</label>
			 <input type="text" class="form-control" name="wechatId" id="wechatId" value="${model.wechatId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="wechatAccount">微信账号</label>
			 <input type="text" class="form-control" name="wechatAccount" id="wechatAccount" value="${model.wechatAccount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="wechatThingUrl">微信二维码</label>
			 <input type="text" class="form-control" name="wechatThingUrl" id="wechatThingUrl" value="${model.wechatThingUrl}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/OtcAppTransaction",function(o){
 	o.see();
 });
 </script>




