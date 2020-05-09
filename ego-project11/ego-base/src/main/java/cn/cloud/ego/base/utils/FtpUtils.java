package cn.cloud.ego.base.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

public class FtpUtils {

    public static boolean upload(String hostname, Integer port, String username, String password,
                                 String basePath, String filePath, String fileName, InputStream in){

        boolean result = false; // 假设上传失败
        // 1.获取远程连接
        FTPClient client = new FTPClient();
        try {
            client.connect(hostname,port);
            // 2.登录认证
            boolean login = client.login(username, password);
            if(login){
                // 3.设置上传参数
                // 使用二进制上传
                client.setFileType(FTP.BINARY_FILE_TYPE);
                // 使用被动模式上传
                client.enterLocalPassiveMode();
                // 上传路径
                // 判断目录是否存在，不存在则创建
                boolean directory = client.changeWorkingDirectory(basePath + filePath);
                if(!directory){
                    String templatePath = basePath;
                    String[] paths = filePath.split("/");
                    for (String path : paths) {
                        templatePath += "/"+path;
                        // 创建目录
                        client.makeDirectory(templatePath);
                    }
                    // 重新设置上传路径
                    client.changeWorkingDirectory(basePath + filePath);
                }
            }
            // 4.上传
            result = client.storeFile(fileName, in);
            // 5.关闭连接
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // 6.返回结果
        return result;
    }

}
