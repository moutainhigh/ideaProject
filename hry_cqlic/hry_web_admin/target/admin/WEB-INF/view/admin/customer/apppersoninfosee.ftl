 <!-- Copyright:    -->
 <!-- AppPersonInfoSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 09:45:27      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppPersonInfo--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/customer/apppersoninfolist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerType">customerType</label>
			 <input type="text" class="form-control" name="customerType" id="customerType" value="${model.customerType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="mobilePhone">mobilePhone</label>
			 <input type="text" class="form-control" name="mobilePhone" id="mobilePhone" value="${model.mobilePhone}" disabled/>
		</div>
		<div class="form-group">
			 <label for="email">email</label>
			 <input type="text" class="form-control" name="email" id="email" value="${model.email}" disabled/>
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" value="${model.trueName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sex">sex</label>
			 <input type="text" class="form-control" name="sex" id="sex" value="${model.sex}" disabled/>
		</div>
		<div class="form-group">
			 <label for="birthday">birthday</label>
			 <input type="text" class="form-control" name="birthday" id="birthday" value="${model.birthday}" disabled/>
		</div>
		<div class="form-group">
			 <label for="country">country</label>
			 <input type="text" class="form-control" name="country" id="country" value="${model.country}" disabled/>
		</div>
		<div class="form-group">
			 <label for="cardType">cardType</label>
			 <input type="text" class="form-control" name="cardType" id="cardType" value="${model.cardType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="cardId">cardId</label>
			 <input type="text" class="form-control" name="cardId" id="cardId" value="${model.cardId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerSource">customerSource</label>
			 <input type="text" class="form-control" name="customerSource" id="customerSource" value="${model.customerSource}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="realTime">realTime</label>
			 <input type="text" class="form-control" name="realTime" id="realTime" value="${model.realTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="emailCode">emailCode</label>
			 <input type="text" class="form-control" name="emailCode" id="emailCode" value="${model.emailCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="cardIdUsd">cardIdUsd</label>
			 <input type="text" class="form-control" name="cardIdUsd" id="cardIdUsd" value="${model.cardIdUsd}" disabled/>
		</div>
		<div class="form-group">
			 <label for="cardIdValidPeriod">cardIdValidPeriod</label>
			 <input type="text" class="form-control" name="cardIdValidPeriod" id="cardIdValidPeriod" value="${model.cardIdValidPeriod}" disabled/>
		</div>
		<div class="form-group">
			 <label for="postalAddress">postalAddress</label>
			 <input type="text" class="form-control" name="postalAddress" id="postalAddress" value="${model.postalAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="postCode">postCode</label>
			 <input type="text" class="form-control" name="postCode" id="postCode" value="${model.postCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="contacts">contacts</label>
			 <input type="text" class="form-control" name="contacts" id="contacts" value="${model.contacts}" disabled/>
		</div>
		<div class="form-group">
			 <label for="stage">stage</label>
			 <input type="text" class="form-control" name="stage" id="stage" value="${model.stage}" disabled/>
		</div>
		<div class="form-group">
			 <label for="contactsTel">contactsTel</label>
			 <input type="text" class="form-control" name="contactsTel" id="contactsTel" value="${model.contactsTel}" disabled/>
		</div>
		<div class="form-group">
			 <label for="handIdCardUrl">handIdCardUrl</label>
			 <input type="text" class="form-control" name="handIdCardUrl" id="handIdCardUrl" value="${model.handIdCardUrl}" disabled/>
		</div>
		<div class="form-group">
			 <label for="idCardFrontUrl">idCardFrontUrl</label>
			 <input type="text" class="form-control" name="idCardFrontUrl" id="idCardFrontUrl" value="${model.idCardFrontUrl}" disabled/>
		</div>
		<div class="form-group">
			 <label for="idCardBackUrl">idCardBackUrl</label>
			 <input type="text" class="form-control" name="idCardBackUrl" id="idCardBackUrl" value="${model.idCardBackUrl}" disabled/>
		</div>
		<div class="form-group">
			 <label for="handDealUrl">handDealUrl</label>
			 <input type="text" class="form-control" name="handDealUrl" id="handDealUrl" value="${model.handDealUrl}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isExamine">isExamine</label>
			 <input type="text" class="form-control" name="isExamine" id="isExamine" value="${model.isExamine}" disabled/>
		</div>
		<div class="form-group">
			 <label for="eamineRefusalReason">eamineRefusalReason</label>
			 <input type="text" class="form-control" name="eamineRefusalReason" id="eamineRefusalReason" value="${model.eamineRefusalReason}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isCancle">isCancle</label>
			 <input type="text" class="form-control" name="isCancle" id="isCancle" value="${model.isCancle}" disabled/>
		</div>
		<div class="form-group">
			 <label for="cancleReason">cancleReason</label>
			 <input type="text" class="form-control" name="cancleReason" id="cancleReason" value="${model.cancleReason}" disabled/>
		</div>
		<div class="form-group">
			 <label for="withdrawCheckMoney">withdrawCheckMoney</label>
			 <input type="text" class="form-control" name="withdrawCheckMoney" id="withdrawCheckMoney" value="${model.withdrawCheckMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="personBank">personBank</label>
			 <input type="text" class="form-control" name="personBank" id="personBank" value="${model.personBank}" disabled/>
		</div>
		<div class="form-group">
			 <label for="personCard">personCard</label>
			 <input type="text" class="form-control" name="personCard" id="personCard" value="${model.personCard}" disabled/>
		</div>
		<div class="form-group">
			 <label for="frontpersonCard">frontpersonCard</label>
			 <input type="text" class="form-control" name="frontpersonCard" id="frontpersonCard" value="${model.frontpersonCard}" disabled/>
		</div>
		<div class="form-group">
			 <label for="surname">surname</label>
			 <input type="text" class="form-control" name="surname" id="surname" value="${model.surname}" disabled/>
		</div>
		<div class="form-group">
			 <label for="papersType">papersType</label>
			 <input type="text" class="form-control" name="papersType" id="papersType" value="${model.papersType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="btcDate">btcDate</label>
			 <input type="text" class="form-control" name="btcDate" id="btcDate" value="${model.btcDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="btcCount">btcCount</label>
			 <input type="text" class="form-control" name="btcCount" id="btcCount" value="${model.btcCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="lendTimes">lendTimes</label>
			 <input type="text" class="form-control" name="lendTimes" id="lendTimes" value="${model.lendTimes}" disabled/>
		</div>
		<div class="form-group">
			 <label for="lengPing">lengPing</label>
			 <input type="text" class="form-control" name="lengPing" id="lengPing" value="${model.lengPing}" disabled/>
		</div>
		<div class="form-group">
			 <label for="lengRiskRate">lengRiskRate</label>
			 <input type="text" class="form-control" name="lengRiskRate" id="lengRiskRate" value="${model.lengRiskRate}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/customer/AppPersonInfo",function(o){
 	o.see();
 });
 </script>




