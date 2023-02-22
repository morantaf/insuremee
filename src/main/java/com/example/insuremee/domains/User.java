package com.example.insuremee.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Entity(name = "users")
@Table
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_sequence", allocationSize = 1, sequenceName = "user_sequence")
    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToOne
    private Insurance insurance;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name= "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> userRoles = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<IncidentRequest> incidentRequests;

    public Collection<Role> getUserRoles() {
        return Collections.unmodifiableCollection(this.userRoles);
    }

    public void grantRole(Role role) {
        this.userRoles.add(role);
        role.getUsers().add(this);
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRoles = new HashSet<>();
    }

    public User(String email, String password, String firstName, String lastName, Set<Role> userRoles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRoles = userRoles;
    }

    public User(String email, String password, String firstName, String lastName, Insurance insurance) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.insurance = insurance;
        this.userRoles = new HashSet<>();
    }

    public User(String email, String password, String firstName, String lastName, Insurance insurance, Set<Role> userRoles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.insurance = insurance;
        this.userRoles = userRoles;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).toList();
    }

    @Override
    public String getUsername() {
        return email;
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
