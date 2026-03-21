package com.ecommerce.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;


    @NotBlank
    @Size(min = 5, message = "Street name must be atleast 5 characters")
    private String street;


    @NotBlank
    @Size(min = 5, message = "Building name must be atleast 5 characters")
    private String buildingname;

    @NotBlank
    @Size(min = 5, message = "City name must be atleast 4 characters")
    private String  city;

    @NotBlank
    @Size(min = 5, message = "State name must be atleast 2 characters")
    private String state;

    @NotBlank
    @Size(min = 5, message = "Country name must be atleast 2 characters")
    private String country;

    @NotBlank
    @Size(min = 5, message = "Pincode must be atleast 6 characters")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users =  new ArrayList<User>();

    public Address(String street, String buildingname, String city, String state, String country, String pincode) {
        this.street = street;
        this.buildingname = buildingname;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;

    }
}
