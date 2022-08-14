 <!-- Copyright:    -->
 <!-- YwkCustomerMinerSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-11-16 16:44:13      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 
  <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
                <div class="col-md-6 column">
					<div class="form-group">
						 <label for="dianfeiNum"><p style="color:red;margin: 10px 0 0;">已选买单总数量:${map.buySum}&nbsp;SME</p></label>
					</div>
				</div>
			  	<div class="col-md-6 column">
					<div class="form-group">
						 <label for="dianfeiNum"><p style="color:red;margin: 10px 0 0;">匹配卖总数量:${map.sellSum}&nbsp;SME</p></label>
					</div>
				</div>
			  	<!--div class="col-md-6 column">
					<div class="form-group">
						 <label for="remark">剩余买单:${map.buyPay}&nbsp;SME</label>
					</div>
				</div>
			  	<div class="col-md-6 column">
					<div class="form-group">
						 <label for="remark">剩余卖单:${map.sellPay}&nbsp;SME</label>
					</div>
				</div-->
            </div>
        </div>
        </form>
 	    <div id="toolbar1">
             
 	    </div>
 	    <table id="table_sell"
 	           data-toolbar="#toolbar"
 	           data-show-refresh="false"
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
 
 <div class="row">
	<div class="col-md-2 column"  style="float:right;"> 
		<button type="button" id="peipeiSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>
 </div>

 <script type="text/javascript">
  seajs.use(["js/admin/klg/transaction/matchingbuy","js/base/HrySelect","js/base/HryDateTop"],function(o,HrySelect,t){
    HrySelect.init();
    t.init();
 	o.listsell();
 });
 </script>




