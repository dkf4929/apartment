package com.project.apartment.domain.member;

import com.project.apartment.domain.enums.Gender;
import com.project.apartment.domain.member.dto.MemberSaveDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Table(name = "member", uniqueConstraints = {
        @UniqueConstraint(name="uk_reg_no_and_name", columnNames = {"regNo", "name"}),
        @UniqueConstraint(name="uk_login_id", columnNames = "loginId")
})
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String password;

    @NotNull
    private String regNo;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private String address;

    @NotNull
    private String birthDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getName();
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
        return true;
    }

    public static Member createMember(String loginId,
                                      String password,
                                      String regNo,
                                      String name,
                                      Gender gender,
                                      String address,
                                      String birthDate,
                                      List<String> roles) {
        Member member = new Member();

        member.loginId = loginId;
        member.password = password;
        member.regNo = regNo;
        member.name = name;
        member.gender = gender;
        member.address = address;
        member.birthDate = birthDate;
        member.roles = roles;

        return member;
    }
}
