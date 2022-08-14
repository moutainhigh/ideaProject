package hry.util;

import hry.util.properties.PropertiesUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FileUploadUtils {

    private static final List<String> fileTypes = new ArrayList<String>() {{
        add("jpg");
        add("png");
        add("gif");
        add("bmp");
        add("JPG");
        add("PNG");
        add("GIF");
        add("BMP");
        add("mp4");
        add("MP4");
    }};
    private static final List<String> picTypes = new ArrayList<String>() {{
        add("jpg");
        add("png");
        add("gif");
        add("bmp");
        add("JPG");
        add("PNG");
        add("GIF");
        add("BMP");
    }};
    private static final List<String> vidoTypes = new ArrayList<String>() {{
        add("mp4");
        add("MP4");
    }};

    /**
     * 上传文件
     *
     * @param fileMap
     * @return
     */
    public static List<Map<String,String>> uploadFile(@RequestParam("file") Map<String,MultipartFile> fileMap) {
        List<Map<String,String>> files = new ArrayList<>();
        try {
            for (Map.Entry<String,MultipartFile> entry : fileMap.entrySet()) {
                String key = entry.getKey();
                MultipartFile file = entry.getValue();
                if (file != null) {
                    int type = fileType(file);
                    if (0 == type) {
                        return null;
                    }
                    // 获取文件名
                    String fileName = file.getOriginalFilename();
                    // 上传图片
                    if (fileName != null && fileName.length() > 0) {
                        // 新图片名称
                        String newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
                        newFileName = "hryfilefront/" + newFileName;
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        //压缩文件
                        Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.8f).toOutputStream(byteArrayOutputStream);
                        //上传流
                        InputStream ossStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        byteArrayOutputStream.close();
                        String img_server_type = PropertiesUtils.APP.getProperty("app.img.server.type");
                        switch (img_server_type) {
                            case "oss": // 阿里云oss
                                OssUtil.upload(ossStream, newFileName, true);
                                break;
                            case "aws": // 亚马逊aws
                                AWSUtil.uploadToS3(ossStream, newFileName);
                                break;
                            case "azure": // 微软azure
                                AzureUtil.upload(ossStream, newFileName);
                                break;
                            default: // 默认阿里云oss
                                OssUtil.upload(ossStream, newFileName, true);
                                break;
                        }
                        Map<String,String> map = new HashMap<String,String>();
                        map.put("path", newFileName);
                        map.put("type", String.valueOf(type));
                        files.add(map);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    /**
     * 校验文件类型
     *
     * @param fileMap
     * @return
     */
    public static Boolean checkFile(@RequestParam("file") Map<String,MultipartFile> fileMap) {
        for (Map.Entry<String,MultipartFile> entry : fileMap.entrySet()) {
            String key = entry.getKey();
            MultipartFile file = entry.getValue();
            if (file != null) {
                // 获取文件名
                String fileName = file.getOriginalFilename();
                int beginIndex = fileName.lastIndexOf(".");
                int length = fileName.length();
                String endName = fileName.substring(beginIndex, length);
                boolean contains = fileTypes.contains(endName);
                if (!contains) {
                    return false;
                }
                InputStream inputStream = null;
                try {
                    inputStream = file.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                String fileType = FileType.getFileType(inputStream);
                contains = fileTypes.contains(endName);
                if (!contains) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 校验文件类型是否为图片
     *
     * @param fileMap
     * @return
     */
    public static Boolean hasPic(@RequestParam("file") Map<String,MultipartFile> fileMap) {
        for (Map.Entry<String,MultipartFile> entry : fileMap.entrySet()) {
            String key = entry.getKey();
            MultipartFile file = entry.getValue();
            if (file != null) {
                // 获取文件名
                file.getContentType();
                String fileName = file.getOriginalFilename();
                int beginIndex = fileName.lastIndexOf(".");
                int length = fileName.length();
                String endName = fileName.substring(beginIndex, length);
                boolean contains = picTypes.contains(endName);
                if (contains) {
                    return contains;
                }
            }
        }
        return false;
    }

    /**
     * 文件类型 1:图片 2:视频 0:其它
     *
     * @param file
     * @return
     */
    public static int fileType(@RequestParam("file") MultipartFile file) {
        if (file != null) {
            // 获取文件名
            file.getContentType();
            String fileName = file.getOriginalFilename();
            int beginIndex = fileName.lastIndexOf(".");
            int length = fileName.length();
            String endName = fileName.substring(beginIndex, length);
            boolean contains = picTypes.contains(endName);
            if (contains) {
                return 1;
            }
            contains = vidoTypes.contains(endName);
            if (contains) {
                return 2;
            }
        }
        return 0;
    }

    /**
     * 校验文件类型是否为视频
     *
     * @param fileMap
     * @return
     */
    public static Boolean hasVideo(@RequestParam("file") Map<String,MultipartFile> fileMap) {
        for (Map.Entry<String,MultipartFile> entry : fileMap.entrySet()) {
            String key = entry.getKey();
            MultipartFile file = entry.getValue();
            if (file != null) {
                // 获取文件名
                file.getContentType();
                String fileName = file.getOriginalFilename();
                int beginIndex = fileName.lastIndexOf(".");
                int length = fileName.length();
                String endName = fileName.substring(beginIndex, length);
                boolean contains = vidoTypes.contains(endName);
                if (contains) {
                    return contains;
                }
            }
        }
        return false;
    }

}

