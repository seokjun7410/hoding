package com.cos.security1.auth;

//시큐리티가 /login을 낚아채서 로그인을 진행히시킨다.
//로그인 진행이 완료가 되면 (시큐리티가 가지고있는)session을 만들어줍니다. (security contextHolder 키값으로 세션정보를 저장시킨다.)
//security contextHolder에 들어갈수 있는 오브젝트는 Authentication 타입 객체이다
// Authentication안에는 User정보가 있어야됨
//User 오브젝트타입 = > UserDetails 타입 객체임

import com.cos.security1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//Security Session => Authentication => UserDetails
public class PrincipalDetail implements UserDetails {

    private User user;

    public PrincipalDetail(final User user) {
        this.user = user;
    }


    //해당 유저의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> role = new ArrayList<>();
        role.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                System.out.println("user = " + user.getRole());
                return user.getRole();
            }
        });
        return role;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //우리 사이트에서 1년동안 회원이 로그인을 안하면 휴먼계정으로 변경되는 정책이 있다면
        //현재시간 - user.getLoginDate() > 1년초과 return false;
        return true;
    }
}
