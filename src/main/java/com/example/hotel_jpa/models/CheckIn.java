package com.example.hotel_jpa.models;

import javafx.beans.property.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "checkins")
public class CheckIn {
    public enum State{
        ACTIVE,
        EXPIRED,
        CANCELED,
        BOOKED
    }

    private Long id;
    private ObjectProperty<Client> client = new SimpleObjectProperty<>(this,"client");
    private ObjectProperty<Room> room = new SimpleObjectProperty<>(this, "room");
    private ObjectProperty<LocalDate> dateOfSettlement = new SimpleObjectProperty<>(this, "dateOfSettlement");
    private ObjectProperty<LocalDate> dateOfRelease = new SimpleObjectProperty<>(this, "dateOfRelease");
    private StringProperty note = new SimpleStringProperty("empty");
    private ObjectProperty<State> state = new SimpleObjectProperty<>(State.ACTIVE);

    public CheckIn() {
    }

    public CheckIn(Client client, Room room, LocalDate dateOfSettlement, LocalDate dateOfRelease) {
        this.client.set(client);
        this.room.set(room);
        this.dateOfSettlement.set(dateOfSettlement);
        this.dateOfRelease.set(dateOfRelease);
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "id=" + id +
                ", Client=" + client.getValue() +
                ", Room=" + room.getValue() +
                ", dateOfSettlement=" + dateOfSettlement.getValue() +
                ", dateOfRelease=" + dateOfRelease.getValue() +
                ", note='" + note.getValue() + '\'' +
                ", state=" + state.getValue() +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "client")
    public Client getClient() {
        return client.getValue();
    }
    public void setClient(Client client) {
        this.client.set(client);
    }
    public ObjectProperty<Client> clientProperty(){ return client;}

    @ManyToOne
    @JoinColumn(name = "room")
    public Room getRoom() {
        return room.getValue();
    }
    public void setRoom(Room room) {
        this.room.set(room);
    }
    public ObjectProperty<Room> roomProperty(){ return this.room;}

    @Column(name = "date_settlement")
    public LocalDate getDateOfSettlement() {
        return dateOfSettlement.getValue();
    }
    public void setDateOfSettlement(LocalDate dateOfSettlement) {
        this.dateOfSettlement.set(dateOfSettlement);
    }
    public ObjectProperty<LocalDate> dateOfSettlementProperty(){return this.dateOfSettlement;}

    @Column(name = "date_release")
    public LocalDate getDateOfRelease() { return dateOfRelease.getValue();}
    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease.set(dateOfRelease);
    }
    public ObjectProperty<LocalDate> dateOfReleaseProperty(){ return this.dateOfRelease; }

    @Column(name = "note")
    public String getNote() {
        return note.getValue();
    }
    public void setNote(String note) {
        this.note.set(note);
    }
    public StringProperty noteProperty(){return this.note;}

    @Column(name = "state")
    public State getState() { return state.getValue(); }
    public void setState(State state) {this.state.set(state);}
    public ObjectProperty<State> stateProperty(){return this.state;}

    public StringProperty clientsName(){
        return new SimpleStringProperty(getClient().getFirstName() + ' ' + getClient().getLastName() +
                ' ' + getClient().getPassport());
    }

    public StringProperty roomsName(){
        return new SimpleStringProperty(getRoom().getName() +'(' + getRoom().getNumber() +')');
    }
}
