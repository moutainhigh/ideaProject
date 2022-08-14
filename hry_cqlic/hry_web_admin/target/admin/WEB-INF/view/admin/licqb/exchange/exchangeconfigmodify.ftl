 <!-- Copyright:    -->
 <!-- ExchangeConfigModify.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-12-17 16:32:12      -->

 <#include "/base/base.ftl">
 <link rel="stylesheet" type="text/css" href="${ctx}/static/${version}/lib/jdate/css/jedate.css" />
 <script type="text/javascript" src="${ctx}/static/${version}/lib/jdate/jedate-6.5.0.js"></script>
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">项目规则编辑<button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/exchange/exchangeconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	 <div class="col-md-4 column">
		 <div class="form-group">
			 <label for="itemName">项目名称</label>
			 <input type="text" class="form-control" name="itemName" id="itemName" value="${model.itemName}" disabled/>
		 </div>
		 <div class="form-group">
			 <label for="payCoinCode">平台币</label>
			 <select class="form-control" id="payCoinCode" name="payCoinCode">
				 <option value="">请选择</option>
				 <#list exProductList as exProduct>
					 <option value="${exProduct.coinCode}" <#if model.payCoinCode == exProduct.coinCode>selected</#if>>${exProduct.coinCode}</option>
				 </#list>
			 </select>
			 <#--<input type="text" class="form-control" name="payCoinCode" id="payCoinCode" value="${platCoin}" disabled/>-->
		 </div>
		 <div class="form-group">
			 <label for="gainCoinCode">兑换币种</label>
			 <select class="form-control" id="gainCoinCode" name="gainCoinCode" value="${model.gainCoinCode}" <#if model.status == 1>disabled</#if> >
				 <option value="">请选择</option>
				 <#list exProductList as exProduct>
					 <option value="${exProduct.coinCode}" <#if model.gainCoinCode == exProduct.coinCode>selected</#if>>${exProduct.coinCode}</option>
				 </#list>
			 </select>
		 </div>
		 <div class="form-group">
			 <label for="ratio">兑换汇率</label>
			 <input type="text" class="form-control" name="ratio" id="ratio" value="${model.ratio}" <#if model.status == 1>disabled</#if>/>
		 </div>
		 <div class="form-group">
			 <label for="totalNum">项目兑换总数</label>
			 <input type="text" class="form-control" name="totalNum" id="totalNum" value="${model.totalNum}" <#if model.status == 1>disabled</#if> />
		 </div>
		 <div class="form-group">
			 <label for="singleMinNum">单次最低兑换数量</label>
			 <input type="text" class="form-control" name="singleMinNum" id="singleMinNum" value="${model.singleMinNum}" <#if model.status == 1>disabled</#if>/>
		 </div>
		 <div class="form-group">
			 <label for="soloMaxNum">最多兑换量</label>
			 <input type="text" class="form-control" name="soloMaxNum" id="soloMaxNum" value="${model.soloMaxNum}" <#if model.status == 1>disabled</#if>/>
		 </div>
		 <div class="form-group">
			 <label for="itemStartDate">项目预热时间</label>
			 <input type="text" class="form-control" name="itemStartDate" id="itemStartDate" value="${model.itemStartDate?string("yyyy-MM-dd HH:mm:ss")}" placeholder="yyyy-mm-dd hh:mm:ss" readonly <#if model.status == 1>disabled</#if> />
		 </div>
		 <div class="form-group">
			 <label for="gainStartDate">开始时间</label>
			 <input type="text" class="form-control jeinput" name="gainStartDate" id="gainStartDate" value="${model.gainStartDate}" placeholder="hh:mm:ss" readonly/>
		 </div>
		 <div class="form-group">
			 <label for="gainEndDate">结束时间</label>
			 <input type="text" class="form-control jeinput" name="gainEndDate" id="gainEndDate" value="${model.gainEndDate}" placeholder="hh:mm:ss" readonly/>
		 </div>
		 <div class="form-group">
			 <label for="hasChangeRatio">兑换比例</label>
			 <input type="text" class="form-control" name="hasChangeRatio" id="hasChangeRatio" value="${model.hasChangeRatio}" />
		 </div>
		 <div class="form-group">
			 <label for="sort">排序</label>
			 <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}" />
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
 seajs.use("js/admin/licqb/exchange/ExchangeConfig",function(o){
 	o.modify();
 });
 var allinput = $(".jeinput");
 for(var i = 0 ; i < allinput.length; i++){
	 var input = $(allinput[i]);
	 var id = input.attr("id");
	 jeDate('#'+id,{
		 format:"hh:mm:ss",
		 minDate:"00:00:00",              //最小日期
		 maxDate:"23:59:59",              //最大日期
		 format: "hh:mm:ss"
	 });
 }
 jeDate('#itemStartDate',{
	 format: "YYYY-MM-DD hh:mm:ss", //控制是否显示小时
 });
 </script>






