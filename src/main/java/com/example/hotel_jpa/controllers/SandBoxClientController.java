package com.example.hotel_jpa.controllers;


import com.example.hotel_jpa.models.CheckIn;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.services.ICheckInService;
import com.example.hotel_jpa.services.IRoomService;
import com.example.hotel_jpa.services.impls.CheckInServiceImpl;
import com.example.hotel_jpa.services.impls.ClientServiceImpl;
import com.example.hotel_jpa.services.impls.RoomServiceImpl;
import com.example.hotel_jpa.view.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class SandBoxClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private CheckInServiceImpl checkInService;

    @Autowired
    private RoomServiceImpl roomService;

    private App app;

    public void makeSomeMagic(){

        List<Room> listRoom = roomService.getAll();
        List<Client> listClient = clientService.getAll();

        List<CheckIn> listCheckIn = Arrays.asList(
                new CheckIn(listClient.get(0), listRoom.get(0), LocalDate.now(), LocalDate.now().plusDays(3)),
                new CheckIn(listClient.get(1), listRoom.get(1), LocalDate.now(), LocalDate.now().plusDays(4)),
                new CheckIn(listClient.get(2), listRoom.get(2), LocalDate.now(), LocalDate.now().plusDays(5))
        );

        listCheckIn.forEach(checkIn -> checkInService.addCheckIn(checkIn));

        checkInService.getAll().forEach(System.out::println);
    }

    public void setApp(App app) {
        this.app = app;
    }
}
