package com.example.hotel_jpa.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private int id;

    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public String lastName;
    @Column(name = "patronumic")
    public String patronymic;
    @Column(name = "passport")
    public String passport;
    @Column(name = "comment")
    public String comment = "No comment";

    public Client() {
    }

    public Client(String firstName, String lastName, String patronymic, String passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", passport='" + passport + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPassport() {
        return passport;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
