 <!-- Copyright:   互融云 -->
 <!-- RruleList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-02-20 15:56:55      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
	 <div class="col-md-3 column" style=" background-color:#F5F5F5">
	 	<h3 class="page-header"  style="border-bottom:1px solid #000;">分类</h3>
	 </div>

 </div>


 <div class="row">
     <div class="col-md-3 column" style=" background-color:#F5F5F5">
     	<!-- border:1px solid blue;-->
     	
     	<ul id="tree_dic" class="ztree" style="width:95%; overflow:auto;height:700px;"></ul>
     </div>
     
     <input type="hidden" id="dic_selected">
     <input type="hidden" id="dic_selected_mkey">
     <div class="col-md-9 column" id="dicList_right">
     	
     	<!-- 切换空间 -->

     	<!-- 切换空间 -->
     	
     </div>
 </div>
 </div>


<script type="text/javascript">
seajs.use("js/admin/dic/AppDic",function(o){
	o.tree();
});
</script>







