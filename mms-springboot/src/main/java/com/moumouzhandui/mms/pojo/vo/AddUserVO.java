package com.moumouzhandui.mms.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 手动添加用户账号
 *
 * @author 冷血毒舌
 * @create 2022/11/10 9:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "手动添加用户账号")
public class AddUserVO {


    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(name = "username", value = "用户名", required = true, dataType = "String")
    private String username;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(name = "nickname", value = "昵称", required = true, dataType = "String")
    private String nickname;

    /**
     * 角色id
     */
    @ApiModelProperty(name = "roleId", value = "角色id", required = true, dataType = "List<Integer>")
    private List<Integer> roleId;
}
