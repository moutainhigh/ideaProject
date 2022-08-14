<!-- Copyright:    -->
 <!-- ExProductAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:44:37      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加上架币
            <button type="button" class="btn  btn-info-blue pull-right"
                    onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exproductlist')"><i class="fa fa-mail-forward"></i>返回
            </button>
        </h3>
    </div>
</div>


<form id="form">

    <div class="row">

        <div class="col-md-4 column">

            <div class="form-group">
                <label for="coinCode">币种代码(英文)</label>
                <input type="text" class="form-control" name="coinCode" id="coinCode"/>
            </div>

            <div class="form-group">
                <label for="coinCode">币种精度</label>
                <@HrySelect key="coinprecision"  name="precision"  id="precision"   > </@HrySelect>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="addCoinType">上币类型</label>
                <@HrySelect key="addCoinType"  name="addCoinType"  id="addCoinType"   > </@HrySelect>
            </div>
        </div>

        <div class="col-md-4 column">
            <div class="form-group">
                <label for="contractAddress">合约地址</label>
                <input type="text" class="form-control" name="contractAddress" id="contractAddress"/>
            </div>
        </div>

    </div>




    <div class="row">
        <div class="col-md-8 column">
            <div class="form-group">
                <label for="productReferral">币种介绍</label>
                <input type="text" class="form-control" name="productReferral" id="productReferral"/>
            </div>
        </div>

    </div>



    <div class="row">
        <div class="col-md-2 column">
            <button type="button" id="addSubmit" class="btn btn-blue btn-block">提交</button>
        </div>
    </div>

</form>
</div>
<script type="text/javascript">
    seajs.use(["js/admin/exchange/ExProduct","js/base/HryUpload"], function (o,HryUpload) {
        HryUpload.upload();
        o.proxyadd();
    });
</script>




