 <!-- Copyright:    -->
 <!-- ExRobotList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 16:33:44      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">交易账号设置</h3>
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
        <form id="table_query_form">
        <div class="row">
            <div class="_params">
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="coinCode">交易区</label>
							<@HrySelect url="${ctx}/exchange/extradingarea/queryOpenArea"  itemvalue="tradingArea"  itemname="tradingArea"   name="fixPriceCoinCode_EQ"  id="fixPriceCoinCode_EQ"   > </@HrySelect>
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


            <#--<button id="setAccount" class="btn  btn-info-blue" >
 	            <i class="glyphicon glyphicon-edit"></i>设定账号
 	        </button>-->
            <button id="setMoreAccount" class="btn  btn-info-blue" >
                <i class="glyphicon glyphicon-edit"></i>批量设定账号
            </button>

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
               data-page-size="50"
 	           >
 	    </table>
     </div>
 </div>


 <div class="row">
 </div>

	<#--开启交易时输入密码-->
    <div id="setAccountDiv" style="display:none; top:20px;">
        <form style="text-align: center;">
            <input type="hidden" id="ids">
            注册用户账号:
            <input type="text" id="account"  style="text-align: center;margin-top: 20px;"/><br>
            注册用户密码:
            <input type="password" id="accountpwd" style="text-align: center;margin-top: 20px;"/><br>
            <div class="col-md-2 column" style="margin: 0px 135px -100px;">
                <button type="button" id="confirmset" class="btn  btn-info-blue btn-warning"
                        style="margin-top: 18px;margin-left: 8px;">提交
                </button>
            </div>
        </form>
    </div>

</div>
 <script type="text/javascript">
 seajs.use(["js/admin/exchange/ExRobotDeep","js/base/HrySelect"],function(o,HrySelect){
 	o.accountinfolist(1);
    HrySelect.init();
 });

 </script>

