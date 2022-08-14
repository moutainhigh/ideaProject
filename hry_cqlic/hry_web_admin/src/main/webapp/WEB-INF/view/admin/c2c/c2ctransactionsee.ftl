 <!-- Copyright:    -->
 <!-- C2cTransactionSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 13:34:46      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">C2C查看订单 <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/c2c/c2ctransactionlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
            <label for="coinCode">交易币种</label>
            <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
        </div>

		<div class="form-group">
			 <label for="transactionMoney">交易金额</label>
			 <input type="text" class="form-control" name="transactionMoney" id="transactionMoney" value="${model.transactionMoney}" disabled/>
		</div>
        <div class="form-group">
            <label for="randomNum">备注码</label>
            <input type="text" class="form-control" name="randomNum" id="randomNum" value="${model.randomNum}" disabled/>
        </div>
	</div>
	 <div  class="col-md-4 column">
         <div class="form-group">
             <label for="transactionType">交易类型</label>
             <input type="text" class="form-control" name="transactionType" id="transactionType" value="<#if model.transactionType==1>买<#else>卖</#if>" disabled/>
         </div>
         <div class="form-group">
             <label for="transactionCount">交易数量</label>
             <input type="text" class="form-control" name="transactionCount" id="transactionCount" value="${model.transactionCount}" disabled/>
         </div>
         <div class="form-group">
             <label for="transactionPrice">交易单价</label>
             <input type="text" class="form-control" name="transactionPrice" id="transactionPrice" value="${model.transactionPrice}" disabled/>
         </div>
	 </div>
 </div>
<#if detail.transactionType==2>
<hr></hr>
     <h4>卖方信息</h4>
 <div class="row">
     <div class="col-md-4 column">
         <div class="form-group">
             <label for="transactionType">持卡人</label>
             <input type="text" class="form-control" name="transactionType" id="transactionType" value="${detail.bankowner}" disabled/>
         </div>


         <div class="form-group">
             <label for="randomNum">银行卡号</label>
             <input type="text" class="form-control" name="randomNum" id="randomNum" value="${detail.bankcard}" disabled/>
         </div>
     </div>
     <div  class="col-md-4 column">
         <div class="form-group">
             <label for="transactionMoney">开户行名称</label>
             <input type="text" class="form-control" name="transactionMoney" id="transactionMoney" value="${detail.bankname}" disabled/>
         </div>
         <div class="form-group">
             <label for="transactionCount">银行卡所在地</label>
             <input type="text" class="form-control" name="transactionCount" id="transactionCount" value="${detail.bankAddress}" disabled/>
         </div>
     </div>
 </div>
</#if>
<#if detail.transactionType==1>
<hr></hr>
     <h4>交易商信息</h4>
 <div class="row">
     <div class="col-md-4 column">
         <div class="form-group">
             <label for="transactionType">持卡人</label>
             <input type="text" class="form-control" name="transactionType" id="transactionType" value="${detail.businessmanTrueName}" disabled/>
         </div>


         <div class="form-group">
             <label for="randomNum">银行卡号</label>
             <input type="text" class="form-control" name="randomNum" id="randomNum" value="${detail.bankcard}" disabled/>
         </div>
     </div>
     <div  class="col-md-4 column">
         <div class="form-group">
             <label for="transactionMoney">开户行名称</label>
             <input type="text" class="form-control" name="transactionMoney" id="transactionMoney" value="${detail.bankname}" disabled/>
         </div>
         <#--<div class="form-group">
             <label for="transactionCount">银行卡所在地</label>
             <input type="text" class="form-control" name="transactionCount" id="transactionCount" value="${detail.bankAddress}" disabled/>
         </div>-->
     </div>
 </div>
</#if>
 </form>
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/c2c/C2cTransaction",function(o){
 	o.see();
 });
 </script>




