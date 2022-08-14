package hry.ico.service.impl;

import com.alibaba.fastjson.JSONObject;
import hry.bean.ObjectUtil;
import hry.exchange.product.model.ExProduct;
import hry.ico.service.CoinCodeKeepDigitService;
import hry.redis.common.utils.RedisService;
import hry.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("coinCodeKeepDigitService")
public class CoinCodeKeepDigitServiceImpl implements CoinCodeKeepDigitService {

    private static String redisKey="ex:Product";
   @Resource
   private RedisService redisService;
    //获取保留位数
    @Override
    public int getCoinCodeKeepDigit(String coinCode) {
         String  coinInfo=redisService.getMap(redisKey,coinCode);
         if(StringUtil.isNull(coinInfo)){
             try {
                 ExProduct exProduct= JSONObject.parseObject(coinInfo, ExProduct.class);
                 return  exProduct.getKeepDecimalForCoin();
             }catch (Exception e){
                 e.printStackTrace();
             }
         }
         return 0;
    }
}
