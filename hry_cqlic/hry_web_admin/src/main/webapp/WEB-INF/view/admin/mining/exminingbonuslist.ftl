<!-- Copyright:    -->
 <!-- ExMiningList.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-13 16:11:36      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-lg-12">
		 <#if status==1>
             <h3 class="page-header">已分红用户管理</h3>
		 <#else>
			 	 <h3 class="page-header">未分红用户管理</h3>
		 </#if>
         </div>
     </div>

     <div class="row">
         <div class="col-md-12">
             <form id="table_query_form">
                 <div class="row">
                     <div class="_params">
                         <input type="hidden" class="form-control" tablesearch name="status" id="status" value="${status}"/>
                         <div class="col-md-2 column">
                             <div class="form-group">
                                 <label for="buyEmail">身份证号</label>
                                 <input type="text" class="form-control" tablesearch name="cardNumber_LIKE"/>
                             </div>
                         </div>
                         <div class="col-md-2 column">
                             <div class="form-group">
                                 <label for="buyMobilePhone">收入币种</label>

                      <@HrySelect url="${ctx}/exchange/exproduct/findall"  itemvalue="coinCode"  itemname="coinCode"   name="coinCode_EQ"  id="coinCode"   > </@HrySelect>
                             </div>
                         </div>
                         <div class="col-md-2 column">
                             <div class="form-group">
                                 <label for="transactionTime_GT">查询日期</label>
                                 <input type="text" class="form-control" name="queryDate" id="queryDate" readonly tablesearch  data-date-format="yyyy-mm-dd" data-min-view="month" value="${(info.birthday?string("yyyy-MM-dd"))!}">

                             </div>
                         </div>


                         <div class="col-md-2 column">
                             <div style="margin-top: 26px;">
                                 <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                                 <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                             </div>
                         </div>
                     </div>
                 </div>
             </form>
             <div id="toolbar">
			<#if status==0>
                <button id="manua" class="btn btn-info-blue" >
                    <i class="glyphicon glyphicon-edit"></i>发放
                </button>
			</#if>

             </div>
             <table id="table"
                    data-toolbar="#toolbar"
                    data-show-refresh="true"
                    data-show-columns="false"
                    data-show-export="false"
                    data-search="true"
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
     seajs.use(["js/admin/mining/ExMiningBonus","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,HryDate){
         HryDate.init();
         HrySelect.init();
         o.list();
     });

 </script>

