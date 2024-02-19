package com.security.proj.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @Column(name = "user_id", columnDefinition = "INT", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(255)")
    private String username;
    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    private String password;
    @Column(name = "fullname", columnDefinition = "VARCHAR(255)")
    private String fullname;
    @Transient
    private Set<Role> roles;

    public User(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}