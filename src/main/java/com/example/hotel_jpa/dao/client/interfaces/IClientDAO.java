package com.example.hotel_jpa.dao.client.interfaces;

import com.example.hotel_jpa.exceptions.NoSuchClientException;
import com.example.hotel_jpa.models.Client;

import java.util.List;

public interface IClientDAO {
    Client create(Client client);

    List<Client> getAll();
    Client getClient(int id) throws NoSuchClientException;
    List<Client> getClient(String lastName) throws NoSuchClientException;

    Client update(Client client) throws NoSuchClientException;

    boolean delete(Client client);
}
