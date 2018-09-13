package com.example.hotel_jpa.dao.checkin.interfaces;

import com.example.hotel_jpa.exceptions.NoSuchCheckinException;
import com.example.hotel_jpa.models.CheckIn;

import java.util.List;

public interface ICheckinDAO {
    CheckIn create(CheckIn checkIn);

    List<CheckIn> getAll();
    CheckIn getCheckIn(int id) throws NoSuchCheckinException;
    List<CheckIn> getCheckInsByClient(int idClient) throws NoSuchCheckinException;
    List<CheckIn> getCheckInsByRoom(int idRoom) throws NoSuchCheckinException;

    CheckIn update(CheckIn checkIn) throws NoSuchCheckinException;

    boolean delete(CheckIn checkIn);
}
