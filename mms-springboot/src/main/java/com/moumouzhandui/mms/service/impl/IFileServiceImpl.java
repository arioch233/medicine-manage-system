package com.moumouzhandui.mms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.common.enums.StatusCodeEnum;
import com.moumouzhandui.mms.entity.IFile;
import com.moumouzhandui.mms.exception.ServiceException;
import com.moumouzhandui.mms.mapper.IFileMapper;
import com.moumouzhandui.mms.pojo.dto.FileDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.service.IFileService;
import com.moumouzhandui.mms.utils.BeanCopyUtils;
import com.moumouzhandui.mms.utils.MyFileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 冷血毒舌
 * @description 针对表【sys_file】的数据库操作Service实现
 * @createDate 2022-10-21 00:02:59
 */
@Service
public class IFileServiceImpl extends ServiceImpl<IFileMapper, IFile>
        implements IFileService {

    @Resource
    private IFileMapper fileMapper;

    @Override
    public Page<FileDTO> listImage(ConditionVO conditionVO) {
        QueryWrapper<IFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        if (conditionVO.getKeywords() != null) {
            queryWrapper.like("filename", conditionVO.getKeywords());
        }
        Page<IFile> page = page(new Page<>(conditionVO.getCurrent(), conditionVO.getSize()), queryWrapper);
        Page<FileDTO> pageDTO = new Page<>();
        BeanCopyUtils.copyIPage(page, pageDTO, FileDTO.class);
        return pageDTO;
    }

    @Override
    public String saveImageFile(MultipartFile file, String imageUploadPath, String imageBaseURL) throws IOException {
        Map<String, Object> baseInfo = MyFileUtils.generateFileBaseInfo(file);
        IFile imageFile = new IFile();
        // 文件md5
        imageFile.setMd5((String) baseInfo.get("md5"));
        // 文件类型
        imageFile.setType((String) baseInfo.get("type"));
        // 文件大小
        imageFile.setSize((Long) baseInfo.get("size"));
        // 文件名
        String filename = MyFileUtils.generateFileName((String) baseInfo.get("uuid"), (String) baseInfo.get("type"));
        imageFile.setFilename(filename);
        // 文件链接启用
        imageFile.setEnable(1);
        // 设置文件url
        String url = "";
        // 判断文件md5是否已经存在，若存在则不重复上传
        IFile dbFiles = fileMapper.selectOne(new LambdaQueryWrapper<IFile>().eq(IFile::getMd5, baseInfo.get("md5")));
        if (Objects.nonNull(dbFiles)) {
            url = dbFiles.getUrl();
        } else {
            // 上传文件
            File uploadFile = new File(imageUploadPath + filename);
            // 创建目录
            MyFileUtils.createPath(uploadFile);
            file.transferTo(uploadFile);
            // 拼接url
            url = imageBaseURL + filename;
        }
        imageFile.setUrl(url);
        if (!save(imageFile)) {
            throw new ServiceException(StatusCodeEnum.FILE_SAVE.getCode(), StatusCodeEnum.FILE_SAVE.getDescription());
        }
        return imageFile.getUrl();
    }

    @Override
    public void deleteFile(List<Integer> imageIds, String imageUploadPath) {
        List<IFile> list = listByIds(imageIds);
        List<String> filenames = new ArrayList<>();
        for (IFile file : list) {
            filenames.add(file.getFilename());
        }
        MyFileUtils.deleteFile(filenames, imageUploadPath);
        this.removeBatchByIds(imageIds);
    }
}




