package com.cursosdedesarrollo.apirestjpa.entities.relations.onetooneuni;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "UserUni")
@Table(name = "USERS_UNI")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 65)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 65)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 128)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;
}
