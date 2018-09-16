package com.example.hotel_jpa.models;


import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    private Long id;

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty patronymic;
    private StringProperty passport;
    private StringProperty comment;


    public Client() {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.patronymic = new SimpleStringProperty();
        this.passport = new SimpleStringProperty();
        this.comment = new SimpleStringProperty();
    }

    public Client(String firstName, String lastName, String patronymic, String passport) {
        this();
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.patronymic.set(patronymic);
        this.passport.set(passport);
        this.comment.set("no comment");
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


    @Column(name = "first_name")
    public String getFirstName() {
        return firstName.get();
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName.get();
    }
    public StringProperty lastNameProperty(){return lastName;}

    @Column(name = "patronymic")
    public String getPatronymic() {
        return patronymic.get();
    }
    public StringProperty patronymicProperty(){return patronymic;}

    @Column(name = "passport")
    public String getPassport() {
        return passport.get();
    }

    public StringProperty passportProperty(){return passport;}

    @Column(name = "comment")
    public String getComment() {
        return comment.get();
    }
    public StringProperty commentProperty(){return comment;}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    public Long idProperty(){return id;}


    public void setFirstName(String firstName) {
        if (firstName != null)
            try {
                this.firstName.set(firstName);
            } catch (Exception e) {
//                e.printStackTrace();
            }
    }

    public void setLastName(String lastName) {
        try {
            this.lastName.set(lastName);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void setPatronymic(String patronymic) {
        try {
            this.patronymic.set(patronymic);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void setPassport(String passport) {
        try {
            this.passport.set(passport);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void setComment(String comment) {
        try {
            this.comment.set(comment);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void setId(Long id) {
        this.id = id;
    }


}
