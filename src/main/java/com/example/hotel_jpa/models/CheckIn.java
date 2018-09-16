package com.example.hotel_jpa.models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "checkins")
public class CheckIn {
    public enum State{
        ACTIVE,
        EXPIRED,
        CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @Column(name = "date_settlement")
    private LocalDate dateOfSettlement;

    @Column(name = "date_release")
    private LocalDate dateOfRelease;

    @Column(name = "note")
    private String note;

    @Column(name = "state")
    private State state = State.ACTIVE;



    public CheckIn() {
    }

    public CheckIn(Client client, Room room, LocalDate dateOfSettlement, LocalDate dateOfRelease) {
        this.client = client;
        this.room = room;
        this.dateOfSettlement = dateOfSettlement;
        this.dateOfRelease = dateOfRelease;
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "id=" + id +
                ", Client=" + client +
                ", Room=" + room +
                ", dateOfSettlement=" + dateOfSettlement +
                ", dateOfRelease=" + dateOfRelease +
                ", note='" + note + '\'' +
                ", state=" + state +
                '}';
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getDateOfSettlement() {
        return dateOfSettlement;
    }

    public void setDateOfSettlement(LocalDate dateOfSettlement) {
        this.dateOfSettlement = dateOfSettlement;
    }

    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
