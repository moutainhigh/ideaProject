 <!-- Copyright:    -->
 <!-- AppAccountList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-15 13:08:06      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">用户缓存核算</h3>
     </div>
 </div>
 <div class="row">
			<div class="_params">
	 		</div>
 </div>
 <div class="row">
 	<div class="col-md-12">
        <form id="table_query_form">
            <div class="row">
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
                        <label for="trueName">名字</label>
                        <input type="text" class="form-control" tablesearch name="trueName"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div style="margin-top: 26px;">
                        <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                        <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                    </div>
                </div>
            </div>
        </form>
 	    <div id="toolbar">
             <button id="yueallcheck" class="btn  btn-info-blue"  >
 	            <i class="glyphicon glyphicon-plus"></i>全部错误余额核算
 	        </button>
             <button  class="btn btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/customer/redischeckyueinfoallerror')" >
 	            <i class="glyphicon glyphicon-share"></i>全部错误余额信息
 	        </button>
             <button id="yueinfoerror" class="btn  btn-info-blue" >
 	            <i class="glyphicon glyphicon-edit"></i>余额核算错误信息
 	        </button>
             <button id="yue2mysql" class="btn  btn-info-blue" >
 	            <i class="glyphicon glyphicon-remove"></i>余额核算到数据库
 	        </button>
			<button id="yueinfo" class="btn  btn-info-blue" >
 	            <i class="glyphicon glyphicon-remove"></i>余额详情
 	        </button>
 	    </div>
 	    <table id="table"
 	           data-toolbar="#toolbar"
 	           data-show-refresh="false"
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
 seajs.use("js/admin/customer/RedisCheck",function(o){
 	o.checklist();
 });

 </script>


  <div id="div_yueallcheck" style="display:none; top:20px;">
      <form style="text-align: center;">
          前几天到现在有过资金变化的客户才进行核算
          <input type="number" id="days" placeholder="天数" style="text-align: center;margin-top: 20px;"/>
          <div class="col-md-2 column" style="margin: 0px 135px -100px;">
              <button type="button" id="yueallcheck_submit" class="btn  btn-info-blue "
                      style="margin-top: 18px;margin-left: 8px;">开始计算
              </button>
          </div>
      </form>
  </div>
