package util;

import java.math.BigDecimal;

public class BigDecimalUtil {
    private static int digit=8;

    public static String bigDecimalToString(BigDecimal decimal){
        return decimal.setScale(digit,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
    }

    public static BigDecimal bigDecimalScale8(BigDecimal decimal){
        return decimal.setScale(digit,BigDecimal.ROUND_DOWN);
    }
    public static BigDecimal bigDecimalScaleDigit(BigDecimal decimal,int digit){
        return decimal.setScale(digit,BigDecimal.ROUND_DOWN);
    }
    public static String bigDecimalScaleDigitToString(BigDecimal decimal,int digit){
        return decimal.setScale(digit,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
    }
}
