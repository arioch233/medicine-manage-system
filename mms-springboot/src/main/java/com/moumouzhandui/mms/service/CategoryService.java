package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.entity.Category;
import com.moumouzhandui.mms.pojo.dto.CategoryDTO;
import com.moumouzhandui.mms.pojo.vo.CategoryVO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;

import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_category】的数据库操作Service
 * @createDate 2022-10-25 19:26:34
 */
public interface CategoryService extends IService<Category> {

    Page<CategoryDTO> listCategory(ConditionVO conditionVO);

    void updateCategory(CategoryVO categoryVO);

    void deleteCategory(List<Integer> categoryIds);
}
