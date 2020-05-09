package cn.cloud.ego.manager.service.impl;

import cn.cloud.ego.base.utils.FilePathUtils;
import cn.cloud.ego.base.utils.FtpUtils;
import cn.cloud.ego.base.utils.IDUtils;
import cn.cloud.ego.base.vo.UploadResult;
import cn.cloud.ego.manager.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class UploadServiceImpl implements UploadService {

    @Value("${ftp.hostname}")
    private String hostname;
    @Value("${ftp.port}")
    private Integer port;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.basePath}")
    private String basePath;
    @Value("${ftp.picBasePath}")
    private String picBasePath;

    /**
     * 上传图片
     * @param uploadFile
     * @return
     */
    @Override
    public UploadResult upload(MultipartFile uploadFile) {
        // 1.创建UploadResult对象
        UploadResult result = new UploadResult();
        boolean upload = false; // 假设上传失败
        try {
            // 2.设置上传参数
            // 动态路径
            String filePath = FilePathUtils.getFilePath();
            // 图片类型
            String fileType = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
            // 图片名称
            String fileName = IDUtils.genImageName() + fileType;
            // 输入流
            InputStream in = uploadFile.getInputStream();
            // 3.上传图片
            upload = FtpUtils.upload(this.hostname, this.port, this.username, this.password, this.basePath, filePath, fileName, in);
            if(upload){
                result.setError(0);
                result.setUrl(this.picBasePath + filePath + fileName);
            }else{
                result.setError(1);
                result.setMessage("上传失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.setError(1);
            result.setMessage("上传失败!");
        }
        // 4.返回结果
        return result;
    }

}
