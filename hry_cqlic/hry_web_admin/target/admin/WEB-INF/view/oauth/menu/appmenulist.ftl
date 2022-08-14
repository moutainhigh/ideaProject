 <!-- Copyright:   互融云 -->
 <!-- AppUserList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
	 <div class="col-md-2 column" style=" background-color:#F5F5F5">
	 	<h3 class="page-header"  style="border-bottom:1px solid #000;">应用模块</h3>
	 </div>
	 <div class="col-md-4 column">
		<h3 class="page-header"  style="border-bottom:1px solid #000;">&nbsp</h3>
	 </div>
 	 <div class="col-md-6 column">
		<h3 class="page-header"  style="border-bottom:1px solid #000;">&nbsp</h3>
	 </div>
 </div>

 <div class="row">
     <div class="col-md-2 column" style=" background-color:#F5F5F5">
     	<div id="treeview" class=""></div>
     </div>
     
     <input type="hidden" id="treeview_selected_key">
     <input type="hidden" id="tree_selected_id">
     <input type="hidden" id="tree_selected_mkey">
     <input type="hidden" id="tree_selected_name">
     <div class="col-md-4 column">
     	<ul id="tree_center" class="ztree" style="width:95%; overflow:auto;height:700px;"></ul>
     </div>
 	 <div class="col-md-6 column" id="tree_right">
 	 
	 </div>
 </div>
 </div>

<script type="text/javascript">
 seajs.use("js/admin/menu/AppMenu",function(o){
 	o.list();
 	
 });

 </script>

