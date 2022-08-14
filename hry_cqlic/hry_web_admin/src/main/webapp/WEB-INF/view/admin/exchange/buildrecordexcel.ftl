 <!-- Copyright:    -->
 <!-- BuildRecordAdd.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-17 12:01:26      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">K线修复excel  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}//v.do?u=admin/exchange/buildrecordadd')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="tradeCoinCode">交易对</label>
                    <@HrySelect url="${ctx}/exchange/exrobot/selectlist"  itemvalue="coinCode"  itemname="coinCode"  name="coinCode"  id="coinCode"   > </@HrySelect>
            </div>
            <#--<form id="form" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="tradeCoinCode">交易对</label>
                    <@HrySelect url="${ctx}/exchange/exrobot/selectlist"  itemvalue="coinCode"  itemname="coinCode"  name="coinCode"  id="coinCode"   > </@HrySelect>
                </div>
                <div class="form-group">
                    <label for="picturePath">上传</label>
                    <div class="hryUpload" style="width: 100%">
                        <div class="hry_inputBox">
                            <input class="form-control hryUpload_filePath " type="file" id="efile" name="efile" style="display:none">
                            <button type="button" class="btn btn-info btn-block" id="uploadExcel">上传excel</button>
                        </div>
                        <div class="hry_imgBox">

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2 column">
                        <button type="button" id="excelOk" class="btn  btn-info-blue btn-warning" >提交</button>
                    </div>
                </div>
            </form>-->
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="name">开</label>
                <input type="text" class="form-control" name="start" id="start" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="name">高</label>
                <input type="text" class="form-control" name="high" id="high" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="name">低</label>
                <input type="text" class="form-control" name="low" id="low" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="name">收</label>
                <input type="text" class="form-control" name="end" id="end" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="name">数量</label>
                <input type="text" class="form-control" name="amount" id="amount" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="name">时间</label>
                <input type="text" class="form-control" name="time" id="time" />
            </div>
            <span style="color: red; font-size: 12px;">时间格式必须为：yyyy-MM-dd HH:mm，例如：2018-05-01 07:03</span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 column">
            <button type="button" id="excelOk" class="btn  btn-info-blue btn-warning" >提交</button>
        </div>
    </div>
</form>

</div>
<script type="text/javascript">
seajs.use(["js/admin/exchange/BuildRecord","js/base/HrySelect","js/base/HryDateTop"],function(o,HrySelect,HryDate){
    HrySelect.init();
    HryDate.init();
    o.upload();
});
</script>




