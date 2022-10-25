package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.ResourceVO;
import com.moumouzhandui.mms.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 资源模块
 *
 * @author 冷血毒舌
 * @create 2022/10/21 0:17
 */
@Api(tags = "资源模块")
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 查询所有资源列表
     *
     * @param conditionVO 条件
     * @return Result List<ResourceDTO>
     */
    @ApiOperation(value = "获取资源列表")
    @GetMapping("/all")
    public Result listResource(ConditionVO conditionVO) {
        return Result.success(resourceService.listResource(conditionVO));
    }


    /**
     * 更新或保存修改的资源
     *
     * @param resourceVO
     * @return Result
     */
    @ApiOperation(value = "更新保存资源")
    @PostMapping("/update")
    public Result updateResource(@RequestBody ResourceVO resourceVO) {
        resourceService.updateResource(resourceVO);
        return Result.success();
    }

    /**
     * 删除资源
     *
     * @param resourceId
     * @return Result
     */
    @ApiOperation(value = "删除资源")
    @DeleteMapping("/delete/{resourceId}")
    public Result deleteResource(@PathVariable("resourceId") Integer resourceId) {
        resourceService.deleteResource(resourceId);
        return Result.success();
    }

    /**
     * 查看角色资源选项
     *
     * @return {@link Result} 角色资源选项
     */
    @ApiOperation(value = "查看角色资源选项")
    @GetMapping("/role")
    public Result listResourceOption() {
        return Result.success(resourceService.listResourceOption());
    }
}
