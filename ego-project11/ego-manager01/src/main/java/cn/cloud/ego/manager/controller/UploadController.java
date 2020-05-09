package cn.cloud.ego.manager.controller;

import cn.cloud.ego.base.vo.UploadResult;
import cn.cloud.ego.manager.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pic")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传图片
     * @param uploadFile
     * @return
     */
    @PostMapping("/upload")
    public UploadResult upload(MultipartFile uploadFile){
        return uploadService.upload(uploadFile);
    }

}
