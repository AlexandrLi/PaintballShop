package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class User extends BaseEntity {
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Address address;
    private String phoneNumber;
    private Money cash;

    public User() {
        this.role = Role.user;
        this.cash = Money.zero(CurrencyUnit.getInstance("KZT"));
    }

    public User(Integer id) {
        setId(id);
    }

    public Money getCash() {
        return cash;
    }

    public void setCash(Money cash) {
        this.cash = cash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void spendCash(Money cashAmount) {
        this.cash = this.cash.minus(cashAmount);
    }

    @Override
    public String toString() {
        return "User{" + super.toString() +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genderId=" + gender.getId() +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cash=" + cash.getAmount() +
                '}';
    }

    public enum Role {
        user, admin
    }
}