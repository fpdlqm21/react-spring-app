package com.hansol.hansol.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
//UserDetails -> 사용자 인증 정보를 담아두는 인터페이스
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "tel", nullable = false)
    private String tel;

    @Column(name = "address", nullable = false)
    private String address;

//    Builder 생성자
    @Builder
    public User(String email, String password, String name, String tel, String address){
        this.email = email;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.address = address;
    }

    //    유저 권한 반환 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

//    UserDetails는 반드시 getUsername메소드 오버라이딩해야함
    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public String getPassword(){
        return password;
    }

//   계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

//    계정 감금 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

//    password 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    계정 사용 가능 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
