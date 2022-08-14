 <!-- Copyright:    -->
 <!-- appBusinessmanModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 13:33:52      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">修改交易商  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/c2c/appbusinessmanlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="trueName">真实姓名</label>
                 <input type="text" class="form-control" name="trueName"  value="${model.trueName}" id="trueName"/>
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="nationality">国籍</label>
                 <@HrySelect key="sysLanguage" value="${model.nationality}"  name="nationality"  id="nationality"   > </@HrySelect>
             </div>
         </div>
     </div>

     <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="type">类别</label>
                 <@HrySelect key="appbusinessmanType"  name="type"  id="type" value="${model.type}"   > </@HrySelect>
             </div>
         </div>
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="changeCoin">交易币种</label>
                 <@HrySelect url="${ctx}//exchange/exproduct/findall"  itemvalue="coinCode"  itemname="coinCode"  name="changeCoin"  id="changeCoin" value="${model.changeCoin}"  > </@HrySelect>

             </div>
         </div>
     </div>

    <#-- <div class="row">
         <div class="col-md-4 column">
             <div class="form-group">
                 <label for="price">交易定价</label>
                 <input type="number" class="form-control" name="price"  value="${model.price}" id="price"/>
             </div>
         </div>

     </div>-->


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>
</div>
 <script type="text/javascript">
 seajs.use(["js/admin/c2c/appBusinessman","js/base/HrySelect"],function(o,HrySelect){
 	o.modify();
 	HrySelect.init();
 });
 </script>






