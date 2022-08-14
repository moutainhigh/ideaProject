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
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

public class QrCodeUtils {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int LOGO_WIDTH = 60;
    // LOGO高度
    private static final int LOGO_HEIGHT = 60;

    /**
     * 插入LOGO
     *
     * @param source       二维码图片
     * @param logoPath     LOGO图片地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, URL logoPath, boolean needCompress) throws Exception {
        if (logoPath.getFile() == null) {
            throw new Exception("logo file not found.");
        }
        Image src = ImageIO.read(logoPath);
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成二维码(内嵌LOGO)
     * 调用者指定二维码文件名
     *
     * @param content      内容
     * @param logoPath     LOGO地址
     * @param fileName     二维码文件名
     * @param builtIn      是否内嵌LOGO
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static String generateCode(String content, URL logoPath, String fileName, boolean builtIn, boolean needCompress) throws Exception {
        Hashtable<EncodeHintType,Object> hints = new Hashtable<EncodeHintType,Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoPath != null && !"".equals(logoPath) && builtIn) {
            // 插入图片
            QrCodeUtils.insertImage(image, logoPath, needCompress);
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
                OssUtil.upload(ossStream, "hryfile/" + fileName + ".jpg", false);
                break;
            case "aws": // 亚马逊aws
                AWSUtil.uploadToS3(ossStream, "hryfile/" + fileName + ".jpg");
                break;
            case "azure": // 微软azure
                AzureUtil.upload(ossStream, "hryfile/" + fileName + ".jpg");
                break;
            default: // 默认阿里云oss
                OssUtil.upload(ossStream, "hryfile/" + fileName + ".jpg", false);
                break;
        }
        return "hryfile/" + fileName + ".jpg";
    }
}

