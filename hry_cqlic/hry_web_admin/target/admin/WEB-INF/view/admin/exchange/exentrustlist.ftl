 <!-- Copyright:    -->
 <!-- ExEntrustList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 11:12:40      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">当前委托</h3>
     </div>
 </div>
 <div class="row">
			<div class="_params">
	 		</div>
 </div>
 <div class="row">
 	<div class="col-md-12">
 		<div class="_params">
 			
 		</div>
 	    <div id="toolbar">

 	    </div>
 	    <table id="table"
               data-toolbar="#toolbar"
               data-show-refresh="true"
               data-show-columns="true"
               data-show-export="true"
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


 <div class="row">
 </div>
    <div class="col s4 hide" id="matchDetailDiv">

        <!-- begin row -->

        <div class="row">
            <!-- begin col-3 -->
            <div class="col-sm-12">
                <div class="table-responsive fund-dtable">
                    <table class="table" id="table2">
                        <thead>
                        <tr class="active">
                            <th>成交时间</th>
                            <th>成交数量</th>
                            <th>成交价格</th>
                            <th>成交额</th>

                        </tr>
                        </thead>
                        <tbody id="tbody">


                        </tbody>
                    </table>
                </div>

            </div>
            <!-- end col-3 -->
        </div>
        <!-- end row -->
    </div>

</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExEntrust",function(o){
 	o.list("listnow");
 });

 </script>

