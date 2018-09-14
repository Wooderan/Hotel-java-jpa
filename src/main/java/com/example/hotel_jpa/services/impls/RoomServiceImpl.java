package com.example.hotel_jpa.services.impls;

import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.repositories.IRoomRepository;
import com.example.hotel_jpa.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements IRoomService {

    @Autowired
    private IRoomRepository repository;

    @Override
    public Room addRoom(Room client) {
        return repository.saveAndFlush(client);
    }

    @Override
    public void delete(long id) {
        repository.deleteById((int) id);
    }

    @Override
    public Room getByNumber(Integer number) {
        return repository.findByNumber(number);
    }

    @Override
    public Room editRoom(Room client) {
        return repository.saveAndFlush(client);
    }

    @Override
    public List<Room> getAll() {
        return repository.findAll();
    }
}
