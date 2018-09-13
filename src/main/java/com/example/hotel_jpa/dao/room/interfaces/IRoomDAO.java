package com.example.hotel_jpa.dao.room.interfaces;

import com.example.hotel_jpa.exceptions.NoSuchRoomException;
import com.example.hotel_jpa.models.Room;

import java.util.List;

public interface IRoomDAO {
    Room create(Room room);

    List<Room> getAll();
    Room getRoom(int id) throws NoSuchRoomException;
    Room getRoomByNumber(int number) throws NoSuchRoomException;

    Room update(Room room) throws NoSuchRoomException;

    boolean delete(Room room);
}
