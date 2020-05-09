package cn.cloud.ego.manager.service;

import cn.cloud.ego.base.vo.UploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    /**
     * 上传图片
     * @param uploadFile
     * @return
     */
    UploadResult upload(MultipartFile uploadFile);

}
