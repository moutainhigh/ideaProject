<!-- Copyright:    -->
 <!-- AppCustomerAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 09:43:00      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">实名审核
            <button type="button" class="btn  btn-info-blue pull-right"
                    onclick="loadUrl('${ctx}/customer/appcustomer/toCustomer.do?isReal=0')"><i
                    class="fa fa-mail-forward"></i>返回
            </button>
        </h3>
    </div>
</div>


<form id="form">
    <input type="hidden" class="form-control" name="customerid" value="${model.id}" id="customerid"/>
    <div class="row">
        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="email">手机号</label>
                    <input type="text" class="form-control" name="email" value="${model.appPersonInfo.mobilePhone}" id="email"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="email">邮箱</label>
                    <input type="text" class="form-control" name="email" value="${model.appPersonInfo.email}" id="email"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="surname">姓氏</label>
                    <input type="text" class="form-control" name="surname" value="${model.appPersonInfo.surname}" id="surname"/>
                </div>
            </div>
            <div class="col-md-4 column">

                <div class="form-group">
                    <label for="trueName">名字</label>
                    <input type="text" class="form-control" name="trueName" value="${model.appPersonInfo.trueName}" id="trueName"/>
                </div>
            </div>
        </div>

        <div class="row">
            <#--<div class="col-md-4 column">-->
                <#--<div class="form-group">-->
                    <#--<label for="cardType">证件类型</label>-->
                    <#--<input type="text" class="form-control" name="cardType" value="${model.cardType}" id="cardType"/>-->
                <#--</div>-->
            <#--</div>-->
            <div class="col-md-4 column">

                <div class="form-group">
                    <label for="cardId">证件号</label>
                    <input type="text" class="form-control" name="cardId" value="${model.appPersonInfo.cardId}" id="cardId"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="name">身份证正面照</label>
                    <img width="700" height="500" src="${model.appPersonInfo.personBank}">
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="name">身份证反面照</label>
                    <img width="700" height="500" src="${model.appPersonInfo.personCard}">
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="name">手持身份证照片</label>
                    <img width="700" height="500" src="${model.appPersonInfo.frontpersonCard}">
                </div>
            </div>

        </div>

    </div>

    <div class="row">
        <div class="col-md-2 column">
            <button type="button" id="audit" class="btn btn-blue btn-block">通过</button>
        </div>
        <div class="col-md-2 column">
            <button type="button" id="auditback" class="btn btn-blue btn-block">驳回</button>
        </div>
    </div>

</form>
</div>

<script type="text/javascript">
    seajs.use("js/admin/customer/AppCustomer", function (o) {
        o.realname();
    });
</script>




