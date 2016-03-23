package com.epam.alexandrli.paintballshop.entity;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity {
    private String email;
    // TODO: 07.02.2016 find type for password variable
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
    private List<Address> addressList = new ArrayList<>();
    private String phoneNumber;

    public User() {
    }

    public User(Integer id, String email, String password, String firstName, String lastName, Gender gender, List<Address> addressList, String phoneNumber) {
        super(id);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.addressList = addressList;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
