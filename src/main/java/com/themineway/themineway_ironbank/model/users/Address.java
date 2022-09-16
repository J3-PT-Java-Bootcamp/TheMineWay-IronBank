package com.themineway.themineway_ironbank.model.users;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    String countryCode;
    String street;
    Integer number;
}