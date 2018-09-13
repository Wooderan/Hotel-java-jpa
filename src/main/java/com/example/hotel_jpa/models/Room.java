package com.example.hotel_jpa.models;

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

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private int id;

    @Column(name = "number")
    private int number;
    @Column(name = "number_peoples")
    private int numberOfPeoples;
    @Column(name = "comfortable")
    private Comfortable comfortable;
    @Column(name = "state")
    private State state = State.FREE;
    @Column(name = "price")
    private double price;

    public Room() {
    }

    public Room(int number, int numberOfPeoples, Comfortable comfortable, double price) {
        this.number = number;
        this.numberOfPeoples = numberOfPeoples;
        this.comfortable = comfortable;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", numberOfPeoples=" + numberOfPeoples +
                ", comfortable=" + comfortable +
                ", price=" + price +
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfPeoples() {
        return numberOfPeoples;
    }

    public void setNumberOfPeoples(int numberOfPeoples) {
        this.numberOfPeoples = numberOfPeoples;
    }

    public Comfortable getComfortable() {
        return comfortable;
    }

    public void setComfortable(Comfortable comfortable) {
        this.comfortable = comfortable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}