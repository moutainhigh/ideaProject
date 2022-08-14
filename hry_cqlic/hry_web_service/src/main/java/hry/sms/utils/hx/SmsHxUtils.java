package hry.sms.utils.hx;



import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;


public class SmsHxUtils {
    private static Logger logger = Logger.getLogger(SmsHxUtils.class);
	//以下为所需的参数，测试时请修改,中文请先转为16进制再发送
	private final static String strReg = "101100-WEB-JZWW-758265";   //注册号（由华兴软通提供）
	private final static String strPwd = "TKARWUXL";                 //密码（由华兴软通提供）
	private final static String strSourceAdd = "";                   //子通道号，可为空（预留参数一般为空）

    //以下参数为服务器URL,以及发到服务器的参数，不用修改
    private final static String strRegUrl = "http://www.stongnet.com/sdkhttp/reg.aspx";
    private final static String strBalanceUrl = "http://www.stongnet.com/sdkhttp/getbalance.aspx";
    private final static  String strSmsUrl = "http://www.stongnet.com/sdkhttp/sendsms.aspx";
    private final static String strSchSmsUrl = "http://www.stongnet.com/sdkhttp/sendschsms.aspx";
    private final static String strStatusUrl = "http://www.stongnet.com/sdkhttp/getmtreport.aspx";
    private final static String strUpPwdUrl = "http://www.stongnet.com/sdkhttp/uptpwd.aspx";
    
    
	/**
	 * <p>发送短信</p>
	 * @author:         Liu Shilei
	 * @param:    @param mobilePhone
	 * @param:    @param content2
	 * @return: void 
	 * @Date :          2016年5月18日 下午2:36:21   
	 * @throws:
	 */
	public static String sendSms(String reg,String pwd,String mobilePhone, String code,String content) throws UnknownHostException {
		try {
			logger.error(content);
			content = HttpSend.paraTo16(content);

			String strSmsParam = "reg=" + reg + "&pwd=" + pwd + "&sourceadd=" + strSourceAdd + "&phone=" + mobilePhone + "&content=" + content;;
		    String strRes  = new String();
			strRes = HttpSend.postSend(strSmsUrl, strSmsParam);
			logger.error("短信第三方返回结果:"+strRes);
			return strRes;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return null;
	}
    
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		//以下为所需的参数，测试时请修改,中文请先转为16进制再发送
		String strReg = "101100-WEB-JZWW-482566";   //注册号（由华兴软通提供）
        String strPwd = "RUXUANSI";                 //密码（由华兴软通提供）
        String strSourceAdd = "";                   //子通道号，可为空（预留参数一般为空）
        String strTim = HttpSend.paraTo16("2012-2-16 12:00:00"); //定时发送时间
        String strPhone = "18393116713,18911468726,15917060107";//手机号码，多个手机号用半角逗号分开，最多1000个
        String content = "尊敬的用户：您的验证码为：123456【互融云币】";
        String strContent = HttpSend.paraTo16(content);       //短信内容
        
        String strUname = HttpSend.paraTo16("华兴"); //用户名，不可为空
        String strMobile = "18201202026";            //手机号，不可为空
        String strRegPhone = "01065688262";             //座机，不可为空
        String strFax = "01065685318";               //传真，不可为空
        String strEmail = "hxrt@stongnet.com";       //电子邮件，不可为空
        String strPostcode = "100080";               //邮编，不可为空
        String strCompany = HttpSend.paraTo16("华兴软通");    //公司名称，不可为空
        String strAddress = HttpSend.paraTo16("天阳ja");//公司地址，不可为空
        
        String strNewPwd = "BBBBBBBB";
        
        //以下参数为服务器URL,以及发到服务器的参数，不用修改
        String strRegUrl = "http://www.stongnet.com/sdkhttp/reg.aspx";
        String strBalanceUrl = "http://www.stongnet.com/sdkhttp/getbalance.aspx";
        String strSmsUrl = "http://www.stongnet.com/sdkhttp/sendsms.aspx";
        String strSchSmsUrl = "http://www.stongnet.com/sdkhttp/sendschsms.aspx";
        String strStatusUrl = "http://www.stongnet.com/sdkhttp/getmtreport.aspx";
        String strUpPwdUrl = "http://www.stongnet.com/sdkhttp/uptpwd.aspx";
        String strRegParam = "reg=" + strReg + "&pwd=" + strPwd + "&uname=" + strUname + "&mobile=" + strMobile + "&phone=" + strRegPhone + "&fax=" + strFax + 
                             "&email=" + strEmail + "&postcode=" + strPostcode + 
                             "&company=" + strCompany + "&address=" + strAddress;
        String strBalanceParam = "reg=" + strReg + "&pwd=" + strPwd;
        String strSmsParam = "reg=" + strReg + "&pwd=" + strPwd + "&sourceadd=" + strSourceAdd + "&phone=" + strPhone + "&content=" + strContent;;
        String strSchSmsParam = "reg=" + strReg + "&pwd=" + strPwd + "&sourceadd=" + strSourceAdd + "&tim=" + strTim + "&phone=" + strPhone + "&content=" + strContent;;
        String strStatusParam = "reg=" + strReg + "&pwd=" + strPwd;
        String strUpPwdParam = "reg=" + strReg + "&pwd=" + strPwd + "&newpwd=" + strNewPwd;
        
        String strRes  = new String();
        
        //以下为HTTP接口主要方法，测试时请打开对应注释进行测试
        //注册
        //strRes = HttpSend.postSend(strRegUrl, strRegParam);
        
        //查询余额
       // strRes = HttpSend.postSend(strBalanceUrl, strBalanceParam);
        
        //发送短信
         strRes = HttpSend.postSend(strSmsUrl, strSmsParam);
        
        //定时短信
        //strRes = HttpSend.postSend(strSchSmsUrl, strSchSmsParam);
        
        //状态报告
        //strRes = HttpSend.postSend(strStatusUrl, strStatusParam);
        
        //修改密码
        //strRes = HttpSend.postSend(strUpPwdUrl, strUpPwdParam);
        
        
        logger.error(strRes);
        
        
        
	}



}
