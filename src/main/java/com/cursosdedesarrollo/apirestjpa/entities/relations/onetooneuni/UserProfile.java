package com.cursosdedesarrollo.apirestjpa.entities.relations.onetooneuni;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity(name="UserProfileUni")
@Table(name = "user_profile_uni")
@Data
public class UserProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    @Size(max = 15)
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    @Size(max = 100)
    private String address1;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String state;

    @Size(max = 100)
    private String country;

    @Column(name = "zip_code")
    @Size(max = 32)
    private String zipCode;
}
