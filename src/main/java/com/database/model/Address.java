package com.database.model;

public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;


    public Address(String street, String houseNumber, String zipCode, String city ) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {

        return String.format("%s %s %s %s",street, houseNumber, zipCode, city);

    }
}
