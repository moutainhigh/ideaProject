var inputs={
	number:function(val){
      let re='';
	  let newVal = val;
      let reg = /^[0-9]*$/
      let newVals=[];
      if(newVal!=null&&newVal!=""){
      	newVals=newVal.split("")
      }
      let news='';
      let index=0;
      for(let i=0;i<newVals.length;i++){
      		news=news+newVals[i];
	      	if (index==0&&!reg.test(news)&&index==0) {
	      		index=1;
	      		let a='';
	      		if(news.length>1){
	      			a=news.substring(0,news.length-1);
	      		}
		      	re=a;
	      }
      }

      if(index==0){
      	re=newVal;
      }
      return re;

	},
	numberAndLetter:function(val){

	  let re='';
	  let newVal = val;
      let reg = /^[a-zA-Z0-9]+$/
      let newVals=[];
      if(newVal!=null&&newVal!=""){
      	newVals=newVal.split("")
      }
      let news='';
      let index=0;
      for(let i=0;i<newVals.length;i++){
      		news=news+newVals[i];
	      	if (index==0&&!reg.test(news)&&index==0) {
	      		index=1;
	      		let a='';
	      		if(news.length>1){
	      			a=news.substring(0,news.length-1);
	      		}
		      	re=a;
	      }
      }

      if(index==0){
      	re=newVal;
      }
      return re;
	},
	telNumberVerification:function(val){
		var reg=11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/;
		if(!reg.test(val)){
			return false;
		}
		return true;
	},
	emailVerification:function(val){
		var reg=/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
		if(!reg.test(val)){
			return false;
		}
		return true;
	},
	idCardVerification:function(idCard){
		//15位和18位身份证号码的正则表达式
         var regIdCard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
         //如果通过该验证，说明身份证格式正确，但准确性还需计算
         if(regIdCard.test(idCard)){
             if(idCard.length==18){
                 var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
                 var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
                 var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
                 for(var i=0;i<17;i++){
                     idCardWiSum+=idCard.substring(i,i+1)*idCardWi[i];
                     }
                 var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
                 var idCardLast=idCard.substring(17);//得到最后一位身份证号码

                 //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
                 if(idCardMod==2){
                     if(idCardLast=="X"||idCardLast=="x"){
                         //alert("恭喜通过验证啦！");
                         return true;
                         }else{
                             // alert("身份证号码错误！");
                             return false;
                     }
                     }else{
                         //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                         if(idCardLast==idCardY[idCardMod]){
                             //alert("恭喜通过验证啦！");
                             return true;
                     }else{
                         //alert("身份证号码错误！");
                         return false;
                     }
                 }
                 }
             }else{
                 //alert("身份证格式不正确!");
                 return false;
         }
	},
	checkSocialCreditCode:function(Code){
　　var patrn = /^[0-9A-Z]+$/;
 　　//18位校验及大写校验
　　 if ((Code.length != 18) || (patrn.test(Code) == false)) {
　　　　　　return false;
　　　　 }
	　　else {
	　　　　var Ancode;//统一社会信用代码的每一个值
	 　　　var Ancodevalue;//统一社会信用代码每一个值的权重
	　　　　var total = 0;
	　　　　var weightedfactors = [1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28];//加权因子
	　　　　var str = '0123456789ABCDEFGHJKLMNPQRTUWXY';
	　　　　//不用I、O、S、V、Z
	　　　　for (var i = 0; i < Code.length - 1; i++) {
		 　　　　Ancode = Code.substring(i, i + 1);
		　　　　Ancodevalue = str.indexOf(Ancode);
		　　　　total = total + Ancodevalue * weightedfactors[i];
		　　　　//权重与加权因子相乘之和
	　　　　}
	 　　　　var logiccheckcode = 31 - total % 31;
	　　　　if (logiccheckcode == 31){
	　　　　　　logiccheckcode = 0;
	　　　　}
	　　　　var Str = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,J,K,L,M,N,P,Q,R,T,U,W,X,Y";
	　　　　var Array_Str = Str.split(',');
	　　　　logiccheckcode = Array_Str[logiccheckcode];
	　　　　 var checkcode = Code.substring(17, 18);
	　　　　 if (logiccheckcode != checkcode) {
	　　　　　　return false;
	 　　　　}
			return true;
	 　　}
	},
  passwordVerification: function (val) {
    var reg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d]{8,12}$/;
    if(!reg.test(val)){
      return false;
    }
    return true;
  },
  formatBankNo:function(BankNo){
  var account = new String (BankNo);
  account = account.substring(0,22); /*帐号的总数, 包括空格在内 */
  if (account.match (".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null){
    /* 对照格式 */
    if (account.match (".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" +
        ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null){
      var accountNumeric = accountChar = "", i;
      for (i=0;i<account.length;i++){
        accountChar = account.substr (i,1);
        if (!isNaN (accountChar) && (accountChar != " ")) accountNumeric = accountNumeric + accountChar;
      }
      account = "";
      for (i=0;i<accountNumeric.length;i++){  /* 可将以下空格改为-,效果也不错 */
        if (i == 4) account = account + " "; /* 帐号第四位数后加空格 */
        if (i == 8) account = account + " "; /* 帐号第八位数后加空格 */
        if (i == 12) account = account + " ";/* 帐号第十二位后数后加空格 */
        account = account + accountNumeric.substr (i,1)
      }
    }
    alert("a")
  }
  else
  {
    account = " " + account.substring (1,5) + " " + account.substring (6,10) + " " + account.substring (14,18) + "-" + account.substring(18,25);
    alert(b)
  }
  if (account != BankNo.value){
    BankNo.value = account;
    alert(c)
  }
}
}
export default inputs
