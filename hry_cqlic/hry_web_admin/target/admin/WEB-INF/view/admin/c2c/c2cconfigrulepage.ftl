 <!-- Copyright:    -->
 <!-- C2cCoinAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 12:06:01      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">C2C交易匹配规则管理 </h3>
    </div>
</div>


<form id="form" >

    <div class="row">
        <div class="col-md-6 column">
            <div class="form-group has-feedback">
                <div class="radio">
                    <label>
                        <input type="radio" name="c2crule" value="0" id ="rule1" <#if c2crule.c2crule=='0'>class="dfdf" checked </#if>  >指定匹配
                    </label>
                </div>
                <i class="form-control-feedback glyphicon glyphicon-ok" id="iconRule1" style="display: none;"></i>
            </div>
            <div class="form-group">
                <label for="coinCode">交易商类型</label>
                <select  class="form-control" name="businessmanType" id="businessmanType">
                    <option <#if c2crule.businessmanType=='A'>selected="selected" </#if>  value="A">A类</option>
                    <option <#if c2crule.businessmanType=='B'>selected="selected" </#if> value="B">B类</option>
                </select>
            </div>

            <div class="alert alert-danger">
                匹配说明：选择商家类别，按照交易笔数从小到大匹配。
            </div>
        </div>
    </div>


    <div class="row">

        <div class="col-md-6 column">
            <hr/>
            <div class="form-group has-feedback has-success">
                <div class="radio">
                    <label>
                        <input type="radio" name="c2crule"  value="1" id ="rule2" <#if c2crule.c2crule=='1'>checked </#if> >随机匹配
                    </label>
                </div>
                <i class="form-control-feedback glyphicon glyphicon-ok" id="iconRule2" style="display: none;"></i>
            </div>

            <div class="alert alert-danger">
                匹配说明：商家类型系统随机选择。
            </div>

        </div>
    </div>
<#--
    <div class="row">
        <div class="col-md-6 column">
            <hr/>
            <div class="form-group has-feedback">
                <div class="radio">
                    <label>
                        <input type="radio" name="c2crule" value="2" id ="rule3" <#if c2crule.c2crule=='2'>checked </#if> >价格匹配
                    </label>
                </div>
                <i class="form-control-feedback glyphicon glyphicon-ok" id="iconRule3" style="display: none;"></i>
            </div>

            <div class="form-group">
                <label for="coinCode">交易商类型</label>
                <select  class="form-control " name="businessmanType2" id="businessmanType2">
                    <option <#if c2crule.businessmanType2=='AB'>selected="selected" </#if>  value="AB">A、B随机</option>
                    <option <#if c2crule.businessmanType2=='A'>selected="selected" </#if>  value="A">A类</option>
                    <option <#if c2crule.businessmanType2=='B'>selected="selected" </#if> value="B">B类</option><
                </select>
            </div>

            <div class="alert alert-danger">
                匹配说明：选择商家类型，按照价格由低到高匹配。
            </div>
        </div>
    </div>-->





<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>
<input type="hidden" id="c2cruleValue" value="${c2crule.c2crule}">
</form>
 </div>
<script type="text/javascript">
    seajs.use(["js/admin/c2c/C2cConfig","js/base/HrySelect"],function(o,HrySelect){
        HrySelect.init();
        o.rule();
    });
</script>




