package hry.util;

import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;

public class Base64Util {
    private static Logger logger = LoggerFactory.getLogger(Base64Util.class);
    public static String base64ToFile(String img, String path) {
        BASE64Decoder decoder = new BASE64Decoder();
        String image = img.substring(img.indexOf(",") + 1);
        String imgPath = path + "\\" + StringUtil.getUUID()+".jpg";
        File file = new File(path);
        OutputStream out = null;
        try {
            if (!file.exists()){
                file.mkdirs();
            }
            byte[] bytes = decoder.decodeBuffer(image);
            out = new FileOutputStream(imgPath);
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgPath;
    }

    /**
     * base64 to 临时file
     * @param img
     * @param name
     * @return
     * @throws IOException
     */
    public static File base64ToTempFile(String img, String name) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        String image = img.substring(img.indexOf(",") + 1);
        File file = File.createTempFile(name, ".jpg");
        OutputStream out = null;
        try {
            byte[] bytes = decoder.decodeBuffer(image);
            out = new FileOutputStream(file);
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.flush();
                out.close();
                file.deleteOnExit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    /**
     * base64 to 临时file
     * @param img
     * @param name
     * @return
     * @throws IOException
     */
    public static MultipartFile base64ToMultipart(String img, String name) throws IOException {
        File file=base64ToTempFile(img,name);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        return multipartFile;
    }


    /**
     * inputStream to file
     * @param img
     * @param name
     * @return
     * @throws IOException
     */
    public static File inputStreamToFile(InputStream stream, String name, String suffix) throws IOException {
        File file = File.createTempFile(name, suffix);

        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        stream.close();
        file.deleteOnExit();
        return file;
    }

    public static File getAuthFile(String path){
        File file = new File(path);
        if (file.exists()){
            return file;
        }
        return null;
    }

    public static void delFile(String path){
        File dirFile = new File(path);
        File childFile = null;
        try {
            if (dirFile.exists() && dirFile.isDirectory()){
                String[] list = dirFile.list();
                for (String s : list) {
                    childFile = new File(path + "\\" + s);
                    childFile.delete();
                }
            }
            logger.info("删除活体认证图片成功");
        } catch (Exception e) {
            logger.error("删除活体认证图片失败");
        }
    }
}
