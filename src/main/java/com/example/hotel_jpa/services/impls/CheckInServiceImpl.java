package com.example.hotel_jpa.services.impls;

import com.example.hotel_jpa.models.CheckIn;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.repositories.ICheckInRepository;
import com.example.hotel_jpa.services.ICheckInService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CheckInServiceImpl implements ICheckInService {

    @Autowired
    private ICheckInRepository repository;

    @Override
    public CheckIn addCheckIn(CheckIn checkin) {
        return repository.saveAndFlush(checkin);
    }

    @Override
    public void delete(long id) {
        repository.deleteById( id);
    }

    @Override
    public CheckIn getByClient(Client client) {
        return repository.findByClient(client);
    }

    @Override
    public CheckIn getByRoom(Room room) {
        return repository.findByRoom(room);
    }

    @Override
    public CheckIn editCheckIn(CheckIn client) {
        return repository.saveAndFlush(client);
    }

    @Override
    public List<CheckIn> getAll() {
        return repository.findAll();
    }

    public ObservableList<CheckIn> getAllObserved() {
        return FXCollections.observableArrayList(repository.findAll());
    }

    @Transactional
    public void setState(Long id, CheckIn.State state) {
        repository.setState(id, state);
    }

    public ObservableList<CheckIn> getAllActive() {
        ObservableList<CheckIn> retList = FXCollections.observableArrayList();
        retList.addAll(repository.findByState(CheckIn.State.ACTIVE));
        retList.addAll(repository.findByState(CheckIn.State.BOOKED));
        return retList;
    }
}
