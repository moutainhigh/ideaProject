/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0
 * @Date:        2015年10月12日 上午10:56:35
 */
package hry.util.properties;

/**
 * <p>
 * 全局常量
 * </p>
 *
 * @author: Gao Mimi
 * @Date : 2015年10月12日 上午10:56:35
 */
public class StringConstant {

	/**
	 * 编码方式
	 */
	public static final String CHAR_GBK = "GBK";
	public static final String CHAR_UTF = "UTF-8";
	/**
	 * 应用程序的格式化符
	 */
	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 短日期格式
	 */
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	public static final String DATE_FORMAT_TIME = "HH:mm:ss";
	/**
	 * 附件目录
	 */
	public static final String ATTACH_FILES = "attachFiles/";

	/**
	 * 上个页面地址
	 */
	public static final String BACK_URL = "BackURL";

	public static final String IGNORE_BACK_URL = "ignoreBackURL";

	/**
	 * 当前请求的地址 带参数
	 */
	public static final String CURRENT_URL = "currentURL";

	/**
	 * 当前请求的地址 不带参数
	 */
	public static final	String NO_QUERYSTRING_CURRENT_URL = "noQueryStringCurrentURL";

	/**
	 * 绝对路径
	 */
	public static final String BASE_PATH = "base";
	/**
	 * 根目录
	 */
	public static final String CONTEXT_PATH = "ctx";
	/**
	 * 静态资源 文件路径  css/js/img
	 */
	public static final String STATIC_CONTEXT_PATH = "staticCtx";

	 /**
	  * 验证码随机数在seesion存储的key值
	  */
   public static final String CAPTHCHA_RAND="captcha";
   /**
    * 网站缓存管理KEY
    */
   public static final String CONFIG_CACHE="configCache";

   /**
    * 地区数据字典缓存
    */
   public static final String AREA_CACHE="areaCache";
   /**
    * 网站全局配置信息缓存KEY（保存需要被管理的缓存的key值）
    */
   public static final String CACHE_TYPE="cacheType";

   /**
    * 银行缓存
    */
   public static final String CACHE_BANK="DIC:bank";

   /**
    * 成功标识
    */
   public static final String SUCCESS="8888";
   /**
    * 失败标识
    */
   public static final String FAIL="0000";

   /**
    * 国际站
    */
   public static final String WEBSITE_EN="en";
   /**
    * 中国站
    */
   public static final String WEBSITE_CN="cn";
   /**
    * banner缓存
    */
   public static final String CACHE_BANNER="DIC:banner";

   /**
    * 币的产品list
    */
   public static final String COININFOLIST="coinInfoList";


   /**
    * 币的产品c2c币list
    */
   public static final String PRODUCTFIXLIST="productFixList";



}
