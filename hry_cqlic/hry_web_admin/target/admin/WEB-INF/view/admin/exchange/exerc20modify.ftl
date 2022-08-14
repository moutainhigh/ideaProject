 <!-- Copyright:    -->
 <!-- ExErc20Modify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-08 17:02:30      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">上架币种修改  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exerc20list')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
        <div class="form-group">
            <label for="name">币代码</label>
            <input type="text" class="form-control" name="name" id="name" value="${model.name}"/>

        </div>

        <div class="form-group">
            <label for="contractAddress">合约地址</label>
            <input type="text" class="form-control" name="contractAddress" id="contractAddress" value="${model.contractAddress}"/>
        </div>
        <div class="form-group">
            <label for="myprecision">币的精度</label>
			 <@HrySelect key="coinprecision"  name="myprecision"  id="myprecision"  value="${model.myprecision}" > </@HrySelect>
        </div>
        <div class="form-group">
            <label for="addCoinType">上架类型</label>
			  <@HrySelect key="addCoinType"  name="addCoinType"  id="addCoinType"   value="${model.addCoinType}"> </@HrySelect>
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
 seajs.use("js/admin/exchange/ExErc20",function(o){
 	o.modify();
 });
 </script>






