package com.example.hotel_jpa.controllers;


import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.services.ICheckInService;
import com.example.hotel_jpa.services.IRoomService;
import com.example.hotel_jpa.services.impls.CheckInServiceImpl;
import com.example.hotel_jpa.services.impls.ClientServiceImpl;
import com.example.hotel_jpa.services.impls.RoomServiceImpl;
import com.example.hotel_jpa.view.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
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
        List<Client> list = Arrays.asList(
                new Client("Eugen", "Krivulia", "Kostiantynovich", "UK123"),
                new Client("Ivan", "Ivanov", "Ivanovich", "UK456"),
                new Client("Petr", "Petrov", "Petrovich", "UK789")
        );

        list.forEach(client -> clientService.addClient(client));

        List<Client> retList = clientService.getAll();
        retList.forEach(System.out::println);
    }

    public void setApp(App app) {
        this.app = app;
    }
}
