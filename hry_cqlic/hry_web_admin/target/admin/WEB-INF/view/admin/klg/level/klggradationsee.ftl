 <!-- Copyright:    -->
 <!-- KlgGradationSee.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-11 17:30:24      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">级差管理--查看  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/level/klggradationlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
        <div class="form-group">
            <label for="name">名称</label>
            <input type="text" class="form-control" name="name" id="name"  value="${model.name}" disabled/>
        </div>
		<div class="form-group">
			 <label for="gradation">级差数</label>
			 <input type="text" class="form-control" name="gradation" id="gradation" value="${model.gradation}" disabled/>
		</div>
		<div class="form-group">
			 <label for="minLevelSort">最小会员等级</label>
            <@HrySelect url="${ctx}/klg/level/klglevelconfig/findAll"  itemvalue="sort"  itemname="levelName"  name="minLevelSort"  id="minLevelSort" value="${model.minLevelSort}" disabled > </@HrySelect>
		</div>
		<div class="form-group">
			 <label for="levelNum">星级个数</label>
			 <input type="text" class="form-control" name="levelNum" id="levelNum" value="${model.levelNum}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use(["js/admin/klg/level/KlgGradation","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,HryDate){
     o.see();
     HrySelect.init();
     HryDate.init();

 });
 </script>




