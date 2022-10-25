package cn.tedu.banye_admin_back_end.mapper;

import cn.tedu.banye_admin_back_end.pojo.vo.UserLoginVO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    /**
     * 根据用户名查询用户相关信息返回信息用来验证登录
     * @param username 登陆时前端传过来的用户名
     * @return 登陆时所需要校验的信息
     */
    UserLoginVO loginByUsername(String username);
}
