package com.themineway.themineway_ironbank.model.users;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    public String countryCode;
    public String street;
    public Integer number;
}