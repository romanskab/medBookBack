package com.oktenweb.medbookback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oktenweb.medbookback.configs.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("DOCTOR")
public class Doctor extends User{
    private String surname;
    private String fatherName;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "doctor")
    @JsonIgnore
    private List<Visit> visits = new ArrayList<Visit>();

    @Override
    public String toString() {
        return "Doctor{" +
                "surname='" + surname + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", speciality='" + speciality + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender=" + gender +
                ", role=" + role +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
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
}
