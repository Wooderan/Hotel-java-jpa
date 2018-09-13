package com.example.hotel_jpa.services.chekin.impls;

import com.example.hotel_jpa.dao.checkin.impls.CheckinDAOImpl;
import com.example.hotel_jpa.dao.checkin.interfaces.ICheckinDAO;
import com.example.hotel_jpa.exceptions.NoSuchCheckinException;
import com.example.hotel_jpa.models.CheckIn;

import java.util.List;

public class CheckInService implements ICheckinDAO {
    CheckinDAOImpl checkinDAO = new CheckinDAOImpl();

    @Override
    public CheckIn create(CheckIn checkIn) {
        return checkinDAO.create(checkIn);
    }

    @Override
    public List<CheckIn> getAll() {
        return checkinDAO.getAll();
    }

    @Override
    public CheckIn getCheckIn(int id) throws NoSuchCheckinException {
        return checkinDAO.getCheckIn(id);
    }

    @Override
    public List<CheckIn> getCheckInsByClient(int idClient) throws NoSuchCheckinException {
        return checkinDAO.getCheckInsByClient(idClient);
    }

    @Override
    public List<CheckIn> getCheckInsByRoom(int idRoom) throws NoSuchCheckinException {
        return checkinDAO.getCheckInsByClient(idRoom);
    }

    @Override
    public CheckIn update(CheckIn checkIn) throws NoSuchCheckinException {
        return checkinDAO.update(checkIn);
    }

    @Override
    public boolean delete(CheckIn checkIn) {
        return false;
    }
}
