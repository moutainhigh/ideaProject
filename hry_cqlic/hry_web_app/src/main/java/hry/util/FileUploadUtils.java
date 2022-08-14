package hry.util;

import hry.util.rsa.RSAUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

public class FileUploadUtils {
    /**
     * 上传图片
     *
     * @param files
     * @return
     */
    public static String[] upload(@RequestParam("file") MultipartFile[] files) {
        String[] pathImg = new String[3];
        try {
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    MultipartFile file = files[i];
                    if(file !=null){
                        // 获取文件名
                        String filename = file.getOriginalFilename();
                        // 上传图片
                        if (filename != null && filename.length() > 0) {
                            // 新图片名称
                            String newFileName = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));


                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                            //压缩文件
                            Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.8f).toOutputStream(byteArrayOutputStream);
                            //上传流
                            InputStream ossStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                            byteArrayOutputStream.close();


                            String img_server_type = PropertiesUtils.APP.getProperty("app.img.server.type");
                            switch (img_server_type) {
                                case "oss": // 阿里云oss
                                    OssUtil.upload(ossStream, "hryfile/"+ newFileName,true);
                                    break;
                                case "aws": // 亚马逊aws
                                    AWSUtil.uploadToS3(ossStream, "hryfile/"+ newFileName);
                                    break;
                                case "azure": // 微软azure
                                    AzureUtil.upload(ossStream, "hryfile/"+ newFileName);
                                    break;
                                default: // 默认阿里云oss
                                    OssUtil.upload(ossStream, "hryfile/"+ newFileName,true);
                                    break;
                            }
                            pathImg[i] = "hryfile/" + newFileName;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathImg;
    }

    public static HttpServletRequest getRequest() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            return request;
        } catch (Exception e) {
        }
        return null;

    }
    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public static void delete(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
        }
    }

    /**
     * 获取RSA密钥，并将RSA公钥存入mav中，将私钥存入session中
     * @param request
     * @param mav
     * @param RSA_privateKey
     */
    public static void setPublicKey(HttpServletRequest request, ModelAndView mav, String RSA_privateKey) {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            //私钥存入session
            String privateKey = RSAUtils.getPrivateKey(keyMap);
            request.getSession().setAttribute(RSA_privateKey,privateKey);
            //公钥返回给业务
            String publicKey = RSAUtils.getPublicKey(keyMap);
            mav.addObject("RSA_publicKey",publicKey);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
