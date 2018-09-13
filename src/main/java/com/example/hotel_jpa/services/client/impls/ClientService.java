package com.example.hotel_jpa.services.client.impls;

import com.example.hotel_jpa.dao.client.impls.ClientDAOImpl;
import com.example.hotel_jpa.dao.client.interfaces.IClientDAO;
import com.example.hotel_jpa.exceptions.NoSuchClientException;
import com.example.hotel_jpa.models.Client;

import java.util.List;

public class ClientService implements IClientDAO {
    ClientDAOImpl clientDAO = new ClientDAOImpl();

    @Override
    public Client create(Client client) {
        return clientDAO.create(client);
    }

    @Override
    public List<Client> getAll() {
        return clientDAO.getAll();
    }

    @Override
    public Client getClient(int id) throws NoSuchClientException {
        return clientDAO.getClient(id);
    }

    @Override
    public List<Client> getClient(String lastName) throws NoSuchClientException {
        return clientDAO.getClient(lastName);
    }

    @Override
    public Client update(Client client) throws NoSuchClientException {
        return clientDAO.update(client);
    }

    @Override
    public boolean delete(Client client) {
        return clientDAO.delete(client);
    }
}
