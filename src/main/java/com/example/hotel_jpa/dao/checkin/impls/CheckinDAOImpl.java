package com.example.hotel_jpa.dao.checkin.impls;

import com.example.hotel_jpa.dao.checkin.interfaces.ICheckinDAO;
import com.example.hotel_jpa.exceptions.NoSuchCheckinException;
import com.example.hotel_jpa.models.CheckIn;
import com.example.hotel_jpa.models.datasources.DataSourceChekIn;

import java.util.ArrayList;
import java.util.List;

public class CheckinDAOImpl implements ICheckinDAO {
    DataSourceChekIn dataSourceChekIn = new DataSourceChekIn();

    @Override
    public CheckIn create(CheckIn checkIn) {
        return dataSourceChekIn.add(checkIn);
    }

    @Override
    public List<CheckIn> getAll() {
        return dataSourceChekIn.getCheckInsList();
    }

    @Override
    public CheckIn getCheckIn(int id) throws NoSuchCheckinException {
        if (dataSourceChekIn.getTopId() > id) throw new NoSuchCheckinException("Wrong ID");
        for (CheckIn checkIn: dataSourceChekIn.getCheckInsList()){
            if (checkIn.getId() == id)
                return checkIn;
        }
        throw new NoSuchCheckinException("This should be never throws!");
    }

    @Override
    public List<CheckIn> getCheckInsByClient(int idClient) throws NoSuchCheckinException {
        List<CheckIn> retList = new ArrayList<>();
        for (CheckIn checkIn: dataSourceChekIn.getCheckInsList()){
            if (checkIn.getIdClient() == idClient)
                retList.add(checkIn);
        }
        if (retList.isEmpty()) throw new NoSuchCheckinException("List empty");
        return retList;
    }

    @Override
    public List<CheckIn> getCheckInsByRoom(int idRoom) throws NoSuchCheckinException {
        List<CheckIn> retList = new ArrayList<>();
        for (CheckIn checkIn: dataSourceChekIn.getCheckInsList()){
            if (checkIn.getIdRoom() == idRoom)
                retList.add(checkIn);
        }
        if (retList.isEmpty()) throw new NoSuchCheckinException("List empty");
        return retList;
    }

    @Override
    public CheckIn update(CheckIn checkIn) throws NoSuchCheckinException {
        CheckIn old = this.getCheckIn(checkIn.getId());
        old = checkIn;
        return old;
    }

    @Override
    public boolean delete(CheckIn checkIn) {
        return dataSourceChekIn.remove(checkIn);
    }
}
