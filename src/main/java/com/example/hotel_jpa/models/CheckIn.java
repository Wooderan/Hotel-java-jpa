package com.example.hotel_jpa.models;

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
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private int id;

    @Column(name = "id_client")
    private int idClient;
    @Column(name = "id_room")
    private int idRoom;
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

    public CheckIn(int idClient, int idRoom, LocalDate dateOfSettlement, LocalDate dateOfRelease) {
        this.idClient = idClient;
        this.idRoom = idRoom;
        this.dateOfSettlement = dateOfSettlement;
        this.dateOfRelease = dateOfRelease;
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", idRoom=" + idRoom +
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
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
