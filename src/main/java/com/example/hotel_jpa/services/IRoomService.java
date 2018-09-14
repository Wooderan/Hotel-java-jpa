package com.example.hotel_jpa.services;

import com.example.hotel_jpa.models.Room;

import java.util.List;

public interface IRoomService {
    Room addRoom(Room client);
    void delete(long id);
    Room getByNumber(Integer number);
    Room editRoom(Room client);
    List<Room> getAll();
}
