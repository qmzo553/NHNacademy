package com.nhnacademy;

public class Person {

    String name;
    Address address;

    public Person(String name) {
        this.name = name;
        this.address = new Address(13487, "Seongnam");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }
}
