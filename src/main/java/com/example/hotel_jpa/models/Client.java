package com.example.hotel_jpa.models;


import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {

    private Long id;

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty patronymic;
    private StringProperty passport;
    private StringProperty comment;

    private Set<CheckIn> checkins = new HashSet<>();


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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}
    public Long idProperty(){return id;}

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {this.firstName.set(firstName);}
    public StringProperty firstNameProperty() {
        return firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) {this.lastName.set(lastName);}
    public StringProperty lastNameProperty(){return lastName;}

    @Column(name = "patronymic")
    public String getPatronymic() {
        return patronymic.get();
    }
    public void setPatronymic(String patronymic) {this.patronymic.set(patronymic);}
    public StringProperty patronymicProperty(){return patronymic;}

    @Column(name = "passport")
    public String getPassport() {
        return passport.get();
    }
    public void setPassport(String passport) {this.passport.set(passport);}
    public StringProperty passportProperty(){return passport;}

    @Column(name = "comment")
    public String getComment() {
        return comment.get();
    }
    public void setComment(String comment) {this.comment.set(comment);}
    public StringProperty commentProperty(){return comment;}

    @ManyToMany(mappedBy = "client", fetch = FetchType.EAGER)
    public Set<CheckIn> getCheckins() {return checkins;}
    public void setCheckins(Set<CheckIn> checkins) {this.checkins = checkins;}
}
