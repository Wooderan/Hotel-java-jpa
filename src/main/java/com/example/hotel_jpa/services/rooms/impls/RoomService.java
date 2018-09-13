package com.example.hotel_jpa.services.rooms.impls;

import com.example.hotel_jpa.dao.room.impls.RoomDAOImpl;
import com.example.hotel_jpa.dao.room.interfaces.IRoomDAO;
import com.example.hotel_jpa.exceptions.NoSuchRoomException;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.services.rooms.interfaces.IRoomService;

import java.util.List;
import java.util.stream.Collectors;

public class RoomService implements IRoomDAO, IRoomService {
    RoomDAOImpl roomDAO = new RoomDAOImpl();

    @Override
    public Room create(Room room) {
        return roomDAO.create(room);
    }

    @Override
    public List<Room> getAll() {
        return roomDAO.getAll();
    }

    @Override
    public Room getRoom(int id) throws NoSuchRoomException {
        return roomDAO.getRoom(id);
    }

    @Override
    public Room getRoomByNumber(int number) throws NoSuchRoomException {
        return roomDAO.getRoomByNumber(number);
    }

    @Override
    public Room update(Room room) throws NoSuchRoomException {
        return roomDAO.update(room);
    }

    @Override
    public boolean delete(Room room) {
        return roomDAO.delete(room);
    }

    @Override
    public List<Room> filter(Integer numberOfPeoples, Room.Comfortable comfortable, Double upperPrice) {
        return getAll().stream().filter(room ->
                room.getState() == Room.State.FREE &&
                room.getNumberOfPeoples() >= numberOfPeoples &&
                room.getComfortable() == comfortable &&
                room.getPrice() <= upperPrice
        ).collect(Collectors.toList());
    }
}
