package cn.tedu.banye_admin_back_end.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyUserDetails extends User {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyUserDetails(
            String username, String password, boolean enabled,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled,
                true, true, true,
                authorities);
    }
}
