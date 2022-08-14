 <!-- Copyright:   互融云 -->
 <!-- AppUserList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
	 <div class="col-md-3 column" style=" background-color:#F5F5F5">
	 	<h3 class="page-header"  style="border-bottom:1px solid #000;">组织机构</h3>
	 </div>
	 <div class="col-md-9 column">
		<h3 class="page-header"  style="border-bottom:1px solid #000;">&nbsp</h3>
	 </div>
 </div>

 <div class="row">
     <div class="col-md-3 column" style=" background-color:#F5F5F5">
     	<ul id="tree_field" class="ztree" style="width:95%; overflow:auto;height:700px;"></ul>
     </div>
     
     <input type="hidden" id="tree_selected_id">
     <input type="hidden" id="tree_selected_name">
     <input type="hidden" id="tree_selected_id_mkey">
     <div class="col-md-9 column" id="tree_right">
     	<!-- 切换空间 -->
     	<!-- 切换空间 -->
     </div>
 </div>
 </div>

 <script type="text/javascript">
 seajs.use("js/oauth/user/AppOrganization",function(o){
 	o.tree();
 });

 </script>

