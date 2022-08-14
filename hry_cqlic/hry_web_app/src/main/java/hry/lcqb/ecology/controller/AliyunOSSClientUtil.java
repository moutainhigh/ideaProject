package hry.lcqb.ecology.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 *
 * @ClassName: AliyunOSSClientUtil
 * @Description: 阿里云OSS上传文件模块
 * @author wy
 * @date 2017年5月6日 下午6:01:46
 *
 */
@Component
public class AliyunOSSClientUtil {
    // log日志
    private static Logger logger = Logger.getLogger(AliyunOSSClientUtil.class);
    // 阿里云API的内或外网域名
    private static String ENDPOINT;
    // 阿里云API的密钥Access Key ID
    private static String ACCESS_KEY_ID;
    // 阿里云API的密钥Access Key Secret
    private static String ACCESS_KEY_SECRET;
    // 阿里云API的bucket名称
    public static String BACKET_NAME;
    // 阿里云API的文件夹名称
    private static String FOLDER;
    private static String FORMAT;
    private static String FORMATS;
    private static String FOLDER_VIDEO;

    private static OSSClient ossClient;

    // 初始化属性
    static {
        ENDPOINT = com.baidu.ueditor.PropertiesUtils.APP.getProperty("app.oss.endpoint");
        ACCESS_KEY_ID = com.baidu.ueditor.PropertiesUtils.APP.getProperty("app.oss.accessKeyId");
        ACCESS_KEY_SECRET = com.baidu.ueditor.PropertiesUtils.APP.getProperty("app.oss.accessKeySecret");
        BACKET_NAME = com.baidu.ueditor.PropertiesUtils.APP.getProperty("app.oss.publicBucketName");
        FOLDER = "folder";
        FORMAT = "format";
        FORMATS = "formats";
        FOLDER_VIDEO = "folder_video";
    }

    /**
     * 获取阿里云OSS客户端对象
     *
     * @return ossClient
     */
    public static OSSClient getOSSClient() {
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 创建存储空间
     *
     * @param ossClient
     *            OSS连接
     * @param bucketName
     *            存储空间
     * @return
     */
    public static String createBucketName(OSSClient ossClient, String bucketName) {
        // 存储空间
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            // 创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     *
     * @param ossClient
     *            oss对象
     * @param bucketName
     *            存储空间
     */
    public static void deleteBucket(OSSClient ossClient, String bucketName) {
        ossClient.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     *
     * @param ossClient
     *            oss连接
     * @param bucketName
     *            存储空间
     * @param folder
     *            模拟文件夹名如"qj_nanjing/"
     * @return 文件夹名
     */
    public static String createFolder(OSSClient ossClient, String bucketName, String folder) {
        // 文件夹名
        final String keySuffixWithSlash = folder;
        // 判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            // 创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            // 得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param ossClient
     *            oss连接
     * @param bucketName
     *            存储空间
     * @param folder
     *            模拟文件夹名 如"qj_nanjing/"
     * @param key
     *            Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key) {
        ossClient.deleteObject(bucketName, folder + key);
        logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    /**
     * 上传图片至OSS 文件流
     *
     * @param ossClient
     *            oss连接
     * @param file
     *            上传文件（文件全路径如：D:\\image\\cake.jpg）
     * @param bucketName
     *            存储空间
     * @param uploadFilePath
     *            上传文件路径
     * @param fileName
     *            文件名称
     * @return String 返回的唯一MD5数字签名
     */
    public static String[] uploadObject2OSS(OSSClient ossClient,MultipartFile file, String fileName) {
        String resultStr = null;
        String[] fo = new String[] { "", "" };
        try {
            // 以输入流的形式上传文件
            String folder = "";
            folder = "hryfile/video/";
            InputStream is = file.getInputStream();
            logger.info("上传到路径" + folder + fileName);
            // 文件大小
            Long fileSize = file.getSize();
            logger.info("上传到路径" +  folder + fileName);
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(BACKET_NAME,  folder + fileName, is, metadata);
            // 解析结果
            resultStr = putResult.getETag();
            fo[1] =  folder + fileName;
            fo[0] = resultStr;

            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return fo;
    }

    public static byte[] image2Bytes(String imgSrc) throws Exception {
        FileInputStream fin = new FileInputStream(new File(imgSrc));
        // 可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes = new byte[fin.available()];
        // 将文件内容写入字节数组，提供测试的case
        fin.read(bytes);

        fin.close();
        return bytes;
    }
    //图片转化为byte数组
    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName
     *            文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        // 文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)
                || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        if (".mp4".equalsIgnoreCase(fileExtension)) {
            return "video/mp4";
        }
        // 默认返回类型
        return "image/jpeg";
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public static String getUrl(OSSClient ossClient, String bucketName, String fileName) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
        if (url != null) {
            return url.toString();
        }
        return "获网址路径出错";
    }

    // 测试
    public static void main(String[] args) throws Exception {
        AliyunOSSClientUtil t = new AliyunOSSClientUtil();
    }

}
