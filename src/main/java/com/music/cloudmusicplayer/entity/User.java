package com.music.cloudmusicplayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Author: Peony
 * @Date: 2020/11/10 14:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;

    @NotEmpty(message = "请输入名字")
    private String userName;

    @NotEmpty(message = "请输入邮箱")
    @Pattern(regexp = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?",
            message = "邮箱不合法")
    private String userEmail;

    @NotEmpty(message = "请输入密码")
    @Length(min = 8,max = 16,message = "密码长度在8-16之间")
    private String userPassword;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer isDeleted;
}
