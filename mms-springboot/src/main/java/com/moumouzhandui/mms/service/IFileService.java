package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.entity.IFile;
import com.moumouzhandui.mms.pojo.dto.FileDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【sys_file】的数据库操作Service
 * @createDate 2022-10-21 00:02:59
 */
public interface IFileService extends IService<IFile> {

    Page<FileDTO> listImage(ConditionVO conditionVO);

    String saveImageFile(MultipartFile file, String imageUploadPath, String imageBaseURL) throws IOException;

    void deleteFile(List<Integer> imageIds, String imageUploadPath);
}
