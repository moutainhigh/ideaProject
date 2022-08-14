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
	 		<form id="table_query_form_buy" class="table_query_form_buy" style="padding:5px 0 0 10px;border: 1px solid #1ea2e3;">
		        <div class="row">
		            <div class="_params">
		            	 <div class="col-md-2 column">
		                     <div class="form-group">
		                         <label for="email">账号(邮箱)</label>
		                         <input type="text" class="form-control" tablesearch name="email"/>
		                     </div>
		                 </div>
		                 <div class="col-md-2 column">
		                     <div class="form-group">
		                         <label for="mobilePhone">账号(手机号)</label>
		                         <input type="text" class="form-control" tablesearch name="mobilePhone"/>
		                     </div>
		                 </div>
		                 <div class="col-md-2 column">
		                    <div class="form-group">
		                        <label for="entrustTime_GT">预约时间起</label>
		                        <input type="text" class="form-control" name="created_GT" id="entrustTime_GT" readonly tablesearch
		                               data-date-format="yyyy-mm-dd" data-min-view="month"
		                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
		                    </div>
		                </div>
		                <div class="col-md-2 column">
		                    <div class="form-group">
		                        <label for="entrustTime_GT">预约时间止</label>
		                        <input type="text" class="form-control" name="created_LT" id="entrustTime_LT" readonly tablesearch
		                               data-date-format="yyyy-mm-dd" data-min-view="month"
		                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
		                    </div>
		                </div>
		                <input type="hidden" class="form-control" tablesearch name="status" value="1"/>
		                <input type="hidden" class="form-control" tablesearch name="rushOrders" value="1"/>
		                 <div class="col-md-2 column">
		                     <div style="margin-top: 26px;">
		                         <button type="button" id="table_query_buy" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
		                         <button type="button" id="table_reset_buy" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
		                     </div>
		                 </div>
		            </div>
		        </div>
	        </form>
	        <div class="title_sty">
        		<span class="somr_pay">
        			<dl>
        				<dd>待支付：<span id="buyPay1">0</span> USDT</dd>
        				<dd>待收款：<span id="buyPay2">0</span> SMEC</dd>
        				<dd>已支付剩余：<span id="buyPay3">0</span> USDT</dd>
        			</dl>
        		</span>
        		<span class="somr_pay">
        			<dl>
        				<dd>排队成功：<span id="sellPay1">0</span> SMEC</dd>
        				<dd>待收款：<span id="sellPay2">0</span> USDT</dd>
        				<dd>卖出剩余：<span id="sellPay3">0</span> SMEC</dd>
        			</dl>
        		</span>
	        </div>
	         <div id="toolbar2">
	         	<!--button id="pipei" class="btn btn-info-blue">
                    <i class="glyphicon glyphicon-share"></i>匹配
                </button-->
	         	<button id="pipeiConfirm" class="btn btn-info-blue">
                    <i class="glyphicon glyphicon-share"></i>匹配
                </button>
	         	<button id="tiaokong" class="btn btn-info-blue">
                    <i class="glyphicon glyphicon-share"></i>调控
                </button>
	         	<button id="chidan" class="btn btn-info-blue">
                    <i class="glyphicon glyphicon-share"></i>吃单
                </button>
	         	<button id="surplusOut" class="btn btn-info-blue">
                    <i class="glyphicon glyphicon-share"></i>剩余转出
                </button>
	         	<button id="buyAlonePipeiSubmit" class="btn btn-info-blue">
                    <i class="glyphicon glyphicon-share"></i>买单单独匹配
                </button>
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
	</div>
 </div>

 <script type="text/javascript">
 seajs.use(["js/admin/klg/transaction/matchingbuy","js/base/HrySelect","js/base/HryDateTop"],function(o,HrySelect,t){
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

 </style>

