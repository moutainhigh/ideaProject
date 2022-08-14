 <!-- Copyright:    -->
 <!-- C2cCoinAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 12:06:01      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">C2C交易设置 </h3>
    </div>
</div>


<form id="form" >


    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="c2cTimeOut">交易超时时间（分钟）,默认为30分钟</label>
                <input type="number" class="form-control" name="c2cTimeOut"  id="c2cTimeOut" value="${c2cTimeOut}"/>
            </div>
        </div>
    </div>



<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>
 </div>
<script type="text/javascript">
seajs.use(["js/admin/c2c/C2cConfig","js/base/HrySelect"],function(o,HrySelect){
    HrySelect.init();
	o.timeout();
});
</script>




