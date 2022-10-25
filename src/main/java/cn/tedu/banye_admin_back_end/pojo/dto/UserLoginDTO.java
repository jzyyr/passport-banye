package cn.tedu.banye_admin_back_end.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用来接收登录时所传回来的数据
 */
@Data
public class UserLoginDTO implements Serializable {

    /**
     * 在刻划断传来的用户名
     */
    private String username;

    /**
     * 在客户算传来的密码
     */
    private String password;

}
