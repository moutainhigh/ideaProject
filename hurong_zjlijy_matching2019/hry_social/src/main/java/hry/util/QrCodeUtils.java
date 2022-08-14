package hry.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import hry.util.oss.AWSUtil;
import hry.util.oss.AzureUtil;
import hry.util.oss.OssUtil;
import hry.util.properties.PropertiesUtils;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.UUID;

public class QrCodeUtils {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int LOGO_WIDTH = 60;
    // LOGO高度
    private static final int LOGO_HEIGHT = 60;

    private static BufferedImage createImage(String content, String fileName, boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        InputStream is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        //压缩文件
        Thumbnails.of(is).scale(1f).outputQuality(0.8f).toOutputStream(byteArrayOutputStream);
        //上传流
        InputStream ossStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        byteArrayOutputStream.close();
        String img_server_type = PropertiesUtils.APP.getProperty("app.img.server.type");
        switch (img_server_type) {
            case "oss": // 阿里云oss
                OssUtil.upload(ossStream, "hryfile/"+ fileName+".jpg",false);
                break;
            case "aws": // 亚马逊aws
                AWSUtil.uploadToS3(ossStream, "hryfile/"+ fileName+".jpg");
                break;
            case "azure": // 微软azure
                AzureUtil.upload(ossStream, "hryfile/"+ fileName+".jpg");
                break;
            default: // 默认阿里云oss
                OssUtil.upload(ossStream, "hryfile/"+ fileName+".jpg",false);
                break;
        }

        return image;
    }


    /**
     * 生成二维码(内嵌LOGO)
     * 调用者指定二维码文件名
     *
     * @param content
     *            内容
     * @param logoPath
     *            LOGO地址
     * @param
     *
     * @param fileName
     *            二维码文件名
     * @param needCompress
     *            是否压缩LOGO
     * @throws Exception
     */
    public static String encode(String content,String fileName, boolean needCompress) throws Exception {
        BufferedImage image = QrCodeUtils.createImage(content, fileName, needCompress);
        return fileName;
    }


    public static void main(String[] args) throws Exception {
        String text = "https://blog.csdn.net/ianly123";
        //含Logo，指定二维码图片名
        String s1 = "http://hry-exchange-public.oss-cn-beijing.aliyuncs.com/hryfile/2/2/tux.jpg";
        URL a = new URL(s1);
        String s = UUID.randomUUID().toString();
        String qrcode = QrCodeUtils2.encode(text, null, "C:/Users/yujl/Pictures/Saved Pictures"+s, true);
        System.err.println(s);
    }
}

