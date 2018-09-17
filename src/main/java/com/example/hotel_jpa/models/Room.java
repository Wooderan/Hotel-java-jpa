package com.example.hotel_jpa.models;

import javafx.beans.property.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    public enum Comfortable{
        SUITE,
        HALF_SUITE,
        REGULAR
    }

    public enum State{
        FREE,
        BUSY
    }

    private Long id;

    private StringProperty name = new SimpleStringProperty(this, "name", "no name");

    private IntegerProperty number = new SimpleIntegerProperty(this, "number", 0);

    private IntegerProperty numberOfPeoples = new SimpleIntegerProperty(this, "numberOfPeoples", 0);

    private ObjectProperty<Comfortable> comfortable = new SimpleObjectProperty<>(this, "comfortable", Comfortable.REGULAR);

    private ObjectProperty<State> state = new SimpleObjectProperty<>(this, "state", State.FREE);

    private DoubleProperty price = new SimpleDoubleProperty(this, "price", 0);

    public Room() {
    }

    public Room(String name, int number, int numberOfPeoples, Comfortable comfortable, double price) {
        this.name.set(name);
        this.number.set(number);
        this.numberOfPeoples.set( numberOfPeoples);;
        this.comfortable.set(comfortable);
        this.price.set(price);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                "name=" + name +
                ", number=" + number +
                ", numberOfPeoples=" + numberOfPeoples +
                ", comfortable=" + comfortable +
                ", price=" + price +
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

    @Column(name = "number")
    public int getNumber() {
        return number.getValue();
    }
    public void setNumber(int number) {
        this.number.set(number);
    }
    public IntegerProperty numberProperty(){
        return this.number;
    }

    @Column(name = "name")
    public String getName() {
        return name.getValue();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty(){
        return this.name;
    }

    @Column(name = "number_peoples")
    public int getNumberOfPeoples() {
        return numberOfPeoples.getValue();
    }
    public void setNumberOfPeoples(int numberOfPeoples) {
        this.numberOfPeoples.set(numberOfPeoples);
    }
    public IntegerProperty numberOfPeoplesProperty(){
        return this.numberOfPeoples;
    }

    @Column(name = "comfortable")
    public Comfortable getComfortable() {
        return comfortable.getValue();
    }
    public void setComfortable(Comfortable comfortable) {
        this.comfortable.set(comfortable);
    }
    public ObjectProperty<Comfortable> comfortableProperty(){
        return this.comfortable;
    }

    @Column(name = "state")
    public State getState() {
        return state.getValue();
    }
    public void setState(State state) {
        this.state.set(state);
    }
    public ObjectProperty<State> stateProperty(){
        return this.state;
    }

    @Column(name = "price")
    public double getPrice() {
        return price.getValue();
    }
    public void setPrice(double price) {
        this.price.set(price);
    }
    public DoubleProperty priceProperty(){
        return this.price;
    }
}
