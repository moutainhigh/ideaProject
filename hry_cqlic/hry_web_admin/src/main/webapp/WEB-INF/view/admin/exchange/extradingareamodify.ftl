 <!-- Copyright:    -->
 <!-- ExTradingAreaModify.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-23 11:12:53      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">交易区修改  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/extradingarealist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="tradingArea">交易区</label>
            <@HrySelect url="${ctx}/exchange/exproduct/findPublished"  value="${model.tradingArea}" itemvalue="coinCode"  itemname="coinCode"  name="tradingArea"  id="tradingArea"   > </@HrySelect>
		</div>
		<#--<div class="form-group">
			 <label for="struts">状态</label>
			<@HrySelect key="yesno"  name="struts"  id="struts"  value="${model.struts}"  > </@HrySelect>
		</div>-->
		<div class="form-group">
			 <label for="sort">排序</label>
			 <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>
 </div>

 <script type="text/javascript">
 seajs.use(["js/admin/exchange/ExTradingArea","js/base/HrySelect"],function(o,HrySelect){
 	o.modify();
     HrySelect.init();
 });
 </script>






