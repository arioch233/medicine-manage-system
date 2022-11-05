package com.moumouzhandui.mms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.common.enums.StatusCodeEnum;
import com.moumouzhandui.mms.entity.Category;
import com.moumouzhandui.mms.exception.ServiceException;
import com.moumouzhandui.mms.mapper.CategoryMapper;
import com.moumouzhandui.mms.pojo.dto.CategoryDTO;
import com.moumouzhandui.mms.pojo.vo.CategoryVO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.service.CategoryService;
import com.moumouzhandui.mms.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_category】的数据库操作Service实现
 * @createDate 2022-10-25 19:26:34
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDTO> listCategory(ConditionVO conditionVO) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        queryWrapper.eq("type", conditionVO.getCategoryType());
        if (conditionVO.getKeywords() != null) {
            queryWrapper.like("category_name", conditionVO.getKeywords());
        }
        Page<Category> page = page(new Page<>(conditionVO.getCurrent(), conditionVO.getSize()), queryWrapper);
        Page<CategoryDTO> pageDTO = new Page<>();
        BeanCopyUtils.copyIPage(page, pageDTO, CategoryDTO.class);
        return pageDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCategory(CategoryVO categoryVO) {
        // 判断分类是否已经存在
        Category existCategory = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, categoryVO.getCategoryName())
                .eq(Category::getType, categoryVO.getCategoryType()));
        if (Objects.nonNull(existCategory) && !existCategory.getId().equals(categoryVO.getId())) {
            throw new ServiceException(StatusCodeEnum.CATEGORY.getCode(), StatusCodeEnum.CATEGORY.getDescription());
        }
        // 若不存在则保存
        Category category = BeanUtil.copyProperties(categoryVO, Category.class);
        category.setType(categoryVO.getCategoryType());
        this.saveOrUpdate(category);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCategory(List<Integer> categoryIds) {
        this.removeBatchByIds(categoryIds);
    }

    @Override
    public List<CategoryDTO> listCategoryOptions(ConditionVO conditionVO) {
        return BeanCopyUtils.copyList(categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                        .eq(Category::getType, conditionVO.getCategoryType()))
                , CategoryDTO.class);
    }
}




