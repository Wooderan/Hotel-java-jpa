package com.example.hotel_jpa.services.impls;

import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.repositories.IRoomRepository;
import com.example.hotel_jpa.services.IRoomService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements IRoomService {

    @Autowired
    private IRoomRepository repository;

    @Override
    public Room addRoom(Room room) {
        if (repository.findByNumber(room.getNumber()) == null)
            return repository.saveAndFlush(room);
        return room;
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Room getByNumber(Integer number) {
        return repository.findByNumber(number);
    }

    @Override
    public Room editRoom(Room room) {
        return repository.saveAndFlush(room);
    }

    @Override
    public List<Room> getAll() {
        return repository.findAll();
    }

    public ObservableList<Room> getAllObserved() {
        ObservableList<Room> retList = FXCollections.observableArrayList(repository.findAll());
        return retList;
    }
}
