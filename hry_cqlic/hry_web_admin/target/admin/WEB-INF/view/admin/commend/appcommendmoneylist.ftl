 <!-- Copyright:    -->
 <!-- AppCommendMoneyList.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 19:49:22      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">推荐派发管理</h3>
     </div>
 </div>
 <div class="row">
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
                            <label for="email">返佣币种</label>
                            <input type="text" class="form-control" tablesearch name="coinCode"/>
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
 </div>
 <div class="row">
 	<div class="col-md-12">
 		<div class="_params">
 			
 		</div>
 	    <div id="toolbar">
             <button id="distribut" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-remove"></i>派发佣金
 	        </button>
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
 seajs.use("js/admin/commend/AppCommendMoney",function(o){
 	o.list();
 });

 </script>

