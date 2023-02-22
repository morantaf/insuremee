package com.example.insuremee.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity(name = "authorities")
@Table
@NoArgsConstructor
public class Role {

    @Id
    @SequenceGenerator(name = "role_sequence", allocationSize = 1, sequenceName = "role_sequence")
    @GeneratedValue(generator = "role_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String authority;

    @ManyToMany(mappedBy = "userRoles", fetch = FetchType.LAZY)
    private Set<User> users;

    public Role(String authority) {
        this.authority = authority;
    }

    public Set<User> getUsers() {
        return users;
    }
}
