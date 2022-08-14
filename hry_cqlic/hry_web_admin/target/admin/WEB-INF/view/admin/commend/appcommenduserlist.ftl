 <!-- Copyright:    -->
 <!-- AppCommendUserList.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 20:10:54      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">推荐人管理</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<div class="_params">
            <form id="table_query_form">
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">邮箱</label>
                        <input type="text" class="form-control" tablesearch name="email"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="mobilePhone">手机号</label>
                        <input type="text" class="form-control" tablesearch name="mobilePhone"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">推荐码</label>
                        <input type="text" class="form-control" tablesearch name="receCode"/>
                    </div>
                </div>

                <div class="col-md-2 column">
                    <div style="margin-top: 26px;">
                        <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                        <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                    </div>
                </div>
            </form>
 			
 		</div>
 	    <div id="toolbar">
             <#--<button id="add" class="btn btn-success" onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommenduseradd')" >-->
 	            <#--<i class="glyphicon glyphicon-plus"></i>添加-->
 	        <#--</button>-->
             <#--<button id="see" class="btn btn-info" >-->
 	            <#--<i class="glyphicon glyphicon-share"></i>查看-->
 	        <#--</button>-->
             <#--<button id="modify" class="btn btn-warning" >-->
 	            <#--<i class="glyphicon glyphicon-edit"></i>编辑-->
 	        <#--</button>-->
             <#--<button id="remove" class="btn btn-danger" >-->
 	            <#--<i class="glyphicon glyphicon-remove"></i>删除-->
 	        <#--</button>-->
 	    </div>
 	    <table id="table"
 	           data-toolbar="#toolbar"
 	           data-show-refresh="true"
 	           data-show-columns="false"
 	           data-show-export="false"
 	           data-search="false"
 	           data-detail-view="false"
 	           data-minimum-count-columns="2"
 	           data-pagination="true"
 	           data-id-field="id"
 	           data-page-list="[10, 25, 50, 100]"
 	           data-show-footer="false"  
 	           data-side-pagination="server"
 	           >
 	    </table>
     </div>
 </div>


 </div>

 <script type="text/javascript">
 seajs.use("js/admin/commend/AppCommendUser",function(o){
 	o.list();
 });

 </script>

