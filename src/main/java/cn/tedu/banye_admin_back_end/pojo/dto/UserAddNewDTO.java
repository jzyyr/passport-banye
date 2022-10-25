package cn.tedu.banye_admin_back_end.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserAddNewDTO implements Serializable {
    private static final long serialVersionUID = -55064349147027862L;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phonenumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 创建人的用户id
     */
    private Long createBy;
}
