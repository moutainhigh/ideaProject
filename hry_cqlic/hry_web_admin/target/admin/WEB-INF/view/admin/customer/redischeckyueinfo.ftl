 <!-- Copyright:    -->
 <!-- AppAccountSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-15 13:08:06      -->

 <#include "/base/base.ftl">
 <script type="text/javascript"  src="${ctx}/static/${version}/lib/sb_admin2/template-web.js"></script>

 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">余额详情 <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=admin/customer/checkaccount')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-6 column">
		<h4 class="text-center">现在资金情况</h4>
	</div>
     <div class="col-md-6 column">
         <h4 class="text-center">重新核算资金情况</h4>
     </div>
 </div>
	 <hr/>
     <div class="row">
         <div class="col-md-6 column">
             <table class="table table-striped">
                 <thead>
                 <tr>
                     <th>用户名</th>
                     <th>币种</th>
                     <th>可用</th>
                     <th>冻结</th>
                 </tr>
                 </thead>
                 <tbody id="old_body">
                 </tbody>
             </table>
         </div>
         <div class="col-md-6 column">
             <table class="table table-striped">
                 <thead>
                 <tr>
                     <th>币种</th>
                     <th>可用</th>
                     <th>冻结</th>
                 </tr>
                 </thead>
                 <tbody id="new_body">

                 </tbody>
             </table>

         </div>
     </div>
     <div class="row">
         <div class="col-md-2 column">
             <button type="button" id="openinfo" customerid="${customerId}" class="btn btn-info-blue" >资金详情</button>
         </div>
     </div>

 </form>
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/customer/RedisCheck",function(o){
 	o.yueinfo(${customerId});
 });
 </script>

<script type="text/html" id="tmpl-old-record">
   <%for (var i = 0; i < list.length; i++) {%>
   <tr>
       <td><%=list[i].userName%></td>
       <td><%=list[i].coinCode%></td>
       <td><%=list[i].hotMoney%></td>
       <td><%=list[i].coldMoney%></td>
   </tr>
   <%}%>
</script>
 <script type="text/html" id="tmpl-new-record">
   <%for (var i = 0; i < list.length; i++) {%>
   <tr>
       <td><%=list[i].coinCode%></td>
       <td><%=list[i].hotMoney%></td>
       <td><%=list[i].coldMoney%></td>
   </tr>
   <%}%>
</script>

 <script type="text/html" id="tmpl-sureoldlist-record">
   <%for (var i = 0; i < sureoldlist.length; i++) {%>
   <tr>
       <td><%=sureoldlist[i].sureTime%></td>
       <td><%=sureoldlist[i].coinCode%></td>
       <td><%=sureoldlist[i].hotMoney%></td>
       <td><%=sureoldlist[i].coldMoney%></td>

       <td><%=hisorylist[i].rechargeMoney%></td>
       <td><%=hisorylist[i].rechargeMoneyFee%></td>
       <td><%=hisorylist[i].withdrawMoney%></td>
       <td><%=hisorylist[i].sellTransactionMoney%></td>
       <td><%=hisorylist[i].buyTransactionMoney%></td>
       <td><%=hisorylist[i].transactionFee%></td>

       <td><%=hisorylist[i].sellTransactionFixPrice%></td>
       <td><%=hisorylist[i].buyTransactionFixPrice%></td>
       <td><%=hisorylist[i].transactionFeeFixPrice%></td>
       <td><%=hisorylist[i].coldEntrustMoney%></td>
       <td><%=hisorylist[i].coldEntrustFixPrice%></td>
       <td><%=hisorylist[i].withdrawcoldMoney%></td>

       <td><%=hisorylist[i].drawalMoney%></td>
   </tr>
   <%}%>
</script>


<div id="div_info" style="display:none; top:20px;">
    <div class="row">
        <div class="col-md-6 column">
            <h4 class="text-center">现在资金情况</h4>
        </div>
        <div class="col-md-6 column">
            <h4 class="text-center">重新核算资金情况</h4>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 column">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>用户名</th>
                    <th>币种</th>
                    <th>可用</th>
                    <th>冻结</th>
                </tr>
                </thead>
                <tbody id="info_old_body">
                </tbody>
            </table>
        </div>
        <div class="col-md-6 column">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>币种</th>
                    <th>可用</th>
                    <th>冻结</th>
                </tr>
                </thead>
                <tbody id="info_new_body">

                </tbody>
            </table>

        </div>
    </div>
    <div class="row">
        <div class="col-md-12 column">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>时间</th>
                    <th>币种</th>
                    <th>可用</th>
                    <th>冻结</th>
                    <th>+充值</th>
                    <th>-充值手续费</th>
                    <th>-提现</th>
                    <th>-卖成交额(交易币)</th>
                    <th>-+买成交额(交易币)</th>
                    <th>-成交手续费</th>
                    <th>+卖成交额(定价币)</th>
                    <th>-买成交额(定价币)</th>
                    <th>-成交手续费</th>
                    <th>-委托冻结(交易币)</th>
                    <th>-委托冻结(定价币)</th>
                    <th>-提现冻结</th>
                    <th>+佣金收入</th>
                </tr>
                </thead>
                <tbody id="info_sureoldlist_body">
                </tbody>
            </table>
        </div>
    </div>
</div>




