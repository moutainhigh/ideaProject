 <!-- Copyright:    -->
 <!-- KlgSellTransactionList.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-16 14:25:18      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">买单维度匹配</h3>
     </div>
 </div>

 <div class="row">
	 <div class="col-lg-12">
	 	<div class="col-md-5">
	 		<form id="table_query_form_buy" class="table_query_form_buy" style="padding:5px 0 0 10px;border: 1px solid #1ea2e3;">
		 		<div class="row">
		            <div class="_params">
		                 <div class="col-md-4 column">
		                    <div class="form-group">
		                        <label>买单账户</label>
		                        <input type="text" class="form-control">
		                    </div>
		                </div>
		                <div class="col-md-4 column">
		                    <div class="form-group">
		                        <label>预计成交天数</label>
		                        <input type="text" class="form-control">
		                    </div>
		                </div>
		                <div class="col-md-3 column">
		                    <div class="form-group">
		                        <label for="entrustTime_GT">预测时间</label>
		                        <input type="text" class="form-control" readonly
		                               data-date-format="yyyy-mm-dd" data-min-view="month">
		                    </div>
		                </div>
		            </div>
		        </div>
		        <div class="row">
		            <div class="_params">
		                 <div class="col-md-3 column">
		                    <div class="form-group">
		                        <label for="entrustTime_GT">预约时间起</label>
		                        <input type="text" class="form-control" name="created_GT" id="entrustTime_GT" readonly tablesearch
		                               data-date-format="yyyy-mm-dd" data-min-view="month"
		                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
		                    </div>
		                </div>
		                <div class="col-md-3 column">
		                    <div class="form-group">
		                        <label for="entrustTime_GT">预约时间止</label>
		                        <input type="text" class="form-control" name="created_LT" id="entrustTime_LT" readonly tablesearch
		                               data-date-format="yyyy-mm-dd" data-min-view="month"
		                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
		                    </div>
		                </div>
		                <input type="hidden" class="form-control" tablesearch name="status" value="1"/>
		                <input type="hidden" class="form-control" tablesearch name="rushOrders" value="1"/>
		                 <div class="col-md-6 column">
		                     <div style="margin-top: 26px;">
		                         <button type="button" id="table_query_buy" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
		                         <button type="button" id="table_reset_buy" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
		                     </div>
		                 </div>
		            </div>
		        </div>
	        </form>
	        <div class="title_sty">
	        	<button type="button" id="tiaokong" class="btn  btn-primary">调控</button>
	        	<button type="button" id="chidan" class="btn  btn-primary">吃单</button>
        		<!--span class="addCla"><i></i>调控</span-->
        		<!--span class="addCla"><i></i>吃单</span-->
        		<span class="somr_pay">
        			<dl>
        				<dd>待支付：<span id="buyPay1">0</span> USDT</dd>
        				<dd>待收款：<span id="buyPay2">0</span> SME</dd>
        				<dd>已支付剩余：<span id="buyPay3">0</span> USDT</dd>
        			</dl>
        		</span>
	        </div>
	        <p style="color:red;margin: 10px 0 0;"  id="smeBuySum">已选买单：0 SME</p>
	 	    <table id="table_buy"
	 	           data-toolbar="#toolbar"
	 	           data-show-refresh="true"
	 	           data-show-columns="false"
	 	           data-show-export="false"
	 	           data-search="false"
	 	           data-detail-view="false"
	 	           data-minimum-count-columns="2"
	 	           data-pagination="true"
	 	           data-id-field="id"
	 	           data-page-list="[10, 25, 50, 100]"
	 	           data-show-footer="false"  
	 	           data-side-pagination="server"
	 	           >
	 	    </table>
	     </div>
	     
	     
	     <div class="col-md-1">
	    	 <div style="margin-top: 165px;">
	             <button type="button" id="pipei" class="btn "><i class="glyphicon glyphicon-search"></i>匹配</button>
	         </div>
	         <div style="margin-top: 26px;">
	             <button type="button" id="pipeiConfirm" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>确认</button>
	         </div>
	     </div>
	     
	     
	     <div class="col-md-5">
	 		<form id="table_query_form_sell" class="table_query_form_sell" style="padding:5px 0 0 10px;border: 1px solid #1ea2e3;">
		 		<div class="row">
		            <div class="_params">
		                 <div class="col-md-4 column">
		                    <div class="form-group">
		                        <label>买单账户</label>
		                        <input type="text" class="form-control">
		                    </div>
		                </div>
		                <div class="col-md-4 column">
		                    <div class="form-group">
		                        <label>预计成交天数</label>
		                        <input type="text" class="form-control">
		                    </div>
		                </div>
		                <div class="col-md-3 column">
		                    <div class="form-group">
		                        <label for="entrustTime_GT">预测时间</label>
		                        <input type="text" class="form-control" readonly
		                               data-date-format="yyyy-mm-dd" data-min-view="month">
		                    </div>
		                </div>
		            </div>
		        </div>
		        <div class="row">
		            <div class="_params">
		                 <div class="col-md-3 column">
		                    <div class="form-group">
		                        <label for="entrustTime_GT">预约时间起</label>
		                        <input type="text" class="form-control" name="created_GT" id="entrustTime_GT" readonly tablesearch
		                               data-date-format="yyyy-mm-dd" data-min-view="month"
		                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
		                    </div>
		                </div>
		                <div class="col-md-3 column">
		                    <div class="form-group">
		                        <label for="entrustTime_GT">预约时间止</label>
		                        <input type="text" class="form-control" name="created_LT" id="entrustTime_LT" readonly tablesearch
		                               data-date-format="yyyy-mm-dd" data-min-view="month"
		                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
		                    </div>
		                </div>
		                 <div class="col-md-6 column">
		                     <div style="margin-top: 26px;">
		                         <button type="button" id="table_query_sell" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
		                         <button type="button" id="table_reset_sell" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
		                     </div>
		                 </div>
		            </div>
		        </div>
	        </form>
	        <div class="title_sty">
        		<span class="addCla"><i></i>调控</span>
        		<span class="addCla"><i></i>吃单</span>
        		<span class="somr_pay">
        			<dl>
        				<dd>排队成功：<span id="sellPay1">0</span> SME</dd>
        				<dd>待收款：<span id="sellPay2">0</span> USDT</dd>
        				<dd>卖出剩余：<span id="sellPay3">0</span> SME</dd>
        			</dl>
        		</span>
	        </div>
	        <p style="color:red;margin: 10px 0 0;" id="smeSellSum">已选买单：0 SME</p>
	 	    <table id="table_sell"
	 	           data-toolbar="#toolbar"
	 	           data-show-refresh="true"
	 	           data-show-columns="false"
	 	           data-show-export="false"
	 	           data-search="false"
	 	           data-detail-view="false"
	 	           data-minimum-count-columns="2"
	 	           data-pagination="true"
	 	           data-id-field="id"
	 	           data-page-list="[10, 25, 50, 100]"
	 	           data-show-footer="false"  
	 	           data-side-pagination="server"
	 	           >
	 	    </table>
	     </div>
	 </div>
	</div>
 </div>

 <script type="text/javascript">
 seajs.use(["js/admin/klg/transaction/matching","js/base/HrySelect","js/base/HryDateTop"],function(o,HrySelect,t){
    HrySelect.init();
    t.init();
 	o.listbuy();
 	o.listsell();
 	
 });

 </script>
 <style>
 .addCla i{
 	position:relative;
 }
 .addCla{
 	line-height: 76px;
    font-size: 13px;
    color: #333333;
    text-indent: 30px;
 }
 .addCla i::before{
 	content: "";
    position: absolute;
    display: inline-block;
    width: 3px;
    height: 15px;
    background: #26a3e3;
    top: -1px;
    left: -11px;
 }
 .addCla i::after{
 	content: "";
    display: inline-block;
    width: 15px;
    height: 3px;
    background: #26a3e3;
    top: 5px;
    left: -17px;
    position: absolute;
 }
 .title_sty{
 	margin-top: 15px;
    padding: 5px 0 0 10px;
    background-color: rgba(214, 227, 243, 1);
    border: 1px solid #1ea2e3;
    display: flex;
 }
 .somr_pay{
 	width: 280px;
 }
 .somr_pay dl{
 	text-align: right;
    color: #FF0000;
    font-size: 13px;
    margin-top: 11px;
    margin-bottom: 0;
 }
 .form-control{
 	height:24px;
 }
 .table_query_form_buy .btn-primary{
 	height: 29px;
    line-height: 9px;
    margin-top: -3px;
 }
 </style>

