 <!-- Copyright:    -->
 <!-- KlgPlatformAccountList.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-15 16:35:25      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">平台账户管理</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
                
            </div>
        </div>
        </form>
 	    <div id="toolbar">
            <button id="transfer" class="btn btn-info-blue">
 	            <i class="glyphicon glyphicon-plus"></i>转账
 	        </button>
			<button id="recharge" class="btn btn-info-blue">
				<i class="glyphicon glyphicon-plus"></i>充值
			</button>
 	    </div>
 	    <table id="table"
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


 <div id="add-main" style="display: none;">
	 <div class="row">
		 <div class="col-md-4 column">
			 <div class="form-group">
				 <label for="dividendNum">转出账户：</label>
				 <@HrySelect key="account_type" name="account" id="account"  readonly="readonly"></@HrySelect>
			 </div>
			 <div class="form-group">
				 <label for="coinCode">转出至：</label>
				 <@HrySelect key="account_type" name="toAccount" id="toAccount" ></@HrySelect>
			 </div>
			 <div class="form-group">
				 <label for="reason">转出数量</label>
				 <input type="text" class="form-control" name="number" id="number" />
			 </div>
			 <div class="layui-input-block">
				 <button class="layui-btn" lay-submit lay-filter="save" id="submitTransfer">立即提交</button><#--
				 <button type="reset" class="layui-btn layui-btn-primary" id="closeBtn" >重置</button>-->
			 </div>
		 </div>
	 </div>
 </div>



 <div id="recharge-main" style="display: none;">
	 <div class="row">
		 <div class="col-md-4 column">
			 <div class="form-group">
				 <label for="dividendNum">充值账户：</label>
				 <@HrySelect key="account_type" name="addAccount" id="addAccount"  readonly="readonly"></@HrySelect>
			 </div>
			 <div class="form-group">
				 <label for="reason">充值数量</label>
				 <input type="text" class="form-control" name="money" id="money" />
			 </div>
			 <div class="layui-input-block">
				 <button class="layui-btn" lay-submit lay-filter="save" id="submitRecharge">立即提交</button>
			 </div>
		 </div>
	 </div>
 </div>
 <script type="text/javascript">
 seajs.use("js/admin/klg/platform/KlgPlatformAccount",function(o){
 	o.list();
 });

 </script>

