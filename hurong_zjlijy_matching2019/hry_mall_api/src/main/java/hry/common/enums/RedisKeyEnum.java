package hry.common.enums;

/**
 * 放入redis缓存中的key值定义
 *
 */
public class RedisKeyEnum {
	public enum KeyEnum{
		REDIS_DATA_DIC("数据字典","data_dic"),
		SYS_MESSAGE_TYPE("内容分类配置","sys_message_type"),
		REDIS_PUBLIC_CONFIG("公共配置","public_config"),
		
		REIDS_DATA_AREADIC_PROVINCE("地区数据字典(省)","date_area_dic_province"),
		REIDS_DATA_AREADIC_CITY("地区数据字典(市)","date_area_dic_city"),
		REIDS_DATA_AREADIC_COUNTY("地区数据字典(县)","date_area_dic_county"),
		APP_SFTP("sftp配置","app_sftp"),
		SFTP_PIC("sftp上传图片类型","sftp_pic"),
		SFTP_PDF("sftp上传PDF","sftp_pdf"),
		SFTP_DOC("sftp上传文档类型","sftp_doc"),
		MALL_DATE("商城多语言","mall_data"),
		GOODS_CLASS("商品分类名称","goodsClass"),
		GOODS_NAME("商品名称","goodsName"),
		GOODS_SUBTITLE("商品副名称","goodsSubtitle"),
		SHOP_FLOOR("商品名称","shopFloor"),
		GOODS_SPEC("商品规格名称","goodsSpec"),
		GOODS_SPEC_VALUE("商品规格值","goodsSpecValue"),
		DATA_SENSITIVE("过滤敏感词","data_sensitive");
		
		private String name;  //用于获取说明，
	    private String index;  //获取数值
		private KeyEnum(String name, String index) {
		    this.name = name;  
		    this.index = index;  
		}
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public String getIndex() {  
	        return index;  
	    }  
	    public void setIndex(String index) {  
	        this.index = index;  
	    }  
	}
}
