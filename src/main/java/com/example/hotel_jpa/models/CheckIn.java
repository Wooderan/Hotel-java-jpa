package com.example.hotel_jpa.models;

import javafx.beans.property.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


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
    private Set<Client> clients = new HashSet<>();
    private ObjectProperty<Room> room = new SimpleObjectProperty<>(this, "room");
    private ObjectProperty<LocalDate> dateOfSettlement = new SimpleObjectProperty<>(this, "dateOfSettlement");
    private ObjectProperty<LocalDate> dateOfRelease = new SimpleObjectProperty<>(this, "dateOfRelease");
    private StringProperty note = new SimpleStringProperty("empty");
    private ObjectProperty<State> state = new SimpleObjectProperty<>(State.ACTIVE);

    public CheckIn() {
    }

    public CheckIn(Room room, LocalDate dateOfSettlement, LocalDate dateOfRelease) {
        this.room.set(room);
        this.dateOfSettlement.set(dateOfSettlement);
        this.dateOfRelease.set(dateOfRelease);
    }

    public CheckIn(Set<Client> client, Room room, LocalDate dateOfSettlement, LocalDate dateOfRelease) {
        this.clients = client;
        this.room.set(room);
        this.dateOfSettlement.set(dateOfSettlement);
        this.dateOfRelease.set(dateOfRelease);
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "id=" + id +
                ", Client=" + clients +
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
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @Column(name = "date_settlement")
    public LocalDate getDateOfSettlement() {return dateOfSettlement.getValue();}
    public void setDateOfSettlement(LocalDate dateOfSettlement) {this.dateOfSettlement.set(dateOfSettlement);}
    public ObjectProperty<LocalDate> dateOfSettlementProperty(){return this.dateOfSettlement;}

    @Column(name = "date_release")
    public LocalDate getDateOfRelease() { return dateOfRelease.getValue();}
    public void setDateOfRelease(LocalDate dateOfRelease) {this.dateOfRelease.set(dateOfRelease);}
    public ObjectProperty<LocalDate> dateOfReleaseProperty(){ return this.dateOfRelease; }

    @Column(name = "note")
    public String getNote() {return note.getValue();}
    public void setNote(String note) {this.note.set(note);}
    public StringProperty noteProperty(){return this.note;}

    @Column(name = "state")
    public State getState() { return state.getValue(); }
    public void setState(State state) {this.state.set(state);}
    public ObjectProperty<State> stateProperty(){return this.state;}

    @ManyToOne
    @JoinColumn(name = "room")
    public Room getRoom() {return room.getValue();}
    public void setRoom(Room room) {this.room.set(room);}


    //-------------------clients--------------------------------
    @ManyToMany(/*cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            },*/
            fetch = FetchType.EAGER
    )
    @JoinTable(name = "checkin_client",
            joinColumns = @JoinColumn(name = "id_checkin"),
            inverseJoinColumns = @JoinColumn(name = "id_client")
    )
    public Set<Client> getClient() {return clients;}
    public void setClient(Set<Client>  client) {this.clients = client;}
    public void addClient(Client client){
        clients.add(client);
        client.getCheckins().add(this);
    }
    //----------------------------------------------------------
}
