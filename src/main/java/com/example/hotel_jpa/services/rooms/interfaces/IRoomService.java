package com.example.hotel_jpa.services.rooms.interfaces;

import com.example.hotel_jpa.models.Room;

import java.util.List;

public interface IRoomService {
    List<Room> filter(Integer numberOfPeoples, Room.Comfortable comfortable, Double upperPrice);
}
