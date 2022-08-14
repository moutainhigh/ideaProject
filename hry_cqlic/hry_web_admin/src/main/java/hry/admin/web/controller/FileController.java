package hry.admin.web.controller;

import hry.bean.JsonResult;
import hry.util.*;
import hry.util.file.FileUtil;
import hry.util.properties.PropertiesUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

@Controller
@RequestMapping("/file")
public class FileController {



    /**
     * 异步上传图片
     *
     * @param multipartRequest
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public JsonResult upload(DefaultMultipartHttpServletRequest multipartRequest)  throws IOException {
        JsonResult json = new JsonResult();
        json.setSuccess(true);
        if (multipartRequest != null) {
            //得到文件名称迭代器
            Iterator<String> iterator = multipartRequest.getFileNames();
            //遍历
            while (iterator.hasNext()) {
                //获得文件
                MultipartFile file = multipartRequest.getFile(iterator.next());
                String names=file.getOriginalFilename();
                if (names != null && (names.endsWith("jpg") || names.endsWith("jpge") || names.endsWith("png") || names.endsWith("gif") || names.endsWith("bmp") || names.endsWith("JPG") || names.endsWith("PNG") || names.endsWith("JPGE") || names.endsWith("GIF") || names.endsWith("BMP"))) {
                } else {
                    JsonResult jsonResult = new JsonResult();
                    jsonResult.setMsg("图片格式不正确");
                    jsonResult.setSuccess(false);
                    return jsonResult;
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                //压缩文件
                Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.8f).toOutputStream(byteArrayOutputStream);

                //验证流
                InputStream validate = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                //上传流
                InputStream ossStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.close();


                String fileType = FileType.getFileType(validate);
                if (fileType != null && (fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("png") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("bmp"))) {
                } else {
                    JsonResult jsonResult = new JsonResult();
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg("图片格式不正确");
                    return jsonResult;
                }
                //判断文件是否为空
                if (!file.isEmpty()) {

                    String fileName =  UUIDUtil.getUUID()+"."+file.getOriginalFilename().split("\\.")[1];
                    String fileSavePath[] = FileUtil.createFileSavePath(fileName);

                    // 保存
                    try {
                        findUploadUtil(ossStream, fileSavePath);
                        //返回文件相对路径
                        json.setObj(fileSavePath[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        json.setSuccess(true);
        return json;
    }

    /**
     * 异步上传文件
     *
     * @param multipartRequest
     * @return
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public JsonResult fileUpload(DefaultMultipartHttpServletRequest multipartRequest)  throws IOException {
        JsonResult json = new JsonResult();
        json.setSuccess(true);
        if (multipartRequest != null) {
            //得到文件名称迭代器
            Iterator<String> iterator = multipartRequest.getFileNames();
            //遍历
            while (iterator.hasNext()) {
                //获得文件
                MultipartFile file = multipartRequest.getFile(iterator.next());
                //上传流

                InputStream ossStream = file.getInputStream();

                //判断文件是否为空
                if (!file.isEmpty()) {
                    String[] split = file.getOriginalFilename().split("\\.");
                    int fileNameHasDecimalPoints=split.length-1;
                    String fileName =  UUIDUtil.getUUID()+"."+file.getOriginalFilename().split("\\.")[fileNameHasDecimalPoints];
                    String fileSavePath[] = FileUtil.createFileSavePath(fileName);

                    // 保存
                    try {
                        findUploadUtil(ossStream, fileSavePath);
                        //返回文件相对路径
                        json.setObj(fileSavePath[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        ossStream.close();
                    }

                }
            }
        }

        json.setSuccess(true);
        return json;
    }



    /**
     * 上传二维码
     *
     * @param
     * @return
     */
    @RequestMapping("/fileQrUpload")
    @ResponseBody
    public JsonResult fileQrUpload(HttpServletRequest request,String fileUrl)  throws IOException {
        JsonResult json = new JsonResult();

                String url="http://bshare.optimix.asia/barCode?site=weixin&url="+fileUrl;
                    //上传流
                    URL  strUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) strUrl.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("GET");
                    connection.setUseCaches(false);
                    connection.connect();
                    connection.setConnectTimeout(100000);
                    connection.setReadTimeout(100000);
                     InputStream ossStream =connection.getInputStream();
                    String fileName = UUIDUtil.getUUID() + ".jpg";
                    String fileSavePath[] = FileUtil.createFileSavePath(fileName);

                    // 保存
                    try {
                        findUploadUtil(ossStream, fileSavePath);
                        //返回文件相对路径
                        json.setObj(fileSavePath[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                            connection.disconnect();
                    }
        json.setSuccess(true);
        return json;
    }

    /**
     * 判断使用哪种上传服务器
     * @param ossStream
     * @param fileSavePath
     */
    private void findUploadUtil(InputStream ossStream, String[] fileSavePath) {
        String img_server_type = PropertiesUtils.APP.getProperty("app.img.server.type");
        switch (img_server_type) {
            case "oss": // 阿里云oss
                OssUtil.upload(ossStream, fileSavePath[0],false);
                break;
            case "aws": // 亚马逊aws
                AWSUtil.uploadToS3(ossStream, fileSavePath[0]);
                break;
            case "azure": // 微软azure
                AzureUtil.upload(ossStream, fileSavePath[0]);
                break;
            default: // 默认阿里云oss
                OssUtil.upload(ossStream, fileSavePath[0],false);
                break;
        }
    }
}
