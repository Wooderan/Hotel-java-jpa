package com.example.hotel_jpa.dao.room.impls;

import com.example.hotel_jpa.dao.room.interfaces.IRoomDAO;
import com.example.hotel_jpa.exceptions.NoSuchRoomException;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.models.datasources.DataSourceRooms;

import java.util.List;

public class RoomDAOImpl implements IRoomDAO {
    DataSourceRooms dataSourceRooms = new DataSourceRooms();

    @Override
    public Room create(Room room) {
        return dataSourceRooms.add(room);
    }

    @Override
    public List<Room> getAll() {
        return dataSourceRooms.getRoomsList();
    }

    @Override
    public Room getRoom(int id) throws NoSuchRoomException {
        if (dataSourceRooms.getTopId() > id) throw new NoSuchRoomException("Wrong ID");
        for (Room room: this.getAll()){
            if (room.getId() == id)
                return room;
        }

        throw new NoSuchRoomException("This should be never throws");
    }

    @Override
    public Room getRoomByNumber(int number) throws NoSuchRoomException {
        for (Room room: this.getAll()){
            if (room.getNumber() == number)
                return room;
        }
        throw new NoSuchRoomException("There is no room with such number");
    }

    @Override
    public Room update(Room room) throws NoSuchRoomException {
        Room old = getRoom(room.getId());
        old =  room;
        return old;
    }

    @Override
    public boolean delete(Room room) {
        return dataSourceRooms.remove(room);
    }
}
