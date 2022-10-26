package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.service.IFileService;
import com.moumouzhandui.mms.utils.MyFileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件模块
 *
 * @author 冷血毒舌
 * @create 2022/10/21 0:19
 */
@Api(tags = "文件模块")
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Value("${files.upload.path.images}")
    private String imageUploadPath;

    @Value("${files.url.base}")
    private String imageBaseURL;

    @Autowired
    private IFileService iFileService;


    /**
     * 获取当前文件列表
     *
     * @param conditionVO
     * @return
     */
    @ApiOperation(value = "获取当前文件列表")
    @GetMapping("/image/all")
    public Result listImage(ConditionVO conditionVO) {
        return Result.success(iFileService.listImage(conditionVO));
    }

    /**
     * 上传图片
     *
     * @param file
     * @return Result String 返回图片地址
     */
    @ApiOperation(value = "上传图片")
    @PostMapping("/image/upload")
    public Result uploadFile(@RequestParam MultipartFile file) throws IOException {
        return Result.success(iFileService.saveImageFile(file, imageUploadPath, imageBaseURL));
    }

    /**
     * 图片下载
     *
     * @param fileName 图片唯一标识
     * @param response
     * @return
     */
    @ApiOperation(value = "图片下载")
    @GetMapping("/image/download/{fileName}")
    public void downloadImage(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        MyFileUtils.downloadFile(fileName, imageUploadPath, response);
    }

    /**
     * 图片删除
     *
     * @param imageIds
     * @return
     */
    @ApiOperation(value = "删除图片")
    @DeleteMapping("/image/delete")
    public Result deleteFile(@RequestBody List<Integer> imageIds) {
        iFileService.deleteFile(imageIds, imageUploadPath);
        return Result.success();
    }

}
