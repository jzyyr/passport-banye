package cn.tedu.banye_admin_back_end.service;


import cn.tedu.banye_admin_back_end.pojo.dto.UserAddNewDTO;
import cn.tedu.banye_admin_back_end.pojo.dto.UserLoginDTO;

public interface IUserService {

    String login(UserLoginDTO userLoginDTO);

}
