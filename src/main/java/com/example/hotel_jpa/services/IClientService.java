package com.example.hotel_jpa.services;

import com.example.hotel_jpa.models.Client;

import java.util.List;

public interface IClientService {

    Client addClient(Client client);
    void delete(long id);
    Client getByLastName(String name);
    Client editClient(Client client);
    List<Client> getAll();
}
