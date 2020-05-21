package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("first_name")
    public String firstName;
    @SerializedName("last_name")
    public String lastName;
    @SerializedName("address_line_1")
    public String addressLine1;
    @SerializedName("address_line_2")
    public String addressLine2;
    @SerializedName("city")
    public String city;
    @SerializedName("company_name")
    public String companyName;
    @SerializedName("state_province")
    public String stateProvince;
    @SerializedName("postal_code")
    public String postalCode;
    @SerializedName("country_code")
    public String countryCode;
    @SerializedName("phone")
    public Phone phone;
    @SerializedName("email")
    public String email;

    public Address firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Address lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Address addressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public Address addressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    public Address companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public Address stateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
        return this;
    }

    public Address postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public Address countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public Address phone(Phone phone) {
        this.phone = phone;
        return this;
    }

    public Address email(String email) {
        this.email = email;
        return this;
    }
    
}