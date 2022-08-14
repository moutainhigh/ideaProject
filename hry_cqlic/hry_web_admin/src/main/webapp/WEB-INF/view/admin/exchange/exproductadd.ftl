<!-- Copyright:    -->
 <!-- ExProductAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:44:37      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加币种
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
                <label for="name">币种名称</label>
                <input type="text" class="form-control" name="name" value="${model.name}" id="name" />
            </div>
        </div>
        <div class="col-md-4 column">

            <div class="form-group">
                <label for="coinCode">币种代码(英文)</label>
                <input type="text" class="form-control" name="coinCode" value="${model.coinCode}"id="coinCode" />
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="name">发行时间</label>
                <input type="text" class="form-control" name="issueTime" id="issueTime"    data-date-format="yyyy-mm-dd hh:mm:ss" data-min-view="month"  value="${model.issueTime?string("yyyy-MM-dd hh:mm:ss")}">
            </div>
        </div>
        <div class="col-md-4 column">

            <div class="form-group">
                <label for="coinCode">众筹价格</label>
                <input type="text" class="form-control" name="crowdfundingPrice" value="${model.crowdfundingPrice}" id="crowdfundingPrice" />
            </div>
        </div>

    </div>


    <div class="row">
        <div class="col-md-4 column">

            <div class="form-group">
                <label for="coinCode">币种全称</label>
                <input type="text" class="form-control" name="allName" value="${model.allName}"id="allName" />
            </div>
        </div>
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="name">发行总量</label>
                <input type="text" class="form-control" name="totalNum" value="${model.totalNum}" id="totalNum"/>
            </div>
        </div>


    </div>
    <div class="row">
        <div class="col-md-4 column">

            <div class="form-group">
                <label for="coinCode">流通总量</label>
                <input type="text" class="form-control" name="stock" value="${model.stock}" id="stock" />
            </div>
        </div>



    </div>

    <div class="row">
        <div class="col-md-2 column">
            <div class="form-group">
                <label for="picturePath">上传币种图标(规格:20px*20px)</label>
                <div class="hryUpload" style="width: 100%">
                    <div class="hry_inputBox">
                        <input class="form-control hryUpload_filePath " type="hidden" name="picturePath" value="${model.picturePath}" >
                        <button type="button" class="btn btn-primary btn-block">上传图片</button>
                    </div>
                    <div class="hry_imgBox">

                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-8 column">
            <div class="form-group">
                <label for="walletDownload">钱包下载地址</label>
                <input type="text" class="form-control" name="walletDownload" value="${model.walletDownload}" id="walletDownload"/>

            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-8 column">
            <div class="form-group">
                <label for="walletDownload">白皮书</label>
                <input type="text" class="form-control" name="writeBook" value="${model.writeBook}" id="writeBook"/>

            </div>
        </div>

    </div>
    <div class="row">
        <div class="col-md-8 column">
            <div class="form-group">
                <label for="blockBrowser">区块查询</label>
                <input type="text" class="form-control" name="blockBrowser" value="${model.blockBrowser}" id="blockBrowser"/>


            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-8 column">
            <div class="form-group">
                <label for="officialWebsite">官网</label>
                <input type="text" class="form-control" name="officialWebsite" value="${model.officialWebsite}" id="officialWebsite"/>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-8 column">
            <div class="form-group">
                <label for="officialWebsite">币种介绍</label>
                <input type="text" class="form-control" name="productReferral" value="${model.productReferral}" id="productReferral"/>
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
    seajs.use(["js/admin/exchange/ExProduct","js/base/HryUpload","js/base/HryDate"], function (o,HryUpload,HryDate) {
        HryUpload.upload();
        o.add();
        HryDate.init();
    });
</script>




