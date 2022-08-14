<!-- Copyright:    -->
 <!-- ExProductParameterList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:49:05      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">币种交易参数配置</h3>
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
                <button id="configmodify" class="btn  btn-info-blue">
                    <i class="glyphicon glyphicon-edit"></i>编辑
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
    <div class="row">
    </div>
</div>
 <script type="text/javascript">
     seajs.use("js/admin/exchange/ExProduct", function (o) {
         o.list();
     });

 </script>

