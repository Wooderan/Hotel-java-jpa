package com.example.hotel_jpa.services;

import com.example.hotel_jpa.models.CheckIn;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.models.Room;

import java.util.List;

public interface ICheckInService {
    CheckIn addCheckIn(CheckIn client);
    void delete(long id);
    CheckIn getByClient(Client client);
    CheckIn getByRoom(Room room);
    CheckIn editCheckIn(CheckIn client);
    List<CheckIn> getAll();
}
