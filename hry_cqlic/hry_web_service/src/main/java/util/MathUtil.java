package util;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
 
import org.apache.log4j.Logger;

public class MathUtil {
    protected final static Logger logger = Logger.getLogger(MathUtil.class);
 
    /**
     * 生成一个安全码（UUID）
     * 
     * @return
     */
    public static String getSecurityCode() {
        return UUID.randomUUID().toString();
    }
 
    /**
     * 随机生成字符串
     * 
     * @param length    想要生成的长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
 
    /**
     * 获取6-10 的随机位数数字
     * @param length    想要生成的长度
     * @return result
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        int randInt = 0;
        for (int i = 0; i < n; i++) {
            randInt = rand.nextInt(10);
 
            result += randInt;
        }
        return result;
    }
 
    /**
     * MD5
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String getMD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            logger.error("MD5转换异常！message：" + e.getMessage());
            e.printStackTrace();
        }
 
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }
    
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z" };  
  
  
		public static String generateShortUuid() {  
		    StringBuffer shortBuffer = new StringBuffer(); 
		    SimpleDateFormat sd1 = new SimpleDateFormat("dd");
		    String dd=sd1.format(new Date());
		  
		    String uuid = UUID.randomUUID().toString().replace("-", "");  
		    for (int i = 0; i < 8; i++) {  
		        String str = uuid.substring(i * 4, i * 4 + 4);  
		        int x = Integer.parseInt(str, 16);  
		        shortBuffer.append(chars[x % 0x3E]);  
		        if(i==3){
		        	  shortBuffer.append(dd.substring(0, 1));
		        }
		        if(i==6){
		        	   shortBuffer.append(dd.substring(1, 2));
		        }
		    } 
		 
		    return shortBuffer.toString();  
  
}  
}