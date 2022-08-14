<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <div class="_params">
                <form id="sonTable_query_form">
                    <input id="customerId" name="customerId" type="hidden" value="${customerId}">
                </form>

            </div>
            <div id="userToolbar">
            </div>
            <table id="userTable"
                   data-toolbar="#userToolbar"
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
    seajs.use("js/admin/licqb/record/CommendUser",function(o){
        o.userAsset();
    });

</script>

