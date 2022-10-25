package cn.tedu.banye_admin_back_end.service.impl;

import cn.tedu.banye_admin_back_end.mapper.UserMapper;
import cn.tedu.banye_admin_back_end.pojo.dto.UserAddNewDTO;
import cn.tedu.banye_admin_back_end.pojo.dto.UserLoginDTO;
import cn.tedu.banye_admin_back_end.pojo.entity.User;
import cn.tedu.banye_admin_back_end.security.MyUserDetails;
import cn.tedu.banye_admin_back_end.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Value("${xiaobaiyun.jwt.secret-Key}")
    String secratKey;

    @Value("${xiaobaiyun.jwt.minute}")
    long minute;

    @Autowired
    UserMapper mapper;

    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public String login(UserLoginDTO userLoginDTO) {
       log.debug("开始处理登录业务员的service");
        Authentication authentication=new UsernamePasswordAuthenticationToken(
               userLoginDTO.getUsername(),userLoginDTO.getPassword()
        );
        Authentication authenticateResult=authenticationManager.authenticate(authentication);
        log.debug("执行认证成功，AuthenticationManager返回：{}", authenticateResult);
        Object principal = authenticateResult.getPrincipal();
        log.debug("认证结果中的Principal数据：{}", principal);
        MyUserDetails userDetails = (MyUserDetails) principal;

        log.debug("认证成功");
        log.debug("准备生成JWT数据");
        Map<String,Object> map=new HashMap<>();
        map.put("username",userDetails.getUsername());
        map.put("id",userDetails.getId());
        Date expirationDate=new Date(System.currentTimeMillis()+60*minute*1000);
        String jwt= Jwts.builder()
                .setHeaderParam("alg","HS256")
                .setHeaderParam("typ","JWT")
                .setClaims(map)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,secratKey)
                .compact();
        log.debug("返回JWT数据");
        return jwt;
    }

}
