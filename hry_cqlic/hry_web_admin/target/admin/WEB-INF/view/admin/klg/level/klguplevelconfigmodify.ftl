 <!-- Copyright:    -->
 <!-- KlgLevelConfigModify.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-11 17:30:43      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">会员升级--规则  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/level/klguplevelconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="levelName">等级名称</label>
			 <input type="text" class="form-control" name="levelName" id="levelName" value="${model.levelName}" disabled/>
		</div>
		<div class="form-group">
			<label for="recommendNum">推荐个数</label>
			<input type="text" class="form-control" name="recommendNum" id="recommendNum" value="${model.recommendNum}" />
		</div>
		<div class="form-group">
			<label for="recommendSort">推荐星级别</label>
            <@HrySelect url="${ctx}/klg/level/klglevelconfig/findAll"  itemvalue="sort"  itemname="levelName"  name="recommendSort"  id="recommendSort" value="${model.recommendSort}" > </@HrySelect>
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
 seajs.use(["js/admin/klg/level/KlgLevelConfig","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,HryDate){
     o.uplevelmodify();
     HrySelect.init();
     HryDate.init();

 });
 </script>






