package hry.app.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtil {

    // token过期时间1天，毫秒
    private static final long EXPIRE_TIME = 24*60*60*1000;


    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @param source 来源，电脑(pc)还是手机端(app)
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String source, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(username)
                    .withClaim("source", source)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuer();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 获得Token中的信息无需secret解密也能获得
     * @param token
     * @param claim
     * @return java.lang.String
     * @author Wang926454
     * @date 2018/9/7 16:54
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            System.out.println("获取"+claim+"失败,token错误");
            //e.printStackTrace();
        }
        return null;
    }
    /**
     * 生成签名,5min后过期
     * @param username 用户名
     * @param secret 用户的密码
     * @param source 来源，电脑(pc)还是手机端(app)
     * @return 加密的token
     */
    public static String sign(String username, String source, String secret) {
        try {
            // 签发时间
            Date iatDate = new Date();
            Date date = new Date(iatDate.getTime() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withIssuer(username)
                    .withClaim("source", source)
                    .withExpiresAt(date)
                    .withIssuedAt(iatDate)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        String token = sign("e65@qq.com", "pc", "hryToken");
        String token2 = sign("e65@qq.com", "pc", "hryToken");
        System.out.println(token);
        System.out.println("是否相等"+token.equals(token2));
        while (true) {
            Thread.sleep(1000L);
            if(verify(token, "e65@qq.com", "pc", "hryToken")){
                System.out.println(true);
            } else {
                System.out.println(false);
                token = sign("e65@qq.com", "pc", "hryToken");
                System.out.println(token);
            }
        }
    }
}
