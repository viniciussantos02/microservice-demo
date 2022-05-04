package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Adress {
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String street;
    private String number;
    @Column(name = "additional_inform")
    private String additionalInform;
    private String state;
    private String district;
}
