package com.study.demoProject.config.auth;


import com.study.demoProject.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//  스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//  스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.

@RequiredArgsConstructor
@Getter
// UserDatails 객체 상속시 시큐리티의 고유한 세션저장소에 저장을 할 수 있게 된다.
// principal (접근 주체) = 세션처럼 사용 = Spring Security Context 에 보관됨
public class PrincipalDetail implements UserDetails {

    private User user; // 콤포지션 : 객체를 품고 있는 것

    //생성자
    public PrincipalDetail(User user) {
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //사용자 pk
    public Long getCode() {
        return user.getCode();
    }

    //사용자 아이디
    //UserDetails 인터페이스를 사용하기위해 id를 Username으로 설정 (Security)
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //사용자 패스워드
    @Override
    public String getPassword() {
        return user.getUser_pw();
    }

    // 사용자 이름
    public String getName() {
        return user.getName();
    }

    // 사용자 휴대폰 번호
    public String getPhone() {
        return user.getUser_phone();
    }

    //사용자 닉네임
//    public String getNickname() {
//        return user.getUser_nickname();
//    }

    //사용자 생년월일
    public String getDate() {
        return user.getUser_birth();
    }

    //사용자 이메일
    public String getEmail() {
        return user.getUser_email();
    }

    //사용자 주소
    public String getAdress() {
        return user.getUser_address();
    }






    //계정이 만료되었는지 (true: 만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있는지 (true: 잠겨있지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드가 만료되지 않았는지 (true: 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화되어 있는지 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> user.getRoleKey());

        return collection;
    }


}
