package hry.cm4.util;

import hry.util.properties.PropertiesUtils;
import tk.mybatis.mapper.util.StringUtil;

import java.nio.charset.Charset;

/**
 * Java简单的加密解密算法，使用异或运算
 */
public class AccountDeEnUtil {

    private static final String key0 = "(FDSF)*&<JYTYUM";
    private static final Charset charset = Charset.forName("UTF-8");
    private static byte[] keyBytes = key0.getBytes(charset);
    private static String isDeEnAccount= PropertiesUtils.APP.getProperty("app.isDeEnAccount");
    public static String encode(String enc){
        if(StringUtil.isEmpty(isDeEnAccount)||isDeEnAccount.equals("1")){
            byte[] b = enc.getBytes(charset);
            for(int i=0,size=b.length;i<size;i++){
                for(byte keyBytes0:keyBytes){
                    b[i] = (byte) (b[i]^keyBytes0);
                }
            }
            return new String(b);
        }else{
            return enc;
        }

    }

    public static String decode(String dec){
        if(StringUtil.isEmpty(isDeEnAccount)||isDeEnAccount.equals("1")){
            byte[] e = dec.getBytes(charset);
            byte[] dee = e;
            for(int i=0,size=e.length;i<size;i++){
                for(byte keyBytes0:keyBytes){
                    e[i] = (byte) (dee[i]^keyBytes0);
                }
            }
            return new String(e);
        }else{
            return dec;
        }
    }
}
