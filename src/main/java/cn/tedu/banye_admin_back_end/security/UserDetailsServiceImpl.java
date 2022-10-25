package cn.tedu.banye_admin_back_end.security;

import cn.tedu.banye_admin_back_end.mapper.UserMapper;
import cn.tedu.banye_admin_back_end.pojo.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Spring Security调用了loadUserByUsername()方法，参数：{}", username);
        UserLoginVO user=mapper.loginByUsername(username);
        List<GrantedAuthority> authorities=new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("这是一个山寨的权限标识");
        authorities.add(authority);

        if (user==null){
            log.debug("此用户名不存在即将抛出异常{}",username);
            String message="登陆失败用户名不存在";
            throw new BadCredentialsException(message);
        }

        if (user.getUserName().equals(username)){

            MyUserDetails userDetails=new MyUserDetails(
                    user.getUserName(),user.getPassword(),user.getStatus()==0,authorities
            );
            userDetails.setId(user.getId());

            log.debug("即将向Spring Security返回UserDetails对象：{}", userDetails);
            return userDetails;
        }
        return null;
    }
}
