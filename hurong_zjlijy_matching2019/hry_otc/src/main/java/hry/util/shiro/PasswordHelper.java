package hry.util.shiro;


import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码加密
 * <p> TODO</p>
 * @author:         Liu Shilei
 * @Date :          2015年9月25日 上午10:36:45
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }


    /**
     * shiro盐值加密
     * @param pwd
     * @param salt
     * @return
     */
    public String encryString(String pwd,String salt){
	    	String newPassword = new SimpleHash(
	                algorithmName,
	                pwd,
	                ByteSource.Util.bytes(salt),
	                hashIterations).toHex();
	    	return newPassword;
    }

    public static void main(String [] args) {
    		PasswordHelper passwordHelper = new PasswordHelper();
    		String encryString = passwordHelper.encryString("admin", "df735750558faa8b6c12329c8d24dc5ahy");
    		System.out.println(encryString);
    }


}
