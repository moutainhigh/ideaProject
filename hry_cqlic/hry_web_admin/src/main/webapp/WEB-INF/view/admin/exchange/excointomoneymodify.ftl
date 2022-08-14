 <!-- Copyright:    -->
 <!-- ExCointoMoneyModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-22 10:07:05      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">修改兑换汇率  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/excointomoneylist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="lan">语种</label>、
            <@HrySelect key="sysLanguage" name="lan" id="sysLangId" value="${model.lan}" ></@HrySelect>
		</div>
		<div class="form-group">
			 <label for="exchange">法币币种</label>
            <@HrySelect url="${ctx}/exchange/exlawcoin/findall"  itemvalue="name" value="${model.exchange}" itemname="name"  name="exchange"  id="exchange"   > </@HrySelect>
		</div>
		<div class="form-group">
			 <label for="rate">费率</label>
			 <input type="text" class="form-control" name="rate" id="rate" value="${model.rate}"/>
		</div>
		<div class="form-group">
			 <label for="coinSymbol">币种符号</label>
			 <input type="text" class="form-control" name="coinSymbol" id="coinSymbol" value="${model.coinSymbol}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">币种代号</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="isSynC2C">C2C是否同步</label>
            <label>
                &nbsp;&nbsp;&nbsp;<input type="radio" name="isSynC2C" id="optionsRadios1" value="1">是
                &nbsp;&nbsp;&nbsp;<input type="radio" name="isSynC2C" id="optionsRadios2"  value="0" checked>否
            </label>
		</div>
		<#--<div class="form-group">
			 <label for="state">state</label>
			 <input type="text" class="form-control" name="state" id="state" value="${model.state}"/>
		</div>-->
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
 seajs.use(["js/admin/exchange/ExCointoMoney","js/base/HrySelect"],function(o,HrySelect){
 	o.modify();
     HrySelect.init();
 });
 </script>






