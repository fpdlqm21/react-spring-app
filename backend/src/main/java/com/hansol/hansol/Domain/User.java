package com.hansol.hansol.Domain;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

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

    @Builder
    public User(String email, String password, String name, String tel, String address){
        this.email = email;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.address = address;
    }
}
