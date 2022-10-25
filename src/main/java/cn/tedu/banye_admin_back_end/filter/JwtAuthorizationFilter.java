package cn.tedu.banye_admin_back_end.filter;

import cn.tedu.banye_admin_back_end.security.LoginPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${xiaobaiyun.jwt.secret-Key}")
    String secratKey;

    private static final int JWT_MIN_LENGTH=130;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("业务开始进行");
        String jwt=request.getHeader("Authorization");
        if (!StringUtils.hasText(jwt) || jwt.length() < JWT_MIN_LENGTH){
            log.debug("没有获取到有效的JWT");
            filterChain.doFilter(request,response);
            return;
        }

        log.debug("尝试解析jwt数据......");
        Claims claims= Jwts.parser().setSigningKey(secratKey).parseClaimsJws(jwt).getBody();
        Long id=claims.get("id",Long.class);
        String username=claims.get("username",String.class);
        log.debug("从JWT中解析得到数据：id={}", id);
        log.debug("从JWT中解析得到数据：username={}", username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("这是一个山寨的权限标识");
        authorities.add(authority);

        LoginPrincipal loginPrincipal = new LoginPrincipal();
        loginPrincipal.setId(id);
        loginPrincipal.setUsername(username);

        Authentication authentication=new UsernamePasswordAuthenticationToken(
                loginPrincipal,null,authorities
        );

        SecurityContext securityContext= SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        filterChain.doFilter(request,response);
    }
}
